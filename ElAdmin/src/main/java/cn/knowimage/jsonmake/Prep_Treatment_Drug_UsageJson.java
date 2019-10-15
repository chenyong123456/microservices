package cn.knowimage.jsonmake;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 功能:创建key=prep_treatment_drug_usage的json对象
 */
public class Prep_Treatment_Drug_UsageJson {

	public static JSONObject prep_treatment_drug_usage;
	public static JSONObject antibio_usage;
	public static JSONObject anaesthetic_usage;
	public static JSONObject otherdrugs_usage;
	public static JSONArray id_;

	public static JSONObject prep_treatment_drug_usage(String antibio_usage_num,String anaesthetic_usage_num,String otherdrugs_usage_num,
													   String prep_treatment_antibio_usage_id, String prep_treatment_anaesthetic_usage_id, String prep_treatment_otherdrugs_usage_id){

		prep_treatment_drug_usage = new JSONObject();

		int num_antibio;
		if(antibio_usage_num==null||"".equals(antibio_usage_num)){
			num_antibio = 0;
		}else{
			num_antibio = Integer.parseInt(antibio_usage_num);
		}

		int num_anaesthetic;
		if(anaesthetic_usage_num==null||"".equals(anaesthetic_usage_num)){
			num_anaesthetic = 0;
		}else{
			num_anaesthetic = Integer.parseInt(anaesthetic_usage_num);
		}

		int num_otherdrugs;
		if(otherdrugs_usage_num==null||"".equals(otherdrugs_usage_num)){
			num_otherdrugs = 0;
		}else{
			num_otherdrugs = Integer.parseInt(otherdrugs_usage_num);
		}

		JSONArray prep_treatment_antibio_usage_id_s = JSONArray.fromObject(prep_treatment_antibio_usage_id);
		JSONArray prep_treatment_anaesthetic_usage_id_s = JSONArray.fromObject(prep_treatment_anaesthetic_usage_id);
		JSONArray prep_treatment_otherdrugs_usage_id_s = JSONArray.fromObject(prep_treatment_otherdrugs_usage_id);

		antibio_usage = new JSONObject();
		antibio_usage.put("num", num_antibio);
		id_ = new JSONArray();
		for (int i = 0; i < antibio_usage.getInt("num"); i++) {
			id_.add(prep_treatment_antibio_usage_id_s.getString(i));
		}
		antibio_usage.put("id_0", id_);
		prep_treatment_drug_usage.put("antibio_usage", antibio_usage);


		anaesthetic_usage = new JSONObject();
		anaesthetic_usage.put("num", num_anaesthetic);
		id_ = new JSONArray();//id_重新分配了内存前面的值全部覆盖
		for (int i = 0; i < anaesthetic_usage.getInt("num"); i++) {
			id_.add(prep_treatment_anaesthetic_usage_id_s.getString(i));
		}
		anaesthetic_usage.put("id_0", id_);
		prep_treatment_drug_usage.put("anaesthetic_usage", anaesthetic_usage);


		otherdrugs_usage = new JSONObject();
		otherdrugs_usage.put("num", num_otherdrugs);
		id_ = new JSONArray();
		for (int i = 0; i < otherdrugs_usage.getInt("num"); i++) {
			id_.add(prep_treatment_otherdrugs_usage_id_s.getString(i));
		}
		otherdrugs_usage.put("id_0", id_);
		prep_treatment_drug_usage.put("otherdrugs_usage", otherdrugs_usage);
		//测试
		System.out.println(prep_treatment_drug_usage.toString());
		return prep_treatment_drug_usage;
	}

}