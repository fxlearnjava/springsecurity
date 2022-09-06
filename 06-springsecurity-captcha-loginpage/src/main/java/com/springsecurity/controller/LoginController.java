package com.springsecurity.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
public class LoginController {

    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }

    @RequestMapping("/hello")
    public @ResponseBody String hello(){
        return "hello";
    }


    @RequestMapping("/git")
    public @ResponseBody String helloGit(){
        return "helloGit";
    }


    @RequestMapping("/masterTest")
    public @ResponseBody String masterTest(){
        return "masterTest";
    }

    @RequestMapping("/gitFix")
    public @ResponseBody String gitFix(){
        return "gitFix";
    }
}
