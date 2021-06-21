package cn.cuit.exam.mapper;

import cn.cuit.exam.bean.Admin;
import cn.cuit.exam.bean.User;
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
    Integer getClassNum(@Param("mshort") String mshort, @Param("semester")Integer semester, @Param("cnt")Integer cnt);

    // 查询班级人数通过cid
    Integer getClassNumByCid(Integer cid);

    // 通过course_name查询cno
    String getCnoByCoursename(String cname);

    // 通过tname查询tno
    String getTnoByTname(String tname);

    // 查询班级cid
    Integer getClassId(@Param("mshort") String mshort, @Param("semester")Integer semester, @Param("cnt")Integer cnt);

    // 通过专业名查询专业号
    String getMajorMnoByMname(String mname);

    // 通过专业简称获取专业号
    String getMajorMnoByMshort(String mshort);

    // 根据用户名查找管理员
    Admin queryAdminByUsername(String username);

    // 根据用户名查找用户
    User queryUserByUsername(String username);

    // 更改管理员密码
    void updateAdminPassword(Admin admin);

    // 更改用户密码
    void updateUserPassword(User user);

    String getTnameByTno(String username);

    String getSnameBySno(String username);

}
