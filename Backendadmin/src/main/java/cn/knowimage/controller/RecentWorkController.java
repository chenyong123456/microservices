package cn.knowimage.controller;

import cn.knowimage.service.RecentWorkService;
import lombok.extern.slf4j.Slf4j;
import net.sf.json.JSONArray;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

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
        System.out.println("|-----------"+username+"开始查找最近的工作记录---------|");
        JSONArray jsonArray = (JSONArray) recentWorkService.findMyRecentWork(username);
        return jsonArray;
    }
    //待删除
    /*
     String s = "(保存修改)";
        String json = jsonArray.toString();
        Pattern p = Pattern.compile(s);
        Matcher m = p.matcher(json);
        int count = 0;
        while (m.find()){
            count++;
            System.out.println("匹配次数=="+count);
            System.out.println("匹配开始=="+json.charAt(m.start()));
            System.out.println("匹配结束=="+json.charAt(m.end()));
            System.out.println("位置开始=="+m.start());
            System.out.println("位置结束=="+m.end());
        }
        System.out.println(m.replaceAll("111"));
     */
}
