package cn.knowimage.controller;

import cn.knowimage.jsonmake.*;
import cn.knowimage.pojo.Audit_Info;
import cn.knowimage.pojo.Pathway_Info;
import cn.knowimage.service.Audit_InfoService;
import cn.knowimage.service.Pathway_InfoService;
import cn.knowimage.service.redis.RedisService;
import cn.knowimage.util.StringIncise;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@CrossOrigin
@Controller
public class Pathway_InfoController {

	@Autowired
	public Pathway_InfoService pathway_InfoService;

	@Autowired
	public Audit_InfoService audit_InfoService;

	//注入redis的服务
	@Autowired
	public RedisService redisService;

	@Value("${CASE_SHOW}")
	public String CASE_SHOW;//小褚的缓存-->总文件夹

	//展示录入数据的界面
	@RequestMapping("/addExamForm")
	public String loggingToData(){
		return "addExamForm";
	}

	//查寻Pathway_Info表的指定pathway_name的相关数据
	@RequestMapping(value="/pathway_InfoBy_Only_pathway_name")
	@ResponseBody
	public JSONObject selectByPathway_name(String p_name){
		//pathWay_name = "小儿气管（支气管）异物";
		JSONObject lastJson = new JSONObject();
		System.out.println(p_name);
		if(!redisService.exists(CASE_SHOW+":"+"selectByPathway_name:"+p_name)) {//redis中不存在数据,去mysql中查询
			List<Pathway_Info> list = pathway_InfoService.selectByPathway_name(p_name);
			//model.addAttribute("pathway_Info", list);
			lastJson.put("pathway_id", list.get(0).getPathway_id());
			lastJson.put("pathway_index", list.get(0).getPathway_index());
			lastJson.put("pathway_name", list.get(0).getPathway_name());
			lastJson.put("first_diagnose", list.get(0).getFirst_diagnosis());
			lastJson.put("suitable_subject_disc", list.get(0).getSuitable_subject_disc());
			lastJson.put("diagnosis", list.get(0).getDiagnosis());
			lastJson.put("treatment_choice", list.get(0).getTreatment_choice());
			lastJson.put("treatment_days", list.get(0).getTreatment_days());
			lastJson.put("treatment_entry_standard", list.get(0).getTreatment_entry_standard());
			lastJson.put("type", list.get(0).getType());
			lastJson.put("drug_use_period", list.get(0).getDrug_use_period());
			lastJson.put("prep_treatment_common", list.get(0).getPrep_treatment_common());
			lastJson.put("prep_treatment_drug_usage", list.get(0).getPrep_treatment_drug_usage());
			lastJson.put("prep_treatment_common", list.get(0).getPrep_treatment_common());
			lastJson.put("prep_treatment_extension", list.get(0).getPrep_treatment_extension());
			lastJson.put("treatment", list.get(0).getTreatment());
			lastJson.put("drug_usage", list.get(0).getDrug_usage());
			lastJson.put("after_medical_treatment", list.get(0).getAfter_medical_treatment());
			lastJson.put("after_treatment_drug_usage", list.get(0).getAfter_treatment_drug_usage());
			lastJson.put("discharge_criteria", list.get(0).getDischarge_criteria());
			lastJson.put("other_notice", list.get(0).getOther_notice());
			//查询数据库的数据就写入redis
			redisService.set(CASE_SHOW+":"+"selectByPathway_name:"+p_name,lastJson.toString());
		}else{
			//redis中有就直接从redis中拿
			lastJson = JSONObject.fromObject(redisService.get(CASE_SHOW+":"+"selectByPathway_name:"+p_name));
		}
		return lastJson;
		//return list;
	}

	//查寻Pathway_Info表的指定pathway_name的相关的name名字-->会出现名称的下拉列表
	@RequestMapping("pathway_InfoBypathway_name")
	@ResponseBody
	public String selectBy_OnlyPathway_name(String list_name) {
		System.out.println(list_name);
		if(!redisService.exists(CASE_SHOW+":"+"selectBy_OnlyPathway_name:"+list_name)) {//redis中不存在
			List<Pathway_Info> list = pathway_InfoService.selectByPathway_name(list_name);
			JSONArray arrays = new JSONArray();
			for (Pathway_Info pathway_Info : list) {
				arrays.add(pathway_Info.getPathway_name());
			}
			redisService.set(CASE_SHOW+":"+"selectBy_OnlyPathway_name:"+list_name,arrays.toString());
			return arrays.toString();
		}else{//redis中存在数据
			return (String)redisService.get(CASE_SHOW+":"+"selectBy_OnlyPathway_name:"+list_name);
		}
	}

