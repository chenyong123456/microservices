package cn.knowimage.util;

import org.springframework.web.multipart.MultipartFile;
import java.io.*;

public class ImageUpload {
    public static int upload(MultipartFile file, String pathway_index, String flag) throws IOException {
        System.out.println("看flag的数据格式----->*" + flag);
        System.out.println("获取文件名----->*" + file.getOriginalFilename());
        System.out.println("开始上传图片");
        if (file.isEmpty()) {
            System.out.println("文件为空");
            return 0;
        }
        //获取文件类型,图片的后缀名
        String originalFileName = file.getOriginalFilename();
        String fileType =  originalFileName.substring(originalFileName.lastIndexOf("."),originalFileName.length());
        System.out.println("看过去文件类型的后缀名:--->" + fileType);
        // 文件名
        String fileName = pathway_index + flag + fileType;
        System.out.println("文件名是在什么:**------>" + fileName);
        // 上传后的路径

        //将文件上传到服务器
        /**
         * Description: 向FTP服务器上传文件
         * @param host FTP服务器hostname
         * @param port FTP服务器端口
         * @param username FTP登录账号
         * @param password FTP登录密码
         * @param basePath FTP服务器基础目录
         * @param filePath FTP服务器文件存放路径。例如分日期存放：/2015/01/01。文件的路径为basePath+filePath
         * @param filename 上传到FTP服务器上的文件名
         * @param input 输入流
         * @return 成功返回true，否则返回false
         */
        boolean root = FtpUtil.uploadFile("192.168.50.4", 21, "ftpuser", "ftpuser", "/usr/local/nginx/html",
                "/image", fileName, file.getInputStream());
        if(root){
            System.out.println("图片上传服务器成功!");
            return 1;
        }
        System.out.println("图片上传服务器失败!");
        return 0;
    }
}
