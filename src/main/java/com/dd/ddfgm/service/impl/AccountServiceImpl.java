package com.dd.ddfgm.service.impl;

import com.dd.ddfgm.entity.*;
import com.dd.ddfgm.enums.JobsEnum;
import com.dd.ddfgm.mapper.AccountMapper;
import com.dd.ddfgm.mapper.RoleMapper;
import com.dd.ddfgm.service.AccountService;
import com.dd.ddfgm.utils.EnumUtil;
import com.dd.ddfgm.utils.HiddenStringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountMapper accountMapper;

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public User getUserByName(String accountname) {
        return accountMapper.getUserByName(accountname);
    }

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
    public Integer getLoginAccounts() {
        return accountMapper.getLoginAccounts().size();
    }

    @Override
    public List<OnlineAccount> getOnlineAccounts() {
        ArrayList<OnlineAccount> onlineAccounts = (ArrayList<OnlineAccount>) accountMapper.getLoginAccounts();
        String[] ips;
        for (OnlineAccount onlineAccount : onlineAccounts) {
            // ip匿名化处理
            ips = onlineAccount.getLogin_ip().split("\\.");
            onlineAccount.setLogin_ip(new StringBuffer().append(ips[0]).append(".").append(ips[1]).append(".").append("*").append(".").append("*").toString());
            String jod = onlineAccount.getJob() + "_" + onlineAccount.getGrow_type();
            String GameCareer = EnumUtil.getByCode(jod, JobsEnum.class).getGameCareer();
            onlineAccount.setGameCareer(GameCareer);
            onlineAccount.setAccountname(HiddenStringUtil.hiddenStringHalf(onlineAccount.getAccountname()));
        }
        return onlineAccounts;
    }

    @Override
    public List<OnlineAccount> getAllAccounts() {
        ArrayList<OnlineAccount> onlineAccounts = (ArrayList<OnlineAccount>) accountMapper.getAllAccounts();
        String[] ips;
        for (OnlineAccount onlineAccount : onlineAccounts) {
            ips = onlineAccount.getLogin_ip().split("\\.");
            onlineAccount.setLogin_ip(new StringBuffer().append(ips[0]).append(".").append(ips[1]).append(".").append("*").append(".").append("*").toString());
            String jod = onlineAccount.getJob() + "_" + onlineAccount.getGrow_type();
            String GameCareer = EnumUtil.getByCode(jod, JobsEnum.class).getGameCareer();
            onlineAccount.setGameCareer(GameCareer);
            onlineAccount.setAccountname(HiddenStringUtil.hiddenStringHalf(onlineAccount.getAccountname()));
        }
        return onlineAccounts;
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
    public Integer rechargeDB(String accountname, Integer cera) {
        Account account = accountMapper.getAccountInfo(accountname);
        if (null == account)
            return 0;
        int nowCera = account.getCera();
        Integer result = accountMapper.rechargeDB(account.getUID(), nowCera + cera);
        return result;
    }

    @Override
    public Integer rechargeDD(String accountname, Integer cera_point) {
        Account account = accountMapper.getAccountInfo(accountname);
        if (null == account)
            return 0;
        int nowCeraPoint = account.getCera_point();
        return accountMapper.rechargeDD(account.getUID(), cera_point + nowCeraPoint);
    }

    @Override
    public List<RankDTO> getRankList() {
        List<RankDTO> rankList = roleMapper.getRankList();
        int rankLev = 1;
        for (RankDTO rankDTO : rankList) {
            rankDTO.setRankLev(rankLev++);
        }
        return rankList;
    }
}
