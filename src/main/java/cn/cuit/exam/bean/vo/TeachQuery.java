package cn.cuit.exam.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class TeachQuery extends Query{
    @ApiModelProperty(value = "课程号(必传)")
    private String cno;
    @ApiModelProperty(value = "学院名(必穿)")
    private String school;
}
