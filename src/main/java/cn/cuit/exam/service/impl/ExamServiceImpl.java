package cn.cuit.exam.service.impl;

import cn.cuit.exam.bean.Class;
import cn.cuit.exam.bean.Exam;
import cn.cuit.exam.bean.common.Utils;
import cn.cuit.exam.mapper.ClassMapper;
import cn.cuit.exam.service.ExamService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

public class ExamServiceImpl implements ExamService {

    @Autowired
    private ClassMapper classMapper;


    @Override
    public int InsertExam() {
        return 0;
    }

    /**
     * 排考第二步
     * 已知参数：班级、场次、课程、日期、持续时间
     */
    @Override
    public List<Exam> autoInsertExamSecondary(List<Exam> examList) {
        if (examList.size() == 0) return null;

        List<Class> classList = new ArrayList<>();
        for (Exam exam : examList) {
            classList.add(classMapper.queryByCid(exam.getCid()));
        }
        Exam temp = examList.get(0);
        if (Utils.isClassTimeScheduleConflict(classList, temp.getDay(), temp.getDuration())) {




            return examList;
        } else {
            return null;
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
