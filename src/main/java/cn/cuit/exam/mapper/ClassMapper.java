package cn.cuit.exam.mapper;

import cn.cuit.exam.bean.Class;
import org.mapstruct.Mapper;

@Mapper
public interface ClassMapper {

    Class queryByCid(int cid);

}
