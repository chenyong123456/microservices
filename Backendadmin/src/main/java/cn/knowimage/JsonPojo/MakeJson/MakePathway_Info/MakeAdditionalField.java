package cn.knowimage.JsonPojo.MakeJson.MakePathway_Info;

import cn.knowimage.pojo.ReceivePathway;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 对应前端页面中的额外字段内容
 */
public class MakeAdditionalField {
    public static String make(ReceivePathway receivePathway){
        String indefinite_field = receivePathway.getIndefinite_field();
        //定义总对象  additional_field为JSONObject
        JSONObject additional_field = new JSONObject();
        //临时JSON对象field
        JSONObject field = new JSONObject();
        //创建临时JSON数组 field_content
        JSONArray field_content = new JSONArray();
        //获得前端的JSON对象
        JSONArray indefinite_field_s = JSONArray.fromObject(indefinite_field);
        //判断是否为空，为空则默认
        if ("".equals(indefinite_field_s.getJSONObject(0).getString("field_name"))){
            additional_field.put("field_num",0);
        }else {
            //获得json长度，既num值
            additional_field.put("field_num", indefinite_field_s.size());
            //开始遍历JSON，转成规定格式
            for (int i = 0 ; i<indefinite_field_s.size();i++){

                //创建临时json对象
                //获得field_pos  遍历indefinite_field_s第i个，获取键为field_pos，存入field_pos
                field.put("field_pos",indefinite_field_s.getJSONObject(i).getString("field_pos"));

                //获得field_name 遍历indefinite_field_s第i个，获取键为field_name，存入field_name
                field.put("field_name",indefinite_field_s.getJSONObject(i).getString("field_name"));

                //获得field_content
                for (int j = 0 ; j<indefinite_field_s.getJSONObject(i).getJSONArray("field_content").size();j++){
                    field_content.add(indefinite_field_s.getJSONObject(i).getJSONArray("field_content").getJSONObject(j).getString("value"));
                }

                //存入临时JSON对象field_content 给 field
                field.put("field_content",field_content);
                //存入additional_field

                additional_field.put(String.format("field_%d",i),field);
                //清空两个临时json
                field.clear();
                field_content.clear();
            }
        }
        return additional_field.toString();


    }
}
