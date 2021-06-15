package cn.cuit.exam;

import cn.cuit.exam.bean.common.Utils;

import java.util.Calendar;

public class Main {

    public static void main(String[] args) {
        Calendar today = Calendar.getInstance();
        System.out.println(getWeekdayByDate(today));
    }

    public static int getWeekByDate(Calendar date) {
        return (int) ((date.getTimeInMillis() - Utils.getInitDate().getTimeInMillis()) / (1000 * 3600 * 24 * 7) + 1);
    }

    public static int getWeekdayByDate(Calendar date) {
        return (int) (((date.getTimeInMillis() - Utils.getInitDate().getTimeInMillis()) % (1000 * 3600 * 24 * 7)) / (1000 * 3600 * 24 + 1));
    }

}
