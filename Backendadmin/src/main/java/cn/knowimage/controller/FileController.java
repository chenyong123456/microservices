package cn.knowimage.controller;

import net.sf.json.JSONObject;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;

@RestController
@CrossOrigin
public class FileController {
    @RequestMapping(value = "uploadFile")
    public String uploadFile(String first_title,String image_prefix,String below_description,MultipartFile file[]){
        System.out.println(first_title);
        System.out.println(image_prefix);
        System.out.println(below_description);
        int size = file.length;
        for (int i = 0 ; i<size;i++) {
            System.out.println(file[i].getOriginalFilename());
        }
//        System.out.println("image_prefix="+image_prefix);
//        System.out.println("first_title="+first_title);
//        System.out.println("below_description="+below_description);
//        System.out.println("开始上传图片");
//        if (file.isEmpty()) {
//            System.out.println("文件为空");
//        }
//        String fileName = file.getName();  // 文件名
//        String filePath = "C:\\Users\\wh123\\Desktop\\HospitalProject\\ImageUpload\\"; // 上传后的路径
//        File dest = new File(filePath + fileName);
//        String result;
//        if (!dest.getParentFile().exists()) {
//            dest.getParentFile().mkdirs();
//        }
//        try {
//            file.transferTo(dest);
//            result = "上传成功";
//            System.out.println(result);
//        } catch (IOException e) {
//            result = "上传失败";
//            e.printStackTrace();
//        }
        return "result";
    }
}
