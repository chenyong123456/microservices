package cn.knowimage.pathwayform.service.impl;

import cn.knowimage.mapper.ExamFormMapper;
import cn.knowimage.pathwayform.vo.ExamForm;
import cn.knowimage.pathwayform.service.ExamFormService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ExamFormServiceImpl implements ExamFormService {


    @Autowired
    ExamFormMapper examFormMapper;
    @Override
    public int addExamForm(ExamForm examForm) {
        return examFormMapper.addExamForm(examForm);
    }

    @Override
    public ExamForm getExamFormById(ExamForm examForm) {
        return examFormMapper.getExamFormById(examForm);
    }
    @Override
    public int updateExamFormById(ExamForm examForm) {
        return examFormMapper.updateExamFormById(examForm);
    }
    @Override
    public int updateTreatmentById(ExamForm examForm) {
        return examFormMapper.updateTreatmentById(examForm);
    }
}
