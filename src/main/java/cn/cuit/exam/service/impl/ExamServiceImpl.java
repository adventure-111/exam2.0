package cn.cuit.exam.service.impl;

import cn.cuit.exam.bean.*;
import cn.cuit.exam.bean.common.*;
import cn.cuit.exam.bean.parameter.ManualExamArr2Param;
import cn.cuit.exam.bean.vo.CourseQuery;
import cn.cuit.exam.bean.vo.TeacherQuery;
import cn.cuit.exam.mapper.*;
import cn.cuit.exam.service.ExamService;
import cn.cuit.exam.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.*;
import java.text.ParseException;

@Service
public class ExamServiceImpl implements ExamService {
    @Autowired
    private ClassroomMapper classroomMapper;
    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private TeacherService teacherService;
    @Autowired
    private InspectorMapper inspectorMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private ExamineeMapper examineeMapper;
    @Autowired
    private AbsenceMapper absenceMapper;
    @Autowired
    private CourseMapper courseMapper;


    public void test() {
        System.out.println("OK!");
    }


    /**
     * 获取所有供选方案
     * 排考第二步
     */
    @Override
    public ClassroomAllocation[] getClassroomAllocation(List<Klass> classList, Exam temp) {
        Klass classTemp = classList.get(0);
        Classroom crtemp = null;

        System.out.println(Utils.isClassTimeScheduleConflict(classList, temp.day, temp.getDuration()));

        //如果所有班级之间两两不冲突
        if (Utils.isClassTimeScheduleConflict(classList, temp.day, temp.getDuration())) {

            int duration = temp.getDuration() / 5;  //考试时长

            System.out.println("getClassroomAllocation");

            // 获取每栋教学楼的可用教室
            List<Classroom>[] teachBuildings = new ArrayList[4];
            for (int i = 0; i < 4; ++i) {
                teachBuildings[i] = classroomMapper.queryClassroomByTeachBuildingIdAndType(i + 1, temp.getType());
                if (teachBuildings[i].size() > 0) {
                    crtemp = teachBuildings[i].get(0);
                }
            }

            System.out.println(crtemp);

            // 如果没有可用教室
            if (crtemp == null) return null;

            int examineeNum = classTemp.getNum() * classList.size();    //考生人数
            int examRoomNum = (int) Math.ceil(1.0 * examineeNum / crtemp.getCapacity()); //获取所需考场数，向上取整

            System.out.println(examineeNum + " " + examRoomNum);

            //存储四个教学楼的所有合法教室在特定的考试时间段有空闲时段的可用教室
            List<Classroom>[][] usableClassrooms = new ArrayList[4][3];
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 3; ++j) usableClassrooms[i][j] = new ArrayList<Classroom>();
            }

            // 获取每个教学楼三个时间点可用的教室
            for (int i = 0; i < 4; ++i) {   //遍历教学楼
                for (int j = 0; j < 3; ++j) {   //遍历三个时间点
                    for (Classroom room : teachBuildings[i]) {
                        if (!CourseTable.classnameToId.containsKey(room.getSite()) || CourseTable.classrooms.get(CourseTable.classnameToId.
                                get(room.getSite())).queryForFree(temp.getWeek(), temp.getWeekDay(), Utils.UsableTime[j], Utils.UsableTime[i] + duration)) {
                            usableClassrooms[i][j].add(room);
                        }
                    }
                    System.out.println(usableClassrooms[i][j].size());
                }
            }

