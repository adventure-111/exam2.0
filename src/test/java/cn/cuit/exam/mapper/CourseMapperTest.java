package cn.cuit.exam.mapper;

import cn.cuit.exam.bean.Course;
import org.junit.jupiter.api.Test;

public class CourseMapperTest {

    CourseMapper mapper;

    @Test
    void testQuery() {

    }

    @Test
    void testInsert() {
        Course course = new Course();
        course.setCno("CS005A");
        course.setCname("����ϵͳԭ��");
        course.setType(2);


    }

}
