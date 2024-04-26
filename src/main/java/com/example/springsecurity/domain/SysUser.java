package com.example.springsecurity.domain;

import java.io.Serializable;
import java.util.List;

public class SysUser implements Serializable {
    private Long userId;
    private String userName;
    private String password;

    /** 角色ID */
    private Long roleId;
    /** 角色对象 */
    private List<SysRole> roles;

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public void setRoles(List<SysRole> roles) {
        this.roles = roles;
    }

    public List<SysRole> getRoles() {
        return roles;
    }

    @Override
    public String toString() {
        return "SysUser{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", roleId=" + roleId +
                ", roles=" + roles +
                '}';
    }
}
