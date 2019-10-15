package cn.knowimage.insertjsonsecond;

import cn.knowimage.util.StringConvertNumber;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Mr.G
 * @date 2019/8/12
 * 将前端输入的after_medical_treatment字段格式修改成数据库规定的json格式
 */

public class NewAfterMedicalTreatment {
    public static JSONObject after_medical_treatment(String afterDuration, String afterDuration_treatment_min,
                                                     String afterDuration_treatment_max, String afterDuration_treatment_text,
                                                     String after_medicalScenario) {
        //定义总对象
        JSONObject After_medical_treatment = new JSONObject();
        JSONObject duration = new JSONObject();
        JSONObject scenario = new JSONObject();
        //将afterDuration_treatment_text放入名为time_text的对象
        duration.put("time_text", afterDuration_treatment_text);
        //如果treatment_days_duration为空，则无时间定义
        if ("".equals(afterDuration)) {
            duration.put("time_unit", "");
            duration.put("min", 0);
            duration.put("max", 0);
            //treatment_days_duration不为空

            //treatment_days_duration="其他时间描述",max=空,min=空
        } else if ("其它时间描述".equals(afterDuration)) {
            duration.put("time_unit", "其它时间描述");
            duration.put("min", 0);
            duration.put("max", 0);
        } else {
            duration.put("time_unit", "时");
            //如果未填min和max，表示min和max默认为0，否则进行时间转换
            //stringToFloat(时间类型,时间数字)，默认全部转换为小时制，下同
            if (afterDuration_treatment_min == null || "".equals(afterDuration_treatment_min)) {
                duration.put("min", 0);
            } else {
                duration.put("min", StringConvertNumber.stringToFloat(afterDuration, afterDuration_treatment_min));
            }
            if (afterDuration_treatment_max == null || "".equals(afterDuration_treatment_max)) {
                duration.put("max", 0);
            } else {
                duration.put("max", StringConvertNumber.stringToFloat(afterDuration, afterDuration_treatment_max));
            }
        }
        After_medical_treatment.put("duration", duration);
        //将after_medicalScenario字符串放入json数组
        JSONArray after_medicalScenario_s = JSONArray.fromObject(after_medicalScenario);

        //如果after_medicalScenario为空，则创建数据库标准格式数据，内含的所有值为空
        if (after_medicalScenario_s==null||after_medicalScenario_s.size()==0){
            JSONObject id_0 = new JSONObject();
            JSONArray obligatory_exam = new JSONArray();
            JSONArray optional_exam = new JSONArray();
            JSONArray recovery_plan = new JSONArray();
            obligatory_exam.add("");
            optional_exam.add("");
            recovery_plan.add("");
            id_0.put("obligatory_exam",obligatory_exam);
            id_0.put("optional_exam",optional_exam);
            id_0.put("recovery_plan",recovery_plan);
            scenario.put("num",1);
            scenario.put("id_0",id_0);
        }else {
            JSONObject id_ = new JSONObject();
            int num = after_medicalScenario_s.size();
            for (int i = 0; i < num; i++) {
                //依次获取after_medicalScenario_s数组中的第i个对象
                JSONObject jsonObject = after_medicalScenario_s.getJSONObject(i);
                //定义obligatory_exam_s数组获取第i个对象里的数组，通过键值对形式获取
                JSONArray obligatory_exam_s = jsonObject.getJSONArray("obligatory_exam");
                JSONArray optional_exam_s = jsonObject.getJSONArray("optional_exam");
                JSONArray recovery_plan_s = jsonObject.getJSONArray("recovery_plan");

                //注意字符串判断是否相等需使用equals，前期bug原因：使用==判断，导致没有生效
//            if ("".equals(obligatory_exam_s.getJSONObject(0).getString("value")) && "".equals(optional_exam_s.getJSONObject(0).getString("value")) && "".equals(recovery_plan_s.getJSONObject(0).getString("value"))) {
//                //如果obligatory_exam， optional_exam， recovery_plan均为空，则创建scecario:{num:0}
//                scenario.put("num", 0);
//            } else {
                scenario.put("num", num);

                //定义一个数组，用来存放获取到的数组
                //创建obligatory_exam字段
                JSONArray obligatory_exam = new JSONArray();
                //定义num获取数组长度，为之后的循环做条件判断
                int obligatory_exam_num = obligatory_exam_s.size();
                for (int j = 0; j < obligatory_exam_num; j++) {
                    //获取obligatory_exam_s数组下第i个json对象的value值，通过键值对形式获取字符串值
                    obligatory_exam.add(obligatory_exam_s.getJSONObject(j).getString("value"));
                }
                id_.put("obligatory_exam", obligatory_exam);

                //创建optional_exam字段
                JSONArray optional_exam = new JSONArray();
                int optional_exam_num = optional_exam_s.size();
                for (int j = 0; j < optional_exam_num; j++) {
                    //获取optional_exam_s数组下第i个json对象的value值，通过键值对形式获取字符串值
                    optional_exam.add(optional_exam_s.getJSONObject(j).getString("value"));
                }
                id_.put("optional_exam", optional_exam);

                //创建recovery_plan
                JSONArray recovery_plan = new JSONArray();
                int recovery_plan_num = recovery_plan_s.size();
                for (int j = 0; j < recovery_plan_num; j++) {
                    //获取recovery_plan_s数组下第i个json对象的value值，通过键值对形式获取字符串值
                    recovery_plan.add(recovery_plan_s.getJSONObject(j).getString("value"));
                }
                id_.put("recovery_plan", recovery_plan);
                //动态生成id_x对象存放数据
                scenario.put(String.format("id_%d", i), id_);

            }
        }



        After_medical_treatment.put("scenario", scenario);

        return After_medical_treatment;
    }
}
