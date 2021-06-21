package cn.cuit.exam.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ClassMapperTest {

    @Autowired
    private ClassMapper classMapper;

    @Test
    void test1() {
        System.out.println(classMapper.queryByCid(1));
    }

}
