package cn.knowimage.pathwayform.jsonconvert;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.eclipse.jetty.util.StringUtil;

/**
 * TreatmentInfo 医疗信息字段 转换为json格式,做新增时使用
 *
 * @author lyx
 * @date 2019/08/22
 */
public class TreatmentInfoJson {
    /**
     * 医疗信息
     */
    private static JSONObject treatmentInfo;
    /**
     * 阶段号
     */
    private static JSONObject stageX;
    /**
     * 手术日
     */
    private static JSONObject operationDay;

    /**
     * 阶段的住院时间
     */
    private static JSONObject currentTime;
    /**
     * 重点医嘱
     */
    private static JSONObject keyOrders;
    /**
     * 病情变异记录
     */
    private static JSONObject diseaseVariation;
    /**
     * 护士值班时间段
     */
    private static JSONObject nurseOnduty;
    /**
     * 白班
     */
    private static JSONObject dayShift;
    /**
     * 小夜班
     */
    private static JSONObject nightShiftS;
    /**
     * 大夜班
     */
    private static JSONObject nightShiftB;
    /**
     * 无明确时间
     */
    private static JSONObject timeUndefined;
    /**
     * 主要诊断工作
     */
    private static JSONArray primaryDiagnosi;
    /**
     * 长期医嘱
     */
    private static JSONArray longTermOrders;
    /**
     * 临时医嘱
     */
    private static JSONArray temporaryOrders;
    /**
     * 出院医嘱
     */
    private static JSONArray dischargeOrders;
    /**
     * 重点注意事项
     */
    private static JSONArray notification;
    /**
     * 主要护理工作
     */
    private static JSONArray majorNursingWork;
    /**
     * 变异原因
     */
    private static JSONArray reason;
    /**
     * 护士签名
     */
    private static JSONArray nurseName;
    /**
     * 医师签名
     */
    private static JSONArray physicianName;
    /**
     * 阶段描述相关信息(2019.9.30新增)
     */
    private static JSONObject stageDescription;
    /**
     * 额外医嘱或者其他字段
     */
    private static JSONObject extraField;
    /**
     * 其他字段信息
     */
    private static JSONObject fieldInfoX;
    /**
     * 其他字段内容
     */
    private static JSONArray fieldContent;

