package cn.knowimage.JsonPojo.MakeJson.MakePathway_Info;

import cn.knowimage.pojo.ReceivePathway;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MakeTableInfo  {
    public static String make(ReceivePathway receivePathway){
        //定义总对象
        JSONObject table_info = new JSONObject();
        //获得前端json table_info
        String receivePathwayTable_info = receivePathway.getTable_info();
        //根据格式转换为JSONArray
        JSONArray tableJsonArray = JSONArray.fromObject(receivePathwayTable_info);
        //开始遍历该JsonArray
        //1.首先判断该字段是否为默认值，即用户并没有在该表做操作
        if ("0".equals(tableJsonArray.getJSONObject(0).getString("row_count"))&&
            "0".equals(tableJsonArray.getJSONObject(0).getString("column_count"))&&
            "".equals(tableJsonArray.getJSONObject(0).getString("top_title"))&&
            "".equals(tableJsonArray.getJSONObject(0).getString("below_description"))&&
            "".equals(tableJsonArray.getJSONObject(0).getString("table_pos"))){
            System.out.println("tableInfo默认格式++++++++++++++++++++");
            //开始拼数据库默认值
            table_info.put("table_num",0);
            JSONObject table_0 = new JSONObject();
            table_0.put("table_prefix","@GRID@_1");
            table_0.put("row_count",0);
            table_0.put("column_count",0);
            table_0.put("top_title","");
            table_0.put("below_description","");
            table_0.put("table_pos","");
            JSONArray content = new JSONArray();
            JSONArray value = new JSONArray();
            content.add(value);
            table_0.put("content",content);
            table_info.put("table_0",table_0);
        }else {
            //获得长度
            int num = tableJsonArray.size();
            table_info.put("table_num",num);
            for (int i = 0 ; i<num ; i++){
                JSONObject jsonObject = new JSONObject();
                JSONObject object = tableJsonArray.getJSONObject(i);
                String table_prefix = object.getString("table_name");
                int row_count = object.getInt("row_count");
                int column_count = object.getInt("column_count");
                int table_pos = object.getInt("table_pos");
                String top_title = object.getString("top_title");
                String below_description = object.getString("below_description");
                JSONArray array = object.getJSONArray("content");
                JSONArray content = new JSONArray();
                JSONArray content_son= new JSONArray();
                jsonObject.put("table_prefix",table_prefix);
                jsonObject.put("row_count",row_count);
                jsonObject.put("column_count",column_count);
                jsonObject.put("top_title",top_title);
                jsonObject.put("below_description",below_description);
                jsonObject.put("table_pos",table_pos);
                //循环遍历前端传入的array
                //首先判断该array长度是否为0;
                if (array.size()==0){
                    content.add(content_son);
                }else {
                    for (int j = 0; j < array.size(); j++) {
                        for (int m=0;m<array.getJSONArray(j).size();m++) {
                            content_son.add(array.getJSONArray(j).getJSONObject(m).getString("value"));
                        }
                        content.add(content_son);
                        content_son.clear();
                    }
                }
                jsonObject.put("content",content);
                content_son.clear();
                content.clear();
                table_info.put("table_"+i,jsonObject);
            }
        }
        //table_info.put("listndex", receivePathway.get);
        return table_info.toString();
    }
}
