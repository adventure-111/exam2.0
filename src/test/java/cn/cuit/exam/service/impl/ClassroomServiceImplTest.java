package cn.cuit.exam.service.impl;

import cn.cuit.exam.service.ClassroomService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClassroomServiceImplTest {

    @Autowired
    private ClassroomService classroomService;

    @Test
    void test1() {
        System.out.println(classroomService.queryClassroomByTeachBuildingIdAndType(2, 2));
    }

}
