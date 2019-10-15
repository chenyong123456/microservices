package cn.knowimage.service;

import net.sf.json.JSONObject;

/**
 * 功能:主要为Bpmn提供接口
 * @author 啊勇
 */
public interface BpmnService {

    //查询指定的pathway_id的一条记录
    public JSONObject selectByPathway_id(String pathway_id);

}
