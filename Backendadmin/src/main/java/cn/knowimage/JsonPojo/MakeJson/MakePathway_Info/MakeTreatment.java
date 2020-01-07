package cn.knowimage.JsonPojo.MakeJson.MakePathway_Info;

import cn.knowimage.pojo.ReceivePathway;
import cn.knowimage.util.StringConvertNumber;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class MakeTreatment {
    public static String make(ReceivePathway receivePathway){
        String duration_treatment_text = receivePathway.getDuration_treatment_text();
        String treatment_days_duration = receivePathway.getTreatment_days_duration();
        String duration_treatment_min = receivePathway.getDuration_treatment_min();
        String duration_treatment_max = receivePathway.getDuration_treatment_max();
        String sceneList = receivePathway.getSceneList();
        //定义总对象
        JSONObject treatment = new JSONObject();
        //定义duration对象
        JSONObject duration = new JSONObject();

        duration = new JSONObject();
        //将输入数据库中的时间单位强制为时
        duration.put("time_unit", "小时");
        //直接获取输入的text，放入对象duration
        duration.put("time_text", duration_treatment_text);
        //如果treatment_days_duration为空，则无时间定义
        if ("".equals(treatment_days_duration)) {
            duration.put("time_unit", "");
            duration.put("min", 0);
            duration.put("max", 0);

        }
        //treatment_days_duration="其他时间描述",max=0,min=0
        //如果treatment_days_duration为其它时间描述，则time_unit赋值为其它时间描述，min个max置为0
        else if ("其它时间描述".equals(treatment_days_duration)) {
            duration.put("time_unit", "其它时间描述");
            duration.put("min", 0);
            duration.put("max", 0);
        } else {
            //如果time_unit有定义，且不为其它时间描述，则min和max全部转换为小时制
            //duration.put("time_unit",treatment_days_duration);
            if (duration_treatment_min == null || "".equals(duration_treatment_min)) {
                duration.put("min", 0);
            } else {
                //将min通过方法StringConvertNumber转换为小时制
                duration.put("min", StringConvertNumber.stringToFloat(treatment_days_duration, duration_treatment_min));
            }

            if (duration_treatment_max == null || "".equals(duration_treatment_max)) {
                duration.put("max", 0);
            } else {
                //将max通过方法StringConvertNumber转换为小时制
                duration.put("max", StringConvertNumber.stringToFloat(treatment_days_duration, duration_treatment_max));
            }
        }

        //将duration放入对象treatment中
        treatment.put("duration", duration);
        JSONObject scenario_s = new JSONObject();
        //创建输入的总数组           既将前端的sceneList转换为可遍历的数组
        JSONArray scenario = JSONArray.fromObject(sceneList);
        //如果scenario为空，说明前端传来的sceneList为空数组，需将空数组转换成数据库标准格式，所有值为空
        if (    (scenario.getJSONObject(0).getJSONArray("caseChildnum").size()==0)&&
                (scenario.getJSONObject(0).getJSONArray("obligatoryExam").size() == 0)&&
                (scenario.getJSONObject(0).getJSONArray("optionalExam").size() == 0)&&
                (scenario.getJSONObject(0).getJSONArray("notification").size() == 0)){
            JSONObject scenario_id_0 = new JSONObject();
            JSONObject treatment_plan = new JSONObject();
            JSONObject id_0 = new JSONObject();
            JSONArray  content_item = new JSONArray();
            id_0.put("content","");
            id_0.put("content_item",content_item);
            treatment_plan.put("ref","");
            treatment_plan.put("num",0);
            treatment_plan.put("id_0",id_0);
            scenario_id_0.put("treatment_plan",treatment_plan);
            JSONArray obligatory_exam = new JSONArray();
            JSONArray optional_exam = new JSONArray();
            JSONArray item_content = new JSONArray();
            scenario_id_0.put("obligatory_exam",obligatory_exam);
            scenario_id_0.put("optional_exam",optional_exam);
            scenario_id_0.put("item_content",item_content);
            scenario_id_0.put("item_text_name","");
            scenario_s.put("scenario_num",0);
            scenario_s.put("scenario_id_0",scenario_id_0);
        }else {
            //获取scenario_num大小     即为 scenario_num 的值
            int scenario_num = scenario.size();

            //将scenario_num放入总json对象scenario_s
            scenario_s.put("scenario_num", scenario_num);

            //定义该数组存储num值，即scenario_num为动态创建num的个数，treatment_plan_num存放num的值
            int[] treatment_plan_num = new int[scenario_num];

            for (int i = 0; i < scenario_num; i++) {
                //定义该对象获取scenario_id下所有treatment_plan对象中的内容

                JSONObject scenario_id = scenario.getJSONObject(i);

                //获取sceneList中的caseChildnum，转换为java可遍历的数组对象
                JSONArray caseChildnum = scenario_id.getJSONArray("caseChildnum");

                //获取treatment_plan_num大小
                treatment_plan_num[i] = caseChildnum.size();
            }

            //开始拼接scenario文档格式的字符串
            //动态创建scenario_id_0对应的对象
            for (int i = 0; i < scenario_num; i++) {

                //获取第一个对象，由前端传来
                JSONObject value = scenario.getJSONObject(i);

                //开始动态创建scenario_id_x
                JSONObject scenario_id = new JSONObject();

                //创建treatment_plan对象
                JSONObject treatment_plan = new JSONObject();

                //将对象value中的treatmentPlanRef值赋给ref，通过键值对形式获取
                treatment_plan.put("ref", value.getString("treatmentPlanRef"));

                //将treatment_plan_num[i]索引指向的num值（即为前面的caseChildnum.size()）赋给num
                treatment_plan.put("num", treatment_plan_num[i]);

                //获取数组caseChildnum中的值
                JSONArray caseChildnum_s = value.getJSONArray("caseChildnum");

                //开始动态创建treatment_plan下id_0对象
                for (int j = 0; j < treatment_plan_num[i]; j++) {

                    //该对象为数组caseChildnum_s中的对象
                    JSONObject caseChildnum_value = caseChildnum_s.getJSONObject(j);

                    JSONObject id_ = new JSONObject();
                    id_.put("content", caseChildnum_value.getString("treatmentPlanContent"));

                    //获取数组planContentItem中的值
                    JSONArray planContentItem_s = caseChildnum_value.getJSONArray("planContentItem");
                    JSONArray content_item = new JSONArray();
                    for (int k = 0; k < planContentItem_s.size(); k++) {
                        //将planContentItem_s数组中的第k个对象中的键名为value的字符串值放入content_item数组中
                        if (!"".equals(planContentItem_s.getJSONObject(k).getString("value"))) {
                            content_item.add(planContentItem_s.getJSONObject(k).getString("value"));
                        }
                    }
                    id_.put("content_item", content_item);
                    treatment_plan.put(String.format("id_%d", j), id_);
                }
                //将创建好的treatment_plan加入到scenario_id对象中
                scenario_id.put("treatment_plan", treatment_plan);

                //下面开始创建数组obligatoryExam，optionalExam，notification

                //获取value对象下的键名为obligatoryExam数组
                JSONArray obligatoryExam_s = value.getJSONArray("obligatoryExam");
                //创建该数组存放输出的数据
                JSONArray obligatoryExam = new JSONArray();
                for (int j = 0; j < obligatoryExam_s.size(); j++) {
                    if (!"".equals(obligatoryExam_s.getJSONObject(j).getString("value"))) {
                        obligatoryExam.add(obligatoryExam_s.getJSONObject(j).getString("value"));
                    }
                }
                scenario_id.put("obligatory_exam", obligatoryExam);

                //开始创建optionalExam对象
                JSONArray optionalExam_s = value.getJSONArray("optionalExam");
                JSONArray optionalExam = new JSONArray();
                for (int j = 0; j < optionalExam_s.size(); j++) {
                    if (!"".equals(optionalExam_s.getJSONObject(j).getString("value"))) {
                        optionalExam.add(optionalExam_s.getJSONObject(j).getString("value"));
                    }
                }
                scenario_id.put("optional_exam", optionalExam);

                //开始创建notification对象
                //第一步，获取前端value中的 notification，并命名为notification_s的JsonObject
                try {
                    JSONArray notification_s = value.getJSONArray("notification");
                    JSONArray item_content = new JSONArray();
                    //第二步，创建总对象notification
                    JSONObject notification = new JSONObject();
                    //第三步，开始创建
                    //先判断是否为空
                    if (notification_s != null) {
                        //非空，存入item_text_name
                        //存入数组对象
                        for (int j = 0; j < notification_s.size(); j++) {
                            if (!"".equals(notification_s.getJSONObject(j).getString("value"))) {
                                item_content.add(notification_s.getJSONObject(j).getString("value"));
                            }
                        }
                        scenario_id.put("item_text_name",value.getString("item_field_name2").toString());
                        scenario_id.put("item_content", item_content);
                    }else {
                        scenario_id.put("item_text_name", "");
                        scenario_id.put("item_content", "");
                    }
                }catch (Exception e){

                }

                //optional_exam创建完之后加入scenario_id_0字段中
                scenario_s.put(String.format("scenario_id_%d", i), scenario_id);



            }

        }


        treatment.put("scenario", scenario_s);

        return treatment.toString();
    }
}
