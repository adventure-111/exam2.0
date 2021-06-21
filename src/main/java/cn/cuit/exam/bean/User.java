package cn.cuit.exam.bean;

import io.swagger.annotations.ApiOperation;
import lombok.Data;
import org.springframework.web.bind.annotation.DeleteMapping;

@Data
public class User {

    private  String username;

    private  String password;

    private  String role;

    private String newPassword;
}
