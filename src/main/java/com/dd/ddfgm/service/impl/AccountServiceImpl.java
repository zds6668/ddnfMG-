package com.dd.ddfgm.service.impl;

import com.dd.ddfgm.entity.Account;
import com.dd.ddfgm.entity.Role;
import com.dd.ddfgm.enums.JobsEnum;
import com.dd.ddfgm.mapper.AccountMapper;
import com.dd.ddfgm.mapper.RoleMapper;
import com.dd.ddfgm.service.AccountService;
import com.dd.ddfgm.utils.EnumUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
        if (null != account && account.getQq() == null)
            account.setQq("这逼很神秘,没有留下qq");
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
        ArrayList<Role> roles = (ArrayList<Role>) roleMapper.getRoles(uid);
        for (Role role:roles) {
            String job = role.getJob() + "_" +  role.getGrow_type();
            String GameCareer = EnumUtil.getByCode(job, JobsEnum.class).getGameCareer();
            role.setGameCareer(GameCareer);
        }
        return roles;
    }

    @Override
    public Integer rechargeDB(Integer uid, Integer cera) {
        Integer result = accountMapper.rechargeDB(uid, cera);
        return result;
    }

    @Override
    public Integer rechargeDD(Integer uid, Integer cera_point) {
        return accountMapper.rechargeDD(uid, cera_point);
    }
}
