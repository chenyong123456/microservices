package cn.knowimage.controller;

import cn.knowimage.JsonPojo.MakeJson.MakeJsonPathway;
import cn.knowimage.pojo.PathwayInfo;
import cn.knowimage.pojo.ReceivePathway;
import cn.knowimage.service.PathwayInfoService;
import cn.knowimage.service.RecentWorkService;
import cn.knowimage.service.UserService;
import cn.knowimage.util.ClincialResult;
import cn.knowimage.util.JsonUtils;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 彭雷2019.10.12
 * 主要表单提交
 */
@CrossOrigin
@Controller
@Slf4j
public class PathwayInfoController {
    @Autowired
    PathwayInfoService pathwayInfoService;
    @Autowired
    RecentWorkService recentWorkService;
    @Autowired
    UserService userService;
    /**
     *
     * @param pathwayPojo 梁梁传入数据
     * @return 返回成功或者失败
     */
    @RequestMapping("/insertInfo")
    @ResponseBody

    public String insertPathwayInfo(String pathwayPojo) {
        System.out.println("|-----------开始进行添加或更新操作---------");
        //测试数据     避免前端多次测试
        //pathwayPojo="{\"selectId\":\"\",\"publisher\":\"01\",\"publishYear\":\"2014\",\"fileNumber\":\"8899\",\"versionNumber\":\"00\",\"pathway_name\":\"道歉没用\",\"first_diagnose\":\"\",\"suitable_subject_disc\":\"\",\"diagnosis\":\"\",\"treatment_choice_ref\":\"\",\"treatment_choice_scenario\":\"[{\\\"treatmentChoiceId\\\":[{\\\"value\\\":\\\"\\\"}]}]\",\"treatment_days\":\"[{\\\"tag_name\\\":\\\"\\\",\\\"time_unit\\\":\\\"\\\",\\\"text\\\":\\\"\\\",\\\"min\\\":\\\"0\\\",\\\"max\\\":\\\"0\\\"}]\",\"treatment_entry_standard\":\"[{\\\"value\\\":\\\"\\\"},{\\\"value\\\":\\\"\\\"}]\",\"prep_treatment_common\":\"\",\"prep_treatmentCommon\":\"[{\\\"time_unit\\\":\\\"\\\",\\\"text\\\":\\\"\\\",\\\"min\\\":\\\"0\\\",\\\"max\\\":\\\"0\\\",\\\"obligatory_exam\\\":[{\\\"value\\\":\\\"\\\"}],\\\"optional_exam\\\":[{\\\"value\\\":\\\"\\\"}],\\\"item_field_name1\\\":\\\"\\\",\\\"notification\\\":[{\\\"value\\\":\\\"\\\"}]}]\",\"radio\":\"1\",\"prep_treatmentAntibio_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"prep_treatmentAnaesthetic_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"prep_treatmentOtherdrugs_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"prep_treatment_extension\":\"[{\\\"content\\\":\\\"\\\",\\\"content_item\\\":[{\\\"value\\\":\\\"\\\"}]}]\",\"treatment_days_duration\":\"\",\"duration_treatment_min\":\"0\",\"duration_treatment_max\":\"0\",\"duration_treatment_text\":\"\",\"sceneList\":\"[{\\\"treatmentPlanRef\\\":\\\"\\\",\\\"caseChildnum\\\":[{\\\"treatmentPlanContent\\\":\\\"\\\",\\\"planContentItem\\\":[{\\\"value\\\":\\\"\\\"}]}],\\\"obligatoryExam\\\":[{\\\"value\\\":\\\"\\\"}],\\\"optionalExam\\\":[{\\\"value\\\":\\\"\\\"}],\\\"item_field_name2\\\":\\\"\\\",\\\"notification\\\":[{\\\"value\\\":\\\"\\\"}]}]\",\"drug_usageAntibio_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"drug_usageAnaesthetic_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"drug_usageOtherdrugs_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"afterDuration\":\"\",\"afterDuration_treatment_min\":\"0\",\"afterDuration_treatment_max\":\"0\",\"afterDuration_treatment_text\":\"\",\"after_medicalScenario\":\"[{\\\"obligatory_exam\\\":[{\\\"value\\\":\\\"\\\"}],\\\"optional_exam\\\":[{\\\"value\\\":\\\"\\\"}],\\\"recovery_plan\\\":[{\\\"value\\\":\\\"\\\"}]}]\",\"after_treatmentAntibio_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"after_treatmentAnaesthetic_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"after_treatmentOtherdrugs_usage\":\"[{\\\"value\\\":\\\"\\\"}]\",\"discharge_criteria\":\"[{\\\"value\\\":\\\"\\\"}]\",\"other_notice\":\"[{\\\"value\\\":\\\"\\\"}]\",\"submitter\":\"李俊峰\",\"indefinite_field\":\"[{\\\"field_pos\\\":3,\\\"field_name\\\":\\\"\\\",\\\"field_content\\\":[{\\\"value\\\":\\\"\\\"}]}]\",\"submitterid\":\"51\",\"commit\":\"0\",\"table_info\":\"[{\\\"table_name\\\":\\\"@GRID@_1\\\",\\\"row_count\\\":1,\\\"column_count\\\":3,\\\"top_title\\\":\\\"名字\\\",\\\"below_description\\\":\\\"发的\\\",\\\"content\\\":[[{\\\"value\\\":\\\"1\\\"},{\\\"value\\\":\\\"2\\\"},{\\\"value\\\":\\\"3\\\"}]]}]\"}";
        ReceivePathway receivePathway = JsonUtils.jsonToPojo(pathwayPojo, ReceivePathway.class);
        System.out.println("前端传入数据为:"+pathwayPojo);
        //创建pathwayInfo字段
        PathwayInfo pathwayInfo = MakeJsonPathway.newPathwayInfo(receivePathway);
        recentWorkService.insertRecentWork(pathwayInfo);
        int statePathwayInfo = pathwayInfoService.insertPathwayInfo(receivePathway,pathwayInfo);
        System.out.println();
        if (statePathwayInfo==1){
            return "ok";
        }else return "false";
    }

