package cn.cuit.exam.service.impl;


import cn.cuit.exam.bean.Student;
import cn.cuit.exam.mapper.StudentMapper;
import cn.cuit.exam.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public Student queryStudentBySno(String id) {
        Student student = studentMapper.selectByPrimaryKey(id);
        return student;
    }
}
