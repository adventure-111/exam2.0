package cn.cuit.exam.service;


import cn.cuit.exam.bean.PageBean;
import cn.cuit.exam.bean.Student;
import cn.cuit.exam.bean.vo.StudentQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public interface StudentService {

    /**
     * 条件分页查询
     * @param studentQuery
     * @return
     */
    PageBean<Student> queryStudent(StudentQuery studentQuery);

    /**
     * 添加学生
     * @param student
     * @return
     */
    int addStudent(Student student);

//    /**
//     * 删除学生
//     * @param sno
//     * @return
//     */
//    void deleteStudent(String sno);
//
//    /**
//     * 批量删除学生
//     * @param snoList
//     * @return
//     */
//    void deleteStudents(List<String> snoList);
//
//    /**
//     * 增加学生
//     * @param map（sno, sname, password, mno, semester, cnt）
//     * @return
//     */
//    boolean insertStudent(Map<String, Object> map);
//
//    /**
//     * 修改学生信息
//     * @param map
//     */
//    void updateStudent(Map<String, Object> map);
}
