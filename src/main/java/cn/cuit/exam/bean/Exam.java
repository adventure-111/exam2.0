package cn.cuit.exam.bean;

import cn.cuit.exam.bean.common.Utils;
<<<<<<< HEAD
<<<<<<< HEAD
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Data
public class Exam implements Serializable {
    // 请求 ： 课程号、场次、考试日期、考试时长，班级列表

    private int eno;

    @ApiModelProperty(value = "课程号")
    private String cno;

    @ApiModelProperty(value = "场次")
    private int cnt;
    private String site;

    @ApiModelProperty(value = "考试日期", hidden = true)
    private Calendar day;
    @ApiModelProperty(value = "考试日期", example = "2021-6-30")
    private String d_ay;
    private Calendar start;
    private Calendar end;
    @ApiModelProperty(value = "考试时长（分钟）", example = "90")
    private Integer duration;
=======
=======
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
import lombok.Data;

import java.util.Calendar;

@Data
public class Exam {

    private int eno;
    private String cno;
    private int cnt;
    private String site;

    private Calendar day;
    private Calendar start;
    private Calendar end;
    private int duration;
<<<<<<< HEAD
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
=======
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c

    private String teacher1;
    private String teacher2;
    private int state;

    private int type;

<<<<<<< HEAD
<<<<<<< HEAD
    @ApiModelProperty(value = "班级列表cid", example = "32")
    private List<Integer> cidList;

    public Exam() {}

    public void setD_ay(String d_ay) {
        this.d_ay = d_ay;
        SimpleDateFormat sdf= new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = sdf.parse(d_ay);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        day = Calendar.getInstance();
        day.setTime(date);
    }

=======
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
=======
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
    /**
     * 获取该场次考试的周数
     */
    public int getWeek() {
        Calendar init = Utils.getInitDate();
        int week = (int) (day.getTimeInMillis() - init.getTimeInMillis()) / (1000 * 3600 * 24 * 7);
        return week;
    }

    /**
     * 考试当天是星期几
     */
    public int getWeekDay() {
        Calendar init = Utils.getInitDate();
        int week = this.getWeek();
        int weekdays = (int) (day.getTimeInMillis() - init.getTimeInMillis()) / (1000 * 3600 * 24);
        return weekdays % week + 1;
    }

}
