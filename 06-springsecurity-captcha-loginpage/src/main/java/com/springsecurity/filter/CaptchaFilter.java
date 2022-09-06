package com.springsecurity.filter;

import cn.hutool.core.util.StrUtil;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class CaptchaFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        //页面传过来的验证码
        String captchaCode = request.getParameter("captcha");
        //判断是否是登录页面发送的请求
        String requestURI = request.getRequestURI();
        if ("/userLogin".equals(requestURI)) {
            //判断传过来的验证码是否为空
            if (StringUtils.hasText(captchaCode)) {
                //从session中获取验证码，判断和页面传的验证码是否一致
                String sessionCaptchaCode = (String) request.getSession().getAttribute("captchaCode");
                if (StrUtil.equals(captchaCode, sessionCaptchaCode)) {
                    //放行
                    filterChain.doFilter(request, response);
                } else {
                    request.getSession().setAttribute("errMessage", "验证码不匹配");
                    response.sendRedirect("/toLogin");//重定向到登录页
                }
            } else {
                //验证码为空
                request.getSession().setAttribute("errMessage", "请输入验证码");
                response.sendRedirect("/toLogin");//重定向到登录页
            }
        } else {
            //放行
            filterChain.doFilter(request, response);
        }
    }
}
