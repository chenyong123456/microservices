package cn.knowimage.JsonPojo.MakeJson;

import cn.knowimage.JsonPojo.MakeJson.MakePathway_Info.*;
import cn.knowimage.util.tableImageOut;
import cn.knowimage.pojo.PathwayInfo;
import cn.knowimage.pojo.ReceivePathway;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.*;

@Component
public class MakeJsonPathway {

    public static PathwayInfo newPathwayInfo(ReceivePathway receivePathway) {

        PathwayInfo pathwayInfo = new PathwayInfo();
        //创建pathwayInfo的Pathway_index
        String fileNumber = receivePathway.getFileNumber();
        if (receivePathway.getFileNumber().equals("")) {
            fileNumber = "0000" + fileNumber;
        } else if (fileNumber.length() == 1) {
            fileNumber = "000" + fileNumber;
        } else if (fileNumber.length() == 2) {
            fileNumber = "00" + fileNumber;
        } else if (fileNumber.length() == 3) {
            fileNumber = "0" + fileNumber;
        }
        //设置时间格式，方便构造时使用
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //构造pathway_index
        String pathway_index = receivePathway.getPublisher() + receivePathway.getPublishYear() + fileNumber + receivePathway.getVersionNumber();
        pathwayInfo.setPathway_index(pathway_index);
        pathwayInfo.setFirst_diagnosis(receivePathway.getFirst_diagnose());
        pathwayInfo.setPathway_name(receivePathway.getPathway_name());
        pathwayInfo.setSuitable_subject_disc(receivePathway.getSuitable_subject_disc());
        pathwayInfo.setDiagnosis(receivePathway.getDiagnosis());
        pathwayInfo.setTreatment_choice(MakeTreatmentChoice.make(receivePathway));
        pathwayInfo.setTreatment_entry_standard(MakeTreatmentEntryStandard.make(receivePathway));
        pathwayInfo.setType(receivePathway.getPrep_treatment_common());
        pathwayInfo.setDrug_use_period(Integer.parseInt(receivePathway.getRadio()));
        pathwayInfo.setPrep_treatment_common(MakePrepTreatmentCommon.make(receivePathway));
        pathwayInfo.setPrep_treatment_drug_usage(MakePrepTreatmentDrugUsage.make(receivePathway));
        pathwayInfo.setPrep_treatment_extension(MakePrepTreatmentExtension.make(receivePathway));
        pathwayInfo.setTreatment(MakeTreatment.make(receivePathway));
        pathwayInfo.setTreatment_days(MakeTreatmentDays.make(receivePathway));
        pathwayInfo.setDrug_usage(MakeDrugUsage.make(receivePathway));
        pathwayInfo.setAfter_medical_treatment(MakeAfterMedicalTreatment.make(receivePathway));
        pathwayInfo.setAfter_treatment_drug_usage(MakeAfterTreatmentDrugUsage.make(receivePathway));
        pathwayInfo.setDischarge_criteria(MakeDischargeCriteria.make(receivePathway));
        pathwayInfo.setOther_notice(MakeOtherNotice.make(receivePathway));
        pathwayInfo.setAdditional_field(MakeAdditionalField.make(receivePathway));
        pathwayInfo.setData_upload_moment(df.format(new Date()));
        pathwayInfo.setAudit_state(0);
        pathwayInfo.setSubmitter_id(receivePathway.getSubmitterid());
        pathwayInfo.setCommit_state(receivePathway.getCommit());
        pathwayInfo.setEditor_id(receivePathway.getSubmitterid());
        pathwayInfo.setTable_info(MakeTableInfo.make(receivePathway));

        tableImageOut cg = new tableImageOut();
        String tableA = pathwayInfo.getTable_info();
        JSONObject jsonObject = JSONObject.fromObject(tableA);
        int num = jsonObject.getInt("table_num");
        for (int i = 0 ; i<num ;i++){
            JSONObject table_ = jsonObject.getJSONObject("table_"+i);
            int row = table_.getInt("row_count");
            int col = table_.getInt("column_count");
            String[][] tableInfo = new String[row][col];
            String title_info =table_.getString("below_description");
            int maxLength=0;
            String str="";
            for (int m = 0 ; m<row ;m++ ) {
                for (int n = 0; n < col; n++) {
                    tableInfo[m][n] = (String) table_.getJSONArray("content").getJSONArray(m).get(n);
                    if (tableInfo[m][n].length()>maxLength){
                        str = tableInfo[m][n];
                        maxLength=tableInfo[m][n].length();
                    }
                }
            }
            String table_prefix = table_.getString("table_prefix");
            cg.myGraphicsGeneration(tableInfo,"C:\\Users\\wh123\\Desktop\\HospitalProject\\TableImages\\"+pathway_index+"-"+table_prefix+".png",title_info,str);
        }
        return pathwayInfo;
    }


}
