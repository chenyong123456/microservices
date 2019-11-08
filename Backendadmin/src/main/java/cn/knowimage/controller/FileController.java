package cn.knowimage.controller;

import cn.knowimage.pojo.ImageInfo;
import cn.knowimage.service.ImageInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class FileController {
    @Autowired
    ImageInfoService imageInfoService;
    //上传图片接口
    @RequestMapping(value = "uploadFile")
    public String uploadFile(ImageInfo imageInfo){
        //调用imageInfoService的makeImageInfo方法
        imageInfoService.makeImageInfo(imageInfo);
        return "result";
    }
}
