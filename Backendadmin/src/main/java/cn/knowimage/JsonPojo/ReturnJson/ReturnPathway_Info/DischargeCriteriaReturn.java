package cn.knowimage.JsonPojo.ReturnJson.ReturnPathway_Info;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Mr.G
 * @date 2019/8/12
 * 数据回显功能实现方法
 * 将数据库中discharge_criteria字段格式转换为前台能够识别的数据格式
 * 其中下划线变量对应数据库原字段，无需更改
 */
public class DischargeCriteriaReturn {
    public static String discharge_criteria(String discharge_criteria) {
        //该数组获取输入的字符串
        JSONArray discharge_criteria_s = JSONArray.fromObject(discharge_criteria);
        //定义该数组，用于存放输出的value对象
        JSONArray discharge_criteria_num = new JSONArray();
        //如果数据库字段为默认字段
        if (discharge_criteria_s.size()==0){
            JSONObject value = new JSONObject();
            //依次获取数组discharge_criteria_s中的第i个字符串
            value.put("value","");
            discharge_criteria_num.add(value);
        }else {
            //根据数组的长度循环
            for (int i = 0; i < discharge_criteria_s.size(); i++) {
                JSONObject value = new JSONObject();
                //依次获取数组discharge_criteria_s中的第i个字符串
                value.put("value", discharge_criteria_s.get(i));
                discharge_criteria_num.add(value);
            }
        }
        return discharge_criteria_num.toString();
    }
}
