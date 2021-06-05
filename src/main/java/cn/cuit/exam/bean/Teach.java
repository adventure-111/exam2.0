package cn.cuit.exam.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Teach {

    @ApiModelProperty(hidden = true)
    private String cno;

    @ApiModelProperty(hidden = true)
    private String tno;

    @ApiModelProperty(hidden = true)
    private int cid;

    public Teach(String cno, String tno, int cid) {
        this.cno = cno;
        this.tno = tno;
        this.cid = cid;
    }
}
