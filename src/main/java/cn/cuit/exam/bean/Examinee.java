package cn.cuit.exam.bean;

import com.alibaba.fastjson.annotation.JSONField;
import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.sql.Time;
import java.util.Calendar;
import java.util.Date;

@Data
public class Examinee implements Serializable {

    private String sno;
    private int eno;
    private String seat;

    @ApiModelProperty(value = "课程号")
    private String cno;

    @ApiModelProperty(value = "课程名")
    private String cname;

    @JsonFormat(pattern="yyyy-MM-dd",timezone = "GMT")
    @ApiModelProperty(value = "考试日期")
    private Date day;

    @JsonFormat(pattern="HH:mm",timezone = "GMT")
    @ApiModelProperty(value = "开始时间")
    private Date start;

    @JsonFormat(pattern="HH:mm", timezone="GMT")
    @ApiModelProperty(value = "结束时间")
    private Date end;

    @ApiModelProperty(value = "考试地点")
    private String site;

}
