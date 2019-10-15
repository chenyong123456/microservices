package cn.knowimage.service.impl;

import cn.knowimage.mapper.Pathway_InfoMapper;
import cn.knowimage.pojo.Pathway_Info;
import cn.knowimage.service.Pathway_InfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Pathway_InfoServiceImpl implements Pathway_InfoService {

    //对表Pathway_info实现增加
    @Autowired
    public Pathway_InfoMapper pathway_InfoMapper;

    @Override
    public int insertPathway_info(Pathway_Info pathway_Info) {
        return pathway_InfoMapper.insertPathway_info(pathway_Info);
    }

    //	查询Pathway_Info表中的所有数据
    @Override
    public List<Pathway_Info> findAll() {
        return pathway_InfoMapper.findAll();
    }

    //对表Pathway_info进行但单个字段的查询
    @Override
    public List<Pathway_Info> selectByPathway_name(String pathWay_name) {
        return pathway_InfoMapper.selectByPathway_name(pathWay_name);
    }

    @Override
    public List<Pathway_Info> selectByPathway_id(Integer pathway_id) {
        return pathway_InfoMapper.selectByPathway_id(pathway_id);
    }
}