    public static void main(String[] args) {
        getTreatmentInfoJsonData("[{\n" +
                "\t\"currentMin\": \"0\",\n" +
                "\t\"currentMax\": \"0\",\n" +
                "\t\"currentUnit\": \"\",\n" +
                "\t\"extra_description\": \"\",\n" +
                "\t\"options2\": [{\n" +
                "\t\t\"value\": \"天\",\n" +
                "\t\t\"label\": \"天\"\n" +
                "\t}, {\n" +
                "\t\t\"value\": \"时\",\n" +
                "\t\t\"label\": \"时\"\n" +
                "\t}, {\n" +
                "\t\t\"value\": \"周\",\n" +
                "\t\t\"label\": \"周\"\n" +
                "\t}, {\n" +
                "\t\t\"value\": \"月\",\n" +
                "\t\t\"label\": \"月\"\n" +
                "\t}],\n" +
                "\t\"prefix_description\": [{\n" +
                "\t\t\"value\": \"住院\",\n" +
                "\t\t\"label\": \"住院\"\n" +
                "\t}, {\n" +
                "\t\t\"value\": \"出院前\",\n" +
                "\t\t\"label\": \"出院前\"\n" +
                "\t}, {\n" +
                "\t\t\"value\": \"出院后\",\n" +
                "\t\t\"label\": \"出院后\"\n" +
                "\t}, {\n" +
                "\t\t\"value\": \"其他描述\",\n" +
                "\t\t\"label\": \"其他描述\"\n" +
                "\t}],\n" +
                "\t\"fill_prefix_description\": \"住院\",\n" +
                "\t\"isOperation\": \"0\",\n" +
                "\t\"operationNum\": \"\",\n" +
                "\t\"primary_diagnosi\": [],\n" +
                "\t\"key_orders\": {\n" +
                "\t\t\"long_term_orders\": [],\n" +
                "\t\t\"long_term_extra_text\": \"\",\n" +
                "\t\t\"temporary_orders\": [],\n" +
                "\t\t\"temporary_extra_text\": \"\",\n" +
                "\t\t\"discharge_orders\": [],\n" +
                "\t\t\"discharge_extra_text\": \"\",\n" +
                "\t\t\"notification\": [],\n" +
                "\t\t\"extra_field\": [{\n" +
                "\t\t\t\"field_info\": \"\",\n" +
                "\t\t\t\"field_content\": []\n" +
                "\t\t}]\n" +
                "\t},\n" +
                "\t\"major_nursing_work\": [],\n" +
                "\t\"disease_variation_status\": \"0\",\n" +
                "\t\"reason\": []\n" +
                "}, {\n" +
                "\t\"currentMin\": \"0\",\n" +
                "\t\"currentMax\": \"0\",\n" +
                "\t\"currentUnit\": \"\",\n" +
                "\t\"options2\": [{\n" +
                "\t\t\"value\": \"天\",\n" +
                "\t\t\"label\": \"天\"\n" +
                "\t}, {\n" +
                "\t\t\"value\": \"时\",\n" +
                "\t\t\"label\": \"时\"\n" +
                "\t}, {\n" +
                "\t\t\"value\": \"周\",\n" +
                "\t\t\"label\": \"周\"\n" +
                "\t}, {\n" +
                "\t\t\"value\": \"月\",\n" +
                "\t\t\"label\": \"月\"\n" +
                "\t}],\n" +
                "\t\"prefix_description\": [{\n" +
                "\t\t\"value\": \"住院\",\n" +
                "\t\t\"label\": \"住院\"\n" +
                "\t}, {\n" +
                "\t\t\"value\": \"出院前\",\n" +
                "\t\t\"label\": \"出院前\"\n" +
                "\t}, {\n" +
                "\t\t\"value\": \"出院后\",\n" +
                "\t\t\"label\": \"出院后\"\n" +
                "\t}, {\n" +
                "\t\t\"value\": \"其他描述\",\n" +
                "\t\t\"label\": \"其他描述\"\n" +
                "\t}],\n" +
                "\t\"fill_prefix_description\": \"住院\",\n" +
                "\t\"extra_description\": \"\",\n" +
                "\t\"isOperation\": \"0\",\n" +
                "\t\"operationNum\": \"\",\n" +
                "\t\"primary_diagnosi\": [],\n" +
                "\t\"key_orders\": {\n" +
                "\t\t\"long_term_orders\": [],\n" +
                "\t\t\"long_term_extra_text\": \"\",\n" +
                "\t\t\"temporary_orders\": [],\n" +
                "\t\t\"temporary_extra_text\": \"\",\n" +
                "\t\t\"discharge_orders\": [],\n" +
                "\t\t\"discharge_extra_text\": \"\",\n" +
                "\t\t\"notification\": [],\n" +
                "\t\t\"extra_field\": [{\n" +
                "\t\t\t\"field_info\": \"\",\n" +
                "\t\t\t\"field_content\": []\n" +
                "\t\t}]\n" +
                "\t},\n" +
                "\t\"major_nursing_work\": [],\n" +
                "\t\"disease_variation_status\": \"0\",\n" +
                "\t\"reason\": []\n" +
                "}]");
    }

