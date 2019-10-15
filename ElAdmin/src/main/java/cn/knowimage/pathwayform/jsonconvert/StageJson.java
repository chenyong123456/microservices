package cn.knowimage.pathwayform.jsonconvert;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import java.util.Iterator;

public class StageJson{
    /**
     * 医疗信息
     */
    private static JSONObject treatmentInfo;
    /**
     * 阶段号
     */
    private static JSONObject stageX;
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
    private static JSONArray primaryDiagnosiArray;
    /**
     * 长期医嘱
     */
    private static JSONArray longTermOrdersArray;
    /**
     * 临时医嘱
     */
    private static JSONArray temporaryOrdersArray;
    /**
     * 出院医嘱
     */
    private static JSONArray dischargeOrdersArray;
    /**
     * 重点注意事项
     */
    private static JSONArray notificationArray;
    /**
     * 主要护理工作
     */
    private static JSONArray majorNursingWorkArray;
    /**
     * 变异原因
     */
    private static JSONArray reasonArray;
    /**
     * 护士签名
     */
    private static JSONArray nurseNameArray;
    /**
     * 医师签名
     */
    private static JSONArray physicianNameArray;

    /**
     * 将前台传过来的数据处理成数据库中的格式(做编辑时使用)
     * @param primary_diagnosi
     * @param long_term_orders
     * @param temporary_orders
     * @param discharge_orders
     * @param notification
     * @param major_nursing_work
     * @param disease_variation_status
     * @param reason
     * @param nurse_onduty
     * @param day_shiftName
     * @param night_shiftSName
     * @param night_shiftBName
     * @param time_undefinedName
     * @param physician_name
     * @param current_time
     * @return
     */
    public static JSONObject getStageJson(String primary_diagnosi,String long_term_orders,String temporary_orders,
                           String discharge_orders,String notification,String major_nursing_work,
                           String disease_variation_status,String reason,
                           String nurse_onduty,String day_shiftName,
                           String night_shiftSName,String night_shiftBName,
                           String time_undefinedName,String physician_name,String current_time){
        treatmentInfo = new JSONObject();
        stageX = new JSONObject();
        currentTime = new JSONObject();

        String[] str=current_time.split("-");
        System.out.println("最小值"+str[0]);
        System.out.println("最大值"+str[1].split("天|时|月|周")[0]);
        currentTime.put("c_min",Integer.parseInt(str[0]));
        currentTime.put("c_max",Integer.parseInt(str[1].split("天|时|月|周")[0]));
        keyOrders = new JSONObject();
        primaryDiagnosiArray = getOrdersArray(primary_diagnosi);
        longTermOrdersArray  = getOrdersArray(long_term_orders);
        temporaryOrdersArray = getOrdersArray(temporary_orders);
        dischargeOrdersArray = getOrdersArray(discharge_orders);
        notificationArray = getOrdersArray(notification);
        stageX.put("current_time",currentTime);
        stageX.put("primary_diagnosi",primaryDiagnosiArray);
        keyOrders.put("long_term_orders",longTermOrdersArray);
        keyOrders.put("temporary_orders",temporaryOrdersArray);
        keyOrders.put("discharge_orders",dischargeOrdersArray);
        keyOrders.put("notification",notificationArray);

        stageX.put("key_orders",keyOrders);

        majorNursingWorkArray = getOrdersArray(major_nursing_work);
        stageX.put("major_nursing_work",majorNursingWorkArray);
        diseaseVariation = new JSONObject();
        if(disease_variation_status.equals("1")){
            diseaseVariation.put("status",1);
            diseaseVariation.put("reason",getNameAndReasonArray(reason));
        }else{
            diseaseVariation.put("status",0);
            diseaseVariation.put("reason",new JSONArray());
        }
        stageX.put("disease_variation",diseaseVariation);
        nurseOnduty = new JSONObject();
        dayShift = new JSONObject();
        nightShiftS = new JSONObject();
        nightShiftB = new JSONObject();
        timeUndefined = new JSONObject();

        dayShift.put("nurse_name",getNameAndReasonArray(day_shiftName));
        nightShiftS.put("nurse_name",getNameAndReasonArray(night_shiftSName));
        nightShiftB.put("nurse_name",getNameAndReasonArray(night_shiftBName));
        timeUndefined.put("nurse_name",getNameAndReasonArray(time_undefinedName));

        nurseOnduty.put("day_shift",dayShift);
        nurseOnduty.put("night_shift_s",nightShiftS);
        nurseOnduty.put("night_shift_b",nightShiftB);
        nurseOnduty.put("time_undefined",timeUndefined);
        stageX.put("nurse_onduty",nurseOnduty);
        stageX.put("physician_name",getNameAndReasonArray(physician_name));
        //stageX.put("stage_num",1);
        System.out.println("stage_0********"+stageX.toString());
        return  stageX;
    }

