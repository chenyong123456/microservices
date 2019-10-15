package cn.knowimage.JsonPojo.ReturnJson.ReturnPathway_Info;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author 彭雷
 * @date 2019/9/10
 * 数据回显功能实现方法
 * 将数据库中additional_field字段格式转换为前台能够识别的数据格式
 * 其中下划线变量对应数据库原字段，无需更改
 *  indefinite_field: [
 *         {
 *           field_pos: "",
 *           field_name: "",
 *           field_content: [
 *             {
 *               value: ""
 *             }
 *           ]
 *         }
 *       ],
 *
 */
public class AdditionalFieldReturn {
    public static String additional_field(String additional_field){
        //获取数据库JSON类型
        JSONObject jsonObject = JSONObject.fromObject(additional_field);
        //穿给前端的JSONArray
        JSONArray jsonArray = new JSONArray();
        //临时JSON  field_pos
        JSONObject field_pos = new JSONObject();
        //临时JSON  field_name
        JSONObject field_name = new JSONObject();
        //临时JSON  field_content
        JSONArray field_content = new JSONArray();

        JSONObject field_content_s = new JSONObject();
        //判断JSON是否为默认值
        if (!"0".equals(jsonObject.getString("field_num"))) {

            //非  开始给前端拼JSON
            //根据约定  field_num既为 jsonArray的长度
            int nums=Integer.parseInt(jsonObject.getString("field_num"));
            for (int i = 0; i < nums; i++) {
                //临时字段 field
                JSONObject field = jsonObject.getJSONObject(String.format("field_%d",i));
                if (field.getString("field_pos")!=null) {
                    field.put("field_pos", field.getString("field_pos"));
                }else field.put("field_pos","");
                if (field.getString("field_name")!=null) {
                    field.put("field_name", field.getString("field_name"));
                }else  field.put("field_name","");
                //开始遍历field_content
                //获得field_content长度 即为num
                int num = field.getJSONArray("field_content").size();
                for ( int j=0 ; j < num ; j++){
                    //创建临时字段value
                    JSONObject value = new JSONObject();
                    value.put("value",field.getJSONArray("field_content").getString(j).replaceAll("\t",""));
                    field_content.add(value);
                }
                field.put("field_content",field_content);
                field_content.clear();
                jsonArray.add(field);
            }
        }else {
            //临时JSON field
            JSONObject field = new JSONObject();
            //数据库中的为默认值，既field_num=0
            field.put("field_pos","3");
            field.put("field_name","");
            field_content_s.put("value","");
            field_content.add(field_content_s);
            field.put("field_content",field_content);
            jsonArray.add(field);
        }
        return jsonArray.toString();

    }
}
