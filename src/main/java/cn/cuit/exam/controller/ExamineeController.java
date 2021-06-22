package cn.cuit.exam.controller;

import cn.cuit.exam.bean.Examinee;
import cn.cuit.exam.mapper.ExamMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "学生端", description = "学生端")
public class ExamineeController {
    @Autowired
    private ExamMapper examMapper;

    @GetMapping("/exam_info_view/student")
    @ApiOperation(value = "查看考试信息")
    public List examInfo(String username) {
        System.out.println("-----------------------"+username);
        System.out.println("===============");
        List examinee = examMapper.getStuExamInfo(username);
        System.out.println(examinee);
        return examinee;
    }

}
