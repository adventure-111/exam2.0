package cn.cuit.exam.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TeachQuery extends Query{
    private String cno;
    private String tno;
    private int cid;

    public TeachQuery(String cno, String tno, int cid) {
        this.cno = cno;
        this.tno = tno;
        this.cid = cid;
    }
}
