package com.example.springsecurity.controller;


import com.example.springsecurity.domain.LoginUser;
import com.example.springsecurity.service.TokenService;
import com.example.springsecurity.utils.AjaxResult;
import com.example.springsecurity.utils.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
public class TestController {
    @Autowired
    private TokenService tokenService;

    @RequestMapping("/test")
    public String atest(){
        System.out.println("sssss");
        return "Hello";
    }

    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginUser loginUser){
        AjaxResult ajax=AjaxResult.success();
//        生成令牌
        String token=tokenService.createToken(loginUser);
        ajax.put(Constants.TOKEN,token);
        return ajax;
    }

    @GetMapping("/getInfo")
    public AjaxResult getInfo()
    {
        //获取用户

        //获取角色集合

        //获取权限集合

        AjaxResult ajax = AjaxResult.success();
        //放入ajax

        return ajax;
    }
}
