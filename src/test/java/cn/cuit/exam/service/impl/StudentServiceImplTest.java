package cn.cuit.exam.service.impl;

import cn.cuit.exam.bean.Student;
import cn.cuit.exam.bean.vo.StudentQuery;
import cn.cuit.exam.service.StudentService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class StudentServiceImplTest {
    @Autowired
    private StudentService studentService;


    @Test
    void queryStudent() {
        StudentQuery studentQuery = new StudentQuery();
        studentQuery.setSchool("软件工程");
        studentQuery.setPageNum(1);
        studentQuery.setPageSize(5);

        System.out.println(studentService.queryStudent(studentQuery));
    }

    @Test
    void addStudent() {
        Student student = new Student();
        student.setSno("2019000004");
        student.setSname("张三");
        student.setPassword("123");
        student.setMname("软件工程");
        student.setSemester(19);
        student.setCnt(2);

        studentService.addStudent(student);
    }

    @Test
    void  deleteStudent() {
        String sno = "2019000021";
        studentService.deleteStudent(sno);
    }

    @Test
    void updateStudent() {
        Student student = new Student();
        student.setSno("2019000090");
        student.setSname("XXXX");
        student.setPassword("1234");
        student.setMname("软件工程");
        student.setSemester(19);
        student.setCnt(3);

        studentService.updateStudent(student);
    }
}