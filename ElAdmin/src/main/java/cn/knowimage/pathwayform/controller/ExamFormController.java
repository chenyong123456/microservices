package cn.knowimage.pathwayform.controller;

import cn.knowimage.pathwayform.dataoutputconvert.DateConvert;
import cn.knowimage.pathwayform.dataoutputconvert.ExamformDataOutput;
import cn.knowimage.pathwayform.jsonconvert.InfoInHospitalJson;
import cn.knowimage.pathwayform.jsonconvert.StageJson;
import cn.knowimage.pathwayform.jsonconvert.TreatmentInfoJson;
import cn.knowimage.pathwayform.po.ExamFormPojo;
import cn.knowimage.pathwayform.po.PathwayPojo;
import cn.knowimage.pathwayform.service.ExamFormService;
import cn.knowimage.pathwayform.vo.ExamForm;
import cn.knowimage.util.HttpClientUtil;
import cn.knowimage.util.JsonUtils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * ExamForm 执行表单业务处理类
 *
 * @author lyx
 * @date 2019/08/27
 */
@Controller
@CrossOrigin
public class ExamFormController {
    @Autowired
    ExamFormService examFormService;

    /**
     * 医嘱表单单独录入
     *
     * @return
     */
    @RequestMapping("/originalform")
    public String operationOriginalform() {
        return "originalform";
    }

    /**
     * 医嘱表单录入
     *
     * @return
     */
    @RequestMapping("/form")
    public String operationExam() {
        return "form";
    }

    /**
     * 医嘱表单显示
     *
     * @return
     */
    @RequestMapping("/index")
    public String selectExamForm() {
        return "index";
    }

    /**
     * 数据显示 住院信息（日历的默认选中）
     *
     * @return JSONObject result
     */
    @RequestMapping("/getExamFormData")
    @ResponseBody
    public JSONObject getExamFormData() {
        JSONObject result = new JSONObject();
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        //默认住院日期为今天
        String admissionDate = simpleDateFormat.format(date);
        //默认出院日期为今天+10天
        //Date afterDate = DateConvert.dateAdd(new Date(),Calendar.DATE,10);
        //String dischargeDate = simpleDateFormat.format(afterDate);
        //定义字符串变量  接收住院信息
        String infoHospitalJson = InfoInHospitalJson.getInfoHospitalJson("", "", admissionDate, admissionDate, "天", "1", "10");
        result.put("info_in_hospital", infoHospitalJson);
        return result;
    }

    /**
     * 根据id查询路径表单
     *
     * @param cp_id 路径表单id
     * @return JSONObject jsonObject 相关信息
     */
    @RequestMapping("/getExamFormDataById")
    @ResponseBody
    public JSONObject getExamFormDataById(@RequestParam(defaultValue = "0") String cp_id) {
        System.out.println("数据呢" + cp_id);
        ExamForm currentExamForm = new ExamForm();
        currentExamForm.setCp_id(cp_id);
        //根据id查询临床路径对象
        ExamForm examForm = examFormService.getExamFormById(currentExamForm);
        JSONObject jsonObject = null;
        //如果对象查询到了
        if (examForm != null) {
            jsonObject = new JSONObject();
            jsonObject.put("cp_id", "" + examForm.getCp_id());
            jsonObject.put("form_manager", examForm.getForm_manager());
            jsonObject.put("form_name", examForm.getForm_name());
            jsonObject.put("suitable_subject_disc", examForm.getSuitable_subject_disc());
            jsonObject.put("severity_level", "" + examForm.getSeverity_level());
            jsonObject.put("standard_entry_days", JSONObject.fromObject(examForm.getInfo_in_hospital()).getJSONObject("standard_entry_days").getString("time_unit"));
            jsonObject.put("timeMin", JSONObject.fromObject(examForm.getInfo_in_hospital()).getJSONObject("standard_entry_days").getString("min"));
            jsonObject.put("timeMax", JSONObject.fromObject(examForm.getInfo_in_hospital()).getJSONObject("standard_entry_days").getString("max"));
/*            if(examForm.getInfo_in_hospital() != null && !"".equals(examForm.getInfo_in_hospital())){
                 JSONObject hospitalInfo = JSONObject.fromObject(examForm.getInfo_in_hospital());
                 jsonObject.put("standard_entry_days",hospitalInfo.getJSONObject("standard_entry_days").getString("time_unit"));
            }else{
                jsonObject.put("standard_entry_days","");
            }*/
            //将阶段信息放入json对象,转换为前台需要的格式
            //定义options2为JsonArray

          //  System.out.println("前端需要显示的格式" + JSONObject.fromObject(showTimeString));
          //  jsonObject.put("stage", ExamformDataOutput.getStageJsonObjet(examForm, JSONObject.fromObject(showTimeString)));
            jsonObject.put("stage", ExamformDataOutput.getStageJsonObjet(examForm));
        } else {
            jsonObject = new JSONObject();
            jsonObject.put("status", 404);
            jsonObject.put("msg", "无数据");
        }
        return jsonObject;
    }

