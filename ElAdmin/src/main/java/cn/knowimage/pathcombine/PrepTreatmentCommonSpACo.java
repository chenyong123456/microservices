package cn.knowimage.pathcombine;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @params 对prep_treatment_common字段中检查项进行字符串的拆分和拼接编码格式的指定格式的json格式
 * @author 啊勇
 */
public class PrepTreatmentCommonSpACo {

    /**
     * @params 对PrepTreatmentCommon字段进行相关检查的字眼的拆分
     */
    public static JSONObject prepTreatmentCommonSplit(String prep_treatment_common,String pathWay_name){
        System.out.println(prep_treatment_common);
        //最终要返回给小栗子的总数字
        JSONObject Result = new JSONObject();
        Result.put("name",pathWay_name);
        //存放单个检查项的每一个数组
        //JSONArray notification_s = new JSONArray();
        JSONArray optionalexam_s = new JSONArray();
        JSONArray obligatoryexam_s = new JSONArray();

        //这里是开始字符串的拆分的过程
        //将prep_treatment_common字符串转换为JSONObject格式分数据
        JSONObject prepTreatmentCommon = JSONObject.fromObject(prep_treatment_common);
        //获取scenario对象
        JSONObject scenario = prepTreatmentCommon.getJSONObject("scenario");
        //获取scenario对象中的num值进行动态获取数据
        int num = scenario.getInt("num");
        for (int i = 0; i < num ; i++) {
            //获取id_0的数组
            JSONObject id_ = scenario.getJSONObject(String.format("id_%d", i));

            //获取id_0中的optional_exam数组
            JSONArray optionalexam = id_.getJSONArray("optional_exam");
           // JSONArray optionalexam_add = new JSONArray();
            for (int j = 0; j < optionalexam.size() ; j++) {
                //循环获取每一个字符串xcsdc
                String string = optionalexam.getString(j);
                //对获取到的字符串中的其他字符全部进行替换为指定的、格式
                string = string.replaceAll(", ","、");
                string = string.replaceAll("及","、");
                string = string.replaceAll("等","#等");
                //在这里进行字符串的分割
                String[] split = string.split("、");
                //将截取之后的数组中的个个放入新建的数组中
                for (int k = 0; k < split.length; k++) {
                    optionalexam_s.add(split[k]);
                }
            }
            //optionalexam_s.add(optionalexam_add);

            //获取id_0中的obligatory_exam数组
            JSONArray obligatoryexam =  id_.getJSONArray("obligatory_exam");
            //JSONArray obligatoryexam_add = new JSONArray();
            for (int j = 0; j < obligatoryexam.size(); j++) {
                //循环获取每一个字符串
                String string = obligatoryexam.getString(j);
                //对获取到的字符串中的其他字符全部进行替换为指定的、格式
                string = string.replaceAll(", ","、");
                //在这里进行字符串的分割
                String[] split = string.split("、");
                //将截取之后的数组中的个个放入新建的数组中
                for (int k = 0; k < split.length; k++) {
                    obligatoryexam_s.add(split[k]);
                }
            }
            //obligatoryexam_s.add(obligatoryexam_s);
        }
        System.out.println("**********************");
        Result.put("optional_exam",optionalexam_s);
        Result.put("obligatory_exam",obligatoryexam_s);
        System.out.println(Result.toString());
        return Result;
    }

    public static void main(String[] args) {
        prepTreatmentCommonSplit("{\"scenario\": {\"num\": 2, \"id_0\": {\"duration\": {\"max\": 48, \"min\": 24, \"time_unit\": \"小时\"}, \"notification\": [\"入选临床路径、加强拍背等护理、注意观察肺部症状变化\"], \"optional_exam\": [], \"obligatory_exam\": [\"血常规、CRP、尿常规、粪常规\", \"心肌酶谱及肝肾功能\", \"呼吸道病毒检测\", \"呼吸道细菌培养及药敏\", \"血支原体、衣原体检测\", \"胸片检查\", \"心电图\", \"血气分析检测\"]}, \"id_1\": {\"duration\": {\"max\": 120, \"min\": 72, \"time_unit\": \"小时\"}, \"notification\": [\"如出现心力衰竭、呼吸衰竭等并发症时应当及时退出毛细支气管炎临床路径\"], \"optional_exam\": [], \"obligatory_exam\": [\"血气分析检测\", \"肺功能测定\", \"心电图复查\", \"血清过敏原检查\", \"超声心动图\", \"复查血支原体、衣原体\", \"支气管镜检查\"]}}}","新生儿化脓性脑膜炎");
    }
}
