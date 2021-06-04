package cn.cuit.exam.bean;

import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class Teacher {
    private String tno;
    private String tname;
    private String password;
    private String school;
    private Integer total;
    private Integer current;
    private Integer positivecnt;
    private Integer passivecnt;
}
