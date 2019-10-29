package cn.knowimage.mapper;

import cn.knowimage.pojo.PathwayInfo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface PathwayInfoMapper {
    //新增一条新的数据
    int insertPathwayInfo(@Param("pathway_Info") PathwayInfo pathwayInfo);
    //查找pathwayName通过Index
    String findNameByIndex(@Param("pathway_index") String pathway_index);
    //更新一条数据
    int updatePathwayInfo(@Param("pathway_Info") PathwayInfo pathwayInfo);
    //通过pathway_index查找一条信息
    PathwayInfo findPathwayInfoByIndex(@Param("pathway_index") String pathway_index);
    //模糊查询，查找有query字段的内容
    List<PathwayInfo> findLikePathwayName(@Param("query") String query);
    //模糊查询，查找包括审核状态的字段，包括模糊搜索的所有字段条数
    int findAllStateAndLikeName(@Param("pathway_name") String pathway_name,
                             @Param("audit_state1") Integer audit_state1,
                             @Param("audit_state2") Integer audit_state2,
                             @Param("audit_state3") Integer audit_state3,
                             @Param("commit_state1") Integer commit_state1,
                             @Param("commit_state2") Integer commit_state2);
    //模糊分页查询
    List<PathwayInfo> selectPathwayByPage(@Param("page") Integer page,
                                          @Param("rows") Integer rows,
                                          @Param("pathway_name") String pathway_name,
                                          @Param("audit_state1") Integer audit_state1,
                                          @Param("audit_state2") Integer audit_state2,
                                          @Param("audit_state3") Integer audit_state3,
                                          @Param("commit_state1") Integer commit_state1,
                                          @Param("commit_state2") Integer commit_state2);
    //审核界面审核，既更新后面有关审核的字段
    int updateAudit (@Param("pathwayInfo") PathwayInfo pathwayInfo);

    int deletePathwayInfoByIndex(@Param("pathway_index") String pathwayIndex);

    List<String> selectPathwayNameByEditorId(@Param("editor_id") String editor_id);

    List<PathwayInfo> selectPathwayNameByUserName(@Param("query") String query,@Param("username") String username);

    List<PathwayInfo> finderror();
}
