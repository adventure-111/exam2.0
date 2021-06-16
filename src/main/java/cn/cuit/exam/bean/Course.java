package cn.cuit.exam.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Course {

    @ApiModelProperty(value = "课程号", example = "CS005A")
    private String cno;

    @ApiModelProperty(value = "课程名", example = "操作系统原理")
    private String cname;

    //课程类型，1为公共课，2位专业课
    @ApiModelProperty(value = "类型", example = "1")
    private int type;

    @ApiModelProperty(hidden = true)
    private int num;

}
