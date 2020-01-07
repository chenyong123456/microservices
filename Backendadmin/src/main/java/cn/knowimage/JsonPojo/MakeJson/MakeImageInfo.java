package cn.knowimage.JsonPojo.MakeJson;

import cn.knowimage.pojo.ImageInfo;
import cn.knowimage.util.ImageUpload;
import net.sf.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

/**
 * 在这里处理上传图片的业务逻辑
 * @author yong.Mr
 * @date 2019-11-12
 * @version 1.0
 */
public class MakeImageInfo {
    private static int image_max_size = 500;
    private static int image_max_num = 16;
    private final static int pathway_indexs = 60;

    public static String make(ImageInfo imageInfo,String pathway_index,MultipartFile file[]) throws IOException {
        //这里为图片的存储路径
        String first_title = imageInfo.getFirst_title();
        String image_prefix = imageInfo.getImage_prefix();
        String below_description = imageInfo.getBelow_description();
        String status = imageInfo.getStatus();

       /* String[] first_titles = first_title.split(",");
        String[] image_prefixs = image_prefix.split(",");
        String[] below_descriptions = below_description.split(",");*/
        //String[] statuss = status.split(",");
        //MultipartFile[] file = imageInfo.getFile();
        int size = file.length;
        JSONObject object = new JSONObject();

       // String path = "C:\\Users\\wh123\\Desktop\\HospitalProject\\Backendadmin\\images";
        //File targetFile = null;
        //如果size==0  则为默认值，未选择图片上传，拼默认格式,size=0是不上传图片的
        if (size==0){
                JSONObject image = new JSONObject();
                image.put("image_name", "");
                image.put("image_prefix", "@IMG@_");
                image.put("first_title", "");
                image.put("below_description", "");
                object.put("image_0", image);
         //在这里代表有图片
        }else {
            //遍历, 有图片需要上传到服务器上并且将数据库中的image_info字段进行更改掉
            int i = 0;
            for (; i < size; i++) {
                System.out.println("文件上传进来了吗？-------------------*");
                //获取上传图片的名称
                //String fileName = file[i].getOriginalFilename();

                //完成图片上传
                //targetFile = new File(path, fileName);
                //status = 0 代表新上传的图片
                int uploadflag = 0;
                if(status.equals("0")) {
                    uploadflag  = ImageUpload.upload(file[i], pathway_index, imageInfo.getImage_prefix());
                }

             /*   JSONObject image = new JSONObject();
                image.put("image_name", file[i].getOriginalFilename());
                image.put("image_prefix", image_prefixs[i]);
                image.put("first_title", first_titles[i]);
                image.put("below_description", below_descriptions[i]);
                object.put("image_" + i, image);*/
                //图片上传失败
                if(uploadflag == 0){
                    return "false";
                }
            }
        }
       /* object.put("image_max_size",image_max_size);
        object.put("image_max_num",image_max_num);*/
        return "true";
    }
}
