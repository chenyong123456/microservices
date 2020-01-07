package cn.knowimage.insertjsonsecond;

import cn.knowimage.util.StringConvertNumber;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Mr.G
 * @date 2019/8/12
 * 将前端输入的Treatment_CommonJson字段格式修改成数据库规定的json格式
 */
public class NewPrepTreatmentCommon {
    public static JSONObject Treatment_CommonJson(String prep_treatmentCommon) {
        //创建总对象
        JSONObject Treatment_CommonJson = new JSONObject();
        //用数组接受输入的字符串
        JSONArray prep_treatmentCommon_s = JSONArray.fromObject(prep_treatmentCommon);
        JSONObject scenario = new JSONObject();
        JSONObject duration = new JSONObject();
        JSONObject id_ = new JSONObject();

        //如果传入的prep_treatmentCommon_s数组为空，则需创建数据库标准格式
        if (prep_treatmentCommon_s==null||prep_treatmentCommon_s.size()==0){
            JSONObject id_0 = new JSONObject();
            duration.put("time_unit","");
            duration.put("time_text","");
            duration.put("min","");
            duration.put("max","");
            id_0.put("duration",duration);
            JSONArray obligatory_exam = new JSONArray();
            JSONArray optional_exam = new JSONArray();
            JSONArray notification = new JSONArray();
            obligatory_exam.add("");
            optional_exam.add("");
            notification.add("");
            id_0.put("obligatory_exam",obligatory_exam);
            id_0.put("optional_exam",optional_exam);
            id_0.put("notification",notification);
            scenario.put("num",1);
            scenario.put("id_0",id_0);
            Treatment_CommonJson.put("scenario", scenario);
        }else {
            //获取prep_treatmentCommon_s数组的长度，进行循环判断
            int num = prep_treatmentCommon_s.size();
            for (int i = 0; i < num; i++) {
                JSONObject jsonObject = prep_treatmentCommon_s.getJSONObject(i);
                scenario.put("num", num);
                //text字段获取前台填入的其他描述字段
                String text = jsonObject.getString("text");
                duration.put("time_unit", "小时");
                duration.put("time_text", text);

                if ("".equals(jsonObject.getString("time_unit"))) {
                    //如果time_unit="",则 max=0,min=0
                    duration.put("time_unit", "");
                    duration.put("min", 0);
                    duration.put("max", 0);
                }
                //time_unit="其他时间描述",max=0,min=0
                else if ("其它时间描述".equals(jsonObject.getString("time_unit"))) {
                    duration.put("time_unit", "其它时间描述");
                    duration.put("min", 0);
                    duration.put("max", 0);
                } else {//time_unit!=""
                    if (jsonObject.getString("min") == null || "".equals(jsonObject.getString("min"))) {
                        duration.put("min", 0);
                    } else {
                        //将输入的min值用工具类StringConvertNumber进行转换，将输入的时间全部转换为小时制
                        duration.put("min", StringConvertNumber.stringToFloat(jsonObject.getString("time_unit"), jsonObject.getString("min")));
                    }

                    if (jsonObject.getString("max") == null || "".equals(jsonObject.getString("max"))) {
                        duration.put("max", 0);
                    } else {
                        //将输入的max值用工具类StringConvertNumber进行转换，将输入的时间全部转换为小时制
                        duration.put("max", StringConvertNumber.stringToFloat(jsonObject.getString("time_unit"), jsonObject.getString("max")));
                    }
                }
                id_.put("duration", duration);
                //创建obligatory_exam数组存放值
                JSONArray obligatory_exam_s = jsonObject.getJSONArray("obligatory_exam");
                JSONArray obligatory_exam = new JSONArray();
                for (int j = 0; j < obligatory_exam_s.size(); j++) {
                    //获取obligatory_exam_s数组下的第i个json对象的value值，通过键值对形式获取字符串
                    obligatory_exam.add(obligatory_exam_s.getJSONObject(j).getString("value"));
                }
                id_.put("obligatory_exam", obligatory_exam);

                //创建optional_exam数组存放值
                JSONArray optional_exam_s = jsonObject.getJSONArray("optional_exam");
                JSONArray optional_exam = new JSONArray();
                for (int j = 0; j < optional_exam_s.size(); j++) {
                    //获取optional_exam_s数组下的第i个json对象的value值，通过键值对形式获取字符串
                    optional_exam.add(optional_exam_s.getJSONObject(j).getString("value"));
                }
                id_.put("optional_exam", optional_exam);

                //创建notification数组存放值
                JSONArray notification_s = jsonObject.getJSONArray("notification");
                JSONArray notification = new JSONArray();
                for (int j = 0; j < notification_s.size(); j++) {
                    //获取notification_s数组下的第i个json对象的value值，通过键值对形式获取字符串
                    notification.add(notification_s.getJSONObject(j).getString("value"));
                }
                id_.put("notification", notification);
                scenario.put(String.format("id_%d", i), id_);

                Treatment_CommonJson.put("scenario", scenario);
            }
        }


        return Treatment_CommonJson;
    }
}
