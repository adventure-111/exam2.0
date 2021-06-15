package cn.cuit.exam.service;

import cn.cuit.exam.bean.Class;
import cn.cuit.exam.bean.Exam;
import cn.cuit.exam.bean.common.ClassroomAllocation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExamService {

    void test();

    public ClassroomAllocation[] getClassroomAllocation(List<Class> classList, Exam temp);

    public void autoInsertExamSecondary(ClassroomAllocation[] allocations, Exam temp, int selected, List<Class> classList);

    List<Exam> autoInsertExamThird(List<Exam> examList);

}
