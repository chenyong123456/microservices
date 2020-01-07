package cn.knowimage.service;

import cn.knowimage.pojo.ImageInfo;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ImageInfoService {

   int makeImageInfo(ImageInfo imageInfo, MultipartFile file[]) throws IOException;
}
