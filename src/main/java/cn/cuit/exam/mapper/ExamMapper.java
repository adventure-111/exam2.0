package cn.cuit.exam.mapper;

import cn.cuit.exam.bean.Exam;

import java.util.List;

public interface ExamMapper {

    List<Exam> getAllExam();

    int insertExam(Exam exam);

}
