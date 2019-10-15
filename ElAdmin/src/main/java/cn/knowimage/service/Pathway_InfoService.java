package cn.knowimage.service;

import cn.knowimage.pojo.Pathway_Info;

import java.util.List;

public interface Pathway_InfoService {

	public List<Pathway_Info> findAll();

	public int insertPathway_info(Pathway_Info pathway_Info);

	public List<Pathway_Info> selectByPathway_name(String pathWay_name);

	public List<Pathway_Info> selectByPathway_id(Integer pathway_id);

}