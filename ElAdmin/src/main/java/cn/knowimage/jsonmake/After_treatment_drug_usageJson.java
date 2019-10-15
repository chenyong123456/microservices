package cn.knowimage.jsonmake;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 功能:创建key=after_treatment_drug_usage的jSONObject
 */
public class After_treatment_drug_usageJson {

	public static JSONObject after_treatment_drug_usage;
	public static JSONObject antibio_usage;
	public static JSONObject anaesthetic_usage;
	public static JSONObject otherdrugs_usage;
	public static JSONArray id_;

	public static JSONObject after_treatment_drug_usage(String after_treatment_drug_usage_drug_usage_antibio_usage_num,
														String after_treatment_drug_usage_drug_usage_anaesthetic_usage_num,String after_treatment_drug_usage_drug_usage_otherdrugs_usage_num,
														String after_treatment_drug_usage_antibio_usage_id,String after_treatment_drug_usage_anaesthetic_usage_num,String after_treatment_drug_usage_otherdrugs_usage_num){
		after_treatment_drug_usage = new JSONObject();

		int antibio_usage_num;
		if(after_treatment_drug_usage_drug_usage_antibio_usage_num==null||"".equals(after_treatment_drug_usage_drug_usage_antibio_usage_num)){
			antibio_usage_num = 0;
		}else{
			antibio_usage_num = Integer.parseInt(after_treatment_drug_usage_drug_usage_antibio_usage_num);
		}

		int anaesthetic_usage_num;
		if(after_treatment_drug_usage_drug_usage_anaesthetic_usage_num==null||"".equals(after_treatment_drug_usage_drug_usage_anaesthetic_usage_num)){
			anaesthetic_usage_num = 0;
		}else{
			anaesthetic_usage_num = Integer.parseInt(after_treatment_drug_usage_drug_usage_anaesthetic_usage_num);
		}

		int otherdrugs_usage_num;
		if(after_treatment_drug_usage_drug_usage_otherdrugs_usage_num==null||"".equals(after_treatment_drug_usage_drug_usage_otherdrugs_usage_num)){
			otherdrugs_usage_num = 0;
		}else{
			otherdrugs_usage_num = Integer.parseInt(after_treatment_drug_usage_drug_usage_otherdrugs_usage_num);
		}

		JSONArray after_treatment_drug_usage_antibio_usage_id_s = JSONArray.fromObject(after_treatment_drug_usage_antibio_usage_id);
		JSONArray after_treatment_drug_usage_anaesthetic_usage_num_s = JSONArray.fromObject(after_treatment_drug_usage_anaesthetic_usage_num);
		JSONArray after_treatment_drug_usage_otherdrugs_usage_num_s = JSONArray.fromObject(after_treatment_drug_usage_otherdrugs_usage_num);


		antibio_usage = new JSONObject();
		antibio_usage.put("num", antibio_usage_num);
		id_ = new JSONArray();
		for (int i = 0; i < antibio_usage.getInt("num"); i++) {
			id_.add(after_treatment_drug_usage_antibio_usage_id_s.getString(i));
		}
		antibio_usage.put("id_0", id_);
		after_treatment_drug_usage.put("antibio_usage", antibio_usage);

		anaesthetic_usage = new JSONObject();
		anaesthetic_usage.put("num", anaesthetic_usage_num);
		id_ = new JSONArray();
		for (int i = 0; i < anaesthetic_usage.getInt("num"); i++) {
			id_.add(after_treatment_drug_usage_anaesthetic_usage_num_s.getString(i));
		}
		anaesthetic_usage.put("id_0", id_);
		after_treatment_drug_usage.put("anaesthetic_usage", anaesthetic_usage);

		otherdrugs_usage = new JSONObject();
		otherdrugs_usage.put("num", otherdrugs_usage_num);
		id_ = new JSONArray();
		for (int i = 0; i < otherdrugs_usage.getInt("num"); i++) {
			id_.add(after_treatment_drug_usage_otherdrugs_usage_num_s.getString(i));
		}
		otherdrugs_usage.put("id_0", id_);
		after_treatment_drug_usage.put("otherdrugs_usage", otherdrugs_usage);

		System.out.println(after_treatment_drug_usage.toString());
		return after_treatment_drug_usage;

	}

}