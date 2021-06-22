package cn.cuit.exam.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/test")
public class test {

    @GetMapping({"/gethhh"})
    @ResponseBody
    public String gethhh(HttpServletRequest req) {
        System.out.println("abab");
        HttpSession session = req.getSession();
        session.setAttribute("hhh", "hhh");
        return "hhh.html";
    }

    @GetMapping("/gethhhh")
    @ResponseBody
    public String gethhhh(HttpServletRequest req) {
        return (String) req.getSession().getAttribute("hhh");
    }

}
