package cn.cuit.exam.service.impl;

import cn.cuit.exam.bean.*;
import cn.cuit.exam.bean.common.*;
import cn.cuit.exam.bean.vo.TeacherQuery;
import cn.cuit.exam.mapper.*;
import cn.cuit.exam.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ClassroomMapper classroomMapper;
    @Autowired
    private ExamMapper examMapper;
    @Autowired
    private UtilsMapper utilsMapper;
    @Autowired
    private ClassMapper classMapper;
    @Autowired
    private TeacherMapper teacherMapper;


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

        System.out.println(Utils.isClassTimeScheduleConflict(classList, temp.getDay(), temp.getDuration()));

        //如果所有班级之间两两不冲突
        if (Utils.isClassTimeScheduleConflict(classList, temp.getDay(), temp.getDuration())) {

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
            System.out.println("教室分配"+cas);
            return cas;

        } else {
            return null;
        }
    }



    /**
     * 排考第二步
     * 已知参数：班级、场次、课程、日期、持续时间
     */
    @Override
    public void autoInsertExamSecondary(ClassroomAllocation[] allocations, Exam temp, int selected) {
        temp.setStart(allocations[selected].getStart());
        temp.setEnd(allocations[selected].getEnd());

        // 添加考试和教室的对应关系
        temp.setEno(ExamUtils.examList.size());
        ExamUtils.examList.add(temp);
        List<String> rooms = new ArrayList<>();
        for (Classroom room : allocations[selected].getClassrooms()) {
            rooms.add(room.getSite());
        }

        ExamUtils.ClassroomExamMap.put(temp.getEno(), rooms);

        // 添加考试和班级的对应关系
        List<Integer> klasses = temp.getCidList();
        ExamUtils.ClassExamMap.put(temp.getEno(), klasses);
    }

    @Override
    public List<Inspector> getTeacherAllocation(String school, Exam exam) {

        PriorityQueue<Teacher> teachers = new PriorityQueue<>(
                new Comparator<Teacher>() {
                    @Override
                    public int compare(Teacher o1, Teacher o2) {
                        if (o1.getTotal().equals(o2.getTotal())) {
                            return o1.getPassivecnt() - o2.getPassivecnt();
                        } else {
                            return o1.getTotal() - o2.getTotal();
                        }
                    }
                }
        );

        //获取所有教师
        TeacherQuery teacherQuery = new TeacherQuery();
        teacherQuery.setSchool(school);

        teachers.addAll(teacherMapper.selectTeacher(teacherQuery));

        //获取所有考场
        List<Classroom> classrooms = new ArrayList<>();
        for (String site : ExamUtils.ClassroomExamMap.get(exam.getEno())) {
            classrooms.add(classroomMapper.getClassroomBySite(site));
        }

        //若教师数量不足，则返回空
        if (teachers.size() < classrooms.size() * 2) return null;


        //形成监考表
        List<Inspector> inspectors = new ArrayList<>();
        for (Classroom classroom : classrooms) {
            Inspector inspector = new Inspector();
            inspector.setEno(exam.getEno());
            inspector.setSite(classroom.getSite());

            //获取最应该监考的人
            Teacher temp = teachers.peek();

            //监考次数+1
            assert temp != null;
            inspector.setTeacher1(temp.getTno());
            teacherMapper.addTotalByOne(temp.getTno());

            teachers.poll();

            //获取最应该监考的人
            temp = teachers.peek();

            //监考次数+1
            assert temp != null;
            inspector.setTeacher2(temp.getTno());
            teacherMapper.addTotalByOne(temp.getTno());

            teachers.poll();

            //加入监考者
            inspectors.add(inspector);
        }

        return inspectors;
    }


    /**
     * 未完成的课程表
     *  已知参数
     */
    @Override
    public List<Exam> autoInsertExamThird(List<Exam> examList) {
        return null;
    }

    @Override
    public List<Klass> getClassList(String school, String cno) {
        System.out.println(school+cno);
        return examMapper.getClassList(school, cno);
    }

    // -------------------------------------controller调用-------------------------------------------------
    @Override
    public ClassroomAllocation[] ClassroomAllocation(Exam exam) {
        CourseTable.importCourseTable(new File("src/test/data/data.csv"));
        System.out.println(exam);
        List<Klass> classes = new ArrayList<>();
        for ( Integer cid: exam.getCidList() ) {
            System.out.println(cid);
            Klass c = classMapper.queryByCid(cid);
            c.setOccupy(new int[140][170]);
            classes.add(c);
        }
        return getClassroomAllocation(classes, exam);
    }


}
