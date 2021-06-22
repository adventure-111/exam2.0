package cn.cuit.exam.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class Query {

    @ApiModelProperty(value = "排序字段", example = "sno", required = false)
    private String sort;

    @ApiModelProperty(value = "排序方向", example = "desc", required = false)
    private String direction;

    @ApiModelProperty(value = "页面大小", example = "10", required = true)
    private Integer pageSize = 10;

    @ApiModelProperty(value = "当前页面", example = "1", required = true)
    private Integer pageNum = 0;

    public int getBeginRow() {
        return pageNum*pageSize;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum-1;
    }

}
