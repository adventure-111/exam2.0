package cn.cuit.exam.service;

import cn.cuit.exam.bean.Classroom;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ClassroomService {

    /**
     * 根据教学楼编号和教室类型查找教室
     */
    List<Classroom> queryClassroomByTeachBuildingIdAndType(int id, int type);

}
