package cn.knowimage.pojo;

import lombok.Data;

@Data
public class RecentWork {
    private Integer id;
    private String cp_index;
    private String method;
    private String user_id;
    private String create_time;

    //额外字段
    private String username;
    private String pathway_name;
}
