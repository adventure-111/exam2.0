package cn.cuit.exam.service.impl;

import cn.cuit.exam.bean.Class;
import cn.cuit.exam.bean.Classroom;
import cn.cuit.exam.bean.Course;
import cn.cuit.exam.bean.Exam;
import cn.cuit.exam.bean.common.*;
import cn.cuit.exam.mapper.ClassMapper;
import cn.cuit.exam.mapper.ClassroomMapper;
import cn.cuit.exam.service.ClassroomService;
import cn.cuit.exam.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ExamServiceImpl implements ExamService {

    @Autowired
    private ClassroomMapper classroomMapper;


    public void test() {
        System.out.println("OK!");
    }

    /**
     * 获取所有供选方案
     * 排考第二步
     */
    @Override
    public ClassroomAllocation[] getClassroomAllocation(List<Class> classList, Exam temp) {
        Class classTemp = classList.get(0);
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
    public void autoInsertExamSecondary(ClassroomAllocation[] allocations, Exam temp, int selected, List<Class> classList) {
        temp.setStart(allocations[selected].getStart());
        temp.setEnd(allocations[selected].getEnd());

        // 添加考试和教室的对应关系
        temp.setEno(ExamUtils.examList.size());
        ExamUtils.examList.add(temp);
        for (Classroom room : allocations[selected].getClassrooms()) {
            List<Integer> listTmp;
            if (ExamUtils.ClassroomExamMap.containsKey(room.getSite())) {
                listTmp = ExamUtils.ClassroomExamMap.get(room.getSite());
            } else {
                listTmp = new ArrayList<>();
            }
            listTmp.add(temp.getEno());
            ExamUtils.ClassroomExamMap.put(room.getSite(), listTmp);
        }

        // 添加考试和班级的对应关系
        for (Class t : classList) {
            List<Integer> listTmp;
            if (ExamUtils.ClassExamMap.containsKey(t.getCid())) {
                listTmp = ExamUtils.ClassExamMap.get(t.getCid());
            } else {
                listTmp = new ArrayList<>();
            }
            listTmp.add(temp.getEno());
            ExamUtils.ClassExamMap.put(t.getCid(), listTmp);
        }
    }


    /**
     * 未完成的课程表
     *  已知参数
     */
    @Override
    public List<Exam> autoInsertExamThird(List<Exam> examList) {
        return examList;
    }
}
