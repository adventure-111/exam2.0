package cn.cuit.exam.bean;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class Patrol implements Serializable {

    @ApiModelProperty(value = "巡考1")
    private String patrol1;

    @ApiModelProperty(value = "巡考2")
    private String patrol2;

    @ApiModelProperty(value = "所有监考教师")
    private String teachers;

    private int eno;

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

    @ApiModelProperty(value = "所有考试地点")
    private String sites;

    @ApiModelProperty(value = "场次")
    private String cnt;
}
