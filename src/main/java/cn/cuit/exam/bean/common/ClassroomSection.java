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

    public void update(int week, int weekday, int st, int dur) {
        int x = (week - 1) * 7 + weekday - 1;
        occupy[x][Utils.getCourseSectionStartTime(st)]++;
        occupy[x][Utils.getCourseSectionStartTime(st) + dur + 1]--;
    }

    /**
     * 判断从st到ed的所有时间里是否都空闲
     */
    public boolean queryForFree(int week, int weekday, int st, int ed) {
        int x = (week - 1) * 7 + weekday - 1;
        int[] vis = new int[occupy[0].length];
        vis[0] = Utils.getReverse(occupy[x][0]);
        for (int i = 1; i <= ed; ++i) {
            vis[i] = Utils.getReverse(occupy[x][i]) + vis[i - 1];
        }
        return vis[ed] >= ed - st;
    }

}
