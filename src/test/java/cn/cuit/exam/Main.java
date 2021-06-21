package cn.cuit.exam;

import cn.cuit.exam.bean.common.Utils;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < 10; ++i) list.add(i + 1);
        Collections.shuffle(list);
        System.out.println(list);
    }

    public static int getWeekByDate(Calendar date) {
        return (int) ((date.getTimeInMillis() - Utils.getInitDate().getTimeInMillis()) / (1000 * 3600 * 24 * 7) + 1);
    }

    public static int getWeekdayByDate(Calendar date) {
        return (int) (((date.getTimeInMillis() - Utils.getInitDate().getTimeInMillis()) % (1000 * 3600 * 24 * 7)) / (1000 * 3600 * 24 + 1));
    }

}
