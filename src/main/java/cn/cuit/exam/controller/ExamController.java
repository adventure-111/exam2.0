package cn.cuit.exam.controller;

import cn.cuit.exam.bean.Exam;
import cn.cuit.exam.bean.Inspector;
import cn.cuit.exam.bean.Klass;
import cn.cuit.exam.bean.common.ClassroomAllocation;
import cn.cuit.exam.service.ExamService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController()
@Api(value = "考试安排", description = "考试安排")
public class ExamController {
    @Autowired
    private ExamService examService;

    @GetMapping("/manage/time")
    @ApiOperation(value = "获取班级列表", notes = "school cno")
    public List<Klass> selectClassList(@RequestParam String school, @RequestParam String cno){

        System.out.println(school+cno);
        return examService.getClassList(school, cno);
    }

    @PostMapping("/manage/classroom/get_auto_plan")
    @ApiOperation(value = "获取自动考场排考方案", notes = "请求 -> 课程号、场次、考试日期、考试时长，班级列表\n" +
            "<\br>\t响应 -> 【考试时间段 ：考场列表，...】")
    public ClassroomAllocation[] getClassroomAllocation(@RequestBody Exam exam) {

        return examService.ClassroomAllocation(exam);
    }

    @PostMapping("/manage/teacher/get_auto_plan")
    @ApiOperation(value = "获取自动教师分配方案", notes = "请求 -> 考场方案选择、考试类、学院\n" +
            "<\br>\t响应 -> 【考场 ：监考1，监考2】")
    public List<Inspector> getTeacherInspector(@RequestParam String school, @RequestBody Exam exam) {
        examService.autoInsertExamSecondary(this.getClassroomAllocation(exam), exam, 0);
        return examService.getTeacherAllocation("软件工程", exam);
    }

//    @PostMapping("/manage/")
}
