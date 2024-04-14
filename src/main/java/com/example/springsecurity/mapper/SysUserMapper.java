package com.example.springsecurity.mapper;

import com.example.springsecurity.domain.LoginUser;
import com.example.springsecurity.domain.SysRole;
import com.example.springsecurity.domain.SysUser;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface SysUserMapper {
    public SysUser selectUserByUserName(String userName);

    /**
     * 查询所有角色
     *
     * @return 角色列表
     */
    public List<SysRole> selectRoleByUserId(Long userId);
}