            //获取供选方案
            ClassroomAllocation[] cas = new ClassroomAllocation[4];
            for (int i = 0; i < 4; ++i) {
                for (int j = 0; j < 3; ++j) {
                    if (usableClassrooms[i][j].size() >= examRoomNum) {
                        List<Classroom> tmp = new ArrayList<>();
                        for (Classroom classroom : usableClassrooms[i][j]) {
                            tmp.add(classroom);
                            if (tmp.size() == examRoomNum) break;
                        }
                        cas[i] = (new ClassroomAllocation(Utils.getCalenderByAxis(Utils.UsableTime[j]),
                                Utils.getCalenderByAxis(Utils.UsableTime[j] + duration), tmp));
                    }
                    System.out.println(cas[i]);
                }
            }
            System.out.println("教室分配"+ Arrays.toString(cas));
            return cas;

        } else {
            return null;
        }
    }



    /**
     * 排考第二步
     * 已知参数：教室分配，课程信息，场次
     * 未知参数：用户选择
     */
    @Override
    public void autoInsertExamSecondary(ClassroomAllocation allocation, Exam temp) {
        temp.setStart(allocation.getStart());
        temp.setEnd(allocation.getEnd());

        // 添加考试和教室的对应关系
        temp.setEno(ExamUtils.examList.size());
        ExamUtils.examList.set(temp.getEno(), temp);

        List<String> rooms = new ArrayList<>();
        for (Classroom room : allocation.getClassrooms()) {
            rooms.add(room.getSite());
        }

        ExamUtils.ClassroomExamMap.put(temp.getEno(), rooms);
    }

    @Override
    public List<Inspector> getTeacherAllocation(Exam exam) {

        // 获取所有教师构成的小顶堆
        PriorityQueue<Teacher> teachers = teacherService.getMinHeapBySchool(exam.getSchool());

        // 形成(eno, List<tno>)对照表的值
        List<String> tnos = new ArrayList<>();

        //获取所有考场
        List<Classroom> classrooms = new ArrayList<>();
        for (String site : ExamUtils.ClassroomExamMap.get(exam.getEno())) {
            classrooms.add(classroomMapper.getClassroomBySite(site));
        }

        //若教师数量不足，则返回空
        if (teachers.size() < classrooms.size() * 2 + 2) return null;


        /**
         * 形成监考表
         * 每次取出堆顶的两个元素
         */

        // 设置巡考教师
        exam.setPatrol1(teachers.peek().getTno());
        teachers.poll();
        exam.setPatrol2(teachers.peek().getTno());
        teachers.poll();

        List<Inspector> inspectors = new ArrayList<>();
        for (Classroom classroom : classrooms) {
            // 获取监考教师的基本信息
            Inspector inspector = new Inspector();
            inspector.setEno(exam.getEno());
            inspector.setSite(classroom.getSite());

            //获取最应该监考的人
            Teacher temp = teachers.peek();

            //设置监考1
            assert temp != null;
            inspector.setTeacher1(temp.getTno());
            tnos.add(temp.getTno());

            teachers.poll();

            //获取最应该监考的人
            temp = teachers.peek();

            //设置监考2
            assert temp != null;
            inspector.setTeacher2(temp.getTno());
            tnos.add(temp.getTno());

            teachers.poll();

            //加入监考者
            inspectors.add(inspector);
        }

        // 插入监考教师表
        for (Inspector inspector : inspectors) inspectorMapper.insertInspector(inspector);

        // 返回监考方案
        return inspectors;
    }


    /**
     * 将与这场考试有关的所有考试信息放入数据库
     */
    @Override
    public void tempStore(Exam exam) {
        examMapper.insertExam(exam);
    }

    @Override
    public List<Klass> getClassList(String school, String cno) {
        System.out.println(school+cno);
        return examMapper.getClassList(school, cno);
    }

    /**
     * 排考第二步--手动排考--获取冲突表
     * @param param
     * @return
     * @throws ParseException
     */
    @Override
    public Map<Integer, List<String>> getConflictSituation(ManualExamArr2Param param, int duration) {

        // 将字符串表示的日期转化为Calender类
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date tempDate = null;
        try {
            tempDate = sdf.parse(param.getDay());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        Calendar calendar = Calendar.getInstance();

        assert tempDate != null;
        calendar.setTime(tempDate);

        // 获取当天是第几周的星期几
        int week = Utils.getWeekByDate(calendar);
        int weekday = Utils.getWeekdayByDate(calendar);

        // 结果集
        Map<Integer, List<String>> res = new HashMap<>();

        for (int i = 0; i < Utils.UsableTime.length; ++i) {
            int rel = Utils.UsableTime[i];
            List<String> list = new ArrayList<>();
            for (String site : param.getSites()) {
                if (!CourseTable.classrooms.get(CourseTable.classnameToId.get(site)).queryForFree(
                        week, weekday, rel, rel + duration / 5
                )) {
                    list.add(site);
                }
            }
            res.put(i, list);
        }

        return res;

    }

    // -------------------------------------controller调用-------------------------------------------------
    @Override
    public ClassroomAllocation[] ClassroomAllocation(Exam exam) {
        CourseTable.importCourseTable(new File("src/test/data/data.csv"));
        System.out.println(exam);
        List<Klass> classes = new ArrayList<>();
        for ( Integer cid: ExamUtils.ClassExamMap.get(exam.getEno()) ) {
            System.out.println(cid);
            Klass c = classMapper.queryByCid(cid);
            c.setOccupy(new int[140][170]);
            classes.add(c);
        }
        return getClassroomAllocation(classes, exam);
    }

    public void deploy(Exam exam) {
        examMapper.insertExam(exam);

        // 为参与这场考试的所有考生安排座位

        List<Student> students = new ArrayList<>();
        List<Classroom> rooms =  new ArrayList<>();
        List<Klass> klasses = new ArrayList<>();

        // 1. 获得所有考场
        for (String site : ExamUtils.ClassroomExamMap.get(exam.getEno())) {
            rooms.add(classroomMapper.getClassroomBySite(site));
        }

        // 2. 获得所有班级
        for (int cid : ExamUtils.ClassExamMap.get(exam.getEno())) {
            klasses.add(classMapper.queryByCid(cid));
        }

        // 3. 获得所有学生
        for (Klass klass : klasses) {
            students.addAll(studentMapper.getAllStuByCid(klass.getCid()));
        }

        // 考场位子的数量,每个考场的容量
        int seatNum = 0, cap = rooms.get(0).getCapacity();

        // 考生数量
        int examineeNum = students.size();

        // 考场数量
        int roomNum = rooms.size();

        // 获得位子的总数
        for (Classroom classroom : rooms) {
            seatNum += classroom.getCapacity();
        }

        // 随机分配位置，打乱顺序再逐个依次分配座位
        Collections.shuffle(students);

        for (int i = 0; i < students.size(); ++i) {
            Examinee examinee = new Examinee();

            // 将学生转化为考生
            examinee.setEno(exam.getEno());                 // 设置考试号
            examinee.setSno(students.get(i).getSno());      //设置学号
            examinee.setSite(rooms.get(i / cap).getSite()); //设置地点
            examinee.setSeat(i % cap + 1);                  //设置座位号

            // 加入考试表
            examineeMapper.insertExaminee(examinee);
        }
    }

    public boolean isTeacherFree(String tno, int eno) {
        return !(absenceMapper.queryByEnoAndTno(eno, tno) == null);
    }

    @Override
    public PageBean<Course> getCourseList(CourseQuery courseQuery) {
        // 查询总记录条数
        int totalCount = courseMapper.selectCourseCount(courseQuery);
        // 查询课程集合
        List<Course> list = courseMapper.selectCourse(courseQuery);
        // 创建pageBean
        PageBean<Course> pageBean = new PageBean<Course>(totalCount, list, courseQuery.getPageSize(), courseQuery.getPageNum());

        return pageBean;
    }

    @Override
    public List<Patrol> queryPatrol(String cno) {
        return examMapper.queryPatrol(cno);
    }

    @Override
    public List<Inspector> queryInspector(String eno) {
        return examMapper.queryInspector(eno);
    }

    @Override
    public List<Examinee> queryExaminee(String eno, String site) {
        return examMapper.queryExaminee(eno, site);
    }

    @Override
    public List<Map> getSiteList(String no) {
        return classroomMapper.getSiteByTeachBuildingAndLayers(no);
    }


}
