package com.example.springsecurity.service.Impl;


import com.example.springsecurity.domain.SysUser;
import com.example.springsecurity.mapper.SysMenuMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class SysPermissionServiceImpl {
    @Autowired
    private SysMenuMapper menuMapper;

    /**
     * 获取菜单数据权限
     *
     * @param user 用户信息
     * @return 菜单权限信息
     */
    public Set<String> getMenuPermission(SysUser user)
    {
        Set<String> perms = new HashSet<String>();
        perms.addAll(menuMapper.selectMenuPermsByRoleId(user.getRoleId()));
        return perms;
    }
}
