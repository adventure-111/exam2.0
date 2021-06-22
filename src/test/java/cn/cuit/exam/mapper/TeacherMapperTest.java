package cn.cuit.exam.mapper;

import cn.cuit.exam.bean.vo.TeacherQuery;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class TeacherMapperTest {

    @Autowired
    private TeacherMapper teacherMapper;

    @Test
    void test1() {
        TeacherQuery teacherQuery = new TeacherQuery();
        teacherQuery.setSchool("软件工程");
        System.out.println(teacherMapper.selectTeacher(teacherQuery));
    }

}
