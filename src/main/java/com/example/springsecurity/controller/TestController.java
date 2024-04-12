package com.example.springsecurity.controller;


import com.example.springsecurity.utils.AjaxResult;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @RequestMapping("/test")
    public String atest(){
        System.out.println("sssss");
        return "Hello";
    }

    @PostMapping("/login")
    public AjaxResult login(){
//        生成令牌
        String token=;
        return AjaxResult.success();
    }
}
