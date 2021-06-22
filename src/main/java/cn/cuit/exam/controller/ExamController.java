package cn.cuit.exam.controller;

import cn.cuit.exam.bean.*;
import cn.cuit.exam.bean.common.ClassroomAllocation;
import cn.cuit.exam.bean.common.ExamUtils;
import cn.cuit.exam.bean.parameter.*;
import cn.cuit.exam.bean.vo.CourseQuery;
import cn.cuit.exam.mapper.CourseMapper;
import cn.cuit.exam.mapper.ExamMapper;
import cn.cuit.exam.mapper.TeacherMapper;
import cn.cuit.exam.service.ExamService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
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
    @Autowired
    private CourseMapper courseMapper;

 /********************************************考试信息*******************************************************/

    @PostMapping("/manage/time")
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
            exam.setState(1);
            examMapper.insertExam(exam);
        }
    }

    @PostMapping("/manage/exam/basic_info")
    @ApiOperation(value = "获取考试基本信息", notes = "【日期安排】点击【下一步】 -> 课程号<\\br>\t响应 -> 【】")
    public void examBasicInfo(@RequestBody ExamArr1Param examArr1Param, HttpServletRequest req) {
        HttpSession session = req.getSession();

        System.out.println((String) session.getAttribute("cno"));
        System.out.println(examArr1Param.getCnt());

        Exam exam = examMapper.getExamByCnoAndCnt((String) session.getAttribute("cno"), examArr1Param.getCnt());
        ExamUtils.ClassExamMap.put(exam.getEno(), examArr1Param.getCidList());

        examArr1Param.setDate(examArr1Param.getDate());
        System.out.println(examArr1Param.getCalendar().getTime());

        exam.setDay(examArr1Param.getCalendar());
        exam.setDuration(examArr1Param.getDuration());
        exam.setType(examArr1Param.getType());

        examMapper.updateDay(exam);
    }

    /********************************************自动排考*******************************************************/

    @PostMapping("/manage/classroom/get_auto_plan")
    @ApiOperation(value = "获取自动考场排考方案", notes = "请求 -> 课程号、场次、考试日期、考试时长，班级列表\n" +
            "<\br>\t响应 -> 【考试时间段 ：考场列表，...】")
    public ClassroomAllocation[] getClassroomAllocation(HttpServletRequest req, int cnt) {
        HttpSession session = req.getSession();
        Exam exam = examMapper.getExamByCnoAndCnt(String.valueOf(session.getAttribute("cno")), cnt);

        System.out.println("getClassroomAllocation----------" + (exam));
        System.out.println("getClassroomAllocation----------" + exam.day.getTime());

        ClassroomAllocation[] allocations = examService.ClassroomAllocation(exam);
        req.getSession().setAttribute("roomAllocation", allocations);
        return allocations;
    }

    @PostMapping("/manage/get_user_select")
    @ApiOperation(value = "获取用户选择哪个教室分配方案")
    public void getSelectOfUser(@RequestBody GetSelectOfUserParam getSelectOfUserParam, HttpServletRequest req) {
        HttpSession session = req.getSession();
        Exam exam = examMapper.getExamByCnoAndCnt((String) session.getAttribute("cno"), getSelectOfUserParam.getCnt());
        ClassroomAllocation[] allocations = (ClassroomAllocation[]) session.getAttribute("roomAllocation");
        ClassroomAllocation ca = allocations[getSelectOfUserParam.getSelected() - 1];
        session.setAttribute("ralloc", ca);
        examService.autoInsertExamSecondary(ca, exam);
    }

    @PostMapping("/manage/teacher/get_auto_plan")
    @ApiOperation(value = "获取自动教师分配方案", notes = "请求 -> 考场方案选择、考试类、学院\n" +
            "<\br>\t响应 -> 【考场 ：监考1，监考2】")
    public List<Inspector> getTeacherInspector(HttpServletRequest req, @RequestBody GetTeacherInspectorParam param) {
        HttpSession session = req.getSession();

        Exam exam = examMapper.getExamByCnoAndCnt((String) req.getSession().getAttribute("cno"), param.getCnt());
        ClassroomAllocation allocation = (ClassroomAllocation) session.getAttribute("ralloc");

        List<Inspector> inspectors = examService.getTeacherAllocation(exam, param.getSchool());
        req.getSession().setAttribute("teacherAllocation", inspectors);

        System.out.println(exam);

        for (Inspector inspector : inspectors) {
            inspector.setCno(exam.getCno());
            inspector.setCname(courseMapper.getNameByCno(exam.getCno()));
            inspector.setStart(exam.getStart());
            inspector.setEnd(exam.getEnd());
            inspector.setDay(exam.day.getTime());
            inspector.setTname1(teacherMapper.selectTnameByTno(inspector.getTeacher1()));
            inspector.setTname2(teacherMapper.selectTnameByTno(inspector.getTeacher2()));
        }

        return inspectors;
    }

    /********************************************手动排考*******************************************************/

    @GetMapping("/manage/classroom/get_conflict_table")
    @ApiOperation(value = "获取冲突表", notes = "请求 -> 班级号，场次，考试时长 -> 【考场1：冲突教室1，冲突教室2，...】")
    public Map<Integer, List<String>> getConflictSituation(@RequestBody ManualExamArr2Param param, HttpServletRequest req) {
        Exam exam = examMapper.getExamByCnoAndCnt((String) req.getSession().getAttribute("cno"), param.getCnt());
        return examService.getConflictSituation(param, exam.getDuration());
    }

    @PostMapping("/manage/teacher/isTeacherFree")
    @ApiOperation(value = "获取这个教师是否没有时间")
    public boolean isTeacherFree(@RequestBody IsTeacherFreeParam isTeacherFreeParam) {
        String tno = teacherMapper.selectTeacherByTname(isTeacherFreeParam.getTname()).getTno();
        return examService.isTeacherFree(tno, isTeacherFreeParam.getCnt());
    }

    @PostMapping("/manage/course_list")
    @ApiOperation(value = "获取课程列表", notes = "必要参数：school")
    public PageBean<Course> getCourseList(@RequestBody CourseQuery courseQuery) {

        return examService.getCourseList(courseQuery);
    }

    @GetMapping("/manage/cnt")
    @ApiOperation(value = "查看考试安排1", notes = "课程号 cno")
    public List<Patrol> examView1(String cno){
        return examService.queryPatrol(cno);
    }

    @GetMapping("/manage/cnt/site")
    @ApiOperation(value = "查看考试安排2", notes = "考试号 eno")
    public List<Inspector> examView2(String eno){
        return examService.queryInspector(eno);
    }

    @GetMapping("/manage/cnt/site/seat")
    @ApiOperation(value = "查看考试安排3", notes = "考试号 eno 教室号 site")
    public List<Examinee> examView3(String eno, String site){
        return examService.queryExaminee(eno, site);
    }

    @GetMapping("/manage/site/get_site")
    @ApiOperation(value = "根据教学楼和层数获取教室号", notes = "传值示例一教一层：no=11; 双中心一层：no=B1")
    public List getTeachBuilding(String no) {

        return examService.getSiteList(no);
    }
}
