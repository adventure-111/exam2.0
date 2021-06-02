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
        student.setSno("2019000021");
        student.setSname("XXX");
        student.setPassword("123");
        student.setMname("软件工程");
        student.setSemester(19);
        student.setCnt(9);

        studentService.addStudent(student);
    }



}