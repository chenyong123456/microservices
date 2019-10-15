package cn.knowimage.mapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

@Mapper
public interface PathCombineMapper {

    //路径合并对数据库的json格式的数据-->检查项修改为编码机制格式的字符串
    @Update("update pathway_info set check_code = #{check_code} where pathway_name = #{pathway_name}")
    public int updatePathCombine(@Param("check_code") String check_code,@Param("pathway_name") String pathway_name);
}
