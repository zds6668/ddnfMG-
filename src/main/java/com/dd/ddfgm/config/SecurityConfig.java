package com.dd.ddfgm.config;

import com.dd.ddfgm.service.impl.CustomUserDetailsService;
import com.dd.ddfgm.Validate.ValidateCodeFilter;
import com.dd.ddfgm.utils.md5PasswordEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Five on 2020/7/7 1:09
 */
@EnableWebSecurity// 开启WebSecurity模式
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    UserDetailsService customUserService() { //注册UserDetailsService 的bean
        return new CustomUserDetailsService();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new md5PasswordEncoder();
    }

    //登入成功
    @Bean
    public AuthenticationSuccessHandler authenticationSuccessHandler() {
        return new AuthenticationSuccessHandler() {
            /**
             * 处理登入成功的请求
             * @param httpServletRequest
             * @param httpServletResponse
             * @param authentication
             * @throws IOException
             * @throws ServletException
             */
            @Override
            public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter out = httpServletResponse.getWriter();
                out.write("{\"status\":\"success\",\"msg\":\"登录成功\"}");
                out.flush();
                out.close();
            }
        };
    }

    //登录失败
    @Bean
    public AuthenticationFailureHandler authenticationFailureHandler() {
        return new AuthenticationFailureHandler() {
            /**
             * 处理登录失败的请求
             * @param httpServletRequest
             * @param httpServletResponse
             * @param e
             * @throws IOException
             * @throws ServletException
             */
            @Override
            public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
                httpServletResponse.setContentType("application/json;charset=utf-8");
                PrintWriter out = httpServletResponse.getWriter();
                out.write("{\"status\":\"error\",\"msg\":\"登录失败\"}");
                out.flush();
                out.close();
            }
        };
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        //配置静态文件不需要认证
        web.ignoring().antMatchers("/static/**");
        web.ignoring().antMatchers("/fonts/**");
        web.ignoring().antMatchers("/images/**");
        web.ignoring().antMatchers("/js/**");
        web.ignoring().antMatchers("/css/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterBefore(new ValidateCodeFilter("/login"),
                UsernamePasswordAuthenticationFilter.class);
        //在认证用户名之前认证验证码，如果验证码错误，将不执行用户名和密码的认证
        // TODO 定制请求的授权规则
        http.authorizeRequests()
                .antMatchers("/toLogin", "/").permitAll()
                .antMatchers("/account/dd").hasAuthority("VIP")
                .antMatchers("/account/db").hasAuthority("VIP")
                .antMatchers("/activity/delete").hasAuthority("VIP")
                .antMatchers("/activity/add").hasAuthority("VIP")
                .antMatchers("/addEmail").hasAuthority("VIP")
                .anyRequest().authenticated();
        //session管理
        //session失效后跳转
        http.sessionManagement().invalidSessionUrl("/toLogin");
        // 开启自动配置的登录功能
        // login 请求来到登录页
        // login?error 重定向到这里表示登录失败
        /* 添加验证码过滤器 */
        http.formLogin()
                //配置登录form传过来的账号密码
                .usernameParameter("username")
                .passwordParameter("password")
                // 登录页面
                .loginPage("/toLogin")
                // 登陆表单提交请求
                .loginProcessingUrl("/login")
                .successForwardUrl("/account/info")
                .successHandler(authenticationSuccessHandler())
                .failureHandler(authenticationFailureHandler());
        //开启自动配置的注销的功能
        http.logout().permitAll()
                .invalidateHttpSession(true)
                .logoutSuccessUrl("/toLogin");
        http.csrf().disable();//关闭csrf功能:跨站请求伪造,默认只能通过post方式提交logout请求
    }

    //定义认证规则
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        //在内存中定义，也可以在jdbc中去拿....
        //Spring security 5.0中新增了多种加密方式，也改变了密码的格式。
        //要想我们的项目还能够正常登陆，需要修改一下configure中的代码。我们要将前端传过来的密码进行某种方式加密
        auth.userDetailsService(customUserService()).passwordEncoder(passwordEncoder());
    }

}
