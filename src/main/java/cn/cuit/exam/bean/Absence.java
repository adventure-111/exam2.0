package cn.cuit.exam.bean;

import lombok.Data;

@Data
public class Absence extends Exam{

    private String tno;

    private String tname;

    private int eno;

    private String reason;

    private int state;

    private String comment;

}
