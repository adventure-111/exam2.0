package cn.cuit.exam.mapper;

import cn.cuit.exam.bean.Classroom;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface ClassroomMapper {

    List<Classroom> queryClassroomByTeachBuildingIdAndType(int id, int type);

}
