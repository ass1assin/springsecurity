package com.example.springsecurity.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.SecurityConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.configurers.ExpressionUrlAuthorizationConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    public void configure(HttpSecurity http) throws Exception {
//        http.antMatcher("/login").formLogin();

          http
                .authorizeRequests()
                  .antMatchers("api/login","/login","/api/login").permitAll()
                  .antMatchers(HttpMethod.GET, "/", "/*.html", "/**/*.html", "/**/*.css", "/**/*.js", "/profile/**").permitAll()
                  .anyRequest().authenticated()
                  .and()
//                .formLogin()
//                  .loginPage("/login")
//                  .defaultSuccessUrl("/test")
//                  .permitAll()
//                  .and()
                .csrf().disable()
          ;
    }

}
