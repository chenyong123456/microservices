package cn.knowimage.jsonmake;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 功能:创建key=drug_usage的json对象
 */
public class Drug_usageJson {

	public static JSONObject drug_usage;
	public static JSONObject antibio_usage;
	public static JSONObject anaesthetic_usage;
	public static JSONObject otherdrugs_usage;
	public static JSONArray id_;

	public static JSONObject drug_usage(String drug_usage_antibio_usage_num,String drug_usage_anaesthetic_usage_num,
										String drug_usage_otherdrugs_usage_num,String drug_usage_antibio_usage_id,String drug_usage_anaesthetic_usage_id,String drug_usage_otherdrugs_usage_id){

		drug_usage = new JSONObject();

		int antibio_num;
		if(drug_usage_antibio_usage_num==null||"".equals(drug_usage_antibio_usage_num)){
			antibio_num = 0;
		}else{
			antibio_num = Integer.parseInt(drug_usage_antibio_usage_num);
		}

		int drug_usage_anaesthetic_usage_num_s;
		if(drug_usage_anaesthetic_usage_num==null||"".equals(drug_usage_anaesthetic_usage_num)){
			drug_usage_anaesthetic_usage_num_s = 0;
		}else{
			drug_usage_anaesthetic_usage_num_s = Integer.parseInt(drug_usage_anaesthetic_usage_num);
		}

		int drug_usage_otherdrugs_usage_num_s;
		if(drug_usage_otherdrugs_usage_num==null||"".equals(drug_usage_otherdrugs_usage_num)){
			drug_usage_otherdrugs_usage_num_s = 0;
		}else{
			drug_usage_otherdrugs_usage_num_s = Integer.parseInt(drug_usage_otherdrugs_usage_num);
		}

		JSONArray drug_usage_antibio_usage_id_s = JSONArray.fromObject(drug_usage_antibio_usage_id);
		JSONArray drug_usage_anaesthetic_usage_id_s = JSONArray.fromObject(drug_usage_anaesthetic_usage_id);
		JSONArray drug_usage_otherdrugs_usage_id_s = JSONArray.fromObject(drug_usage_otherdrugs_usage_id);

		antibio_usage = new JSONObject();
		antibio_usage.put("num", antibio_num);
		id_ = new JSONArray();
		for (int i = 0; i < antibio_usage.getInt("num"); i++) {
			id_.add(drug_usage_antibio_usage_id_s.getString(i));
		}
		antibio_usage.put("id_0", id_);
		drug_usage.put("antibio_usage", antibio_usage);


		anaesthetic_usage = new JSONObject();
		anaesthetic_usage.put("num", drug_usage_anaesthetic_usage_num_s);
		id_ = new JSONArray();
		for (int i = 0; i < anaesthetic_usage.getInt("num"); i++) {
			id_.add(drug_usage_anaesthetic_usage_id_s.getString(i));
		}
		anaesthetic_usage.put("id_0", id_);
		drug_usage.put("anaesthetic_usage", anaesthetic_usage);


		otherdrugs_usage = new JSONObject();
		otherdrugs_usage.put("num",drug_usage_otherdrugs_usage_num_s);
		id_ = new JSONArray();
		for (int i = 0; i < otherdrugs_usage.getInt("num"); i++) {
			id_.add(drug_usage_otherdrugs_usage_id_s.getString(i));
		}
		otherdrugs_usage.put("id_0", id_);
		drug_usage.put("otherdrugs_usage", otherdrugs_usage);

		System.out.println(drug_usage.toString());
		return drug_usage;
	}


}