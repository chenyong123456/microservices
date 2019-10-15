package cn.knowimage.jsonoutput;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Mr.G
 * @date 2019/8/12
 * 数据回显功能实现方法
 * 将数据库中treatment_choice字段格式转换为前台能够识别的数据格式
 * 其中下划线变量对应数据库原字段，无需更改
 */
public class TreatmentChoiceReturn {
    public static String treatment_choice_return(String treatment_choice) {
        //定义总对象，存放所有输出的对象
        JSONObject object = new JSONObject();
        //定义一个数组，用来存放转换后的数据
        JSONArray treatment_choice_scenario = new JSONArray();
        //定义一个总对象，获取输入的字符串
        JSONObject treatment_choice_s = JSONObject.fromObject(treatment_choice);
        //定义一个对象，获取总对象里的一个子对象
        JSONObject scenario = treatment_choice_s.getJSONObject("scenario");
        object.put("treatment_choice_ref", treatment_choice_s.getString("ref"));
        //根据num循环，由于输入为string类，需要转换为int类型
        for (int i = 0; i < scenario.getInt("num"); i++) {
            //定义一个对象，用来存放转换后的对象值,在循环体里定义，每次分配新空间，防止同名的数组被覆盖
            JSONObject treatment_choice_scenario_m = new JSONObject();
            //定义数组，动态获取id_0数组
            JSONArray jsonArray = scenario.getJSONArray(String.format("id_%d", i));
            JSONArray treatmentChoiceId = new JSONArray();
            //根据获取的id_0数组长度循环
            for (int j = 0; j < jsonArray.size(); j++) {
                JSONObject value = new JSONObject();
                //将数组jsonArray中的第j个字符串放入对象value中
                value.put("value", jsonArray.getString(j));
                treatmentChoiceId.add(value);
            }
            //将数组放到一个对象中
            treatment_choice_scenario_m.put("treatmentChoiceId", treatmentChoiceId);
            treatment_choice_scenario.add(treatment_choice_scenario_m);
        }
        object.put("treatment_choice_scenario", treatment_choice_scenario);
        return object.toString();

    }
}
