package cn.cuit.exam.bean;

import cn.cuit.exam.bean.common.Utils;
import cn.cuit.exam.service.impl.MajorServiceImpl;
import lombok.Data;

@Data
public class Class {

    private int cid;

    private String mno;

    private int semester;

    private int cnt;

    private int num;

    private int[][] occupy;

    public Class(String mno, int semester, int cnt) {
        this.mno = mno;
        this.semester = semester;
        this.cnt = cnt;
    }

    public static Class getClassByAbbr(String abbr) {
        String mno = Utils.getMajorMapper().getMshortByMno(abbr.substring(0, 2));
        int semester = 0, cnt = 0;
        try {
            semester = Integer.parseInt(abbr.substring(2, 4));
            cnt = Integer.parseInt(abbr.substring(4, 5));
        } catch (NumberFormatException e1) {
            System.out.println("班级缩写格式错误！");
            e1.printStackTrace();
        }
        return Utils.getClassMapper().queryClassWithCidAndNum(new Class(mno, semester, cnt));
    }

    public String getAbbrName() {
        return "" + Utils.getMajorMapper().getMshortByMno("mno") +
                Integer.toString(this.semester) +
                Integer.toString(this.cnt);
    }

    public boolean isFree(int week, int weekday, int st, int ed) {
        int axis = (week - 1) * 7 + weekday - 1;
        for (int i = st; i <= ed; ++i) if (occupy[axis][i] != 0) return false;
        return true;
    }
}
