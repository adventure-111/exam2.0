package cn.cuit.exam.bean.common;

<<<<<<< HEAD
<<<<<<< HEAD
import cn.cuit.exam.bean.Klass;
=======
import cn.cuit.exam.bean.Class;
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
=======
import cn.cuit.exam.bean.Class;
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
import cn.cuit.exam.mapper.ClassMapper;
import cn.cuit.exam.mapper.MajorMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Calendar;
import java.util.List;

public class Utils {

    //特定的考试时间，分别为8:30,14:00,18:30
    public static int[] UsableTime = {6, 72, 126};

    //每月的天数
    public static int[] monthDay = {0, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30,31};

    //判断是否为闰年
    public static boolean isLeapYear(int year) {
        return year % 400 == 0 || (year % 4 == 0 && year % 100 != 0);
    }

    @Autowired
<<<<<<< HEAD
<<<<<<< HEAD
    public static MajorMapper majorMapper;

    @Autowired
    public static ClassMapper classMapper;
=======
=======
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
    private static MajorMapper majorMapper;

    @Autowired
    private static ClassMapper classMapper;
<<<<<<< HEAD
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
=======
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c

    public static MajorMapper getMajorMapper() {
        return majorMapper;
    }

    public static ClassMapper getClassMapper() {
        return classMapper;
    }

    /**
     * 获取本学期第一周的第一天
     */
    public static Calendar getInitDate() {
        Calendar c = Calendar.getInstance();
        c.clear();
        c.set(Calendar.YEAR, 2021);
        c.set(Calendar.MONTH, 2);
        c.set(Calendar.DATE, 1);
        c.set(Calendar.HOUR, 0);
        c.set(Calendar.MINUTE, 0);
        c.set(Calendar.SECOND, 0);
        return c;
    }

    /**
     * 根据第几节课判断该课程的起始时间
     */
    public static int getCourseSectionStartTime(int x) {
        switch (x) {
            case 1:
                return 4;
            case 2:
                return 15;
            case 3:
                return 28;
            case 4:
                return 39;
            case 5:
                return 74;
            case 6:
                return 85;
            case 7:
                return 96;
            case 8:
                return 107;
            case 9:
                return 132;
            case 10:
                return 143;
            case 11:
                return 156;
            default:
                return 0;
        }
    }

    /**
     * 根据选定班级，选定日期，考试时长确定当下选定的班级之间是否能腾出一个共同的时间段
     * 当天是指当天的8:00~22:00
     */
<<<<<<< HEAD
<<<<<<< HEAD
    public static boolean isClassTimeScheduleConflict(List<Klass> classes, Calendar date, int duration) {
=======
    public static boolean isClassTimeScheduleConflict(List<Class> classes, Calendar date, int duration) {
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
=======
    public static boolean isClassTimeScheduleConflict(List<Class> classes, Calendar date, int duration) {
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
        Calendar startSem = Utils.getInitDate();  //获取本学期的第一天的日期
        long d = (date.getTimeInMillis() - startSem.getTimeInMillis()) / (1000 * 3600 * 24);//计算既定日期距初始日期的天数
        int d_axis = (int) d;

        duration /= 5;  //以5分钟为一个单位

        //检测是否所有教室都在三个特定的考试时间点有空闲时段
        int[] cnt = new int[3];


        for (int i = 0; i < 3; ++i) cnt[i] = classes.size();
        for (int i = 0; i < 3; ++i) {
<<<<<<< HEAD
<<<<<<< HEAD
            for (Klass t : classes) {
=======
            for (Class t : classes) {
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
=======
            for (Class t : classes) {
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
                if (!t.isFree(Utils.getWeekByDate(date), Utils.getWeekdayByDate(date),
                        Utils.UsableTime[i], UsableTime[i] + duration)) {
                    cnt[i]--;
                }
            }
        }

        return cnt[0] == classes.size() || cnt[1] == classes.size() || cnt[2] == classes.size();
    }

    /**
     * 根据绝对时间获得相对时间
     */
    public static int getTimeAxis(Calendar time) {
        long res = time.getTimeInMillis() % ((long) 24 * 3600 * 1000);
        System.out.println(res);
        res /= 60 * 1000;
        return (int) (res / 5);
    }

    /**
     * 非操作(!)
     */
    public static int getReverse(int x) {
        if (x > 0) return 0;
        else return 1;
    }

    /**
     * 根据相对时间获得绝对时间
     */
    public static Calendar getCalenderByAxis(int axis) {
        Calendar c = Calendar.getInstance();
        c.clear();
        c.set(Calendar.HOUR, axis / 12);
        c.set(Calendar.MINUTE, axis % 12);
        return c;
    }

    /**
     * 根据日期获得相应的周数
     */
    public static int getWeekByDate(Calendar date) {
        return (int) ((date.getTimeInMillis() - Utils.getInitDate().getTimeInMillis()) / (1000 * 3600 * 24 * 7) + 1);
    }

    /**
     * 根据日期知道那天是星期几
     */
    public static int getWeekdayByDate(Calendar date) {
        return (int) (((date.getTimeInMillis() - Utils.getInitDate().getTimeInMillis()) % (1000 * 3600 * 24 * 7)) / (1000 * 3600 * 24 + 1));
    }

}
