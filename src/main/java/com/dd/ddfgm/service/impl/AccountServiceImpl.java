package com.dd.ddfgm.service.impl;

import com.dd.ddfgm.entity.Account;
import com.dd.ddfgm.entity.Role;
import com.dd.ddfgm.mapper.AccountMapper;
import com.dd.ddfgm.mapper.RoleMapper;
import com.dd.ddfgm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public Account getAccountInfo(String accountname) {
        Account account = accountMapper.getAccountInfo(accountname);
        if (account.getQq() == null)
            account.setQq("没有qq");
        return account;
    }

    @Override
    public Integer getAccountNum() {
        return accountMapper.getAccountNum();
    }

    @Override
    public Role getRole(String charac_name) {
        return roleMapper.getRole(charac_name);
    }

    @Override
    public List<Role> getRoles(Integer uid) {
        return roleMapper.getRoles(uid);
    }
}
