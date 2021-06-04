package cn.cuit.exam.service.impl;

import cn.cuit.exam.bean.Teacher;
import cn.cuit.exam.bean.vo.TeacherQuery;
import cn.cuit.exam.mapper.TeacherMapper;
import cn.cuit.exam.service.TeacherService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class TeacherServiceImplTest {

    @Autowired
    private TeacherServiceImpl teacherService;

    @Test
    void selectTeacher() {
        TeacherQuery teacherQuery = new TeacherQuery();
        teacherQuery.setSchool("软件工程");
        System.out.println(teacherService.selectTeacher(teacherQuery));
    }


    @Test
    void addTeacher() {
        Teacher teacher = new Teacher();
        teacher.setSchool("软件工程");
        teacher.setPassword("123");
        teacher.setTno("0702003");
        teacher.setTname("XXX");
        System.out.println(teacherService.addTeacher(teacher));
    }

    @Test
    void updateTeacher() {
        Teacher teacher = new Teacher();
        teacher.setSchool("软件工程");
        teacher.setPassword("1234");
        teacher.setTno("0702003");
        teacher.setTname("XXXX");
        System.out.println(teacherService.updateTeacher(teacher));
    }

    @Test
    void deleteTeacher() {
        System.out.println(teacherService.deleteTeacher("0702003"));
    }

    @Test
    void deleteTeacherList() {
    }
}