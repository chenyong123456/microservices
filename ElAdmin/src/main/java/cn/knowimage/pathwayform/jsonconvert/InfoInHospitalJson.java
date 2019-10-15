package cn.knowimage.pathwayform.jsonconvert;

import net.sf.json.JSONObject;

/**
 * InfoInHospitalJson   将InfoInHospital字段转为数据库所需的json格式
 * @author lyx
 * @date 2019/8/22
 */
public class InfoInHospitalJson {
    private static JSONObject infoInHospital;
    private static JSONObject standardEntryDays;

    public static String getInfoHospitalJson(String outpatientId, String hospitalId,
                                             String adminssionDate, String dischargeDate,
                                             String timeUnit,String min,String max) {

        infoInHospital = new JSONObject();
        standardEntryDays = new JSONObject();
        infoInHospital.put("outpatient_id",outpatientId);
        infoInHospital.put("hospital_id",hospitalId);
        infoInHospital.put("admission_date",adminssionDate);
        infoInHospital.put("discharge_date",dischargeDate);

        standardEntryDays.put("time_unit",timeUnit);
        if(max!=null&&!max.equals("")){
            standardEntryDays.put("max",Integer.parseInt(max));
        }
        if(max==null||max.equals("")) {
            standardEntryDays.put("max", 0);
        }
        if(min!=null&&!min.equals("")){
            standardEntryDays.put("min",Integer.parseInt(min));
        }
        if(min==null||min.equals("")){
            standardEntryDays.put("min",0);
        }

        infoInHospital.put("standard_entry_days",standardEntryDays);
        System.out.println(infoInHospital.toString());
        return infoInHospital.toString();
    }

    public static void main(String[] args) {
        InfoInHospitalJson info = new InfoInHospitalJson();
        info.getInfoHospitalJson("1","住院号",
                "住院日期：2019/8/22","出院日期：2019/8/22","日","0","0");
    }
}
