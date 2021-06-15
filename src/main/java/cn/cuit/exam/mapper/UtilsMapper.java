package cn.cuit.exam.mapper;

import cn.cuit.exam.bean.Admin;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UtilsMapper {
    // 查询专业列表
    List<String> selectMajorList(String school);

    // 查询年级列表
    List<Integer> selectSemesterList();

    // 查询班级人数
    Integer selectClassNum(@Param("mshort") String mshort, @Param("semester")Integer semester, @Param("cnt")Integer cnt);

    // 根据用户名查找管理员
    Admin queryAdminByUsername(String username);

}
