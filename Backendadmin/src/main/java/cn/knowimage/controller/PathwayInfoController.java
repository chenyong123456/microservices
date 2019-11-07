package cn.knowimage.controller;

import cn.knowimage.JsonPojo.MakeJson.MakeJsonPathway;
import cn.knowimage.pojo.PathwayInfo;
import cn.knowimage.pojo.ReceivePathway;
import cn.knowimage.service.PathwayInfoService;
import cn.knowimage.service.RecentWorkService;
import cn.knowimage.service.UserService;
import cn.knowimage.util.ClincialResult;
import cn.knowimage.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 彭雷2019.10.12
 * 主要表单提交
 */
@CrossOrigin
@Controller
@Slf4j
public class PathwayInfoController {
    @Autowired
    PathwayInfoService pathwayInfoService;
    @Autowired
    RecentWorkService recentWorkService;
    @Autowired
    UserService userService;
    /**
     *
     * @param pathwayPojo 梁梁传入数据
     * @return 返回成功或者失败
     */
    @RequestMapping("/insertInfo")
    @ResponseBody

    public String insertPathwayInfo(String pathwayPojo) {
        System.out.println("|-----------开始进行添加或更新操作---------");
        //测试数据     避免前端多次测试
        //pathwayPojo="{\"selectId\":\"012011078901\",\"publisher\":\"01\",\"publishYear\":\"2011\",\"fileNumber\":\"789\",\"versionNumber\":\"01\",\"pathway_name\":\"彭雷测试表格数据\",\"first_diagnose\":\"ICD–10：C34/D02.2\",\"suitable_subject_disc\":\"第一诊断为原发性支气管肺癌（ICD–10：C34/D02.2）\\n行肺局部切除/肺叶切除/全肺切除/开胸探查术(ICD-10-CM-3:32.29/32.3–32.5)。\",\"diagnosis\":\"根据《临床诊疗指南－呼吸病学分册》（中华医学会编著，人民卫生出版社），《2009年NCCN非小细胞肺癌临床实践指南（中国版）》（NCCN指南中国版专家组），《2009年NCCN小细胞肺癌临床实践指南》（NCCN小细胞肺癌专家组）。\\n1.临床表现：咳嗽、痰血、咯血、呼吸困难、Horner’s征、上腔静脉压迫综合征、远处转移引起的症状以及肺外非特异性表现（副癌综合征）等。\\n2.辅助检查：（1）胸部影像学检查；（2）病理学检查：痰脱落细胞学检查、纤维支气管镜活检、肺穿刺活检等确诊。\\n3.评价肿瘤转移情况的相关检查：腹部CT或超声、肾上腺CT、头颅MRI或增强CT、ECT全身骨扫描、PET–CT等。\\n4.根据上述检查结果进行临床分期。\",\"treatment_choice_ref\":\"《临床诊疗指南－呼吸病学分册》（中华医学会编著，人民卫生出版社），《2009年NCCN非小细胞肺癌临床实践指南（中国版）》（NCCN指南中国版专家组），《2009年NCCN小细胞肺癌临床实践指南》（NCCN小细胞肺癌专家组）。\",\"treatment_choice_scenario\":\"[{\\\"treatmentChoiceId\\\":[{\\\"value\\\":\\\"非小细胞肺癌治疗原则 @GRID@_1\\\"},{\\\"value\\\":\\\"晚期非小细胞肺癌治疗原则。 推荐以化疗为主，放疗和手术治疗为辅的综合治疗以延长患者生存期。化疗有效者可化疗4–6个周期。治疗后进展的患者可改二线治疗\\\"},{\\\"value\\\":\\\"小细胞肺癌治疗原则。 临床分期为Ⅰ期的小细胞肺癌，推荐肺叶切除+纵隔淋巴结清扫术，术后仍为pN0，推荐4–6周期的EP方案化疗；如为pN+，推荐全身化疗同时加纵隔野的放射治疗。不适于手术的I期小细胞肺癌，推荐同期化放疗的治疗。 Ⅱ和Ⅲ期小细胞肺癌，如果PS≤2，推荐同期化放疗的治疗；如果由于合并症而致PS＞2，首选化疗，必要时加上放射治疗。 Ⅳ期小细胞肺癌，首选治疗模式为全身化疗，EP方案为标准治疗方案；伊立替康+顺铂方案也是可选择的方案\\\"}]}]\",\"treatment_days\":\"[{\\\"tag_name\\\":\\\"\\\",\\\"text\\\":\\\"\\\",\\\"time_unit\\\":\\\"周\\\",\\\"min\\\":1.1428571428571428,\\\"max\\\":2}]\",\"treatment_entry_standard\":\"[{\\\"value\\\":\\\"第一诊断必须符合ICD–10：C34/D02.2原发性支气管肺癌疾病编码\\\"},{\\\"value\\\":\\\"有手术治疗指征需外科治疗者，转入外科治疗路径\\\"},{\\\"value\\\":\\\"如患者一般情况较差，KPS评分＜60（或ZPS评分＞2），不进入该临床路径\\\"},{\\\"value\\\":\\\"有明显影响原发性支气管肺癌常规治疗的情况，不进入该临床路径\\\"},{\\\"value\\\":\\\"当患者同时具有其他疾病诊断，但在住院期间不需要特殊处理也不影响第一诊断的临床路径流程实施时，可以进入路径\\\"}]\",\"prep_treatment_common\":\"化疗\",\"prep_treatmentCommon\":\"[{\\\"text\\\":\\\"\\\",\\\"time_unit\\\":\\\"\\\",\\\"min\\\":\\\"0\\\",\\\"max\\\":\\\"0\\\",\\\"obligatory_exam\\\":[{\\\"value\\\":\\\"血常规、尿常规、大便常规\\\"},{\\\"value\\\":\\\"凝血功能、血型、肝肾功能、电解质、感染性疾病筛查（乙肝、丙肝、艾滋病、梅毒等）、肿瘤标志物检查\\\"},{\\\"value\\\":\\\"肺功能、动脉血气分析、心电图、超声心动图\\\"},{\\\"value\\\":\\\"影像学检查：胸片正侧位、胸部CT（平扫＋增强扫描）、腹部超声或CT、ECT全身骨扫描、头颅MRI或CT\\\"}],\\\"optional_exam\\\":[{\\\"value\\\":\\\"纤维支气管镜检查及相应的镜下治疗\\\"},{\\\"value\\\":\\\"全身PET–CT\\\"}],\\\"notification\\\":[{\\\"value\\\":\\\"\\\"}],\\\"item_field_name1\\\":\\\"\\\"}]\",\"radio\":\"0\",\"prep_treatmentAntibio_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"prep_treatmentAnaesthetic_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"prep_treatmentOtherdrugs_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"prep_treatment_extension\":\"[{\\\"content_item\\\":[{\\\"value\\\":\\\"\\\"}],\\\"content\\\":\\\"\\\"}]\",\"treatment_days_duration\":\"\",\"duration_treatment_min\":\"0\",\"duration_treatment_max\":\"0\",\"duration_treatment_text\":\"\",\"sceneList\":\"[{\\\"treatmentPlanRef\\\":\\\"\\\",\\\"caseChildnum\\\":[{\\\"treatmentPlanContent\\\":\\\"化疗方案\\\",\\\"planContentItem\\\":[{\\\"value\\\":\\\"非小细胞肺癌。 1）GP方案： 吉西他滨1000–1250mg/m2静脉滴注第１、８天，顺铂75mg/m2或卡铂AUC=5静脉滴注第1天，21日为一周期。 2）DP方案： 多西他赛75mg/m²静脉滴注第1天，顺铂75mg/m²或卡铂AUC=5静脉滴注第1天，21日为一周期。 3）NP方案： 长春瑞滨25mg/m²静推10分钟第1、8天，顺铂       80mg/m²静滴第1天，21日为一周期。 4）TP方案： 紫杉醇175mg/m²静滴3小时第1天，顺铂75mg/m²或卡铂AUC=5静滴第1天，21日为一周期。 5）PP方案（非鳞癌）： 培美曲塞500mg/m²静滴第1天，顺铂75mg/m²或卡铂  AUC=5静滴第1天，21日为一周期。 非小细胞肺癌二线化疗可选药物包括多西他赛与培美曲塞：多西他赛75mg/m²静滴第1天，21日为一周期；或培美曲塞500mg/m²静滴第1天，21日为一周期\\\"},{\\\"value\\\":\\\"小细胞肺癌 1）EP方案： 顺铂80mg/m²静滴第1天，依托泊苷100mg/m²静滴第1-3天，21天为一周期。 2）IP方案： 伊立替康60mg/m²静滴第1、8、15天，顺铂60mg/m²静滴第1天，28日为一周期。 3）CAV方案： 环磷酰胺1000mg/m²静推或静滴第1天，多柔比星40–50mg/m²（或表柔比星60mg/m²）静推第1天，长春新碱1mg/m²静推第1天，21–28日为一周期。 小细胞肺癌的二线治疗：一线化疗后3个月内进展，二线化疗可选药物有托泊替康、异环磷酰胺、紫杉醇、多西他赛、吉西他滨；一线化疗后3个月后进展，二线化疗首选托泊替康单药或联合用药；6个月后进展，选用初始治疗有效的方案\\\"}]},{\\\"treatmentPlanContent\\\":\\\"靶向治疗\\\",\\\"planContentItem\\\":[{\\\"value\\\":\\\"非小细胞肺癌二线或三线治疗可选用吉非替尼或厄洛替尼。根据现有证据，推荐在有EGFR基因突变的晚期非小细胞肺癌中一线使用吉非替尼或厄洛替尼。目前用于非小细胞肺癌的靶向治疗还包括抗血管生成的贝伐单抗、西妥昔单抗等\\\"}]},{\\\"treatmentPlanContent\\\":\\\"抗肿瘤药物毒副反应的防治\\\",\\\"planContentItem\\\":[{\\\"value\\\":\\\"包括骨髓抑制、消化道反应、脏器损害、过敏反应、肾毒性及局部皮肤刺激的预防和处理\\\"}]},{\\\"treatmentPlanContent\\\":\\\"并发症及转移灶的综合治疗\\\",\\\"planContentItem\\\":[{\\\"value\\\":\\\"\\\"}]}],\\\"obligatoryExam\\\":[{\\\"value\\\":\\\"\\\"}],\\\"optionalExam\\\":[{\\\"value\\\":\\\"\\\"}],\\\"notification\\\":[{\\\"value\\\":\\\"\\\"}],\\\"item_field_name2\\\":\\\"\\\"}]\",\"drug_usageAntibio_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"drug_usageAnaesthetic_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"drug_usageOtherdrugs_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"afterDuration\":\"\",\"afterDuration_treatment_min\":\"0\",\"afterDuration_treatment_max\":\"0\",\"afterDuration_treatment_text\":\"\",\"after_medicalScenario\":\"[{\\\"obligatory_exam\\\":[{\\\"value\\\":\\\"\\\"}],\\\"optional_exam\\\":[{\\\"value\\\":\\\"\\\"}],\\\"recovery_plan\\\":[{\\\"value\\\":\\\"\\\"}]}]\",\"after_treatmentAntibio_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"after_treatmentAnaesthetic_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"after_treatmentOtherdrugs_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"discharge_criteria\":\"[{\\\"value\\\":\\\"生命体征平稳\\\"},{\\\"value\\\":\\\"没有需要继续住院处理的并发症\\\"}]\",\"other_notice\":\"[{\\\"value\\\":\\\"有影响肺癌治疗的合并症，需要进行相关的诊断和治疗\\\"},{\\\"value\\\":\\\"治疗过程中出现并发症和/或抗肿瘤药物严重毒副反应\\\"}]\",\"submitter\":\"李俊峰\",\"indefinite_field\":\"[{\\\"field_pos\\\":3,\\\"field_name\\\":\\\"\\\",\\\"field_content\\\":[{\\\"value\\\":\\\"\\\"}]}]\",\"submitterid\":\"51\",\"commit\":\"0\",\"table_info\":\"[{\\\"table_name\\\":\\\"@GRID@_1\\\",\\\"row_count\\\":3,\\\"table_pos\\\":0,\\\"column_count\\\":6,\\\"top_title\\\":\\\"\\\",\\\"below_description\\\":\\\"#对于一线治疗失败的患者，且有指征者，可以考虑靶向治疗，其中包括表皮生长因子受体（EGFR）酪氨酸激酶抑制剂或抗肿瘤新生血管药物。\\\",\\\"content\\\":[[{\\\"value\\\":\\\"\\\"},{\\\"value\\\":\\\"Ⅰ期\\\"},{\\\"value\\\":\\\"Ⅱ期\\\"},{\\\"value\\\":\\\"Ⅲa期\\\"},{\\\"value\\\":\\\"Ⅲb期\\\"},{\\\"value\\\":\\\"Ⅳ期\\\"}],[{\\\"value\\\":\\\"非小细胞肺癌\\\"},{\\\"value\\\":\\\"手术治疗，完全切除者，不推荐辅助化疗或辅助放疗\\\"},{\\\"value\\\":\\\"手术，术后推荐辅助化疗\\\"},{\\\"value\\\":\\\"1.手术后化疗（或加放疗） 2.化疗+放疗 3.化疗+手术+化疗（或加放疗） 4.靶向治疗#\\\"},{\\\"value\\\":\\\"化、放疗为主，T4中侵犯隆突气管手术或加放疗和化疗、合适者靶向治疗#\\\"},{\\\"value\\\":\\\"化疗加支持治疗，姑息性放疗，合适者靶向治#\\\"}],[{\\\"value\\\":\\\"非小细胞肺癌\\\"},{\\\"value\\\":\\\"手术治疗，完全切除者，不推荐辅助化疗或辅助放疗\\\"},{\\\"value\\\":\\\"手术，术后推荐辅助化疗\\\"},{\\\"value\\\":\\\"1.手术后化疗（或加放疗） 2.化疗+放疗 3.化疗+手术+化疗（或加放疗） 4.靶向治疗#\\\"},{\\\"value\\\":\\\"化、放疗为主，T4中侵犯隆突气管手术或加放疗和化疗、合适者靶向治疗#\\\"},{\\\"value\\\":\\\"化疗加支持治疗，姑息性放疗，合适者靶向治#\\\"}]]}]\",\"fileStatic\":0}";
        System.out.println("前端传入数据为:"+pathwayPojo);
        ReceivePathway receivePathway = JsonUtils.jsonToPojo(pathwayPojo, ReceivePathway.class);
        //创建pathwayInfo字段
        PathwayInfo pathwayInfo = MakeJsonPathway.newPathwayInfo(receivePathway);
        //记录oldPathwayInfo
        PathwayInfo oldPathwayInfo = pathwayInfoService.selectOneByIndex(pathwayInfo.getPathway_index());
        int statePathwayInfo = pathwayInfoService.insertPathwayInfo(receivePathway,pathwayInfo);
        System.out.println();
        if (statePathwayInfo==1){
            System.out.println("statePathwayInfo===="+statePathwayInfo);
            //通过与old比较,记录操作日志
            recentWorkService.insertRecentWork(pathwayInfo,oldPathwayInfo);
            return "ok";
        }else return "false";
    }

