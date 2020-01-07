package cn.knowimage.mapper;

import cn.knowimage.pojo.AuditInfo;
import cn.knowimage.pojo.Department;
import cn.knowimage.pojo.PathwayInfo;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.type.JdbcType;
import java.util.List;

//可以被mybatis扫描到用注解开发不用xml文件-》mapper-locations可以不用配置
//只有mapper和xml文件不在同一个文件夹下时才需要配置locations将mapper.class和xml文件分开
@Mapper
public interface PathwayInfoMapper {

	public List<PathwayInfo> findAllf();

	public List<PathwayInfo> findAll();

	public int insertPathway_info(@Param("pathwayInfo") PathwayInfo pathwayInfo);

	//对表pathway_info进行根据pathway_name进行查找
	public List<PathwayInfo> selectByPathway_name(@Param("pathwayName") String pathwayName);

	//对表pathway_info进行根据pathway_name和pathway_index进行查找
	public List<PathwayInfo> selectByPathway_nameApplet(@Param("pathwayName") String pathwayName, @Param("pathwayIndex") String pathwayIndex);

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
	@Select("select * from pathway_info where pathway_id = #{pathwayId}")
	public List<PathwayInfo> selectByPathway_id(@Param("pathwayId") Integer pathwayId);
	/**
	 * 根据pathWay_id查询
	 */
	public List<PathwayInfo> findByPathWay_id(@Param("pathwayId") int pathwayId);

	/**
	 * 返回String类型数据到页面
	 */
	public int ReturnString(@Param("pathwayInfo") PathwayInfo pathwayInfo);

	/**
	 * 根据name模糊查询，并根据提交者筛选
	 */
	public List<AuditInfo> selectByNameAndSubmitter(@Param("query") String query, @Param("nameSub") String nameSub);

	/**
	 * 根据输入的selectName查询，以上selectName原本为pathway_name，修改为selectName为pathway_id
	 */
	public List<PathwayInfo> selectId(@Param("selectId") Integer selectId);

	/**
	 * 根据query进行模糊查询， query表示pathway_name字段
	 */
	public List<AuditInfo> findAllById(@Param("query") String query);

	/**
	 * 小褚微信临床路径功能，返回全部科室名称
	 */
	public List<Department> findAllName();

	/**
	 * 根据department_name查询pathway_Info表里的所有pathway_name*/
	public List<PathwayInfo> selectP_nameByD_name(String name);


    String selectTableByApplet(@Param("pathwayName") String pathwayName, @Param("pathwayIndex") String pathwayIndex);
}