package cn.knowimage.controller;

import cn.knowimage.pojo.PathwayInfo;
import cn.knowimage.service.AuditInfoService;
import cn.knowimage.service.PathwayInfoService;
import cn.knowimage.service.redis.RedisService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * PC端WIKI的迷糊查询和数据的回显以及微信小程序的
 * @author 啊勇
 */
@CrossOrigin
@Controller
public class PathwayInfoController {
	/**
	 * 注入pathway_Info表的service的服务
	 */
	@Autowired
	public PathwayInfoService pathway_InfoService;
	/**
	 * 注入audit_Info表的service的服务
	 */
	@Autowired
	public AuditInfoService audit_InfoService;
	/**
	 * 注入redis的服务
	 */
	@Autowired
	public RedisService redisService;

	@Value("${CASE_SHOW}")
	public String CASE_SHOW;//小褚的缓存-->总文件夹

	//展示录入数据的界面
	@RequestMapping("/index")
	public String loggingToData(){
		return "index";
	}

	/**
	 * 查寻Pathway_Info表的指定pathway_name(指定的名字)的相关数据 及详情数据-->小褚(pc端和微信小程序) PC端的wiki部分
	 * @param p_name
	 * @return
	 */
	@RequestMapping(value="/pathway_InfoBy_Only_pathway_name")
	@ResponseBody
	public JSONObject selectByPathway_name(String p_name){
		//pathWay_name = "小儿气管（支气管）异物";
		JSONObject lastJson = new JSONObject();
		System.out.println(p_name);
		if(!redisService.exists(CASE_SHOW+":"+"selectByPathway_name:"+p_name)) {//redis中不存在数据,去mysql中查询
			List<PathwayInfo> list = pathway_InfoService.selectByPathway_name(p_name);
			//model.addAttribute("pathway_Info", list);
			lastJson.put("pathway_id", list.get(0).getPathway_id());
			lastJson.put("pathway_index", list.get(0).getPathway_index());
			lastJson.put("pathway_name", list.get(0).getPathway_name());
			lastJson.put("first_diagnose", list.get(0).getFirst_diagnosis());
			lastJson.put("suitable_subject_disc", list.get(0).getSuitable_subject_disc());
			lastJson.put("diagnosis", list.get(0).getDiagnosis());
			lastJson.put("treatment_choice", list.get(0).getTreatment_choice());

			//在这里进行id_0中加入一个字段"tag":"treatment_days",
			JSONObject transfort = JSONObject.fromObject(list.get(0).getTreatment_days());
			JSONObject scenario = transfort.getJSONObject("scenario");
			int num = scenario.getInt("num");
			for (int i = 0; i < num; i++) {
				JSONObject id_ = scenario.getJSONObject(String.format("id_%d",i));
				id_.put("tag","treatment_days");
				scenario.put(String.format("id_%d",i),id_);
			}
			transfort.put("scenario",scenario);
			lastJson.put("treatment_days", transfort);


			lastJson.put("treatment_entry_standard", list.get(0).getTreatment_entry_standard());
			lastJson.put("type", list.get(0).getType());
			lastJson.put("drug_use_period", list.get(0).getDrug_use_period());

			//在这里进行duration中加入一个字段"tag":"treatment_days",
			JSONObject prepTreatmentCommonTrans = JSONObject.fromObject(list.get(0).getPrep_treatment_common());
			JSONObject ptcscenario = prepTreatmentCommonTrans.getJSONObject("scenario");
			int ptcnum = ptcscenario.getInt("num");
			for (int i = 0; i < ptcnum; i++) {
				JSONObject id_ = ptcscenario.getJSONObject(String.format("id_%d",i));
				JSONObject duration = id_.getJSONObject("duration");
				duration.put("tag","prep_treatment_common");
				id_.put("duration",duration);
				ptcscenario.put(String.format("id_%d",i),id_);

			}
			prepTreatmentCommonTrans.put("scenario",ptcscenario);
			lastJson.put("prep_treatment_common", prepTreatmentCommonTrans);


			lastJson.put("prep_treatment_drug_usage", list.get(0).getPrep_treatment_drug_usage());
			lastJson.put("prep_treatment_extension", list.get(0).getPrep_treatment_extension());

			//在这里进行duration中加入一个字段"tag":"treatment",
			JSONObject treatmentTras = JSONObject.fromObject(list.get(0).getTreatment());
			JSONObject treatmentDuration = treatmentTras.getJSONObject("duration");
			treatmentDuration.put("tag","treatment");
			treatmentTras.put("duration",treatmentDuration);

			lastJson.put("treatment", treatmentTras);

			lastJson.put("drug_usage", list.get(0).getDrug_usage());

			//在这里进行duration中加入一个字段"tag":"after_medical_treatment",
			JSONObject after_medical_treatmentTras = JSONObject.fromObject(list.get(0).getAfter_medical_treatment());
			JSONObject after_medical_treatmentDuration = after_medical_treatmentTras.getJSONObject("duration");
			after_medical_treatmentDuration.put("tag","after_medical_treatment");
			after_medical_treatmentTras.put("duration",after_medical_treatmentDuration);

			lastJson.put("after_medical_treatment", after_medical_treatmentTras);


			lastJson.put("after_treatment_drug_usage", list.get(0).getAfter_treatment_drug_usage());
			lastJson.put("discharge_criteria", list.get(0).getDischarge_criteria());
			lastJson.put("other_notice", list.get(0).getOther_notice());
			lastJson.put("additional_field", list.get(0).getAdditional_field());
			//查询数据库的数据就写入redis
			redisService.set(CASE_SHOW+":"+"selectByPathway_name:"+p_name,lastJson.toString());
		}else{
			//redis中有就直接从redis中拿
			lastJson = JSONObject.fromObject(redisService.get(CASE_SHOW+":"+"selectByPathway_name:"+p_name));
		}
		return lastJson;
		//return list;
	}
	/**
	 * 查寻Pathway_Info表的指定pathway_name的相关的name名字-->会出现名称的下拉列表-->小褚  模糊查询 PC端的wiki部分
	 * @param list_name 模糊查询的字段
	 * @return 返回json格式的数组字符串
	 */
	@RequestMapping("pathway_InfoBypathway_name")
	@ResponseBody
	public String selectBy_OnlyPathway_name(String list_name) {
		System.out.println(list_name);
		if(!redisService.exists(CASE_SHOW+":"+"selectBy_OnlyPathway_name:"+list_name)) {//redis中不存在
			List<PathwayInfo> list = pathway_InfoService.selectByPathway_name(list_name);
			JSONArray arrays = new JSONArray();
			for (PathwayInfo pathway_Info : list) {
				arrays.add(pathway_Info.getPathway_name());
			}
			redisService.set(CASE_SHOW+":"+"selectBy_OnlyPathway_name:"+list_name,arrays.toString());
			return arrays.toString();
		}else{//redis中存在数据
			return (String)redisService.get(CASE_SHOW+":"+"selectBy_OnlyPathway_name:"+list_name);
		}
	}

}