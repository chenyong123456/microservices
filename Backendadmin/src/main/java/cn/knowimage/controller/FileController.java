package cn.knowimage.controller;

import cn.knowimage.pojo.ImageInfo;
import cn.knowimage.service.ImageInfoService;
import cn.knowimage.util.ClincialResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;


//

@RestController
@CrossOrigin
public class FileController {

    @Autowired
    ImageInfoService imageInfoService;

    //上传图片接口
    //@SuppressWarnings({ "unchecked", "unused", "rawtypes","all"})
    @RequestMapping(value = "uploadFile")
    public ClincialResult uploadFile(ImageInfo imageInfo,@RequestParam(required = false,defaultValue = "null")MultipartFile file[], HttpServletRequest Request) throws IOException {
        System.out.println("***************************文件上传测试*********************");
        System.out.println("查看first_title的值:"+imageInfo.getFirst_title());
        System.out.println("查看below_description的值:"+imageInfo.getBelow_description());
        System.out.println("查看fileNumber的值:"+imageInfo.getFileNumber());
        System.out.println("查看Image_prefix的值:"+imageInfo.getImage_prefix());
        System.out.println("查看Publisher的值:"+imageInfo.getPublisher());
        System.out.println("查看Publisher的值:"+imageInfo.getPublishYear());
        //imageInfo.setFileNumber("2");
        //System.out.println("前台过来的数据格式为:----->:" +  imageInfo.toString());
        //调用imageInfoService的makeImageInfo方法
          int flag = imageInfoService.makeImageInfo(imageInfo,file);
        //0,代表上传图片失败,1,代表上传图片成功
        if(flag == 0){
            return ClincialResult.build(500,"上传图片失败!");
        }
        return ClincialResult.build(200,"上传图片成功!");
    }

    public static void main(String[] args) {
        String s = "012016016000";
        String year = s.substring(2,6);
        year = "555";
        System.out.println("年份----->:" + year);
    }
}
