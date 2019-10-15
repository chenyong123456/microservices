package cn.knowimage.mapper;

import cn.knowimage.pojo.Audit_Info;
import cn.knowimage.pojo.Audit_InfoAndPathway_Info;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface Audit_InfoMapper {


	@Select("select * from audit_info where pathway_id = #{pathway_id}")
	public List<Audit_Info> selectByPathway_id(@Param("pathway_id") Integer pathway_id);

	@Select("SELECT a.pathway_id,\n" +
			"\t\t\ta.pathway_name,\n" +
			"\t\t\tsubmitter,\n" +
			"\t\t\tdata_upload_moment,\n" +
			"\t\t\tchecker,\n" +
			"\t\t\taudit_state,\n" +
			"\t\t\taudit_time,\n" +
			"\t\t\taudit_remark,\n" +
			"\t\t\tsubmitter_id,\n" +
			"\t\t\tchecker_id,\n" +
			"\t\t\tpathway_index,\n" +
			"\t\t\tfirst_diagnosis,\n" +
			"\t\t\tsuitable_subject_disc,\n" +
			"\t\t\tdiagnosis,\n" +
			"\t\t\ttreatment_choice,\n" +
			"\t\t\ttreatment_days,\n" +
			"\t\t\ttreatment_entry_standard,\n" +
			"\t\t\ttype,\n" +
			"\t\t\tdrug_use_period,\n" +
			"\t\t\tprep_treatment_common,\n" +
			"\t\t\tprep_treatment_drug_usage,\n" +
			"\t\t\tprep_treatment_extension,\n" +
			"\t\t\ttreatment,\n" +
			"\t\t\tdrug_usage,\n" +
			"\t\t\tafter_medical_treatment,\n" +
			"\t\t\tafter_treatment_drug_usage,\n" +
			"\t\t\tdischarge_criteria,\n" +
			"\t\t\tother_notice\n" +
			"\t\t\tFROM\n" +
			"\t\t\taudit_info a,\n" +
			"\t\t\tpathway_info p\n" +
			"\t\t\tWHERE\n" +
			"\t\t\ta.pathway_id = p.pathway_id AND audit_state in (${audit_state1},${audit_state2},${audit_state3}) AND a.pathway_name LIKE '%${pathway_name}%'")
	public List<Audit_Info> findAll(@Param("pathway_name") String pathway_name,@Param("audit_state1") Integer audit_state1,@Param("audit_state2") Integer audit_state2,
									@Param("audit_state3") Integer audit_state3);

	public int insertAudit_info(Audit_Info audit_Info);

	//根据pathway_name查询Audit_info表中指定submitter所有数据
	@Select("SELECT * FROM audit_info WHERE submitter = #{submitter}")
	public List<Audit_Info> selectByName(@Param("submitter") String submitter);

	//根据pathway_name查询Audit_info表中指定submitter和相应的时间区所有数据
	@Select("SELECT * FROM audit_info WHERE submitter = #{submitter} AND data_upload_moment >= '${time1}' AND data_upload_moment <= '${time2}'")
	public List<Audit_Info> selectByNameToTime(@Param("submitter") String submitter,@Param("time1")String time1,@Param("time2")String time2);

	//注解开发和xml文件开发都可用不相互影响
	@Select("SELECT\n" +
			"\ta.pathway_id,\n"+
			"\ta.pathway_name,\n" +
			"\tsubmitter,\n" +
			"\tdata_upload_moment,\n" +
			"\tchecker,\n" +
			"\taudit_state,\n" +
			"\taudit_time,\n" +
			"\taudit_remark,\n" +
			"\tsubmitter_id,\n" +
			"\tchecker_id,\n" +
			"\tpathway_index,\n" +
			"\tfirst_diagnosis,\n" +
			"\tsuitable_subject_disc,\n" +
			"\tdiagnosis,\n" +
			"\ttreatment_choice,\n" +
			"\ttreatment_days,\n" +
			"\ttreatment_entry_standard,\n" +
			"\ttype,\n" +
			"\tdrug_use_period,\n" +
			"\tprep_treatment_common,\n" +
			"\tprep_treatment_drug_usage,\n" +
			"\tprep_treatment_extension,\n" +
			"\ttreatment,\n" +
			"\tdrug_usage,\n" +
			"\tafter_medical_treatment,\n" +
			"\tafter_treatment_drug_usage,\n" +
			"\tdischarge_criteria,\n" +
			"\tother_notice\n" +
			"FROM\n" +
			"\taudit_info a,\n" +
			"\tpathway_info p\n" +
			"WHERE\n" +
			"\ta.pathway_id = p.pathway_id AND audit_state in (${audit_state1},${audit_state2},${audit_state3})  AND a.pathway_name LIKE '%${pathway_name}%' limit ${(page-1)*rows},${rows}" )
	public List<Audit_InfoAndPathway_Info> selectById(@Param("page") Integer page, @Param("rows") Integer rows,@Param("pathway_name") String pathway_name,
													  @Param("audit_state1") Integer audit_state1,@Param("audit_state2") Integer audit_state2,
													  @Param("audit_state3") Integer audit_state3);

	//对表的审核的状态进行修改
	@Update("UPDATE audit_info" +
			" SET checker=#{checker},audit_state=#{audit_state},audit_time=NOW(),audit_remark=#{audit_remark},checker_id=#{checkerid}" +
			" WHERE pathway_id=#{pathway_id}")
	public int updateById(@Param("pathway_id") Integer pathway_id,@Param("checker") String checker,
						  @Param("audit_state") Integer audit_state,@Param("audit_remark") String audit_remark,
						  @Param("checkerid") Integer checkerid);

}
