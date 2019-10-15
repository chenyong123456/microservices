package cn.knowimage.JsonPojo.MakeJson.MakePathway_Info;

import cn.knowimage.pojo.ReceivePathway;
import cn.knowimage.util.StringConvertNumber;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MakeTreatmentDays {
    public static String make(ReceivePathway receivePathway) {
        String treatment_days= receivePathway.getTreatment_days();
        //定义总对象
        JSONObject Treatment_Days = new JSONObject();
        //将输入的字符串放入json数组
        JSONArray treatment_days_s = JSONArray.fromObject(treatment_days);
        JSONObject scenario = new JSONObject();

        //如果以下字段均为默认值，则前端未编辑该条字段
        if (    "".equals(treatment_days_s.getJSONObject(0).getString("time_unit"))&&
                "".equals(treatment_days_s.getJSONObject(0).getString("text"))&&
                "0".equals(treatment_days_s.getJSONObject(0).getString("min"))&&
                "0".equals(treatment_days_s.getJSONObject(0).getString("max"))){
            JSONObject id_0 = new JSONObject();
            scenario.put("num",0);
            id_0.put("tag_name","");
            id_0.put("time_unit","");
            id_0.put("min",0);
            id_0.put("max",0);
            id_0.put("time_text","");
            scenario.put("id_0",id_0);
        }else {
            //获得JSON数组长度
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
                    id_.put("time_unit", "小时");
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

        return Treatment_Days.toString();


    }
}
