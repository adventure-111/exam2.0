package cn.cuit.exam.bean.parameter;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

@Data
public class IsTeacherFreeParam {

    @ApiModelProperty(value = "场次", example = "1")
    private int cnt;

    @ApiModelProperty(value = "教师名", example = "安俊秀")
    private String tname;

}
