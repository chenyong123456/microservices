package cn.knowimage.JsonPojo.ReturnJson.ReturnPathway_Info;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Mr.G
 * @date 2019/8/12
 * 数据回显功能实现方法
 * 将数据库中prep_treatmentCommon字段格式转换为前台能够识别的数据格式
 * 其中下划线变量对应数据库原字段，无需更改
 */
public class PrepTreatmentCommonReturn {
    public static String prep_treatmentCommon(String prep_treatmentCommon) {
        JSONArray array = new JSONArray();
        JSONObject object = new JSONObject();
        //定义一个总对象，存放输入的字符串
        JSONObject prep_treatmentCommon_s = JSONObject.fromObject(prep_treatmentCommon);
        //获取总对象中的子对象scenario
        JSONObject scenario = prep_treatmentCommon_s.getJSONObject("scenario");

        //判断prep_treatmentCommon_s对象是否为空，如果为空，则返回前台可接受的格式，且所有值为空
        if ("0".equals(scenario.getString("num"))){
            object.put("time_unit","");
            object.put("text","");
            object.put("min","0");
            object.put("max","0");
            //返回前端需要的三个数组，且数组为空
            JSONObject value = new JSONObject();
            value.put("value","");
            JSONArray optional_exam = new JSONArray();
            JSONArray obligatory_exam = new JSONArray();
            JSONArray notification = new JSONArray();
            optional_exam.add(value);
            obligatory_exam.add(value);
            notification.add(value);
            //接收三个空数组，放入对象
            object.put("obligatory_exam",obligatory_exam);
            object.put("optional_exam",optional_exam);
            object.put("notification",notification);
            object.put("item_text_name1","");
            array.add(object);
            return array.toString();

        }else{
            //获取num值，转为为int型进行判断
            for (int i = 0; i < Integer.parseInt(scenario.getString("num")); i++) {
                //定义对象依次获取id_0中的对象
                JSONObject jsonObject = scenario.getJSONObject(String.format("id_%d", i));
                //获取scenario对象中的子对象duration
                JSONObject duration = jsonObject.getJSONObject("duration");
                JSONObject time_unit = new JSONObject();
                JSONObject text = new JSONObject();
                JSONObject min = new JSONObject();
                JSONObject max = new JSONObject();

                object.put("text", duration.getString("time_text"));
                //判断text是否为空，如果为空，正常输出已有的值，如果不为空，time_unit=其他时间描述，min，max为空
                if ("".equals(duration.getString("time_text"))) {
                    object.put("time_unit", duration.getString("time_unit"));
                    object.put("min", duration.getString("min"));
                    object.put("max", duration.getString("max"));

                } else {
                    object.put("time_unit", "其它时间描述");
                    object.put("min", "");
                    object.put("max", "");
                }
                //获取jsonObject对象中的数组
                JSONArray obligatory_exam_s = jsonObject.getJSONArray("obligatory_exam");
                //该数组用于存放输出的json对象
                JSONArray obligatory_exam = new JSONArray();
                if (obligatory_exam_s.size()==0){
                    JSONObject value = new JSONObject();
                    //获取optional_exam数组中的第j个字符串，赋值给value对象
                    value.put("value", "");
                    //将每次获取的value值放入数组
                    obligatory_exam.add(value);
                    object.put("obligatory_exam", obligatory_exam);
                }else {
                    for (int j = 0; j < obligatory_exam_s.size(); j++) {
                        JSONObject value = new JSONObject();
                        //获取obligatory_exam数组中的第j个字符串，赋值给value对象
                        value.put("value", obligatory_exam_s.getString(j));
                        //将每次获取的value值放入数组
                        obligatory_exam.add(value);
                        object.put("obligatory_exam", obligatory_exam);
                    }
                }

                JSONArray optional_exam_s = jsonObject.getJSONArray("optional_exam");
                //该数组用于存放输出的json对象
                JSONArray optional_exam = new JSONArray();
                //如果该字段为空
                if (optional_exam_s.size()==0){
                    JSONObject value = new JSONObject();
                    //获取optional_exam数组中的第j个字符串，赋值给value对象
                    value.put("value", "");
                    //将每次获取的value值放入数组
                    optional_exam.add(value);
                    object.put("optional_exam", optional_exam);
                }else {
                    for (int j = 0; j < optional_exam_s.size(); j++) {
                        JSONObject value = new JSONObject();
                        //获取optional_exam数组中的第j个字符串，赋值给value对象
                        value.put("value", optional_exam_s.getString(j));
                        //将每次获取的value值放入数组
                        optional_exam.add(value);
                        object.put("optional_exam", optional_exam);
                    }
                }

                //该数组用于存放输出的json对象
                JSONArray notification = new JSONArray();
                JSONArray notification_a = jsonObject.getJSONArray("item_content");
                //如果该字段为空
                if (notification_a.size()==0){
                    JSONObject value = new JSONObject();
                    //获取notification_s数组中的第j个字符串，赋值给value对象
                    value.put("value","");
                    notification.add(value);
                    object.put("notification", notification);
                }else {
                    for (int j = 0; j < notification_a.size(); j++) {
                        JSONObject value = new JSONObject();
                        //获取notification_s数组中的第j个字符串，赋值给value对象
                        value.put("value", notification_a.getString(j));
                        notification.add(value);
                        object.put("notification", notification);
                    }
                }
                //将每次获取的value值放入数组
                if (!"".equals(jsonObject.getString("item_text_name"))) {
                    object.put("item_field_name1", jsonObject.getString("item_text_name"));
                }else object.put("item_field_name1","");
                array.add(object);
                object.clear();
            }
            return array.toString();
        }

    }
}
