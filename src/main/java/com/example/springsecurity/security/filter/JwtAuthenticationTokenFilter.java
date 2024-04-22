package com.example.springsecurity.security.filter;

import com.example.springsecurity.domain.LoginUser;
import com.example.springsecurity.service.TokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * token过滤器 验证token有效性
 * 
 * @author ruoyi
 */
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter
{
    @Autowired
    private TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException
    {
        LoginUser loginUser = tokenService.getLoginUser(request);
        if (loginUser!=null && SecurityContextHolder.getContext().getAuthentication()==null)
        {
//            用户的认证信息
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(loginUser, null, loginUser.getAuthorities());
//            创建认证相关的额外信息,例如用户的IP地址等
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
//            用户认证信息设置到安全上下文
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        chain.doFilter(request, response);
    }
}
