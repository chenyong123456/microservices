package cn.knowimage.JsonPojo.ReturnJson.ReturnPathway_Info;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * @author Mr.G
 * @date 2019/8/12
 * 数据回显功能实现方法
 * 将数据库中treatment字段格式转换为前台能够识别的数据格式
 * 其中下划线变量对应数据库原字段，无需更改
 */
public class TreatmentReturn {
    public static String treatment(String treatment) {
        //获取输入的字符串，转为总对象
        JSONObject treatment_s = JSONObject.fromObject(treatment);
        //定义总对象，存放所有输出对象
        JSONObject object = new JSONObject();
        //获取treatment_s对象里的子对象duration的time_unit，min，max，text值
        object.put("treatment_days_duration", treatment_s.getJSONObject("duration").getString("time_unit"));
        object.put("duration_treatment_min", treatment_s.getJSONObject("duration").getInt("min"));
        object.put("duration_treatment_max", treatment_s.getJSONObject("duration").getInt("max"));
        object.put("duration_treatment_text", treatment_s.getJSONObject("duration").getString("time_text"));
        //获取treatment_s对象里的scenario子对象
        JSONObject scenario = treatment_s.getJSONObject("scenario");
        //定义输出的总数组sceneList
        JSONArray sceneList = new JSONArray();
        JSONArray planContentItem = new JSONArray();

        //定义obligatoryExam数组，用于存放value对象
        JSONArray obligatoryExam = new JSONArray();

        //定义optionalExam数组，用于存放value对象
        JSONArray optionalExam = new JSONArray();

        //该对象用于存放sceneList数组中的各个子对象
        JSONObject json = new JSONObject();

        JSONArray notification = new JSONArray();

        JSONArray caseChildnum = new JSONArray();
        json.put("ulShow",false);
        //首先判断scenario字段是否为默认值，如果是，则给前端默认格式
        if ("0".equals(scenario.getString("scenario_num"))){
            JSONObject jsonObject = new JSONObject();
            JSONObject value = new JSONObject();
            //将content_item_s数组中的string值依次赋给value，键值对形式
            value.put("value","");
            //将获取的value放入数组planContentItem
            planContentItem.add(value);
            //各个数组中不能含value
            //obligatoryExam.add(value);
            //optionalExam.add(value);
            //notification.add(value);
            jsonObject.put("treatmentPlanContent","");
            jsonObject.put("planContentItem",planContentItem);
            //caseChildnum中为空的话,传给前台为空数组即可
            //caseChildnum.add(jsonObject);
            json.put("treatmentPlanRef","");
            json.put("openYixueBijian", false);
            json.put("obligatoryExam",obligatoryExam);
            json.put("openYixueKejian", false);
            json.put("optionalExam", optionalExam);
            json.put("openYixueQita", false);
            json.put("notification", notification);
            json.put("item_field_name2","");
            json.put("YiliaoShow",false);
            json.put("caseChildnum",caseChildnum);
            sceneList.add(json);
        }else {
            //根据scenario_num值循环，最外层循环
            for (int i = 0; i < scenario.getInt("scenario_num"); i++) {
                //依次获取scenario对象中的scenario_id_0对象
                JSONObject scenario_id_ = scenario.getJSONObject(String.format("scenario_id_%d", i));
                //获取scenario_id_0对象中的子对象treatment_plan
                JSONObject treatment_plan = scenario_id_.getJSONObject("treatment_plan");

                //将ref值赋值给treatmentPlanRef，放入json对象
                json.put("treatmentPlanRef", treatment_plan.getString("ref"));
                JSONObject child = new JSONObject();
                if ( treatment_plan.getInt("num")==0){
                    JSONObject value = new JSONObject();
                    //将content_item_s数组中的string值依次赋给value，键值对形式
                    value.put("value", "");
                    child.put("treatmentPlanContent","");
                    //将获取的value放入数组planContentItem
                    planContentItem.add(value);
                    child.put("planContentItem",planContentItem);
                    //caseChildnum.add(child);
                    json.put("YiliaoShow",false);
                    json.put("caseChildnum", caseChildnum);
                }else {
                    json.put("YiliaoShow",true);
                    for (int j = 0; j < treatment_plan.getInt("num"); j++) {
                        //定义id_对象，依次获取id_x的对象值
                        JSONObject id_ = treatment_plan.getJSONObject(String.format("id_%d", j));
                        //获取数组caseChildnum中的子对象
                        child.put("treatmentPlanContent", id_.getString("content"));
                        //获取id_0下content_item数组中的值
                        JSONArray content_item_s = id_.getJSONArray("content_item");
                        //如果该字段为空
                        if (content_item_s.size() == 0) {
                            JSONObject value = new JSONObject();
                            //将content_item_s数组中的string值依次赋给value，键值对形式
                            value.put("value", "");
                            //将获取的value放入数组planContentItem
                            planContentItem.add(value);
                        } else {
                            //根据content_item数组长度循环
                            for (int k = 0; k < content_item_s.size(); k++) {
                                JSONObject value = new JSONObject();
                                //将content_item_s数组中的string值依次赋给value，键值对形式
                                value.put("value", content_item_s.getString(k));
                                //将获取的value放入数组planContentItem
                                planContentItem.add(value);
                            }
                        }

                        //将数组planContentItem放入对象child中
                        child.put("planContentItem", planContentItem);
                        planContentItem.clear();
                        //将对象child依次放入数组caseChildnum
                        caseChildnum.add(child);
                        //将数组caseChildnum放入对象json
                        json.put("caseChildnum", caseChildnum);
                    }
                }


                //定义三个数组，分别获取scenario_id_对象下的obligatory_exam，optional_exam，notification三个对象
                JSONArray obligatory_exam_s = scenario_id_.getJSONArray("obligatory_exam");
                JSONArray optional_exam_s = scenario_id_.getJSONArray("optional_exam");


                //如果该字段为空
                if (obligatory_exam_s.size() == 0) {
                    JSONObject value = new JSONObject();
                    //依次获取数组中的第j1个string值
                    value.put("value", "");
                    json.put("openYixueBijian",false);
                   // obligatoryExam.add(value);
                } else {
                    json.put("openYixueBijian",true);
                    //根据obligatory_exam_s数组长度循环
                    for (int j1 = 0; j1 < obligatory_exam_s.size(); j1++) {
                        JSONObject value = new JSONObject();
                        //依次获取数组中的第j1个string值
                        value.put("value", obligatory_exam_s.getString(j1));
                        obligatoryExam.add(value);
                    }
                }
                //将obligatoryExam对象放入sceneList数组下的子对象json
                    json.put("obligatoryExam", obligatoryExam);
                //如果该字段为空
                if (optional_exam_s.size() == 0) {
                    JSONObject value = new JSONObject();
                    //依次获取数组中的第j2个string值
                    value.put("value", "");
                    json.put("openYixueKejian",false);
                    //optionalExam.add(value);
                } else {
                    json.put("openYixueKejian",true);
                    //根据optional_exam_s数组长度循环
                    for (int j2 = 0; j2 < optional_exam_s.size(); j2++) {
                        JSONObject value = new JSONObject();
                        //依次获取数组中的第j2个string值
                        value.put("value", optional_exam_s.getString(j2));
                        optionalExam.add(value);
                    }
                }
                //将obligatoryExam对象放入sceneList数组下的子对象json
                json.put("optionalExam", optionalExam);

                //定义notification数组，用于存放value对象
                JSONArray notification_a = scenario_id_.getJSONArray("item_content");

                //如果该字段为空
                if (notification_a.size() == 0) {
                    JSONObject value = new JSONObject();
                    value.put("value", "");
                    //notification.add(value);
                    json.put("openYixueQita", false);
                    json.put("notification", notification);
                } else {
                    json.put("openYixueQita",true);
                    //根据notification_s数组长度循环
                    for (int j3 = 0; j3 < notification_a.size(); j3++) {
                        JSONObject value = new JSONObject();
                        value.put("value", notification_a.getString(j3));
                        notification.add(value);
                        json.put("notification", notification);
                    }
                }
                //将notification对象放入sceneList数组下的子对象json
                if (!"".equals(scenario_id_.getString("item_text_name"))) {
                    json.put("item_field_name2", scenario_id_.getString("item_text_name"));
                } else{
                    json.put("item_field_name2", "");
                }
                sceneList.add(json);
            }
        }
        //将sceneList数组放入对象object中
        object.put("sceneList", sceneList);
        return object.toString();
    }
}
