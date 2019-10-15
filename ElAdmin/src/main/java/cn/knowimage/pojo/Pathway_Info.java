package cn.knowimage.pojo;

public class Pathway_Info {

	private Integer pathway_id;
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

	public Pathway_Info() {
		super();
	}
	public Pathway_Info(Integer pathway_id, String pathway_index, String first_diagnosis, String pathway_name,
						String suitable_subject_disc, String diagnosis, String treatment_choice, String treatment_days,
						String treatment_entry_standard, String type, Integer drug_use_period, String prep_treatment_common,
						String prep_treatment_drug_usage, String prep_treatment_extension, String treatment, String drug_usage,
						String after_medical_treatment, String after_treatment_drug_usage, String discharge_criteria,
						String other_notice) {
		super();
		this.pathway_id = pathway_id;
		this.pathway_index = pathway_index;
		this.first_diagnosis = first_diagnosis;
		this.pathway_name = pathway_name;
		this.suitable_subject_disc = suitable_subject_disc;
		this.diagnosis = diagnosis;
		this.treatment_choice = treatment_choice;
		this.treatment_days = treatment_days;
		this.treatment_entry_standard = treatment_entry_standard;
		this.type = type;
		this.drug_use_period = drug_use_period;
		this.prep_treatment_common = prep_treatment_common;
		this.prep_treatment_drug_usage = prep_treatment_drug_usage;
		this.prep_treatment_extension = prep_treatment_extension;
		this.treatment = treatment;
		this.drug_usage = drug_usage;
		this.after_medical_treatment = after_medical_treatment;
		this.after_treatment_drug_usage = after_treatment_drug_usage;
		this.discharge_criteria = discharge_criteria;
		this.other_notice = other_notice;
	}
	public Integer getPathway_id() {
		return pathway_id;
	}
	public void setPathway_id(Integer pathway_id) {
		this.pathway_id = pathway_id;
	}
	public String getPathway_index() {
		return pathway_index;
	}
	public void setPathway_index(String pathway_index) {
		this.pathway_index = pathway_index;
	}
	public String getFirst_diagnosis() {
		return first_diagnosis;
	}
	public void setFirst_diagnosis(String first_diagnosis) {
		this.first_diagnosis = first_diagnosis;
	}
	public String getPathway_name() {
		return pathway_name;
	}
	public void setPathway_name(String pathway_name) {
		this.pathway_name = pathway_name;
	}
	public String getSuitable_subject_disc() {
		return suitable_subject_disc;
	}
	public void setSuitable_subject_disc(String suitable_subject_disc) {
		this.suitable_subject_disc = suitable_subject_disc;
	}
	public String getDiagnosis() {
		return diagnosis;
	}
	public void setDiagnosis(String diagnosis) {
		this.diagnosis = diagnosis;
	}
	public String getTreatment_choice() {
		return treatment_choice;
	}
	public void setTreatment_choice(String treatment_choice) {
		this.treatment_choice = treatment_choice;
	}
	public String getTreatment_days() {
		return treatment_days;
	}
	public void setTreatment_days(String treatment_days) {
		this.treatment_days = treatment_days;
	}
	public String getTreatment_entry_standard() {
		return treatment_entry_standard;
	}
	public void setTreatment_entry_standard(String treatment_entry_standard) {
		this.treatment_entry_standard = treatment_entry_standard;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public Integer getDrug_use_period() {
		return drug_use_period;
	}
	public void setDrug_use_period(Integer drug_use_period) {
		this.drug_use_period = drug_use_period;
	}
	public String getPrep_treatment_common() {
		return prep_treatment_common;
	}
	public void setPrep_treatment_common(String prep_treatment_common) {
		this.prep_treatment_common = prep_treatment_common;
	}
	public String getPrep_treatment_drug_usage() {
		return prep_treatment_drug_usage;
	}
	public void setPrep_treatment_drug_usage(String prep_treatment_drug_usage) {
		this.prep_treatment_drug_usage = prep_treatment_drug_usage;
	}
	public String getPrep_treatment_extension() {
		return prep_treatment_extension;
	}
	public void setPrep_treatment_extension(String prep_treatment_extension) {
		this.prep_treatment_extension = prep_treatment_extension;
	}
	public String getTreatment() {
		return treatment;
	}
	public void setTreatment(String treatment) {
		this.treatment = treatment;
	}
	public String getDrug_usage() {
		return drug_usage;
	}
	public void setDrug_usage(String drug_usage) {
		this.drug_usage = drug_usage;
	}
	public String getAfter_medical_treatment() {
		return after_medical_treatment;
	}
	public void setAfter_medical_treatment(String after_medical_treatment) {
		this.after_medical_treatment = after_medical_treatment;
	}
	public String getAfter_treatment_drug_usage() {
		return after_treatment_drug_usage;
	}
	public void setAfter_treatment_drug_usage(String after_treatment_drug_usage) {
		this.after_treatment_drug_usage = after_treatment_drug_usage;
	}
	public String getDischarge_criteria() {
		return discharge_criteria;
	}
	public void setDischarge_criteria(String discharge_criteria) {
		this.discharge_criteria = discharge_criteria;
	}
	public String getOther_notice() {
		return other_notice;
	}
	public void setOther_notice(String other_notice) {
		this.other_notice = other_notice;
	}


}