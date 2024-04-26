package com.example.springsecurity.service.Impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.example.springsecurity.domain.RouterVo;
import com.example.springsecurity.domain.SysMenu;
import com.example.springsecurity.mapper.SysMenuMapper;
import com.example.springsecurity.utils.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class SysMenuServiceImpl {

    @Autowired
    private SysMenuMapper menuMapper;

    /**
     * 根据用户ID查询菜单
     *
     * @param userId 用户名称
     * @return 菜单列表
     */

    public List<SysMenu> selectMenuTreeByUserId(Long userId)
    {
        List<SysMenu> menus = null;
//        if (SecurityUtils.isAdmin(userId))
//        {
            menus = menuMapper.selectMenuTreeAll();
//        }
//        else
//        {
//            menus = menuMapper.selectMenuTreeByUserId(userId);
//        }
//        return getChildPerms(menus, 0);
        return menus;
    }
    /**
     * 构建前端路由所需要的菜单
     *
     * @param menus 菜单列表
     * @return 路由列表
     */
    public List<RouterVo> buildMenus(List<SysMenu> menus)
    {
        List<RouterVo> routers = new LinkedList<RouterVo>();
        for (SysMenu menu : menus)
        {
            RouterVo router = new RouterVo();
            router.setName(menu.getMenuName());
            router.setPath(menu.getPath());
            router.setComponent(menu.getComponent());
            routers.add(router);
        }
        return routers;
    }

    /**
     * 根据角色ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectMenuPermsByRoleId(Long roleId)
    {
        List<String> perms = menuMapper.selectMenuPermsByRoleId(roleId);
        Set<String> permsSet = new HashSet<>();
        for (String perm : perms)
        {
            if (StringUtils.isNotEmpty(perm))
            {
                permsSet.addAll(Arrays.asList(perm.trim().split(",")));
            }
        }
        return permsSet;
    }
}
