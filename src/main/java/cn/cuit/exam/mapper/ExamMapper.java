package cn.cuit.exam.mapper;

import cn.cuit.exam.bean.Exam;
import cn.cuit.exam.bean.Klass;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ExamMapper {

    List<Exam> getAllExam();

    int insertExam(Exam exam);

    List<Klass> getClassList(@Param("school") String school, @Param("cno") String cno);
}
