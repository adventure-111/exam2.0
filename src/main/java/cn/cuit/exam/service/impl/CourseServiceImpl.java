package cn.cuit.exam.service.impl;

import cn.cuit.exam.bean.Course;
import cn.cuit.exam.bean.Klass;
import cn.cuit.exam.bean.PageBean;
import cn.cuit.exam.bean.Teach;
import cn.cuit.exam.bean.vo.CourseQuery;
import cn.cuit.exam.bean.vo.TeachQuery;
import cn.cuit.exam.mapper.CourseMapper;
import cn.cuit.exam.mapper.UtilsMapper;
import cn.cuit.exam.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    private CourseMapper courseMapper;
    @Autowired
    private UtilsMapper utilsMapper;

    @Override
    @Transactional
    public int addCourse(Course course) {
        // 判断该课程是否存在
        if (courseMapper.getCourseByCno(course.getCno()) != null) return 0;

        // 添加课程
        int count = courseMapper.insertCourse(course);
        return count;
    }

    @Override
    @Transactional
    public int addTeach(Teach teach) {
        // 判断该授课关系是否存在
        if (courseMapper.getTeach(teach) != null) return 0;

        Integer cid = new Klass(teach.getClass_name(), utilsMapper).getCid();
        String cno = utilsMapper.getCnoByCoursename(teach.getCourse_name());
        String tno = utilsMapper.getTnoByTname(teach.getTname());

        if (cid == null || cno == null || tno == null) return 0;

        teach.setCid(cid);
        teach.setCno(cno);
        teach.setTno(tno);

        // 添加授课关系
        int flag = courseMapper.insertTeach(teach);

        return flag;
    }

    @Override
    @Transactional
    public Map addTeachList(List<Teach> teachList) {
        int totalCount = 0;
        int successCount = 0;
        for (Teach teach : teachList) {
            totalCount++;
            successCount = addTeach(teach);
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("success", successCount);
        map.put("fail", totalCount - successCount);

        return map;
    }

    @Override
    @Transactional
    public Map addCourseList(List<Course> list) {
        int totalCount = 0;
        int successCount = 0;
        for (Course course : list) {
            totalCount++;
            successCount = addCourse(course);
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("success", successCount);
        map.put("fail", totalCount - successCount);

        return map;
    }

    @Override
    @Transactional
    public int deleteCourse(String school, String cno) {
        // 判断该课程其他学院是否存在教学班
        if (courseMapper.hasClassInOtherSchool(school, cno).size() > 0) {
            courseMapper.deleteTeachBySchoolCourse(school, cno);
            return -1;
        }

        courseMapper.deleteTeachBySchoolCourse(school, cno);
        // 删除课程
        int count = courseMapper.deleteCourse(cno);
        return count;
    }

    @Override
    @Transactional
    public Map deleteCourseList(String school, List<String> list) {
        int can_notCount = 0;
        int successCount = 0;
        int totalCount = 0;
        for (String cno : list) {
            totalCount++;
            int count = deleteCourse(school, cno);
            if (count < 0) can_notCount++;
            else successCount += count;
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("success", successCount);
        map.put("fail", totalCount - successCount - can_notCount);
        map.put("can_not", can_notCount);

        return map;
    }

    @Override
    @Transactional
    public int updateCourse(Course course) {
        // 判断该课程是否存在
        if (courseMapper.getCourseByCno(course.getCno()) == null) return 0;

        // 修改课程信息
        int count = courseMapper.updateCourse(course);
        return count;
    }

    @Override
    @Transactional
    public PageBean<Course> selectCourse(CourseQuery courseQuery) {
        // 查询结果为全校的课程集合 但选课人数为该学院选课班级总人数！
        // 查询总记录条数
        int totalCount = courseMapper.selectAllSchoolCourseCount(courseQuery);
        // 查询课程集合
        List<Course> courseList = courseMapper.selectCourse(courseQuery);
        List<Course> courseListAll = courseMapper.selectAllSchoolCourse(courseQuery);
        for (Course course : courseListAll) {
            for (Course course1 : courseList) {
                if (course.getCno().equals(course1.getCno())) {
                    course.setNum(course1.getNum());
                }
            }
        }

        // 创建pageBean
        PageBean<Course> pageBean = new PageBean<Course>(totalCount, courseListAll, courseQuery.getPageSize(), courseQuery.getPageNum());

        return pageBean;
    }

    @Override
    @Transactional
    public PageBean<Teach> selectTeach(TeachQuery teachQuery) {
        // 查询总记录条数
        int totalCount = courseMapper.selectTeachCount(teachQuery);
        // 查询课程集合
        List<Teach> list = courseMapper.selectTeach(teachQuery);
        // 创建pageBean
        PageBean<Teach> pageBean = new PageBean<Teach>(totalCount, list, teachQuery.getPageSize(), teachQuery.getPageNum());

        return pageBean;
    }

    @Override
    public int deleteTeach(Teach teach) {
        // 将班级名转化为班级id，将教师名转换为教师工号tno
        Integer cid = new Klass(teach.getClass_name(), utilsMapper).getCid();
        String tno = utilsMapper.getTnoByTname(teach.getTname());
        teach.setCid(cid);
        teach.setTno(tno);
        // 删除
        return courseMapper.deleteTeach(teach);
    }

    @Override
    public Map deleteTeachList(List<Teach> teachList) {
        int totalCount = 0;
        int successCount = 0;
        for ( Teach teach : teachList ) {
            totalCount++;
            successCount += deleteTeach(teach);
        }
        Map<String , Integer> map = new HashMap<>();
        map.put("success", successCount);
        map.put("fail", totalCount - successCount);

        return map;
    }

    @Override
    public int deleteTeachAll(String school, String cno) {
        return courseMapper.deleteTeachBySchoolCourse(school, cno);
    }

    @Override
    public Map deleteCourseAll(String school) {
        int can_notCount = 0;
        int successCount = 0;
        int totalCount = 0;
        for (String cno : courseMapper.getAllCno()) {
            totalCount++;
            int count = deleteCourse(school, cno);
            if (count < 0) can_notCount++;
            else successCount += count;
        }
        Map<String, Integer> map = new HashMap<>();
        map.put("success", successCount);
        map.put("fail", totalCount - successCount - can_notCount);
        map.put("can_not", can_notCount);

        return map;
    }

}
