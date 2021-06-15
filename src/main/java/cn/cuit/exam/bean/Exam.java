package cn.cuit.exam.bean;

import cn.cuit.exam.bean.common.Utils;
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

    private String teacher1;
    private String teacher2;
    private int state;

    private int type;

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
