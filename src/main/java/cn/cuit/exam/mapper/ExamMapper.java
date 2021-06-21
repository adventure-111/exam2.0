package cn.cuit.exam.mapper;

<<<<<<< HEAD
<<<<<<< HEAD
import cn.cuit.exam.bean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
=======
=======
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
import cn.cuit.exam.bean.Exam;

import java.util.List;

<<<<<<< HEAD
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
=======
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
public interface ExamMapper {

    List<Exam> getAllExam();

    int insertExam(Exam exam);

<<<<<<< HEAD
<<<<<<< HEAD
    List<Klass> getClassList(@Param("school") String school, @Param("cno") String cno);

    List<Examinee> getStuExamInfo(String sno);

    List<Inspector> getInspectorInfo(String tno);

    List<Patrol> getPatrolInfo(String tno);

    List<Patrol> queryPatrol(String cno);

    List<Inspector> queryInspector(String eno);

    List<Examinee> queryExaminee(@Param("eno") String eno, @Param("site") String site);
=======
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
=======
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
}