    /**
     *
     * @param selectId 被查询的index，与前端约定为该字段名为selectId
     * @return 查询出的结果
     */
    @RequestMapping("/findByname")
    @ResponseBody
    public JSONObject findPathway_InfoByname(String selectId){
        System.out.println("|-----------开始进行查询操作---------|");
        System.out.println("查询的id为="+selectId);
        JSONObject returnJson = pathwayInfoService.findPathwayInfoByIndex(selectId);
        System.out.println();
        return returnJson ;
    }

    /**
     *
     * @param query 模糊查询条件，既与query这个字段有关的所有字段
     * @return  所有与query有关的字段
     */
    @RequestMapping("/findAllInfo")
    @ResponseBody
    public JSONArray findAllById(String query) {
        System.out.println("进入模糊查询查找所有,查找与'"+query+"'有关的");
        JSONArray jsonArray = pathwayInfoService.findLikePathwayName(query);
        return jsonArray;
    }
    @ResponseBody
    @RequestMapping("/queryName")
    public JSONObject selectByNameAndSubmitter(String query, String submitter) {
        System.out.println("用户:"+submitter+"进入模糊查询查找所有,查找与'"+query+"'有关的");
        JSONObject jsonObject = pathwayInfoService.selectPathwayNameByUserName(query, submitter);
        //将返回值全部放在数组里传递给前台
        return jsonObject;
    }