    /**
     *
     * @param selectId 被查询的index，与前端约定为该字段名为selectId
     * @return 查询出的结果
     */
    @RequestMapping("/findByname")
    @ResponseBody
    public JSONObject findPathway_InfoByname(String selectId){
        System.out.println("|-----------开始进行查询操作---------|");
        System.out.println("查询的id为="+selectId);
        JSONObject returnJson = pathwayInfoService.findPathwayInfoByIndex(selectId);
        System.out.println();
        return returnJson ;
    }

    /**
     *
     * @param query 模糊查询条件，既与query这个字段有关的所有字段
     * @return  所有与query有关的字段
     */
    @RequestMapping("/findAllInfo")
    @ResponseBody
    public JSONArray findAllById(String query) {
        System.out.println("进入模糊查询查找所有,查找与'"+query+"'有关的");
        JSONArray jsonArray = pathwayInfoService.findLikePathwayName(query);
        return jsonArray;
    }
    @ResponseBody
    @RequestMapping("/queryName")
    public JSONObject selectByNameAndSubmitter(String query, String nameSub) {
        System.out.println(query);
        System.out.println(nameSub);
        JSONObject jsonObject = pathwayInfoService.selectPathwayNameByUserName(query, nameSub);
        //将返回值全部放在数组里传递给前台
        return jsonObject;
    }

    /**
     *
     * @param page  分页page
     * @param rows  分页rows
     * @param pathway_name  数据库对应的字段pathway_name
     * @param audit_state   数据库对应的字段audit_state
     * @return  分页查询的结果
     */
    @RequestMapping("/pathway_id")
    @ResponseBody
    public JSONObject selectAudit_InfoByPathway_Id(@RequestParam(value = "page", required = false, defaultValue = "0") Integer page,
                                                   @RequestParam(value = "rows", required = false, defaultValue = "10") Integer rows,
                                                   @RequestParam(value = "pathway_name", required = false, defaultValue = "") String pathway_name,
                                                   @RequestParam(value = "audit_state", required = false, defaultValue = "500") Integer audit_state,
                                                   @RequestParam(value = "commit_state", required = false, defaultValue = "500") Integer commit_state) {
        System.out.println("|-----------开始分页查询操作---------|");
        System.out.println("前端传入状态为++++audit_state="+audit_state+"        commit_state="+commit_state);
        Integer audit_state1,audit_state2,audit_state3,commit_state1,commit_state2;
        //500代表前台没有传入数据，查询所有的数据
        if(audit_state==500||commit_state==500){
            audit_state1 = 0;audit_state2 = 1;audit_state3 = 2;
            commit_state1 = 0; commit_state2 = 1;
        } else{
            audit_state1 = audit_state;audit_state2 = audit_state;audit_state3 = audit_state;
            commit_state1 = commit_state; commit_state2 = commit_state;
        }
        if(page==0){
            page = 1;
        }
        JSONObject jsonObject = pathwayInfoService.pathwayByPage(pathway_name, audit_state1, audit_state2, audit_state3, page, rows,commit_state1,commit_state2 );
        System.out.println("分页查询返回前端数据为:"+jsonObject);
        System.out.println();
        return jsonObject;
    }

    /**
     *
     * @param pathwayInfo 需要字段audit_state,check_id,audit_remark,commit_state,editor_id(暂时是submitter_id)
     * @return 返回成功或者失败
     */
    @RequestMapping("/updateAudit")
    @ResponseBody
    public ClincialResult updateById(PathwayInfo pathwayInfo) {
        System.out.println("|-----------开始进行审核操作---------|");
        pathwayInfo.setCommit_state("1");
        if (pathwayInfo.getAudit_state()==2){
            pathwayInfo.setCommit_state("0");
        }
        int flag = pathwayInfoService.updateAudit(pathwayInfo);
        if (flag == 1) {//审核成功！
            System.out.println("审核成功");
            return 	ClincialResult.ok();
        }else{//审核失败！
            System.out.println();
            return	ClincialResult.build(500,"审核失败");
        }
    }

    /**
     * 作用:前端传入pathway_index 删除数据库中的某个对应字段
     * @param pathway_index 路径版本号
     * @return 成功返回200 失败返回500
     */
    @RequestMapping("deletePathwayInfo")
    @ResponseBody
    public JSONObject deletePathwayInfo(String pathway_index,String username,String password){
        JSONObject jsonObject = new JSONObject();
        System.out.println("|-----------开始进行删除操作---------|");
        System.out.println("删除了id为"+pathway_index+"用户名:"+username+"密码:"+password);
        int state=0;
        if (userService.checkUser(username,password)!=0) {
            state = pathwayInfoService.deletePathwayInfo(pathway_index);
            System.out.println("state==="+state);
        }
        if (state==3){
            jsonObject.put("state",200);
            return jsonObject ;
        }else {
            jsonObject.put("state",500);
            return jsonObject;
        }
    }

    @RequestMapping("/myWork")
    @ResponseBody
    public List<String> findMyWork(String submitterid){
        return pathwayInfoService.findMyWork(submitterid);
    }
}
