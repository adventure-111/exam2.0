package cn.cuit.exam.controller;

import cn.cuit.exam.bean.ExcelUtils;
import cn.cuit.exam.bean.PageBean;
import cn.cuit.exam.bean.Student;
import cn.cuit.exam.bean.vo.StudentQuery;
import cn.cuit.exam.mapper.UtilsMapper;
import cn.cuit.exam.service.StudentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;
import java.util.stream.Collectors;

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

    @PutMapping("/student/import")
    @ApiOperation(value = "添加学生(导入文件)",
            notes = "文件示例：学号 姓名 密码 专业 年级 班级号\n",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map importStudent(@RequestPart(value = "file") MultipartFile file) {
//        long t1 = System.currentTimeMillis();
//        long t2 = System.currentTimeMillis();
//        System.out.println(String.format("read over! cost:%sms", (t2 - t1)));
        System.out.println("!!!!!!!!!!!!!!!!!!!!!!");
        List<Student> list = ExcelUtils.readExcel("", Student.class, file);
//        System.out.println(list);
        Map map = studentService.addStudentList(list);

        return map;
    }

    @DeleteMapping("/student")
    @ApiOperation(value = "删除学生")
    public int deleteStudent(String sno) {
        int count = studentService.deleteStudent(sno);

        return count;
    }


    @DeleteMapping("/delStudentList")
    @ApiOperation(value = "批量删除学生", notes = "[\"2019000003\", \"2019000004\"]")
    public void deleteStudentList(@RequestBody String[] snos) {
        System.out.println(snos);
        List snoList = Arrays.stream(snos).collect(Collectors.toList());
        System.out.println(snoList);
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
    public Map majorList(String school) {
        System.out.println("--------------");
        Map<String, ArrayList<Map>> map = new HashMap<>();
        ArrayList<Map> majors = new ArrayList<>();
        List<String> list = utilsMapper.selectMajorList(school);
        System.out.println(list);
        for ( String m :  list ) {
            Map<String, String> major = new HashMap<>();
            major.put("name", m);
            major.put("value", m);
            majors.add(major);
            System.out.println("-----------------"+m);
        }
        System.out.println("-----------------"+majors+"--------------");
        map.put("list", majors);
        return map;
    }

    @GetMapping("/student/semesterList")
    @ApiOperation(value = "查询条件:年级下拉列表")
    public List semesterList() {
         return utilsMapper.selectSemesterList();
    }

}
