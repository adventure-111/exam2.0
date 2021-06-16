package cn.cuit.exam.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class CourseQuery extends StudentQuery{

    @ApiModelProperty(value = "课程号", example = "CS005A")
    private String cno;

    @ApiModelProperty(value = "课程名", example = "操作系统原理")
    private String cname;

    @ApiModelProperty(value = "类型", example = "1", hidden = true)
    private int type;

    @ApiModelProperty(value = "学院名", example = "软件工程")
    private String school;

    @ApiModelProperty(hidden = true)
    int num;

}
