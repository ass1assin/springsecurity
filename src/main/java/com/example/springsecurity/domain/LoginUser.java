package com.example.springsecurity.domain;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.alibaba.fastjson2.annotation.JSONField;
import java.util.Collection;
import java.util.Set;

public class LoginUser implements UserDetails{
    /**
     * 用户ID
     */
    private Long userId;

    /**
     * 用户唯一标识
     */
    private String token;

    /**
     * 用户信息
     */
    private SysUser user;

    /**
     * 权限列表
     */
    private Set<String> permissions;

    public LoginUser(Long userId,  SysUser user , Set<String> permissions)
    {
        this.userId = userId;
        this.user = user;
        this.permissions = permissions;
    }


    public String getToken() {
        return token;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public SysUser getUser() {
        return user;
    }

    public void setUser(SysUser user) {
        this.user = user;
    }

    public Set<String> getPermissions() {
        return permissions;
    }

    public void setPermissions(Set<String> permissions) {
        this.permissions = permissions;
    }

    @Override
    public String getPassword()
    {
        return user.getPassword();
    }

    @Override
    public String getUsername()
    {
        return user.getUserName();
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }


    /**
     * 指定用户是否解锁,锁定的用户无法进行身份验证
     *
     * @return
     */
//    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonLocked()
    {
        return true;
    }

    /**
     * 账户是否未过期,过期无法验证
     */
//    @JSONField(serialize = false)
    @Override
    public boolean isAccountNonExpired()
    {
        return true;
    }



//    @JSONField(serialize = false)
    @Override
    public boolean isCredentialsNonExpired()
    {
        return true;
    }

//    @JSONField(serialize = false)
    @Override
    public boolean isEnabled()
    {
        return true;
    }

    @Override
    public String toString() {
        return "LoginUser{" +
                "userId=" + userId +
                ", token='" + token + '\'' +
                ", user=" + user +
                ", permissions=" + permissions +
                '}';
    }
}
