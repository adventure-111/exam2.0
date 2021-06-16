package cn.cuit.exam.bean;

import cn.cuit.exam.bean.common.Utils;
import cn.cuit.exam.mapper.UtilsMapper;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.models.auth.In;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Data
public class Klass implements Serializable {

    private Integer cid;

    @ApiModelProperty(hidden = true)
    private String mno;

    @ApiModelProperty(hidden = true)
    private Integer semester;

    @ApiModelProperty(hidden = true)
    private Integer cnt;

    private Integer num;


    private String cname;

    @ApiModelProperty(hidden = true)
    private String mshort;

    @ApiModelProperty(hidden = true)
    private int[][] occupy;

    public Klass(){};

    public Klass(String mno, int semester, int cnt) {
        this.mno = mno;
        this.semester = semester;
        this.cnt = cnt;
    }

    public static Klass getClassByAbbr(String abbr) {
        String mno = Utils.getMajorMapper().getMshortByMno(abbr.substring(0, 2));
        int semester = 0, cnt = 0;
        try {
            semester = Integer.parseInt(abbr.substring(2, 4));
            cnt = Integer.parseInt(abbr.substring(4, 5));
        } catch (NumberFormatException e1) {
            System.out.println("班级缩写格式错误！");
            e1.printStackTrace();
        }
        return Utils.getClassMapper().queryClassWithCidAndNum(new Klass(mno, semester, cnt));
    }

    public String Get_AbbrName() {
        return "" + Utils.getMajorMapper().getMshortByMno("mno") +
                Integer.toString(this.semester) +
                Integer.toString(this.cnt);
    }

    public boolean isFree(int week, int weekday, int st, int ed) {
        int axis = (week - 1) * 7 + weekday - 1;
        for (int i = st; i <= ed; ++i) if (occupy[axis][i] != 0) return false;
        return true;
    }

    public Klass(String cname, UtilsMapper utilsMapper) {
        if ( cname.length() == 5 ) {
            this.semester = Integer.parseInt(cname.substring(2,4));
            this.mshort = cname.substring(0, 2);
            this.cnt = Integer.parseInt(cname.substring(4,5));
        } else if ( cname.length() == 6 ) {
            this.semester = Integer.parseInt(cname.substring(3, 5));
            this.mshort = cname.substring(0, 3);
            this.cnt = Integer.parseInt(cname.substring(5, 6));
        } else if ( cname.length() == 7 ) {
            this.semester = Integer.parseInt(cname.substring(4, 6));
            this.mshort = cname.substring(0, 4);
            this.cnt = Integer.parseInt(cname.substring(6, 7));
        }
        this.mno = utilsMapper.getMajorMnoByMshort(this.mshort);
        this.num = utilsMapper.getClassNum(mshort, semester, cnt);
        this.cid = utilsMapper.getClassId(mshort, semester, cnt);
        this.cname = cname;
    }

    public String getCname() {
        if ( cname == null )
            this.cname = mshort + semester + cnt;
        return this.cname;
    }
}
