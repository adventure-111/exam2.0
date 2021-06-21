package cn.cuit.exam.service;

import cn.cuit.exam.bean.*;
import cn.cuit.exam.bean.common.ClassroomAllocation;
import cn.cuit.exam.bean.parameter.ManualExamArr2Param;
import cn.cuit.exam.bean.vo.CourseQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface ExamService {

    void test();

    ClassroomAllocation[] getClassroomAllocation(List<Klass> classList, Exam temp);

    // 用户选择方案后，点击下一步，此处将存储所有已知信息
    void autoInsertExamSecondary(ClassroomAllocation allocation, Exam temp);

    // 获得教师分配方案
    List<Inspector> getTeacherAllocation(Exam exam);

    // 暂存
    void tempStore(Exam exam);

    // 发布
    void deploy(Exam exam);

    // 手动排考第二步，对于给出的每一间教室，都返回一场冲突情况表
    Map<Integer, List<String>> getConflictSituation(ManualExamArr2Param param, int duration);

    boolean isTeacherFree(String tno, int eno);

    // -----------------------------------Controller要调用的业务接口------------------------------------------

    List<Klass> getClassList(String school, String cno);

    ClassroomAllocation[] ClassroomAllocation(Exam exam);

    PageBean<Course> getCourseList(CourseQuery courseQuery);

    List<Patrol> queryPatrol(String cno);

    List<Inspector> queryInspector(String eno);

    List<Examinee> queryExaminee(String eno, String site);

    List getSiteList(String no);
}
