package cn.cuit.exam.bean.common;

import cn.cuit.exam.bean.Class;
import cn.cuit.exam.bean.Course;

import java.util.Calendar;
import java.util.List;

public class Utils {

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
    public static boolean isClassTimeScheduleConflict(List<Class> classes, Calendar date, int duration) {
        Calendar startSem = Utils.getInitDate();  //获取本学期的第一天的日期
        long d = (date.getTimeInMillis() - startSem.getTimeInMillis()) / (1000 * 3600 * 24);//计算既定日期距初始日期的天数
        int d_axis = (int) d;

        int[] vis = new int[170];
        for (Class t : classes) {
            Course[][] course = t.getCourses();
            for (int i = 0; i < 11; ++i) {
                if (course[d_axis][i] != null) {
                    vis[Utils.getCourseSectionStartTime(i + 1)]++;
                    vis[Utils.getCourseSectionStartTime(i + 9 + 1 + 1)]--;
                }
            }
        }

        duration /= 5;
        for (int i = 1; i < 170; ++i) {
            vis[i] += vis[i - 1];
            if (vis[i] >= duration) return true;
        }
        return false;
    }
}
