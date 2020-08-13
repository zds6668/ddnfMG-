package com.dd.ddfgm.controller;

import com.dd.ddfgm.entity.Account;
import com.dd.ddfgm.entity.Role;
import com.dd.ddfgm.enums.operatStatus;
import com.dd.ddfgm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
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
    public ModelAndView getAccountInfo(@RequestParam(value = "accountname", defaultValue = "") String accountname,
                                       HttpServletRequest request,
                                       Map<String, Object> map) {
        Integer accountNum = accountService.getAccountNum();
        Integer loginAccountNum = accountService.getLoginAccounts();
        map.put("accountNum", accountNum);
        map.put("loginAccountNum", loginAccountNum);
        Account account;
        Principal principal = request.getUserPrincipal();
        String selectName = (String) request.getSession().getAttribute("SELECT_NAME");
        // 如果没有在查询框输入账号
        if ("".equalsIgnoreCase(accountname) || null == accountname) {
            // 如果之前也没有查过
            if ("".equalsIgnoreCase(selectName) || null == selectName) {
                accountname = principal.getName();
            // 说明刚查询过，仍用上一次查询的账号
            } else {
                accountname = selectName;
            }
        }
        account = accountService.getAccountInfo(accountname);
        if (account == null) {
            map.put("status", operatStatus.FAILED.getStatus());
            request.getSession().removeAttribute("SELECT_NAME");
        } else {
            request.getSession().setAttribute("SELECT_NAME", accountname);
            Integer uid = account.getUID();
            List<Role> roles = accountService.getRoles(uid);
            map.put("roles", roles);
            map.put("status", operatStatus.SUCCESS.getStatus());
            map.put("account", account);
        }
        return new ModelAndView("index", map);
    }

    @PostMapping("/db")
    public String rechargeDB(@RequestParam(value = "dbname") String accountname,
                             @RequestParam(value = "dbnum", defaultValue = "0") Integer cera,
                             HttpServletRequest request,
                             Map<String, Object> map) {
        Integer result = accountService.rechargeDB(accountname, cera);
        return "redirect:/account/info";
    }

    @PostMapping("/dd")
    public String rechargeDD(@RequestParam(value = "ddname") String accountname,
                             @RequestParam(value = "ddnum", defaultValue = "0") Integer cera_point,
                             HttpServletRequest request,
                             Map<String, Object> map) {
        Integer result = accountService.rechargeDD(accountname, cera_point);
        return "redirect:/account/info";
    }
}
