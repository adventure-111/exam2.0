package cn.cuit.exam.service.impl;

import cn.cuit.exam.bean.Exam;
import cn.cuit.exam.bean.Klass;
import cn.cuit.exam.bean.common.CourseTable;
import cn.cuit.exam.mapper.ClassMapper;
import cn.cuit.exam.service.ExamService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

@SpringBootTest
public class ExamServiceImplTest {

    @Autowired
    private ExamService examService;

    @Autowired
    private ClassMapper classMapper;

    @Test
    void test1() {
        File file = new File("src/test/data/data.csv");
        CourseTable.importCourseTable(file);
        List<Klass> classList = new ArrayList<>();
        for (int i = 1; i <= 5; ++i) {
           Klass temp = classMapper.queryByCid(i);
            temp.setOccupy(new int[140][170]);
            classList.add(temp);
        }

        Exam exam = new Exam();
        exam.setCno("CS005A");
        exam.setCnt(1);
        exam.setType(2);
        exam.setDay(Calendar.getInstance());
        exam.setState(0);
        exam.setDuration(90);

        System.out.println(examService.getClassroomAllocation(classList, exam)[1]);

        
    }

}
