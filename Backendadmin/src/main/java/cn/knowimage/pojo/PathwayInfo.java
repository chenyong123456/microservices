package cn.knowimage.pojo;

import lombok.Data;

@Data
public class PathwayInfo {
    private String pathway_id;
    private String pathway_index;
    private String first_diagnosis;
    private String pathway_name;
    private String suitable_subject_disc;
    private String diagnosis;
    private String treatment_choice;
    private String treatment_days;
    private String treatment_entry_standard;
    private String type;
    private Integer drug_use_period;
    private String prep_treatment_common;
    private String prep_treatment_drug_usage;
    private String prep_treatment_extension;
    private String treatment;
    private String drug_usage;
    private String after_medical_treatment;
    private String after_treatment_drug_usage;
    private String discharge_criteria;
    private String other_notice;
    private String additional_field;
    private String data_upload_moment;
    private Integer audit_state;
    private String audit_time;
    private String audit_remark;
    private String submitter_id;
    private String checker_id;
    private String commit_state;
    private String editor_id;
    private String table_info;
}
