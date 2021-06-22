package cn.cuit.exam.bean.parameter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetTeacherInspectorParam {

    @ApiModelProperty(value = "选择（教室分配方案）", example = "1")
    private int selected;

    @ApiModelProperty(value = "学院", example = "软件工程")
    private String school;

    @ApiModelProperty(value = "场次", example = "1")
    private int cnt;

}
