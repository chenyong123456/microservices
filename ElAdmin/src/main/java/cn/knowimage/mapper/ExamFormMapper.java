package cn.knowimage.mapper;

import cn.knowimage.pathwayform.vo.ExamForm;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExamFormMapper {
    public int addExamForm(ExamForm examForm);
    public ExamForm getExamFormById(ExamForm examForm);
    public int updateExamFormById(ExamForm examForm);
    public int updateTreatmentById(ExamForm examForm);
}
