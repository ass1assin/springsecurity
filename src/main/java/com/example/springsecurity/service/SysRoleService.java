package com.example.springsecurity.service;

import com.example.springsecurity.domain.SysRole;
import com.example.springsecurity.mapper.SysUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class SysRoleService {

    @Autowired
    SysUserMapper sysUserMapper;

    /**
     * 根据用户ID查询权限
     *
     * @param userId 用户ID
     * @return 权限列表
     */
    public Set<String> selectRolePermissionByUserId(Long userId){
        List<SysRole> perms=sysUserMapper.selectRoleByUserId(userId);
        Set<String> permsSet=new HashSet<>();
        for(SysRole perm : perms){
            if (perm!=null){
                permsSet.addAll(Arrays.asList(perm.getRoleKey().trim().split(",")));
            }
        }
        return permsSet;
    }
}
