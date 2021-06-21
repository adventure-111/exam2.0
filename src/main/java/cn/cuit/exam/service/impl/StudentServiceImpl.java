package cn.cuit.exam.service.impl;

import cn.cuit.exam.bean.PageBean;
import cn.cuit.exam.bean.Student;
import cn.cuit.exam.bean.vo.StudentQuery;
import cn.cuit.exam.mapper.StudentMapper;
import cn.cuit.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    @Transactional
    public PageBean<Student> queryStudent(StudentQuery studentQuery) {
        // 查询总记录条数
        int totalCount = studentMapper.queryStuCount(studentQuery);
        // 查询学生集合
        List<Student> stuList = studentMapper.queryStu(studentQuery);
        // 创建pageBean
        PageBean<Student> pageBean = new PageBean<Student>(totalCount, stuList, studentQuery.getPageSize(), studentQuery.getPageNum());

        return pageBean;
    }

    @Override
    @Transactional
    public int addStudent(Student student) {
        // 判断该学生是否存在
        if ( studentMapper.selectBySno(student.getSno()) == 1 ) return 0;
        // 判断该班级是否存在
        student.setMno(studentMapper.selectMajorMno(student.getMname()));
        Integer cid = studentMapper.selectClassId(student);
        if ( cid != null) {
            // 存在则人数+1
            studentMapper.addStuNum(cid);
        } else {
            // 不存在则创建班级
            studentMapper.insertClass(student);
            cid = studentMapper.selectClassId(student);
        }
        student.setCid(cid);
        studentMapper.insertStu(student);
        studentMapper.insertUser(student);

        return 1;
    }

    @Override
    public Map addStudentList(List<Student> studentList) {
        int successCount = 0;
        int totalCount = 0;

        for ( Student student : studentList ) {
            totalCount++;
            successCount += addStudent(student);
        }

        Map<String, Integer> map = new HashMap<>();
        map.put("success", successCount);
        map.put("fail", totalCount - successCount);

        return map;
    }


    @Override
    @Transactional
    public int deleteStudent(String sno) {
        // 减少班级学生人数
        studentMapper.reduceStuNum(sno);
        // 删除学生
        studentMapper.deleteStu(sno);
        // 删除用户
        int count = studentMapper.deleteUser(sno);
        // 删除考生
        studentMapper.deleteExaminee(sno);

        return count;
    }

    @Override
    public void deleteStudentList(List<String> snoList) {
        for ( String sno : snoList ) {
            deleteStudent(sno);
        }
    }

    @Override
    @Transactional
    public void updateStudent(Student student) {
        if ( studentMapper.selectBySno(student.getSno()) == 0 ) return;
        Integer cid_old = studentMapper.selectClassIdBySno(student.getSno());
        student.setMno(studentMapper.selectMajorMno(student.getMname()));
        Integer cid_new = studentMapper.selectClassId(student);

        if ( cid_new == null || !cid_old.equals(cid_new) ) {    // 若班级改变
            studentMapper.reduceStuNum(student.getSno());       // 原班级人数减一
            if ( cid_new != null ) {            // 班级存在 现班级人数加一
                studentMapper.addStuNum(cid_new);
            } else {
                studentMapper.insertClass(student);   // 班级不存在 则创建班级
                cid_new = studentMapper.selectClassId(student);
            }
        }
        student.setCid(cid_new);
        studentMapper.updateStu(student);   // 更新学生表
        studentMapper.updateUser(student);  // 更新用户表
    }


}
