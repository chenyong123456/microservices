package cn.knowimage.JsonPojo.MakeJson.MakePathway_Info;

import cn.knowimage.pojo.ReceivePathway;
import net.sf.json.JSONArray;

/**
 * 变异分析
 */
public class MakeOtherNotice {
    public static String make(ReceivePathway receivePathway) {
        String other_notice = receivePathway.getOther_notice();
        //创建other_notice_s数组，存放输入的字符串
        JSONArray other_notice_s = JSONArray.fromObject(other_notice);
        //创建该数组用于存放输出的数据
        JSONArray other_notice_item = new JSONArray();
        if ("".equals(other_notice_s.getJSONObject(0).getString("value"))){
        }else {
            for (int i = 0; i < other_notice_s.size(); i++) {
                //获取other_notice_s数组下的第i个对象的value值，通过键值对形式获取字符串
                other_notice_item.add(other_notice_s.getJSONObject(i).getString("value"));
            }
        }
        return other_notice_item.toString();
    }
}
