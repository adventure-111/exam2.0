package cn.cuit.exam.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Calendar;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Inspector extends Exam{

    private String tno1;

    private String tno2;

    private String site;

    private String tname1;

    private String tname2;

    public Inspector(String teacher1, String teacher2, int eno, String site) {
        this.tno1 = teacher1;
        this.tno2 = teacher2;
        this.setEno(eno);
        this.site = site;
    }


}
