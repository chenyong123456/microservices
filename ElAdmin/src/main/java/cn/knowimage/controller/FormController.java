package cn.knowimage.controller;

import cn.knowimage.pojo.Audit_Info;
import cn.knowimage.pojo.Pathway_Info;
import cn.knowimage.insertjsonsecond.*;
import cn.knowimage.jsonoutput.*;
import cn.knowimage.service.Audit_InfoService;
import cn.knowimage.service.Pathway_InfoService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
/**
 * @author Mr.G
 * @date 2019/8/12
 * 新表单提交控制器
 */
@CrossOrigin
@Controller
public class FormController {
@Autowired
    public Pathway_InfoService pathway_infoService;
@Autowired
    public Audit_InfoService audit_InfoService;

  @RequestMapping("/getPart")
  @ResponseBody
  public void getPart(@RequestParam(value = "name",required = true) String name,@RequestParam(value = "password",required = true)String password){
      System.out.println("name---->:"+name);
      System.out.println("password--->>:"+password);
      System.out.println("ssssss");
  }

/*
 * 新表单的插入数据功能实现
 * */
    @RequestMapping("/insertInfo")
    @ResponseBody
    public String insertPathwayInfo( @RequestParam(required = false) String publisher,
                                     @RequestParam(required = false) String publishYear,
                                     @RequestParam(required = false) String fileNumber,
                                     @RequestParam(required = false) String versionNumber,
                                     @RequestParam(required = false) String pathway_name,
                                     @RequestParam(required = false) String first_diagnose,
                                     @RequestParam(required = false) String suitable_subject_disc,
                                     @RequestParam(required = false) String diagnosis,
                                     @RequestParam(required = false) String treatment_choice_ref,
                                     @RequestParam(required = false) String treatment_choice_scenario,
                                     @RequestParam(required = false) String treatment_days,
                                     @RequestParam(required = false) String treatment_entry_standard,
                                     @RequestParam(required = false) String prep_treatment_common,
                                     @RequestParam(required = false) String prep_treatmentCommon,
                                     @RequestParam(required = false) String radio,
                                     @RequestParam(required = false) String prep_treatmentAntibio_usage,
                                     @RequestParam(required = false) String prep_treatmentAnaesthetic_usage,
                                     @RequestParam(required = false) String prep_treatmentOtherdrugs_usage,
                                     @RequestParam(required = false) String prep_treatment_extension,
                                     @RequestParam(required = false) String treatment_days_duration,
                                     @RequestParam(required = false) String duration_treatment_min,
                                     @RequestParam(required = false) String duration_treatment_max,
                                     @RequestParam(required = false) String duration_treatment_text,
                                     @RequestParam(required = false) String sceneList,
                                     @RequestParam(required = false) String drug_usageAntibio_usage,
                                     @RequestParam(required = false) String drug_usageAnaesthetic_usage,
                                     @RequestParam(required = false) String drug_usageOtherdrugs_usage,
                                     @RequestParam(required = false) String afterDuration,
                                     @RequestParam(required = false) String afterDuration_treatment_min,
                                     @RequestParam(required = false) String afterDuration_treatment_max,
                                     @RequestParam(required = false) String afterDuration_treatment_text,
                                     @RequestParam(required = false) String after_medicalScenario,
                                     @RequestParam(required = false) String after_treatmentAntibio_usage,
                                     @RequestParam(required = false) String after_treatmentAnaesthetic_usage,
                                     @RequestParam(required = false) String after_treatmentOtherdrugs_usage,
                                     @RequestParam(required = false) String discharge_criteria,
                                     @RequestParam(required = false) String other_notice,
                                     @RequestParam String submitter){
    //以下测试数据是否传入
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    String updateDate =formatter.format(new Date());
    System.out.println(updateDate);
    //拼接fileNumber，规定该字段必须为四位数，不足四位自动补0
    if(fileNumber.equals("")){
        fileNumber = "0000" + fileNumber;
    }else if(fileNumber.length()==1){
        fileNumber = "000" + fileNumber;
    }else if(fileNumber.length()==2){
        fileNumber = "00" + fileNumber;
    }else if(fileNumber.length()==3){
        fileNumber = "0" + fileNumber;
    }
    //相数据中来构造json格式的数据格式
    Integer drug_use_period = Integer.parseInt(radio);
    //该字段分别输入四个，需要拼接传入数据库
    String pathway_index = publisher+publishYear+fileNumber+versionNumber;
    System.out.println("pathway_index:"+pathway_index);
    System.out.println("pathway_name:"+pathway_name);
    System.out.println("first_diagnose:"+first_diagnose);
    System.out.println("suitable_subject_disc:"+suitable_subject_disc);
    System.out.println("diagnosis:"+diagnosis);
    //treatment_choice字段的场景
    System.out.println("treatment_choice_scenario"+treatment_choice_scenario);
    System.out.println(treatment_choice_ref);

    //treatment_days字段的场景
    System.out.println("treatment_days:"+treatment_days);

    //在treatment_entry_standard和prep_treatment_common字段测试
    System.out.println("treatment_entry_standard:"+treatment_entry_standard);
    //输出type
    System.out.println("type:"+prep_treatment_common);
    System.out.println("drug_use_period:"+drug_use_period);
    //prep_treatment_common字段的值
    System.out.println("prep_treatmentCommon:"+prep_treatmentCommon);


    //prep_treatment_drug_usage字段的值
    System.out.println("prep_treatmentAntibio_usage:"+prep_treatmentAntibio_usage);
    System.out.println("prep_treatmentAnaesthetic_usage:"+prep_treatmentAnaesthetic_usage);
    System.out.println("prep_treatmentOtherdrugs_usage"+prep_treatmentOtherdrugs_usage);

    //prep_treatment_extension字段的值
    System.out.println("prep_treatment_extension:"+prep_treatment_extension);


    //treatment字段的值-->从这里开始拼接
    System.out.println(treatment_days_duration);
    System.out.println(duration_treatment_min);
    System.out.println(duration_treatment_max);
    System.out.println(duration_treatment_text);
    System.out.println("sceneList:"+sceneList);

    //drug_usage字段的值
    System.out.println("drug_usageAntibio_usage:"+drug_usageAntibio_usage);
    System.out.println("drug_usageAnaesthetic_usage:"+drug_usageAnaesthetic_usage);
    System.out.println("drug_usageOtherdrugs_usage:"+drug_usageOtherdrugs_usage);

    //after_medical_treatment字段的值
    System.out.println("******************************");
    System.out.println(afterDuration);
    System.out.println(afterDuration_treatment_min);
    System.out.println(afterDuration_treatment_max);
    System.out.println(afterDuration_treatment_text);
    System.out.println("after_medicalScenario:"+after_medicalScenario);//obligatory_exam数组需要分配的大小
    System.out.println("********************************************");

    //after_treatment_drug_usage字段的值
    System.out.println("after_treatmentAntibio_usage:"+after_treatmentAntibio_usage);
    System.out.println("after_treatmentAnaesthetic_usage:"+after_treatmentAnaesthetic_usage);
    System.out.println("after_treatmentOtherdrugs_usage:"+after_treatmentOtherdrugs_usage);

    System.out.println("********************************************");

    //discharge_criteria字段的值
    System.out.println(discharge_criteria);
    System.out.println("********************************************");

    //other_notice字段的值
    System.out.println(other_notice);

    //submitter字段的值
    System.out.println(submitter);
    //对fileNumber索引进行不足4位数字


    Pathway_Info pathway_info = new Pathway_Info();
    pathway_info.setPathway_index(pathway_index);
    pathway_info.setPathway_name(pathway_name);
    pathway_info.setFirst_diagnosis(first_diagnose);
    pathway_info.setSuitable_subject_disc(suitable_subject_disc);
    pathway_info.setDiagnosis(diagnosis);
    //生成treatment_choice字段
    pathway_info.setTreatment_choice(NewTreatmentChoice.newTreatment_choice(treatment_choice_ref,treatment_choice_scenario).toString());
    //生成treatment_days字段
    pathway_info.setTreatment_days(NewTreatmentDays.Treatment_Days(treatment_days).toString());
    //生成treatment_entry_standard字段
    pathway_info.setTreatment_entry_standard(NewTreatmentEntryStandar.Treatment_Entry_Standar(treatment_entry_standard).toString());
    //对应json文件中的type手术类型
    pathway_info.setType(prep_treatment_common);
    //生成prep_treatmentCommon字段
    pathway_info.setPrep_treatment_common(NewPrepTreatmentCommon.Treatment_CommonJson(prep_treatmentCommon).toString());
    //对应json文件中的drug_use_period字段

    pathway_info.setDrug_use_period(drug_use_period);
    //拼json文件中prep_treatment_drug_usage字段
    pathway_info.setPrep_treatment_drug_usage(NewPrepTreatmentDrugUsage.Prep_Treatment_Drug_Usage(
            prep_treatmentAntibio_usage,prep_treatmentAnaesthetic_usage,prep_treatmentOtherdrugs_usage).toString());
    //生成prep_treatment_extension字段
    pathway_info.setPrep_treatment_extension(NewPrepTreatmentExtension.Treatment_ExtensionJson(prep_treatment_extension).toString());
    //生成treatment字段
    pathway_info.setTreatment(NewTreatment.treatment(treatment_days_duration,duration_treatment_min,duration_treatment_max,
            duration_treatment_text,sceneList).toString());
    //生成Drug_usage字段
    pathway_info.setDrug_usage(NewDrugUsage.drug_usage(drug_usageAntibio_usage,drug_usageAnaesthetic_usage,drug_usageOtherdrugs_usage).toString());
    //生成after_medical_treatment字段
    pathway_info.setAfter_medical_treatment(NewAfterMedicalTreatment.after_medical_treatment(afterDuration,afterDuration_treatment_min,
            afterDuration_treatment_max,afterDuration_treatment_text,after_medicalScenario).toString());
    //生成After_treatment_drug_usage字段
    pathway_info.setAfter_treatment_drug_usage(NewAfterTreatmentDrugUsage.after_treatment_drug_usageJson(after_treatmentAntibio_usage,
            after_treatmentAnaesthetic_usage,after_treatmentOtherdrugs_usage).toString());
    //生成discharge_criteria字段
    pathway_info.setDischarge_criteria(NewDischargeCriteria.discharge_criteria(discharge_criteria).toString());
    //生成other_notice字段
    pathway_info.setOther_notice(NewOtherNotice.other_notice(other_notice).toString());
    int count = pathway_infoService.insertPathway_info(pathway_info);
    //添加audit_Info表的数据
    Audit_Info audit_Info = new Audit_Info();
    audit_Info.setPathway_name(pathway_name);
    audit_Info.setSubmitter(submitter);
    audit_Info.setData_upload_moment(updateDate);
    audit_Info.setChecker("Li");
    audit_Info.setAudit_state(0);
    //数据格式转换完成之后请放开改行代码来新增数据的记录  记住在audit_Info新增相应的记录
    int counta = audit_InfoService.insertAudit_info(audit_Info);

    if((count == 1)&&(counta == 1)){
        return "ok";
    }else{
        return "false";
    }
}

//通过id查询数据，将数据回显到页面
@RequestMapping("/find")
@ResponseBody
public JSONObject findPathway_Info(int pathWay_id){
    //创建last对象，将取到的值全部放入其中，传给前台
    JSONObject last = new JSONObject();
    //将根据id查询到的数据放入list中
    List<Pathway_Info> list = pathway_infoService.selectByPathway_id(pathWay_id);
    List<Audit_Info> audit_infoList = audit_InfoService.selectByPathway_id(pathWay_id);
    //获取数据库中pathway_index字段
    String pathway_index = list.get(0).getPathway_index();
    //将pathway_index字段拆分成四个字段,publisher占两位，publishYear和fileNumber占四位，versionNumber占两位
    String publisher = pathway_index.substring(0,2);
    String publishYear = pathway_index.substring(2,6);
    String fileNumber = pathway_index.substring(6,10);
    String versionNumber = pathway_index.substring(10,12);
    String first_diagnose = list.get(0).getFirst_diagnosis();
    String pathway_name = list.get(0).getPathway_name();
    String suitable_subject_disc = list.get(0).getSuitable_subject_disc();
    String diagnosis = list.get(0).getDiagnosis();
    String treatment_choice = TreatmentChoiceReturn.treatment_choice_return(list.get(0).getTreatment_choice());
    String treatment_days = TreatmentDaysReturn.treatment_days(list.get(0).getTreatment_days());
    String treatment_entry_standard = TreatmentEntryStandardReturn.treatment_entry_standard(list.get(0).getTreatment_entry_standard());
    String type = list.get(0).getType();
    //将数据库中int类型的drug_use_period字段转为string型
    String  drug_use_period = String.valueOf(list.get(0).getDrug_use_period());
    String prep_treatment_common = PrepTreatmentCommonReturn.prep_treatmentCommon(list.get(0).getPrep_treatment_common());
    String prep_treatment_drug_usage = PrepTreatmentDrugUsageReturn.prep_treatment_drug_usage(list.get(0).getPrep_treatment_drug_usage());
    String prep_treatment_extension = PrepTreatmentExtensionReturn.prep_treatment_extension(list.get(0).getPrep_treatment_extension());
    String treatment = TreatmentReturn.treatment(list.get(0).getTreatment());
    String drug_usage = DrugUsageReturn.drug_usage(list.get(0).getDrug_usage());
    String after_medical_treatment = AfterMedicalTreatmentReturn.after_medical_treatment(list.get(0).getAfter_medical_treatment());
    String after_treatment_drug_usage = AfterTreatmentDrugUsageReturn.after_treatment_drug_usage(list.get(0).getAfter_treatment_drug_usage());
    String discharge_criteria = DischargeCriteriaReturn.discharge_criteria(list.get(0).getDischarge_criteria());
    String other_notice = OtherNoticeReturn.other_notice(list.get(0).getOther_notice());
    //该字段在audit_info表单中
    //注意！测试数据库中 pathway_info和audit_info表单中 pathway_id未同步，所以报错
    String submitter = audit_infoList.get(0).getSubmitter();
    last.put("publisher",publisher);
    last.put("publishYear",publishYear);
    last.put("fileNumber",fileNumber);
    last.put("versionNumber",versionNumber);
    last.put("first_diagnose",first_diagnose);
    last.put("pathway_name",pathway_name);
    last.put("suitable_subject_disc",suitable_subject_disc);
    last.put("diagnosis",diagnosis);
    last.put("treatment_choice",treatment_choice);
    last.put("treatment_days",treatment_days);
    last.put("treatment_entry_standard",treatment_entry_standard);
    last.put("type",type);
    last.put("drug_use_period",drug_use_period);
    last.put("prep_treatment_common",prep_treatment_common);
    last.put("prep_treatment_drug_usage",prep_treatment_drug_usage);
    last.put("prep_treatment_extension",prep_treatment_extension);
    last.put("treatment",treatment);
    last.put("drug_usage",drug_usage);
    last.put("after_medical_treatment",after_medical_treatment);
    last.put("after_treatment_drug_usage",after_treatment_drug_usage);
    last.put("discharge_criteria",discharge_criteria);
    last.put("other_notice",other_notice);
    last.put("submitter",submitter);
    return last;
}
//新表单的访问路径，映射到newForm页面
@RequestMapping("/newForm")
public String newForm(){
    return "newForm";
}
}
