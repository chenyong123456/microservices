package cn.knowimage.JsonPojo.MakeJson;

import cn.knowimage.JsonPojo.MakeJson.MakePathway_Info.*;
import cn.knowimage.demo.imageOut;
import cn.knowimage.pojo.PathwayInfo;
import cn.knowimage.pojo.ReceivePathway;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

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

        List<List<List<String>>> allValue = new ArrayList<>();
        JSONObject jsonObject  = JSONObject.fromObject(pathwayInfo.getTable_info());
        List list = new ArrayList();
        for (int i = 0 ; i<jsonObject.getInt("table_num");i++) {
            JSONObject table_info = jsonObject.getJSONObject("table_"+i);
            JSONArray content = table_info.getJSONArray("content");
            for (int j = 0; j < table_info.getInt("row_count");j++) {
                List<String> contents = new ArrayList<>();
                for (int k = 0 ; k < table_info.getInt("column_count");k++) {
                    contents.add(content.getJSONArray(j).getString(k));
                }
                list.add(contents);
            }
            allValue.add(list);
            List<String> titles = new ArrayList<>();
            titles.add(table_info.getString("below_description"));
            List<String[]> headers = new ArrayList<>();
            String[] h1 = new String[]{"名字","年龄","身高","婚姻","啊啊啊啊","asdasdas"};
            headers.add(h1);
            try {
                imageOut.graphicsGeneration(allValue,titles,headers,"",2);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return pathwayInfo;
    }


}
