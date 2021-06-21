package cn.cuit.exam.bean.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeachQuery extends Query{
    @ApiModelProperty(value = "课程号(必传)")
    private String cno;
<<<<<<< HEAD
    @ApiModelProperty(value = "学院名(必穿)")
    private String school;
=======
    private String tno;
    private int cid;
<<<<<<< HEAD
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
=======
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
}
