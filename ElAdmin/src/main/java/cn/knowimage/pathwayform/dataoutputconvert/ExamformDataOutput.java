package cn.knowimage.pathwayform.dataoutputconvert;

import cn.knowimage.pathwayform.vo.ExamForm;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

/**
 * 将ExamFormjson格式转换为前台指定的格式
 *
 * @autor lyx
 * @date 2019/08/28
 */
public class ExamformDataOutput {
    /**
     * 对infoInHospital进行格式转换
     *
     * @param infoInHospital 住院信息
     * @return JSONObject infoInHospitalJsonObject
     */
    public static JSONObject getInHospitalDataJsonObject(String infoInHospital) {
        //对数据库中传过来的住院信息数据进行转换为json对象
        JSONObject infoInHospitalJsonObject = JSONObject.fromObject(infoInHospital);
        String timeUnit = infoInHospitalJsonObject.getJSONObject("standard_entry_days").getString("time_unit");
        String max = infoInHospitalJsonObject.getJSONObject("standard_entry_days").getString("max");
        String min = infoInHospitalJsonObject.getJSONObject("standard_entry_days").getString("min");
        String str = min + "-" + max + timeUnit;
        infoInHospitalJsonObject.put("standard_entry_days", str);
        return infoInHospitalJsonObject;
    }

