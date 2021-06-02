package cn.cuit.exam.bean.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
//@ApiModel(value = "学生条件查询请求参数", description = "_学生条件查询请求参数")
public class StudentQuery extends Query{

    @ApiModelProperty(value = "年级", example = "19", required = false)
    private Integer semester;

    @ApiModelProperty(value = "专业名", example = "软件", required = false)
    private String mname;

    @ApiModelProperty(value = "班级名", example = "软工195", required = false)
    private String cname;

    @ApiModelProperty(hidden = true)
    private String mshort;

    @ApiModelProperty(hidden = true)
    private Integer cnt;

    @ApiModelProperty(value = "学号", example = "", required = false)
    private String sno;

    @ApiModelProperty(value = "姓名", example = "", required = false)
    private String sname;

    @ApiModelProperty(value = "学院", example = "软件工程", required = true)
    private String school;

    /**
     * 班名与届数不符
     * @param semester
     */
    public void setSemester(Integer semester) {
        if ( this.semester != null && this.semester != semester ) {
            this.semester = 0;
        } else {
            this.semester = semester;
        }
    }

    /**
     * 将班级名转化为 mno semester cno
     * @param cname
     */
    public void setCname(String cname) {
        Integer semester_temp;
        if ( cname.length() == 5 ) {
            semester_temp = Integer.parseInt(cname.substring(2,4));
            this.mshort = cname.substring(0,2);
            this.cnt = Integer.parseInt(cname.substring(4,5));
        } else if ( cname.length() == 6 ){
            semester_temp = Integer.parseInt(cname.substring(3,5));
            this.mshort = cname.substring(0,3);
            this.cnt = Integer.parseInt(cname.substring(5,6));
        } else {
            semester_temp = 0; // 班级名称格式不对
        }

        if ( this.semester != null && semester_temp != this.semester) {
            this.semester = 0;// 班级名称中的年级与年级不一致
        } else {
            this.semester = semester_temp;
        }
        this.cname = cname;
    }
}
