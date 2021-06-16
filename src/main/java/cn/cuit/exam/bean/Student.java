package cn.cuit.exam.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Data
public class Student implements Serializable {

    @ExcelColumn(value = "学号", col = 1)
    private String sno;

    @ExcelColumn(value = "姓名", col = 2)
    private String sname;

    @ExcelColumn(value = "密码", col = 3)
    private String password;

    @ExcelColumn(value = "专业", col = 4)
    private String mname;

    @ApiModelProperty(hidden = true)
    private String mno;

    @ApiModelProperty(hidden = true)
    private Integer cid;

    @ApiModelProperty(hidden = true)
    private String mshort;

    @ExcelColumn(value = "届数", col = 5)
    private Integer semester;

    @ExcelColumn(value = "班级号", col = 6)
    private Integer cnt;
}