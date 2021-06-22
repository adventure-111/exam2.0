package cn.cuit.exam.mapper;

import cn.cuit.exam.bean.Course;
import cn.cuit.exam.bean.Teach;
import cn.cuit.exam.bean.vo.CourseQuery;
import cn.cuit.exam.bean.vo.TeachQuery;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface CourseMapper {

    // 根据课程号查询课程
    Course getCourseByCno(String cno);

    // 查询授课关系
    Teach getTeach(Teach teach);

    // 查询所有课程号
    List<String> getAllCno();

    // 添加课程
    int insertCourse(Course course);

    // 添加授课关系
    int insertTeach(Teach teach);

    // 删除课程
    int deleteCourse(String cno);

//    // 删除授课关系
//    int deleteTeach(String cno, String cid);

    // 删除本学院该课程的授课关系
    int deleteTeachBySchoolCourse(@Param("school")String school, @Param("cno") String cno);

    // 判断其他学院是否存在该课程的教学班
    List<Integer> hasClassInOtherSchool(@Param("school") String school, @Param("cno") String cno);

    // 更新课程信息
    int updateCourse(Course course);

    // 查询课程总记录
    int selectCourseCount(CourseQuery courseQuery);

    // 条件分页查询课程
    List<Course> selectCourse(CourseQuery courseQuery);

    // 查询其他学院课程总记录
    int selectAllSchoolCourseCount(CourseQuery courseQuery);

    // 条件分页查询其他学院课程
    List<Course> selectAllSchoolCourse(CourseQuery courseQuery);

    // 查询授课关系总记录
    int selectTeachCount(TeachQuery teachQuery);

    // 查询授课关系
    List<Teach> selectTeach(TeachQuery teachQuery);

    // 删除授课关系
    int deleteTeach(Teach teach);

    String getNameByCno(String cno);
}
