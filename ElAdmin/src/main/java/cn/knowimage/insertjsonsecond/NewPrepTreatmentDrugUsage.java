package cn.knowimage.insertjsonsecond;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Mr.G
 * @date 2019/8/12
 * 将前端输入的Prep_Treatment_Drug_Usage字段格式修改成数据库规定的json格式
 * 之前问题已解决，问题原因，由于只创建了一个id_对象，其中有三次都对id_对象进行操作，所以被覆盖了
 * 解决方法，新开辟id_1,id_2空间
 */
public class NewPrepTreatmentDrugUsage {
    public static JSONObject Prep_Treatment_Drug_Usage(String prep_treatmentAntibio_usage, String prep_treatmentAnaesthetic_usage,
                                                       String prep_treatmentOtherdrugs_usage) {

        //定义总对象
        JSONObject Treatment_Drug_Usage = new JSONObject();
        //定义三个数组 将传来的字符串全部压入数组
        JSONArray prep_treatmentAntibio_usage_s = JSONArray.fromObject(prep_treatmentAntibio_usage);
        JSONArray prep_treatmentAnaesthetic_usage_s = JSONArray.fromObject(prep_treatmentAnaesthetic_usage);
        JSONArray prep_treatmentOtherdrugs_usage_s = JSONArray.fromObject(prep_treatmentOtherdrugs_usage);

        //如果prep_treatmentAntibio_usage_s数组为空，则仍需创建数据库标准格式数据，值为空
        if (prep_treatmentAntibio_usage_s==null||prep_treatmentAntibio_usage_s.size()==0){
            JSONObject antibio_usage = new JSONObject();
            JSONArray id_0 = new JSONArray();
            id_0.add("");
            antibio_usage.put("num",1);
            antibio_usage.put("id_0",id_0);
            Treatment_Drug_Usage.put("antibio_usage", antibio_usage);
        }else{
            //prep_treatmentAntibio_usage
            JSONArray id_ = new JSONArray();
            //定义antibio_usage对象，等待存放值
            JSONObject antibio_usage = new JSONObject();
            //获取输入的数组长度
            antibio_usage.put("num", prep_treatmentAntibio_usage_s.size());
            //根据输入的数组长度循环判断
            for (int i = 0; i < prep_treatmentAntibio_usage_s.size(); i++) {
                //定义jsonObject对象获取prep_treatmentAntibio_usage_s数组下的第i个对象，方便调用
                JSONObject jsonObject = prep_treatmentAntibio_usage_s.getJSONObject(i);
                id_.add(jsonObject.getString("value"));
            }
            antibio_usage.put("id_0", id_);
            Treatment_Drug_Usage.put("antibio_usage", antibio_usage);
        }

        if(prep_treatmentAnaesthetic_usage_s==null||prep_treatmentAnaesthetic_usage_s.size()==0){
            JSONObject anaesthetic_usage = new JSONObject();
            JSONArray id_0 = new JSONArray();
            id_0.add("");
            anaesthetic_usage.put("num",1);
            anaesthetic_usage.put("id_0",id_0);
            Treatment_Drug_Usage.put("anaesthetic_usage", anaesthetic_usage);
        }else {
            //prep_treatmentAnaesthetic_usage
            JSONArray id_1 = new JSONArray();
            //定义antibio_usage对象，等待存放值
            JSONObject anaesthetic_usage = new JSONObject();
            //获取输入的数组长度
            anaesthetic_usage.put("num", prep_treatmentAnaesthetic_usage_s.size());
            //根据输入的数组长度循环判断
            for (int i = 0; i < prep_treatmentAnaesthetic_usage_s.size(); i++) {
                //定义jsonObject对象获取prep_treatmentAnaesthetic_usage_s数组下的第i个对象，方便调用
                JSONObject jsonObject = prep_treatmentAnaesthetic_usage_s.getJSONObject(i);
                id_1.add(jsonObject.getString("value"));
            }
            anaesthetic_usage.put("id_0", id_1);
            Treatment_Drug_Usage.put("anaesthetic_usage", anaesthetic_usage);
        }

        if (prep_treatmentOtherdrugs_usage_s==null||prep_treatmentOtherdrugs_usage_s.size()==0){
            JSONObject otherdrugs_usage = new JSONObject();
            JSONArray id_0 = new JSONArray();
            id_0.add("");
            otherdrugs_usage.put("num",1);
            otherdrugs_usage.put("id_0",id_0);
            Treatment_Drug_Usage.put("otherdrugs_usage", otherdrugs_usage);
        }else {
            //prep_treatmentOtherdrugs_usage
            JSONArray id_2 = new JSONArray();
            JSONObject otherdrugs_usage = new JSONObject();
            otherdrugs_usage.put("num", prep_treatmentOtherdrugs_usage_s.size());
            for (int i = 0; i < prep_treatmentOtherdrugs_usage_s.size(); i++) {
                //定义jsonObject对象获取prep_treatmentOtherdrugs_usage_s数组下的第i个对象，方便调用
                JSONObject jsonObject = prep_treatmentOtherdrugs_usage_s.getJSONObject(i);
                id_2.add(jsonObject.getString("value"));
            }
            otherdrugs_usage.put("id_0", id_2);
            Treatment_Drug_Usage.put("otherdrugs_usage", otherdrugs_usage);
        }


        return Treatment_Drug_Usage;
    }
}
