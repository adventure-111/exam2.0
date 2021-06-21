package cn.cuit.exam.mapper;

import io.swagger.annotations.ApiModelProperty;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class UtilsMapperTest {
    @Autowired
    private UtilsMapper utilsMapper;

    @Test
    void selectMajorList() {
        System.out.println(utilsMapper.selectMajorList("软件工程"));
    }

    @Test
    void selectSemesterList() {
        System.out.println(utilsMapper.selectSemesterList());
    }

    @Test
    void selectClassNum() {
        System.out.println(utilsMapper.selectClassNum("软工",19,2));
    }

    @Test
    void selectAdmin() {
        System.out.println(utilsMapper.queryAdminByUsername("rjgc"));
    }
}