package cn.knowimage.pathcombine;

import cn.knowimage.util.HttpClientUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.*;

@SuppressWarnings("AlibabaRemoveCommentedCode")
public class TestMerge {
    // 用LinkedHashSet根据插入的顺序来维护集合的链接表
    private static Set mergeOptionalSet = new LinkedHashSet();
    private static Set mergeObligatorySet = new LinkedHashSet();

    public static void main(String[] args) {
        Map<String,String > map1 = new HashMap<String, String>();
        Map<String,String > map2 = new HashMap<String, String>();
        map1.put("pathway_name","小儿气管（支气管）异物");
        map2.put("pathway_name", "性早熟");
        // 拿到两条数据
        String s1 = HttpClientUtil.doGet("http://192.168.50.118:8088/returnString", map1);
        JSONObject bpmnJson1 = JSONObject.fromObject(s1);
        String s2 = HttpClientUtil.doGet("http://192.168.50.118:8088/returnString", map2);
        JSONObject bpmnJson2 = JSONObject.fromObject(s2);
//        System.out.println(s1 + "\n" + s2);

        JSONArray bpmn1_optional_exam = bpmnJson1.getJSONObject("prep_treatment_common").getJSONObject("scenario").getJSONObject("id_0").getJSONArray("optional_exam");
        JSONArray bpmn1_obligatory_exam = bpmnJson1.getJSONObject("prep_treatment_common").getJSONObject("scenario").getJSONObject("id_0").getJSONArray("obligatory_exam");
        JSONArray bpmn2_optional_exam = bpmnJson2.getJSONObject("prep_treatment_common").getJSONObject("scenario").getJSONObject("id_0").getJSONArray("optional_exam");
        JSONArray bpmn2_obligatory_exam = bpmnJson2.getJSONObject("prep_treatment_common").getJSONObject("scenario").getJSONObject("id_0").getJSONArray("obligatory_exam");

//        System.out.println(bpmn1_optional_exam + "\n" + bpmn2_optional_exam);
        System.out.println(bpmn1_obligatory_exam + "\n" + bpmn2_obligatory_exam);

        for (int i = 0; i < bpmn1_optional_exam.size(); i++) {
            String[] strArray = bpmn1_optional_exam.get(i).toString().split(":");
            mergeOptionalSet.add(strArray[1]);
        }
        for (int i = 0; i < bpmn2_optional_exam.size(); i++) {
            String[] strArray = bpmn2_optional_exam.get(i).toString().split(":");
            mergeOptionalSet.add(strArray[1]);
        }

        for (int i = 0; i < bpmn1_obligatory_exam.size(); i++) {
            String[] strArray = bpmn1_obligatory_exam.get(i).toString().split(":");
            mergeObligatorySet.add(strArray[1]);
        }
        for (int i = 0; i < bpmn2_obligatory_exam.size(); i++) {
            String[] strArray = bpmn2_obligatory_exam.get(i).toString().split(":");
            mergeObligatorySet.add(strArray[1]);
        }
        System.out.println(mergeOptionalSet);
        System.out.println(mergeObligatorySet);
    }
}
