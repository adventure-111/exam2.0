package cn.cuit.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller("/" +
        "hhh")
public class test {

    @GetMapping({"/gethhh"})
    @ResponseBody
    public String gethhh() {
        System.out.println("abab");
        return "hhh.html";
    }

    @GetMapping("/gethhhh")
    public String gethhhh() {
        return "hhhh.html";
    }

}
