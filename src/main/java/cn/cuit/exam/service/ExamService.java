package cn.cuit.exam.service;

import cn.cuit.exam.bean.Exam;
import cn.cuit.exam.bean.Inspector;
import cn.cuit.exam.bean.Klass;
import cn.cuit.exam.bean.common.ClassroomAllocation;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExamService {

    void test();

    public ClassroomAllocation[] getClassroomAllocation(List<Klass> classList, Exam temp);

    public void autoInsertExamSecondary(ClassroomAllocation[] allocations, Exam temp, int selected);

    public List<Inspector> getTeacherAllocation(String school, Exam exam);

    public List<Exam> autoInsertExamThird(List<Exam> examList);

    // -----------------------------------Controller要调用的业务接口------------------------------------------

    List<Klass> getClassList(String school, String cno);

    ClassroomAllocation[] ClassroomAllocation(Exam exam);
}
