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
     * @return 小褚的界面渲染-->搜索界面
     * @throws Exception
     */
    @RequestMapping(value = "aa/wiki", produces = MediaType.IMAGE_JPEG_VALUE + ";charset=utf-8")
    public String aa() throws Exception {
        return "new_search";
    }
    /**
     * 主要小褚的路径病例详情界面
     * @throws Exception
     */
    //小褚的界面渲染
    @RequestMapping(value = "aa/new_bdbk",produces = MediaType.IMAGE_JPEG_VALUE+";charset=utf-8")
    public String new_bdbk()throws Exception {
        return "new_bdbk";
    }

}

