package cn.knowimage.insertjsonsecond;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Mr.G
 * @date 2019/8/12
 * 将前端输入的Treatment_choice字段格式修改成数据库规定的json格式
 */
public class NewTreatmentChoice {
    private static JSONArray id_;
    private static JSONObject scenario;

    public static JSONObject newTreatment_choice(String treatment_choice_ref, String treatment_choice_scenario) {
        JSONObject treatment_choice = new JSONObject();
        treatment_choice.put("ref", treatment_choice_ref);
        scenario = new JSONObject();
        //定义treatment_choice_scenario_s数组，存放字符串treatment_choice_scenario
        JSONArray treatment_choice_scenario_s = JSONArray.fromObject(treatment_choice_scenario);

        //如果treatment_choice_scenario_s为空，表示前端传来的数据为空数组，但数据库仍需传入标准格式，值为空
        if (treatment_choice_scenario_s==null||treatment_choice_scenario_s.size()==0){
            JSONArray id_0 = new JSONArray();
            id_0.add("");
            scenario.put("num",1);
            scenario.put("id_0",id_0);
        }else{
            int num = treatment_choice_scenario_s.size();
            scenario.put("num", num);
            for (int i = 0; i < num; i++) {
                //定义jsonObject对象，依次获取数组中的对象
                JSONObject jsonObject = treatment_choice_scenario_s.getJSONObject(i);
                //定义数组，通过键值对形式获取数组对象
                JSONArray treatmentChoiceId = jsonObject.getJSONArray("treatmentChoiceId");
                id_ = new JSONArray();
                //通过上一步获取的数组大小循环获得value值
                for (int j = 0; j < treatmentChoiceId.size(); j++) {
                    id_.add(treatmentChoiceId.getJSONObject(j).getString("value"));
                }
                scenario.put(String.format("id_%d", i), id_);
            }
        }
        treatment_choice.put("scenario", scenario);
        return treatment_choice;
    }
}
