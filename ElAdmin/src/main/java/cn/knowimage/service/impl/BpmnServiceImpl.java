package cn.knowimage.service.impl;

import cn.knowimage.mapper.Pathway_InfoMapper;
import cn.knowimage.pojo.Pathway_Info;
import cn.knowimage.service.BpmnService;
import cn.knowimage.service.redis.RedisService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 功能:主要为Bpmn的数据进行处理
 * @author 啊勇
 */
//@ComponentScan(basePackages = "cn.knowimage.controller")
@Service
public class BpmnServiceImpl implements BpmnService {

    @Autowired
    public Pathway_InfoMapper pathway_InfoMapper;

    //注入redis的服务
    @Autowired
    public RedisService redisService;

    @Value("${BPMN}")
    public String BPMN;//Bpmn的缓存-->文件夹

    //对表Pathway_info进行但个字段的查询实现增加
    @Override
    public JSONObject selectByPathway_id(String pathway_id) {

        System.out.println(pathway_id);
        JSONObject json_template = new JSONObject();

        Integer pathway_ids = Integer.parseInt(pathway_id);

        if(!redisService.exists(BPMN+":"+"selectByPathway_id:"+pathway_id)) {//redis中不存在,去数据库中查,查完了还要存入redis中
            //根据pathway_Id进行查询一条数据
            List<Pathway_Info> list = pathway_InfoMapper.selectByPathway_id(pathway_ids);

            //pathway_index字段的处理
            String pathway_index = list.get(0).getPathway_index();
		/*
		返回从起始位置（beginIndex）到目标位置（endIndex）之间的字符串，但不包含目标位置（endIndex）的字符[)
		String pathway_fabu = pathway_index.substring(0, 2);
		String pathway_year = pathway_index.substring(2, 6);
		String pathway_Xuhao = pathway_index.substring(6, 10);
		String pathway_Banben = pathway_index.substring(10);
		json_template.put("pathway_fabu", pathway_fabu);
		json_template.put("pathway_year", pathway_year);
		json_template.put("pathway_Xuhao", pathway_Xuhao);
		json_template.put("pathway_Banben", pathway_Banben);
        pathway_name字段的处理
        */

            String pathway_name = list.get(0).getPathway_name();
            json_template.put("pathway_name", pathway_name);
            //first_diagnose字段的处理
            String first_diagnose = list.get(0).getFirst_diagnosis();
            json_template.put("first_diagnose", first_diagnose);
            //suitable_subject_disc字段的处理
            String suitable_subject_disc = list.get(0).getSuitable_subject_disc();
            json_template.put("suitable_subject_disc", suitable_subject_disc);
            //diagnosis字段的处理
            String diagnosis = list.get(0).getDiagnosis();
            json_template.put("diagnosis", diagnosis);

            //对treatment_choice字段进行转换-->不是单个字段
            JSONObject treatment_choice = JSONObject.fromObject(list.get(0).getTreatment_choice());
            json_template.put("treatment_choice", treatment_choice);

            //对treatment_days字段进行转换-->不是单个字段
            JSONObject treatment_days = JSONObject.fromObject(list.get(0).getTreatment_days());
            json_template.put("treatment_days", treatment_days);

            //对treatment_entry_standard字段进行转换-->不是单个字段
            JSONObject treatment_entry_standard = JSONObject.fromObject(list.get(0).getTreatment_entry_standard());
            json_template.put("treatment_entry_standard", treatment_entry_standard);

            //对type字段进行转换-->是单个字段
            json_template.put("type", list.get(0).getType());

            //对drug_use_period字段进行转换-->是单个字段
            json_template.put("drug_use_period", list.get(0).getDrug_use_period());

            //对prep_treatment_common字段进行转换-->不是单个字段
            JSONObject prep_treatment_common = JSONObject.fromObject(list.get(0).getPrep_treatment_common());
            json_template.put("prep_treatment_common", prep_treatment_common);

            //对prep_treatment_drug_usage字段进行转换-->不是单个字段
            JSONObject prep_treatment_drug_usage = JSONObject.fromObject(list.get(0).getPrep_treatment_drug_usage());
            json_template.put("prep_treatment_drug_usage", list.get(0).getPrep_treatment_drug_usage());

            //对prep_treatment_extension字段进行转换-->不是单个字段
            JSONObject prep_treatment_extension = JSONObject.fromObject(list.get(0).getPrep_treatment_extension());
            json_template.put("prep_treatment_extension", prep_treatment_extension);

            //对treatment字段进行转换-->不是单个字段
            JSONObject treatment = JSONObject.fromObject(list.get(0).getTreatment());
            json_template.put("treatment", treatment);

            //对drug_usage字段进行转换-->不是单个字段
            JSONObject drug_usage = JSONObject.fromObject(list.get(0).getDrug_usage());
            json_template.put("drug_usage", drug_usage);

            //对after_medical_treatment字段进行转换-->不是单个字段
            JSONObject after_medical_treatment = JSONObject.fromObject(list.get(0).getAfter_medical_treatment());
            json_template.put("after_medical_treatment", after_medical_treatment);

            //对after_treatment_drug_usage字段进行转换-->不是单个字段
            JSONObject after_treatment_drug_usage = JSONObject.fromObject(list.get(0).getAfter_treatment_drug_usage());
            json_template.put("after_treatment_drug_usage", after_treatment_drug_usage);

            //对discharge_criteria字段进行转换-->是单个字段-->为一个数组
            JSONArray discharge_criteria = JSONArray.fromObject(list.get(0).getDischarge_criteria());
            json_template.put("discharge_criteria", discharge_criteria);

            //对other_notice字段进行转换-->是单个字段-->为一个数组
            JSONArray other_notice = JSONArray.fromObject(list.get(0).getOther_notice());
            json_template.put("other_notice", other_notice);
            redisService.set(BPMN+":"+"selectByPathway_id:"+pathway_id,json_template.toString());

        }else{//redis中存在数值直接拿出,无需去查询数据库
            json_template = JSONObject.fromObject(redisService.get(BPMN + ":" + "selectByPathway_id:" + pathway_id));
            return json_template;
        }

        return json_template;
    }
}
