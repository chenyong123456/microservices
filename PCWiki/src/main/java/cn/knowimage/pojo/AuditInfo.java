package cn.knowimage.pojo;

public class AuditInfo {

	private Integer pathway_id;
	private String pathway_name;
	private String submitter;
	private String data_upload_moment;
	private String checker;
	private Integer audit_state;

	private String audit_time;
	private String audit_remark;
	private Integer submitter_id;
	private Integer checker_id;

	public AuditInfo() {
		super();
	}

	public AuditInfo(Integer pathway_id, String pathway_name, String submitter, String data_upload_moment, String checker, Integer audit_state, String audit_time, String audit_remark, Integer submitter_id, Integer checker_id) {
		this.pathway_id = pathway_id;
		this.pathway_name = pathway_name;
		this.submitter = submitter;
		this.data_upload_moment = data_upload_moment;
		this.checker = checker;
		this.audit_state = audit_state;
		this.audit_time = audit_time;
		this.audit_remark = audit_remark;
		this.submitter_id = submitter_id;
		this.checker_id = checker_id;
	}

	public Integer getPathway_id() {
		return pathway_id;
	}

	public void setPathway_id(Integer pathway_id) {
		this.pathway_id = pathway_id;
	}

	public String getPathway_name() {
		return pathway_name;
	}

	public void setPathway_name(String pathway_name) {
		this.pathway_name = pathway_name;
	}

	public String getSubmitter() {
		return submitter;
	}

	public void setSubmitter(String submitter) {
		this.submitter = submitter;
	}

	public String getData_upload_moment() {
		return data_upload_moment;
	}

	public void setData_upload_moment(String data_upload_moment) {
		this.data_upload_moment = data_upload_moment;
	}

	public String getChecker() {
		return checker;
	}

	public void setChecker(String checker) {
		this.checker = checker;
	}

	public Integer getAudit_state() {
		return audit_state;
	}

	public void setAudit_state(Integer audit_state) {
		this.audit_state = audit_state;
	}

	public String getAudit_time() {
		return audit_time;
	}

	public void setAudit_time(String audit_time) {
		this.audit_time = audit_time;
	}

	public String getAudit_remark() {
		return audit_remark;
	}

	public void setAudit_remark(String audit_remark) {
		this.audit_remark = audit_remark;
	}

	public Integer getSubmitter_id() {
		return submitter_id;
	}

	public void setSubmitter_id(Integer submitter_id) {
		this.submitter_id = submitter_id;
	}

	public Integer getChecker_id() {
		return checker_id;
	}

	public void setChecker_id(Integer checker_id) {
		this.checker_id = checker_id;
	}
}
