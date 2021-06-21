package cn.cuit.exam.bean.parameter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class ManualExamArr2Param {

    @ApiModelProperty(value = "考试日期", example = "2021-6-13")
    private String day;

    @ApiModelProperty(value = "地点", example = "H1101")
    List<String> sites;

    @ApiModelProperty(value = "场次", example = "2021-6-13")
    private int cnt;

}
