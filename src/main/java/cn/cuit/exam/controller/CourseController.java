package cn.cuit.exam.controller;


import cn.cuit.exam.bean.Course;
import cn.cuit.exam.bean.ExcelUtils;
import cn.cuit.exam.bean.PageBean;
import cn.cuit.exam.bean.Teach;
import cn.cuit.exam.bean.vo.CourseQuery;
import cn.cuit.exam.bean.vo.TeachQuery;
import cn.cuit.exam.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.awt.*;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "课程数据维护", description = "课程数据维护")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PutMapping("/course")
    @ApiOperation(value = "添加课程", notes = "请求参数有：课程号 课程名 类型")
    public int addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @PutMapping("/course/import")
    @ApiOperation(value = "添加课程(导入文件)",
            notes = "文件示例：课程号 课程名 类型",
            consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map importCourse(@RequestPart(value = "file")MultipartFile file) {
        List<Course> list = ExcelUtils.readExcel("", Course.class, file);
        return courseService.addCourseList(list);
    }

    @PutMapping("/course/teach")
    @ApiOperation(value = "添加授课关系", notes = "请求参数有：课程名 班级名 教师名")
    public int addTeach(@RequestBody Teach teach) { return courseService.addTeach(teach); }


    @PutMapping("/course/teach/import")
    @ApiOperation(value = "添加授课关系(导入文件)",
                    notes = "文件示例：课程名 班级名 教师名",
                    consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Map importTeach(@RequestPart(value = "file")MultipartFile file) {
        List<Teach> list = ExcelUtils.readExcel("", Teach.class, file);
        return courseService.addTeachList(list);
    }


    @DeleteMapping("/course")
    @ApiOperation(value = "删除课程", notes = "{ \"school\" : \"软件工程\", \"cno\":\"CS005A\"} 请求参数：学院school 课程号cno，\n" +
            "\t</br>当该课程其他学院存在教学班时返回-1（提示该课程不可删除，但是删除了本学院的授课关系）删除成功返回1 删除失败返回0")
    public int deleteCourse(@RequestBody Map map) {
        String school = (String) map.get("school");
        String cno = (String) map.get("cno");
        System.out.println(map);
        return courseService.deleteCourse(school, cno);
    }

    @DeleteMapping("/course/List")
    @ApiOperation(value = "批量删除课程", notes = "{ \"school\" : \"软件工程\", \"list\":[\"CS005A\", \"CS006A\"]}")
    public Map deleteCourseList(@RequestBody Map map) {
        String school = (String) map.get("school");
        List<String> list = (List<String>) map.get("list");
        return courseService.deleteCourseList(school, list);
    }

    @PatchMapping("/course")
    @ApiOperation(value = "修改课程信息", notes = "请求参数：cno(不可修改) cname type")
    public int updateCourse(@RequestBody Course course) {
        return courseService.updateCourse(course);
    }

    @PostMapping("/course")
    @ApiOperation(value = "查询课程数据", notes = "school(requested){\"school\": \"软件工程\"}")
    public PageBean<Course> selectCourse(@RequestBody CourseQuery courseQuery) {
        System.out.println(",,,,");
        return courseService.selectCourse(courseQuery);
    }

    @PostMapping("/course/teach")
    @ApiOperation(value = "查询授课关系", notes = "{\"school\":\"软件工程\", \"cno\":\"CS005A\" }")
    public PageBean<Teach> selectTeach(@RequestBody TeachQuery teachQuery) {
        return courseService.selectTeach(teachQuery);
    }

    @DeleteMapping("/course/teach")
    @ApiOperation(value = "删除授课关系", notes = "请求参数有：课程号 班级名 教师名 {\"cno\":\"CS005A\", \"class_name\":\"软工192\", \"tname\":\"冯波\"}")
    public int deleteTeachList(@RequestBody Teach teach) { return courseService.deleteTeach(teach); }

    @DeleteMapping("/course/teach/List")
    @ApiOperation(value = "批量删除授课关系", notes = "请求参数有：课程号 班级名 教师名【数组】[{\"cno\":\"CS005A\", \"class_name\":\"软工192\", \"tname\":\"冯波\"}, {\"cno\":\"CS005A\", \"class_name\":\"软工192\", \"tname\":\"冯波\"}]")
    public Map deleteTeachList(@RequestBody List<Teach> teachList) { return courseService.deleteTeachList(teachList); }

    @DeleteMapping("course/teach/all")
    @ApiOperation(value = "删除全部授课关系", notes = "请求参数有：学院名 课程号 {\"school\":\"软件工程\", \"cno\":\"CS005A\" }")
    public int deleteTeachAll(@RequestBody Map map) {
        String school = (String) map.get("school");
        String cno = (String) map.get("cno");
        return courseService.deleteTeachAll(school, cno);
    }

    @DeleteMapping("course/all")
    @ApiOperation(value = "删除全部课程", notes = "请求参数有：学院名 {\"school\":\"软件工程\" }")
    public Map deleteCourseAll(@RequestBody String school) {
        return courseService.deleteCourseAll(school);
    }
}
