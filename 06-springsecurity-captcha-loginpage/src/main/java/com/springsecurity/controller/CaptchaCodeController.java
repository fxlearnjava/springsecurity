package com.springsecurity.controller;
import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Controller
public class CaptchaCodeController {

    @RequestMapping("/captchaCode")
    public void captchaCode(HttpServletRequest request, HttpServletResponse response) throws IOException {
        // java.awt.*
        //产生验证码图片的。图片的宽是116，高是36，验证码的长度是4，干扰线的条数是20
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(80, 25, 4, 20);
        //获取验证码图片中的字符串
        String captchaCode = lineCaptcha.getCode();

        //把验证码图片中的字符串放入session
        request.getSession().setAttribute("captchaCode", captchaCode);

        //获取到response的响应流
        ServletOutputStream outputStream = response.getOutputStream();
        //把图片放入到response的相应流中
        lineCaptcha.write(outputStream);

        outputStream.flush();
        outputStream.close();
    }
}
