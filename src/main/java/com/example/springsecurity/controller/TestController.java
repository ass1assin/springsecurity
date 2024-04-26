package com.example.springsecurity.controller;


import com.example.springsecurity.domain.LoginBody;
import com.example.springsecurity.domain.LoginUser;
import com.example.springsecurity.domain.SysMenu;
import com.example.springsecurity.domain.SysUser;
import com.example.springsecurity.service.Impl.SysMenuServiceImpl;
import com.example.springsecurity.service.Impl.SysPermissionServiceImpl;
import com.example.springsecurity.service.SysRoleService;
import com.example.springsecurity.service.TestService;
import com.example.springsecurity.service.TokenService;
import com.example.springsecurity.utils.AjaxResult;
import com.example.springsecurity.utils.SecurityUtils;
import com.example.springsecurity.utils.constant.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
public class TestController {
    @Autowired
    private TestService testService;

    @Autowired
    private SysMenuServiceImpl menuService;

    @Autowired
    private SysPermissionServiceImpl sysPermissionService;

//    @Autowired
//    private SysRoleService sysRoleService;

    @RequestMapping("/test")
    public String atest(){
        System.out.println("sssss");
        return "Hello";
    }

    @PostMapping("/login")
    public AjaxResult login(@RequestBody LoginBody loginBody){
        AjaxResult ajax=AjaxResult.success();
//        生成令牌
//        String token=tokenService.createToken(loginUser);
        String token=testService.login(loginBody.getUsername(),loginBody.getPassword());
        ajax.put(Constants.TOKEN,token);
        return ajax;
    }

    /**
     * 获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("getInfo")
    public AjaxResult getInfo()
    {
        //获取用户
        SysUser user = SecurityUtils.getLoginUser().getUser();
        //获取角色集合
//        Set<String> roles=sysRoleService.selectRolePermissionByUserId(user.getUserId());
        //获取权限集合
        Set<String> permissions=sysPermissionService.getMenuPermission(user);
        AjaxResult ajax = AjaxResult.success();
        //放入ajax
        ajax.put("user", user);
//        ajax.put("roles", roles);
        ajax.put("permissions", permissions);
        return ajax;
    }

    /**
     * 获取路由信息
     *
     * @return 路由信息
     */
    @GetMapping("/getRouters")
    public AjaxResult getRouters()
    {
        Long userId = SecurityUtils.getUserId();
        List<SysMenu> menus = menuService.selectMenuTreeByUserId(userId);
        return AjaxResult.success(menuService.buildMenus(menus));
    }
}
