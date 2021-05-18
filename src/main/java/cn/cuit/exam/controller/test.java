package cn.cuit.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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
