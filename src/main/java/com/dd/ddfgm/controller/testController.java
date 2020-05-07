package com.dd.ddfgm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class testController {
    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/auth")
    public String auth() {
        return "auth";
    }
    @RequestMapping("/reg")
    public String reg() {
        return "reg";
    }
}
