package cn.cuit.exam.controller;

import cn.cuit.exam.bean.Exam;
import cn.cuit.exam.bean.Inspector;
import cn.cuit.exam.bean.Klass;
import cn.cuit.exam.bean.common.ClassroomAllocation;
import cn.cuit.exam.bean.common.ClassroomSection;
import cn.cuit.exam.bean.common.ExamUtils;
import cn.cuit.exam.bean.parameter.ExamArr1Param;
import cn.cuit.exam.bean.parameter.GetSelectOfUserParam;
import cn.cuit.exam.bean.parameter.IsTeacherFreeParam;
import cn.cuit.exam.bean.parameter.ManualExamArr2Param;
import cn.cuit.exam.mapper.ExamMapper;
import cn.cuit.exam.mapper.TeacherMapper;
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
    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    /********************************************考试信息*******************************************************/

    @GetMapping("/manage/time")
    @ApiOperation(value = "获取班级列表", notes = "school cno")
    public List<Klass> selectClassList(@RequestParam String school, @RequestParam String cno){
        System.out.println(school+cno);
        return examService.getClassList(school, cno);
    }

    @PostMapping("/manage/set_exam")
    @ApiOperation(value = "获取课程号", notes = "点击【排考】 -> 课程号<\\br>\t响应 -> 跳转至考试安排页面")
    public void setExam(HttpServletRequest req, String cno) {
        req.getSession().setAttribute("cno" ,cno);    // 将该课程号存入session
    }

    @PostMapping("/manage/get_exam_count")
    @ApiOperation(value = "获取考试场数", notes = "排考第一步：点击【确定】 -> 场次数 -> 教室分配")
    public void getExamCount(int cnt, HttpServletRequest req) {
        Exam exam = new Exam();
        exam.setCno((String) req.getSession().getAttribute("cno")); // 插入cnt个考试类
        for (int i = 1; i <= cnt; ++i) {
            exam.setCnt(i);
            examMapper.insertExam(exam);
        }
    }

    @PostMapping("/manage/exam/basic_info")
    @ApiOperation(value = "获取考试基本信息", notes = "【日期安排】点击【下一步】 -> 课程号<\\br>\t响应 -> 【】")
    public void examBasicInfo(@RequestParam ExamArr1Param examArr1Param, HttpServletRequest req) {
        HttpSession session = req.getSession();
        Exam exam = examMapper.getExamByCnoAndCnt((String) session.getAttribute("cno"), examArr1Param.getCnt());
        ExamUtils.ClassExamMap.put(exam.getEno(), examArr1Param.getCidList());
        examArr1Param.setDate(examArr1Param.getDate());
        exam.setDay(examArr1Param.getCalendar());
        exam.setDuration(examArr1Param.getDuration());
        examMapper.updateDay(exam);
    }

    /********************************************自动排考*******************************************************/

    @PostMapping("/manage/classroom/get_auto_plan")
    @ApiOperation(value = "获取自动考场排考方案", notes = "请求 -> 课程号、场次、考试日期、考试时长，班级列表\n" +
            "<\br>\t响应 -> 【考试时间段 ：考场列表，...】")
    public ClassroomAllocation[] getClassroomAllocation(HttpServletRequest req, int cnt) {
        HttpSession session = req.getSession();
        Exam exam = examMapper.getExamByCnoAndCnt(String.valueOf(session.getAttribute("cno")), cnt);
        ClassroomAllocation[] allocations = examService.ClassroomAllocation(exam);
        req.getSession().setAttribute("roomAllocation", allocations);
        return allocations;
    }

    @PostMapping("/manage/get_user_select")
    @ApiOperation(value = "获取用户选择哪个教室分配方案")
    public void getSelectOfUser(@RequestParam GetSelectOfUserParam getSelectOfUserParam, HttpServletRequest req) {
        HttpSession session = req.getSession();
        Exam exam = examMapper.getExamByCnoAndCnt((String) session.getAttribute("cno"), getSelectOfUserParam.getCnt());
        ClassroomAllocation[] allocations = (ClassroomAllocation[]) session.getAttribute("roomAllocation");
        examService.autoInsertExamSecondary(allocations[getSelectOfUserParam.getSelected() - 1], exam);
    }

    @PostMapping("/manage/teacher/get_auto_plan")
    @ApiOperation(value = "获取自动教师分配方案", notes = "请求 -> 考场方案选择、考试类、学院\n" +
            "<\br>\t响应 -> 【考场 ：监考1，监考2】")
    public void getTeacherInspector(HttpServletRequest req, @RequestParam int selected) {
        ClassroomAllocation[] allocations = (ClassroomAllocation[]) req.getSession().getAttribute("roomAllocation");
        Exam exam = (Exam) req.getSession().getAttribute("exam");
        List<Inspector> inspectors = examService.getTeacherAllocation(exam);
        req.getSession().setAttribute("teacherAllocation", inspectors);
    }

    /********************************************手动排考*******************************************************/

    @PostMapping("/manage/classroom/get_conflict_table")
    @ApiOperation(value = "获取冲突表", notes = "请求 -> 班级号，场次，考试时长 -> 【考场1：冲突教室1，冲突教室2，...】")
    public Map<Integer, List<String>> getConflictSituation(ManualExamArr2Param param, HttpServletRequest req) {
        Exam exam = examMapper.getExamByCnoAndCnt((String) req.getSession().getAttribute("cno"), param.getCnt());
        return examService.getConflictSituation(param, exam.getDuration());
    }

    @PostMapping("/manage/teacher/isTeacherFree")
    @ApiOperation(value = "获取这个教师是否没有时间")
    public boolean isTeacherFree(@RequestParam IsTeacherFreeParam isTeacherFreeParam) {
        String tno = teacherMapper.selectTeacherByTname(isTeacherFreeParam.getTname()).getTno();
        return examService.isTeacherFree(tno, isTeacherFreeParam.getCnt());
    }
}
