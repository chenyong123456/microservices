package cn.knowimage.controller;

import cn.knowimage.pojo.Audit_Info;
import cn.knowimage.service.Audit_InfoService;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import java.util.List;

/**
 * @params 主要为王果果的表单进行检索指定登录名字自己的提交数据pathway_id
 * @author 啊勇
 */
@CrossOrigin
@Controller
@RequestMapping("/search")
public class SearchIdAuditInfoFormController {

    @Autowired
    public Audit_InfoService audit_InfoService;

    //根据pathway_name查询Audit_info表中指定submitter的数据
    @RequestMapping("/byname")
    @ResponseBody
    public JSONArray selectByName(@RequestParam(value = "nameSub",required = false ,defaultValue = "") String submitter){
        System.out.println(submitter);
        JSONArray resultList = null;
        try {
            List<Audit_Info> audit_infos = audit_InfoService.selectByName(submitter);
            resultList  = new JSONArray();
            for (Audit_Info audit_info : audit_infos) {
                resultList.add(audit_info.getPathway_id());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultList;
    }

    //根据时间去进行查询的指定submitter的数据
    @RequestMapping("/bynameortime")
    @ResponseBody
    public JSONArray selectByData_upload_moment(@RequestParam(value = "nameSub",required = false ,defaultValue = "") String submitter,
                                                @RequestParam(value = "timeshijian",required = false,defaultValue = "vfdv")String timeshijian){

        System.out.println(submitter);
        System.out.println(timeshijian);

        JSONArray jsonArray = JSONArray.fromObject(timeshijian);
        JSONArray resultList = null;
        try {
            List<Audit_Info> audit_infos = audit_InfoService.selectByNameToTime(submitter,jsonArray.getString(0),jsonArray.getString(1));
            resultList  = new JSONArray();
            for (Audit_Info audit_info : audit_infos) {
                resultList.add(audit_info.getPathway_id());
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return resultList;
    }
}