    /**
     * 根据id,日期查询路径表单
     *
     * @param cp_id    路径表单id
     * @param dateInfo 当前住院日期
     * @return JSONObject jsonObject 路径表单信息
     */
    @RequestMapping("/getExamFormDataByStage")
    @ResponseBody
    public JSONObject getExamFormDataByStage(@RequestParam(defaultValue = "0", required = false) String cp_id,
                                             @RequestParam(defaultValue = "", required = false) String dateInfo) {
        System.out.println("cp_id"+cp_id);
        System.out.println("dateInfo"+dateInfo);
        ExamForm examForms = new ExamForm();
        examForms.setCp_id(cp_id);
        //如果前台传过来的日期为空
        if (dateInfo == null || dateInfo.equals("")) {
            //默认选中日期为今天
            dateInfo = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        }
        //定义变量接收 日期相差天数
        long day = DateConvert.dateDiff(new SimpleDateFormat("yyyy-MM-dd").format(new Date()), dateInfo, "yyyy-MM-dd");
        //转换为int整形  接收当前住院为第几天
        int currentDay = new Long(day).intValue() + 1;
        //定义json对象 存放路径表单信息
        JSONObject jsonObject = new JSONObject();
        //定义json对象 存放提示信息
        JSONObject error = new JSONObject();


        //如果对象不为空
        if (examFormService.getExamFormById(examForms) != null) {
            //根据id查询路径表单对象
            ExamForm examForm = examFormService.getExamFormById(examForms);
            System.out.println("examForm==============>"+examForm.toString());

            jsonObject.put("form_name", examForm.getForm_name());
            jsonObject.put("suitable_subject_disc", examForm.getSuitable_subject_disc());
            jsonObject.put("severity_level", examForm.getSeverity_level());
            jsonObject.put("patient_info", examForm.getPatient_info());
            jsonObject.put("info_in_hospital", ExamformDataOutput.getInHospitalDataJsonObject(examForm.getInfo_in_hospital()));
            //将该日期的治疗阶段信息 存放于json对象
            jsonObject.put("treatment_info", ExamformDataOutput.getTreatmentInfoDataJsonObjet(examForm.getTreatment_info(), examForm.getInfo_in_hospital(), currentDay));
            jsonObject.put("form_manager", examForm.getForm_manager());
            //取出该日期的治疗阶段json对象
            JSONObject treatmentInfoNull = jsonObject.getJSONObject("treatment_info");
            //如果该日期不存在治疗信息
            if (treatmentInfoNull.size() == 0 || treatmentInfoNull == null) {
                error.put("state", 500);
                error.put("msg", "该日期不存在住院信息!");
                return error;
            }
        } else { //如果对象为空 即未查询到
            error.put("status", 404);
            error.put("msg", "请输入正确的id查询!");
            return error;

        }
        return jsonObject;
    }

