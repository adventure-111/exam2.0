package cn.cuit.exam.mapper;

import cn.cuit.exam.bean.Classroom;
import cn.cuit.exam.bean.vo.ClassroomQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ClassroomMapper {

    List<Classroom> queryClassroomByTeachBuildingIdAndType(int id, int type);

    // 添加教室
    int insertClassroom(Classroom classroom);

    // 通过教室编号获取
    Classroom getClassroomBySite(String site);

    // 条件查询教室
    List<Classroom> selectClassroom(ClassroomQuery classroomQuery);

    // 条件查询教室总记录条数
    int selectClassroomCount(ClassroomQuery classroomQuery);

    List<Map> getSiteByTeachBuildingAndLayers(String no);
}
