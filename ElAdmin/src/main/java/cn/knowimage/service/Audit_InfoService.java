
package cn.knowimage.service;

import cn.knowimage.pojo.Audit_Info;
import cn.knowimage.pojo.Audit_InfoAndPathway_Info;

import java.util.List;

public interface Audit_InfoService {

	public List<Audit_Info> findAll(String pathway_name,Integer audit_state1,Integer audit_state2,Integer audit_state3);

	public int insertAudit_info(Audit_Info audit_Info);

	public List<Audit_InfoAndPathway_Info> selectById(Integer page, Integer rows,String pathway_name,Integer audit_state1,Integer audit_state2,Integer audit_state3);

	public int updateById(Integer pathway_id,String checker,Integer audit_state,String audit_remark,Integer checkerid);

	public List<Audit_Info> selectByName(String submitter);

	public List<Audit_Info> selectByPathway_id(Integer pathway_id);

	public List<Audit_Info> selectByNameToTime(String submitter,String time1,String time2);

}