    /**
     * 根据选中的id以及日期修改阶段数据（该功能暂时屏蔽）
     *
     * @param cp_id
     * @param primary_diagnosi
     * @param long_term_orders
     * @param temporary_orders
     * @param discharge_orders
     * @param notification
     * @param major_nursing_work
     * @param disease_variation_status
     * @param reason
     * @param nurse_onduty
     * @param day_shiftName
     * @param night_shiftSName
     * @param night_shiftBName
     * @param time_undefinedName
     * @param physician_name
     * @param current_time
     * @param dateInfo
     * @return
     */
    @RequestMapping("/editExamFormDataById")
    @ResponseBody
    public JSONObject editExamFormDataById(@RequestParam(required = false) String cp_id,
                                           @RequestParam(required = false) String primary_diagnosi,
                                           @RequestParam(required = false) String long_term_orders,
                                           @RequestParam(required = false) String temporary_orders,
                                           @RequestParam(required = false) String discharge_orders,
                                           @RequestParam(required = false) String notification,
                                           @RequestParam(required = false) String major_nursing_work,
                                           @RequestParam(required = false) String disease_variation_status,
                                           @RequestParam(required = false) String reason,
                                           @RequestParam(required = false) String nurse_onduty,
                                           @RequestParam(required = false) String day_shiftName,
                                           @RequestParam(required = false) String night_shiftSName,
                                           @RequestParam(required = false) String night_shiftBName,
                                           @RequestParam(required = false) String time_undefinedName,
                                           @RequestParam(required = false) String physician_name,
                                           @RequestParam(required = false) String current_time,
                                           @RequestParam(required = false) String dateInfo) {

        //如果前台传过来的日期为空
        if (dateInfo == null || dateInfo.equals("")) {
            //默认选中日期为今天
            dateInfo = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        }
        //定义变量接收 日期相差天数（默认住院日期为今天）
        long day = DateConvert.dateDiff(new SimpleDateFormat("yyyy-MM-dd").format(new Date()), dateInfo, "yyyy-MM-dd");
        //转换为int整形  接收当前住院为第几天
        int currentDay = new Long(day).intValue() + 1;
        String[] str = current_time.split("-");
        JSONObject jsonObject = new JSONObject();
        ExamForm examForm = new ExamForm();
        examForm.setCp_id(cp_id);

        if (examFormService.getExamFormById(examForm) != null) {
            //JSONObject examResult = JSONObject.fromObject(examFormService.getExamForm(examForm));
            JSONObject examObject = JSONObject.fromObject(examFormService.getExamFormById(examForm).getTreatment_info());
            // System.out.println(examObject.toString());
            int num = examObject.getInt("stage_num");
            for (int i = 0; i < num; i++) {
                JSONObject examJSONObject = examObject.getJSONObject(String.format("stage_%d", i));
                int currentMin = examJSONObject.getJSONObject("current_time").getInt("min");
                int currentMax = examJSONObject.getJSONObject("current_time").getInt("max");
                if (currentDay <= currentMax && currentDay >= currentMin) {
                    jsonObject = StageJson.getStageJson(primary_diagnosi, long_term_orders, temporary_orders, discharge_orders,
                            notification, major_nursing_work, disease_variation_status, reason, nurse_onduty, day_shiftName,
                            night_shiftSName, night_shiftBName, time_undefinedName, physician_name, current_time);
                    ExamForm updateTreatment = new ExamForm();
                    updateTreatment.setCp_id(cp_id);
                    examObject.put(String.format("stage_%d", i), jsonObject);
                    updateTreatment.setTreatment_info(examObject.toString());
                    //修改
                    examFormService.updateTreatmentById(updateTreatment);
                    jsonObject.put("status", 100);
                    jsonObject.put("msg", "修改成功");
                }
            }
        } else {
            jsonObject.put("status", 401);
            jsonObject.put("message", "请先查询出再做修改");

        }
        return jsonObject;
    }

    @RequestMapping("/add")
    @ResponseBody
    public void add(PathwayPojo pathwayPojo) {
        String json = JsonUtils.objectToJson(pathwayPojo);
        System.out.println(json);
        Map<String, Object> map = new HashMap<>();
        map.put("pathwayPojo", json);
        HttpClientUtil.doGetByObject("http://192.168.50.5:9999/insertInfo", map);
    }

    @RequestMapping("/showNewForm")
    @ResponseBody
    public JSONObject showNewForm(ExamFormPojo examFormPojo) {
        JSONObject json = new JSONObject();
        System.out.println(examFormPojo.getStage());
        JSONObject json2 = JSONObject.fromObject(examFormPojo.getStage());
        json.put("options2", json2.getJSONArray("options2"));
        json.put("prefix_description", json2.getJSONArray("prefix_description"));
        return json;
    }

