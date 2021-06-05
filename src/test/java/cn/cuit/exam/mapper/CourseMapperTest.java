package cn.cuit.exam.mapper;

import cn.cuit.exam.bean.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class CourseMapperTest {

    @Autowired
    private CourseMapper courseMapper;

    @Test
    public void test1() {
        Course course = new Course();
        course.setCno("CS005A");
        course.setCname("����ϵͳԭ��");
        course.setType(2);
        courseMapper.InsertCourse(course);
    }

}
