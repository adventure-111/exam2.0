package cn.cuit.exam.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class test {

    @GetMapping({"/gethhh"})
    public String gethhh() {
        System.out.println("abab");
        return "hhh.html";
    }

    @RequestMapping("/gethhhh")
    public String gethhhh() {
        return "hhhh.html";
    }

}