    @RequestMapping("/addOriginalNewForm")
    @ResponseBody
    public JSONObject addNewForm(ExamFormPojo examFormPojo) {
        System.out.println("examform单独进了新增");
        JSONObject jsonObject = new JSONObject();
        System.out.println("id>>>>>>>>>>" + examFormPojo.getCp_id());
        System.out.println("isOperation===========>" + examFormPojo.getIsOperation());
        System.out.println("operationNum==========>" + examFormPojo.getOperationNum());
        System.out.println("stage=============》" + examFormPojo.getStage());
        System.out.println("form_name=============>" + examFormPojo.getForm_name());
        System.out.println("examform_suitable_subject_disc=============>" + examFormPojo.getSuitable_subject_disc());
        System.out.println("pathwayinfo_suitable_subject=======>" + examFormPojo.getSuitable_subject_disc());
        System.out.println("standard_entry_days=============>" + examFormPojo.getStandard_entry_days());
        System.out.println("timeMin=============>" + examFormPojo.getTimeMin());
        System.out.println("timeMax=============>" + examFormPojo.getTimeMax());
        System.out.println("severity_level=============>" + examFormPojo.getSeverity_level());
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        String recordTime = simpleDateFormat.format(date);
        //默认出院日期为今天+10天
        Date afterDate = DateConvert.dateAdd(new Date(), Calendar.DATE, 10);
        String dischargeDate = simpleDateFormat.format(afterDate);
        String infoInHospitalJson = InfoInHospitalJson.getInfoHospitalJson
                ("", "", recordTime, dischargeDate, examFormPojo.getStandard_entry_days(), examFormPojo.getTimeMin(), examFormPojo.getTimeMax());
        System.out.println("infoInHospitalJson>>>>>>>>>>>>>>>>>>>>>>" + infoInHospitalJson);
        ExamForm searchExam = new ExamForm();
        searchExam.setCp_id(examFormPojo.getCp_id());
        if (examFormService.getExamFormById(searchExam) == null) {
            ExamForm addExamForm = new ExamForm(examFormPojo.getCp_id(), examFormPojo.getForm_name(), "",
                    examFormPojo.getSuitable_subject_disc(), Integer.parseInt(examFormPojo.getSeverity_level()), infoInHospitalJson,
                    TreatmentInfoJson.getTreatmentInfoJsonData(examFormPojo.getStage()), examFormPojo.getForm_manager(), recordTime);
            examFormService.addExamForm(addExamForm);
        } else {
            ExamForm updateExamForm = new ExamForm(examFormPojo.getCp_id(), examFormPojo.getForm_name(), "",
                    examFormPojo.getSuitable_subject_disc(), Integer.parseInt(examFormPojo.getSeverity_level()), infoInHospitalJson,
                    TreatmentInfoJson.getTreatmentInfoJsonData(examFormPojo.getStage()), examFormPojo.getForm_manager(), recordTime);
            examFormService.updateExamFormById(updateExamForm);
        }
        return jsonObject;
    }

