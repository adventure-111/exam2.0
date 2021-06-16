package cn.cuit.exam.mapper;

import cn.cuit.exam.bean.Klass;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClassMapper {

    Klass queryByCid(int cid);

    Klass queryClassWithCidAndNum(Klass c);

}
