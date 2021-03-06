package cn.cuit.exam.service;

import cn.cuit.exam.bean.Classroom;
import cn.cuit.exam.bean.PageBean;
import cn.cuit.exam.bean.vo.ClassroomQuery;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;


public interface ClassroomService {

    /**
     * 根据教学楼编号和教室类型查找教室
     */
    List<Classroom> queryClassroomByTeachBuildingIdAndType(int id, int type);

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
}
