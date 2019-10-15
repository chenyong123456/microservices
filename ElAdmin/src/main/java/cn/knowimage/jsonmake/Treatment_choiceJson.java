package cn.knowimage.jsonmake;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 分类json,
 * 功能:主要创建key=treatment_choice 的value(为一个JSONObject)
 */
public class Treatment_choiceJson {

	//返回key=treatment_choice 的json值
	public static JSONObject treatment_choice(String treatment_choice_scenario,String treatment_choice_ref,
											  String treatment_choice_scenario_num,String treatment_choice_scenario_num_id){//参数主要为value为JSONObject中的值

		JSONObject treatment_choice = new JSONObject();
		treatment_choice.put("ref", treatment_choice_ref);

		//treatment_choice_scenario_num将每一个id_i的数组大小转换为数组["3","2"]
		JSONArray treatment_choice_scenario_num_s = JSONArray.fromObject(treatment_choice_scenario_num);
		JSONArray treatment_choice_scenario_num_id_s = JSONArray.fromObject(treatment_choice_scenario_num_id);

		int num;//将前台传过来的的String类型是转换为int类型的
		if(treatment_choice_scenario==null||"".equals(treatment_choice_scenario)){
			num = 0;
		}else{
			num = Integer.parseInt(treatment_choice_scenario);
		}

		//scenario场景的定义为treatment_choice的嵌套子对象.
		JSONObject scenario = new JSONObject();
		scenario.put("num", num);//根据num动态创建id_0数据,在这里目前是手动输入该值.
		int count = 0;//计数treatment_choice_scenario_num_id数组中的字段的位置
		for (int i = 0; i < scenario.getInt("num"); i++) {
			//id_0,id_1,id_2......
			JSONArray id_ = new JSONArray();//num为几就创建几个JSONArray数组.
			if(treatment_choice_scenario_num_s.get(i)==null||"".equals(treatment_choice_scenario_num_s.get(i))){

			}else{
				for (int j = 0; j < Integer.parseInt((String)treatment_choice_scenario_num_s.get(i)); j++) {
					id_.add(treatment_choice_scenario_num_id_s.getString(count));
					count++;
				}
			}

			//String.format("id_%d", i)->将相应的变量转化为字符串  就是id_0,id_1,id_2....这种格式.
			scenario.put(String.format("id_%d", i), id_);
			System.out.println(String.format("id_%d", i));
		}
//		Date date = new Date();
//		System.out.println(String.format("%te", date));
		//key=treatment_choice的对象.

		treatment_choice.put("scenario", scenario) ;//key=scenario为一个JSONObject对象  

		System.out.println(treatment_choice.toString());
		return treatment_choice;
	}

}