package cn.cuit.exam.bean;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@ToString
@Data
public class Student implements Serializable {
    private String sno;

    private String sname;

    private String password;

    private String mname;

    @ApiModelProperty(hidden = true)
    private String mno;

    @ApiModelProperty(hidden = true)
    private Integer cid;

    @ApiModelProperty(hidden = true)
    private String mshort;

    private Integer semester;

    private Integer cnt;
}