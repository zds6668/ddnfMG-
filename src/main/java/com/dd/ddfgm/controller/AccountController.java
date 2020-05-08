package com.dd.ddfgm.controller;

import com.dd.ddfgm.entity.Account;
import com.dd.ddfgm.entity.Role;
import com.dd.ddfgm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/account")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/nums")
    public Integer getAccountNum() {
        return accountService.getAccountNum();
    }

    @RequestMapping("/info")
    @ResponseBody
    public Account getAccountInfo() {
        return accountService.getAccountInfo("111111");
    }

    @RequestMapping("/getroles")
    @ResponseBody
    public List<Role> getAccountRoles() {
        return accountService.getRoles(1);
    }

    @RequestMapping("/getrole")
    @ResponseBody
    public Role getRole() {
        return accountService.getRole("jiangju");
    }
}
