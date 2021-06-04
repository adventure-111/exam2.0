package cn.cuit.exam.controller;

import cn.cuit.exam.bean.PageBean;
import cn.cuit.exam.bean.Student;
import cn.cuit.exam.bean.vo.StudentQuery;
import cn.cuit.exam.mapper.StudentMapper;
import cn.cuit.exam.mapper.UtilsMapper;
import cn.cuit.exam.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "学生数据维护", description = "学生数据维护")
public class StudentController {
    @Autowired
    private StudentService studentService;
    @Autowired
    private UtilsMapper utilsMapper;


    @PostMapping("/student")
    @ApiOperation(value = "学生数据查询", notes = "请求参数 必要参数: school; pageNum默认值:1; pageSize默认值：10;</br>响应数据 班级表示为mshort+semester+cnt")
    public PageBean<Student> queryStudent(@RequestBody StudentQuery studentQuery) {
        PageBean<Student> pageBean = studentService.queryStudent(studentQuery);

        return  pageBean;
    }

    @PutMapping("/student")
    @ApiOperation(value = "添加学生", notes = "请求参数有：学号 姓名 密码 专业 年级 班级号\n" +
            "<br>sno sname password mname semester cno")
    public int addStudent(@RequestBody Student student) {
        int count = studentService.addStudent(student);

        return count;
    }

    @DeleteMapping("/student")
    @ApiOperation(value = "删除学生")
    public int deleteStudent(String sno) {
        int count = studentService.deleteStudent(sno);

        return count;
    }


    @DeleteMapping("/delStudentList")
    @ApiOperation(value = "批量删除学生", notes = "[\"2019000003\", \"2019000004\"]")
    public void deleteStudentList(@RequestBody List<String> snoList) {
        studentService.deleteStudentList(snoList);
    }

    @PatchMapping("/student")
    @ApiOperation(value = "修改学生信息", notes = "请求参数有：学号 姓名 密码 专业 年级 班级号"  +
                    "<br>sno sname password mname semester cno")
    public void updateStudent(@RequestBody Student student) {
        studentService.updateStudent(student);
    }

    @GetMapping("/student/majorList")
    @ApiOperation(value = "查询条件:专业下拉列表", notes = "传入参数示例：软件工程")
    public List majorList(String school) {
        return utilsMapper.selectMajorList(school);
    }
   @GetMapping("/student/semesterList")
   @ApiOperation(value = "查询条件:年级下拉列表")
   public List semesterList() {
        return utilsMapper.selectSemesterList();
   }

}
