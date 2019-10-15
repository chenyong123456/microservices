package cn.knowimage.insertjsonsecond;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Mr.G
 * @date 2019/8/12
 * 将前端输入的Treatment_Entry_Standar字段格式修改成数据库规定的json格式
 */
public class NewTreatmentEntryStandar {
    public static JSONObject Treatment_Entry_Standar(String treatment_entry_standard) {
        //定义总对象
        JSONObject Treatment_Entry_Standar = new JSONObject();
        //将输入的字符串压入数组treatment_entry_standard_s
        JSONArray treatment_entry_standard_s = JSONArray.fromObject(treatment_entry_standard);
        //输入的treatment_entry_standard_s为空，则需创建数据库的标准格式，所有值为空
        if (treatment_entry_standard_s==null||treatment_entry_standard_s.size()==0){
            Treatment_Entry_Standar.put("num",2);
            Treatment_Entry_Standar.put("id_0","");
            Treatment_Entry_Standar.put("id_1","");
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


        return Treatment_Entry_Standar;
    }
}
