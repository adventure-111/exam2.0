package cn.cuit.exam.mapper;

import cn.cuit.exam.bean.Student;
import cn.cuit.exam.bean.vo.StudentQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface StudentMapper {

    // 查询
    List<Student> queryStu(StudentQuery s);

    // 查询记录总条数
    Integer queryStuCount(StudentQuery s);

    // 按学号查询
    Integer selectBySno(String sno);

    // 查询班级cid
    Integer selectClassId(Student student);

    // 查询专业mno
    String selectMajorMno(String mname);

    // 查询专业简称
    String selectMajorMshort(String mname);

    // 增加学生人数
    int addStuNum(int cid);

    // 添加班级
    int insertClass(Student student);

    // 填加学生
    int insertStu(Student student);

    // 添加用户
    int insertUser(Student student);

}