	//查寻Pathway_Info表的所有数据
	@RequestMapping("/pathway_Info")
	@ResponseBody
	public List<Pathway_Info> pathway_Info(Model model){

		List<Pathway_Info> list = pathway_InfoService.findAll();
//		for (Pathway_Info pathway_info : list) {
//			System.out.print(pathway_info.getPathway_index()+" ");
//			System.out.print(pathway_info.getPrep_treatment_common()+" ");
//			System.out.print(pathway_info.getAfter_medical_treatment()+" ");
//			System.out.print(pathway_info.getAfter_treatment_drug_usage()+" ");
//			System.out.print(pathway_info.getDischarge_criteria()+" ");
//			System.out.print(pathway_info.getDrug_usage()+" ");
//			System.out.println("pathway_info = " + pathway_info.getOther_notice());
//		}
        System.out.println(list.get(0).getTreatment_days());
       // System.out.println(StringIncise.InciseToReload("{\"scenario\":{\"num\":1,\"id_0\":{\"max\":96,\"min\":0,\"tag_name\":\"\",\"time_unit\":\"小时\"}}}"));
        System.out.println("*********************");
        System.out.println(StringIncise.InciseToReload(list.get(0).getTreatment_days()));
//        System.out.println(list.get(0).getPathway_index() + " ");
        System.out.println("*********");
        System.out.println(StringIncise.InciseToReload(list.get(0).getTreatment_choice()));
//        System.out.println(list.get(0).getPrep_treatment_common() + " ");
//        System.out.println("*********");
//        System.out.println(list.get(0).getAfter_medical_treatment() + " ");
//        System.out.println("*********");
//        System.out.println(list.get(0).getAfter_treatment_drug_usage() + " ");
//        System.out.println("*********");
//        System.out.println(list.get(0).getDischarge_criteria() + " ");
//        System.out.println("*********");
//        System.out.println(list.get(0).getDrug_usage() + " ");
//        System.out.println("*********");
//        System.out.println("Other_notice = " + list.get(0).getOther_notice());
//        System.out.println("*********");
//        System.out.println("Discharge_criteria = " + list.get(0).getDischarge_criteria());
		model.addAttribute("pathway_Info", list);
		return list;
	}
	//对Pathway_Info和audit_Info表实现同时insert
	@RequestMapping("/insert")
	@ResponseBody
	public String insertPathway_info(Pathway_Info pathway_info,String treatment_choice_scenario,@RequestParam String treatment_choice_scenario_num,
									 @RequestParam String treatment_choice_scenario_num_id,@RequestParam String treatment_days_scenario_num,
									 @RequestParam String treatment_days_tag_name,
									 @RequestParam String treatment_min,@RequestParam String treatment_max,
									 @RequestParam String treatment_days_time_unit,
									 @RequestParam String pathway_fabu,@RequestParam String pathway_year,
									 @RequestParam String pathway_Xuhao,@RequestParam String pathway_Banben,
									 @RequestParam String pathway_name,
									 @RequestParam String suitable_subject_disc,@RequestParam String diagnosis,
									 @RequestParam String treatment_choice_ref,@RequestParam String first_diagnose,
									 @RequestParam String treatment_entry_standard_num,@RequestParam String treatment_entry_standard_num_id,
									 @RequestParam String prep_treatment_common,@RequestParam String prep_treatment_common_num,
									 @RequestParam String prep_treatment_common_time_unit,@RequestParam String pre_treatment_min,
									 @RequestParam String pre_treatment_max,@RequestParam String obligatory_exam_num,
									 @RequestParam String optional_exam_num,@RequestParam String notification_num,
									 @RequestParam String obligatory_exam,@RequestParam String optional_exam,
									 @RequestParam String notification,@RequestParam String antibio_usage_num,
									 @RequestParam String anaesthetic_usage_num,@RequestParam String otherdrugs_usage_num,
									 @RequestParam String prep_treatment_antibio_usage_id,@RequestParam String prep_treatment_anaesthetic_usage_id,
									 @RequestParam String prep_treatment_otherdrugs_usage_id,
									 @RequestParam String prep_treatment_extension_num,@RequestParam String prep_treatment_extension_content,
									 @RequestParam String content_item_num,@RequestParam String content_item,
									 @RequestParam String treatment_days_duration,@RequestParam String duration_treatment_min,
									 @RequestParam String duration_treatment_max,@RequestParam String treatment_scenario,
									 @RequestParam String treatment_plan_ref,@RequestParam String treatment_plan_num,
									 @RequestParam String treatment_plan_num_id_content,@RequestParam String treatment_plan_num_id_content_item_num,
									 @RequestParam String treatment_scenario_content_item,@RequestParam String treatment_scenario_obligatory_exam_num,
									 @RequestParam String treatment_scenario_optional_exam_num,@RequestParam String  treatment_scenario_notification_num,
									 @RequestParam String treatment_scenario_obligatory_exam,@RequestParam String treatment_scenario_optional_exam,
									 @RequestParam String treatment_scenario_notification,@RequestParam String drug_usage_antibio_usage_num,
									 @RequestParam String drug_usage_anaesthetic_usage_num,@RequestParam String drug_usage_otherdrugs_usage_num,
									 @RequestParam String drug_usage_antibio_usage_id,@RequestParam String drug_usage_anaesthetic_usage_id,
									 @RequestParam String drug_usage_otherdrugs_usage_id,@RequestParam String after_medical_treatment_duration,
									 @RequestParam String after_medical_treatment_duration_treatment_min,@RequestParam String after_medical_treatment_duration_treatment_max,
									 @RequestParam String after_medical_treatment_scenario,@RequestParam String after_medical_treatment_recovery_plan_num,
									 @RequestParam String after_medical_obligatory_exam_num,@RequestParam String after_medical_optional_exam_num,
									 @RequestParam String after_medical_treatment_recovery_plan,@RequestParam String after_medical_optional_exam,
									 @RequestParam String after_medical_obligatory_exam,
									 @RequestParam String after_treatment_drug_usage_drug_usage_antibio_usage_num,
									 @RequestParam String after_treatment_drug_usage_drug_usage_anaesthetic_usage_num,
									 @RequestParam String after_treatment_drug_usage_drug_usage_otherdrugs_usage_num,
									 @RequestParam String after_treatment_drug_usage_antibio_usage_id,
									 @RequestParam String after_treatment_drug_usage_anaesthetic_usage_num,
									 @RequestParam String after_treatment_drug_usage_otherdrugs_usage_num,
									 @RequestParam String discharge_criteria,@RequestParam String discharge_criteria_item,
									 @RequestParam String other_notice,@RequestParam String other_notice_item,
									 @RequestParam String submitter,@RequestParam String radio){

		//对pathway_Xuhao索引进行不足4位数字
		if(pathway_Xuhao.equals("")){
			pathway_Xuhao = "0000"+pathway_Xuhao;
		}else if(pathway_Xuhao.length()==1){
			pathway_Xuhao = "000" + pathway_Xuhao;
		}else if(pathway_Xuhao.length()==2){
			pathway_Xuhao = "00" + pathway_Xuhao;
		}else if(pathway_Xuhao.length()==3){
			pathway_Xuhao = "0" + pathway_Xuhao;
		}
		//测试数据是否传入
		String pathway_index = pathway_fabu+ pathway_year+pathway_Xuhao+pathway_Banben;
		System.out.println(pathway_index);
		System.out.println(pathway_name);
		System.out.println(first_diagnose);
		System.out.println(suitable_subject_disc);
		System.out.println(diagnosis);
		//treatment_choice字段的场景
		System.out.println(treatment_choice_scenario);//动态循环id_1的个数
		System.out.println(treatment_choice_ref);
		System.out.println(treatment_choice_scenario_num);//treatment_choice_scenario_num每一个id_i的数组中的大小
		System.out.println(treatment_choice_scenario_num_id);//传的是数组treatment_choice中id_0[]数组中的字段
		//treatment_days字段的场景
		System.out.println(treatment_days_scenario_num);
		System.out.println(treatment_days_tag_name);
		System.out.println(treatment_min);
		System.out.println(treatment_max);
		System.out.println(treatment_days_time_unit);

		//在treatment_entry_standard和prep_treatment_common字段测试
		System.out.println(treatment_entry_standard_num);
		System.out.println(treatment_entry_standard_num_id);
		System.out.println(prep_treatment_common);//json文件那种type字段

		//prep_treatment_common字段的值
		System.out.println(prep_treatment_common_num);
		System.out.println(prep_treatment_common_time_unit);
		System.out.println(pre_treatment_min);
		System.out.println(pre_treatment_max);

		System.out.println(obligatory_exam_num);
		System.out.println(optional_exam_num);
		System.out.println(notification_num);

		System.out.println(obligatory_exam);
		System.out.println(optional_exam);
		System.out.println(notification);

		//prep_treatment_drug_usage字段的值
		System.out.println(antibio_usage_num);
		System.out.println(anaesthetic_usage_num);
		System.out.println(otherdrugs_usage_num);
		System.out.println(prep_treatment_antibio_usage_id);
		System.out.println(prep_treatment_anaesthetic_usage_id);
		System.out.println(prep_treatment_otherdrugs_usage_id);

		//prep_treatment_extension字段的值
		System.out.println(prep_treatment_extension_num);
		System.out.println(prep_treatment_extension_content);
		System.out.println(content_item_num);
		System.out.println(content_item);

		//treatment字段的值-->从这里开始拼接
		System.out.println(treatment_days_duration);
		System.out.println(duration_treatment_min);
		System.out.println(duration_treatment_max);
		System.out.println(treatment_scenario);

		System.out.println(treatment_plan_ref);
		System.out.println(treatment_plan_num);
		System.out.println(treatment_plan_num_id_content);
		System.out.println(treatment_plan_num_id_content_item_num);

		System.out.println(treatment_scenario_content_item);
		System.out.println(treatment_scenario_obligatory_exam_num);
		System.out.println(treatment_scenario_optional_exam_num);
		System.out.println(treatment_scenario_notification_num);

		System.out.println(treatment_scenario_obligatory_exam);
		System.out.println(treatment_scenario_optional_exam);
		System.out.println(treatment_scenario_notification);

		//drug_usage字段的值
		System.out.println(drug_usage_antibio_usage_num);
		System.out.println(drug_usage_anaesthetic_usage_num);
		System.out.println(drug_usage_otherdrugs_usage_num);
		System.out.println(drug_usage_antibio_usage_id);

		System.out.println(drug_usage_anaesthetic_usage_id);
		System.out.println(drug_usage_otherdrugs_usage_id);

		//after_medical_treatment字段的值
		System.out.println("******************************");
		System.out.println(after_medical_treatment_duration);
		System.out.println(after_medical_treatment_duration_treatment_min);
		System.out.println(after_medical_treatment_duration_treatment_max);
		System.out.println(after_medical_treatment_scenario);
		//数组应该存入相应的数量
		System.out.println(after_medical_obligatory_exam_num);//obligatory_exam数组需要分配的大小
		System.out.println(after_medical_optional_exam_num);//optional_exam数组需要分配的大小
		System.out.println(after_medical_treatment_recovery_plan_num);//recovery_plan数组需要分配的大小
		//要存入数组中相应的值
		System.out.println(after_medical_obligatory_exam);
		System.out.println(after_medical_optional_exam);
		System.out.println(after_medical_treatment_recovery_plan);
		System.out.println("********************************************");

		//after_treatment_drug_usage字段的值
		//分配的是每个数组的大小(及要分配的数组的大小空间)
		System.out.println(after_treatment_drug_usage_drug_usage_antibio_usage_num);
		System.out.println(after_treatment_drug_usage_drug_usage_anaesthetic_usage_num);
		System.out.println(after_treatment_drug_usage_drug_usage_otherdrugs_usage_num);
		//依次存的数组中所需要的值
		System.out.println(after_treatment_drug_usage_antibio_usage_id);
		System.out.println(after_treatment_drug_usage_anaesthetic_usage_num);
		System.out.println(after_treatment_drug_usage_otherdrugs_usage_num);
		System.out.println("********************************************");

		//discharge_criteria字段的值
		System.out.println(discharge_criteria);
		System.out.println(discharge_criteria_item);
		System.out.println("********************************************");

		//other_notice字段的值
		System.out.println(other_notice);
		System.out.println(other_notice_item);

		//submitter字段的值
		System.out.println(submitter);

		//相数据中来构造json格式的数据格式
		pathway_info.setPathway_index(pathway_index);
		pathway_info.setPathway_name(pathway_name);
		pathway_info.setFirst_diagnosis(first_diagnose);
		pathway_info.setSuitable_subject_disc(suitable_subject_disc);
		pathway_info.setDiagnosis(diagnosis);

		System.out.println("*****************************");
		System.out.println(radio);
		System.out.println("*******************************");

		//请求其他类来生成其他的json格式的数据和treatment_choice字段
		pathway_info.setTreatment_choice(Treatment_choiceJson.treatment_choice(treatment_choice_scenario,treatment_choice_ref
				,treatment_choice_scenario_num,treatment_choice_scenario_num_id).toString());
		//生成treatment_days字段
		pathway_info.setTreatment_days(Treatment_DaysJson.treatment_days(treatment_days_scenario_num,treatment_days_tag_name,treatment_min,
				treatment_max,treatment_days_time_unit).toString());

		pathway_info.setTreatment_entry_standard(Treatment_Entry_StandarJson.treatment_entry_standard(treatment_entry_standard_num,
				treatment_entry_standard_num_id).toString());

		//对应json文件中的type手术类型
		pathway_info.setType(prep_treatment_common);

		//对应json文件中的drug_use_period字段
		Integer drug_use_period = Integer.parseInt(radio);
		pathway_info.setDrug_use_period(drug_use_period);

		//拼json文件中prep_treatment_drug_usage字段
		pathway_info.setPrep_treatment_common(Prep_Treatment_CommonJson.prep_treatment_common(prep_treatment_common_num,prep_treatment_common_time_unit,
				pre_treatment_min,pre_treatment_max,obligatory_exam_num,optional_exam_num,notification_num,
				obligatory_exam,optional_exam,notification).toString());
		//拼json文件中prep_treatment_common字段
		pathway_info.setPrep_treatment_drug_usage(Prep_Treatment_Drug_UsageJson.prep_treatment_drug_usage(antibio_usage_num,anaesthetic_usage_num,otherdrugs_usage_num,
				prep_treatment_antibio_usage_id,prep_treatment_anaesthetic_usage_id,prep_treatment_otherdrugs_usage_id).toString());

		pathway_info.setPrep_treatment_extension(Prep_Treatment_ExtensionJson.prep_treatment_extension(prep_treatment_extension_num,
				prep_treatment_extension_content,content_item_num,content_item).toString());

		pathway_info.setTreatment(TreatmentJson.treatment(treatment_days_duration,duration_treatment_min,duration_treatment_max,
				treatment_scenario,treatment_plan_ref,treatment_plan_num,treatment_plan_num_id_content,
				treatment_plan_num_id_content_item_num,treatment_scenario_content_item,treatment_scenario_obligatory_exam_num,
				treatment_scenario_optional_exam_num,treatment_scenario_notification_num,treatment_scenario_obligatory_exam,
				treatment_scenario_optional_exam,treatment_scenario_notification).toString());

		pathway_info.setDrug_usage(Drug_usageJson.drug_usage(drug_usage_antibio_usage_num,drug_usage_anaesthetic_usage_num,
				drug_usage_otherdrugs_usage_num,drug_usage_antibio_usage_id,drug_usage_anaesthetic_usage_id,drug_usage_otherdrugs_usage_id).toString());


		pathway_info.setAfter_medical_treatment(After_medical_treatmentJson.after_medical_treatment(after_medical_treatment_duration,
				after_medical_treatment_duration_treatment_min,after_medical_treatment_duration_treatment_max,after_medical_treatment_scenario,
				after_medical_obligatory_exam_num,after_medical_optional_exam_num,after_medical_treatment_recovery_plan_num,
				after_medical_obligatory_exam,after_medical_optional_exam,after_medical_treatment_recovery_plan).toString());

		pathway_info.setAfter_treatment_drug_usage(After_treatment_drug_usageJson.after_treatment_drug_usage(after_treatment_drug_usage_drug_usage_antibio_usage_num,
				after_treatment_drug_usage_drug_usage_anaesthetic_usage_num,after_treatment_drug_usage_drug_usage_otherdrugs_usage_num,
				after_treatment_drug_usage_antibio_usage_id,after_treatment_drug_usage_anaesthetic_usage_num,after_treatment_drug_usage_otherdrugs_usage_num).toString());

		pathway_info.setDischarge_criteria(Discharge_criteriaAndOther_notice.discharge_criteria(discharge_criteria_item,discharge_criteria).toString());//写到这里了

		pathway_info.setOther_notice(Discharge_criteriaAndOther_notice.other_notice(other_notice_item,other_notice).toString());
		//数据的插入操作数据库,数据格式转换完成之后请放开改行代码来新增数据的记录
		int count = pathway_InfoService.insertPathway_info(pathway_info);

		//添加audit_Info表的数据
		Audit_Info audit_Info = new Audit_Info();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String updateDate =formatter.format(new Date());

		audit_Info.setPathway_name(pathway_name);
		audit_Info.setSubmitter(submitter);
		audit_Info.setData_upload_moment(updateDate);
		audit_Info.setChecker("Li");
		audit_Info.setAudit_state(0);
		//数据格式转换完成之后请放开改行代码来新增数据的记录  记住在audit_Info新增相应的记录
		int counta = audit_InfoService.insertAudit_info(audit_Info);
		if((count == 1)&&(counta == 1)){
			//redisService.set(CASE_SHOW+":"+"selectByPathway_name:"+pathway_name, JsonUtils.objectToJson(pathway_info));
			return "ok";
		}else{
			return "false";
		}


	}


}