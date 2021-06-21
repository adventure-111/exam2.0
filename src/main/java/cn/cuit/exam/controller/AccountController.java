package cn.cuit.exam.controller;

import cn.cuit.exam.bean.Admin;
import cn.cuit.exam.bean.User;
import cn.cuit.exam.mapper.UtilsMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
@Api(value = "账号接口", description = "账号接口")
public class AccountController {

    @Autowired
    private UtilsMapper utilsMapper;

    @PostMapping(value = "/login")
    @ApiOperation(value = "登录", notes = "请求：role(A,T,S) 分别代表 管理员，教师，学生" +
            "</br> 响应： (not_found, password_incorrect, login_success\\学院名) 分别代表 用户不存在, 密码错误, 登录成功")
    public Map login(HttpServletRequest request, @RequestBody User user) {
        String login = null;
        String name = null;
        if ( user.getRole().equals("A") ) {
            //查询数据库
            Admin db_admin = utilsMapper.queryAdminByUsername(user.getUsername());
            if (db_admin == null) login = "not_found";
            else {
                if (user.getPassword().equals(db_admin.getPassword())) {
                    login = "login_success";
                     name = db_admin.getSchool();
                } else {
                    login = "password_incorrect";
                }
            }
        } else {
            //查询数据库
            User db_user = utilsMapper.queryUserByUsername(user.getUsername());
            if (db_user == null) login = "not_found";
            else {
                if (user.getPassword().equals(db_user.getPassword())) {
                    if ( db_user.getRole().equals("T")) name= utilsMapper.getTnameByTno(user.getUsername());
                    else if ( db_user.getRole().equals("S")) name = utilsMapper.getSnameBySno(user.getUsername());
                    HttpSession session = request.getSession();
                    session.setAttribute("username", user.getUsername());
                    login = "login_success";
                } else {
                    login ="password_incorrect";
                }
            }
        }

        Map<String , String> map = new HashMap<>();
        map.put("login", login);
        map.put("name", name);
        return map;
    }

    @PostMapping("/admin/update_password")
    @ApiOperation(value = "管理员更改密码", notes = "password, username newPassword")
    public int updateAdminPassword(@RequestBody Admin admin) {
        //查询数据库
        Admin db_admin = utilsMapper.queryAdminByUsername(admin.getUsername());
       if ( db_admin == null || !db_admin.getPassword().equals(admin.getPassword())) return 0;
       utilsMapper.updateAdminPassword(admin);
       return 1;
    }

    @PostMapping("/user/update_password")
    @ApiOperation(value = "管理员更改密码", notes = "password, username newPassword")
    public int updateUserPassword(@RequestBody User user) {
        //查询数据库
        User db_user = utilsMapper.queryUserByUsername(user.getUsername());
        if ( db_user == null || !db_user.getPassword().equals(user.getPassword()) ) return 0;
        utilsMapper.updateUserPassword(user);
        return 1;
    }
}
