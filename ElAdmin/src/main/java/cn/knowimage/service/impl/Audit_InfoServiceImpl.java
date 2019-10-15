
package cn.knowimage.service.impl;

import cn.knowimage.mapper.Audit_InfoMapper;
import cn.knowimage.pojo.Audit_Info;
import cn.knowimage.pojo.Audit_InfoAndPathway_Info;
import cn.knowimage.service.Audit_InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能:主要对表audit_info进行相应的操作
 * @author 啊勇
 */
@Service
public class Audit_InfoServiceImpl implements Audit_InfoService {

	@Autowired
	public Audit_InfoMapper audit_InfoMapper;

	//查询audit_Info表中的所有字段
	@Override
	public List<Audit_Info> findAll(String pathway_name,Integer audit_state1,Integer audit_state2,Integer audit_state3) {
		return audit_InfoMapper.findAll(pathway_name,audit_state1,audit_state2,audit_state3);
	}

	//对audit_Info实现添加一条记录
	@Override
	public int insertAudit_info(Audit_Info audit_Info) {
		return audit_InfoMapper.insertAudit_info(audit_Info);
	}

	//对对audit_Info表根据pathway_id来查询一条记录
	@Override
	public List<Audit_InfoAndPathway_Info> selectById(Integer page, Integer rows,String pathway_name,Integer audit_state1,Integer audit_state2,Integer audit_state3) {
		System.out.println("***************");
		System.out.println(page);
		System.out.println(audit_state1+":"+audit_state2+":"+audit_state3);
		return audit_InfoMapper.selectById(page,rows,pathway_name,audit_state1,audit_state2,audit_state3);
	}

	//对audit_Info表根据pathway_id来进行状态的审核
	@Override
	public int updateById(Integer pathway_id, String checker, Integer audit_state, String audit_remark, Integer checkerid) {
		return audit_InfoMapper.updateById(pathway_id,checker,audit_state,audit_remark,checkerid);
	}

	//根据pathway_name查询Audit_info表中所有数据
	@Override
	public List<Audit_Info> selectByName(String submitter) {
		return audit_InfoMapper.selectByName(submitter);
	}

	@Override
	public List<Audit_Info> selectByPathway_id(Integer pathway_id) {
		return audit_InfoMapper.selectByPathway_id(pathway_id);
	}
	//根据pathway_name查询Audit_info表中指定submitter和相应的时间区所有数据
	@Override
	public List<Audit_Info> selectByNameToTime(String submitter, String time1, String time2) {
		return audit_InfoMapper.selectByNameToTime(submitter,time1,time2);
	}
}
