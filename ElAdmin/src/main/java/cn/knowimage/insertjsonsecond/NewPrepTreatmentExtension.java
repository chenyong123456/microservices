package cn.knowimage.insertjsonsecond;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Mr.G
 * @date 2019/8/12
 * 将前端输入的Treatment_ExtensionJson字段格式修改成数据库规定的json格式
 */
public class NewPrepTreatmentExtension {
    public static JSONObject Treatment_ExtensionJson(String prep_treatment_extension) {
        //定义总对象，将之后所有生成的对象都放入其中
        JSONObject Treatment_ExtensionJson = new JSONObject();
        //定义总数组存放输入的字符串
        JSONArray prep_treatment_extension_s = JSONArray.fromObject(prep_treatment_extension);
        JSONObject id_ = new JSONObject();
        //如果prep_treatment_extension_s数组为空，则需将输入的该值转为数据库标准值，包含的所有值为空
        if (prep_treatment_extension_s==null||prep_treatment_extension_s.size()==0){
            JSONObject id_0 = new JSONObject();
            JSONArray content_item = new JSONArray();
            content_item.add("");
            id_0.put("content","");
            id_0.put("content_item",content_item);
            Treatment_ExtensionJson.put("num",1);
            Treatment_ExtensionJson.put("id_0",id_0);
        }else {
            //num为输入数组长度
            int num = prep_treatment_extension_s.size();
            Treatment_ExtensionJson.put("num", num);
            //根据数组长度循环
            for (int i = 0; i < num; i++) {
                //定义该对象，依次接收数组中的每一个对象
                JSONObject jsonObject = prep_treatment_extension_s.getJSONObject(i);
                id_.put("content", jsonObject.getString("content"));
                JSONArray content_item = new JSONArray();
                //根据键值对形式获取对象中的数组
                JSONArray content_item_s = jsonObject.getJSONArray("content_item");
                for (int j = 0; j < content_item_s.size(); j++) {
                    //将content_item_s数组下的第i个json对象的value值加入到content_item数组中
                    content_item.add(content_item_s.getJSONObject(j).getString("value"));
                }
                id_.put("content_item", content_item);
                //动态创建id_X对象
                Treatment_ExtensionJson.put(String.format("id_%d", i), id_);
            }
        }


        return Treatment_ExtensionJson;
    }
}
