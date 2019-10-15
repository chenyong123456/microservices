package cn.knowimage.insertjsonsecond;

import net.sf.json.JSONArray;

/**
 * @author Mr.G
 * @date 2019/8/12
 * 将前端输入的other_notice字段格式修改成数据库规定的json格式
 */
public class NewOtherNotice {
    public static JSONArray other_notice(String other_notice) {
        //创建other_notice_s数组，存放输入的字符串
        JSONArray other_notice_s = JSONArray.fromObject(other_notice);
        //创建该数组用于存放输出的数据
        JSONArray other_notice_item = new JSONArray();
        if (other_notice_s==null||other_notice_s.size()==0){
            other_notice_item.add("");
        }else {
            for (int i = 0; i < other_notice_s.size(); i++) {
                //获取other_notice_s数组下的第i个对象的value值，通过键值对形式获取字符串
                other_notice_item.add(other_notice_s.getJSONObject(i).getString("value"));
            }
        }
        return other_notice_item;
    }
}
