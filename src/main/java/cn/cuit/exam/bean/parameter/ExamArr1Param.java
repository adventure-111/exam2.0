package cn.cuit.exam.bean.parameter;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 考试安排第二步所需要的的参数
 */
@Data
public class ExamArr1Param {

    @ApiModelProperty(value = "班级列表cid", example = "[1,2,3]")
    private List<Integer> cidList;

    private Calendar calendar;

    @ApiModelProperty(value = "考试日期", example = "2021-6-30")
    private String date;

    @ApiModelProperty(value = "考试时长（分钟）", example = "90")
    private Integer duration;

    @ApiModelProperty(value = "场次")
    private int cnt;

    public void setDate(String date) {
        this.date = date;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date tempDate = null;
        try {
            tempDate = sdf.parse(date);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        this.calendar = Calendar.getInstance();
        calendar.clear();

        assert tempDate != null;    // 断言tempDate非空
        calendar.setTime(tempDate);
    }

}