    /**
     * 将阶段数转换为前端需要的格式（根据id查询所有的阶段数）
     *
     * @param examForm examForm 对象
     * @return JSONArray stageArrayLast
     */
    public static JSONArray getStageJsonObjet(ExamForm examForm) {
        JSONObject stageJsonLast = new JSONObject();
        JSONObject treatmentInfoJsonObject = JSONObject.fromObject(examForm.getTreatment_info());
        System.out.println("examForm.getTreatment_info()" + examForm.getTreatment_info());
        System.out.println("treatmentInfoJsonObject" + treatmentInfoJsonObject.toString());
        JSONArray stageArrayLast = new JSONArray();
        JSONObject stage_X = null;
        JSONObject keyOrderLastJsonObject = null;
        JSONObject keyOrdersJsonObject = null;
        //定义extraField数组对象
        JSONArray extraFieldArray = null;
        //定义extra对象
        JSONObject extraObject = new JSONObject();
        System.out.println("treatmentInfoJsonObject.isEmpty()" + treatmentInfoJsonObject.isEmpty() + "obj" + treatmentInfoJsonObject.toString());
        if (examForm.getTreatment_info() != null && !treatmentInfoJsonObject.isEmpty()) {
            //定义变量接收
            int num = treatmentInfoJsonObject.getInt("stage_num");
            //对key_orders中数组对象转换
            for (int i = 0; i < num; i++) {
                //拿到阶段对象
                stage_X = new JSONObject();
                stage_X = treatmentInfoJsonObject.getJSONObject(String.format("stage_%d", i));
                //定义keyOrders json对象
                keyOrdersJsonObject = stage_X.getJSONObject("key_orders");
                keyOrderLastJsonObject = new JSONObject();
                stageJsonLast.put("currentMin", stage_X.getJSONObject("current_time").getString("min"));
                stageJsonLast.put("currentMax", stage_X.getJSONObject("current_time").getString("max"));
                stageJsonLast.put("isOperation", stage_X.getJSONObject("operation_day").getInt("is_there_operation_day") + "");
                stageJsonLast.put("operationNum", stage_X.getJSONObject("operation_day").getInt("bias_num") + "");
                stageJsonLast.put("currentUnit", stage_X.getJSONObject("current_time").getString("time_unit"));
              /*  System.out.println("你再说一遍不是。。。。。。。。"+showTime.toString());
                stageJsonLast.put("options2", showTime.getJSONArray("options2"));
                stageJsonLast.put("prefix_description",showTime.getJSONArray("prefix_description"));*/
         //       if(!"".equals(stage_X.getJSONObject("stage_description").getString("prefix_description"))&&null!=stage_X.getJSONObject("stage_description").getString("prefix_description")){
                System.out.println("stage_X==============="+stage_X.toString());
                System.out.println("我的东西呢"+ stage_X.getJSONObject("stage_description").getString("prefix_description"));
                stageJsonLast.put("fill_prefix_description", stage_X.getJSONObject("stage_description").getString("prefix_description"));
                String extraDescription = stage_X.getJSONObject("stage_description").getString("extra_description");
                if(!"".equals(extraDescription)){
                    stageJsonLast.put("extra_description", stage_X.getJSONObject("stage_description").getString("extra_description"));
                }else{
                    stageJsonLast.put("extra_description", "");
                }

                stageJsonLast.put("primary_diagnosi", stage_X.getJSONArray("primary_diagnosi"));
                keyOrderLastJsonObject.put("long_term_orders", keyOrdersJsonObject.getJSONArray("long_term_orders"));
                keyOrderLastJsonObject.put("long_term_extra_text", keyOrdersJsonObject.getString("long_term_extra_text"));
                keyOrderLastJsonObject.put("temporary_orders", keyOrdersJsonObject.getJSONArray("temporary_orders"));
                keyOrderLastJsonObject.put("temporary_extra_text", keyOrdersJsonObject.getString("temporary_extra_text"));
                //    keyOrdersJsonObject.remove("temporary_orders_extra_text");
                keyOrderLastJsonObject.put("discharge_orders", keyOrdersJsonObject.getJSONArray("discharge_orders"));
                keyOrderLastJsonObject.put("discharge_extra_text", keyOrdersJsonObject.getString("discharge_extra_text"));
                keyOrderLastJsonObject.put("notification", keyOrdersJsonObject.getJSONArray("notification"));
                int fieldNum = keyOrdersJsonObject.getJSONObject("extra_field").getInt("field_num");
                System.out.println("长度在哪里" + fieldNum);
                if (fieldNum == 0) {
                    extraFieldArray = new JSONArray();
                    extraObject.put("field_info", "");
                    extraObject.put("field_content", new JSONArray());
                    extraFieldArray.add(extraObject);
                } else {
                    System.out.println("长度在哪里" + fieldNum);
                    extraFieldArray = new JSONArray();
                    for (int j = 0; j < fieldNum; j++) {
                        extraObject.put("field_info", keyOrdersJsonObject.getJSONObject("extra_field").getJSONObject(String.format("field_info_%d", j)).getString("title"));
                        extraObject.put("field_content", keyOrdersJsonObject.getJSONObject("extra_field").getJSONObject(String.format("field_info_%d", j)).getJSONArray("field_content"));
                        //这里生成一个对象放进到数组中去
                        extraFieldArray.add(extraObject);
                    }
                }

                keyOrderLastJsonObject.put("extra_field", extraFieldArray);
                stageJsonLast.put("key_orders", keyOrderLastJsonObject);
                stageJsonLast.put("major_nursing_work", stage_X.getJSONArray("major_nursing_work"));
                stageJsonLast.put("disease_variation_status", "" + stage_X.getJSONObject("disease_variation").getInt("status"));
                if (stage_X.getJSONObject("disease_variation").getInt("status") != 0) {
                    stageJsonLast.put("reason", stage_X.getJSONObject("disease_variation").getJSONArray("reason"));
                } else {
                    JSONArray reasonNull = new JSONArray();
                    reasonNull.add("");
                    stageJsonLast.put("reason", reasonNull);
                }
                stageArrayLast.add(stageJsonLast);
            }

        } else {
            keyOrderLastJsonObject = new JSONObject();
            extraObject = new JSONObject();
            extraFieldArray = new JSONArray();
            stageJsonLast.put("currentMin", "");
            stageJsonLast.put("currentMax", "");
            stageJsonLast.put("isOperation", "0");
            stageJsonLast.put("operationNum", "");
            stageJsonLast.put("currentUnit", "");
            stageJsonLast.put("extra_description", "");
            stageJsonLast.put("fill_prefix_description", "");
            stageJsonLast.put("primary_diagnosi", "");
            keyOrderLastJsonObject.put("long_term_orders", new JSONArray());
            keyOrderLastJsonObject.put("temporary_orders", new JSONArray());
            keyOrderLastJsonObject.put("discharge_orders", new JSONArray());
            keyOrderLastJsonObject.put("notification", new JSONArray());
            keyOrderLastJsonObject.put("long_term_extra_text", "");
            keyOrderLastJsonObject.put("temporary_extra_text", "");
            keyOrderLastJsonObject.put("discharge_extra_text", "");
            extraObject.put("field_info", "");
            extraObject.put("field_content", new JSONArray());
            extraFieldArray.add(extraObject);
            stageJsonLast.put("key_orders", keyOrderLastJsonObject);
            stageJsonLast.put("extra_field", extraFieldArray);
            stageJsonLast.put("major_nursing_work", new JSONArray());
            stageJsonLast.put("disease_variation_status", "");
            stageJsonLast.put("reason", new JSONArray());
            stageArrayLast.add(stageJsonLast);
        }
        System.out.println("最终-------------------------》"+stageArrayLast.toString());
        return stageArrayLast;
    }


