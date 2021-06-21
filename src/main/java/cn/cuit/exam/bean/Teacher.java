package cn.cuit.exam.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Teacher {
<<<<<<< HEAD
    @ExcelColumn( value = "工号", col = 1 )
    private String tno;
    @ExcelColumn( value = "姓名", col = 2 )
    private String tname;
    @ExcelColumn( value = "密码", col = 3 )
=======

    private String tno;

    private String tname;

>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
    private String password;

    private String school;

    private Integer total;

    private Integer current;

    private Integer positivecnt;

    private Integer passivecnt;

}
