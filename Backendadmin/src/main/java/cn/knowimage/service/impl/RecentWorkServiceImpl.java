package cn.knowimage.service.impl;

import cn.knowimage.mapper.PathwayInfoMapper;
import cn.knowimage.mapper.RecentWorkMapper;
import cn.knowimage.pojo.PathwayInfo;
import cn.knowimage.pojo.RecentWork;
import cn.knowimage.service.RecentWorkService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class RecentWorkServiceImpl implements RecentWorkService {
    @Autowired
    RecentWorkMapper recentWorkMapper;
    @Autowired
    PathwayInfoMapper pathwayInfoMapper;
    @Async
    @Override
    public void insertRecentWork(PathwayInfo newPathwayInfo) {
        System.out.println("|-----------开始进行添加新的日志在RecentWork表中---------");
        //首先判断oldPathwayInfo存在否
        PathwayInfo oldPathwayInfo =pathwayInfoMapper.findPathwayInfoByIndex(newPathwayInfo.getPathway_index());
        //创建时间
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        RecentWork recentWork = new RecentWork();
        System.out.println(oldPathwayInfo);
        if (oldPathwayInfo==null){
            recentWork.setCreate_time(formatter.format(new Date()));
            recentWork.setCp_index(newPathwayInfo.getPathway_index());
            recentWork.setUser_id(newPathwayInfo.getSubmitter_id());
            recentWork.setMethod("提交了字段"+newPathwayInfo.getPathway_name());
            recentWorkMapper.insertRecentWork(recentWork);
        }else {
            System.out.println("-------------Json判断----------------");
            System.out.println();
            recentWork.setCreate_time(formatter.format(new Date()));
            recentWork.setCp_index(newPathwayInfo.getPathway_index());
            recentWork.setUser_id(newPathwayInfo.getSubmitter_id());
            try {
                JSONAssert.assertEquals(oldPathwayInfo.getTreatment_choice(),newPathwayInfo.getTreatment_choice(),false);
            } catch (Throwable e) {
                System.out.println("|-----------修改治疗方案的选择---------");
                System.out.println("old="+oldPathwayInfo.getTreatment_choice());
                System.out.println("new="+newPathwayInfo.getTreatment_choice());
                recentWork.setMethod("提交修改了"+newPathwayInfo.getPathway_name()+"字段治疗方案的选择");
                recentWorkMapper.insertRecentWork(recentWork);
            }

            try {
                JSONAssert.assertEquals(oldPathwayInfo.getTreatment_days(),newPathwayInfo.getTreatment_days(),false);
            }catch (Throwable e) {
                System.out.println("|-----------修改标准住院日---------");
                System.out.println("old="+oldPathwayInfo.getTreatment_days());
                System.out.println("new="+newPathwayInfo.getTreatment_days());
                recentWork.setMethod("提交修改了"+newPathwayInfo.getPathway_name()+"字段标准住院日");
                recentWorkMapper.insertRecentWork(recentWork);
            }

            try {
                JSONAssert.assertEquals(oldPathwayInfo.getTreatment_days(),newPathwayInfo.getTreatment_days(),false);
            }catch (Throwable e) {
                System.out.println("|-----------修改进入路径标准---------");
                System.out.println("old="+oldPathwayInfo.getTreatment_entry_standard());
                System.out.println("new="+newPathwayInfo.getTreatment_entry_standard());
                recentWork.setMethod("提交修改了"+newPathwayInfo.getPathway_name()+"字段进入路径标准");
                recentWorkMapper.insertRecentWork(recentWork);
            }

            try {
                JSONAssert.assertEquals(oldPathwayInfo.getPrep_treatment_common(),newPathwayInfo.getPrep_treatment_common(),false);
            }catch (Throwable e) {
                System.out.println("|-----------修改手术前的检查项目---------");
                System.out.println("old="+oldPathwayInfo.getFirst_diagnosis());
                System.out.println("new="+newPathwayInfo.getFirst_diagnosis());
                recentWork.setMethod("提交修改了"+newPathwayInfo.getPathway_name()+"字段手术前的检查项目");
                recentWorkMapper.insertRecentWork(recentWork);
            }

            try {
                JSONAssert.assertEquals(oldPathwayInfo.getPrep_treatment_drug_usage(),newPathwayInfo.getPrep_treatment_drug_usage(),false);
            }catch (Throwable e) {
                System.out.println("|-----------修改手术前用药情况---------");
                System.out.println("old="+oldPathwayInfo.getPrep_treatment_drug_usage());
                System.out.println("new="+newPathwayInfo.getPrep_treatment_drug_usage());
                recentWork.setMethod("提交修改了"+newPathwayInfo.getPathway_name()+"字段手术前用药情况");
                recentWorkMapper.insertRecentWork(recentWork);
            }

            try {
                JSONAssert.assertEquals(oldPathwayInfo.getPrep_treatment_extension(),newPathwayInfo.getPrep_treatment_extension(),false);
            }catch (Throwable e) {
                System.out.println("|-----------修改手术前准备工作---------");
                System.out.println("old="+oldPathwayInfo.getPrep_treatment_extension());
                System.out.println("new="+newPathwayInfo.getPrep_treatment_extension());
                recentWork.setMethod("提交修改了"+newPathwayInfo.getPathway_name()+"字段手术前准备工作");
                recentWorkMapper.insertRecentWork(recentWork);
            }

            try {
                JSONAssert.assertEquals(oldPathwayInfo.getTreatment(),newPathwayInfo.getTreatment(),false);
            }catch (Throwable e) {
                System.out.println("|-----------修改手术日内容---------");
                System.out.println("old="+oldPathwayInfo.getTreatment());
                System.out.println("new="+newPathwayInfo.getTreatment());
                recentWork.setMethod("提交修改了"+newPathwayInfo.getPathway_name()+"字段手术日内容");
                recentWorkMapper.insertRecentWork(recentWork);
            }

            try {
                JSONAssert.assertEquals(oldPathwayInfo.getDrug_usage(),newPathwayInfo.getDrug_usage(),false);
            }catch (Throwable e) {
                System.out.println("|-----------修改手术过程中用药情况---------");
                System.out.println("old="+oldPathwayInfo.getDrug_usage());
                System.out.println("new="+newPathwayInfo.getDrug_usage());
                recentWork.setMethod("提交修改了"+newPathwayInfo.getPathway_name()+"字段手术过程中用药情况");
                recentWorkMapper.insertRecentWork(recentWork);
            }

            try {
                JSONAssert.assertEquals(oldPathwayInfo.getAfter_medical_treatment(),newPathwayInfo.getAfter_medical_treatment(),false);
            }catch (Throwable e) {
                System.out.println("|-----------修改术后的复查和恢复性治疗内容---------");
                System.out.println("old="+oldPathwayInfo.getAfter_medical_treatment());
                System.out.println("new="+newPathwayInfo.getAfter_medical_treatment());
                recentWork.setMethod("提交修改了"+newPathwayInfo.getPathway_name()+"字段术后的复查和恢复性治疗内容");
                recentWorkMapper.insertRecentWork(recentWork);
            }

            try {
                JSONAssert.assertEquals(oldPathwayInfo.getAfter_treatment_drug_usage(),newPathwayInfo.getAfter_treatment_drug_usage(),false);
            }catch (Throwable e) {
                System.out.println("|-----------修改手术完成后的用药情况---------");
                System.out.println("old="+oldPathwayInfo.getAfter_treatment_drug_usage());
                System.out.println("new="+newPathwayInfo.getAfter_treatment_drug_usage());
                recentWork.setMethod("提交修改了"+newPathwayInfo.getPathway_name()+"字段手术完成后的用药情况");
                recentWorkMapper.insertRecentWork(recentWork);
            }

            try {
                JSONAssert.assertEquals(oldPathwayInfo.getDischarge_criteria(),newPathwayInfo.getDischarge_criteria(),false);
            }catch (Throwable e) {
                System.out.println("|-----------修改出院标准---------");
                System.out.println("old="+oldPathwayInfo.getDischarge_criteria());
                System.out.println("new="+newPathwayInfo.getDischarge_criteria());
                recentWork.setMethod("提交修改了"+newPathwayInfo.getPathway_name()+"字段出院标准");
                recentWorkMapper.insertRecentWork(recentWork);
            }

            try {
                JSONAssert.assertEquals(oldPathwayInfo.getOther_notice(),newPathwayInfo.getOther_notice(),false);
            }catch (Throwable e) {
                System.out.println("|-----------修改变异及原因分析---------");
                System.out.println("old="+oldPathwayInfo.getOther_notice());
                System.out.println("new="+newPathwayInfo.getOther_notice());
                recentWork.setMethod("提交修改了"+newPathwayInfo.getPathway_name()+"字段变异及原因分析");
                recentWorkMapper.insertRecentWork(recentWork);
            }

            try {
                JSONAssert.assertEquals(oldPathwayInfo.getAdditional_field(),newPathwayInfo.getAdditional_field(),false);
            }catch (Throwable e) {
                System.out.println("|-----------修改额外字段---------");
                System.out.println("old="+oldPathwayInfo.getAdditional_field());
                System.out.println("new="+newPathwayInfo.getAdditional_field());
                recentWork.setMethod("提交修改了"+newPathwayInfo.getPathway_name()+"字段额外字段");
                recentWorkMapper.insertRecentWork(recentWork);
            }

            if (!oldPathwayInfo.getFirst_diagnosis().equals(newPathwayInfo.getFirst_diagnosis())){
                System.out.println("|-----------修改第一诊断的医学编码---------");
                System.out.println("old="+oldPathwayInfo.getFirst_diagnosis());
                System.out.println("new="+newPathwayInfo.getFirst_diagnosis());
                recentWork.setMethod("提交修改了"+newPathwayInfo.getPathway_name()+"字段第一诊断的医学编码");
                recentWorkMapper.insertRecentWork(recentWork);
            }
            if (!oldPathwayInfo.getSuitable_subject_disc().equals(newPathwayInfo.getSuitable_subject_disc())){
                System.out.println("|-----------修改适用对象---------");
                System.out.println("old="+oldPathwayInfo.getSuitable_subject_disc());
                System.out.println("new="+newPathwayInfo.getSuitable_subject_disc());
                recentWork.setMethod("提交修改了"+newPathwayInfo.getPathway_name()+"字段适用对象");
                recentWorkMapper.insertRecentWork(recentWork);
            }
            if (!oldPathwayInfo.getDiagnosis().equals(newPathwayInfo.getDiagnosis())){
                System.out.println("|-----------修改诊断依据---------");
                System.out.println("old="+oldPathwayInfo.getDiagnosis());
                System.out.println("new="+newPathwayInfo.getDiagnosis());
                recentWork.setMethod("提交修改了"+newPathwayInfo.getPathway_name()+"字段诊断依据");
                recentWorkMapper.insertRecentWork(recentWork);
            }


            if (!oldPathwayInfo.getType().equals(newPathwayInfo.getType())){
                System.out.println("|-----------修改临时路径类型---------");
                System.out.println("old="+oldPathwayInfo.getType());
                System.out.println("new="+newPathwayInfo.getType());
                recentWork.setMethod("提交修改了"+newPathwayInfo.getPathway_name()+"字段临时路径类型");
                recentWorkMapper.insertRecentWork(recentWork);
            }

            if (!oldPathwayInfo.getDrug_use_period().equals(newPathwayInfo.getDrug_use_period())){
                System.out.println("|-----------修改具体药物使用时间段---------");
                System.out.println("old="+oldPathwayInfo.getDrug_use_period());
                System.out.println("new="+newPathwayInfo.getDrug_use_period());
                recentWork.setMethod("提交修改了"+newPathwayInfo.getPathway_name()+"字段具体药物使用时间段");
                recentWorkMapper.insertRecentWork(recentWork);
            }
        }
    }

    @Override
    public JSONArray findMyRecentWork(String username) {
        List<RecentWork> myRecentWork =recentWorkMapper.findMyRecentWork(username);
        RecentWork recentWork;
        List<RecentWork> list  = new ArrayList<>();
        if (myRecentWork.size()!=0){
            recentWork = myRecentWork.get(0);
            String index = recentWork .getCreate_time();
            String method = recentWork.getMethod();
            for (int i =1 ; i<myRecentWork.size();i++){
                RecentWork recentWorkOut = myRecentWork.get(i);
                if (index.equals(recentWorkOut.getCreate_time())){
                    System.out.println("if判断true");
                    recentWork.setMethod(method+","+recentWorkOut.getMethod());
                    method = recentWork.getMethod();
                }else {
                    list.add(recentWork);
                    System.out.println("if判断false");
                    index = recentWorkOut.getCreate_time();
                    method = recentWorkOut.getMethod();
                    recentWork = recentWorkOut;
                }
            }
            System.out.println(list);
        }

        JSONObject jsonObject = new JSONObject();
        JSONArray jsonArray = new JSONArray();
        for (int i = 0 ; i <list.size(); i++ ){
            if (jsonArray.size()==10) break;
            String method;
            int state=0;
            if (list.get(i).getMethod().contains(",")) {
                method = list.get(i).getMethod().split(",")[0] + "等内容";
                state=1;
            }else method =list.get(i).getMethod();
            String more = list.get(i).getMethod();
            jsonObject.put("name",list.get(i).getPathway_name());
            jsonObject.put("time",list.get(i).getCreate_time());
            jsonObject.put("username",list.get(i).getUsername());
            jsonObject.put("method",method);
            jsonObject.put("more",more);
            jsonObject.put("id",list.get(i).getCp_index());
            jsonObject.put("state",state);
            jsonArray.add(jsonObject);
            jsonObject.clear();
        }
        return jsonArray;
    }
}
