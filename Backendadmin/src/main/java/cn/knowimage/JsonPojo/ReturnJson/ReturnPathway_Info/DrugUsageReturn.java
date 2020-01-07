package cn.knowimage.JsonPojo.ReturnJson.ReturnPathway_Info;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Mr.G
 * @date 2019/8/12
 * 数据回显功能实现方法
 * 将数据库中drug_usage字段格式转换为前台能够识别的数据格式
 * 其中下划线变量对应数据库原字段，无需更改
 */
public class DrugUsageReturn {
    public static String drug_usage(String drug_usage) {
        //定义总对象存放全部的输出数组
        JSONObject jsonObject = new JSONObject();
        //定义总对象，获取输入的三个数组
        JSONObject drug_usage_s = JSONObject.fromObject(drug_usage);
        //定义该数组，获取输出的值
        JSONArray drug_usageAntibio_usage = new JSONArray();
        //获取总对象drug_usage_s下的子对象antibio_usage
        JSONObject antibio_usage = drug_usage_s.getJSONObject("antibio_usage");
        //如果数据库中为默认值，既为空
        if ("0".equals(antibio_usage.getString("num"))){
            JSONObject value = new JSONObject();
            value.put("value","");
            //drug_usageAntibio_usage.add(value);
        }else {
            //获取对象antibio_usage中的num值
            for (int i = 0; i < antibio_usage.getInt("num"); i++) {
                JSONObject value = new JSONObject();
                //将antibio_usage对象下的名为id_0的数组中，第i个字符串放入value对象中
                value.put("value", antibio_usage.getJSONArray("id_0").getString(i));
                drug_usageAntibio_usage.add(value);
            }
        }
        jsonObject.put("drug_usageAntibio_usage", drug_usageAntibio_usage);

        //定义该数组，获取输出的值
        JSONArray drug_usageAnaesthetic_usage = new JSONArray();
        //获取总对象drug_usage_s下的子对象anaesthetic_usage
        JSONObject anaesthetic_usage = drug_usage_s.getJSONObject("anaesthetic_usage");
        //如果数据库中为默认值，既为空
        if ("0".equals(anaesthetic_usage.getString("num"))){
            JSONObject value = new JSONObject();
            value.put("value","");
            //drug_usageAnaesthetic_usage.add(value);
        }else {
            //获取对象anaesthetic_usage中的num值
            for (int i = 0; i < anaesthetic_usage.getInt("num"); i++) {
                JSONObject value = new JSONObject();
                //获取anaesthetic_usage对象下的id_0数组中的第i个字符串值，放入value对象中
                value.put("value", anaesthetic_usage.getJSONArray("id_0").getString(i));
                drug_usageAnaesthetic_usage.add(value);
            }
        }
        jsonObject.put("drug_usageAnaesthetic_usage", drug_usageAnaesthetic_usage);

        //定义该数组，获取输出的值
        JSONArray drug_usageOtherdrugs_usage = new JSONArray();
        //获取总对象drug_usage_s下的子对象otherdrugs_usage
        JSONObject otherdrugs_usage = drug_usage_s.getJSONObject("otherdrugs_usage");
        //如果数据库中为默认值，既为空
        if ("0".equals(otherdrugs_usage.getString("num"))){
            JSONObject value = new JSONObject();
            value.put("value","");
            //drug_usageOtherdrugs_usage.add(value);
        }else {
            //获取对象otherdrugs_usage中的num值
            for (int i = 0; i < otherdrugs_usage.getInt("num"); i++) {
                JSONObject value = new JSONObject();
                //获取anaesthetic_usage对象下的id_0数组中的第i个字符串值，放入value对象中
                value.put("value", otherdrugs_usage.getJSONArray("id_0").getString(i));
                drug_usageOtherdrugs_usage.add(value);
            }
        }
        jsonObject.put("drug_usageOtherdrugs_usage", drug_usageOtherdrugs_usage);
        return jsonObject.toString();
    }
}
