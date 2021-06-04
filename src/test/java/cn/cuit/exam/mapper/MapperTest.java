package cn.cuit.exam.mapper;

import cn.cuit.exam.bean.Student;
import cn.cuit.exam.bean.Teacher;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MapperTest {

    //    @Autowired
//    private ClassMapper classMapper;
//    @Autowired
//    private MajorMapper majorMapper;
    @Autowired
    private StudentMapper studentMapper;
    @Autowired
    private TeacherMapper teacherMapper;

    @Test
    void selectBySno() {
        studentMapper.selectBySno("2019081188");
    }

    void insertClass() {
        Student student = new Student();
//        student.setMshort();
//        studentMapper.insertClass()
    }

    @Test
    void addTeacher() {
        Teacher teacher = new Teacher();
        teacher.setSchool("Èí¼þ¹¤³Ì");
        teacher.setPassword("123");
        teacher.setTno("0702002");
        teacher.setTname("XXX");
//        System.out.println(teacherMapper.addTeacher(teacher));
        System.out.println(teacherMapper.addUser(teacher));
    }
}