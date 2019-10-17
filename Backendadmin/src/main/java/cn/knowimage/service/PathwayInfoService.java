package cn.knowimage.service;

import cn.knowimage.pojo.PathwayInfo;
import cn.knowimage.pojo.ReceivePathway;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.List;

public interface PathwayInfoService {
    int insertPathwayInfo(ReceivePathway receivePathway,PathwayInfo pathwayInfo);
    JSONObject findPathwayInfoByIndex(String pathway_index);
    JSONArray findLikePathwayName(String query);
    JSONObject pathwayByPage(String pathway_name,Integer audit_state1,Integer audit_state2,Integer audit_state3,Integer page,Integer rows,Integer commit_state1,Integer commit_state2);
    int updateAudit(PathwayInfo pathwayInfo);
    int deletePathwayInfo(String pathwayIndex);
    List<String> findMyWork(String editor_id) ;

}
