package cn.cuit.exam.service;

import cn.cuit.exam.bean.Classroom;
<<<<<<< HEAD
<<<<<<< HEAD
import cn.cuit.exam.bean.PageBean;
import cn.cuit.exam.bean.vo.ClassroomQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


=======
=======
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
import org.springframework.stereotype.Service;

import java.util.List;

@Service
<<<<<<< HEAD
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
=======
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
public interface ClassroomService {

    /**
     * 根据教学楼编号和教室类型查找教室
     */
    List<Classroom> queryClassroomByTeachBuildingIdAndType(int id, int type);

<<<<<<< HEAD
<<<<<<< HEAD
    /**
     * 添加教室
     * @param classroom
     * @return
     */
    int addClassroom(Classroom classroom);

    /**
     * 批量添加教室
     * @param list
     * @return
     */
    Map addClassroomList(List<Classroom> list);

    /**
     * 条件分页查询教室
     * @param classroomQuery
     * @return
     */
    PageBean<Classroom> selectClassroom(ClassroomQuery classroomQuery);

    int updateClassroom(Classroom classroom);

    Map deleteClassroomList(List<String> siteList);

    int deleteClassroom(String site);
=======
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
=======
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
}
