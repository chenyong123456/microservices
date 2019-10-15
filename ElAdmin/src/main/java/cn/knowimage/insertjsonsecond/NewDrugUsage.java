package cn.knowimage.insertjsonsecond;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Mr.G
 * @date 2019/8/12
 * 将前端输入的drug_usage字段格式修改成数据库规定的json格式
 * 注意id_分配的地址！！！！！，防止被覆盖
 */
public class NewDrugUsage {
    public static JSONObject drug_usage(String drug_usageAntibio_usage, String drug_usageAnaesthetic_usage, String drug_usageOtherdrugs_usage) {
        //定义总对象
        JSONObject drug_usage = new JSONObject();
        //定义三个数组分别存放输入的三个字符串
        JSONArray drug_usageAntibio_usage_s = JSONArray.fromObject(drug_usageAntibio_usage);
        JSONArray drug_usageAnaesthetic_usage_s = JSONArray.fromObject(drug_usageAnaesthetic_usage);
        JSONArray drug_usageOtherdrugs_usage_s = JSONArray.fromObject(drug_usageOtherdrugs_usage);

        //如果drug_usageAntibio_usage_s数组为空，则创建一个标准格式的全是空值的对象
        if (drug_usageAntibio_usage_s==null||drug_usageAntibio_usage_s.size()==0){
            JSONObject antibio_usage = new JSONObject();
            JSONArray id_0 = new JSONArray();
            id_0.add("");
            antibio_usage.put("num",1);
            antibio_usage.put("id_0",id_0);
            drug_usage.put("antibio_usage", antibio_usage);
        }else {
            //antibio_usage
            //定义一个对象，用来存放对应的num和id_
            JSONObject antibio_usage = new JSONObject();
            //定义id_数组，存放值
            JSONArray id_ = new JSONArray();
            //通过数组长度获取num
            int antibio_usage_num = drug_usageAntibio_usage_s.size();
            antibio_usage.put("num", antibio_usage_num);
            //根据获取的num循环
            for (int i = 0; i < antibio_usage_num; i++) {
                //依次获取数组中的第i个对象
                JSONObject jsonObject = drug_usageAntibio_usage_s.getJSONObject(i);
                id_.add(jsonObject.getString("value"));
            }
            antibio_usage.put("id_0", id_);
            drug_usage.put("antibio_usage", antibio_usage);
        }

        if (drug_usageAnaesthetic_usage_s==null||drug_usageAnaesthetic_usage_s.size()==0){
            JSONObject anaesthetic_usage = new JSONObject();
            JSONArray id_0 = new JSONArray();
            id_0.add("");
            anaesthetic_usage.put("num",1);
            anaesthetic_usage.put("id_0",id_0);
            drug_usage.put("anaesthetic_usage", anaesthetic_usage);
        }else {
            //anaesthetic_usage
            //定义一个对象，用来存放对应的num和id_
            JSONObject anaesthetic_usage = new JSONObject();
            //新建id_1，分配新的id_地址，防止被前面已定义的id_覆盖
            JSONArray id_1 = new JSONArray();
            //通过数组长度获取num
            int anaesthetic_usage_num = drug_usageAnaesthetic_usage_s.size();
            anaesthetic_usage.put("num", anaesthetic_usage_num);
            for (int i = 0; i < anaesthetic_usage_num; i++) {
                //创建jsonObject对象获取drug_usageAnaesthetic_usage_s数组下的第i个对象，方便后面的调用
                JSONObject jsonObject = drug_usageAnaesthetic_usage_s.getJSONObject(i);
                id_1.add(jsonObject.getString("value"));
            }
            anaesthetic_usage.put("id_0", id_1);
            drug_usage.put("anaesthetic_usage", anaesthetic_usage);
        }

       if (drug_usageOtherdrugs_usage_s==null||drug_usageOtherdrugs_usage_s.size()==0){
           JSONObject otherdrugs_usage = new JSONObject();
           JSONArray id_0 = new JSONArray();
           id_0.add("");
           otherdrugs_usage.put("num",1);
           otherdrugs_usage.put("id_0",id_0);
           drug_usage.put("otherdrugs_usage", otherdrugs_usage);
       }else {
           //otherdrugs_usage
           //定义一个对象，用来存放对应的num和id_
           JSONObject otherdrugs_usage = new JSONObject();
           //新建id_2，分配新的id_地址，防止被前面已定义的id_覆盖
           JSONArray id_2 = new JSONArray();
           //通过数组长度获取num
           int otherdrugs_usage_num = drug_usageOtherdrugs_usage_s.size();
           otherdrugs_usage.put("num", otherdrugs_usage_num);
           for (int i = 0; i < otherdrugs_usage_num; i++) {
               JSONObject jsonObject = drug_usageOtherdrugs_usage_s.getJSONObject(i);
               id_2.add(jsonObject.getString("value"));
           }
           otherdrugs_usage.put("id_0", id_2);
           drug_usage.put("otherdrugs_usage", otherdrugs_usage);
       }
        return drug_usage;
    }
}
