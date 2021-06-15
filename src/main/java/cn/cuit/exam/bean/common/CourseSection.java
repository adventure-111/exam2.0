package cn.cuit.exam.bean.common;

import lombok.Data;

import java.util.List;

@Data
public class CourseSection {

    private String classAbbr;

    private String Classname;

    private String tname;

    private String site;

    private int stWeek;

    private int edWeek;

    private int weekday;

    private List<Integer> occupy;

}
