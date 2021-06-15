package cn.cuit.exam.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MajorMapperTest {

    @Autowired
    private MajorMapper mapper;

    @Test
    void test1() {
        System.out.println(mapper.getMshortByMno("0702"));
    }

}
