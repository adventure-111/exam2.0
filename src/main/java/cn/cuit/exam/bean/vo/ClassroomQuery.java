package cn.cuit.exam.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;

@Data
public class ClassroomQuery extends Query{
    @ApiModelProperty(value = "教学楼", example = "1")
    private String teachBuilding;

    @ApiModelProperty(value = "教室类型", example = "2")
    private Integer type;

//    public void setType(String type1) {
//        this.type = Integer.valueOf(type1);
//    }
}
