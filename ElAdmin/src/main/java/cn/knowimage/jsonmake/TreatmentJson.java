package cn.knowimage.jsonmake;

import cn.knowimage.util.StringConvertNumber;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 功能:创建key=treatment的json对象
 */
public class TreatmentJson {

	public static JSONObject treatment;
	public static JSONObject duration;
	public static JSONObject scenario;
	public static JSONObject scenario_id_;
	public static JSONObject treatment_plan;
	public static JSONObject id_;
	public static JSONArray content_item;
	public static JSONArray obligatory_exam;
	public static JSONArray optional_exam;
	public static JSONArray notification;

	public static JSONObject treatment(String treatment_days_duration,String duration_treatment_min,String duration_treatment_max,
									   String treatment_scenario,String treatment_plan_ref,String treatment_plan_num,String treatment_plan_num_id_content,
									   String treatment_plan_num_id_content_item_num,String treatment_scenario_content_item,String treatment_scenario_obligatory_exam_num,
									   String treatment_scenario_optional_exam_num,String treatment_scenario_notification_num,String treatment_scenario_obligatory_exam,
									   String treatment_scenario_optional_exam,String treatment_scenario_notification){

		treatment = new JSONObject();
		//拼接treatment字段中duration对象


		duration = new JSONObject();
		duration.put("time_unit", "小时");
		if("".equals(treatment_days_duration)){
			duration.put("time_unit", "");
			duration.put("min",0);
			duration.put("max",0);
		}else{
			if(duration_treatment_min==null||"".equals(duration_treatment_min)){
				duration.put("min",0);
			}else{
				duration.put("min", StringConvertNumber.stringToFloat(treatment_days_duration,duration_treatment_min));
			}

			if(duration_treatment_max==null||"".equals(duration_treatment_max)){
				duration.put("max",0);
			}else{
				duration.put("max", StringConvertNumber.stringToFloat(treatment_days_duration,duration_treatment_max));
			}
		}

		treatment.put("duration", duration);

		JSONArray treatment_plan_ref_s = JSONArray.fromObject(treatment_plan_ref);
		JSONArray treatment_plan_num_s = JSONArray.fromObject(treatment_plan_num);
		JSONArray treatment_plan_num_id_content_s = JSONArray.fromObject(treatment_plan_num_id_content);

		JSONArray treatment_plan_num_id_content_item_num_s = JSONArray.fromObject(treatment_plan_num_id_content_item_num);
		JSONArray treatment_scenario_content_item_s = JSONArray.fromObject(treatment_scenario_content_item);
		JSONArray treatment_scenario_obligatory_exam_num_s = JSONArray.fromObject(treatment_scenario_obligatory_exam_num);

		JSONArray treatment_scenario_optional_exam_num_s = JSONArray.fromObject(treatment_scenario_optional_exam_num);
		JSONArray treatment_scenario_notification_num_s = JSONArray.fromObject(treatment_scenario_notification_num);
		JSONArray treatment_scenario_obligatory_exam_s = JSONArray.fromObject(treatment_scenario_obligatory_exam);

		JSONArray treatment_scenario_optional_exam_s = JSONArray.fromObject(treatment_scenario_optional_exam);
		JSONArray treatment_scenario_notification_s = JSONArray.fromObject(treatment_scenario_notification);

		int content_count = 0; //content_count指向content数组中相应的内容的值的索引
		int content_item_count = 0;//指向content_item该分配的长度的数组
		int content_item_conten_count =0;//指向content_item该内容的数组
		int obligatory_exam_count = 0;//指向obligatory_exam该内容的数组
		int optional_exam_count = 0;//指向optional_exam该内容的数组
		int notification_count = 0;//指向notification该内容的数组
		//一下是拼接treatment字段中scenario对象  该对象是里面有动态创建相应的对象

		int scenario_num;//scenario_num动态创建scenario对象中scenario_id_0字段
		if(treatment_scenario==null||"".equals(treatment_scenario)){
			scenario_num = 0;
		}else{
			scenario_num = Integer.parseInt(treatment_scenario);
		}

		scenario = new JSONObject();
		scenario.put("scenario_num", scenario_num);
		for (int i = 0; i < scenario.getInt("scenario_num"); i++) {//动态创建scenario_id_0对象for循环中创建scenario_id_0对象中的内容
			//每循环一次创建scenario_id_0,scenario_id_1.....这样的对象
			scenario_id_ = new JSONObject();
			//而在scenario_id_0中有treatment_plan这样的对象
			treatment_plan = new JSONObject();
			//在这里创建treatment_plan对象
			treatment_plan.put("ref", treatment_plan_ref_s.getString(i));
			if(treatment_plan_num_s.getString(i)==null||"".equals(treatment_plan_num_s.getString(i))){
				treatment_plan.put("num",0);
			}else{
				treatment_plan.put("num", Integer.parseInt(treatment_plan_num_s.getString(i)));
			}

			for (int j = 0; j < treatment_plan.getInt("num"); j++) {
				id_ = new JSONObject();
				//id_0,,id_1,id_2......里面的JSONArray
				content_item = new JSONArray();

				if(treatment_plan_num_id_content_item_num_s.getString(content_item_count)==null||"".equals(treatment_plan_num_id_content_item_num_s.getString(content_item_count))){
					content_item_count++;
				}else{
					for (int j2 = 0; j2 < Integer.parseInt(treatment_plan_num_id_content_item_num_s.getString(content_item_count)); j2++) {
						content_item.add(treatment_scenario_content_item_s.getString(content_item_conten_count));
						content_item_conten_count++;
					}
					content_item_count++;
				}

				id_.put("content", treatment_plan_num_id_content_s.getString(content_count));
				content_count++;
				id_.put("content_item", content_item);
				treatment_plan.put(String.format("id_%d", j), id_);
			}
			//该句代码只是用来覆盖掉id_1对象中的content的值,只是一个测试代码,在前台数据传入进来了可以不需要
			//treatment_plan.getJSONObject("id_1").put("content", "术前用药:阿托品等");
			//将创建好了的treatment_plan存入到scenario_id_x对象中s
			scenario_id_.put("treatment_plan", treatment_plan);
			//以上为treatment_plan字段的创建


			obligatory_exam = new JSONArray();

			if(treatment_scenario_obligatory_exam_num_s.getString(i)==null||"".equals(treatment_scenario_obligatory_exam_num_s.getString(i))){

			}else{
				for (int j = 0; j < Integer.parseInt(treatment_scenario_obligatory_exam_num_s.getString(i)); j++) {
					obligatory_exam.add(treatment_scenario_obligatory_exam_s.getString(obligatory_exam_count));
					obligatory_exam_count++;
				}
			}
			scenario_id_.put("obligatory_exam", obligatory_exam);

			optional_exam = new JSONArray();

			if(treatment_scenario_optional_exam_num_s.getString(i)==null||"".equals(treatment_scenario_optional_exam_num_s.getString(i))){

			}else{
				for (int j = 0; j < Integer.parseInt(treatment_scenario_optional_exam_num_s.getString(i)); j++) {
					optional_exam.add(treatment_scenario_optional_exam_s.getString(optional_exam_count));
					optional_exam_count++;
				}
			}
			scenario_id_.put("optional_exam", optional_exam);

			notification = new JSONArray();
			if(treatment_scenario_notification_num_s.getString(i)==null||"".equals(treatment_scenario_notification_num_s.getString(i))){

			}else{
				for (int j = 0; j < Integer.parseInt(treatment_scenario_notification_num_s.getString(i)); j++) {
					notification.add(treatment_scenario_notification_s.getString(notification_count));
					notification_count++;
				}
			}
			scenario_id_.put("notification", notification);
			//每循环一次完就存到scenario对象中.
			scenario.put(String.format("scenario_id_%d", i), scenario_id_);
		}

		//当循环结束时及scenario对象创建成功 将其加到treatment对象中
		treatment.put("scenario", scenario);
		System.out.println(treatment.toString());
		return treatment;
	}

}