    public static JSONArray getNameAndReasonArray(String data){
        JSONArray arrayResult = new JSONArray();
        if(data==null||data.equals("")){
            arrayResult = new JSONArray();
        }else{
            JSONArray array = JSONArray.fromObject(data);
            System.out.println(array.toString()+array.size());
            if(array != null && array.size() != 0){
                for (int i = 0; i < array.size(); i++) {
                    System.out.println("arr"+array.get(i));
                    if (!array.get(i).equals("")) {
                        //这里注意了取的key值,由于每一个字段的key是不一样的
                        Iterator<String> keys = array.getJSONObject(i).keys();
                        // keys.hasNext();
                        while (keys.hasNext()){
                            String string = array.getJSONObject(i).getString(keys.next());
                            System.out.println("数据呢"+string);
                            if(!string.equals("无")){
                                arrayResult.add(string);
                            }
                            System.out.println("字符串内容===========》" + arrayResult.toString());
                        }
                    }
                }

            }

        }
        return arrayResult;
    }

 /*   public static void main(String[] args) {
        getNameAndReasonArray("[{\"reason\":\"休息不够\"},{\"reason\":\"没吃饱\"}]");
    }*/

    /**
     * [{"id":false,"primaryDiagnosi":"询问病史和体格检查"},{"id":false,"primaryDiagnosi":"完成病历书写"}]
     * @param data 为数组中包含是数据JSONObject这种形式的数据对象
     * 只将值primaryDiagnosi放入到数组中去,转换为数据库中指定的json格式的数据格式
     * @return 返回一个JSONArray的数组
     */
    public static JSONArray getOrdersArray(String data){
        JSONArray arrayResult = new JSONArray();
        if(data == null || data.equals("") || data.equals("null")){

        }else{
            JSONArray array = JSONArray.fromObject(data);
            //在这里控制一下,保证数组中什么都没有,程序不会报异常
            if(array != null && array.size() != 0) {
                for (int i = 0; i < array.size(); i++) {
                    if(!array.get(i).equals("")) {
                        Boolean status = array.getJSONObject(i).getBoolean("id");
                        if (status) {
                            //这里要keys.next();两下,将指针向下移动两下
                            //获得对象的key可以这样得到
                            Iterator<String> keys = array.getJSONObject(i).keys();
                            //拿第二个键
                            keys.next();
                            //这里取的是数值 循环取出第二个键的值
                            String string = array.getJSONObject(i).getString(keys.next());
                            //！！！
                            arrayResult.add(string);
                        }
                    }
                }
            }
        }
        System.out.println("字符串内容===========》"+arrayResult.toString());
        return arrayResult;
    }

    public static void main(String[] args) {
        getOrdersArray("[{\"id\":false,\"primaryDiagnosi\":\"询问病史及体格检查\"},{\"id\":false,\"primaryDiagnosi\":\"病情告知\"},{\"id\":false,\"primaryDiagnosi\":\"如患儿病情重，应及时通知上级医师\"}]\n");
     //     getNameAndReasonArray("[{\"nightShiftSmallName\":\"无\"}]");
    }
}
