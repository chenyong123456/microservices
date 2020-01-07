package cn.knowimage.JsonPojo.ReturnJson.ReturnPathway_Info;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Mr.G
 * @date 2019/8/12
 * 数据回显功能实现方法
 * 将数据库中after_medical_treatment字段格式转换为前台能够识别的数据格式
 * 其中下划线变量对应数据库原字段，无需更改
 */
public class AfterMedicalTreatmentReturn {
    public static String after_medical_treatment(String after_medical_treatment) {
        //将获取的字符串放入该对象,该对象为总对象
        JSONObject jsonObject = JSONObject.fromObject(after_medical_treatment);
        //定义总对象存放输出的全部数据
        JSONObject object = new JSONObject();
        //获取jsonObject对象下的子对象duration
        JSONObject duration = jsonObject.getJSONObject("duration");
        //直接通过键值对形式获取字符串值，放入对应名字的对象中
        object.put("afterDuration", duration.getString("time_unit"));
        object.put("afterDuration_treatment_text", duration.getString("time_text"));
        object.put("afterDuration_treatment_min", duration.getString("min"));
        object.put("afterDuration_treatment_max", duration.getString("max"));
        //定义该数组，获取obligatory_exam，optional_exam，recovery_plan值
        JSONArray after_medicalScenario = new JSONArray();
        JSONObject after = new JSONObject();
        //获取总对象下的子对象scenario
        JSONObject scenario = jsonObject.getJSONObject("scenario");

        //如果after_medical_treatment对象中的scenario对象为空，则需要返回前端可接受的格式，且值为空
        if ("0".equals(scenario.getString("num"))){
            //定义三个数组，接受空值
            JSONObject value = new JSONObject();
            value.put("value","");
            JSONArray obligatory_exam = new JSONArray();
            JSONArray optional_exam = new JSONArray();
            JSONArray recovery_plan = new JSONArray();
            //obligatory_exam.add(value);
            //optional_exam.add(value);
            //recovery_plan.add(value);
            //将三个空数组放入对象after
            after.put("ulShow5", false);
            after.put("openShhouBixu", false);
            after.put("obligatory_exam",obligatory_exam);
            after.put("openShhouKexuan", false);
            after.put("optional_exam",optional_exam);
            after.put("openShhouHuifu", false);
            after.put("recovery_plan",recovery_plan);
            //将after对象放入after_medicalScenario数组
            after_medicalScenario.add(after);
        }else {
            after.put("ulShow5", false);
            //获取scenario对象下的num值进行循环
            if (scenario.getInt("num")==0){

            }
            for (int i = 0; i < scenario.getInt("num"); i++) {
                //该对象依次获取id_X对象的值
                JSONObject id_ = scenario.getJSONObject(String.format("id_%d", i));
                //定义该数组，获取obligatory_exam所有值
                JSONArray obligatory_exam = new JSONArray();
                JSONArray obligatory_exam_s = id_.getJSONArray("obligatory_exam");
                //如果数据库字段为空
                if(obligatory_exam_s.size()==0){
                    JSONObject value = new JSONObject();
                    value.put("value", "");
                    //obligatory_exam.add(value);
                    after.put("openShhouBixu", false);
                    after.put("obligatory_exam", obligatory_exam);
                }
                else {
                    //根据数组obligatory_exam_s长度进行循环
                    for (int j = 0; j < obligatory_exam_s.size(); j++) {
                        JSONObject value = new JSONObject();
                        //将数组obligatory_exam_s中的值依次放入value中
                        value.put("value", obligatory_exam_s.getString(j));
                        obligatory_exam.add(value);
                        after.put("openShhouBixu", true);
                        after.put("obligatory_exam", obligatory_exam);
                    }
                }
                //定义该数组，获取optional_exam所有值
                JSONArray optional_exam = new JSONArray();
                JSONArray optional_exam_s = id_.getJSONArray("optional_exam");
                //如果数据库字段为空
                if(optional_exam_s.size()==0){
                    JSONObject value = new JSONObject();
                    value.put("value", "");
                    //optional_exam.add(value);
                    after.put("openShhouKexuan", false);
                    after.put("optional_exam", optional_exam);
                }
                else {
                    //根据数组optional_exam_s长度进行循环
                    for (int j = 0; j < optional_exam_s.size(); j++) {
                        JSONObject value = new JSONObject();
                        //将数组obligatory_exam_s中的值依次放入value中
                        value.put("value", optional_exam_s.getString(j));
                        optional_exam.add(value);
                        after.put("openShhouKexuan", true);
                        after.put("optional_exam", optional_exam);

                    }
                }
                //定义该数组，获取recovery_plan所有值
                JSONArray recovery_plan = new JSONArray();
                JSONArray recovery_plan_s = id_.getJSONArray("recovery_plan");
                //如果数据库字段为空
                if(recovery_plan_s.size()==0){
                    JSONObject value = new JSONObject();
                    value.put("value", "");
                    //recovery_plan.add(value);
                    after.put("openShhouHuifu", false);
                    after.put("recovery_plan", recovery_plan);
                }
                //根据数组recovery_plan_s长度进行循环
                for (int j = 0; j < recovery_plan_s.size(); j++) {
                    JSONObject value = new JSONObject();
                    //将数组recovery_plan_s中的值依次放入value中
                    value.put("value", recovery_plan_s.getString(j));
                    recovery_plan.add(value);
                    after.put("openShhouHuifu", true);
                    after.put("recovery_plan", recovery_plan);
                }
                after_medicalScenario.add(after);
            }
        }
        object.put("after_medicalScenario", after_medicalScenario);
        return object.toString();
    }
}
