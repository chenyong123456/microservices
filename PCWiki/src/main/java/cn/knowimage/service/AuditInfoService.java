
package cn.knowimage.service;

import cn.knowimage.pojo.AuditInfo;
import cn.knowimage.pojo.AuditInfoAndPathwayInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AuditInfoService {

	public List<AuditInfo> findAll(String pathway_name, Integer audit_state1, Integer audit_state2, Integer audit_state3);

	public int insertAudit_info(AuditInfo audit_Info);

	public List<AuditInfoAndPathwayInfo> selectById(Integer page, Integer rows, String pathway_name, Integer audit_state1, Integer audit_state2, Integer audit_state3);

	public int updateById(Integer pathway_id, String checker, Integer audit_state, String audit_remark, Integer checkerid);

	public List<AuditInfo> selectByName(String submitter);

	public List<AuditInfo> selectByPathway_id(Integer pathway_id);

	public List<AuditInfo> selectByNameToTime(String submitter, String time1, String time2);


//	代码整合的方法
	public List<AuditInfo> findByPathWay_id(@Param("pathWay_id") int pathWay_id);

	public List<AuditInfo> selectByPathway_name(String selectName);

	public List<AuditInfo> selectId(Integer selectName);

}
