package cn.cuit.exam.bean;

import lombok.Data;

import java.util.Calendar;

@Data
public class Exam {

    private int eno;
    private String cno;
    private int cid;
    private int cnt;
    private String site;

    private Calendar day;
    private Calendar start;
    private Calendar end;

    private String teacher1;
    private String teacher2;
    private int state;

    public int getDuration() {
        long res = (this.end.getTimeInMillis() - this.start.getTimeInMillis()) / (1000 * 60 * 5);
        return (int) res;
    }

}
