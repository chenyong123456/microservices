package cn.knowimage.pathwayform.service;

import cn.knowimage.pathwayform.vo.ExamForm;

/**
 * 执行表单 业务层
 * @auto lyx
 * @date 2019/8/22
 */
public interface ExamFormService {
    public int addExamForm(ExamForm examForm);
    public ExamForm getExamFormById(ExamForm examForm);
    public int updateExamFormById(ExamForm examForm);
    public int updateTreatmentById(ExamForm examForm);
}
