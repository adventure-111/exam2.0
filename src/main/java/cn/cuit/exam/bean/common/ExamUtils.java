package cn.cuit.exam.bean.common;

import cn.cuit.exam.bean.Exam;

import java.util.*;

public class ExamUtils {

    // 所有考试信息(发布、删除、未发布)
    public static List<Exam> examList = new ArrayList<>();

    //(cid, List<eno>)对照表
    public static Map<Integer, List<Integer>> ClassExamMap = new HashMap<>();

    //(site, List<eno>)对照表
    public static Map<String, List<Integer>> ClassroomExamMap = new HashMap<>();

}
