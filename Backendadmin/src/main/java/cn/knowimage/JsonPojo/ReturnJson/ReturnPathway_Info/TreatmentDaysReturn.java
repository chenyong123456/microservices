package cn.knowimage.JsonPojo.ReturnJson.ReturnPathway_Info;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Mr.G
 * @date 2019/8/12
 * 数据回显功能实现方法
 * 将数据库中treatment_days字段格式转换为前台能够识别的数据格式
 * 其中下划线变量对应数据库原字段，无需更改
 */
public class TreatmentDaysReturn {
    public static String treatment_days(String treatment_days) {
        //定义一个总对象，存放输入的字符串
        JSONObject treatment_days_s = JSONObject.fromObject(treatment_days);
        //获取总对象里的scenario对象，对象需要层层剥离，否则获取不到值
        JSONObject scenario = treatment_days_s.getJSONObject("scenario");
        //定义总数组，将转换的值全部放入其中
        JSONArray treatment_days_num = new JSONArray();

        //如果scenario对象为空，则需返回前端可接受的格式，且scenario对象里的所有值为空
        if (Integer.parseInt(scenario.getString("num"))==0){
            //定义该对象，存放tag_name，time_unit，min，max四个空对象
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("tag_name","");
            jsonObject.put("time_unit","");
            jsonObject.put("text","");
            jsonObject.put("min","0");
            jsonObject.put("max","0");
            //将jsonObject对象放入数组中
            treatment_days_num.add(jsonObject);
        }else {

            //通过获取scenario对象中的num值进行循环，
            for (int i = 0; i < Integer.parseInt(scenario.getString("num")); i++) {
                //定义jsonObject对象，动态获取scenario对象下的id_X对象的字符串值
                JSONObject jsonObject = scenario.getJSONObject(String.format("id_%d", i));
                JSONObject value = new JSONObject();
                //获取jsonObject对象中键名为tag_name的值，放入对象value中，且名字也为tag_name，下同
                value.put("tag_name", jsonObject.getString("tag_name"));
                value.put("text", jsonObject.getString("time_text"));
                //如果text为空，则正常输出数据库的值给前台
                if ("".equals(jsonObject.getString("time_text"))) {
                    //获取jsonObject下的键名为time_unit的字符串值，放入对象value中
                    value.put("time_unit", jsonObject.getString("time_unit"));
                    //获取int类型数据
                    value.put("min", jsonObject.getInt("min"));
                    value.put("max", jsonObject.getInt("max"));
                    treatment_days_num.add(value);
                } else {//text不为空，则返回time_unit为”其他时间描述“，且min，max为空
                    value.put("time_unit", jsonObject.getString("time_unit"));
                    //获取int类型数据
                    value.put("min", 0);
                    value.put("max", 0);
                    treatment_days_num.add(value);
                }
            }
        }

        return treatment_days_num.toString();
    }
}
