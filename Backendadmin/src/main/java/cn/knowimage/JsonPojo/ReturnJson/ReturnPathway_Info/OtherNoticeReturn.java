package cn.knowimage.JsonPojo.ReturnJson.ReturnPathway_Info;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Mr.G
 * @date 2019/8/12
 * 数据回显功能实现方法
 * 将数据库中other_notice字段格式转换为前台能够识别的数据格式
 * 其中下划线变量对应数据库原字段，无需更改
 */
public class OtherNoticeReturn {
    public static String other_notice(String other_notice) {
        //该数组获取输入的字符串
        JSONArray other_notice_s = JSONArray.fromObject(other_notice);
        //定义该数组，用于存放输出的value对象
        JSONArray other_notice_num = new JSONArray();
        //如果数据库字段为默认字段
        if (other_notice_s.size()==0){
            JSONObject value = new JSONObject();
            //依次获取数组discharge_criteria_s中的第i个字符串
            value.put("value", "");
            other_notice_num.add(value);
        }else {
            //根据数组的长度循环
            for (int i = 0; i < other_notice_s.size(); i++) {
                JSONObject value = new JSONObject();
                //依次获取数组discharge_criteria_s中的第i个字符串
                value.put("value", other_notice_s.getString(i));
                other_notice_num.add(value);
            }
        }
        return other_notice_num.toString();
    }
}
