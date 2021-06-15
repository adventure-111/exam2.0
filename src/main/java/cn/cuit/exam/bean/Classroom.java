package cn.cuit.exam.bean;

import lombok.Data;

@Data
public class Classroom {

    private String site;

    private int capacity;

    /**
     * 普通教室=1，阶梯教室=2，机房=3
     */
    private int type;

}
