package cn.cuit.exam.bean.parameter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class GetSelectOfUserParam {

    @ApiModelProperty(value = "场次", example = "1")
    private int cnt;

    @ApiModelProperty(value = "用户的选择", example = "1")
    private int selected;

}
