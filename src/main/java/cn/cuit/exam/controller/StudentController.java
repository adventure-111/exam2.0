package cn.cuit.exam.controller;

import cn.cuit.exam.bean.PageBean;
import cn.cuit.exam.bean.Student;
import cn.cuit.exam.bean.vo.StudentQuery;
import cn.cuit.exam.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "学生数据维护")
public class StudentController {
    @Autowired
    private StudentService studentService;


    @PostMapping("/studentList")
    @ApiOperation(value = "学生数据查询", notes = "请求参数 必要参数: school; pageNum默认值:1; pageSize默认值：10;</br>响应数据 班级表示为mshort+semester+cnt")
    public PageBean<Student> queryStudent(@RequestBody StudentQuery studentQuery) {
        PageBean<Student> pageBean = studentService.queryStudent(studentQuery);

        return  pageBean;
    }

    @PostMapping("/student")
    @ApiOperation(value = "添加学生", notes = "请求参数有：学号 姓名 密码 专业 年级 班级号\n" +
            "<br>sno sname password mname semester cno")
    public int addStudent(@RequestBody Student student) {
        int count = studentService.addStudent(student);

        return count;
    }
}
