package cn.knowimage.jsonmake;

import cn.knowimage.util.StringConvertNumber;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 功能:创建key=prep_treatment_common的json对象
 */
public class Prep_Treatment_CommonJson {

	public static JSONObject prep_treatment_common;
	public static JSONObject scenario;
	public static JSONObject id_;
	public static JSONObject duration;
	public static JSONArray obligatory_exam_s_a;//最终存到prep_treatment_common中的数据
	public static JSONArray optional_exam_s_a;
	public static JSONArray notification_s_a;

	public static JSONObject prep_treatment_common(String prep_treatment_common_num,String prep_treatment_common_time_unit,
												   String pre_treatment_min,String pre_treatment_max,
												   String obligatory_exam_num,String optional_exam_num,String notification_num,//id_i中三个数组的分配的长度
												   String obligatory_exam,String optional_exam,String notification){//id_i中每个数组中相应数量的值

		prep_treatment_common = new JSONObject();
		JSONArray prep_treatment_common_time_unit_s = JSONArray.fromObject(prep_treatment_common_time_unit);
		JSONArray pre_treatment_min_s = JSONArray.fromObject(pre_treatment_min);
		JSONArray pre_treatment_max_s = JSONArray.fromObject(pre_treatment_max);
		JSONArray obligatory_exam_num_s = JSONArray.fromObject(obligatory_exam_num);
		JSONArray optional_exam_num_s = JSONArray.fromObject(optional_exam_num);
		JSONArray notification_num_s = JSONArray.fromObject(notification_num);
		JSONArray obligatory_exam_s = JSONArray.fromObject(obligatory_exam);
		JSONArray optional_exam_s = JSONArray.fromObject(optional_exam);
		JSONArray notification_s = JSONArray.fromObject(notification);

		int num;
		if(prep_treatment_common_num==null||"".equals(prep_treatment_common_num)){
			num = 0;
		}else{
			num = Integer.parseInt(prep_treatment_common_num);
		}

		scenario = new JSONObject();
		scenario.put("num", num);
		int obligatory_count = 0;
		int optional_count = 0;
		int notification_count = 0;
		for (int i = 0; i < scenario.getInt("num"); i++) {
			id_ = new JSONObject();
			//这里处理id_0,id_1,id_2...中的相应的value
			duration = new JSONObject();
			duration.put("time_unit","小时");
			if(prep_treatment_common_time_unit_s.getString(i).equals("")){
				duration.put("time_unit", "");
				duration.put("min", 0);
				duration.put("max", 0);
			}else{
				if(pre_treatment_min_s.getString(i)==null||"".equals(pre_treatment_min_s.getString(i))){
					duration.put("min", "");
				}else{
					duration.put("min", StringConvertNumber.stringToFloat(prep_treatment_common_time_unit_s.getString(i),pre_treatment_min_s.getString(i)));
				}
				//duration.put("min", Integer.parseInt(pre_treatment_min_s.getString(i)));

				if(pre_treatment_max_s.getString(i)==null||"".equals(pre_treatment_max_s.getString(i))){
					duration.put("max", "");
				}else{
					duration.put("max", StringConvertNumber.stringToFloat(prep_treatment_common_time_unit_s.getString(i),pre_treatment_max_s.getString(i)));
				}
			}


			//duration.put("max", Integer.parseInt(pre_treatment_max_s.getString(i)));
			id_.put("duration", duration);

			obligatory_exam_s_a = new JSONArray();//obligatory_exam存字段数组的数据
			if(obligatory_exam_num_s.getString(i)==null||"".equals(obligatory_exam_num_s.getString(i))){

			}else{
				for (int j = 0; j < Integer.parseInt(obligatory_exam_num_s.getString(i)); j++) {
					obligatory_exam_s_a.add(obligatory_exam_s.getString(obligatory_count));
					obligatory_count++;
				}
			}
			id_.put("obligatory_exam", obligatory_exam_s_a);

			optional_exam_s_a = new JSONArray();//optional_exam存字段数组的数据
			if(optional_exam_num_s.getString(i)==null||"".equals(optional_exam_num_s.getString(i))){

			}else{
				for (int j = 0; j < Integer.parseInt(optional_exam_num_s.getString(i)); j++) {
					optional_exam_s_a.add(optional_exam_s.getString(optional_count));
					optional_count++;
				}
			}
			id_.put("optional_exam", optional_exam_s_a);


			notification_s_a = new JSONArray();//notification存字段数组的数据

			if(notification_num_s.getString(i)==null||"".equals(notification_num_s.getString(i))){

			}else{
				for (int j = 0; j < Integer.parseInt(notification_num_s.getString(i)); j++) {
					notification_s_a.add(notification_s.getString(notification_count));
					notification_count++;
				}
			}
			id_.put("notification", notification_s_a);

			scenario.put(String.format("id_%d", i), id_);
		}
		prep_treatment_common.put("scenario", scenario);

		System.out.println(prep_treatment_common.toString());
		return prep_treatment_common;
	}

}