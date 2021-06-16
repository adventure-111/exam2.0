package cn.cuit.exam.service.impl;

import cn.cuit.exam.bean.Classroom;
import cn.cuit.exam.bean.PageBean;
import cn.cuit.exam.bean.Teacher;
import cn.cuit.exam.bean.vo.ClassroomQuery;
import cn.cuit.exam.mapper.ClassroomMapper;
import cn.cuit.exam.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    private ClassroomMapper mapper;

    @Override
    public List<Classroom> queryClassroomByTeachBuildingIdAndType(int id, int type) {
        return mapper.queryClassroomByTeachBuildingIdAndType(id, type);
    }

    @Override
    public int addClassroom(Classroom classroom) {
        if ( mapper.getClassroomBySite(classroom.getSite()) != null ) return 0;
        return mapper.insertClassroom(classroom);
    }

    @Override
    public Map addClassroomList(List<Classroom> list) {
        int successCount = 0;
        int totalCount = 0;

        for ( Classroom classroom : list ) {
            totalCount++;
            successCount += addClassroom(classroom);
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("success", successCount);
        map.put("fail", totalCount-successCount);
        return map;
    }

    @Override
    public PageBean<Classroom> selectClassroom(ClassroomQuery classroomQuery) {
        // 查询记录总条数
        int totalCount = mapper.selectClassroomCount(classroomQuery);
        // 查询教师集合
        List<Classroom> teacherList = mapper.selectClassroom(classroomQuery);
        // 创建pageBean
        PageBean<Classroom> pageBean = new PageBean<>(totalCount, teacherList, classroomQuery.getPageSize(), classroomQuery.getPageNum());

        return pageBean;
    }

    @Override
    public int updateClassroom(Classroom classroom) {
        return 0;
    }

    @Override
    public Map deleteClassroomList(List<String> siteList) {
        return null;
    }

    @Override
    public int deleteClassroom(String site) {
        return 0;
    }
}
