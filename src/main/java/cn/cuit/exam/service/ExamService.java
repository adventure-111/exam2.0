package cn.cuit.exam.service;

import cn.cuit.exam.bean.Exam;

import java.util.List;

public interface ExamService {

    int InsertExam();

    List<Exam> autoInsertExamSecondary(List<Exam> examList);

    List<Exam> autoInsertExamThird(List<Exam> examList);

}
