package com.example.blog_jwt_token.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.blog_jwt_token.service.naverLoingService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class controller {

        
    @Autowired
    private naverLoingService naverLoingService;

    @RequestMapping("/auth/navercallback")
    public String naverLogin2(HttpServletRequest request,HttpServletResponse response) {
        System.out.println("naverlogin요청");
        naverLoingService.LoginNaver(naverLoingService.getNaverToken(request.getParameter("code"), request.getParameter("state")),request,response);
        return "gohome";
    }
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
