package cn.knowimage.pojo;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class ImageInfo {
    private String first_title;
    private String image_prefix;
    private String below_description;
    private MultipartFile file[];
    private String publisher;
    private String publishYear;
    private String fileNumber;
    private String versionNumber;

}
