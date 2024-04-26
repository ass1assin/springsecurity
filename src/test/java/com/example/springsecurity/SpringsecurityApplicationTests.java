package com.example.springsecurity;

import com.example.springsecurity.controller.TestController;
import com.example.springsecurity.demos.web.User;
import com.example.springsecurity.domain.LoginBody;
import com.example.springsecurity.domain.LoginUser;
import com.example.springsecurity.domain.SysUser;
import com.example.springsecurity.mapper.SysUserMapper;
import com.example.springsecurity.service.SysRoleService;
import com.example.springsecurity.service.TokenService;
import com.example.springsecurity.service.UserDetailsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

@SpringBootTest
class SpringsecurityApplicationTests {

    @Autowired
    TestController testController;

    @Autowired
    public SysUserMapper sysUserMapper;

    @Autowired
    SysRoleService sysRoleService;

    @Autowired
    UserDetailsServiceImpl userDetailsService;

    @Autowired
    TokenService tokenService;

    @Test
    void contextLoads() {
//        LoginBody loginBody=new LoginBody();
//        loginBody.setUsername("admin");
//        loginBody.setPassword("123456");
//        System.out.println(testController.login(loginBody));
        System.out.println("getinfo"+testController.getInfo());
//        System.out.println(sysUserMapper.selectUserByUserName("admin"));
    }

    @Test
    void s(){
        Set<String> permissions = new HashSet<>();
        permissions.add("admin"); // 添加权限
        SysUser sysUser = new SysUser();
        sysUser.setUserName("admin");
        sysUser.setPassword("908908");
        LoginUser loginUser = new LoginUser(1L,sysUser, permissions);
//        System.out.println(tokenService.createToken(loginUser));
        tokenService.refreshToken(loginUser);
    }

    @Test
    void a(){
        System.out.println(testController.getRouters());
    }

    @Test
    void b(){

        System.out.println(userDetailsService.loadUserByUsername("admin"));
    }

}
