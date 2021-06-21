package cn.cuit.exam.service.impl;

import cn.cuit.exam.bean.PageBean;
import cn.cuit.exam.bean.Teacher;
import cn.cuit.exam.bean.vo.TeacherQuery;
import cn.cuit.exam.mapper.TeacherMapper;
import cn.cuit.exam.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

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
    public Map addTeacherList(List<Teacher> teacherList, String school) {
        int successCount = 0;
        int totalCount = 0;

        for ( Teacher teacher : teacherList ) {
            totalCount++;
            teacher.setSchool(school);
            successCount += addTeacher(teacher);
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("success", successCount);
        map.put("fail", totalCount-successCount);
        return map;
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

    @Override
    public PriorityQueue<Teacher> getMinHeapBySchool(String school) {
        // 教师小顶堆
        PriorityQueue<Teacher> teacherQueue = new PriorityQueue<>(
                (o1, o2) -> {
                    if (o1.getTotal().equals(o2.getTotal())) {
                        return o2.getPassivecnt() - o1.getPassivecnt();
                    } else {
                        return o2.getTotal() - o1.getTotal();
                    }
                }
        );

        // 获取所有教师
        TeacherQuery tq = new TeacherQuery();
        tq.setSchool(school);
        teacherQueue.addAll(teacherMapper.selectTeacher(tq));
        return teacherQueue;
    }
}
