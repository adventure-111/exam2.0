package cn.cuit.exam.bean;

<<<<<<< HEAD
import cn.cuit.exam.mapper.CourseMapper;
import cn.cuit.exam.mapper.UtilsMapper;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;

@Data
public class Teach implements Serializable {

//    @ApiModelProperty(hidden = true)
=======
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teach {

>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
    private String cno;

    private String tno;

<<<<<<< HEAD
    @ApiModelProperty(hidden = true)
    private Integer cid;

    @ApiModelProperty(value = "课程名", example = "操作系统原理")
    @ExcelColumn(value = "课程名", col = 1)
    private String course_name;

    @ApiModelProperty(value = "班级名", example = "软工192")
    @ExcelColumn(value = "班级名", col = 2)
    private String class_name;

    @ApiModelProperty(value = "教师姓名", example = "冯波")
    @ExcelColumn(value = "教师姓名", col = 3)
    private String tname;

=======
    private int cid;
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
}
