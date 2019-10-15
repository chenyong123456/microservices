package cn.knowimage.jsonoutput;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Mr.G
 * @date 2019/8/12
 * 数据回显功能实现方法
 * 将数据库中after_treatment_drug_usage字段格式转换为前台能够识别的数据格式
 * 其中下划线变量对应数据库原字段，无需更改
 */
public class AfterTreatmentDrugUsageReturn {
    public static String after_treatment_drug_usage(String after_treatment_drug_usage) {
        //将输入的字符串转为json对象
        JSONObject after_treatment_drug_usage_s = JSONObject.fromObject(after_treatment_drug_usage);
        //定义大对象，用于存放接下来生成的三个数组
        JSONObject jsonObject = new JSONObject();
        //获取after_treatment_drug_usage_s对象下的子对象antibio_usage_s
        JSONObject antibio_usage_s = after_treatment_drug_usage_s.getJSONObject("antibio_usage");
        //创建after_treatmentAntibio_usage数组，存放value对象
        JSONArray after_treatmentAntibio_usage = new JSONArray();
        //获取antibio_usage_s对象下的num值，进行循环
        for (int i = 0; i < antibio_usage_s.getInt("num"); i++) {
            JSONObject value = new JSONObject();
            //依次获取antibio_usage_s对象下的id_0数组中的值，赋给value
            value.put("value", antibio_usage_s.getJSONArray("id_0").getString(i));
            after_treatmentAntibio_usage.add(value);
        }
        jsonObject.put("after_treatmentAntibio_usage", after_treatmentAntibio_usage);
        //获取after_treatment_drug_usage_s对象下的子对象anaesthetic_usage
        JSONObject anaesthetic_usage_s = after_treatment_drug_usage_s.getJSONObject("anaesthetic_usage");
        //创建after_treatmentAnaesthetic_usage数组，存放value对象
        JSONArray after_treatmentAnaesthetic_usage = new JSONArray();
        //获取antibio_usage_s对象下的num值，进行循环
        for (int i = 0; i < anaesthetic_usage_s.getInt("num"); i++) {
            JSONObject value = new JSONObject();
            //依次获取anaesthetic_usage_s对象下的id_0数组中的值，赋给value
            value.put("value", anaesthetic_usage_s.getJSONArray("id_0").getString(i));
            after_treatmentAnaesthetic_usage.add(value);
        }
        jsonObject.put("after_treatmentAnaesthetic_usage", after_treatmentAnaesthetic_usage);

        //获取after_treatment_drug_usage_s对象下的子对象otherdrugs_usage
        JSONObject otherdrugs_usage_s = after_treatment_drug_usage_s.getJSONObject("otherdrugs_usage");
        //创建after_treatmentOtherdrugs_usage数组，存放value对象
        JSONArray after_treatmentOtherdrugs_usage = new JSONArray();

        //获取otherdrugs_usage_s对象下的num值，进行循环
        for (int i = 0; i < otherdrugs_usage_s.getInt("num"); i++) {
            JSONObject value = new JSONObject();
            //依次获取antibio_usage_s对象下的id_0数组中的值，赋给value
            value.put("value", otherdrugs_usage_s.getJSONArray("id_0").getString(i));
            after_treatmentOtherdrugs_usage.add(value);
        }
        jsonObject.put("after_treatmentOtherdrugs_usage", after_treatmentOtherdrugs_usage);
        return jsonObject.toString();
    }
}
