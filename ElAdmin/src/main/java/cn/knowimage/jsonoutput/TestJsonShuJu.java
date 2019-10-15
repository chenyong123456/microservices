package cn.knowimage.jsonoutput;
/**
 * 功能:用于测试输出json格式的正确性
 * @author 啊勇
 *
 */
public class TestJsonShuJu {
	
	public static void treatment_choice() {
		//System.out.println(Treatment_choiceOutput.treatment_choiceOut("{\"ref\": \"\", \"scenario\": {\"num\": 1, \"id_0\": [\"确保生命体征平稳\", \"开放性骨折，急诊视情况给予相应处理\", \"根据骨折情况，视情况给予相应急诊处理以及二期手术\"]}}"));
		//System.out.println(Treatment_daysOutput.treatment_days("{'scenario': {'num': 1, 'id_0': {'max': 384, 'min': 384, 'tag_name': '', 'time_unit': '小时'}}}"));
		//System.out.println(Treatment_entry_standardOutput.treatment_entry_standardOutput("{'num': 5, 'id_0': '第一诊断必须符合多部位骨折疾病编码(ICD-10：T02.3-T02.6)', 'id_1': '当患者同时具有其他疾病诊断，但在住院期间不需要特殊处理也不影响第一诊断的临床路径流程实施时，可以进入路径', 'id_2': '急诊手术的患者不进入路径', 'id_3': '需要分期手术的患者不进入路径（需2次及2次以上手术者）', 'id_4': '合并其他系统损伤的患者不进入路径'}"));
		//System.out.println(Prep_treatment_commonOutput.prep_treatment_commonOut("{\"scenario\": {\"num\": 1, \"id_0\": {\"duration\": {\"max\": 0, \"min\": 0, \"time_unit\": \"\"}, \"notification\": [], \"optional_exam\": [\"X线检查、CT、B超等\"], \"obligatory_exam\": [\"血常规、尿常规\", \"肝功能、肾功能、电解质、、血型、血糖、凝血功能、感染性疾病筛查（乙肝、丙肝、梅毒、艾滋病等）\", \"胸部X线片、心电图\"]}}}"));
		System.out.println(TreatmentOutput.treatment_commonOut("[{'treatmentPlanRef':'手术场景1的医疗方案选择依据','caseChildnum':[{'treatmentPlanContent':'医疗方案1的医疗内容','planContentItem':[{'value':'医疗内容子条目1'},{'value':'医疗内容子条目2'}]},{'treatmentPlanContent':'医疗方案2的医疗内容','planContentItem':[{'value':'医疗内容子条目1'},{'value':'医疗内容子条目2'}]}],'obligatoryExam':[{'value':'手术场景1治疗方案必须检查项目1'},{'value':'手术场景1治疗方案必须检查项目2'}],'optionalExam':[{'value':'手术场景1治疗方案可选检查项目1'},{'value':'手术场景1治疗方案可选检查项目2'}],'notification':[{'value':'手术场景1治疗方案治疗方案必要告知信息项目1'},{'value':'手术场景1治疗方案治疗方案必要告知信息项目2'}]},{'treatmentPlanRef':'手术场景2医疗方案选择依据','caseChildnum':[{'treatmentPlanContent':'手术场景2医疗方案1医疗内容','planContentItem':[{'value':'手术场景2医疗方案1疗内容子条目数 1'},{'value':'手术场景2医疗方案1疗内容子条目数 2'}]},{'treatmentPlanContent':'手术场景2医疗方案2医疗内容','planContentItem':[{'value':'手术场景2医疗方案2疗内容子条目数 1'},{'value':'手术场景2医疗方案2疗内容子条目数 2'}]}],'obligatoryExam':[{'value':'手术场景2治疗方案必须检查项目1'},{'value':'手术场景2治疗方案必须检查项目2'}],'optionalExam':[{'value':'手术场景2治疗方案可选检查项目1'},{'value':'手术场景2治疗方案可选检查项目2'}],'notification':[{'value':'手术场景2治疗方案必要告知信息1'},{'value':'手术场景2治疗方案必要告知信息2'}]}]"));
	}
	
	public static void main(String[] args) {
		treatment_choice();
	}

}
