package cn.cuit.exam.service.impl;

import cn.cuit.exam.bean.PageBean;
import cn.cuit.exam.bean.Teacher;
import cn.cuit.exam.bean.vo.TeacherQuery;
import cn.cuit.exam.mapper.TeacherMapper;
import cn.cuit.exam.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    private TeacherMapper teacherMapper;

    @Override
    public PageBean<Teacher> selectTeacher(TeacherQuery teacherQuery) {
        // 查询记录总条数
        int totalCount = teacherMapper.selectTeacherCount(teacherQuery);
        // 查询教师集合
        List<Teacher> teacherList = teacherMapper.selectTeacher(teacherQuery);
        // 创建pageBean
        PageBean<Teacher> pageBean = new PageBean<>(totalCount, teacherList, teacherQuery.getPageSize(), teacherQuery.getPageNum());

        return pageBean;
    }

    @Override
    public int addTeacher(Teacher teacher) {
        // 判断教师是否存在
        if ( teacherMapper.selectTeacherByTno(teacher.getTno()) != null ) return 0;
        // 添加教师
        teacherMapper.addTeacher(teacher);
        // 添加用户
        teacherMapper.addUser(teacher);
        return 1;
    }

    @Override
    @Transactional
    public int updateTeacher(Teacher teacher) {
        teacherMapper.updateTeacher(teacher);
        int count = teacherMapper.updateUser(teacher);
        return count;
    }

    @Override
    public int deleteTeacher(String tno) {
        teacherMapper.deleteTeacher(tno);
        int count = teacherMapper.deleteUser(tno);
        return count;
    }

    @Override
    public void deleteTeacherList(List<String> tnoList) {
        for ( String tno : tnoList ) {
            deleteTeacher(tno);
        }
    }
}
