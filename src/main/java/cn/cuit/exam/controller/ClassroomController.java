package cn.cuit.exam.controller;

import cn.cuit.exam.bean.Classroom;
import cn.cuit.exam.bean.ExcelUtils;
import cn.cuit.exam.bean.PageBean;
import cn.cuit.exam.bean.vo.ClassroomQuery;
import cn.cuit.exam.service.ClassroomService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@Api(value = "教室数据维护", description = "教室数据维护")
public class ClassroomController {
    @Autowired
    private ClassroomService classroomService;

    @PutMapping("/classroom")
    @ApiOperation(value = "添加教室", notes = "{\"site\": H1101, \"capacity\": 150, \"type\": 2}")
    public int addClassroom(@RequestBody Classroom classroom) {
        return classroomService.addClassroom(classroom);
    }

    @PutMapping("/classroom/import")
    @ApiOperation(value = "导入教室数据", notes = "文件列名：编号 容量 类型")
    public Map importClassroom(@RequestPart(value = "file")MultipartFile file) {
        List<Classroom> list = ExcelUtils.readExcel("", Classroom.class, file);
        Map map = classroomService.addClassroomList(list);
        return map;
    }

    @PostMapping("/classroom")
    @ApiOperation(value = "教室数据查询")
    public PageBean<Classroom> queryClassroom(@RequestBody ClassroomQuery classroomQuery) {
        PageBean<Classroom> pageBean = classroomService.selectClassroom(classroomQuery);

        return pageBean;
    }
//
//    @DeleteMapping("/classroom")
//    @ApiOperation(value = "删除教室数据")
//    public int deleteClassroom(@RequestBody String site) {
//        return classroomService.deleteClassroom(site);
//    }
//
//    @DeleteMapping("/classroom/list")
//    @ApiOperation(value = "批量删除教室数据")
//    public Map deleteClassroom(@RequestBody List<String> siteList) {
//        return classroomService.deleteClassroomList(siteList);
//    }
//
//    @PatchMapping("/classroom")
//    @ApiOperation(value = "修改课程数据")
//    public int updateClassroom(@RequestBody Classroom classroom) {
//        return classroomService.updateClassroom(classroom);
//    }
}
