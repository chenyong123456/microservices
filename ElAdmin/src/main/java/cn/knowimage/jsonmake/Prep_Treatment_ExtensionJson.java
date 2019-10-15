package cn.knowimage.jsonmake;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 功能:创建key=prep_treatment_extension的json对象
 */
public class Prep_Treatment_ExtensionJson {

	public static JSONObject prep_treatment_extension;
	public static JSONObject id_;
	public static JSONArray content_item_s_a;

	public static JSONObject prep_treatment_extension(String prep_treatment_extension_num,String prep_treatment_extension_content,
													  String content_item_num,String content_item){

		prep_treatment_extension = new JSONObject();

		int num;
		if(prep_treatment_extension_num==null||"".equals(prep_treatment_extension_num)){
			num = 0;
		}else{
			num = Integer.parseInt(prep_treatment_extension_num);
		}

		JSONArray prep_treatment_extension_content_s = JSONArray.fromObject(prep_treatment_extension_content);
		JSONArray content_item_num_s = JSONArray.fromObject(content_item_num);
		JSONArray content_item_s = JSONArray.fromObject(content_item);

		int count = 0;
		prep_treatment_extension.put("num",num);
		for (int i = 0; i < prep_treatment_extension.getInt("num"); i++) {
			id_ = new JSONObject();
			content_item_s_a = new JSONArray();

			if(content_item_num_s.getString(i)==null||"".equals(content_item_num_s.getString(i))){

			}else{
				for (int j = 0; j < Integer.parseInt(content_item_num_s.getString(i)); j++) {
					content_item_s_a.add(content_item_s.getString(count));
					count++;
				}
			}

			//content_item数据总所要加入的内容
			id_.put("content", prep_treatment_extension_content_s.getString(i));
			id_.put("content_item", content_item_s_a);//content_item为以JSONArray
			prep_treatment_extension.put(String.format("id_%d", i), id_);
		}
		System.out.println(prep_treatment_extension.toString());
		return prep_treatment_extension;
	}

}