package cn.cuit.exam.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Slf4j
@RestController
@RequestMapping("/test")
public class test {

    @GetMapping({"/gethhh"})
    public String gethhh() {
        System.out.println("abab");
        return "";
    }

    @GetMapping("/gethhhh")
    public String gethhhh(HttpServletRequest req) {

        return (String) req.getSession().getAttribute("hello");
    }

    @RequestMapping("/session")
    public String springSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.setAttribute("hello", "123123123");
        return String.valueOf(session.getAttribute("hello"));
    }

}
