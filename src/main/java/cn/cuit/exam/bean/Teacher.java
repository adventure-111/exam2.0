package cn.cuit.exam.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Teacher {
    @ExcelColumn( value = "工号", col = 1 )
    private String tno;
    @ExcelColumn( value = "姓名", col = 2 )
    private String tname;
    @ExcelColumn( value = "密码", col = 3 )
    private String password;
    @ExcelColumn( value = "学院", col = 4 )
    private String school;
    private Integer total;
    private Integer current;
    private Integer positivecnt;
    private Integer passivecnt;
}