    /**
     *
     * @param page  分页page
     * @param rows  分页rows
     * @param pathway_name  数据库对应的字段pathway_name
     * @param audit_state   数据库对应的字段audit_state
     * @return  分页查询的结果
     */
    @RequestMapping("/pathway_id")
    @ResponseBody
    public JSONObject selectAudit_InfoByPathway_Id(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                   @RequestParam(value = "rows", required = false, defaultValue = "10") Integer rows,
                                                   @RequestParam(value = "pathway_name", required = false, defaultValue = "") String pathway_name,
                                                   @RequestParam(value = "audit_state", required = false, defaultValue = "500") Integer audit_state,
                                                   @RequestParam(value = "commit_state", required = false, defaultValue = "500") Integer commit_state) {
        System.out.println("|-----------开始分页查询操作---------|");
        System.out.println("前端传入状态为++++audit_state="+audit_state+"        commit_state="+commit_state);
        Integer audit_state1,audit_state2,audit_state3,commit_state1,commit_state2;
        //500代表前台没有传入数据，查询所有的数据
        if(audit_state==500||commit_state==500){
            audit_state1 = 0;audit_state2 = 1;audit_state3 = 2;
            commit_state1 = 0; commit_state2 = 1;
        } else{
            audit_state1 = audit_state;audit_state2 = audit_state;audit_state3 = audit_state;
            commit_state1 = commit_state; commit_state2 = commit_state;
        }
        if(page==0){
            page = 1;
        }
        JSONObject jsonObject = pathwayInfoService.pathwayByPage(pathway_name, audit_state1, audit_state2, audit_state3, page, rows,commit_state1,commit_state2 );
        System.out.println("分页查询返回前端数据为:"+jsonObject);
        System.out.println();
        return jsonObject;
    }

