package cn.knowimage.service.impl;

import cn.knowimage.mapper.AuditInfoMapper;
import cn.knowimage.mapper.PathwayInfoMapper;
import cn.knowimage.pojo.AuditInfo;
import cn.knowimage.pojo.Department;
import cn.knowimage.pojo.PathwayInfo;
import cn.knowimage.service.PathwayInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PathwayInfoServiceImpl implements PathwayInfoService {

    //对表Pathway_info实现增加
    @Autowired
    public PathwayInfoMapper pathway_InfoMapper;

    @Autowired
    public AuditInfoMapper audit_infoMapper;


    @Override
    public int insertPathway_info(PathwayInfo pathway_Info) {
        return pathway_InfoMapper.insertPathway_info(pathway_Info);
    }

    //	查询Pathway_Info表中的所有数据
    @Override
    public List<PathwayInfo> findAll() {
        return pathway_InfoMapper.findAll();
    }

    //对表Pathway_info进行但单个字段的查询
    @Override
    public List<PathwayInfo> selectByPathway_name(String pathWay_name) {
        return pathway_InfoMapper.selectByPathway_name(pathWay_name);
    }

    //对表Pathway_info进行pathway_name和pathway_index进行查询
    @Override
    public List<PathwayInfo> selectByPathway_nameApplet(String pathWay_name,String pathway_index) {
        return pathway_InfoMapper.selectByPathway_nameApplet(pathWay_name,pathway_index);
    }

    //根据PathWay_id查询全部
    @Override
    public List<PathwayInfo> findByPathWay_id(int pathWay_id) {
        return pathway_InfoMapper.findByPathWay_id(pathWay_id);
    }

    @Override
    public List<PathwayInfo> selectByPathway_id(Integer pathway_id) {
        return pathway_InfoMapper.selectByPathway_id(pathway_id);
    }

    @Override
    public int ReturnString(PathwayInfo pathway_Info) {
        return pathway_InfoMapper.ReturnString(pathway_Info);
    }

    //根据name模糊查询，并根据提交者筛选
    @Override
    public List<AuditInfo> selectByNameAndSubmitter(String query, String nameSub) {
        return pathway_InfoMapper.selectByNameAndSubmitter(query, nameSub);
    }

    //根据id查询，修改之前的selectName
    @Override
    public List<PathwayInfo> selectId(Integer selectId) {
        return pathway_InfoMapper.selectId(selectId);
    }

    //根据query模糊查询,query表示pathway_name
    @Override
    public List<AuditInfo> findAllById(String query) {
        return pathway_InfoMapper.findAllById(query);
    }

    //查询全部科室名字
    @Override
    public List<Department> findAllName(){
        return pathway_InfoMapper.findAllName();
    }

    //根据输入的department_name 查询该科室名字下对应的所有pathway_name
    @Override
    public List<PathwayInfo> selectP_nameByD_name(String name){
        return pathway_InfoMapper.selectP_nameByD_name(name);
    }

    @Override
    public String selectTableByApplet(String p_name, String p_index) {
        return pathway_InfoMapper.selectTableByApplet(p_name,p_index);
    }

    //查询Pathway_Info表中的所有数据
    @Override
    public List<PathwayInfo> findAllf() {
        return pathway_InfoMapper.findAllf();
    }


}