    /**
     * 将治疗信息字段 转换为json格式
     *
     * @param stage 接收的前台阶段数据
     * @return String treatmentInfo.toString() treatmentInfo字符串格式
     */
    public static String getTreatmentInfoJsonData(String stage) {
        System.out.println("做修改用的stage呢==============================>" + stage);
        //将传过来的数据转换成 jsonArray对象
        JSONArray treatmentArray = JSONArray.fromObject(stage);
        //如果前端传过来的数据不为null并且不为空
        if (treatmentArray != null && treatmentArray.size() != 0) {
            //定义整形变量 存放阶段数
            int stageNum = treatmentArray.size();
            treatmentInfo = new JSONObject();
            treatmentInfo.put("stage_num", stageNum);
            /**
             * 循环遍历阶段数
             */
            for (int i = 0; i < stageNum; i++) {
                //构造stage阶段json对象
                stageX = new JSONObject();
                //定义字符串变量接收  描述前缀(住院/出院/住院前/出院前)
                String fillPrefixDescription = treatmentArray.getJSONObject(i).getString("fill_prefix_description");
                //定义字符串变量接收  额外描述信息
                String ExtraDescription = treatmentArray.getJSONObject(i).getString("extra_description");
                stageDescription = new JSONObject();
                stageDescription.put("prefix_description", fillPrefixDescription);
                stageDescription.put("extra_description", ExtraDescription);
                stageX.put("stage_description", stageDescription);
                //定义字符串变量接收  当前阶段住院天数最小值
                String min = treatmentArray.getJSONObject(i).getString("currentMin");
                //定义字符串变量接收  当前阶段住院天数最大值
                String max = treatmentArray.getJSONObject(i).getString("currentMax");
                //定义字符串变量接收  当前阶段时间单位
                String timeUnit = treatmentArray.getJSONObject(i).getString("currentUnit");
                //构造currentTime当前阶段住院时间json对象
                currentTime = new JSONObject();
                if (min != null && !min.equals("")) {
                    currentTime.put("min", Integer.parseInt(min));
                } else {
                    currentTime.put("min", 0);
                }
                if (max != null && !max.equals("")) {
                    currentTime.put("max", Integer.parseInt(max));
                } else {
                    currentTime.put("max", 0);
                }
                currentTime.put("time_unit", timeUnit);
                //current_time对象创建完成put到stage_X对象中
                stageX.put("current_time", currentTime);
                //接收手术日状态   0不是手术日   1是手术日
                String operationDayStatus = treatmentArray.getJSONObject(i).getString("isOperation");
                System.out.println("operationDayStatus==============>" + operationDayStatus);
                operationDay = new JSONObject();
                //构造operationDay json对象
                //1 有明确手术日
                if (operationDayStatus.equals("1")) {
                    operationDay.put("is_there_operation_day", 1);
                    //接收前台传过来的手术日
                    int currentOpertionDay = Integer.parseInt(treatmentArray.getJSONObject(i).getString("operationNum"));
                    //手术偏移日为 手术日减去住院第一天
                    operationDay.put("bias_num", currentOpertionDay);
                }
                //0  无手术日
                if (operationDayStatus.equals("0")) {
                    operationDay.put("is_there_operation_day", 0);
                    operationDay.put("bias_num", 0);
                }
                //2  无明确手术日
                if (operationDayStatus.equals("2")) {
                    operationDay.put("is_there_operation_day", 2);
                    operationDay.put("bias_num", 0);
                }
                stageX.put("operation_day", operationDay);
                //定义字符串变量 接收primary_diagnosi数据
                String primaryDiag = treatmentArray.getJSONObject(i).getString("primary_diagnosi");
                String[] primaryDiagArrary = primaryDiag.split("\n");

                primaryDiagnosi = new JSONArray();
                /**
                 *  循环遍历 去掉标签1^
                 */

                for (int j = 0; j < primaryDiagArrary.length; j++) {
                    //primaryDiagArrary[j].split("\\^").length<1判断字符串中是否有\\^该格式的
                    //防止数组越界
                    //   System.out.println("值呢"+primaryDiagArrary[j]+primaryDiagArrary[j].split("\\^")[1]);
                    if (primaryDiagArrary[j] == null || "".equals(primaryDiagArrary[j]) || primaryDiagArrary[j].split("\\^").length <= 1 ||
                            primaryDiagArrary[j].split("\\^")[1] == null) {
                        //  primaryDiagnosi.add("");
                    } else {
                        primaryDiagnosi.add(primaryDiagArrary[j].split("\\^")[1]);
                    }
                }
                stageX.put("primary_diagnosi", primaryDiagnosi);
                //构造key_orders字段json对象
                keyOrders = new JSONObject();
                //定义json对象变量接收 keyOrder重点医嘱json对象
                JSONObject keyOrderJson = treatmentArray.getJSONObject(i).getJSONObject("key_orders");
                String longTermOrder = keyOrderJson.getString("long_term_orders");
                longTermOrders = new JSONArray();
                String[] longTermOrdersArray = longTermOrder.split("\n");
                /**
                 * 循环进行切割longTermOrdersArray格式处理
                 */
                for (int j = 0; j < longTermOrdersArray.length; j++) {
                    //longTermOrdersArray[j].split("\\^").length<1判断字符串中是否有\\^该格式的
                    //防止数组越界
                    if (longTermOrdersArray[j] == null || "".equals(longTermOrdersArray[j]) || longTermOrdersArray[j].split("\\^").length <= 1 ||
                            longTermOrdersArray[j].split("\\^")[1] == null) {
                        //  longTermOrders.add("");
                    } else {
                        longTermOrders.add(longTermOrdersArray[j].split("\\^")[1]);
                    }
                }
                keyOrders.put("long_term_orders", longTermOrders);
                //定义字符串变量接收 长期医嘱额外描述
                String longTermExtraText = keyOrderJson.getString("long_term_extra_text");
                keyOrders.put("long_term_extra_text", longTermExtraText);
                //定义字符串变量接收 temporary_orders
                String temporaryOrder = keyOrderJson.getString("temporary_orders");
                temporaryOrders = new JSONArray();
                String[] temporaryOrdersArray = temporaryOrder.split("\n");
                /**
                 * 循环进行切割temporaryOrdersArray格式处理
                 */
                for (int j = 0; j < temporaryOrdersArray.length; j++) {
                    //temporary_ordersArray[j].split("\\^").length<1判断字符串中是否有^该格式的
                    //防止数组越界
                    if (temporaryOrdersArray[j] == null || "".equals(temporaryOrdersArray[j]) || temporaryOrdersArray[j].split("\\^").length <= 1 ||
                            temporaryOrdersArray[j].split("\\^")[1] == null) {
                        //      temporaryOrders.add("");
                    } else {
                        temporaryOrders.add(temporaryOrdersArray[j].split("\\^")[1]);
                    }

                }
                keyOrders.put("temporary_orders", temporaryOrders);
                //定义字符串变量接收 临时医嘱额外描述
                String temporaryExtraText = keyOrderJson.getString("temporary_extra_text");
                keyOrders.put("temporary_extra_text", temporaryExtraText);
                //定义字符串变量  接收dischargeOrder
                String dischargeOrder = keyOrderJson.getString("discharge_orders");
                //该字符串不为空 处理discharge_orders字段的数据-->空数组的格式 []
                //if(!StringUtil.isBlank(dischargeOrder)){
                dischargeOrders = new JSONArray();
                String[] dischargeOrdersArray = dischargeOrder.split("\n");
                System.out.println("数据的值-->" + dischargeOrder);
                System.out.println("数组大小----》" + dischargeOrdersArray.length);
                /**
                 * 循环进行切割dischargeOrdersArray格式处理
                 */
                for (int j = 0; j < dischargeOrdersArray.length; j++) {
                    System.out.println("数组越界大小--->" + dischargeOrdersArray[j].split("\\^").length);
                    //discharge_ordersArray[j].split("、").length != 2这个判断很重要,也可以用
                    //discharge_ordersArray[j].split("、").length <=1 严谨用第一个
                    if (dischargeOrdersArray[j] == null || "".equals(dischargeOrdersArray[j]) || dischargeOrdersArray[j].split("\\^").length != 2 ||
                            dischargeOrdersArray[j].split("\\^")[1] == null) {
                        //      dischargeOrders.add("");
                    } else {
                        dischargeOrders.add(dischargeOrdersArray[j].split("\\^")[1]);
                    }
                }
                // }
                String dischargeExtraText = keyOrderJson.getString("discharge_extra_text");
                keyOrders.put("discharge_orders", dischargeOrders);
                keyOrders.put("discharge_extra_text", dischargeExtraText);
                //处理notification字段的数据-->空数组的格式 []
                String notifications = keyOrderJson.getString("notification");
                notification = new JSONArray();
                //该字符串不为空
                if (StringUtil.isNotBlank(notifications)) {
                    String[] notificationArray = notifications.split("\n");
                    /**
                     * 循环进行切割notificationArray格式处理
                     */
                    for (int j = 0; j < notificationArray.length; j++) {
                        if (notificationArray[j] == null || "".equals(notificationArray[j]) || notificationArray[j].split("\\^").length != 2 ||
                                notificationArray[j].split("\\^")[1] == null) {
                            //   notification.add("");
                        } else {
                            notification.add(notificationArray[j].split("\\^")[1]);
                        }
                    }
                }
                keyOrders.put("notification", notification);

                //拿到前台传来的array对象
                JSONArray extraFieldArray = keyOrderJson.getJSONArray("extra_field");
                int fieldNum = 0;
 //               System.out.println("如果field_info为空"+extraFieldArray.getJSONObject(0).getString("field_info"));
                if ("".equals(extraFieldArray.getJSONObject(0).getString("field_info"))) {
                    System.out.println("进了空的");
                    //构造外医嘱或者其他字段json对象
                    extraField = new JSONObject();
                    extraField.put("field_num", fieldNum);
                } else {
                    System.out.println("没进空的");
                    extraField = new JSONObject();
                    fieldNum = extraFieldArray.size();
                    System.out.println("额外字段的长度" + fieldNum);
                    extraField.put("field_num", fieldNum);
                    for (int j = 0; j < fieldNum; j++) {
                        fieldInfoX = new JSONObject();
                        //定义字符串变量 接收前台传来的标题名
                        String title = extraFieldArray.getJSONObject(j).getString("field_info");
                        //定义字符串变量 接收前台传来的field_content(额外字段内容)
                        String fieldContentString = extraFieldArray.getJSONObject(j).getString("field_content");
                        fieldContent = new JSONArray();
                        //如果传来的额外字段内容不为空
                        if (StringUtil.isNotBlank(fieldContentString)){
                            //定义字符串数组存储根据\n切割后的数据
                            String[] contentArray = fieldContentString.split("\n");

                            /**
                             * 循环遍历根据\n切割后的content
                             * 进而进行下一步格式处理，去掉^
                             */
                            for (int k = 0; k < contentArray.length; k++) {
                                //处理格式
                                if (contentArray[k] == null || "".equals(contentArray[k]) || contentArray[k].split("\\^").length != 2
                                        || contentArray[k].split("\\^")[1] == null || "".equals(contentArray[k].split("\\^")[1])){

                                }else{
                                    fieldContent.add(contentArray[k].split("\\^")[1]);
                                }
                            }
                        }
                        System.out.println("contentArray>>>>title"+title);
                        fieldInfoX.put("title",title);
                        fieldInfoX.put("field_content",fieldContent);
                        extraField.put(String.format("field_info_%d",j),fieldInfoX);

                    }

                }
                keyOrders.put("extra_field",extraField);
                stageX.put("key_orders", keyOrders);
                //处理major_nursing_work字段的数据进行处理
                String majorNursingWorkString = treatmentArray.getJSONObject(i).getString("major_nursing_work");
                majorNursingWork = new JSONArray();
                //该字符串不为空
                if (!StringUtil.isBlank(majorNursingWorkString)) {
                    String[] majorNursingWorkArray = majorNursingWorkString.split("\n");
                    for (int j = 0; j < majorNursingWorkArray.length; j++) {
                        //major_nursing_workArray[j].split("\\^").length<1判断字符串中是否有^该格式的
                        //防止数组越界
                        if (majorNursingWorkArray[j] == null || "".equals(majorNursingWorkArray[j]) || majorNursingWorkArray[j].split("\\^").length != 2 ||
                                majorNursingWorkArray[j].split("\\^")[1] == null || "".equals(majorNursingWorkArray[j].split("\\^")[1])){
                        } else {
                            majorNursingWork.add(majorNursingWorkArray[j].split("\\^")[1]);
                        }
                    }
                }
                stageX.put("major_nursing_work", majorNursingWork);
                diseaseVariation = new JSONObject();
                String diseaseStatus = treatmentArray.getJSONObject(i).getString("disease_variation_status");
                System.out.println("diseaseStatus===========>" + diseaseStatus);
                //如果status为1--》有病情变异原因
                if (diseaseStatus.equals("1")) {

                    String reasonOrignal = treatmentArray.getJSONObject(i).getString("reason");
                    String[] reasonArray = reasonOrignal.split("\n");
                    reason = new JSONArray();
                    for (int j = 0; j < reasonArray.length; j++) {
                        if (reasonArray[j] == null || "".equals(reasonArray[j]) || reasonArray[j].split("\\^").length != 2 ||
                                reasonArray[j].split("\\^")[1] == null || "".equals(reasonArray[j].split("\\^")[1])) {
                            //  reason.add("变异原因 数组有误请输入正确的变异原因数据");
                        } else {
                            reason.add(reasonArray[j].split("\\^")[1]);
                        }
                    }
                    diseaseVariation.put("status", 1);
                    diseaseVariation.put("reason", reason);
                } else {
                    diseaseVariation.put("status", 0);
                    diseaseVariation.put("reason", new JSONArray());
                    //diseaseVariation.put("reason",new JSONArray());[]这种格式的数据
                }
                stageX.put("disease_variation", diseaseVariation);
                //构造nurse_onduty json对象
                nurseOnduty = new JSONObject();
                dayShift = new JSONObject();
                nurseOnduty.put("day_shift", dayShift);
                nightShiftS = new JSONObject();
                nurseOnduty.put("night_shift_s", nightShiftS);
                nightShiftB = new JSONObject();
                nurseOnduty.put("night_shift_b", nightShiftB);
                timeUndefined = new JSONObject();
                nurseOnduty.put("time_undefined", timeUndefined);
                //初始化护士值班时间段  这个地方可能为空key = nurseOndutyData
                String nurseOndutyData = "";
                //treatmentArray.getJSONObject(i).containsKey("nurse_onduty")判断是否有key/value
                //treatmentArray.getJSONObject(i).containsValue("csc");
                //treatmentArray.getJSONObject(i).has("nurse_onduty");同理判断是否有key/value
                if (treatmentArray.getJSONObject(i).containsKey("nurse_onduty")) {
                    nurseOndutyData = treatmentArray.getJSONObject(i).getString("nurse_onduty");
                }
                System.out.println("nurseOndutyData-->" + nurseOndutyData);
                //如果nurseOndutyData不为空
                if (!StringUtil.isBlank(nurseOndutyData)) {
                    nurseName = new JSONArray();
                    nurseOnduty.getJSONObject("day_shift").put("nurse_name", nurseName);
                    //用完一次清空一次,避免重复值出现的bug
                    nurseName.clear();
                    nurseOnduty.getJSONObject("night_shift_s").put("nurse_name", nurseName);
                    //用完一次清空一次
                    nurseName.clear();
                    nurseOnduty.getJSONObject("night_shift_b").put("nurse_name", nurseName);
                    //用完一次清空一次
                    nurseName.clear();
                    nurseOnduty.getJSONObject("time_undefined").put("nurse_name", nurseName);
                    //用完一次清空一次
                    nurseName.clear();
                    stageX.put("nurse_onduty", nurseOnduty);
                } else {
                    //nurseOndutyData为空
                    JSONArray nurseNameNull = new JSONArray();
                    nurseOnduty.getJSONObject("day_shift").put("nurse_name", nurseNameNull);
                    nurseOnduty.getJSONObject("night_shift_s").put("nurse_name", nurseNameNull);
                    nurseOnduty.getJSONObject("night_shift_b").put("nurse_name", nurseNameNull);
                    nurseOnduty.getJSONObject("time_undefined").put("nurse_name", nurseNameNull);
                }
                stageX.put("nurse_onduty", nurseOnduty);
                //初始化医生签名 这个地方可能为空physician_name字段
                String physicianNameString = "";
                if (treatmentArray.getJSONObject(i).has(physicianNameString)) {
                    physicianNameString = treatmentArray.getJSONObject(i).getString("physician_name");
                }
                System.out.println("physician_name-->" + physicianNameString);
                physicianName = new JSONArray();
                if (!StringUtil.isBlank(physicianNameString)) {
                    String[] physicianNameArray = physicianNameString.split("\n");
                    for (int j = 0; j < physicianNameArray.length; j++) {
                        //physicianNameArray[j].split(".").length <= 1判断字符串中是否有、该格式的
                        //防止数组越界
                        if (physicianNameArray[j] == null || "".equals(physicianNameArray[j]) || physicianNameArray[j].split("\\^").length != 2 ||
                                primaryDiagArrary[j].split("\\^")[1] == null || "".equals(primaryDiagArrary[j].split("\\^")[1])) {
                            //         primaryDiagnosi.add("");
                        } else {
                            primaryDiagnosi.add(primaryDiagArrary[j].split("\\^")[1]);
                        }
                        physicianName.add(physicianNameArray[j].split("\\^")[1]);
                    }
                }
                stageX.put("physician_name", physicianName);
                treatmentInfo.put(String.format("stage_%d", i), stageX);
            }
        } else {
            treatmentInfo = new JSONObject();
        }
        System.out.println("hello"+treatmentInfo.toString());
        return treatmentInfo.toString();
    }
}