    /**
     *
     * @param pathwayInfo 需要字段audit_state,check_id,audit_remark,commit_state,editor_id(暂时是submitter_id)
     * @return 返回成功或者失败
     */
    @RequestMapping("/updateAudit")
    @ResponseBody
    public ClincialResult updateById(PathwayInfo pathwayInfo) {
        System.out.println("|-----------开始进行审核操作---------|");
        pathwayInfo.setCommit_state("1");
        if (pathwayInfo.getAudit_state()==2){
            pathwayInfo.setCommit_state("0");
        }
        int flag = pathwayInfoService.updateAudit(pathwayInfo);
        if (flag == 1) {//审核成功！
            System.out.println("审核成功");
            return 	ClincialResult.ok();
        }else{//审核失败！
            System.out.println();
            return	ClincialResult.build(500,"审核失败");
        }
    }

    /**
     * 作用:前端传入pathway_index 删除数据库中的某个对应字段
     * @param pathway_index 路径版本号
     * @return 成功返回200 失败返回500
     */
    @RequestMapping("deletePathwayInfo")
    @ResponseBody
    public JSONObject deletePathwayInfo(String pathway_index,String username,String password){
        JSONObject jsonObject = new JSONObject();
        System.out.println("|-----------开始进行删除操作---------|");
        System.out.println("删除了id为"+pathway_index+"用户名:"+username+"密码:"+password);
        int state=0;
        if (userService.checkUser(username,password)!=0) {
            state = pathwayInfoService.deletePathwayInfo(pathway_index);
            System.out.println("state==="+state);
        }
        if (state==3){
            jsonObject.put("state",200);
            return jsonObject ;
        }else {
            jsonObject.put("state",500);
            return jsonObject;
        }
    }

    @RequestMapping("/myWork")
    @ResponseBody
    public List<String> findMyWork(String submitterid){
        return pathwayInfoService.findMyWork(submitterid);
    }

    @RequestMapping("/errorDate")
    @ResponseBody
    public List findError(){
        System.out.println("开始查找错误数据");
        return pathwayInfoService.finderror();
    }

    //@RequestMapping("/insertTest")
    @ResponseBody
    public String insertTest(){
        pathwayInfoService.insertTest();
        return "ok";
    }
}
