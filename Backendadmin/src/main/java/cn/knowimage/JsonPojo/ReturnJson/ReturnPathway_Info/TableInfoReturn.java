package cn.knowimage.JsonPojo.ReturnJson.ReturnPathway_Info;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class TableInfoReturn {
    public static String make(String table_info){
        JSONObject table_info_s = JSONObject.fromObject(table_info);
        //首先判断该json格式是否为默认格式
        if ("0".equals(table_info_s.getString("table_num"))){
            JSONArray jsonArray = new JSONArray();
            JSONObject table = new JSONObject();
            table.put("table_name","@GRID@_1");
            table.put("row_count","0");
            table.put("column_count","0");
            table.put("top_title","");
            table.put("below_description","");
            JSONArray content  = new JSONArray();
            table.put("content",content);
            jsonArray.add(table);
            return jsonArray.toString();
        }else {
            JSONArray jsonArray = new JSONArray();
            //循环遍历JSON
            for (int i = 0 ; i<Integer.parseInt(table_info_s.getString("table_num")) ; i++) {
                JSONObject table = new JSONObject();
                JSONObject jsonObject  = table_info_s.getJSONObject("table_"+i);
                table.put("table_name", jsonObject.getString("table_prefix"));
                table.put("row_count", jsonObject.getString("row_count"));
                table.put("column_count", jsonObject.getString("column_count"));
                table.put("top_title", jsonObject.getString("top_title"));
                table.put("below_description", jsonObject.getString("below_description"));
                JSONArray tableContent = jsonObject.getJSONArray("content");
                JSONArray content = new JSONArray();
                if (tableContent.getJSONArray(0)==null) {
                    table.put("content", content);
                }else {
                    JSONArray contentSon = new JSONArray();
                    for (int m =0 ; m<tableContent.size();m++) {
                        for (int j = 0; j < tableContent.getJSONArray(i).size(); j++) {
                            JSONObject value = new JSONObject();
                            value.put("value", tableContent.getJSONArray(m).get(j));
                            contentSon.add(value);
                            value.clear();
                        }
                        content.add(contentSon);
                        contentSon.clear();
                    }
                    table.put("content", content);
                }
                jsonArray.add(table);
            }
           return jsonArray.toString();
        }
    }
}
