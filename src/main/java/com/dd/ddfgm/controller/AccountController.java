package com.dd.ddfgm.controller;

import com.dd.ddfgm.entity.Account;
import com.dd.ddfgm.entity.Role;
import com.dd.ddfgm.enums.operatStatus;
import com.dd.ddfgm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
    public ModelAndView getAccountInfo(@RequestParam(value = "accountname", defaultValue = "") String accountname,
                                  HttpServletRequest request,
                                  Map<String, Object> map) {
        Integer accountNum = accountService.getAccountNum();
        map.put("accountNum", accountNum);
        Account account;
        if ("".equalsIgnoreCase(accountname)){
            map.put("status", operatStatus.FAILED.getStatus());
            map.put("account", null);
        }
        else {
            account = accountService.getAccountInfo(accountname);
            if (account == null) {
                map.put("status", operatStatus.FAILED.getStatus());
                map.put("account", null);
            } else {
                Integer uid = account.getUID();
                List<Role> roles = accountService.getRoles(uid);
                map.put("roles", roles);
                map.put("status", operatStatus.SUCCESS.getStatus());
                map.put("account", account);
            }
        }
        return new ModelAndView("/index", map);
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

    @RequestMapping("/db")
    public Integer rechargeDB(@RequestParam(value = "uid") Integer uid,
                              @RequestParam(value = "cera") Integer cera,
                                       HttpServletRequest request,
                                       Map<String, Object> map) {
        Integer result = accountService.rechargeDB(uid, cera);
        return result;
    }

    @RequestMapping("/dd")
    public Integer rechargeDD(@RequestParam(value = "uid") Integer uid,
                              @RequestParam(value = "cera_point") Integer cera_point,
                              HttpServletRequest request,
                              Map<String, Object> map) {
        Integer result = accountService.rechargeDD(uid, cera_point);
        return result;
    }
}
