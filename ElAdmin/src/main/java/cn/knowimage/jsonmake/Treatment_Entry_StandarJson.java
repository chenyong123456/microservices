package cn.knowimage.jsonmake;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 功能:创建key=treatment_entry_standard的JSONObject对象
 */
public class Treatment_Entry_StandarJson {

	public static JSONObject treatment_entry_standard;

	public static JSONObject treatment_entry_standard(String treatment_entry_standard_num,
													  String treatment_entry_standard_num_id){//会传入该对象所需要的参数

		int num = Integer.parseInt(treatment_entry_standard_num);
		if(treatment_entry_standard_num==null||"".equals(treatment_entry_standard_num)){
			num = 0;
		}else{
			num = Integer.parseInt(treatment_entry_standard_num);
		}

		JSONArray treatment_entry_standard_num_id_s = JSONArray.fromObject(treatment_entry_standard_num_id);

		treatment_entry_standard = new JSONObject();
		treatment_entry_standard.put("num", num);
		for (int i = 0; i < treatment_entry_standard.getInt("num"); i++) {
			//第一诊断必须符合ICD-10：T17.401/T17.501气管（支气管）异物疾病编码 -->会用传进来的变量替换掉值是不一样的
			treatment_entry_standard.put(String.format("id_%d", i), treatment_entry_standard_num_id_s.getString(i));
		}
		System.out.println(treatment_entry_standard.toString());
		//treatment_entry_standard.put("id_1", "当患者同时具有其他疾病诊断,但住院期间不需要特殊处理也不影响第一诊断的临床路径流程实施时,可以进入路径");
		return treatment_entry_standard;
	}

}
