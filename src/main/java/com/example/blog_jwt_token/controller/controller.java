package com.example.blog_jwt_token.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class controller {

    


    @RequestMapping("/auth/auth")
    public String authAtuh(HttpServletRequest request,HttpServletResponse response) {
        System.out.println("/auth/auth입장");
        System.out.println(request.getHeader("Authorization")+"헤더");
        return "authpage";
    }
    @RequestMapping("/auth2")
    public String Atuh2(HttpServletRequest request,HttpServletResponse response) {
        System.out.println("/auth2입장");
        System.out.println(request.getHeader("Authorization")+"헤더");
        return "authpage.html";
    }
 
    
}
