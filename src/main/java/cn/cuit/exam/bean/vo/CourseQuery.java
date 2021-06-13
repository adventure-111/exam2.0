package cn.cuit.exam.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CourseQuery extends Query{

    @ApiModelProperty(value = "课程号", example = "CS005A")
    private String cno;

    @ApiModelProperty(value = "课程名", example = "操作系统原理")
    private String cname;

    @ApiModelProperty(value = "类型", example = "1")
    private int type;

    @ApiModelProperty(hidden = true)
    int num;

}
