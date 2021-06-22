package cn.cuit.exam.mapper;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExamMapperTest {

    @Autowired
    private ExamMapper examMapper;

    @Test
    void test1() {
        System.out.println(examMapper.getExamByCnoAndCnt("CS005A", 1));
    }

}
