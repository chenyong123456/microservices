package cn.knowimage.JsonPojo.MakeJson.MakePathway_Info;

import cn.knowimage.pojo.ReceivePathway;
import net.sf.json.JSONArray;

public class MakeDischargeCriteria {
    public static String make(ReceivePathway receivePathway) {
        String discharge_criteria_item = receivePathway.getDischarge_criteria();
        //将输入的discharge_criteria字符串压入json数组
        JSONArray discharge_criteria_s = JSONArray.fromObject(discharge_criteria_item);
        //创建新数组，存放输出的数据
        JSONArray discharge_criteria = new JSONArray();
        //如果输入的discharge_criteria_s数组为空，则需创建数据库标准格式数据，值为空
        if ("".equals(discharge_criteria_s.getJSONObject(0).getString("value"))){
        }else {
            for (int i = 0; i < discharge_criteria_s.size(); i++) {
                //获取discharge_criteria_s下的第i个json对象的value值，通过键值对形式获取字符串
                discharge_criteria.add(discharge_criteria_s.getJSONObject(i).getString("value"));
            }
        }
        return discharge_criteria.toString();
    }
}
