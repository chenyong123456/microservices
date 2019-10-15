package cn.knowimage.JsonPojo.MakeJson.MakePathway_Info;

import cn.knowimage.pojo.ReceivePathway;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MakeTreatmentEntryStandard {
    public static String make(ReceivePathway receivePathway) {
        String treatment_entry_standard = receivePathway.getTreatment_entry_standard();
        //定义总对象
        JSONObject Treatment_Entry_Standar = new JSONObject();
        //将输入的字符串压入数组treatment_entry_standard_s
        JSONArray treatment_entry_standard_s = JSONArray.fromObject(treatment_entry_standard);
        //输入的treatment_entry_standard_s为空，则需创建数据库的标准格式，所有值为空
        if (treatment_entry_standard_s.getJSONObject(0).getString("value")==null||"".equals(treatment_entry_standard_s.getJSONObject(0).getString("value"))){
            Treatment_Entry_Standar.put("num",0);
        }else {
            //获取数组长度
            int num = treatment_entry_standard_s.size();
            Treatment_Entry_Standar.put("num", num);
            for (int i = 0; i < num; i++) {
                //获取treatment_entry_standard_s数组下的第i个json对象
                JSONObject jsonObject = treatment_entry_standard_s.getJSONObject(i);
                //将jsonObject对象中的value值动态的赋给id_x对象
                Treatment_Entry_Standar.put(String.format("id_%d", i), jsonObject.getString("value"));
            }
        }


        return Treatment_Entry_Standar.toString();
    }
}