package cn.knowimage.pojo;

import lombok.Data;

@Data
public class ReceivePathway {
    private String selectId;//前端传入id
    private String publisher; //0-1发布者
    private String publishYear;;//2-5; 发布年份
    private String fileNumber;//6-9; 文件编号
    private String versionNumber;//10-11; 版本号 4个属性拼接为pathway_index，值类似为012016000100
    private String pathway_name;//临时路径名字;见文档中的标题(小儿气管（支气管）异物)
    private String first_diagnose;//第一诊断医学代码(ICD-10：T17.401/T17.501)
    private String suitable_subject_disc;//临时路径适用对象(第一诊断为气管异物/支气管异物（ICD-10：T17.401/T17.501）)
    private String diagnosis;//诊断依据 (根据《临床诊疗指南-耳鼻喉科分册》。。。。)
    private String treatment_choice_ref;//选择此方案的医学依据;类似于《临床诊疗指南-耳鼻喉科分册》(中华医学会编著;人民卫生出版社)..字段，与下面这一条字段拼接为Treatment_choice
    private String treatment_choice_scenario;//类似于{["num": 1; "id_0": ["经直接喉镜异物取出术"; "经支气管镜异物取出术"; "经纤维支气管镜异物取出术"; "必要时气管切开或胸外科开胸取异物"]}字段
    private String treatment_days;//标准住院时间
    private String treatment_entry_standard;//进入路径的标准
    private String prep_treatment_common;//医学处理前的检查项目
    private String prep_treatmentCommon;//未找到
    private String radio;
    private String prep_treatmentAntibio_usage;//未找到
    private String prep_treatmentAnaesthetic_usage;//未找到
    private String prep_treatmentOtherdrugs_usage;//未找到
    private String prep_treatment_extension;//该字段代表手术前额外准备工作
    private String treatment_days_duration;//未找到
    private String duration_treatment_min;//未找到
    private String duration_treatment_max;//未找到
    private String duration_treatment_text;//未找到
    private String sceneList;//未找到
    private String drug_usageAntibio_usage;//未找到
    private String drug_usageAnaesthetic_usage;//未找到
    private String drug_usageOtherdrugs_usage;//未找到
    private String afterDuration;//未找到
    private String afterDuration_treatment_min;//未找到
    private String afterDuration_treatment_max;//未找到
    private String afterDuration_treatment_text;//未找到
    private String after_medicalScenario;//未找到
    private String after_treatmentAntibio_usage;//未找到
    private String after_treatmentAnaesthetic_usage;//未找到
    private String after_treatmentOtherdrugs_usage;//未找到
    private String discharge_criteria;//出院标准
    private String other_notice;//变异及原因分析
    private String submitter; //记录提交者名字
    private String indefinite_field;//对应数据库的additional_field
    private String submitterid;
    private String commit;//判断用户的提交或者保存操作，保存=0 提交=1
    private String table_info;
    private int fileStatic;//判断用户是新增的操作还是保存的操作



}
