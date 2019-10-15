package cn.knowimage.jsonoutput;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Mr.G
 * @date 2019/8/12
 * 数据回显功能实现方法
 * 将数据库中prep_treatment_extension字段格式转换为前台能够识别的数据格式
 * 其中下划线变量对应数据库原字段，无需更改
 */
public class PrepTreatmentExtensionReturn {
    public static String prep_treatment_extension(String prep_treatment_extension) {
        //定义总对象，获取输入的字符串
        JSONObject prep_treatment_extension_s = JSONObject.fromObject(prep_treatment_extension);
        //定义总数组，存放所有的输出对象
        JSONArray prep_treatment_extension_num = new JSONArray();
        //获取对象中的num值进行循环
        for (int i = 0; i < prep_treatment_extension_s.getInt("num"); i++) {
            //依次获取id_0对象
            JSONObject jsonObject = prep_treatment_extension_s.getJSONObject(String.format("id_%d", i));
            //定义该对象，用于存放输出content和content_item值
            JSONObject object = new JSONObject();
            //用于存放输出的content_item数组
            JSONArray content_item = new JSONArray();
            object.put("content", jsonObject.getString("content"));
            //获取content_item数组
            JSONArray content_item_s = jsonObject.getJSONArray("content_item");
            //根据获得的content_item数组大小进行循环
            for (int j = 0; j < content_item_s.size(); j++) {
                JSONObject value = new JSONObject();
                //依次获取content_item数组中的第j个string值
                value.put("value", content_item_s.getString(j));
                //依次将value放入数组content_item
                content_item.add(value);
            }
            //将数组content_item放入对象object
            object.put("content_item", content_item);
            //将全部object对象放入prep_treatment_extension_num数组中
            prep_treatment_extension_num.add(object);
        }
        return prep_treatment_extension_num.toString();
    }
}
