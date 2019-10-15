package cn.knowimage.jsonmake;

import cn.knowimage.util.StringConvertNumber;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 功能:创建key=treatment_days的JSONObject对象
 * @author 啊勇
 *
 */
public class Treatment_DaysJson {

	public static JSONObject treatment_days;
	public static JSONObject scenario;
	public static JSONObject id_;

	public static JSONObject treatment_days(String treatment_days_scenario_num,String treatment_days_tag_name,String treatment_min,
											String treatment_max,String treatment_days_time_unit){

		JSONArray treatment_days_tag_name_s = JSONArray.fromObject(treatment_days_tag_name);
		JSONArray treatment_min_s = JSONArray.fromObject(treatment_min);
		JSONArray treatment_max_s = JSONArray.fromObject(treatment_max);
		JSONArray treatment_days_time_unit_s = JSONArray.fromObject(treatment_days_time_unit);

		int num;
		if(treatment_days_scenario_num==null||"".equals(treatment_days_scenario_num)){
			num = 0;
		}else{
			num = Integer.parseInt(treatment_days_scenario_num);
		}

		scenario = new JSONObject();
		//根据num动态生成id_i对象
		scenario.put("num", num);
		for (int i = 0; i < scenario.getInt("num"); i++) {
			id_ = new JSONObject();

			id_.put("tag_name", treatment_days_tag_name_s.getString(i));
			id_.put("time_unit","小时");

			if("".equals(treatment_days_time_unit_s.getString(i))){//time_unit="",max=0,min=0
				id_.put("time_unit","");
				id_.put("min", 0);
				id_.put("max", 0);
			}else{//time_unit!=""
				if(treatment_min_s.getString(i)==null||"".equals(treatment_min_s.getString(i))){
					id_.put("min", 0);
				}else{
					id_.put("min", StringConvertNumber.stringToFloat(treatment_days_time_unit_s.getString(i), treatment_min_s.getString(i)));
				}

				if(treatment_max_s.getString(i)==null||"".equals(treatment_max_s.getString(i))){
					id_.put("max", 0);
				}else{
					id_.put("max", StringConvertNumber.stringToFloat(treatment_days_time_unit_s.getString(i), treatment_max_s.getString(i)));
				}
			}
			scenario.put(String.format("id_%d", i), id_);
			System.out.println(String.format("id_%d", i));
		}
		treatment_days = new JSONObject();
		treatment_days.put("scenario", scenario);

		System.out.println(treatment_days.toString());
		return treatment_days;

	}

}
