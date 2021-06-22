package cn.cuit.exam.controller;

import cn.cuit.exam.bean.ExcelUtils;
import cn.cuit.exam.bean.PageBean;
import cn.cuit.exam.bean.Student;
import cn.cuit.exam.bean.Teacher;
import cn.cuit.exam.bean.vo.StudentQuery;
import cn.cuit.exam.bean.vo.TeacherQuery;
import cn.cuit.exam.mapper.TeacherMapper;
import cn.cuit.exam.mapper.UtilsMapper;
import cn.cuit.exam.service.StudentService;
import cn.cuit.exam.service.TeacherService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@Api(value = "教师数据维护", description = "教师数据维护")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;


    @PostMapping("/teacher")
    @ApiOperation(value = "教师数据查询", notes = "请求参数 必要参数: school; pageNum默认值:1; pageSize默认值：10;")
    public PageBean<Teacher> queryStudent(@RequestBody TeacherQuery teacherQuery) {
        PageBean<Teacher> pageBean = teacherService.selectTeacher(teacherQuery);

        return  pageBean;
    }

    @PutMapping("/teacher")
    @ApiOperation(value = "添加教师", notes = "请求参数有：学号 姓名 密码 专业 年级 班级号\n" +
            "<br>sno sname password mname semester cno")
    public int addStudent(@RequestBody Teacher teacher) {
        int count = teacherService.addTeacher(teacher);

        return count;
    }

    @PutMapping("/teacher/import")
    @ApiOperation(value = "添加教师(导入文件)",
                    notes = "文件示例：工号 姓名 密码")
    public Map importTeacher(@RequestPart(value = "file") MultipartFile file) {
        List<Teacher> list = ExcelUtils.readExcel("", Teacher.class, file);
        Map map = teacherService.addTeacherList(list);
        return map;
    }


    @DeleteMapping("/teacher")
    @ApiOperation(value = "删除教师")
    public int deleteStudent(String tno) {
        int count = teacherService.deleteTeacher(tno);

        return count;
    }

    @DeleteMapping("/delTeacherList")
    @ApiOperation(value = "批量删除教师", notes = "[\"2019000003\", \"2019000004\"]")
    public void deleteStudentList(@RequestBody List<String> snoList) {
        teacherService.deleteTeacherList(snoList);
    }

    @PatchMapping("/teacher")
    @ApiOperation(value = "修改教师信息")
    public void updateStudent(@RequestBody Teacher teacher) {
        teacherService.updateTeacher(teacher);
    }


}
