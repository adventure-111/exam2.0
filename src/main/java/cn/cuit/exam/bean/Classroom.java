package cn.cuit.exam.bean;

<<<<<<< HEAD
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
=======
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c
import lombok.Data;

@Data
public class Classroom {

<<<<<<< HEAD
    @ApiModelProperty(value = "教室编号", example = "H1101")
    @ExcelColumn(value = "编号", col = 1)
    private String site;

    @ApiModelProperty(value = "教室容量", example = "150")
    @ExcelColumn(value = "容量", col = 2)

    private Integer capacity;
=======
    private String site;

    private int capacity;
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c

    /**
     * 普通教室=1，阶梯教室=2，机房=3
     */
<<<<<<< HEAD
    @ApiModelProperty( value = "教室类型", example = "1", notes = "普通教室=1，阶梯教室=2，机房=3")
    @ExcelColumn(value = "类型", col = 3)
    private Integer type;
=======
    private int type;
>>>>>>> 187dfa79b7624ad3b32402b2d51666eb61aa014c

}
