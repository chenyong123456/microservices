package cn.knowimage.JsonPojo.ReturnJson;

import cn.knowimage.JsonPojo.ReturnJson.ReturnPathway_Info.*;
import cn.knowimage.pojo.PathwayInfo;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class ReturnJsonPathway_Info {
    public static JSONObject make(PathwayInfo pathwayInfo,String username){
        //创建总对象
        JSONObject last = new JSONObject();
        String pathway_index = pathwayInfo.getPathway_index();
        String publisher = pathway_index.substring(0, 2).replaceAll("\t", "");
        String publishYear = pathway_index.substring(2, 6).replaceAll("\t", "");
        String fileNumber = pathway_index.substring(6, 10).replaceAll("\t", "");
        String versionNumber = pathway_index.substring(10, 12).replaceAll("\t", "");
        String first_diagnose = pathwayInfo.getFirst_diagnosis().replaceAll("\t", "");
        String pathway_name = pathwayInfo.getPathway_name().replaceAll("\t", "");
        String suitable_subject_disc = pathwayInfo.getSuitable_subject_disc().replaceAll("\t", "");
        String diagnosis = pathwayInfo.getDiagnosis().replaceAll("\t", "");
        String treatment_choice = TreatmentChoiceReturn.treatment_choice_return(pathwayInfo.getTreatment_choice()).replaceAll("\t", "");
        String treatment_days = TreatmentDaysReturn.treatment_days(pathwayInfo.getTreatment_days()).replaceAll("\t", "");
        String treatment_entry_standard = TreatmentEntryStandardReturn.treatment_entry_standard(pathwayInfo.getTreatment_entry_standard()).replaceAll("\t", "");
        String type = pathwayInfo.getType().replaceAll("\t", "");
        //将数据库中int类型的drug_use_period字段转为string型
        String drug_use_period = String.valueOf(pathwayInfo.getDrug_use_period()).replaceAll("\t", "");
        String prep_treatment_common = PrepTreatmentCommonReturn.prep_treatmentCommon(pathwayInfo.getPrep_treatment_common()).replaceAll("\t", "");
        String prep_treatment_drug_usage = PrepTreatmentDrugUsageReturn.prep_treatment_drug_usage(pathwayInfo.getPrep_treatment_drug_usage()).replaceAll("\t", "");
        String prep_treatment_extension = PrepTreatmentExtensionReturn.prep_treatment_extension(pathwayInfo.getPrep_treatment_extension()).replaceAll("\t", "");
        String treatment = TreatmentReturn.treatment(pathwayInfo.getTreatment()).replaceAll("\t", "");
        String drug_usage = DrugUsageReturn.drug_usage(pathwayInfo.getDrug_usage()).replaceAll("\t", "");
        String after_medical_treatment = AfterMedicalTreatmentReturn.after_medical_treatment(pathwayInfo.getAfter_medical_treatment()).replaceAll("\t", "");
        String after_treatment_drug_usage = AfterTreatmentDrugUsageReturn.after_treatment_drug_usage(pathwayInfo.getAfter_treatment_drug_usage()).replaceAll("\t", "");
        String discharge_criteria = DischargeCriteriaReturn.discharge_criteria(pathwayInfo.getDischarge_criteria()).replaceAll("\t", "");
        String other_notice = OtherNoticeReturn.other_notice(pathwayInfo.getOther_notice()).replaceAll("\t", "");
        String additional_field = AdditionalFieldReturn.additional_field(pathwayInfo.getAdditional_field()).replaceAll("\t", "");
        Integer state = pathwayInfo.getAudit_state();
        System.out.println(pathwayInfo);
        Integer commit = Integer.parseInt(pathwayInfo.getCommit_state());
        String submitter = username;
        String table_info = TableInfoReturn.make(pathwayInfo.getTable_info().replaceAll("\t", ""));
        System.out.println("table_info===="+table_info);
        if (state==0 && commit==0) {
            state = 0;
        } else if (state==0 && commit==1) {
            state=1;
        } else if (state==1 && commit==1) {
            state=1;
        } else if(state==2 && commit==0) {
            state=0;
        }

        last.put("publisher", publisher);
        last.put("publishYear", publishYear);
        last.put("fileNumber", fileNumber);
        last.put("versionNumber", versionNumber);
        last.put("first_diagnose", first_diagnose);
        last.put("pathway_name", pathway_name);
        last.put("suitable_subject_disc", suitable_subject_disc);
        last.put("diagnosis", diagnosis);
        last.put("treatment_choice", treatment_choice);
        last.put("treatment_days", treatment_days);
        last.put("treatment_entry_standard", treatment_entry_standard);
        last.put("type", type);
        last.put("drug_use_period", drug_use_period);
        last.put("prep_treatment_common", prep_treatment_common);
        last.put("prep_treatment_drug_usage", prep_treatment_drug_usage);
        last.put("prep_treatment_extension", prep_treatment_extension);
        last.put("treatment", treatment);
        last.put("drug_usage", drug_usage);
        last.put("after_medical_treatment", after_medical_treatment);
        last.put("after_treatment_drug_usage", after_treatment_drug_usage);
        last.put("discharge_criteria", discharge_criteria);
        last.put("other_notice", other_notice);
        last.put("submitter", submitter);
        last.put("indefinite_field", additional_field);
        last.put("static", state);
        last.put("selectId", pathway_index);
        last.put("table_info" , table_info);
        System.out.println("返回前端的数据为:" + last);
        return last;
    }
}
