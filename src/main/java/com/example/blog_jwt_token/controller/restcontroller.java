package com.example.blog_jwt_token.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class restcontroller {
    
    @RequestMapping("/head")
    public String  Head(HttpServletRequest request,HttpServletResponse response) {
        System.out.println("/head 입장");
        System.out.println(request.getHeader("Authorization"));
        return "head";
    }
    @RequestMapping("/auth/head")
    public String  authHead(HttpServletRequest request,HttpServletResponse response) {
        System.out.println("/auth/head 입장");
        return "/auth/head";
    }
    @RequestMapping("/api/v1/user/test")
    public String  user(HttpServletRequest request,HttpServletResponse response) {
        System.out.println("user 입장");
        return "user";
    }
    @RequestMapping("/api/v1/manage/test")
    public String  manage(HttpServletRequest request,HttpServletResponse response) {
        System.out.println("manage 입장");
        return "manage";
    }
    @RequestMapping("/api/v1/admin/test")
    public String  admin(HttpServletRequest request,HttpServletResponse response) {
        System.out.println("admin 입장");
        return "admin";
    }
}
