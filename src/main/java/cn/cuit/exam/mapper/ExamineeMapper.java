package cn.cuit.exam.mapper;

import cn.cuit.exam.bean.Examinee;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ExamineeMapper {

    int insertExaminee(Examinee examinee);

}
