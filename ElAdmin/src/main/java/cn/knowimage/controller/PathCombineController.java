package cn.knowimage.controller;

import cn.knowimage.pathcombine.PrepTreatmentCommonSpACo;
import cn.knowimage.pojo.Pathway_Info;
import cn.knowimage.service.PathCombineService;
import cn.knowimage.service.Pathway_InfoService;
import cn.knowimage.util.ClincialResult;
import cn.knowimage.util.HttpClientUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;

/**
 * @params 主要对数据库中的数据检查,药物使用,术中检查,术后用药,的字符串进行格式的转换
 * @author 啊勇
 */
@CrossOrigin
@Controller
public class PathCombineController {

    // 定义保存修改好的数据的容器，最后会将answerJson返还给陈勇（修改好的数据）
    private static JSONObject answerJson= new JSONObject();
    private static JSONArray after_optional_exam = new JSONArray();
    private static JSONArray after_obligatory_exam = new JSONArray();

    //查询数据库中的表pathway_Info中的相应的数据进行更改为指定编码格式的数据
    @Autowired
    public Pathway_InfoService pathway_InfoService;

    @Autowired
    public PathCombineService PathCombineService;

    /**
     * @params 主要为小栗子提供拆分好了的单个字符串-->只供小栗子在他那里请求使用
     * @return 返回一个json格式的数组字符串给他["血常规","CRP","尿常规","粪常规","心肌酶谱及肝肾功能"]
     */
    @RequestMapping("/stringSplit")
    @ResponseBody
    public JSONObject stringSplit(String pathway_name){
        pathway_name = "小儿气管（支气管）异物";
        //执行查询
        List<Pathway_Info> list = pathway_InfoService.selectByPathway_name(pathway_name);
        //去查询出来的每个值
        JSONObject jsonObject = null;
        for (Pathway_Info pathway_info : list) {
            //在这里进行数据的转换
            jsonObject = PrepTreatmentCommonSpACo.prepTreatmentCommonSplit(pathway_info.getPrep_treatment_common(), pathway_name);
        }
        return jsonObject;
    }

    /**
     * @params 这里主要为我请求小栗子拼接好的编码机制的字符串(拼接好了的字符串)由啊勇来修改数据库
     * @return 这里会返回请求小栗子的controller的接口数据-->这里方法会用到HtppClient服务去请求小栗子的请求的接口来获得拼接好的字符串
     */
    @RequestMapping("/stringCombine")
    @ResponseBody
    public ClincialResult stringCombine(){
        //获取小栗子拼接好了的数据-->编码机制的字符串(已经编码好了的格式数据)
        String combineResult = HttpClientUtil.doGet("http://192.168.50.118:8088/updateData");
        System.out.println(combineResult);
        //将小栗子的数据转换为json对象的格式
        JSONObject codeJson = JSONObject.fromObject(combineResult);//-->这里为拼接好了的字符串
        //这是需要重新update的check_code字段数据
        String pathway_name = codeJson.getString("name");
        String check_code = combineResult;

        //这里主要对小栗子编码好的编码机制进行更新数据库

        int i = 1;
        //int i = PathCombineService.updateCheckCode(check_code, pathway_name);
        //更新成功
        if(i==1){
            return ClincialResult.build(200,"更新成功!",check_code);
        }
        return ClincialResult.build(500,"更新失败!");
    }

    /**
     * @params 这里主要为小栗子返回数据库中的数据接口
     * @return 返回小栗子所需要的数据格式
     */
    @RequestMapping("/returnString")
    @ResponseBody
    public JSONObject returnString(String pathway_name){
        //pathway_name = "小儿气管（支气管）异物";
        JSONObject jsonObject = new JSONObject();
        List<Pathway_Info> list = new ArrayList<>();
        //执行查询
        try {
             list = pathway_InfoService.selectByPathway_name(pathway_name);
        }catch (Exception e){
            jsonObject.put("status",500);
            jsonObject.put("msg","查询失败");
            e.printStackTrace();
            return jsonObject;
        }
        //去查询出来的每个值
        String result = null;
        for (Pathway_Info pathway_info : list) {
            //获取数据库中的数据
            result  = pathway_info.getPrep_treatment_common();
        }
        System.out.println(result);
        jsonObject.put("pathway_name",pathway_name);
        jsonObject.put("prep_treatment_common",result);
        return jsonObject;
    }


}
