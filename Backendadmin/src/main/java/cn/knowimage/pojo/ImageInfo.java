package cn.knowimage.pojo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageInfo {
    private String first_title;
    private String image_prefix;
    private String below_description;
    //@SuppressWarnings({ "unchecked", "unused", "rawtypes","all"})
    //private MultipartFile file[];
    //一下可以拼接pathway_index,进行拼接
    private String publisher;
    private String publishYear;
    private String fileNumber;
    private String versionNumber;
    private String url;
    private String status;

}
