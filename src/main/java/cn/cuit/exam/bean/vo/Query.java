package cn.cuit.exam.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Query {

    @ApiModelProperty(value = "排序字段", example = "sno", required = false)
    public String sort;

    @ApiModelProperty(value = "排序方向", example = "19", required = false)
    public String direction;

    @ApiModelProperty(value = "页面大小", example = "10", required = true)
    public Integer pageSize = 10;

    @ApiModelProperty(value = "当前页面", example = "1", required = true)
    public Integer pageNum = 1;

}