    /**
     * 对TreatmentInfo对象数据进行转换, 转换为指定的数据格式 用于数据查询（例如新增界面可回显数据，分为id查询以及id,日期查询）
     *
     * @param treatmentInfo  医疗信息
     * @param infoInHospital 住院信息
     * @return JSONObject treatmentInfoJsonObject
     */
    public static JSONObject getTreatmentInfoDataJsonObjet(String treatmentInfo, String infoInHospital, int currentInfoDate) {
        System.out.println("数据传来了没" + treatmentInfo);

        //对数据库中传过来的治疗信息数据进行转换为json对象
        JSONObject treatmentInfoJsonObject = JSONObject.fromObject(treatmentInfo);
        JSONObject treatmentInfoLast = new JSONObject();
        //对数据库中传过来的住院信息数据进行转换为json对象
        JSONObject infoInHospitalJsonObject = JSONObject.fromObject(infoInHospital);
        //定义一个jsonObject对象 存放错误信息
        JSONObject error = new JSONObject();
        //定义一个存放住院时间的json对象
        JSONObject currentTime = null;
        //定义一个存放手术日的json对象;
        JSONObject operationDay = null;
        //在这里进行动态数据中是字符串进行处理
        int num = treatmentInfoJsonObject.getInt("stage_num");
        String timeUnit = infoInHospitalJsonObject.getJSONObject("standard_entry_days").getString("time_unit");
        //定义字符串变量  存储手术日描述
        String operationDescription = "";
        //定义字符串变量  存储术后天数
        String afterOperationDescription = "";
        JSONObject stage_X = null;
        //定义一个flag 做标识
        int flag = 0;
        int operationMin = 0;
        int operationMax = 0;
        for (int i = 0; i < num; i++) {
            // treatmentInfoLast = new JSONObject();
            //JSONObject result = new JSONObject();
            stage_X = new JSONObject();
            //在这里取出数据stage_0对其中的凡是数组进行处理
            stage_X = treatmentInfoJsonObject.getJSONObject(String.format("stage_%d", i));
            // System.out.println(stage_X.toString());
            //在这里进行时间字符串的拼接成指定格式的时间格式0-5(时间单位)这种
            currentTime = new JSONObject();
            currentTime = stage_X.getJSONObject("current_time");
            operationDay = new JSONObject();
            operationDay = stage_X.getJSONObject("operation_day");
            if (operationDay.getInt("is_there_operation_day") == 0) {
                operationDescription = "";
            }
            //有手术日（明确手术）
            if (operationDay.getInt("is_there_operation_day") == 1) {
                flag = 1;
                operationMin = currentTime.getInt("min");
                System.out.println("动手术的日子在住院第" + operationMin + "天");
                operationDescription = "(手术日)";
            }
            //有手术日描述（无明确手术日）
            if (operationDay.getInt("is_there_operation_day") == 2) {
                flag = 2;
                operationDescription = "(手术日)";
            }
            if (currentTime.getInt("min") <= currentInfoDate && currentTime.getInt("max") >= currentInfoDate) {

                //明确手术日
                if (flag == 1) {
                    //如果手术日不等于当前日，并且当前阶段为第n天的格式
                    if (operationMin != currentTime.getInt("min") && currentTime.getInt("min") == currentTime.getInt("max")) {
                        //计算术后的天数格式  术后第x天
                        afterOperationDescription = "(术后第" + (currentTime.getInt("min") - operationMin) + "天)";
                    }
                    //如果手术日不等于当前日，并且当前阶段为第n-m天的格式
                    if (operationMin != currentTime.getInt("min") && currentTime.getInt("min") != currentTime.getInt("max")) {
                        //计算术后的天数格式  术后第x-y天
                        afterOperationDescription = "(术后第" + (currentTime.getInt("min") - operationMin) + "-" + (currentTime.getInt("max") - operationMin) + "天)";
                    }

                }
/*                if(flag == 2){
                    afterOperationDescription = "(术后第"+(currentTime.getInt("min")-operationMin)+"-"+(currentTime.getInt("max")-operationMin)+"天)";
                }*/
                System.out.println("当前阶段时间================》" + currentTime);
                //定义字符串变量  存储前台需要阶段的住院天数输出格式 x 或 x-y天/周（描述）
                String timeForm = "";
                //判断 如果当前阶段为第n天
                if (currentTime.getInt("min") != currentTime.getInt("max")) {
                    //当前阶段为第n天
                    timeForm = currentTime.getString("min") + "-" + currentTime.getString("max") + timeUnit + operationDescription + afterOperationDescription;
                }
                //判断 如果当前阶段为第n-m天
                if (currentTime.getInt("min") == currentTime.getInt("max")) {
                    //当前阶段为第n-m天
                    timeForm = currentTime.getString("min") + timeUnit + operationDescription + afterOperationDescription;
                }
                System.out.println("手术日状态================》" + timeForm);
                System.out.println("flag标签=============》" + flag);
                //将转换好的数据格式进行封装到current_time对象中,用字符串代替对象.
                stage_X.put("current_time", timeForm);
                //2就是在里处理一条数据
                JSONArray diagnosiArray = stage_X.getJSONArray("primary_diagnosi");
                JSONObject primaryDiagnosiJson = null;
                JSONArray lastArray = new JSONArray();
                /**
                 * 循环遍历diagnosiArray数组
                 * 处理成前台 指定的格式
                 */
                for (int j = 0; j < diagnosiArray.size(); j++) {
                    primaryDiagnosiJson = new JSONObject();
                    primaryDiagnosiJson.put("id", false);
                    primaryDiagnosiJson.put("primaryDiagnosi", diagnosiArray.getString(j));
                    lastArray.add(primaryDiagnosiJson);
                }
                //处理完primary_diagnosi改为了指定是格式替换掉总对象中的数据
                stage_X.put("primary_diagnosi", lastArray);
                //对key_orders中数组对象转换
                JSONObject keyOrdersJsonObject = stage_X.getJSONObject("key_orders");
                //定义JSONObject变量接收额外医嘱信息
                JSONObject extraFieldJsonObject = keyOrdersJsonObject.getJSONObject("extra_field");
                //定义JSONObject变量接收额外医嘱信息
                JSONObject fieldInfoJsonJsonObjectSingle = new JSONObject();
                //定义JSONArray变量接收额外医嘱内容
                JSONObject fieldInfoJsonJsonObject = new JSONObject();
                //构造额外医嘱信息 JSONArray数组对象
                JSONArray extraFieldJsonArrayLast = new JSONArray();
                //定义JSONObject变量接收额外医嘱内容
                JSONObject fieldContentJsonObjectSingle = new JSONObject();
                //定义JSONArray变量接收额外医嘱内容
                JSONArray fieldContentJsonArrayLast = new JSONArray();
                //定义整形变量接收额外医嘱个数
                int fieldNum = extraFieldJsonObject.getInt("field_num");
                System.out.println("格外医嘱个数"+fieldNum);
                if(fieldNum >= 0){
                   // JSONObject extraFieldJsonObject = new JSONObject();
                   // JSONArray fieldInfoJsonArray = new JSONArray();
                    for (int j = 0; j < fieldNum; j++) {
                        fieldInfoJsonJsonObject.put("title",extraFieldJsonObject.getJSONObject(String.format("field_info_%d",j)).getString("title"));
                        // fieldInfoJsonJsonObject.put("field_content",extraFieldJsonObject.getJSONObject(String.format("field_info_%d",j)).getJSONArray("field_content"));
                        if(extraFieldJsonObject.getJSONObject(String.format("field_info_%d",j)).getJSONArray("field_content").size()!=0){
                            int fieldSize = extraFieldJsonObject.getJSONObject(String.format("field_info_%d",j)).getJSONArray("field_content").size();
                            System.out.println("我有点懵"+extraFieldJsonObject.getJSONObject(String.format("field_info_%d",j)).toString());
                            for (int k = 0; k < fieldSize; k++) {
                                fieldContentJsonObjectSingle = new JSONObject();
                                fieldContentJsonObjectSingle.put("id",false);
                                fieldContentJsonObjectSingle.put("field_content",extraFieldJsonObject.getJSONObject(String.format("field_info_%d",j)).getJSONArray("field_content").get(k));
                                fieldContentJsonArrayLast.add(fieldContentJsonObjectSingle);
                                fieldInfoJsonJsonObject.put("field_content",fieldContentJsonArrayLast);
                               // fieldContentJsonArrayLast = new JSONArray();
                            }
                            //当存在多个额外医嘱的时候  循环压完一次数组，清空一次数据
                            fieldContentJsonArrayLast = new JSONArray();
                         }else{
                            System.out.println("进了else了");
                            fieldContentJsonArrayLast = new JSONArray();
                            fieldInfoJsonJsonObject.put("field_content",fieldContentJsonArrayLast);
                            //fieldContentJsonObjectSingle.put("field_content",new JSONArray());
                        }
                       // fieldContentJsonArrayLast.add(fieldContentJsonObjectSingle);


                        fieldInfoJsonJsonObjectSingle.put("field_info",fieldInfoJsonJsonObject);
                        extraFieldJsonArrayLast.add(fieldInfoJsonJsonObjectSingle);

                    }
               //     extraFieldJsonObjectLast.put("extra_field",extraFieldJsonArrayLast);
                }
                if(fieldNum == 0){
                    extraFieldJsonArrayLast = new JSONArray();
                }
                keyOrdersJsonObject.put("extra_field",extraFieldJsonArrayLast);
                if("".equals(keyOrdersJsonObject.getString("discharge_extra_text"))){
                    keyOrdersJsonObject.put("discharge_extra_text","");
                }else {
                    keyOrdersJsonObject.put("discharge_extra_text", keyOrdersJsonObject.getString("discharge_extra_text"));
                }

                if("".equals(keyOrdersJsonObject.getString("long_term_extra_text"))){
                    keyOrdersJsonObject.put("long_term_extra_text","");
                }else {
                    keyOrdersJsonObject.put("long_term_extra_text", keyOrdersJsonObject.getString("long_term_extra_text"));
                }

                if("".equals(keyOrdersJsonObject.getString("temporary_extra_text"))){
                    keyOrdersJsonObject.put("temporary_extra_text","");
                }else {
                    keyOrdersJsonObject.put("temporary_extra_text", keyOrdersJsonObject.getString("temporary_extra_text"));
                }
                keyOrdersJsonObject.put("extra_field",extraFieldJsonArrayLast);
                //获取long_term_orders数组的数据
                JSONArray longTermOrdersArray = keyOrdersJsonObject.getJSONArray("long_term_orders");
                //定义一个json对象存放指定格式的longtermOrders对象
                JSONObject longTermOrdersObject = null;
                //定义JsonArray对象 存放最终处理后的longTerm数组对象
                JSONArray longTermOrdersLast = new JSONArray();
                /**
                 * 循环遍历longTermOrders数组
                 * 处理成前台 指定的格式
                 */
                for (int j = 0; j < longTermOrdersArray.size(); j++) {
                    if (longTermOrdersArray.getString(j).equals("")) {
                        longTermOrdersLast.add("");
                    } else {
                        longTermOrdersObject = new JSONObject();
                        longTermOrdersObject.put("id", false);
                        longTermOrdersObject.put("longTermOrders", longTermOrdersArray.getString(j));
                        longTermOrdersLast.add(longTermOrdersObject);
                    }
                }
                //将处理后的longTermOders数组对象放入key_orders json对象中
                stage_X.getJSONObject("key_orders").put("long_term_orders", longTermOrdersLast);
                //获取temporary_ordersjson数组并进行处理
                JSONArray temporaryOrdersArray = keyOrdersJsonObject.getJSONArray("temporary_orders");
                JSONObject temporaryOrdersObject = null;
                JSONArray temporaryOrdersLast = new JSONArray();
                /**
                 * 循环遍历temporaryOrdersArray数组
                 * 处理成前台 指定的格式
                 */
                for (int j = 0; j < temporaryOrdersArray.size(); j++) {
                    if (temporaryOrdersArray.getString(j).equals("")) {
                        temporaryOrdersLast.add("");
                    } else {
                        temporaryOrdersObject = new JSONObject();
                        temporaryOrdersObject.put("id", false);
                        temporaryOrdersObject.put("temporaryOrders", temporaryOrdersArray.getString(j));
                        temporaryOrdersLast.add(temporaryOrdersObject);
                    }
                }
                stage_X.getJSONObject("key_orders").put("temporary_orders", temporaryOrdersLast);
                //获取discharge_orders数组的数据并进行格式处理
                JSONArray dischargeOrdersArray = keyOrdersJsonObject.getJSONArray("discharge_orders");
                JSONObject dischargeOrdersObject = null;
                JSONArray dischargeLast = new JSONArray();
                /**
                 * 循环遍历dischargeOrdersArray数组
                 * 处理成前台 指定的格式
                 */
                for (int j = 0; j < dischargeOrdersArray.size(); j++) {
                    if (dischargeOrdersArray.getString(j).equals("")) {
                        dischargeLast.add("");
                    } else {
                        dischargeOrdersObject = new JSONObject();
                        dischargeOrdersObject.put("id", false);
                        dischargeOrdersObject.put("dischargeOrders", dischargeOrdersArray.getString(j));
                        dischargeLast.add(dischargeOrdersObject);
                    }
                }
                stage_X.getJSONObject("key_orders").put("discharge_orders", dischargeLast);

                //获取notification数组的数据并进行格式处理
                JSONArray notificationArray = keyOrdersJsonObject.getJSONArray("notification");
                JSONObject notificationJsonObject = null;
                JSONArray notificationLast = new JSONArray();
                /**
                 * 循环遍历notificationArray数组
                 * 处理成前台 指定的格式
                 */
                for (int j = 0; j < notificationArray.size(); j++) {
                    if (notificationArray.getString(j).equals("")) {
                        notificationLast.add("");
                    } else {
                        notificationJsonObject = new JSONObject();
                        notificationJsonObject.put("id", false);
                        notificationJsonObject.put("notification", notificationArray.getString(j));
                        notificationLast.add(notificationJsonObject);
                    }
                }
                stage_X.getJSONObject("key_orders").put("notification", notificationLast);

                //获取major_nursing_work数组的数据并进行格式处理
                JSONArray majorNurseWorkArray = stage_X.getJSONArray("major_nursing_work");
                JSONObject majorNurseWorkObject = null;
                JSONArray majorLast = new JSONArray();

                /**
                 * 循环遍历majorNurseWorkArray数组
                 * 处理成前台 指定的格式
                 */
                for (int j = 0; j < majorNurseWorkArray.size(); j++) {
                    majorNurseWorkObject = new JSONObject();
                    majorNurseWorkObject.put("id", false);
                    majorNurseWorkObject.put("major_nursing_work", majorNurseWorkArray.getString(j));
                    majorLast.add(majorNurseWorkObject);
                }

                stage_X.put("major_nursing_work", majorLast);
                //拿到physician_name数组数据并进行数据处理
                JSONArray physicianNameArray = stage_X.getJSONArray("physician_name");
                JSONObject physicianNameObject = null;
                JSONArray physicianNameLast = new JSONArray();
                //如果数组有值
                for (int j = 0; j < physicianNameArray.size(); j++) {
                    if (!physicianNameArray.getString(j).equals("")) {
                        physicianNameObject = new JSONObject();
                        physicianNameObject.put("physicianName", physicianNameArray.getString(j));
                        physicianNameLast.add(physicianNameObject);
                    } else {
                        physicianNameObject = new JSONObject();
                        physicianNameObject.put("physicianName", "无");
                        physicianNameLast.add(physicianNameObject);
                    }
                }
                stage_X.put("physician_name", physicianNameLast);
                //获取病情变异记录中的状态
                int status = stage_X.getJSONObject("disease_variation").getInt("status");
                stage_X.getJSONObject("disease_variation").put("status", "" + status);
                if (status != 0) {
                    JSONArray reasonArray = stage_X.getJSONObject("disease_variation").getJSONArray("reason");
                    JSONObject reasonObject = null;
                    JSONArray reasonLast = new JSONArray();
                    for (int j = 0; j < reasonArray.size(); j++) {
                        if (!reasonArray.getString(j).equals("")) {
                            reasonObject = new JSONObject();
                            //存放变异原因
                            reasonObject.put("reason", reasonArray.getString(j));
                            reasonLast.add(reasonObject);
                        } else {
                            reasonObject = new JSONObject();
                            //存放变异原因
                            reasonObject.put("reason", "");
                            reasonLast.add(reasonObject);
                        }
                    }
                    stage_X.getJSONObject("disease_variation").put("reason", reasonLast);
                }

                JSONObject dayShiftObject = stage_X.getJSONObject("nurse_onduty").getJSONObject("day_shift");
                JSONObject nightShiftSmallObject = stage_X.getJSONObject("nurse_onduty").getJSONObject("night_shift_s");
                JSONObject nightShiftBigObject = stage_X.getJSONObject("nurse_onduty").getJSONObject("night_shift_b");
                JSONObject timeUndefindObject = stage_X.getJSONObject("nurse_onduty").getJSONObject("time_undefined");
                //day_shift
                //  if(dayShiftObject!=null) {
                JSONArray dayShiftNameArray = dayShiftObject.getJSONArray("nurse_name");
                JSONObject dayShiftNameObject = null;
                JSONArray dayShiftNameLast = new JSONArray();
                if (dayShiftNameArray.size() != 0) {
                    for (int j = 0; j < dayShiftNameArray.size(); j++) {
                        //if (!dayShiftNameArray.getString(j).equals("")) {
                        dayShiftNameObject = new JSONObject();//为什么放这里？
                        dayShiftNameObject.put("dayShiftName", dayShiftNameArray.getString(j));
                        dayShiftNameLast.add(dayShiftNameObject);
                        //
                    }
                } else {
                    dayShiftNameObject = new JSONObject();
                    dayShiftNameObject.put("dayShiftName", "无");
                    dayShiftNameLast.add(dayShiftNameObject);
                }
                stage_X.getJSONObject("nurse_onduty").put("day_shift", dayShiftNameLast);

                //       }
                // night_shift_s
                //    if(nightShiftSmallObject!=null) {
                JSONArray nightShiftNameArray = nightShiftSmallObject.getJSONArray("nurse_name");
                JSONObject nightShiftNameObject = null;
                JSONArray nightShiftNameLast = new JSONArray();
                if (nightShiftNameArray.size() != 0) {
                    for (int j = 0; j < nightShiftNameArray.size(); j++) {
                        nightShiftNameObject = new JSONObject();//为什么放这里？
                        nightShiftNameObject.put("nightShiftSmallName", nightShiftNameArray.getString(j));
                        nightShiftNameLast.add(nightShiftNameObject);
                    }
                } else {
                    nightShiftNameObject = new JSONObject();
                    nightShiftNameObject.put("nightShiftSmallName", "无");
                    nightShiftNameLast.add(nightShiftNameObject);
                }
                stage_X.getJSONObject("nurse_onduty").put("night_shift_s", nightShiftNameLast);
                //    }

                //    if(nightShiftBigObject!=null) {
                JSONArray nightShiftBigNameArray = nightShiftBigObject.getJSONArray("nurse_name");
                JSONObject nightShiftBigNameObject = null;
                JSONArray nightShiftBigNameLast = new JSONArray();
                if (nightShiftBigNameArray.size() != 0) {
                    for (int j = 0; j < nightShiftBigNameArray.size(); j++) {
                        nightShiftBigNameObject = new JSONObject();//为什么放这里？
                        nightShiftBigNameObject.put("nightShiftBigName", nightShiftBigNameArray.getString(j));
                        nightShiftBigNameLast.add(nightShiftBigNameObject);
                    }
                } else {
                    nightShiftBigNameObject = new JSONObject();
                    nightShiftBigNameObject.put("nightShiftBigName", "无");
                    nightShiftBigNameLast.add(nightShiftBigNameObject);
                }
                stage_X.getJSONObject("nurse_onduty").put("night_shift_b", nightShiftBigNameLast);
                //    }

                //    if(timeUndefindObject!=null) {
                JSONArray timeUndefindArray = timeUndefindObject.getJSONArray("nurse_name");
                JSONObject timeUndefindNameObject = null;
                JSONArray timeUndefindNameLast = new JSONArray();
                if (timeUndefindArray.size() != 0) {
                    for (int j = 0; j < timeUndefindArray.size(); j++) {
                        timeUndefindNameObject = new JSONObject();//为什么放这里？
                        timeUndefindNameObject.put("timeUnderfindName", timeUndefindArray.getString(j));
                        timeUndefindNameLast.add(timeUndefindNameObject);
                    }
                } else {
                    timeUndefindNameObject = new JSONObject();
                    timeUndefindNameObject.put("timeUnderfindName", "无");
                    timeUndefindNameLast.add(timeUndefindNameObject);
                }
                stage_X.getJSONObject("nurse_onduty").put("time_undefined", timeUndefindNameLast);
                //     }
                //  stage_X.getString("stage_num").equals("0");
                treatmentInfoLast.put("stage_num", num);
                // stage_X.clear();
                treatmentInfoLast.put("stage", stage_X);
                System.out.println("最终数据:" + treatmentInfoLast);
                return treatmentInfoLast;
            }
        }

        if (treatmentInfoLast == null || treatmentInfoLast.size() == 0) {
            error.put("errstatus", 500);
            error.put("errmsg", "该阶段不存在该数据!");
        }

        return treatmentInfoLast;
    }
}
