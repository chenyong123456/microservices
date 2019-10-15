package cn.knowimage.controller;

import cn.knowimage.pojo.RecentWork;
import cn.knowimage.service.RecentWorkService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @author 彭雷2019.10.15
 * 日志，最近工作的代码
 */
@CrossOrigin
@Controller
@Slf4j
public class RecentWorkController {
    @Autowired
    RecentWorkService recentWorkService;
    @RequestMapping("/work")
    @ResponseBody
    public JSONArray findMyRecentWork(String username){
        System.out.println("|-----------"+username+"开始查找最近的工作记录---------");
        System.out.println(username);
        JSONArray jsonArray = (JSONArray) recentWorkService.findMyRecentWork(username);
        return jsonArray;
    }
}
