package cn.knowimage.mapper;

import cn.knowimage.pojo.RecentWork;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface RecentWorkMapper {
    int insertRecentWork(@Param("EL_RECENT_WORK") RecentWork recentWork);

    List<RecentWork> findMyRecentWork(@Param("username") String username);
}
