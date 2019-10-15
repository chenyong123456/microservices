package cn.knowimage.pathwayform.vo;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

/**
 * 执行临床路径表单实体类
 * @author lyx
 * @date 2019/8/22
 */
@Entity
@Getter
@Setter
public class ExamForm {
    /**
     * 临床路径id
     */
    //@JsonProperty("SDS")
    private String cp_id;
    /**
     * 表单名
     */
    private String form_name;
    /**
     * 版本号
     */
    private String version;
    /**
     * 适用对象
     */
    private String suitable_subject_disc;
    /**
     * 危险级别
     */
    private Integer severity_level;
    /**
     * 病人信息
     */
    private String patient_info;
    /**
     * 住院信息
     */
    private String info_in_hospital;
    /**
     * 治疗信息
     */
    private String treatment_info;
    /**
     * 执行人
     */
    private String form_manager;
    /**
     * 执行时间
     */
    private String record_time;

    public ExamForm() {
        super();
    }
    public ExamForm(String cp_id,String treatment_info) {
        this.cp_id = cp_id;
        this.treatment_info = treatment_info;

    }
    public ExamForm(String cp_id, String form_name, String version, String suitable_subject_disc, Integer severity_level,String info_in_hospital, String treatment_info) {
        this.cp_id = cp_id;
        this.form_name = form_name;
        this.version = version;
        this.suitable_subject_disc = suitable_subject_disc;
        this.severity_level = severity_level;
        this.info_in_hospital = info_in_hospital;
        this.treatment_info = treatment_info;
    }
    public ExamForm(String cp_id, String form_name, String version, String suitable_subject_disc, Integer severity_level,String info_in_hospital, String treatment_info, String form_manager, String record_time) {
        this.cp_id = cp_id;
        this.form_name = form_name;
        this.version = version;
        this.suitable_subject_disc = suitable_subject_disc;
        this.severity_level = severity_level;
        this.info_in_hospital = info_in_hospital;
        this.treatment_info = treatment_info;
        this.form_manager = form_manager;
        this.record_time = record_time;
    }

}
