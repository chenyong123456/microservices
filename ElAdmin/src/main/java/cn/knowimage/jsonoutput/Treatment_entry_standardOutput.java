package cn.knowimage.jsonoutput;

import net.sf.json.JSONObject;

/**
 * 功能:将数据库中字段为treatment_entry_standard字符串转换为变量传给前台渲染
 * @author 啊勇
 *
 */
public class Treatment_entry_standardOutput {
	
	public static JSONObject treatment_entry_standard_s;
	public static JSONObject last;
	
	public static JSONObject treatment_entry_standardOutput(String treatment_entry_standard){//这个是从数据库中查询出来String类型json格式的字符串
		
		last = new JSONObject();
		treatment_entry_standard_s = JSONObject.fromObject(treatment_entry_standard);
		
		int treatment_entry_standard_num = treatment_entry_standard_s.getInt("num");
		last.put("treatment_entry_standard_num",treatment_entry_standard_num);
		
		for (int i = 0; i < treatment_entry_standard_num; i++) {
			String id_ = treatment_entry_standard_s.getString(String.format("id_%d", i));
			last.put(String.format("id_%d", i), id_);
		}
		return last;
	}
	
	public static void main(String[] args) {

	}

}
