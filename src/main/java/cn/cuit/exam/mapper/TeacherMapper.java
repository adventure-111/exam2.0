package cn.cuit.exam.mapper;

import cn.cuit.exam.bean.Teacher;
import cn.cuit.exam.bean.vo.TeacherQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TeacherMapper {
    // 查询教师
    List<Teacher> selectTeacher(TeacherQuery teacherQuery);

    // 根据工号查询
    Teacher selectTeacherByTno(String tno);

    // 查询记录总条数
    Integer selectTeacherCount(TeacherQuery teacherQuery);

    // 添加教师
    int addTeacher(Teacher teacher);

    // 添加用户
    int addUser(Teacher teacher);

    // 更新教师表
    int updateTeacher(Teacher teacher);

    // 更新用户表
    int updateUser(Teacher teacher);

    // 增加历史监考次数+1
    int addTotalByOne(String tno);

    // 增加本次监考次数
    // 减少本次监考次数
    // 增加历史申请调换次数
    // 增加历史被动调换次数

    // 删除教师
    int deleteTeacher(String tno);

    // 删除用户
    int deleteUser(String tno);
}
