package cn.knowimage.service;

import cn.knowimage.pojo.AuditInfo;
import cn.knowimage.pojo.Department;
import cn.knowimage.pojo.PathwayInfo;

import java.util.List;

public interface PathwayInfoService {

	public List<PathwayInfo> findAllf();

	public List<PathwayInfo> findAll();

	public int insertPathway_info(PathwayInfo pathway_Info);

	public List<PathwayInfo> selectByPathway_name(String pathWay_name);

	public List<PathwayInfo> selectByPathway_nameApplet(String pathWay_name, String pathway_index);

	public List<PathwayInfo> selectByPathway_id(Integer pathway_id);


	//这里为要整合的代码
	public List<PathwayInfo> findByPathWay_id(int pathWay_id);

	public int ReturnString(PathwayInfo pathway_Info);

	public List<AuditInfo> selectByNameAndSubmitter(String query, String nameSub);

	public List<PathwayInfo> selectId(Integer selectId);

	public List<AuditInfo> findAllById(String query);

	public List<Department> findAllName();

	public List<PathwayInfo> selectP_nameByD_name(String name);

}