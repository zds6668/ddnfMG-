package com.dd.ddfgm.Validate;

import com.dd.ddfgm.exception.VerifyCodeException;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Five on 2020/7/8 21:01
 */
public class ValidateCodeFilter extends AbstractAuthenticationProcessingFilter {
    //登录失败
    public AuthenticationFailureHandler authenticationCaptchaFailureHandler() {
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
                out.write("{\"status\":\"error\",\"msg\":\"captcha\"}");
                out.flush();
                out.close();
            }
        };
    }

    private String servletPath;
    private static final String expect = "dnf";
    public ValidateCodeFilter(String servletPath) {
        super(servletPath);
        this.servletPath = servletPath;
        setAuthenticationFailureHandler(authenticationCaptchaFailureHandler());
    }
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if ("POST".equalsIgnoreCase(req.getMethod()) && servletPath.equals(req.getServletPath())) {
            if (!StringUtils.hasText((req.getParameter("captcha")))) {
                unsuccessfulAuthentication(req, res, new VerifyCodeException("验证码不能为空"));
                return;
            }
            if (!expect.equalsIgnoreCase((req.getParameter("captcha")))) {
                unsuccessfulAuthentication(req, res, new VerifyCodeException("验证码不正确"));
                return;
            }
        }
        chain.doFilter(request, response);
    }
    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException, ServletException {
        return null;
    }
}