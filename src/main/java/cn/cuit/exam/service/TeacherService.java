package cn.cuit.exam.service;

import cn.cuit.exam.bean.PageBean;
import cn.cuit.exam.bean.Student;
import cn.cuit.exam.bean.Teacher;
import cn.cuit.exam.bean.vo.TeacherQuery;

import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public interface TeacherService {

    /**
     * 条件分页查询教师
     * @param teacherQuery
     * @return
     */
    PageBean<Teacher> selectTeacher(TeacherQuery teacherQuery);

    /**
     * 添加教师
     * @param teacher
     * @return
     */
    int addTeacher(Teacher teacher);

    /**
     * 批量添加教师
     * @param teacherList
     * @return
     */
    Map addTeacherList(List<Teacher> teacherList, String school);

    /**
     * 更新教师信息
     * @param teacher
     * @return
     */
    int updateTeacher(Teacher teacher);

    /**
     * 删除教师
     * @param tno
     * @return
     */
    int deleteTeacher(String tno);

    /**
     * 批量删除教师
     * @param tnoList
     * @return
     */
    void deleteTeacherList(List<String> tnoList);

    PriorityQueue<Teacher> getMinHeapBySchool(String school);
}

