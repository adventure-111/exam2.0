package cn.cuit.exam.bean.common;

public class ClassroomSection {

    /**
     * 一学期20周，一周7天
     * 一天从8:00到22:00按5分钟为一个单位分成168份
     */
    private final int[][] occupy;

    public ClassroomSection(int[][] occupy) {
        this.occupy = occupy;
    }

    public void update(int week, int weekday, int st) {
        int x = (week - 1) * 7 + weekday - 1;
        occupy[x][Utils.getCourseSectionStartTime(st)]++;
        occupy[x][Utils.getCourseSectionStartTime(st) + 9 + 1]--;
    }

    public void refresh(int week, int weekday) {
        int x = (week - 1) * 7 + weekday - 1;
        for (int i = 1; i < 170; ++i) occupy[x][i] += occupy[x][i - 1];
    }

    public void refreshAll() {
        for (int x = 0; x < 140; ++x) {
            for (int i = 1; i < 170; ++i) occupy[x][i] += occupy[x][i - 1];
        }
    }

}
