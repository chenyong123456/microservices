package cn.knowimage.JsonPojo.MakeJson;

import cn.knowimage.pojo.ImageInfo;
import cn.knowimage.util.ImageUpload;
import net.sf.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public class MakeImageInfo {
    private static int image_max_size = 500;
    private static int image_max_num = 16;
    public static String make(ImageInfo imageInfo,String pathway_index){
        String first_title = imageInfo.getFirst_title();
        String image_prefix = imageInfo.getImage_prefix();
        String below_description = imageInfo.getBelow_description();
        String[] first_titles = first_title.split(",");
        String[] image_prefixs = image_prefix.split(",");
        String[] below_descriptions = below_description.split(",");
        MultipartFile[] file = imageInfo.getFile();
        int size = file.length;
        JSONObject object = new JSONObject();
        //如果size==0  则为默认值，未选择图片上传，拼默认格式
        if (size==0){
            JSONObject image  = new JSONObject();
            image.put("image_name","");
            image.put("image_prefix","@IMG@_");
            image.put("first_title","");
            image.put("below_description","");
            object.put("image_0",image);
        }else {
            //遍历
            int i = 0;
            for (; i < size; i++) {
                //完成图片上传
                //ImageUpload.upload(file[i], pathway_index);
                JSONObject image = new JSONObject();
                image.put("image_name", file[i].getOriginalFilename());
                image.put("image_prefix", image_prefixs[i]);
                image.put("first_title", first_titles[i]);
                image.put("below_description", below_descriptions[i]);
                object.put("image_" + i, image);
            }
        }
        object.put("image_max_size",image_max_size);
        object.put("image_max_num",image_max_num);
        return object.toString();
    }
}
