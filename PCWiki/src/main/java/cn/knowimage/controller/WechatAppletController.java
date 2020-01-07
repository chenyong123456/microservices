package cn.knowimage.controller;

import cn.knowimage.pojo.PathwayInfo;
import cn.knowimage.service.PathwayInfoService;
import cn.knowimage.service.redis.RedisService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 功能:主要为微信小程序提供相应的数据
 * @author 啊勇
 */
@CrossOrigin
@Controller
public class WechatAppletController {
    /**
     * 注入服务层的数据w
     */
    @Autowired
    public PathwayInfoService pathway_InfoService;
    /**
     * 注入redis的服务
     */
    @Autowired
    public RedisService redisService;
    /**
     * 小褚WechatApplet的缓存-->总文件夹
     */
    @Value("${WechatApplet}")
    public String WechatApplet;
    /**
     * 小褚微信小程序的wiki请求的接口和PC端的
     * @param p_name 根据pathway_index和pathway_name进行查询pathway_info查询数据库
     * @return 返回微信小程序所需要的wiki显示的数据
     */
    @RequestMapping("/applet")
    @ResponseBody
    public JSONObject wechatApp(String p_name,String p_index) {
        System.out.println(p_name);
        System.out.println(p_index);
        //pathWay_name = "小儿气管（支气管）异物";
        JSONObject lastJson = new JSONObject();
        JSONObject error = new JSONObject();
        if(p_name == null || "undefined".equals(p_name)){
            error.put("error","查询失败,请联系管理员进行维护!");
            return error;
        }
        //判断redis中是否为空
//        if(!redisService.exists(WechatApplet + ":" + "wechatApp:" + p_name)){//redis中不存在数据,将数据库中的数据全部加入到redis中
//            JSONObject redisJson = new JSONObject();
//            //从数据库中查询出所有数据
//            List<Pathway_Info> list = pathway_InfoService.findAll();
//            //通过for循环将所有数据都加入redis中
//            for (int i = 0; i <list.size(); i++) {
//                //这个数据插入到redis中
//                redisJson.put("pathway_id", list.get(i).getPathway_id());
//                redisJson.put("pathway_index", list.get(i).getPathway_index());
//                redisJson.put("pathway_name", list.get(i).getPathway_name());
//                redisJson.put("first_diagnose", list.get(i).getFirst_diagnosis());
//                redisJson.put("suitable_subject_disc", list.get(i).getSuitable_subject_disc());
//                redisJson.put("diagnosis", list.get(i).getDiagnosis());
//                //这里不是一个字段进行数据的处理,去除带num的数据
//                //处理treatment_choice字段中num数据
//                JSONObject dealJson = JSONObject.fromObject(list.get(i).getTreatment_choice());
//                dealJson.getJSONObject("scenario").remove("num");
//                redisJson.put("treatment_choice", dealJson.toString());
//
//                //处理treatment_days字段的数据-->在这里进行id_0中加入一个字段"tag":"treatment_days",
//                dealJson = JSONObject.fromObject(list.get(i).getTreatment_days());
//                JSONObject scenario = dealJson.getJSONObject("scenario");
//                int num = scenario.getInt("num");
//                for (int j = 0; j < num; j++) {
//                    JSONObject id_ = scenario.getJSONObject(String.format("id_%d",j));
//                    id_.put("tag","treatment_days");
//                    scenario.put(String.format("id_%d",j),id_);
//                }
//                dealJson.put("scenario",scenario);
//                dealJson.getJSONObject("scenario").remove("num");
//                redisJson.put("treatment_days", dealJson.toString());
//
//                //处理treatment_entry_standard字段的数据
//                dealJson = JSONObject.fromObject(list.get(i).getTreatment_entry_standard());
//                dealJson.remove("num");
//                redisJson.put("treatment_entry_standard", dealJson.toString());
//
//                redisJson.put("type", list.get(i).getType());
//                System.out.println("************");
//                System.out.println("username:::"+list.get(i).getType());
//                System.out.println("*****************");
//
//                redisJson.put("drug_use_period", list.get(i).getDrug_use_period());
//
//                //处理prep_treatment_common字段的数据
//                dealJson = JSONObject.fromObject(list.get(i).getPrep_treatment_common());
//                dealJson.getJSONObject("scenario").remove("num");
//                redisJson.put("prep_treatment_common", dealJson.toString());
//
//                //处理prep_treatment_drug_usage字段的数据
//                dealJson = JSONObject.fromObject(list.get(i).getPrep_treatment_drug_usage());
//                dealJson.getJSONObject("antibio_usage").remove("num");
//                dealJson.getJSONObject("anaesthetic_usage").remove("num");
//                dealJson.getJSONObject("otherdrugs_usage").remove("num");
//                redisJson.put("prep_treatment_drug_usage", dealJson.toString());
//
//                //处理prep_treatment_extension字段的数据
//                dealJson = JSONObject.fromObject(list.get(i).getPrep_treatment_extension());
//                dealJson.remove("num");
//                redisJson.put("prep_treatment_extension", dealJson.toString());
//                //处理treatment字段的数据
//                dealJson = JSONObject.fromObject(list.get(i).getTreatment());
//                int scenario_num = dealJson.getJSONObject("scenario").getInt("scenario_num");
//                dealJson.getJSONObject("scenario").remove("scenario_num");
//                //动态循环删除scenario_id_0中的带num的值
//                for (int j = 0; j < scenario_num; j++) {
//                    dealJson.getJSONObject("scenario").getJSONObject(String.format("scenario_id_%d", j)).getJSONObject("treatment_plan").remove("num");
//                }
//                redisJson.put("treatment", dealJson.toString());
//
//                //处理drug_usage字段的数据
//                dealJson = JSONObject.fromObject(list.get(i).getDrug_usage());
//                dealJson.getJSONObject("antibio_usage").remove("num");
//                dealJson.getJSONObject("anaesthetic_usage").remove("num");
//                dealJson.getJSONObject("otherdrugs_usage").remove("num");
//                redisJson.put("drug_usage", dealJson.toString());
//
//                //处理after_medical_treatment字段的数据
//                dealJson = JSONObject.fromObject(list.get(i).getAfter_medical_treatment());
//                dealJson.getJSONObject("scenario").remove("num");
//                redisJson.put("after_medical_treatment", dealJson.toString());
//
//                //处理after_treatment_drug_usage字段的数据
//                dealJson = JSONObject.fromObject(list.get(i).getAfter_treatment_drug_usage());
//                dealJson.getJSONObject("antibio_usage").remove("num");
//                dealJson.getJSONObject("anaesthetic_usage").remove("num");
//                dealJson.getJSONObject("otherdrugs_usage").remove("num");
//                redisJson.put("after_treatment_drug_usage", dealJson.toString());
//
//                redisJson.put("discharge_criteria", list.get(i).getDischarge_criteria());
//                redisJson.put("other_notice", list.get(i).getOther_notice());
//
//                redisService.set(WechatApplet + ":" + "wechatApp:" + list.get(i).getPathway_name(), redisJson.toString());
//                //查询数据库的数据就写入redis
//                //redisService.set(WechatApplet + ":" + "wechatApp:" + list.get(i).getPathway_name(), redisJson.toString());
//            }
//        }

        System.out.println(p_name);
        //原数据为WechatApplet + ":"     其中:被修改
        if (!redisService.exists(WechatApplet + "" + "wechatApp:" + p_name+p_index)) {//redis中不存在数据,去mysql中查询
            //从数据库中查询出来的数据
            List<PathwayInfo> list = pathway_InfoService.selectByPathway_nameApplet(p_name,p_index);
            String url = "http://192.168.50.5:9999/tableImage/";
            //获得表格table数据
            String table = pathway_InfoService.selectTableByApplet(p_name,p_index);
            System.out.println(table);
            JSONObject table_info = JSONObject.fromObject(table);
            System.out.println("table===="+table);
            if(list == null || list.size() == 0){
                JSONObject last = new JSONObject();
                last.put("status",500);
                last.put("msg","请输入正确的病例路径名!");
                return last;
            }else {
                //在这里进行的数据的处理微信小程序取去除带num的数据字段
                //model.addAttribute("pathway_Info", list);
//                lastJson.put("table",table);
                lastJson.put("pathway_id", list.get(0).getPathway_id());
                lastJson.put("pathway_index", list.get(0).getPathway_index());
                lastJson.put("pathway_name", list.get(0).getPathway_name());
                lastJson.put("first_diagnose", list.get(0).getFirst_diagnosis());
                lastJson.put("suitable_subject_disc", list.get(0).getSuitable_subject_disc());
                lastJson.put("diagnosis", list.get(0).getDiagnosis());

                //这里不是一个字段进行数据的处理,去除带num的数据
                //处理treatment_choice字段中num数据
                JSONObject dealJson = JSONObject.fromObject(list.get(0).getTreatment_choice());
                dealJson.getJSONObject("scenario").remove("num");
                JSONObject jsonObject = JSONObject.fromObject(dealJson.toString());
                for (int i=0;i<table_info.getInt("table_num");i++) {
                    int table_num = 0;
                    if (dealJson.toString().contains(table_info.getJSONObject("table_"+i).getString("table_prefix"))){
                        lastJson.put("table",url+"012011078901_@GRID@_1.png");

                    }
                }
                lastJson.put("table",url+"012011078901_@GRID@_1.png");
                lastJson.put("treatment_choice",jsonObject);

                //处理treatment_days字段的数据

                //处理treatment_days字段的数据-->在这里进行id_0中加入一个字段"tag":"treatment_days",
                dealJson = JSONObject.fromObject(list.get(0).getTreatment_days());
                JSONObject scenario = dealJson.getJSONObject("scenario");
                int num = scenario.getInt("num");
                for (int j = 0; j < num; j++) {
                    JSONObject id_ = scenario.getJSONObject(String.format("id_%d", j));
                    id_.put("tag", "treatment_days");
                    scenario.put(String.format("id_%d", j), id_);
                }
                dealJson.put("scenario", scenario);
                dealJson.getJSONObject("scenario").remove("num");
//          dealJson = JSONObject.fromObject(list.get(0).getTreatment_days());
//          dealJson.getJSONObject("scenario").remove("num");
                lastJson.put("treatment_days", dealJson.toString());

                //处理treatment_entry_standard字段的数据
                dealJson = JSONObject.fromObject(list.get(0).getTreatment_entry_standard());
                dealJson.remove("num");
                lastJson.put("treatment_entry_standard", dealJson.toString());

                lastJson.put("type", list.get(0).getType());

                lastJson.put("drug_use_period", list.get(0).getDrug_use_period());

                //处理prep_treatment_common字段的数据

                //在这里进行duration中加入一个字段"tag":"treatment_days",
                JSONObject prepTreatmentCommonTrans = JSONObject.fromObject(list.get(0).getPrep_treatment_common());
                JSONObject ptcscenario = prepTreatmentCommonTrans.getJSONObject("scenario");
                int ptcnum = ptcscenario.getInt("num");
                for (int i = 0; i < ptcnum; i++) {
                    JSONObject id_ = ptcscenario.getJSONObject(String.format("id_%d", i));
                    JSONObject duration = id_.getJSONObject("duration");
                    duration.put("tag", "prep_treatment_common");
                    id_.put("duration", duration);
                    ptcscenario.put(String.format("id_%d", i), id_);
                }
                prepTreatmentCommonTrans.put("scenario", ptcscenario);
//          dealJson = JSONObject.fromObject(list.get(0).getPrep_treatment_common());
                prepTreatmentCommonTrans.getJSONObject("scenario").remove("num");
                lastJson.put("prep_treatment_common", prepTreatmentCommonTrans.toString());

                //处理prep_treatment_drug_usage字段的数据
                dealJson = JSONObject.fromObject(list.get(0).getPrep_treatment_drug_usage());
                dealJson.getJSONObject("antibio_usage").remove("num");
                dealJson.getJSONObject("anaesthetic_usage").remove("num");
                dealJson.getJSONObject("otherdrugs_usage").remove("num");
                lastJson.put("prep_treatment_drug_usage", dealJson.toString());

                //处理prep_treatment_extension字段的数据
                dealJson = JSONObject.fromObject(list.get(0).getPrep_treatment_extension());
                dealJson.remove("num");
                lastJson.put("prep_treatment_extension", dealJson.toString());

                //处理treatment字段的数据
                dealJson = JSONObject.fromObject(list.get(0).getTreatment());
                int scenario_num = dealJson.getJSONObject("scenario").getInt("scenario_num");
                dealJson.getJSONObject("scenario").remove("scenario_num");
                //动态循环删除scenario_id_0中的带num的值
                for (int i = 0; i < scenario_num; i++) {
                    dealJson.getJSONObject("scenario").getJSONObject(String.format("scenario_id_%d", i)).getJSONObject("treatment_plan").remove("num");
                }

                //在这里进行duration中加入一个字段"tag":"treatment",
                JSONObject treatmentDuration = dealJson.getJSONObject("duration");
                treatmentDuration.put("tag", "treatment");
                dealJson.put("duration", treatmentDuration);
                lastJson.put("treatment", dealJson.toString());

                //处理drug_usage字段的数据
                dealJson = JSONObject.fromObject(list.get(0).getDrug_usage());
                dealJson.getJSONObject("antibio_usage").remove("num");
                dealJson.getJSONObject("anaesthetic_usage").remove("num");
                dealJson.getJSONObject("otherdrugs_usage").remove("num");
                lastJson.put("drug_usage", dealJson.toString());

                //处理after_medical_treatment字段的数据
                //在这里进行duration中加入一个字段"tag":"after_medical_treatment",
                JSONObject after_medical_treatmentTras = JSONObject.fromObject(list.get(0).getAfter_medical_treatment());
                JSONObject after_medical_treatmentDuration = after_medical_treatmentTras.getJSONObject("duration");
                after_medical_treatmentDuration.put("tag", "after_medical_treatment");
                after_medical_treatmentTras.put("duration", after_medical_treatmentDuration);

//          dealJson = JSONObject.fromObject(list.get(0).getAfter_medical_treatment());
                after_medical_treatmentTras.getJSONObject("scenario").remove("num");
                lastJson.put("after_medical_treatment", after_medical_treatmentTras.toString());

                //处理after_treatment_drug_usage字段的数据
                dealJson = JSONObject.fromObject(list.get(0).getAfter_treatment_drug_usage());
                dealJson.getJSONObject("antibio_usage").remove("num");
                dealJson.getJSONObject("anaesthetic_usage").remove("num");
                dealJson.getJSONObject("otherdrugs_usage").remove("num");
                lastJson.put("after_treatment_drug_usage", dealJson.toString());


                lastJson.put("discharge_criteria", list.get(0).getDischarge_criteria());
                lastJson.put("other_notice", list.get(0).getOther_notice());
                lastJson.put("additional_field", list.get(0).getAdditional_field());
                //查询数据库的数据就写入redis
                redisService.set(WechatApplet + ":" + "wechatApp:" + p_name+list.get(0).getPathway_index(), lastJson.toString());
            }
        }else{
            //redis中有就直接从redis中拿
            lastJson = JSONObject.fromObject(redisService.get(WechatApplet+":"+"wechatApp:"+p_name+p_index));
        }
            JSONObject json = new JSONObject();
            json.put("data", lastJson);
            return json;
    }
    /**
     * 查寻Pathway_Info表的指定pathway_name的相关的name名字-->会出现名称的下拉列表 模糊查询
     * @param list_name 输入要查询的字段数
     * @return 返回一系列的pathway_name和pathway_index
     */
    @RequestMapping("/appletByOnly_Pathway_name")
    @ResponseBody
    public String wechatAppSelectByPathway_name(String list_name) {
        JSONObject error = new JSONObject();
        JSONObject result = new JSONObject();
        System.out.println(list_name);
        if(list_name == null || list_name.equals("")){
            error.put("content",new JSONArray());
            error.put("index",new JSONArray());
            return error.toString();
        }
        //替换字符串中含有'的字符
        list_name = list_name.replace("\'","");
        //记得加redis
        System.out.println(list_name);
      //  if(!redisService.exists(WechatApplet+":"+"wechatAppSelectByPathway_name:"+list_name)) {//redis中不存在
            List<PathwayInfo> list = pathway_InfoService.selectByPathway_name(list_name);
            JSONArray arrays = new JSONArray();
            JSONArray lengthArray = new JSONArray();
            for (PathwayInfo pathway_Info : list) {
                arrays.add(pathway_Info.getPathway_name());
                lengthArray.add(pathway_Info.getPathway_index());
            }
            result.put("content",arrays);
            result.put("index",lengthArray);
           // redisService.set(WechatApplet+":"+"wechatAppSelectByPathway_name:"+list_name,arrays.toString());
            return result.toString();
//        }else{//redis中存在数据
//            return (String)redisService.get(WechatApplet+":"+"wechatAppSelectByPathway_name:"+list_name);
//        }
    }


}