    /**
     * 对路径表单进行操作(添加、修改)
     *
     * @param examFormPojo 执行路径表单对象
     * @return JSONObject jsonObject 表单对象
     */
    @RequestMapping("/operationExamFormData")
    @ResponseBody
    public JSONObject operationExamFormData(ExamFormPojo examFormPojo, PathwayPojo pathwayPojo) {
        //远程调用新增pathway_info的方法
        String json = JsonUtils.objectToJson(pathwayPojo);
        Map<String, Object> map = new HashMap<>();
        map.put("pathwayPojo", json);
        String s = HttpClientUtil.doGetByObject("http://192.168.50.5:9999/insertInfo", map);
        System.out.println("pl=================" + s);
        System.out.println("进了operationExamFormData方法");
        System.out.println("isOperation===========>" + examFormPojo.getIsOperation());
        System.out.println("operationNum==========>" + examFormPojo.getOperationNum());
        System.out.println("stage=============》" + examFormPojo.getStage());
        System.out.println("form_name=============>" + pathwayPojo.getPathway_name());
        System.out.println("examform_suitable_subject_disc=============>" + examFormPojo.getSuitable_subject_disc());
        System.out.println("pathwayinfo_suitable_subject=======>" + pathwayPojo.getSuitable_subject_disc());
        System.out.println("standard_entry_days=============>" + examFormPojo.getStandard_entry_days());
        System.out.println("timeMin=============>" + examFormPojo.getTimeMin());
        System.out.println("timeMax=============>" + examFormPojo.getTimeMax());
        System.out.println("severity_level=============>" + examFormPojo.getSeverity_level());
        String fileNumber = pathwayPojo.getFileNumber();//6-9; 文件编号
        if (pathwayPojo.getFileNumber().equals("")) {
            fileNumber = "0000" + fileNumber;
        } else if (fileNumber.length() == 1) {
            fileNumber = "000" + fileNumber;
        } else if (fileNumber.length() == 2) {
            fileNumber = "00" + fileNumber;
        } else if (fileNumber.length() == 3) {
            fileNumber = "" + "0" + fileNumber;
        }
        //定义字符串变量  存储版本编号
        String pathway_index = pathwayPojo.getPublisher() + pathwayPojo.getPublishYear() + fileNumber + pathwayPojo.getVersionNumber();
        System.out.println("cp_id=============>" + pathway_index);
        System.out.println("examecp_id======" + examFormPojo.getCp_id());
        Date date = new Date();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
        String recordTime = simpleDateFormat.format(date);
        //默认出院日期为今天+10天
        Date afterDate = DateConvert.dateAdd(new Date(), Calendar.DATE, 10);
        String dischargeDate = simpleDateFormat.format(afterDate);
        String infoInHospitalJson = InfoInHospitalJson.getInfoHospitalJson
                ("", "", recordTime, dischargeDate, examFormPojo.getStandard_entry_days(), examFormPojo.getTimeMin(), examFormPojo.getTimeMax());
        JSONObject jsonObject = new JSONObject();
        ExamForm examForm = new ExamForm();
        //如果未选择级别  默认将级别定为0
        if (examFormPojo.getSeverity_level() == null || examFormPojo.getSeverity_level().equals("")) {
            examFormPojo.setSeverity_level("0");
        }
        System.out.println("pathwayPojo.getSelectId()" + pathwayPojo.getSelectId());
        //如果在路径发布界面没有根据名字进行搜索 即没有获得搜索id（新增）
        if (pathwayPojo.getSelectId() == null || "".equals(pathwayPojo.getSelectId())) {
            examForm.setCp_id(pathway_index);
            System.out.println("cpid呢=======》" + examForm.getCp_id());
            //根据传过来的新增的id进行搜索，如果查询到，证明该版本号已存在
            if (examFormService.getExamFormById(examForm) != null) {
                jsonObject.put("status", "版本号已存在");
            } else {
                //对象不存在，进新增
                System.out.println("进了examform新增");
                ExamForm addExamForm = new ExamForm(pathway_index, pathwayPojo.getPathway_name(), "",
                        examFormPojo.getSuitable_subject_disc(), Integer.parseInt(examFormPojo.getSeverity_level()), infoInHospitalJson,
                        TreatmentInfoJson.getTreatmentInfoJsonData(examFormPojo.getStage()), pathwayPojo.getSubmitterid(), recordTime);
                System.out.println("新增的submitterid===" + pathwayPojo.getSubmitterid());
                System.out.println("新增的recordTime===" + recordTime);
                System.out.println("新增的提交者=====" + pathwayPojo.getSubmitter());
                examFormService.addExamForm(addExamForm);
            }
        } else {
            System.out.println("进了examform修改");
            ExamForm updateExamForm = new ExamForm(pathway_index, pathwayPojo.getPathway_name(), "",
                    examFormPojo.getSuitable_subject_disc(), Integer.parseInt(examFormPojo.getSeverity_level()), infoInHospitalJson,
                    TreatmentInfoJson.getTreatmentInfoJsonData(examFormPojo.getStage()), pathwayPojo.getSubmitterid(), recordTime);
            examFormService.updateExamFormById(updateExamForm);
        }
        System.out.println("输出"+jsonObject.toString());
        return jsonObject;
    }
}


