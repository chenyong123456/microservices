package cn.knowimage.jsonoutput;

import net.sf.json.JSONObject;

/**
 * 功能:将数据库中字段为treatment_days字符串转换为变量传给前台渲染-->供前台王果果进行数据回填
 * @author 啊勇
 *
 */
public class Treatment_daysOutput {
	
	public static JSONObject Scenario;//Scenario对象中会包含id_对象数组
	public static JSONObject id_;
	public static JSONObject treatment_days_s;//将数据库中treatment_days字段json格式转换为JSONObject对象
	
	public static JSONObject treatment_days(String treatment_days) {
		JSONObject last = new JSONObject();
		//数据库的字段json对象
		treatment_days_s = JSONObject.fromObject(treatment_days);//最终拿的总对象
		Scenario = (JSONObject) treatment_days_s.get("scenario");
		//在这里需要获取scenario对象里的子值
		int num = Scenario.getInt("num");
		last.put("num", num);
		
		for (int i = 0; i < num; i++) {
			id_ = (JSONObject) Scenario.get(String.format("id_%d", i));
			last.put(String.format("id_%d", i)+"_tag_name", id_.get("tag_name"));
			last.put(String.format("id_%d", i)+"_time_unit", id_.get("time_unit"));
			last.put(String.format("id_%d", i)+"_min", id_.get("min"));
			last.put(String.format("id_%d", i)+"_max", id_.get("max"));
		}
		return last;
	}

	public static void main(String[] args) {
		
	}

}
