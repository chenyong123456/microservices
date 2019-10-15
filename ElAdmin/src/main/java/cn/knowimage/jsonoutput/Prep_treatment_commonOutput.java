package cn.knowimage.jsonoutput;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 功能:对prep_treatment_common字段String类型json格式的字符串进行转换
 * @author 啊勇
 *
 */
public class Prep_treatment_commonOutput {

	public static JSONObject scenario;
	public static JSONObject duration;
	public static JSONObject id_;
	public static JSONArray obligatory_exam;
	public static JSONArray optional_exam;
	public static JSONArray notification;
	//王果果的数据将数据库中的String类型的json格式的数据
	public static JSONObject prep_treatment_commonOut(String prep_treatment_common) {
		
		JSONObject last = new JSONObject();
		JSONObject prep_treatment_common_s = JSONObject.fromObject(prep_treatment_common);//将数据库中的prep_treatment_common字段的json格式的数据在转换为json对象
		//prep_treatment_common中的子对象
		scenario = prep_treatment_common_s.getJSONObject("scenario");
		int prep_treatment_common_num = scenario.getInt("num");
		last.put("num", prep_treatment_common_num);
		for (int i = 0; i < prep_treatment_common_num; i++) {
			id_ = (JSONObject) scenario.get(String.format("id_%d", i));
			//id_中的元素对象duration字段
			duration = (JSONObject) id_.get("duration");
			last.put(String.format("id_%d", i)+"_time_unit", id_.get("time_unit"));
			last.put(String.format("id_%d", i)+"_min", id_.get("min"));
			last.put(String.format("id_%d", i)+"_max", id_.get("max"));
			
			obligatory_exam = id_.getJSONArray("obligatory_exam");
			last.put(String.format("id_%d"+"_obligatory_exam", i), obligatory_exam);
			
			optional_exam = id_.getJSONArray("optional_exam");
			last.put(String.format("id_%d"+"_optional_exam", i), optional_exam);
			
			notification = id_.getJSONArray("notification");
			last.put(String.format("id_%d"+"_notification", i), notification);
		}
		return last;
	}
	//小栗子的接口只返回相应的json格式的对象
	public static JSONObject prep_treatment_commonOutXiao(String prep_treatment_common) {
		
		JSONObject prep_treatment_common_s = JSONObject.fromObject(prep_treatment_common);//将数据库中的prep_treatment_common字段的json格式的数据在转换为json对象
		//prep_treatment_common中的子对象
//		scenario = prep_treatment_common_s.getJSONObject("scenario");
//		int prep_treatment_common_num = scenario.getInt("num");

//		for (int i = 0; i < prep_treatment_common_num; i++) {
//			
//		}
		return prep_treatment_common_s;
	}
	
	public static void main(String[] args) {
		
	}
}
