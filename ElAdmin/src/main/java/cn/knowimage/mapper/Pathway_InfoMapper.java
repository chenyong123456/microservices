package cn.knowimage.mapper;

import cn.knowimage.pojo.Pathway_Info;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import java.util.List;
//可以被mybatis扫描到用注解开发不用xml文件-》mapper-locations可以不用配置
//只有mapper和xml文件不在同一个文件夹下时才需要配置locations将mapper.class和xml文件分开
@Mapper
public interface Pathway_InfoMapper {

	public List<Pathway_Info> findAll();

	public int insertPathway_info(@Param("pathway_Info") Pathway_Info pathway_Info);

	//对表pathway_info进行根据pathway_name进行查找
	public List<Pathway_Info> selectByPathway_name(@Param("pathWay_name") String pathWay_name);

	//对表pathway_info进行查询一条数据查询指定的数据通过pathway_id进行查询
	@Results(value={
			@Result(column="pathway_index", property="pathway_index",jdbcType= JdbcType.INTEGER),
			@Result(column="pathway_id", property="pathway_id",jdbcType=JdbcType.VARCHAR),
			@Result(column="first_diagnosis", property="first_diagnosis",jdbcType=JdbcType.VARCHAR),
			@Result(column="pathway_name", property="pathway_name"),
			@Result(column="suitable_subject_disc", property="suitable_subject_disc"),
			@Result(column="treatment_choice", property="treatment_choice"),
			@Result(column="treatment_days", property="treatment_days"),
			@Result(column="treatment_entry_standard", property="treatment_entry_standard"),
			@Result(column="drug_use_period", property="drug_use_period"),
			@Result(column="prep_treatment_common", property="prep_treatment_common"),
			@Result(column="prep_treatment_drug_usage", property="prep_treatment_drug_usage"),
			@Result(column="prep_treatment_extension", property="prep_treatment_extension"),
			@Result(column="drug_usage", property="drug_usage"),
			@Result(column="after_medical_treatment", property="after_medical_treatment"),
			@Result(column="after_treatment_drug_usage", property="after_treatment_drug_usage"),
			@Result(column="discharge_criteria", property="discharge_criteria"),
			@Result(column="other_notice", property="other_notice"),
	})
	@Select("select * from pathway_info where pathway_id = #{pathway_id}")
	public List<Pathway_Info> selectByPathway_id(@Param("pathway_id") Integer pathway_id);

}