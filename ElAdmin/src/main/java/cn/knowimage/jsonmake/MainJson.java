package cn.knowimage.jsonmake;

import net.sf.json.JSONObject;
/**
 * 生成最终的json对应json文件中的key/value
 */
public class MainJson {

	public static JSONObject mainJsonMake(){//参数中会传每一个key的value过来(及变量).
		//json对象中可以存放JSONObject,JSONArray
		JSONObject mainJson = new JSONObject();//在该方法上会有每个key的value传过来,每一个参数代表一个key的值,JSONObject就传对象 JSONArray就传数据,单个值就传单个值.
		//key/value的存入
		mainJson.put("pathway_id", 1);
		mainJson.put("pathway_index", "012016000100");
		mainJson.put("pathway_name", "小儿气管（支气管）异物");
		mainJson.put("first_diagnose", "ICD-10：T17.401/T17.501");
		mainJson.put("suitable_subject_disc", "第一诊断为气管异物/支气管异物（ICD-10：T17.401/T17.501）。行支气管镜检查术(ICD-9-CM3:33.2301)。");
		mainJson.put("diagnosis", "ICD-10：根据《临床诊疗指南-耳鼻喉科分册》（中华医学会编著，人民卫生出版社）"
				+ "1.症状:误呛异物后咳嗽或突发咳嗽、慢性咳嗽治疗无好转、或突发气喘及呼吸困难，严重者可出现窒息、呼吸衰竭等表现。"
				+ "2.体征:支气管异物肺部听诊常有一侧呼吸音低，气管内活动异物可听到气管撞击声，张口呼吸可听到哮喘样喘鸣，肺部听诊双侧呼吸音粗，可闻及干湿啰音及喘鸣音。"
				+ "3.胸透可见一侧肺气肿、肺不张以及纵膈摆动等表现。"
				+ "4.胸部CT可见主气管或支气管内异物影。");
		//mainJson.put("treatment_choice", value);//value会有相应的参数传过来,由于前端数据还没传过来,这里就为调用其他类的方法来手动添加.
		//put很多key-->value待加入对象中


		mainJson.put("type", "surgical");
		//在这里会有相应的key的JSONObject加入


		mainJson.put("submitter", "chen");
		mainJson.put("checker", "Li");
		mainJson.put("is_audited", 0);
		mainJson.put("pathway_priority", 0);

		return mainJson;
	}
	//要主方法只是为了测试json数据的正确性,在真正的开发过程中该方法是去掉的,在controller中直接调用MainJson.mainJsonMake(传入相应参数)获取最终的json对象.
	public static void main(String[] args) {
		System.out.println(mainJsonMake().toString());
	}
}