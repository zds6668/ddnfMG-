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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/roles")
public class RolesController {
    @Autowired
    private AccountService accountService;

    @RequestMapping("/all")
    @ResponseBody
    public List<Role> getAllRoles() {
        List<Role> roles = accountService.getRoles(null);
        return roles;
    }

    @RequestMapping("/info")
    public ModelAndView getRolesInfo(Map<String, Object> map) {
        Integer accountNum = accountService.getAccountNum();
        Integer loginAccountNum = accountService.getLoginAccounts();
        map.put("accountNum", accountNum);
        map.put("loginAccountNum", loginAccountNum);
                map.put("status", operatStatus.FAILED.getStatus());

        return new ModelAndView("roles", map);
    }

}
