package cn.cuit.exam.controller;


import cn.cuit.exam.bean.Admin;
import cn.cuit.exam.mapper.UtilsMapper;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "管理员登录", description = "管理员登录")
public class AdminController {

    @Autowired
    private UtilsMapper utilsMapper;

    @RequestMapping(value = "/admin_login.html")
    public String index() {
        return "/admin_login";
    }

    @RequestMapping(value = "/login")
    public String login(Admin admin) {
        //查询数据库
        Admin db_admin = utilsMapper.queryAdminByUsername(admin.getUsername());
        if (db_admin == null) return "/admin_login";
        else {
            if (admin.getPassword().equals(db_admin.getPassword())) {
                return "/manage.html";
            } else {
                return "/admin_login.html";
            }
        }
    }

}
