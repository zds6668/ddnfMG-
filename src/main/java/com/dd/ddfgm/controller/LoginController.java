package com.dd.ddfgm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Five on 2020/7/7 1:03
 */
@Controller
public class LoginController {
    @RequestMapping("/toLogin")
    public String toLogin(){
        return "login";
    }
}
