package cn.knowimage.JsonPojo.MakeJson.MakePathway_Info;

import cn.knowimage.pojo.ReceivePathway;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MakeAfterTreatmentDrugUsage {
    public static String make(ReceivePathway receivePathway){
        String after_treatmentAntibio_usage = receivePathway.getAfter_treatmentAntibio_usage();
        String after_treatmentAnaesthetic_usage = receivePathway.getAfter_treatmentAnaesthetic_usage();
        String after_treatmentOtherdrugs_usage = receivePathway.getAfter_treatmentOtherdrugs_usage();
        //定义总对象，将后续创建的对象全部放入其中
        JSONObject after_treatment_drug_usageJson = new JSONObject();
        //定义三个数组，用于存放输入的三个字符串
        JSONArray after_treatmentAntibio_usage_s = JSONArray.fromObject(after_treatmentAntibio_usage);
        JSONArray after_treatmentAnaesthetic_usage_s = JSONArray.fromObject(after_treatmentAnaesthetic_usage);
        JSONArray after_treatmentOtherdrugs_usage_s = JSONArray.fromObject(after_treatmentOtherdrugs_usage);

        if ((after_treatmentAntibio_usage_s.size() == 0)){
            JSONObject antibio_usage = new JSONObject();
            JSONArray id_0 = new JSONArray();
            antibio_usage.put("num",0);
            antibio_usage.put("id_0",id_0);
            after_treatment_drug_usageJson.put("antibio_usage", antibio_usage);
        }else {
            //定义一个对象，用来存放对应的num和id_
            JSONObject antibio_usage = new JSONObject();
            //定义id_数组，存放值
            JSONArray id_ = new JSONArray();
            //通过数组长度获取num
            int antibio_usage_num = after_treatmentAntibio_usage_s.size();
            antibio_usage.put("num", antibio_usage_num);
            //根据获取的num循环
            for (int i = 0; i < antibio_usage_num; i++) {
                //依次获取数组中的第i个对象
                JSONObject jsonObject = after_treatmentAntibio_usage_s.getJSONObject(i);
                id_.add(jsonObject.getString("value"));
            }
            antibio_usage.put("id_0", id_);
            after_treatment_drug_usageJson.put("antibio_usage", antibio_usage);
        }


        if ((after_treatmentAnaesthetic_usage_s.size() == 0)){
            JSONObject anaesthetic_usage = new JSONObject();
            JSONArray id_0 = new JSONArray();
            anaesthetic_usage.put("num",0);
            anaesthetic_usage.put("id_0",id_0);
            after_treatment_drug_usageJson.put("anaesthetic_usage", anaesthetic_usage);
        }else {
            //anaesthetic_usage
            //定义一个对象，用来存放对应的num和id_
            JSONObject anaesthetic_usage = new JSONObject();
            //新建id_1，分配新的id_地址，防止被前面已定义的id_覆盖
            JSONArray id_1 = new JSONArray();
            //通过数组长度获取num
            int anaesthetic_usage_num = after_treatmentAnaesthetic_usage_s.size();
            anaesthetic_usage.put("num", anaesthetic_usage_num);
            for (int i = 0; i < anaesthetic_usage_num; i++) {
                //定义jsonObject对象获取after_treatmentAnaesthetic_usage_s数组下的第i个对象
                JSONObject jsonObject = after_treatmentAnaesthetic_usage_s.getJSONObject(i);
                id_1.add(jsonObject.getString("value"));
            }
            anaesthetic_usage.put("id_0", id_1);
            after_treatment_drug_usageJson.put("anaesthetic_usage", anaesthetic_usage);
        }

        if ((after_treatmentOtherdrugs_usage_s.size() == 0)){
            JSONObject otherdrugs_usage = new JSONObject();
            JSONArray id_0 = new JSONArray();
            otherdrugs_usage.put("num",0);
            otherdrugs_usage.put("id_0",id_0);
            after_treatment_drug_usageJson.put("otherdrugs_usage", otherdrugs_usage);
        }else {
            //otherdrugs_usage
            //定义一个对象，用来存放对应的num和id_
            JSONObject otherdrugs_usage = new JSONObject();
            //新建id_2，分配新的id_地址，防止被前面已定义的id_覆盖
            JSONArray id_2 = new JSONArray();
            //通过数组长度获取num
            int otherdrugs_usage_num = after_treatmentOtherdrugs_usage_s.size();
            otherdrugs_usage.put("num", otherdrugs_usage_num);
            for (int i = 0; i < otherdrugs_usage_num; i++) {
                JSONObject jsonObject = after_treatmentOtherdrugs_usage_s.getJSONObject(i);
                id_2.add(jsonObject.getString("value"));
            }
            otherdrugs_usage.put("id_0", id_2);
            after_treatment_drug_usageJson.put("otherdrugs_usage", otherdrugs_usage);
        }
        return after_treatment_drug_usageJson.toString();
    }

}
