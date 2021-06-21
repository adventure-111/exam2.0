package cn.cuit.exam.bean.parameter;

import lombok.Data;

import java.util.List;

@Data
public class AutoExamArr2Param {

    private List<Integer> klasses;

    private String day;

    private int duration;

}
