package cn.cuit.exam.mapper;

import cn.cuit.exam.bean.Student;
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
    void test1() {

    }

}
