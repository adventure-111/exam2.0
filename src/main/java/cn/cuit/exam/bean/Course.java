package cn.cuit.exam.bean;

import cn.cuit.exam.mapper.UtilsMapper;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Data
public class Course implements Serializable {

<<<<<<< HEAD
<<<<<<< HEAD
    @ApiModelProperty(value = "课程号", example = "CS005A")
    @ExcelColumn(value = "课程号", col = 1)
    private String cno;

    @ApiModelProperty(value = "课程名", example = "操作系统原理")
    @ExcelColumn(value = "课程名", col = 2)
    private String cname;

    //课程类型，1为公共课，2位专业课
    @ApiModelProperty(value = "类型", example = "1")
    @ExcelColumn(value = "类型", col = 3)
    private Integer type;

    @ApiModelProperty(hidden = true)
    private Integer num;
=======
    //课程号
//    @ApiModelProperty(hidden = true)
    private String cno;

//    @ApiModelProperty(hidden = true)
    private String cname;

    //课程类型，1为公共课，2位专业课
=======
    //课程号
//    @ApiModelProperty(hidden = true)
    private String cno;

//    @ApiModelProperty(hidden = true)
    private String cname;

    //课程类型，1为公共课，2位专业课
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
//    @ApiModelProperty(hidden = true)
    private int type;

    @ApiModelProperty(hidden = true)
    private int num;
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c

//    @ApiModelProperty(value = "学院(必传)", example = "软件工程")
//    private String school;
}
