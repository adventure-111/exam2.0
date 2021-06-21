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



    ClassroomAllocation[] getClassroomAllocation(List<Klass> classList, Exam temp);

    // 用户选择方案后，点击下一步，此处将存储所有已知信息
    void autoInsertExamSecondary(ClassroomAllocation[] allocations, Exam temp, int selected);

    // 获得教师分配方案
    List<Inspector> getTeacherAllocation(Exam exam);

    // 暂存
    void tempStore(Exam exam);

    void deploy(Exam exam);

    // -----------------------------------Controller要调用的业务接口------------------------------------------

    List<Klass> getClassList(String school, String cno);

    ClassroomAllocation[] ClassroomAllocation(Exam exam);
}
