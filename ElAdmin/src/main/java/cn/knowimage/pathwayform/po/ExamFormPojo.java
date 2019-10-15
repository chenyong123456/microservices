package cn.knowimage.pathwayform.po;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * 前台传过来的数据
 * @autor lyx
 * @data 2019/08/26
 */
@Entity
@Getter
@Setter
@Data
public class ExamFormPojo{
    private String cp_id;
    private String form_name;
    private String suitable_subject_disc;
    private String form_manager;
    private String severity_level;
    private String stage;
    private String standard_entry_days;
    private String timeMin;
    private String timeMax;
    private String isOperation;
    private String operationNum;

}
