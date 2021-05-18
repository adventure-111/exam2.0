package cn.cuit.exam.controller;

import cn.cuit.exam.bean.Student;
import cn.cuit.exam.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Api(value = "学生信息管理模块", description = "学生信息管理模块接口信息")
public class StudentController {
    @Autowired
    private StudentService studentService;

    @ApiOperation("查找学生")
    @GetMapping("/student/{id}")
    public @ResponseBody Object student(@PathVariable("id") String id) {
        Student student = studentService.queryStudentBySno(id);

        return student;
    }
}
