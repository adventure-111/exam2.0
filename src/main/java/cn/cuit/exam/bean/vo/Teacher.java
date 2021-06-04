package cn.cuit.exam.bean.vo;

import io.swagger.annotations.ApiModelProperty;

public class Teacher {

    @ApiModelProperty(hidden = true)
    private String tno;

    @ApiModelProperty(hidden = true)
    private String tname;

    @ApiModelProperty(hidden = true)
    private String school;

    @ApiModelProperty(hidden = true)
    private int total;

    @ApiModelProperty(hidden = true)
    private int current;

    @ApiModelProperty(hidden = true)
    private int positiveCnt;

    @ApiModelProperty(hidden = true)
    private int passiveCnt;
}
