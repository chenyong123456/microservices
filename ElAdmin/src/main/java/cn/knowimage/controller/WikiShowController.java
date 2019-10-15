package cn.knowimage.controller;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * tmyleaf中只能映射到static,templates,mapping目录下哦！
 * 功能:前期准备工作的测试,主要小褚的界面的渲染功能
 * @author 啊勇
 */
@Controller
public class WikiShowController {
    /**
     * @return 小褚的界面渲染
     * @throws Exception
     */
    @RequestMapping(value = "aa/wiki", produces = MediaType.IMAGE_JPEG_VALUE + ";charset=utf-8")
    public String aa() throws Exception {
        return "new_search";
    }
    //小褚的界面渲染
    @RequestMapping(value = "aa/new_bdbk",produces = MediaType.IMAGE_JPEG_VALUE+";charset=utf-8")
    public String new_bdbk()throws Exception {
        return "new_bdbk";
    }

//    @Autowired
//    private RedisService redisService;
//    //测试redis是否成功
//    @RequestMapping(value = "/test",produces = MediaType.APPLICATION_JSON_VALUE+";charset=UTF-8")
//    @ResponseBody
//    public String demoTest(){
//        redisService.set("ref","{"
//                +"scenario_num:" +2+", scenario_id_0: { treatment_plan: { ref:N, num:"+4+", id_0: { content:麻醉方式:全身麻醉或局部麻醉,"
//                           + "content_item:['']"
//              +"},id_1: { content:术前用药:阿托品等,"
//                          + "content_item:['']"
//               +" }"
//                +" } "
//		);
//        return "ok";
//    }

}

