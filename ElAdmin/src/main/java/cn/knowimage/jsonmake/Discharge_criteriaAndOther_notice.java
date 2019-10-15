package cn.knowimage.jsonmake;

import net.sf.json.JSONArray;

/**
 * 功能:创建key=discharge_criteria和key=other_notice的JSONObject
 */
public class Discharge_criteriaAndOther_notice {

	public static JSONArray discharge_criteria;
	public static JSONArray other_notice;

	public static JSONArray discharge_criteria(String discharge_criteria_item,String discharge_criteria_s){//在这里会传入该JSONArray所需要的参数
		//这里只是手动添加的静态数据,在实际的开发中会有动态的变量数据传过来
		JSONArray discharge_criteria_item_s = JSONArray.fromObject(discharge_criteria_item);
		discharge_criteria = new JSONArray();

		int discharge_criteria_s_num = 0;
		if(discharge_criteria_s==null||"".equals(discharge_criteria_s)){
			discharge_criteria_s_num = 0;
		}else{
			discharge_criteria_s_num = Integer.parseInt(discharge_criteria_s);
		}

		for (int i = 0; i < discharge_criteria_s_num; i++) {
			discharge_criteria.add(discharge_criteria_item_s.getString(i));
		}
		System.out.println(discharge_criteria.toString());
		return discharge_criteria;
	}

	public static JSONArray other_notice(String other_notice_item,String other_notice_s){//在这里会传入该JSONArray所需要的参数
		JSONArray other_notice_item_s = JSONArray.fromObject(other_notice_item);
		other_notice = new JSONArray();

		int other_notice_s_num = 0;
		if(other_notice_s==null||"".equals(other_notice_s)){
			other_notice_s_num = 0;
		}else{
			other_notice_s_num = Integer.parseInt(other_notice_s);
		}

		for (int i = 0; i < other_notice_s_num; i++) {
			other_notice.add(other_notice_item_s.getString(i));
		}
		System.out.println(other_notice.toString());
		return other_notice;
	}

}
