package cn.knowimage.jsonoutput;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 功能:对treatment字段String类型json格式的字符串进行转换
 * @author 啊勇
 *
 */
public class TreatmentOutput {
	
	public static JSONObject treatment_s;
	public static JSONObject antibio_usage;
	public static JSONObject scenario_id;
	public static JSONObject scenario_s;//最终要生成的json对象
	//scenario_id_0字段的相关变量
	public static JSONObject treatment_plan;//创建treatment_plan对象
	public static JSONArray obligatory_exam;
	public static JSONArray optional_exam;
	public static JSONArray notification;
	public static JSONObject id_;
	public static JSONArray content_item;

	//王果果的数据将数据库中的String类型的json格式的数据
	public static JSONObject treatment_commonOut(String last) {
		//最终要生成的json对象
		scenario_s = new JSONObject();
		//总数组
		JSONArray scenario = JSONArray.fromObject(last);
		//获取scenario_num的大小
		int scenario_num = scenario.size();
		scenario_s.put("scenario_num",scenario_num);

		//存褚每一个treatment_plan的num的值
		int[] treatment_plan_num = new int[scenario_num];//这里面的存的值为treatment_plan中num的值
		for(int i = 0 ;i < scenario_num;i++){
			JSONObject scenario_id = scenario.getJSONObject(i);
			//treatment_plan_num获取的大小
			JSONArray caseChildnum = scenario_id.getJSONArray("caseChildnum");
			treatment_plan_num[i] = caseChildnum.size();
		}

		//下面为拼接scenario文档格式的字符串
		//创建scenario_id_0字段,动态创建scenario_id_0的对象
		for (int i = 0; i < scenario_num; i++) {
			//获取第一个对象(前端传过来的值)
			JSONObject userValue = scenario.getJSONObject(i);
			//动态创建scenario_id_0
			scenario_id = new JSONObject();//将分离出来值放入这里面
			//创建treatment_plan对象
			treatment_plan = new JSONObject();
			treatment_plan.put("ref",userValue.getString("treatmentPlanRef"));
			treatment_plan.put("num",treatment_plan_num[i]);
			//获取caseChildnum的值
			JSONArray caseChildnum_s = userValue.getJSONArray("caseChildnum");
			//这里动态创建treatment_plan里面的id_0对象
			for (int j = 0; j < treatment_plan_num[i] ; j++) {
				JSONObject caseChildnum_value = caseChildnum_s.getJSONObject(j);
				id_ = new JSONObject();
				id_.put("content",caseChildnum_value.getString("treatmentPlanContent"));
				//获取planContentItem的值
				JSONArray planContentItem_s = caseChildnum_value.getJSONArray("planContentItem");
				//定义content_item数组
				content_item = new JSONArray();
				//获取planContentItem中的值
				for (int k = 0; k <planContentItem_s.size() ; k++) {
					content_item.add(planContentItem_s.getJSONObject(k).getString("value"));
				}
				id_.put("content_item",content_item);
				//将创建好的id_加到treatment_plan对象中
				treatment_plan.put(String.format("id_%d",j),id_);
			}
			//treatment_plan创建完加入到scenario_id_0对象中去
			scenario_id.put("treatment_plan",treatment_plan);

			//下面创建obligatory_exam,optional_exam,notification
			JSONArray obligatory_exam_s = userValue.getJSONArray("obligatoryExam");
			obligatory_exam = new JSONArray();
			for (int j = 0; j < obligatory_exam_s.size(); j++) {
				obligatory_exam.add(obligatory_exam_s.getJSONObject(j).getString("value"));
			}
			//obligatory_exam创建完之后加入scenario_id_0字段中
			scenario_id.put("obligatory_exam",obligatory_exam);

			//创建optional_exam字段
			JSONArray optional_exam_s = userValue.getJSONArray("optionalExam");
			optional_exam = new JSONArray();
			for (int j = 0; j <optional_exam_s.size() ; j++) {
				optional_exam.add(optional_exam_s.getJSONObject(j).getString("value"));
			}
			//optional_exam创建完之后加入scenario_id_0字段中
			scenario_id.put("optional_exam",optional_exam);

			//创建notification字段
			JSONArray notification_s = userValue.getJSONArray("notification");
			notification = new JSONArray();
			for (int j = 0; j <notification_s.size() ; j++) {
				notification.add(notification_s.getJSONObject(j).getString("value"));
			}
			//optional_exam创建完之后加入scenario_id_0字段中
			scenario_id.put("notification",notification);
			scenario_s.put(String.format("scenario_id_%d",i),scenario_id);
		}
		return scenario_s;
	}
			
	public static void main(String[] args) {

	}

}
