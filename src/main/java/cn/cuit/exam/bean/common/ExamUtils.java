package cn.cuit.exam.bean.common;

import cn.cuit.exam.bean.Exam;
import io.swagger.models.auth.In;

import java.util.*;

public class ExamUtils {

    // 所有考试信息(发布、删除、未发布)
    public static List<Exam> examList = new ArrayList<>();

    //(eno, List<cid>)对照表
    public static Map<Integer, List<Integer>> ClassExamMap = new HashMap<>();

    //(eno, List<Site>)对照表
    public static Map<Integer, List<String>> ClassroomExamMap = new HashMap<>();

}
