package cn.knowimage.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

public class ImageUpload {
    public static int upload(MultipartFile file,String pathway_index){
        System.out.println("开始上传图片");
        if (file.isEmpty()) {
            System.out.println("文件为空");
            return 0;
        }
        // 文件名
        String fileName = file.getName();
        // 上传后的路径
        String filePath = "C:\\Users\\wh123\\Desktop\\HospitalProject\\ImageUpload\\"+pathway_index+"\\";
        File dest = new File(filePath + fileName);
        int result;
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest);
            result = 1;
            System.out.println(result);
        } catch (IOException e) {
            result = 0;
            e.printStackTrace();
        }
        return result;
    }
}
