package cn.cuit.exam.mapper;

import cn.cuit.exam.bean.Course;
import cn.cuit.exam.bean.Teach;
import cn.cuit.exam.bean.vo.CourseQuery;
import cn.cuit.exam.bean.vo.TeachQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface CourseMapper {

    // 查询课程
    List<Course> queryCourse(CourseQuery courseQuery);

    // 根据课程号查询课程
    Integer queryByCno(String cno);

    // 根据课程名称
    Integer queryByCname(String cname);

    // 根据课程类型查询课程
    Integer queryByType(int type);

    // 查询选修对应课程的人数
    Integer queryNumOfSelected(String cno);

    // 添加课程
    int insertCourse(Course course);

    // 更新课程信息
    int updateCourse(Course course);

    // 删除课程
    int deleteCourse(String cno);

    // 查询授课记录
    List<TeachQuery> queryTeach(TeachQuery teachQuery);

    // 添加授课关系
    int insertTeach(Teach teach);

    // 删除授课关系
    int deleteTeach(Teach teach);

    // 根据课程号删除授课记录
    int deleteTeachByCno(String cno);

    // 根据教师工号删除授课记录
    int deleteTeachByTno(String tno);

}
