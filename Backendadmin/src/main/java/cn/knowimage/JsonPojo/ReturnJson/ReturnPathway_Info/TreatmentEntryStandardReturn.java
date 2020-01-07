package cn.knowimage.JsonPojo.ReturnJson.ReturnPathway_Info;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Mr.G
 * @date 2019/8/12
 * 数据回显功能实现方法
 * 将数据库中treatment_entry_standard字段格式转换为前台能够识别的数据格式
 * 其中下划线变量对应数据库原字段，无需更改
 */
public class TreatmentEntryStandardReturn {
    public static String treatment_entry_standard(String treatment_entry_standard) {
        //将输入的字符串转换为一个json对象
        JSONObject treatment_entry_standard_s = JSONObject.fromObject(treatment_entry_standard);
        //定义一个数组，存放对象
        JSONArray jsonArray = new JSONArray();
        JSONObject jsonObject = new JSONObject();
        //如果该字段为默认值，则传给前端默认值
        if (Integer.parseInt(treatment_entry_standard_s.getString("num"))==0){
            jsonObject.put("value", "");
            jsonArray.add(jsonObject);
            //jsonArray.add(jsonObject);
        }else {
            //获得对象中的num值，进行循环判断，由于输入值为string型，需转换为int
            for (int i = 0; i < Integer.parseInt(treatment_entry_standard_s.getString("num")); i++) {
                //定义str字符串，动态获取treatment_entry_standard_s对象下的id_x字符串
                String str = treatment_entry_standard_s.getString(String.format("id_%d", i));
                jsonObject.put("value", str);
                jsonArray.add(jsonObject);
            }
        }
        return jsonArray.toString();
    }
}
