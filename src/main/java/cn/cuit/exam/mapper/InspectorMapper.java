package cn.cuit.exam.mapper;

import cn.cuit.exam.bean.Inspector;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface InspectorMapper {

    int insertInspector(Inspector inspector);

    List<Object> selectInspectorByEno(int eno);

    int updateInspectorByEnoAndTno(int eno, String tno, String newTno);

}
