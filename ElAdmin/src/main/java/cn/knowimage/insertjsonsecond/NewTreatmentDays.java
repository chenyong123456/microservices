package cn.knowimage.insertjsonsecond;

import cn.knowimage.util.StringConvertNumber;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Mr.G
 * @date 2019/8/12
 * 将前端输入的Treatment_Days字段格式修改成数据库规定的json格式
 */
public class NewTreatmentDays {
    public static JSONObject Treatment_Days(String treatment_days) {
        //定义总对象
        JSONObject Treatment_Days = new JSONObject();
        //将输入的字符串放入json数组
        JSONArray treatment_days_s = JSONArray.fromObject(treatment_days);
        JSONObject scenario = new JSONObject();

        //如果treatment_days_s为空，说明前端传入的treatment_days数组为空，但仍需改成标准格式存入数据库，值为空
        if (treatment_days_s==null||treatment_days_s.size()==0){
            JSONObject id_0 = new JSONObject();
            scenario.put("num",1);
            id_0.put("tag_name","");
            id_0.put("time_unit","");
            id_0.put("min",0);
            id_0.put("max",0);
            id_0.put("time_text","");
            scenario.put("id_0",id_0);
        }else {
            int num = treatment_days_s.size();
            for (int i = 0; i < num; i++) {
                //获取数组中的对象
                JSONObject jsonObject = treatment_days_s.getJSONObject(i);
                scenario.put("num", num);
                JSONObject id_ = new JSONObject();
                //定义text，接受前台传来的text值
                //text字段获取前台填入的其他描述字段
                String text = jsonObject.getString("text");
                id_.put("tag_name", jsonObject.getString("tag_name"));
                id_.put("time_text", text);
                // id_.put("time_unit","小时");
                //time_unit="",max=0,min=0
                if ("".equals(jsonObject.getString("time_unit"))) {
                    id_.put("time_unit", "");
                    id_.put("min", 0);
                    id_.put("max", 0);
                }
                //time_unit="其它时间描述",max=0,min=0
                else if ("其它时间描述".equals(jsonObject.getString("time_unit"))) {
                    id_.put("time_unit", "其它时间描述");
                    id_.put("min", 0);
                    id_.put("max", 0);
                } else {//time_unit!=""
                    id_.put("time_unit", "时");
                    if (jsonObject.getString("max") == null || "".equals(jsonObject.getString("max"))) {
                        id_.put("min", 0);
                    } else {
                        //将输入的时间统一转换成小时制，通过StringConvertNumber方法
                        id_.put("min", StringConvertNumber.stringToFloat(jsonObject.getString("time_unit"), jsonObject.getString("min")));
                    }

                    if (jsonObject.getString("max") == null || "".equals(jsonObject.getString("max"))) {
                        id_.put("max", 0);
                    } else {
                        //将输入的时间统一转换成小时制，通过StringConvertNumber方法
                        id_.put("max", StringConvertNumber.stringToFloat(jsonObject.getString("time_unit"), jsonObject.getString("max")));
                    }
                }
                scenario.put(String.format("id_%d", i), id_);

            }
        }


        Treatment_Days.put("scenario", scenario);

        return Treatment_Days;

    }
}
