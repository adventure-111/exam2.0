package cn.cuit.exam.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class MajorServiceImplTest {

    @Autowired
    private MajorServiceImpl service;

    @Test
    void test1() {
        System.out.println(service.getMshortByMno("0702"));
    }

}
