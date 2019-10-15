package cn.knowimage.jsonoutput;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 功能:将数据库中字段为treatment_choice字符串转换为变量传给前台渲染
 * @author 啊勇
 *
 */

public class Treatment_choiceOutput {
	
	public static JSONObject Scenario;//Scenario对象中会包含id_对象数组
	public static JSONArray id_;
	
	//王果果的数据将数据库中的String类型的json格式的数据
	public static JSONObject treatment_choiceOut(String treatment_choice){//这个是从数据库中查询出来String类型json格式的字符串
		
		JSONObject last = new JSONObject();
		//数据库的字段json对象
		JSONObject treatment_choice_s = JSONObject.fromObject(treatment_choice);//最终拿的总对象
		String treatment_choice_ref = treatment_choice_s.getString("ref");//对应的treatment_choice对象中的ref字段
		Scenario = (JSONObject) treatment_choice_s.get("scenario");
		last.put("ref", treatment_choice_ref);
		//在这里需要获取scenario对象里的子值
		int num = Scenario.getInt("num");
		last.put("num", num);
		
		for (int i = 0; i < num; i++) {
			id_ = (JSONArray) Scenario.get(String.format("id_%d", i));
			last.put(String.format("id_%d", i)+"_num", id_.size());
			last.put(String.format("id_%d", i), id_);
		}
		return last;
	}
	
	//小栗子的接口只返回相应的json格式的对象
	public static JSONObject treatment_choiceOutxiao(String treatment_choice){//这个是从数据库中查询出来String类型json格式的字符串


		JSONObject treatment_choice_s = JSONObject.fromObject(treatment_choice);//最终拿的总对象
		//返回
		return treatment_choice_s;
	}
	
	public static void main(String[] args) {
		
	}

}
