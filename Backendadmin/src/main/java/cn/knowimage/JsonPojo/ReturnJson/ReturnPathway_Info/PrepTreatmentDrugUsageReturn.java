package cn.knowimage.JsonPojo.ReturnJson.ReturnPathway_Info;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Mr.G
 * @date 2019/8/12
 * 数据回显功能实现方法
 * 将数据库中prep_treatment_drug_usage字段格式转换为前台能够识别的数据格式
 * 其中下划线变量对应数据库原字段，无需更改
 */
public class PrepTreatmentDrugUsageReturn {
    public static String prep_treatment_drug_usage(String prep_treatment_drug_usage) {
        //定义需要存放三个大数组的总对象
        JSONObject object = new JSONObject();
        //定义总对象，获取字符串
        JSONObject prep_treatment_drug_usage_s = JSONObject.fromObject(prep_treatment_drug_usage);
        //分别获取总对象中的三个子对象
        JSONObject antibio_usage_s = prep_treatment_drug_usage_s.getJSONObject("antibio_usage");
        JSONObject anaesthetic_usage_s = prep_treatment_drug_usage_s.getJSONObject("anaesthetic_usage");
        JSONObject otherdrugs_usage_s = prep_treatment_drug_usage_s.getJSONObject("otherdrugs_usage");
        //定义三个大数组，分别获取对应的对象值
        JSONArray prep_treatmentAntibio_usage = new JSONArray();
        JSONArray prep_treatmentAnaesthetic_usage = new JSONArray();
        JSONArray prep_treatmentOtherdrugs_usage = new JSONArray();
        //如果antibio_usage为默认值，既num==0
        if ("0".equals(antibio_usage_s.getString("num"))){
            JSONObject jsonObject = new JSONObject();
            //定义该对象，依次获取数组id_0中的value值
            jsonObject.put("value","");
            //将每个jsonObject对象依次放入prep_treatmentAntibio_usage数组中
          //  prep_treatmentAntibio_usage.add(jsonObject);
        }else {
            //prep_treatmentAntibio_usage
            for (int i = 0; i < antibio_usage_s.getInt("num"); i++) {
                JSONObject jsonObject = new JSONObject();
                //定义该对象，依次获取数组id_0中的value值
                jsonObject.put("value", antibio_usage_s.getJSONArray("id_0").getString(i));
                //将每个jsonObject对象依次放入prep_treatmentAntibio_usage数组中
                prep_treatmentAntibio_usage.add(jsonObject);
            }
        }
        //将prep_treatmentAntibio_usage数组放入一个大对象中
        object.put("prep_treatmentAntibio_usage", prep_treatmentAntibio_usage);

        //如果anaesthetic_usage为默认值，既num==0
        if ("0".equals(anaesthetic_usage_s.getString("num"))){
            JSONObject jsonObject = new JSONObject();
            //定义该对象，依次获取数组id_0中的value值
            jsonObject.put("value","");
            //将每个jsonObject对象依次放入prep_treatmentAnaesthetic_usage数组中
            //prep_treatmentAnaesthetic_usage.add(jsonObject);
        }else {
            //prep_treatmentAnaesthetic_usage
            for (int i = 0; i < anaesthetic_usage_s.getInt("num"); i++) {
                JSONObject jsonObject = new JSONObject();
                //定义该对象，依次获取数组id_0中的value值
                jsonObject.put("value", anaesthetic_usage_s.getJSONArray("id_0").getString(i));
                //将每个jsonObject对象依次放入prep_treatmentAnaesthetic_usage数组中
                prep_treatmentAnaesthetic_usage.add(jsonObject);
            }
        }
        //将prep_treatmentAnaesthetic_usage数组放入一个大对象中
        object.put("prep_treatmentAnaesthetic_usage", prep_treatmentAnaesthetic_usage);

        //如果otherdrugs_usage为默认值，既num==0
        if ("0".equals(otherdrugs_usage_s.getString("num"))){
            JSONObject jsonObject = new JSONObject();
            //定义该对象，依次获取数组id_0中的value值
            jsonObject.put("value","");
            //将每个jsonObject对象依次放入prep_treatmentAnaesthetic_usage数组中
           // prep_treatmentOtherdrugs_usage.add(jsonObject);
        }else {
            //prep_treatmentOtherdrugs_usage
            for (int i = 0; i < otherdrugs_usage_s.getInt("num"); i++) {
                JSONObject jsonObject = new JSONObject();
                //定义该对象，依次获取数组id_0中的value值
                jsonObject.put("value", otherdrugs_usage_s.getJSONArray("id_0").getString(i));
                //将每个jsonObject对象依次放入prep_treatmentOtherdrugs_usage数组中
                prep_treatmentOtherdrugs_usage.add(jsonObject);
            }
        }
        //将prep_treatmentAnaesthetic_usage数组放入一个大对象中
        object.put("prep_treatmentOtherdrugs_usage", prep_treatmentOtherdrugs_usage);
        return object.toString();
    }
}
