package cn.cuit.exam.controller;

import cn.cuit.exam.bean.Exam;
import cn.cuit.exam.bean.Inspector;
import cn.cuit.exam.bean.Klass;
import cn.cuit.exam.bean.common.ClassroomAllocation;
import cn.cuit.exam.bean.common.ExamUtils;
import cn.cuit.exam.bean.parameter.ExamArr2Param;
import cn.cuit.exam.service.ExamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
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

    @PostMapping("/manage/set_exam")
    @ApiModelProperty(value = "获取考试基本信息", notes = "点击【排考】 -> 课程号<\\br>\t响应 -> 跳转至考试安排页面")
    public void setExam(HttpServletRequest req, String cno) {
        Exam exam = new Exam();
        exam.setCno(cno);
        req.getSession().setAttribute("exam" ,exam);    // 将该考试类存入session
    }

    @PostMapping("/manage/exam/basic_info")
    @ApiModelProperty(value = "获取考试基本信息", notes = "点击【排考】 -> 课程号<\\br>\t响应 -> 【】")
    public void examArrange(@RequestParam ExamArr2Param examArr2Param, HttpServletRequest req) {
        Exam exam = (Exam) req.getSession().getAttribute("exam");
        HttpSession session = req.getSession();
        exam.setEno(ExamUtils.examList.size());
        ExamUtils.examList.add(exam);
        session.setAttribute("exam", exam);
    }

    @PostMapping("/manage/classroom/get_auto_plan")
    @ApiOperation(value = "获取自动考场排考方案", notes = "请求 -> 课程号、场次、考试日期、考试时长，班级列表\n" +
            "<\br>\t响应 -> 【考试时间段 ：考场列表，...】")
    public ClassroomAllocation[] getClassroomAllocation(HttpServletRequest req, int cnt) {
        HttpSession session = req.getSession();
        Exam exam = (Exam) session.getAttribute("exam");
        ClassroomAllocation[] allocations = examService.ClassroomAllocation(exam);
        req.getSession().setAttribute("roomAllocation", allocations);
        return allocations;
    }

    @PostMapping("/manage/teacher/get_auto_plan")
    @ApiOperation(value = "获取自动教师分配方案", notes = "请求 -> 考场方案选择、考试类、学院\n" +
            "<\br>\t响应 -> 【考场 ：监考1，监考2】")
    public void getTeacherInspector(HttpServletRequest req, @RequestParam int selected) {
        ClassroomAllocation[] allocations = (ClassroomAllocation[]) req.getSession().getAttribute("roomAllocation");
        Exam exam = (Exam) req.getSession().getAttribute("exam");
        examService.autoInsertExamSecondary(allocations, exam, selected);
        List<Inspector> inspectors = examService.getTeacherAllocation(exam);
        req.getSession().setAttribute("teacherAllocation", inspectors);
    }
}
