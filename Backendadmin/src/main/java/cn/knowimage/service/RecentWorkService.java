package cn.knowimage.service;

import cn.knowimage.pojo.PathwayInfo;
import cn.knowimage.pojo.RecentWork;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RecentWorkService {
    void insertRecentWork(PathwayInfo pathwayInfo,PathwayInfo oldPathwayInfo);

    List<RecentWork> findMyRecentWork(String username);
}
