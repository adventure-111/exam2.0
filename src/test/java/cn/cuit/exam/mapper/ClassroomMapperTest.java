package cn.cuit.exam.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClassroomMapperTest {

    @Autowired
    private ClassroomMapper classroomMapper;

    @Test
    void test1() {
        System.out.println(classroomMapper.queryClassroomByTeachBuildingIdAndType(2, 2));
    }

}
