package cn.cuit.exam.service.impl;

import cn.cuit.exam.bean.Absence;
import cn.cuit.exam.bean.Teacher;
import cn.cuit.exam.bean.common.ExamUtils;
import cn.cuit.exam.mapper.AbsenceMapper;
import cn.cuit.exam.mapper.InspectorMapper;
import cn.cuit.exam.mapper.TeacherMapper;
import cn.cuit.exam.service.AbsenceService;
import cn.cuit.exam.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.PriorityQueue;

public class AbsenceServiceImpl implements AbsenceService {

    @Autowired
    private AbsenceMapper absenceMapper;

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private InspectorMapper inspectorMapper;


    @Override
    public int insertAbsence(Absence absence) {
        return absenceMapper.insertAbsence(absence);
    }

    @Override
    public int handleAbsence(Absence absence) {
        if (absence.getState() == 2) {              // 同意调换，系统将随机调换一个教师替补
            updateTeacherAllocation(absence);
        } else if (absence.getState() == 3) {       // 拒绝调换
            absenceMapper.updateAbsence(absence);
        }
        return 0;
    }

    public void updateTeacherAllocation(Absence absence) {
        if (ExamUtils.TeacherExamMap.containsKey(absence.getEno())) {
            // 获得所有参与监考这场考试的教师
            List<String> teachers = ExamUtils.TeacherExamMap.get(absence.getEno());

            // 获取所有教师构成的小顶堆
            PriorityQueue<Teacher> teacherQueue = teacherService.getMinHeapBySchool(teacherMapper.selectSchoolByTno(absence.getTno()));

            // 把原本已安排考试的教师剔除
            while (teacherQueue.size() > 0 && teachers.contains(teacherQueue.peek().getTno())) teacherQueue.poll();

            if (teacherQueue.size() == 0) {
                // 如果没有更过教师，则申请被驳回，驳回原因是没有更多教师
                absence.setState(1);
                absence.setComment("没有更多教师！");
                absenceMapper.updateAbsence(absence);
            } else {
                // 若还有教师，则替补
                inspectorMapper.updateInspectorByEnoAndTno(absence.getEno(), absence.getTno(), teacherQueue.peek().getTno());
            }
        }
    }
}
