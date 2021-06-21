package cn.cuit.exam.mapper;

import cn.cuit.exam.bean.Classroom;
<<<<<<< HEAD
import cn.cuit.exam.bean.vo.ClassroomQuery;
import org.apache.ibatis.annotations.Mapper;
=======
import org.mapstruct.Mapper;
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c

import java.util.List;

@Mapper
public interface ClassroomMapper {

    List<Classroom> queryClassroomByTeachBuildingIdAndType(int id, int type);

<<<<<<< HEAD
    // 添加教室
    int insertClassroom(Classroom classroom);

    // 通过教室编号获取
    Classroom getClassroomBySite(String site);

    // 条件查询教室
    List<Classroom> selectClassroom(ClassroomQuery classroomQuery);

    // 条件查询教室总记录条数
    int selectClassroomCount(ClassroomQuery classroomQuery);
=======
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
}
