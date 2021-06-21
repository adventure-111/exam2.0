package cn.cuit.exam;

import org.apache.ibatis.annotations.Mapper;
import org.mybatis.spring.annotation.MapperScan;
//import org.mybatis.spring.annotation.MapperScans;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
<<<<<<< HEAD
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
=======
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c

@MapperScan("cn.cuit.exam.mapper")
@SpringBootApplication
public class ExamApplication{

    public static void main(String[] args) {
        SpringApplication.run(ExamApplication.class, args);
    }

}
