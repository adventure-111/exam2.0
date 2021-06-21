package cn.cuit.exam.mapper;

import cn.cuit.exam.bean.Absence;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface AbsenceMapper {

    int insertAbsence(Absence absence);

    int updateAbsence(Absence absence);

}
