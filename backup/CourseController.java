package cn.cuit.exam.controller;


import cn.cuit.exam.bean.Course;
import cn.cuit.exam.bean.PageBean;
import cn.cuit.exam.bean.vo.CourseQuery;
import cn.cuit.exam.service.CourseService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Api(value = "课程数据维护", description = "课程数据维护")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @PostMapping("/course")
    @ApiOperation(value = "课程数据查询", notes = "请求参数 必要参数: school; pageNum默认值:1; pageSize默认值：10;</br>")
    public PageBean<Course> queryCourse(@RequestBody CourseQuery courseQuery) {
        return courseService.queryCourse(courseQuery);
    }

    @PutMapping("/addCourse")
    @ApiOperation(value = "添加课程", notes = "请求参数有：课程号 课程名称 课程类型")
    public int addCourse(@RequestBody Course course) {
        return courseService.addCourse(course);
    }

    @DeleteMapping("/delCourse")
    @ApiOperation(value = "删除课程")
    public int deleteCourse(@RequestBody String cno) {
        return courseService.deleteCourse(cno);
    }

    @DeleteMapping("/delCourseList")
    @ApiOperation(value = "批量删除课程", notes = "[\"CS005A\", \"MS004C\"]")
    public int deleteStudentList(@RequestBody List<String> cnoList) { return courseService.deleteCourseList(cnoList); }

}
