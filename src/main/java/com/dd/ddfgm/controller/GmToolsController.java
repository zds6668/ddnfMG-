package com.dd.ddfgm.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Five on 2020/7/9 23:07
 */
@Controller
public class GmToolsController {
    @RequestMapping({"/toItem"})
    public String toItem(){
        return "item";
    }
}
