package cn.cuit.exam.service;

import cn.cuit.exam.bean.vo.StudentQuery;
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
}