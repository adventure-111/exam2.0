package cn.cuit.exam.mapper;

import cn.cuit.exam.bean.Course;
import cn.cuit.exam.bean.Teach;
import cn.cuit.exam.bean.vo.CourseQuery;

import java.util.List;

public interface CourseMapper {

    //查询课程
    List<Course> queryCourse(CourseQuery c);

    //根据课程号查询课程
    Integer QueryByCno(String cno);

    //根据课程名称
    Integer QueryByCname(String cname);

    //根据课程类型查询课程
    Integer QueryByType(int type);

    //查询选修对应课程的人数
    Integer QueryNumOfSelected(String cno);

    //添加课程
    int InsertCourse(Course course);

    //更新课程信息
    int UpdateCourse(Course course);

    //删除课程
    int DeleteCourse(Course course);

    //添加授课关系
    int InsertTeach(Teach teach);

    //删除授课关系
    int DeleteTeach(Teach teach);

}