package cn.knowimage.service.impl;

import cn.knowimage.mapper.PathCombineMapper;
import cn.knowimage.pojo.Pathway_Info;
import cn.knowimage.service.PathCombineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @params 对路径合并进行相应数据库的修改
 * @author 啊勇
 */
@Service
public class PathCombineServiceImpl implements PathCombineService {

    @Autowired
    private PathCombineMapper pathCombineMapper;

    @Override
    public int updateCheckCode(String check_code,String pathway_name) {
        return pathCombineMapper.updatePathCombine(check_code,pathway_name);
    }
}
