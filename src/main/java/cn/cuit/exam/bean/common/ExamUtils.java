package cn.cuit.exam.bean.common;

import cn.cuit.exam.bean.Exam;
<<<<<<< HEAD
import io.swagger.models.auth.In;
=======
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c

import java.util.*;

public class ExamUtils {

    // 所有考试信息(发布、删除、未发布)
    public static List<Exam> examList = new ArrayList<>();

<<<<<<< HEAD
    //(eno, List<cid>)对照表
    public static Map<Integer, List<Integer>> ClassExamMap = new HashMap<>();

    //(eno, List<Site>)对照表
    public static Map<Integer, List<String>> ClassroomExamMap = new HashMap<>();
=======
    //(cid, List<eno>)对照表
    public static Map<Integer, List<Integer>> ClassExamMap = new HashMap<>();

    //(site, List<eno>)对照表
    public static Map<String, List<Integer>> ClassroomExamMap = new HashMap<>();
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c

}
