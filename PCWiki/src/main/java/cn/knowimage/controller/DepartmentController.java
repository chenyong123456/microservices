package cn.knowimage.controller;

import cn.knowimage.pojo.AuditInfo;
import cn.knowimage.pojo.Department;
import cn.knowimage.pojo.PathwayInfo;
import cn.knowimage.service.AuditInfoService;
import cn.knowimage.service.PathwayInfoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * 微信小程序的科室分类业务层（新表单提交控制器）
 * @author Mr.G
 * @date 2019/8/12
 */
@CrossOrigin
@Controller
public class DepartmentController {

    @Autowired
    public PathwayInfoService pathway_infoService;

    @Autowired
    public AuditInfoService audit_InfoService;
    /**
     * 根据路径名字、提交者名字查询相关的AuditInfo信息
     * @param query  路径名称
     * @param nameSub  提交者名字
     * @return JSONArray jsonArray AuditInfo信息
     */
    @ResponseBody
    @RequestMapping("/queryName")
    public JSONArray selectByNameAndSubmitter(String query, String nameSub) {

        List<AuditInfo> list = pathway_infoService.selectByNameAndSubmitter(query, nameSub);
        //将返回值全部放在数组里传递给前台
        JSONArray jsonArray = new JSONArray();
        //根据获取到的list大小循环
        for (int i = 0; i < list.size(); i++) {
            JSONObject value = new JSONObject();
            //获取Pathway_name
            String pathWay_name_s = list.get(i).getPathway_name();
            //获取Audit_state
            Integer state = list.get(i).getAudit_state();
            //获取pathway_id
            Integer ID = list.get(i).getPathway_id();
            value.put("label", pathWay_name_s);
            value.put("value", ID);
            value.put("static", state);
            jsonArray.add(value);
        }
        return jsonArray;
    }
    /**
     * 模糊查询query，query表示pathway_name
     */
    @RequestMapping("/findAllInfo")
    @ResponseBody
    public JSONArray findAllById(String query) {
        List<AuditInfo> list = pathway_infoService.findAllById(query);
        JSONArray jsonArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            JSONObject value = new JSONObject();
            //获取pathway_name
            String pathway_name = list.get(i).getPathway_name();
            //获取Pathway_id
            Integer ID = list.get(i).getPathway_id();
            //获取Audit_state
            Integer state = list.get(i).getAudit_state();
            value.put("label", pathway_name);
            value.put("value", ID);
            value.put("static", state);
            jsonArray.add(value);
        }
        return jsonArray;
    }
    /**
     * 查询所有科室的名字并且,将每个分类中包含的数据大小传个前台applet 微信小程序
     * 查询全部科室名字
     */
    @ResponseBody
    @GetMapping("/findAllName")
    public JSONObject findAllName(){

        JSONObject result = new JSONObject();
        List<Department> list = pathway_infoService.findAllName();
        int arrLength = list.size();
        result.put("arr_length",arrLength);
        JSONArray contentArray = new JSONArray();
        JSONArray cateryArray = new JSONArray();
        //这里循环分类表department中的所有数据
        for (int i = 0; i < list.size(); i++) {
            contentArray.add(list.get(i).getDepartment_name());
            List<PathwayInfo> listLength = pathway_infoService.selectP_nameByD_name(list.get(i).getDepartment_name());
            cateryArray.add(listLength.size());
        }
        result.put("content", contentArray);
        result.put("arr_length", cateryArray);
        return result;
    }
    /**
     * 查询该科室名下对应的所有pathway_name
     * @param name 科室名字
     * @return JSONObject result
     */
    @GetMapping("/selectPnameByDname")
    @ResponseBody
    public JSONObject selectPnameByDname(String name){
        List<PathwayInfo> list = pathway_infoService.selectP_nameByD_name(name);
        JSONObject result = new JSONObject();
        JSONArray contentArray = new JSONArray();
        JSONArray indexArray = new JSONArray();
        for (int i = 0; i < list.size(); i++) {
            contentArray.add(list.get(i).getPathway_name());
            indexArray.add(list.get(i).getPathway_index());
        }
        result.put("content",contentArray);
        result.put("index",indexArray);
        return result;
    }
    //新表单的访问路径，映射到newForm页面
    @RequestMapping("/newForm")
    public String newForm() {
        return "newForm";
    }

}
