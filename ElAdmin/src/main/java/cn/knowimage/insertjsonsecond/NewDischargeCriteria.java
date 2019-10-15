package cn.knowimage.insertjsonsecond;

import net.sf.json.JSONArray;

/**
 * @author Mr.G
 * @date 2019/8/12
 * 将前端输入的discharge_criteria字段格式修改成数据库规定的json格式
 */
public class NewDischargeCriteria {
    public static JSONArray discharge_criteria(String discharge_criteria) {
        //将输入的discharge_criteria字符串压入json数组
        JSONArray discharge_criteria_s = JSONArray.fromObject(discharge_criteria);
        //创建新数组，存放输出的数据
        JSONArray discharge_criteria_item = new JSONArray();
        //如果输入的discharge_criteria_s数组为空，则需创建数据库标准格式数据，值为空
        if (discharge_criteria_s==null||discharge_criteria_s.size()==0){
            discharge_criteria_item.add("");
        }else {
            for (int i = 0; i < discharge_criteria_s.size(); i++) {
                //获取discharge_criteria_s下的第i个json对象的value值，通过键值对形式获取字符串
                discharge_criteria_item.add(discharge_criteria_s.getJSONObject(i).getString("value"));
            }
        }
        return discharge_criteria_item;
    }
}
