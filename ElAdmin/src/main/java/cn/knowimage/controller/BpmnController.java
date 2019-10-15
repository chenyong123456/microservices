package cn.knowimage.controller;

import cn.knowimage.service.BpmnService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 功能:对数据库中的表pathway_info中的数据(String)进行转换为JSONObject
 * @author 啊勇
 */

/**
 * 功能:对bpmn.js提供数据接口
 * @author 啊勇
 */
@CrossOrigin
@Controller
public class BpmnController {
	
	@Autowired
	public BpmnService bpmnService;

	//小栗子的根据pathway_id对表pathway_Id进行查询一条记录
	@RequestMapping("pathway_InfoToPathway_id")
	@ResponseBody
	public JSONObject  SelectBy_OnlyPathway_id(String pathway_Id) {
		System.out.println(pathway_Id);

		//最终的所有json对象！
		JSONObject	json_template = bpmnService.selectByPathway_id(pathway_Id);
		/*
		Integer pathway_ids = Integer.parseInt(pathway_Id);
		//根据pathway_Id进行查询一条数据
		List<Pathway_Info> list = pathway_InfoService.selectByPathway_id(pathway_ids);

		//pathway_index字段的处理
		String pathway_index = list.get(0).getPathway_index();
		//返回从起始位置（beginIndex）到目标位置（endIndex）之间的字符串，但不包含目标位置（endIndex）的字符[)
		String pathway_fabu = pathway_index.substring(0, 2);
		String pathway_year = pathway_index.substring(2, 6);
		String pathway_Xuhao = pathway_index.substring(6, 10);
		String pathway_Banben = pathway_index.substring(10);
		json_template.put("pathway_fabu", pathway_fabu);
		json_template.put("pathway_year", pathway_year);
		json_template.put("pathway_Xuhao", pathway_Xuhao);
		json_template.put("pathway_Banben", pathway_Banben);
		*/
		return json_template;
	}
}
