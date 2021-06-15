package cn.cuit.exam.service.impl;

import cn.cuit.exam.bean.Classroom;
import cn.cuit.exam.mapper.ClassroomMapper;
import cn.cuit.exam.service.ClassroomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassroomServiceImpl implements ClassroomService {

    @Autowired
    private ClassroomMapper mapper;

    @Override
    public List<Classroom> queryClassroomByTeachBuildingIdAndType(int id, int type) {
        return mapper.queryClassroomByTeachBuildingIdAndType(id, type);
    }
}
