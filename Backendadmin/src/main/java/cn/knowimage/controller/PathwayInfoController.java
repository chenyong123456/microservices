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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import javax.servlet.http.HttpServletResponse;
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
     * @param pathwayPojo 梁梁传入数据
     * @return 返回成功或者失败
     */
    @RequestMapping("/insertInfo")
    @ResponseBody
    public String insertPathwayInfo(String pathwayPojo) {
        System.out.println("|-----------开始进行添加或更新操作---------");
        //测试数据     避免前端多次测试
        //pathwayPojo="{\"selectId\":\"012011078901\",\"publisher\":\"01\",\"publishYear\":\"2011\",\"fileNumber\":\"789\",\"versionNumber\":\"01\",\"pathway_name\":\"彭雷测试表格数据\",\"first_diagnose\":\"ICD–10：C34/D02.2\",\"suitable_subject_disc\":\"第一诊断为原发性支气管肺癌（ICD–10：C34/D02.2）\\n行肺局部切除/肺叶切除/全肺切除/开胸探查术(ICD-10-CM-3:32.29/32.3–32.5)。\",\"diagnosis\":\"根据《临床诊疗指南－呼吸病学分册》（中华医学会编著，人民卫生出版社），《2009年NCCN非小细胞肺癌临床实践指南（中国版）》（NCCN指南中国版专家组），《2009年NCCN小细胞肺癌临床实践指南》（NCCN小细胞肺癌专家组）。\\n1.临床表现：咳嗽、痰血、咯血、呼吸困难、Horner’s征、上腔静脉压迫综合征、远处转移引起的症状以及肺外非特异性表现（副癌综合征）等。\\n2.辅助检查：（1）胸部影像学检查；（2）病理学检查：痰脱落细胞学检查、纤维支气管镜活检、肺穿刺活检等确诊。\\n3.评价肿瘤转移情况的相关检查：腹部CT或超声、肾上腺CT、头颅MRI或增强CT、ECT全身骨扫描、PET–CT等。\\n4.根据上述检查结果进行临床分期。\",\"treatment_choice_ref\":\"《临床诊疗指南－呼吸病学分册》（中华医学会编著，人民卫生出版社），《2009年NCCN非小细胞肺癌临床实践指南（中国版）》（NCCN指南中国版专家组），《2009年NCCN小细胞肺癌临床实践指南》（NCCN小细胞肺癌专家组）。\",\"treatment_choice_scenario\":\"[{\\\"treatmentChoiceId\\\":[{\\\"value\\\":\\\"非小细胞肺癌治疗原则 @GRID@_1\\\"},{\\\"value\\\":\\\"晚期非小细胞肺癌治疗原则。 推荐以化疗为主，放疗和手术治疗为辅的综合治疗以延长患者生存期。化疗有效者可化疗4–6个周期。治疗后进展的患者可改二线治疗\\\"},{\\\"value\\\":\\\"小细胞肺癌治疗原则。 临床分期为Ⅰ期的小细胞肺癌，推荐肺叶切除+纵隔淋巴结清扫术，术后仍为pN0，推荐4–6周期的EP方案化疗；如为pN+，推荐全身化疗同时加纵隔野的放射治疗。不适于手术的I期小细胞肺癌，推荐同期化放疗的治疗。 Ⅱ和Ⅲ期小细胞肺癌，如果PS≤2，推荐同期化放疗的治疗；如果由于合并症而致PS＞2，首选化疗，必要时加上放射治疗。 Ⅳ期小细胞肺癌，首选治疗模式为全身化疗，EP方案为标准治疗方案；伊立替康+顺铂方案也是可选择的方案\\\"}]}]\",\"treatment_days\":\"[{\\\"tag_name\\\":\\\"\\\",\\\"text\\\":\\\"\\\",\\\"time_unit\\\":\\\"周\\\",\\\"min\\\":1.1428571428571428,\\\"max\\\":2}]\",\"treatment_entry_standard\":\"[{\\\"value\\\":\\\"第一诊断必须符合ICD–10：C34/D02.2原发性支气管肺癌疾病编码\\\"},{\\\"value\\\":\\\"有手术治疗指征需外科治疗者，转入外科治疗路径\\\"},{\\\"value\\\":\\\"如患者一般情况较差，KPS评分＜60（或ZPS评分＞2），不进入该临床路径\\\"},{\\\"value\\\":\\\"有明显影响原发性支气管肺癌常规治疗的情况，不进入该临床路径\\\"},{\\\"value\\\":\\\"当患者同时具有其他疾病诊断，但在住院期间不需要特殊处理也不影响第一诊断的临床路径流程实施时，可以进入路径\\\"}]\",\"prep_treatment_common\":\"化疗\",\"prep_treatmentCommon\":\"[{\\\"text\\\":\\\"\\\",\\\"time_unit\\\":\\\"\\\",\\\"min\\\":\\\"0\\\",\\\"max\\\":\\\"0\\\",\\\"obligatory_exam\\\":[{\\\"value\\\":\\\"血常规、尿常规、大便常规\\\"},{\\\"value\\\":\\\"凝血功能、血型、肝肾功能、电解质、感染性疾病筛查（乙肝、丙肝、艾滋病、梅毒等）、肿瘤标志物检查\\\"},{\\\"value\\\":\\\"肺功能、动脉血气分析、心电图、超声心动图\\\"},{\\\"value\\\":\\\"影像学检查：胸片正侧位、胸部CT（平扫＋增强扫描）、腹部超声或CT、ECT全身骨扫描、头颅MRI或CT\\\"}],\\\"optional_exam\\\":[{\\\"value\\\":\\\"纤维支气管镜检查及相应的镜下治疗\\\"},{\\\"value\\\":\\\"全身PET–CT\\\"}],\\\"notification\\\":[{\\\"value\\\":\\\"\\\"}],\\\"item_field_name1\\\":\\\"\\\"}]\",\"radio\":\"0\",\"prep_treatmentAntibio_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"prep_treatmentAnaesthetic_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"prep_treatmentOtherdrugs_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"prep_treatment_extension\":\"[{\\\"content_item\\\":[{\\\"value\\\":\\\"\\\"}],\\\"content\\\":\\\"\\\"}]\",\"treatment_days_duration\":\"\",\"duration_treatment_min\":\"0\",\"duration_treatment_max\":\"0\",\"duration_treatment_text\":\"\",\"sceneList\":\"[{\\\"treatmentPlanRef\\\":\\\"\\\",\\\"caseChildnum\\\":[{\\\"treatmentPlanContent\\\":\\\"化疗方案\\\",\\\"planContentItem\\\":[{\\\"value\\\":\\\"非小细胞肺癌。 1）GP方案： 吉西他滨1000–1250mg/m2静脉滴注第１、８天，顺铂75mg/m2或卡铂AUC=5静脉滴注第1天，21日为一周期。 2）DP方案： 多西他赛75mg/m²静脉滴注第1天，顺铂75mg/m²或卡铂AUC=5静脉滴注第1天，21日为一周期。 3）NP方案： 长春瑞滨25mg/m²静推10分钟第1、8天，顺铂       80mg/m²静滴第1天，21日为一周期。 4）TP方案： 紫杉醇175mg/m²静滴3小时第1天，顺铂75mg/m²或卡铂AUC=5静滴第1天，21日为一周期。 5）PP方案（非鳞癌）： 培美曲塞500mg/m²静滴第1天，顺铂75mg/m²或卡铂  AUC=5静滴第1天，21日为一周期。 非小细胞肺癌二线化疗可选药物包括多西他赛与培美曲塞：多西他赛75mg/m²静滴第1天，21日为一周期；或培美曲塞500mg/m²静滴第1天，21日为一周期\\\"},{\\\"value\\\":\\\"小细胞肺癌 1）EP方案： 顺铂80mg/m²静滴第1天，依托泊苷100mg/m²静滴第1-3天，21天为一周期。 2）IP方案： 伊立替康60mg/m²静滴第1、8、15天，顺铂60mg/m²静滴第1天，28日为一周期。 3）CAV方案： 环磷酰胺1000mg/m²静推或静滴第1天，多柔比星40–50mg/m²（或表柔比星60mg/m²）静推第1天，长春新碱1mg/m²静推第1天，21–28日为一周期。 小细胞肺癌的二线治疗：一线化疗后3个月内进展，二线化疗可选药物有托泊替康、异环磷酰胺、紫杉醇、多西他赛、吉西他滨；一线化疗后3个月后进展，二线化疗首选托泊替康单药或联合用药；6个月后进展，选用初始治疗有效的方案\\\"}]},{\\\"treatmentPlanContent\\\":\\\"靶向治疗\\\",\\\"planContentItem\\\":[{\\\"value\\\":\\\"非小细胞肺癌二线或三线治疗可选用吉非替尼或厄洛替尼。根据现有证据，推荐在有EGFR基因突变的晚期非小细胞肺癌中一线使用吉非替尼或厄洛替尼。目前用于非小细胞肺癌的靶向治疗还包括抗血管生成的贝伐单抗、西妥昔单抗等\\\"}]},{\\\"treatmentPlanContent\\\":\\\"抗肿瘤药物毒副反应的防治\\\",\\\"planContentItem\\\":[{\\\"value\\\":\\\"包括骨髓抑制、消化道反应、脏器损害、过敏反应、肾毒性及局部皮肤刺激的预防和处理\\\"}]},{\\\"treatmentPlanContent\\\":\\\"并发症及转移灶的综合治疗\\\",\\\"planContentItem\\\":[{\\\"value\\\":\\\"\\\"}]}],\\\"obligatoryExam\\\":[{\\\"value\\\":\\\"\\\"}],\\\"optionalExam\\\":[{\\\"value\\\":\\\"\\\"}],\\\"notification\\\":[{\\\"value\\\":\\\"\\\"}],\\\"item_field_name2\\\":\\\"\\\"}]\",\"drug_usageAntibio_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"drug_usageAnaesthetic_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"drug_usageOtherdrugs_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"afterDuration\":\"\",\"afterDuration_treatment_min\":\"0\",\"afterDuration_treatment_max\":\"0\",\"afterDuration_treatment_text\":\"\",\"after_medicalScenario\":\"[{\\\"obligatory_exam\\\":[{\\\"value\\\":\\\"\\\"}],\\\"optional_exam\\\":[{\\\"value\\\":\\\"\\\"}],\\\"recovery_plan\\\":[{\\\"value\\\":\\\"\\\"}]}]\",\"after_treatmentAntibio_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"after_treatmentAnaesthetic_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"after_treatmentOtherdrugs_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"discharge_criteria\":\"[{\\\"value\\\":\\\"生命体征平稳\\\"},{\\\"value\\\":\\\"没有需要继续住院处理的并发症\\\"}]\",\"other_notice\":\"[{\\\"value\\\":\\\"有影响肺癌治疗的合并症，需要进行相关的诊断和治疗\\\"},{\\\"value\\\":\\\"治疗过程中出现并发症和/或抗肿瘤药物严重毒副反应\\\"}]\",\"submitter\":\"李俊峰\",\"indefinite_field\":\"[{\\\"field_pos\\\":3,\\\"field_name\\\":\\\"\\\",\\\"field_content\\\":[{\\\"value\\\":\\\"\\\"}]}]\",\"submitterid\":\"51\",\"commit\":\"0\",\"table_info\":\"[{\\\"table_name\\\":\\\"@GRID@_1\\\",\\\"row_count\\\":3,\\\"table_pos\\\":0,\\\"column_count\\\":6,\\\"top_title\\\":\\\"\\\",\\\"below_description\\\":\\\"#对于一线治疗失败的患者，且有指征者，可以考虑靶向治疗，其中包括表皮生长因子受体（EGFR）酪氨酸激酶抑制剂或抗肿瘤新生血管药物。\\\",\\\"content\\\":[[{\\\"value\\\":\\\"\\\"},{\\\"value\\\":\\\"Ⅰ期\\\"},{\\\"value\\\":\\\"Ⅱ期\\\"},{\\\"value\\\":\\\"Ⅲa期\\\"},{\\\"value\\\":\\\"Ⅲb期\\\"},{\\\"value\\\":\\\"Ⅳ期\\\"}],[{\\\"value\\\":\\\"非小细胞肺癌\\\"},{\\\"value\\\":\\\"手术治疗，完全切除者，不推荐辅助化疗或辅助放疗\\\"},{\\\"value\\\":\\\"手术，术后推荐辅助化疗\\\"},{\\\"value\\\":\\\"1.手术后化疗（或加放疗） 2.化疗+放疗 3.化疗+手术+化疗（或加放疗） 4.靶向治疗#\\\"},{\\\"value\\\":\\\"化、放疗为主，T4中侵犯隆突气管手术或加放疗和化疗、合适者靶向治疗#\\\"},{\\\"value\\\":\\\"化疗加支持治疗，姑息性放疗，合适者靶向治#\\\"}],[{\\\"value\\\":\\\"非小细胞肺癌\\\"},{\\\"value\\\":\\\"手术治疗，完全切除者，不推荐辅助化疗或辅助放疗\\\"},{\\\"value\\\":\\\"手术，术后推荐辅助化疗\\\"},{\\\"value\\\":\\\"1.手术后化疗（或加放疗） 2.化疗+放疗 3.化疗+手术+化疗（或加放疗） 4.靶向治疗#\\\"},{\\\"value\\\":\\\"化、放疗为主，T4中侵犯隆突气管手术或加放疗和化疗、合适者靶向治疗#\\\"},{\\\"value\\\":\\\"化疗加支持治疗，姑息性放疗，合适者靶向治#\\\"}]]}]\",\"fileStatic\":0}";
        //pathwayPojo="{\"selectId\":\"012012060900\",\"publisher\":\"01\",\"publishYear\":\"2012\",\"fileNumber\":\"609\",\"versionNumber\":\"00\",\"pathway_name\":\"胃癌姑息化疗\",\"first_diagnose\":\"ICD-10：C16伴Z51.1\",\"suitable_subject_disc\":\"\\n1.第一诊断为胃癌（ICD-10：C16伴Z51.1）\\n2.姑息化疗：有复发转移胃癌患者，或因其他原因无法根治手术的患者。\",\"diagnosis\":\"根据卫生部《胃癌诊疗规范（2011年）》、NCCN《胃癌临床实践指南中国版（2011年）》等。\\n1.临床表现：上腹不适、隐痛、贫血等。\\n2.大便隐血试验多呈持续阳性。\\n3.胃镜检查明确肿瘤情况，取活组织检查作出病理学诊断。\\n4.影像学检查提示并了解有无淋巴结及脏器转移，肿瘤局部脏器浸润；气钡双重造影检查了解肿瘤大小、形态和病变范围。\\n5.根据上述检查结果进行临床分期。\",\"treatment_choice_ref\":\"\",\"treatment_choice_scenario\":\"[{\\\"treatmentChoiceId\\\":[{\\\"value\\\":\\\"\\\"}]}]\",\"treatment_days\":\"[{\\\"tag_name\\\":\\\"\\\",\\\"text\\\":\\\"\\\",\\\"time_unit\\\":\\\"小时\\\",\\\"min\\\":120,\\\"max\\\":216}]\",\"treatment_entry_standard\":\"[{\\\"value\\\":\\\"第一诊断必须符合ICD-10：C16伴Z51.1胃癌疾病编码\\\"},{\\\"value\\\":\\\"有复发转移或准备入院检查确认复发转移，或因其他原因无法根治手术\\\"},{\\\"value\\\":\\\"无需特殊处理的合并症，如消化道大出血、幽门梗阻、胸腹水、肠梗阻等\\\"},{\\\"value\\\":\\\"当患者合并其他疾病，但住院期间不需要特殊处理也不影响第一诊断的临床路径流程实施时，可以进入路径\\\"}]\",\"prep_treatment_common\":\"化疗\",\"prep_treatmentCommon\":\"[{\\\"text\\\":\\\"\\\",\\\"time_unit\\\":\\\"小时\\\",\\\"min\\\":\\\"24\\\",\\\"max\\\":\\\"72\\\",\\\"obligatory_exam\\\":[{\\\"value\\\":\\\"血常规、尿常规、大便常规+隐血\\\"},{\\\"value\\\":\\\"肝肾功能、电解质、血糖、凝血功能、CEA\\\"},{\\\"value\\\":\\\"心电图\\\"}],\\\"optional_exam\\\":[{\\\"value\\\":\\\"AFP、CA199、CA125、CA724、CA242、Her2免疫组化检测\\\"},{\\\"value\\\":\\\"上消化道造影，特别是气钡双重造影（对疑有幽门梗阻的患者，建议使用水溶性造影剂）\\\"},{\\\"value\\\":\\\"必要时可以在基线和评效时行超声胃镜检查\\\"},{\\\"value\\\":\\\"骨扫描：对怀疑有骨转移的胃癌患者，应行骨扫描筛查\\\"},{\\\"value\\\":\\\"合并其他疾病的相关检查\\\"}],\\\"notification\\\":[{\\\"value\\\":\\\"胃镜、胸片（正侧位）或胸部CT、腹部增强CT、盆腔超声、颈部及锁骨上淋巴结超声\\\"},{\\\"value\\\":\\\"病理学活组织检查与诊断（必要时）\\\"}],\\\"item_field_name1\\\":\\\"基线及评效检查项目\\\"}]\",\"radio\":\"1\",\"prep_treatmentAntibio_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"prep_treatmentAnaesthetic_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"prep_treatmentOtherdrugs_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"prep_treatment_extension\":\"[{\\\"content\\\":\\\"\\\",\\\"content_item\\\":[{\\\"value\\\":\\\"体格检查、体能状况评分\\\"},{\\\"value\\\":\\\"排除化疗禁忌\\\"},{\\\"value\\\":\\\"患者、监护人或被授权人签署相关同意书\\\"}]}]\",\"treatment_days_duration\":\"小时\",\"duration_treatment_min\":\"0\",\"duration_treatment_max\":\"0\",\"duration_treatment_text\":\"\",\"sceneList\":\"[{\\\"treatmentPlanRef\\\":\\\"依据卫生部《胃癌诊疗规范（2011年）》等\\\",\\\"caseChildnum\\\":[{\\\"treatmentPlanContent\\\":\\\"推荐使用三药或两药联合方案，对体力状态差、高龄患者，可以考虑采用口服氟尿嘧啶类药物或紫杉类药物的单药化疗方案。\\\",\\\"planContentItem\\\":[{\\\"value\\\":\\\"\\\"}]},{\\\"treatmentPlanContent\\\":\\\"两药方案包括：5-FU+顺铂、卡培他滨+顺铂、替吉奥+顺铂、卡培他滨+奥沙利铂（XELOX）、FOLFOX、替吉奥+奥沙利铂、卡培他滨+紫杉醇、FOLFIRI。\\\",\\\"planContentItem\\\":[{\\\"value\\\":\\\"\\\"}]},{\\\"treatmentPlanContent\\\":\\\"三药方案包括：ECF及其衍生方案（EOX、ECX、EOF），DCF及其改良方案等。\\\",\\\"planContentItem\\\":[{\\\"value\\\":\\\"\\\"}]}],\\\"obligatoryExam\\\":[{\\\"value\\\":\\\"\\\"}],\\\"optionalExam\\\":[{\\\"value\\\":\\\"\\\"}],\\\"notification\\\":[{\\\"value\\\":\\\"@GRID@_1\\\"}],\\\"item_field_name2\\\":\\\"化疗药物\\\"}]\",\"drug_usageAntibio_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"drug_usageAnaesthetic_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"drug_usageOtherdrugs_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"afterDuration\":\"小时\",\"afterDuration_treatment_min\":\"0\",\"afterDuration_treatment_max\":\"0\",\"afterDuration_treatment_text\":\"\",\"after_medicalScenario\":\"[{\\\"obligatory_exam\\\":[{\\\"value\\\":\\\"血常规：建议每周复查1-2次。根据具体化疗方案及血像变化，复查时间间隔可酌情增减\\\"}],\\\"optional_exam\\\":[{\\\"value\\\":\\\"肝肾功能：每周期复查一次。根据具体化疗方案及结果，复查时间间隔可酌情增减\\\"}],\\\"recovery_plan\\\":[{\\\"value\\\":\\\"化疗期间脏器功能损伤的相应防治：止吐、保肝、水化、抑酸剂、止泻药、预防过敏、升白细胞及血小板、贫血治疗\\\"}]}]\",\"after_treatmentAntibio_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"after_treatmentAnaesthetic_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"after_treatmentOtherdrugs_usage\":\"[{\\\"value\\\":\\\"静脉或肠内营养\\\"}]\",\"discharge_criteria\":\"[{\\\"value\\\":\\\"病人一般情况良好，生命体征平稳正常\\\"},{\\\"value\\\":\\\"没有需要住院处理的并发症\\\"}]\",\"other_notice\":\"[{\\\"value\\\":\\\"治疗前、中、后有感染、严重贫血、出血、梗阻及其他合并症者，需进行相关的诊断和治疗，可能延长住院时间并致费用增加。\\\"},{\\\"value\\\":\\\"化疗后出现骨髓抑制，需要对症处理，导致治疗时间延长、费用增加\\\"},{\\\"value\\\":\\\"药物不良反应需要特殊处理，如过敏反应、神经毒性、心脏毒性等\\\"},{\\\"value\\\":\\\"对HER-2表达呈阳性（免疫组化染色呈+++，或免疫组化染色呈++且FISH检测呈阳性）的晚期胃癌患者，可考虑在化疗的基础上，联合使用分子靶向治疗药物曲妥珠单抗。导致费用增加\\\"},{\\\"value\\\":\\\"高龄患者根据个体化情况具体实施\\\"},{\\\"value\\\":\\\"医师认可的变异原因分析，如药物减量使用\\\"},{\\\"value\\\":\\\"其他患者方面的原因等\\\"}]\",\"submitter\":\"李俊峰\",\"indefinite_field\":\"[{\\\"field_pos\\\":3,\\\"field_name\\\":\\\"\\\",\\\"field_content\\\":[{\\\"value\\\":\\\"\\\"}]}]\",\"submitterid\":\"51\",\"commit\":\"0\",\"table_info\":\"[{\\\"table_name\\\":\\\"@GRID@_1\\\",\\\"row_count\\\":11,\\\"column_count\\\":3,\\\"top_title\\\":\\\"\\\",\\\"below_description\\\":\\\"\\\",\\\"table_pos\\\":\\\"0\\\",\\\"content\\\":[[{\\\"value\\\":\\\"药物\\\"},{\\\"value\\\":\\\"给药剂量mg/(m2) 及给药途径\\\"},{\\\"value\\\":\\\"给药时间(天) 及周期间隔\\\"}],[{\\\"value\\\":\\\"替吉奥\\\"},{\\\"value\\\":\\\"40   Bid   po\\\"},{\\\"value\\\":\\\"d1-14   q3w\\\"}],[{\\\"value\\\":\\\"卡培他滨\\\"},{\\\"value\\\":\\\"1000 Bid   po\\\"},{\\\"value\\\":\\\"d1-14   q3w\\\"}],[{\\\"value\\\":\\\"5-FU\\\"},{\\\"value\\\":\\\"425-750 civ 24h 800-1200civ 22h\\\"},{\\\"value\\\":\\\"d1-5    q3w d1-2    q2w\\\"}],[{\\\"value\\\":\\\"顺铂\\\"},{\\\"value\\\":\\\"60-80　 iv drip\\\"},{\\\"value\\\":\\\"d1或分2-3d   q3w\\\"}],[{\\\"value\\\":\\\"奥沙利铂\\\"},{\\\"value\\\":\\\"130     iv drip 85      iv drip\\\"},{\\\"value\\\":\\\"d1      q3w d1      q2w\\\"}],[{\\\"value\\\":\\\"紫杉醇\\\"},{\\\"value\\\":\\\"150-175 iv drip\\\"},{\\\"value\\\":\\\"d1或分为d1,d8 q3w\\\"}],[{\\\"value\\\":\\\"多西紫杉醇\\\"},{\\\"value\\\":\\\"60-75   iv drip\\\"},{\\\"value\\\":\\\"d1      q3w\\\"}],[{\\\"value\\\":\\\"表阿霉素\\\"},{\\\"value\\\":\\\"50-60   iv \\\"},{\\\"value\\\":\\\"d1      q3w\\\"}],[{\\\"value\\\":\\\"醛氢叶酸\\\"},{\\\"value\\\":\\\"20-200  iv\\\"},{\\\"value\\\":\\\"d1-2    q2w\\\"}],[{\\\"value\\\":\\\"伊立替康\\\"},{\\\"value\\\":\\\"180mg  iv\\\"},{\\\"value\\\":\\\"d1      q2w\\\"}]]}]\",\"fileStatic\":0}";
        System.out.println("前端传入的总数据为:"+pathwayPojo);

        ReceivePathway receivePathway = JsonUtils.jsonToPojo(pathwayPojo, ReceivePathway.class);
        PathwayInfo pathwayInfo = null;
        try {
            //创建pathwayInfo字段, 将前端转换为指定数据库所要求的数据对象, 新转换的数据
           pathwayInfo = MakeJsonPathway.newPathwayInfo(receivePathway);
        }catch (IndexOutOfBoundsException e){
            return "false";
        }

        //记录oldPathwayInfo--->从数据库中查询出来的数据-->先取之前的值
        PathwayInfo oldPathwayInfo = pathwayInfoService.selectOneByIndex(pathwayInfo.getPathway_index());
        int statePathwayInfo = pathwayInfoService.insertPathwayInfo(receivePathway,pathwayInfo);
        System.out.println("**************************************************");
        System.out.println("查看相应的修改状态:----->" + statePathwayInfo);
        System.out.println("**************************************************");
        //1 代表成功
        if (statePathwayInfo == 1 ){
            System.out.println("*****************************比较修改之后的两次修改的状态");
            System.out.println("新数据,修改之后的数据----->"+pathwayInfo.toString());
            if(oldPathwayInfo != null) {
                System.out.println("旧数据,修改之前的数据----->" + oldPathwayInfo.toString());
            }
            System.out.println("=================================================");
            //通过与old比较,记录操作日志,向数据库中更新最新记录
            recentWorkService.insertRecentWork(pathwayInfo,oldPathwayInfo);
            return "ok";
        }else{
            return "false";
        }
    }

    /**
     * 记住这里加入了Model
     * @param selectId 被查询的index，与前端约定为该字段名为selectId
     * @return 查询出的结果
     */
    @RequestMapping("/findByname")
    @ResponseBody
    public JSONObject findPathway_InfoByname(String selectId, HttpServletResponse httpServletResponse){

        System.out.println("|-----------开始进行查询操作---------|");
        System.out.println("查询的id为="+selectId);
        JSONObject returnJson = pathwayInfoService.findPathwayInfoByIndex(selectId);
        //在这里写入文件流
       // httpServletResponse.WriteFile("TextFile.txt");
        System.out.println("回显完成************************");
        return returnJson ;
    }

    /**
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
     * 后台系统的分页深刻的界面
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
        //1 代表已提交
        pathwayInfo.setCommit_state("1");
        if (pathwayInfo.getAudit_state()==2){  //审核不通过
            pathwayInfo.setCommit_state("0");  //0为未提交, 1为已提交
        }
        //变为修订中
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
        System.out.println("删除请求是否过来了!********************");
        JSONObject jsonObject = new JSONObject();
        System.out.println("|-----------开始进行删除操作---------|");
        System.out.println("删除了id为"+pathway_index+"用户名:"+username+"密码:"+password);
        int state=0;
        //userService.checkUser(username,password)!=0 用户输入密码正确
        if (userService.checkUser(username,password)!=0) {
            System.out.println("是否进入删除操作!");
            state = pathwayInfoService.deletePathwayInfo(pathway_index);
            int recentWork = pathwayInfoService.deleteElRecentWork(pathway_index);
            System.out.println("state==="+state);
        }
        if (state>0){
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
    public List findError(Model model){
        System.out.println("开始查找错误数据");
        return pathwayInfoService.finderror();
    }


}
