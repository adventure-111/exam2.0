package cn.cuit.exam.controller;

import cn.cuit.exam.bean.Inspector;
import cn.cuit.exam.bean.Patrol;
import cn.cuit.exam.mapper.ExamMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Api(value = "教师端")
public class InspectorController {
    @Autowired
    private ExamMapper examMapper;

    @GetMapping("/exam_info_view/teacher")
    @ApiOperation(value = "查看监考，巡考任务")
    public Map examInfo(String  username) {

        // 查看监考任务
        List<Inspector>  inspectorList= examMapper.getInspectorInfo(username);
        // 查看巡考任务
        List<Patrol>  patrolList= examMapper.getPatrolInfo(username);

        Map<String, List> map = new HashMap<>();
        map.put("inspector", inspectorList);
        map.put("patrol", patrolList);

        return map;
    }
}
