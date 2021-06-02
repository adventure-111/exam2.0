package cn.cuit.exam.service.impl;

import cn.cuit.exam.bean.PageBean;
import cn.cuit.exam.bean.Student;
import cn.cuit.exam.bean.vo.StudentQuery;
import cn.cuit.exam.mapper.StudentMapper;
import cn.cuit.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

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
        PageBean<Student> pageBean = new PageBean<Student>(totalCount, stuList, studentQuery.pageSize, studentQuery.pageNum);

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
//        String mshort = studentMapper.selectMajorMshort(student.getMname());
        studentMapper.insertStu(student);
        studentMapper.insertUser(student);

        return 1;
    }
}
