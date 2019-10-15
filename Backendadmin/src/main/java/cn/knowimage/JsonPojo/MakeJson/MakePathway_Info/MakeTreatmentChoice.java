package cn.knowimage.JsonPojo.MakeJson.MakePathway_Info;

import cn.knowimage.pojo.ReceivePathway;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author 彭雷
 * make数据库字段Treatment_Choice
 * 需要使用前端字段treatment_choice_ref和treatment_choice_scenario
 */
public class MakeTreatmentChoice {
    public static String make(ReceivePathway receivePathway){
        JSONArray id_;
        JSONObject scenario;
        String treatment_choice_ref=receivePathway.getTreatment_choice_ref();
        String treatment_choice_scenario=receivePathway.getTreatment_choice_scenario();
        JSONObject treatment_choice = new JSONObject();
        treatment_choice.put("ref", treatment_choice_ref);
        scenario = new JSONObject();
        //定义treatment_choice_scenario_s数组，存放字符串treatment_choice_scenario
        JSONArray treatment_choice_scenario_s = JSONArray.fromObject(treatment_choice_scenario);

        //如果treatment_choice_scenario_s为空，表示前端传来的数据为空数组，但数据库仍需传入标准格式，值为空
        if ("".equals(treatment_choice_scenario_s.getJSONObject(0).getJSONArray("treatmentChoiceId").getJSONObject(0).getString("value"))){
            scenario.put("num",0);
        }else{
            int num = treatment_choice_scenario_s.size();
            scenario.put("num", num);
            //遍历treatment_choice_scenario_s
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
        return treatment_choice.toString();
    }

}
