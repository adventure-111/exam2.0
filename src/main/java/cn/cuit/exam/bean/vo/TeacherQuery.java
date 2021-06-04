package cn.cuit.exam.bean.vo;

import lombok.Data;

@Data
public class TeacherQuery extends Query{
    private String tno;
    private String tname;
    private String school;
}
