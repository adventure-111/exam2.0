package cn.cuit.exam.service;

import cn.cuit.exam.bean.Course;
import cn.cuit.exam.bean.PageBean;
import cn.cuit.exam.bean.vo.CourseQuery;

import java.util.List;

public interface CourseService {

    /**
     * 条件分页查询
     * @param courseQuery
     * @return
     */
    PageBean<Course> queryCourse(CourseQuery courseQuery);

    /**
     * 添加课程
     * @param course
     * @return
     */
    int addCourse(Course course);

    /**
     * 删除课程
     * @param cno
     * @return
     */
    int deleteCourse(String cno);

    /**
     * 批量删除课程
     * @param courseList
     * @return
     */
    int deleteCourseList(List<String> courseList);

//    /**
//     * 添加授课表
//     * @param teach
//     * @return
//     */
//    int insertTeach(Teach teach);

    /**
     * 根据课程号删除授课表
     * @param cno
     * @return
     */
    int deleteTeachByCno(String cno);

    /**
     * 根据教师工号删除授课表
     * @param tno
     * @return
     */
    int deleteTeachByTno(String tno);

}
