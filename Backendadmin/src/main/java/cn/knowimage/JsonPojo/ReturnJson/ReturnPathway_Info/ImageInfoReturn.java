package cn.knowimage.JsonPojo.ReturnJson.ReturnPathway_Info;

import cn.knowimage.util.FtpUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.ui.Model;

import java.io.File;

/**
 * @author yong.Mr
 * @date 2019/11/14
 * 生成image_info图像
 */
public class ImageInfoReturn {

    public static String imageInfo(String imageInfo){

        JSONObject imageWai = new JSONObject();
        JSONArray image = new JSONArray();
        JSONObject imagelei = new JSONObject();

        File getFile = FtpUtil.downloadSingleFile("192.168.50.4", 21, "ftpuser", "ftpuser", "/usr/local/nginx/html/image","01201627528722@IMG@_1.png", "C:\\Users\\wh123\\Desktop\\image");
        System.out.println("文件下载:"+ getFile);

        imagelei.put("file", "{'name':'test'}");
        System.out.println("-----------------------");
        imagelei.put("image_prefix", "@IMG@_1");
        imagelei.put("first_title", "大哥牛皮啊!");
        imagelei.put("below_description", "测试成功!");
        imagelei.put("url", "https://cy.knowimage.cn:7022/image/01201627528722@IMG@_1.png");
        imagelei.put("status","1");
        //将对象放入数组1中
        image.add(imagelei);
        imagelei.put("image_prefix", "@IMG@_2");
        imagelei.put("first_title", "我去!");
        imagelei.put("below_description", "测试失败!");
        imagelei.put("url", "https://cy.knowimage.cn:7022/image/2.jpg");
        imagelei.put("status","1");
        //将对象放入数组1中
        image.add(imagelei);
        //将commit放入对象中,并且把数组放入对象中
        //imageWai.put("imagecommit", 1);
        imageWai.put("image", image);
        //imageWai.put("fileDataIndex", "");
        return imageWai.toString();
    }

}
