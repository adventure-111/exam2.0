package cn.cuit.exam.mapper;

import cn.cuit.exam.bean.*;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

@Mapper
public interface ExamMapper {

    List<Exam> getAllExam();

    int insertExam(Exam exam);

     int updateDay(Exam exam);

    List<Klass> getClassList(@Param("school") String school, @Param("cno") String cno);

    List<Examinee> getStuExamInfo(String sno);

    List<Inspector> getInspectorInfo(String tno);

    List<Patrol> getPatrolInfo(String tno);

    List<Patrol> queryPatrol(String cno);

    List<Inspector> queryInspector(String eno);

    List<Examinee> queryExaminee(@Param("eno") String eno, @Param("site") String site);

    Exam getExamByCnoAndCnt(String cno, int cnt);

    int updateStateTo2(String cno);

    int updateStateTo_3(String cno);
}
