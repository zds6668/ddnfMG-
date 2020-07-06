package com.dd.ddfgm.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Five on 2020/7/7 1:09
 */
@EnableWebSecurity// 开启WebSecurity模式
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // TODO 定制请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("vip1")
                .antMatchers("/level2/**").hasRole("vip2")
                .antMatchers("/level3/**").hasRole("vip3");
        // 开启自动配置的登录功能
        // login 请求来到登录页
        // login?error 重定向到这里表示登录失败
        http.formLogin()
                //配置登录form传过来的账号密码
                .usernameParameter("username")
                .passwordParameter("password")
                // 登录页面
                .loginPage("/toLogin")
                // 登陆表单提交请求
                .loginProcessingUrl("/login")
                .successForwardUrl("/account/info");
        //开启自动配置的注销的功能
        http.logout()
        .logoutSuccessUrl("/toLogin");
        http.csrf().disable();//关闭csrf功能:跨站请求伪造,默认只能通过post方式提交logout请求
    }

    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //在内存中定义，也可以在jdbc中去拿....
        //Spring security 5.0中新增了多种加密方式，也改变了密码的格式。
        //要想我们的项目还能够正常登陆，需要修改一下configure中的代码。我们要将前端传过来的密码进行某种方式加密
        //spring security 官方推荐的是使用bcrypt加密方式。
        auth.inMemoryAuthentication().passwordEncoder(new BCryptPasswordEncoder())
                .withUser("111111").password(new BCryptPasswordEncoder().encode("123456")).roles("root")
                .and()
                .withUser("vip").password(new BCryptPasswordEncoder().encode("123456")).roles("vip")
                .and()
                .withUser("guest").password(new BCryptPasswordEncoder().encode("123456")).roles("guest");
    }

}