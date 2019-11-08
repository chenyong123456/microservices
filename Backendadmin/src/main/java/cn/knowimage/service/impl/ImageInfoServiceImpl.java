package cn.knowimage.service.impl;

import cn.knowimage.JsonPojo.MakeJson.MakeImageInfo;
import cn.knowimage.pojo.ImageInfo;
import cn.knowimage.service.ImageInfoService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class ImageInfoServiceImpl implements ImageInfoService {

    @Override
    public int makeImageInfo(ImageInfo imageInfo) {
        //获得pathway_index 便于后期update那条字段
//        String fileNumber = imageInfo.getFileNumber();
//        if (imageInfo.getFileNumber().equals("")) {
//            fileNumber = "0000" + fileNumber;
//        } else if (fileNumber.length() == 1) {
//            fileNumber = "000" + fileNumber;
//        } else if (fileNumber.length() == 2) {
//            fileNumber = "00" + fileNumber;
//        } else if (fileNumber.length() == 3) {
//            fileNumber = "0" + fileNumber;
//        }
//        String pathway_index = imageInfo.getPublisher() + imageInfo.getPublishYear() + fileNumber + imageInfo.getVersionNumber();
        String pathway_index = "test";
        //make数据库的imageInfo字段的Json的格式
        String imageInfoJson = MakeImageInfo.make(imageInfo,pathway_index);
        System.out.println(imageInfoJson);
        return 0;
    }
}
