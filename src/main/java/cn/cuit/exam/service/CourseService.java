package cn.cuit.exam.service;

import cn.cuit.exam.bean.Course;
import cn.cuit.exam.bean.PageBean;
import cn.cuit.exam.bean.Teach;
import cn.cuit.exam.bean.vo.CourseQuery;
import cn.cuit.exam.bean.vo.TeachQuery;

import java.util.List;
import java.util.Map;

public interface CourseService {
    /**
     * 添加课程
     * @param course
     * @return
     */
    int addCourse(Course course);

    /**
     * 添加授课关系
      * @param teach
     * @return
     */
    int addTeach(Teach teach);

    /**
     * 批量添加授课关系
     * @param teachList
     * @return
     */
    Map addTeachList(List<Teach> teachList);

    /**
     * 批量添加课程
     * @param list
     * @return
     */
    Map addCourseList(List<Course> list);

    /**
     * 删除课程
     * @param school
     * @param cno
     * @return
     */
    int deleteCourse(String school, String cno);

    /**
     * 批量删除课程
     * @param school
     * @param list
     * @return
     */
    Map deleteCourseList(String school, List<String> list);

    /**
     * 修改课程信息
     * @param course
     * @return
     */
    int updateCourse(Course course);

    /**
     * 查询课程信息
     * @param courseQuery
     * @return
     */
    PageBean<Course> selectCourse(CourseQuery courseQuery);

    /**
     * 查询授课关系
     * @param teachQuery
     * @return
     */
    PageBean<Teach> selectTeach(TeachQuery teachQuery);

    /**
     * 删除授课关系
     * @param teach
     * @return
     */
    int deleteTeach(Teach teach);

    /**
     * 批量删除授课关系
     * @param teachList
     * @return
     */
    Map deleteTeachList(List<Teach> teachList);

    /**
     * 删除全部授课关系
     * @param school
     * @param cno
     * @return
     */
    int deleteTeachAll(String school, String cno);

    /**
     * 删除全部课程信息
     * @param school
     * @return
     */
    Map deleteCourseAll(String school);

}
