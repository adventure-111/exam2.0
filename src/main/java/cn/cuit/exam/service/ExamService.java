package cn.cuit.exam.service;

<<<<<<< HEAD
import cn.cuit.exam.bean.*;
import cn.cuit.exam.bean.common.ClassroomAllocation;
import cn.cuit.exam.bean.vo.CourseQuery;
=======
import cn.cuit.exam.bean.Class;
import cn.cuit.exam.bean.Exam;
import cn.cuit.exam.bean.common.ClassroomAllocation;
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ExamService {

    void test();

<<<<<<< HEAD
    public ClassroomAllocation[] getClassroomAllocation(List<Klass> classList, Exam temp);

    public void autoInsertExamSecondary(ClassroomAllocation[] allocations, Exam temp, int selected);

    public List<Inspector> getTeacherAllocation(String school, Exam exam);

    public List<Exam> autoInsertExamThird(List<Exam> examList);

    // -----------------------------------Controller要调用的业务接口------------------------------------------

    List<Klass> getClassList(String school, String cno);

    ClassroomAllocation[] ClassroomAllocation(Exam exam);

    PageBean<Course> getCourseList(CourseQuery courseQuery);

    List<Patrol> queryPatrol(String cno);

    List<Inspector> queryInspector(String eno);

    List<Examinee> queryExaminee(String eno, String site);
=======
    public ClassroomAllocation[] getClassroomAllocation(List<Class> classList, Exam temp);

    public void autoInsertExamSecondary(ClassroomAllocation[] allocations, Exam temp, int selected, List<Class> classList);

    List<Exam> autoInsertExamThird(List<Exam> examList);

>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
}
