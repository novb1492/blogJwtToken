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
}
