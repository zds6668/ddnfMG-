package com.dd.ddfgm.dao;

import com.dd.ddfgm.entity.*;
import com.dd.ddfgm.enums.JobsEnum;
import com.dd.ddfgm.mapper.AccountMapper;
import com.dd.ddfgm.mapper.RoleMapper;
import com.dd.ddfgm.service.AccountService;
import com.dd.ddfgm.utils.EnumUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountMapperTest {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    AccountService accountService;
    @Autowired
    RoleMapper roleMapper;


    public void getAccountNum() {
        System.out.println(accountMapper.getAccountNum());
    }

    public void getAccountInfo() {
        List<Role> roles = accountService.getRoles(1);
        for (Role role :
                roles) {
            System.out.println(role.getGameCareer());
        }
    }
    @Test
    public void getOnlineAccounts() {
        List<OnlineAccount> onlineAccounts = accountMapper.getLoginAccounts();
        for (OnlineAccount account :
                onlineAccounts) {
            String[] ips = account.getLogin_ip().split("\\.");
            System.out.println(ips[0]);
            System.out.println(ips[3]);
        }
    }

    public void rechargeDB() {
        Account account = accountMapper.getAccountInfo("111111");
        Integer db = new Integer(121231);
        Integer result = accountMapper.rechargeDB(account.getUID(), db);
        System.out.println(result);
    }

    public void rechargeDD() {
        Account account = accountMapper.getAccountInfo("111111");

        Integer dd = new Integer(121231);
        Integer result = accountMapper.rechargeDD(account.getUID(), dd);
        System.out.println(result);
    }

    public void getUserTest() {
        User user = accountMapper.getUserByName("111111");
        System.out.println(user.getPassword());
        System.out.println(user.getVIP());
        System.out.println(user.getVIP() == null);
        System.out.println("".equalsIgnoreCase(user.getVIP()));
    }


    public void getranklist() {
        List<RankDTO> rankDTOList = roleMapper.getRankList();
        System.out.println(rankDTOList.get(0).getZDJ());
    }


    public void getAllAccounts() {
        ArrayList<OnlineAccount> onlineAccounts = (ArrayList<OnlineAccount>) accountMapper.getAllAccounts();
        for (OnlineAccount onlineAccount : onlineAccounts) {
            String jod = onlineAccount.getJob() + "_" + onlineAccount.getGrow_type();
            System.out.println(onlineAccount.getCharac_name());
            System.out.println(jod);
            String GameCareer = EnumUtil.getByCode(jod, JobsEnum.class).getGameCareer();
            onlineAccount.setGameCareer(GameCareer);
        }
    }
}