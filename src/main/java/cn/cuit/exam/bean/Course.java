package cn.cuit.exam.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Course {

    //课程号
//    @ApiModelProperty(hidden = true)
    private String cno;

//    @ApiModelProperty(hidden = true)
    private String cname;

    //课程类型，1为公共课，2位专业课
//    @ApiModelProperty(hidden = true)
    private int type;

    @ApiModelProperty(hidden = true)
    private int num;

}
