package cn.knowimage.jsonmake;

import cn.knowimage.util.StringConvertNumber;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class After_medical_treatmentJson {

	public static JSONObject after_medical_treatment;
	public static JSONObject duration;
	public static JSONObject scenario;
	public static JSONObject id_;
	public static JSONArray obligatory_exam;
	public static JSONArray optional_exam;
	public static JSONArray recovery_plan;

	public static JSONObject after_medical_treatment(String after_medical_treatment_duration,
													 String after_medical_treatment_duration_treatment_min,String after_medical_treatment_duration_treatment_max,String after_medical_treatment_scenario,
													 String after_medical_obligatory_exam_num,String after_medical_optional_exam_num,String after_medical_treatment_recovery_plan_num,
													 String after_medical_obligatory_exam,String after_medical_optional_exam,String after_medical_treatment_recovery_plan){

		after_medical_treatment = new JSONObject();



		int scenario_num;
		if(after_medical_treatment_scenario==null||"".equals(after_medical_treatment_scenario)){
			scenario_num = 0;
		}else{
			scenario_num = Integer.parseInt(after_medical_treatment_scenario);
		}

		JSONArray after_medical_obligatory_exam_num_s = JSONArray.fromObject(after_medical_obligatory_exam_num);
		JSONArray after_medical_optional_exam_num_s = JSONArray.fromObject(after_medical_optional_exam_num);
		JSONArray after_medical_treatment_recovery_plan_num_s = JSONArray.fromObject(after_medical_treatment_recovery_plan_num);

		JSONArray after_medical_obligatory_exam_s = JSONArray.fromObject(after_medical_obligatory_exam);
		JSONArray after_medical_optional_exam_s = JSONArray.fromObject(after_medical_optional_exam);
		JSONArray after_medical_treatment_recovery_plan_s = JSONArray.fromObject(after_medical_treatment_recovery_plan);
		//创建key=duration的json对象
		duration = new JSONObject();
		duration.put("time_unit","时");
		if("".equals(after_medical_treatment_duration)){
			duration.put("time_unit", "");
			duration.put("min",0);
			duration.put("max",0);
		}else{
			if(after_medical_treatment_duration_treatment_min==null||"".equals(after_medical_treatment_duration_treatment_min)){
				duration.put("min", 0);
			}else{
				duration.put("min", StringConvertNumber.stringToFloat(after_medical_treatment_duration,after_medical_treatment_duration_treatment_min));
			}

			if(after_medical_treatment_duration_treatment_max==null||"".equals(after_medical_treatment_duration_treatment_max)){
				duration.put("max", 0);
			}else{
				duration.put("max", StringConvertNumber.stringToFloat(after_medical_treatment_duration,after_medical_treatment_duration_treatment_max));
			}
		}


		after_medical_treatment.put("duration", duration);

		int obligatory_exam_count = 0;
		int optional_exam_count = 0;
		int recovery_plan_count = 0;

		scenario = new JSONObject();
		scenario.put("num", scenario_num);
		for (int i = 0; i < scenario.getInt("num"); i++) {

			id_ = new JSONObject();

			obligatory_exam = new JSONArray();
			if(after_medical_obligatory_exam_num_s.getString(i)==null||"".equals(after_medical_obligatory_exam_num_s.getString(i))){

			}else{
				for (int j = 0; j < Integer.parseInt(after_medical_obligatory_exam_num_s.getString(i)); j++) {
					if(after_medical_obligatory_exam_s.size()==0){
						break;
					}else{//注意王果果这里after_medical_obligatory_exam_s和after_medical_optional_exam_num_s是反过来的,还待解决。
						obligatory_exam.add(after_medical_obligatory_exam_s.getString(obligatory_exam_count));
						obligatory_exam_count++;
					}
				}
			}
			id_.put("obligatory_exam", obligatory_exam);


			optional_exam = new JSONArray();
			if(after_medical_optional_exam_num_s.getString(i)==null||"".equals(after_medical_optional_exam_num_s.getString(i))){

			}else{
				for (int j = 0; j < Integer.parseInt(after_medical_optional_exam_num_s.getString(i)); j++) {

					if(after_medical_optional_exam_s.size()==0){
						break;
					}else{
						optional_exam.add(after_medical_optional_exam_s.getString(optional_exam_count));
						optional_exam_count++;
					}

				}
			}
			id_.put("optional_exam", optional_exam);


			recovery_plan = new JSONArray();
			if(after_medical_treatment_recovery_plan_num_s.getString(i)==null||"".equals(after_medical_treatment_recovery_plan_num_s.getString(i))){

			}else{
				for (int j = 0; j < Integer.parseInt(after_medical_treatment_recovery_plan_num_s.getString(i)); j++){

					if(after_medical_treatment_recovery_plan_s.size()==0){
						break;
					}else{
						recovery_plan.add(after_medical_treatment_recovery_plan_s.getString(recovery_plan_count));
						recovery_plan_count++;
					}

				}
			}
			id_.put("recovery_plan", recovery_plan);

			scenario.put(String.format("id_%d", i), id_);
		}
		after_medical_treatment.put("scenario", scenario);

		System.out.println(after_medical_treatment.toString());
		return after_medical_treatment;
	}

}