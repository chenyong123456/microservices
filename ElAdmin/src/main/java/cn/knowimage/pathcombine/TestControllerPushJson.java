package cn.knowimage.pathcombine;

import cn.knowimage.util.HttpClientUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static cn.knowimage.pathcombine.ReadPropertiesToMapTest.linkedHashMap;

@RestController
public class TestControllerPushJson {
    // 定义保存修改好的数据的容器，最后会将answerJson返还给陈勇（修改好的数据）
    private static JSONObject answerJson= new JSONObject();
    private static JSONArray after_optional_exam = new JSONArray();
    private static JSONArray after_obligatory_exam = new JSONArray();

    @RequestMapping("/updateData")
    public JSONObject updateData(String pathWay_name) throws Exception {
        ReadPropertiesToMapTest readPropertiesToMapTest = new ReadPropertiesToMapTest();
        readPropertiesToMapTest.h();

        // 调用陈勇的接口，拿到一串String格式的JSONObject对象，里面包含四个JSONArray。
        String s = HttpClientUtil.doGet("http://192.168.50.118:8088/stringSplit");

        JSONObject jsonObject = JSONObject.fromObject(s);
        System.out.println("xcsdcsdc");
        System.out.println(jsonObject);

        // 将每一大项待修改数据用JSONArray分割开；
        JSONArray optional_exam = new JSONArray();
        JSONArray obligatory_exam = new JSONArray();
        optional_exam = jsonObject.getJSONArray("optional_exam");
        obligatory_exam = jsonObject.getJSONArray("obligatory_exam");

        // 必选检查项目的数据修改
        for (int i = 0 ; i < optional_exam.size(); i++) {
            String linkedStr = linkedHashMap.get(optional_exam.get(i));
            System.out.println(linkedStr);
            if (!linkedStr.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder();
                after_optional_exam.add(stringBuilder.append(linkedStr + ":" + optional_exam.get(i)).toString().replace("\r", ""));
            }
            else {
                throw new Exception("接受到的数据不是Map中的Key，请检查对应数据以及Map编码表。 该项数据名称为：" + optional_exam.get(i));
            }
        }
        System.out.println(after_optional_exam.toString());

        // 可选检查项目的数据修改
        for (int i = 0 ; i < obligatory_exam.size(); i++) {
            //这里进行获取配置文件中的对应病名字的编码值
            String linkedStr = linkedHashMap.get(obligatory_exam.get(i));
            if (!linkedStr.isEmpty()) {
                StringBuilder stringBuilder = new StringBuilder();
                after_obligatory_exam.add(stringBuilder.append(linkedStr + ":" + obligatory_exam.get(i)).toString().replace("\r", ""));
            }
            else {
                Exception e = new Exception("接受到的数据不是Map中的Key，请检查接受到的对应数据以及Map编码表");
                e.printStackTrace();
            }
        }
        System.out.println(after_obligatory_exam.toString());

        answerJson.put("name","新生儿化脓性脑膜炎");
        answerJson.put("optional_exam", after_optional_exam);
        answerJson.put("obligatory_exam",after_obligatory_exam );
        System.out.println(answerJson);

        return answerJson;
    }
}
