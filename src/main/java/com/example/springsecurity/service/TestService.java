package com.example.springsecurity.service;

import com.example.springsecurity.domain.LoginUser;
//import com.example.springsecurity.security.context.AuthenticationContextHolder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class TestService {

@Resource
private AuthenticationManager authenticationManager;
@Autowired
TokenService tokenService;

public String login(String username,String password){
    // 用户验证
    Authentication authentication = null;
    try{
//        创建一个用户名密码认证令牌
        UsernamePasswordAuthenticationToken authenticationToken=new UsernamePasswordAuthenticationToken(username,password);

        // 该方法会去调用UserDetailsServiceImpl.loadUserByUsername(内含用户名密码是否正确的判断)
//        调用 authenticate 方法会触发认证流程，包括调用 UserDetailsService 的 loadUserByUsername 方法来获取用户详细信息
        authentication = authenticationManager.authenticate(authenticationToken);
    }
    catch (Exception e){
        System.out.println("认证失败：" + e.getMessage());
    }
    finally
    {
        System.out.println("执行了的认证流程");
    }
    LoginUser loginUser = (LoginUser) authentication.getPrincipal();
    return tokenService.createToken(loginUser);
}
}
