package com.dd.ddfgm.dao;

import com.dd.ddfgm.entity.Account;
import com.dd.ddfgm.entity.OnlineAccount;
import com.dd.ddfgm.entity.Role;
import com.dd.ddfgm.entity.User;
import com.dd.ddfgm.mapper.AccountMapper;
import com.dd.ddfgm.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountMapperTest {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    AccountService accountService;


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

    public void getOnlineAccounts() {
        List<OnlineAccount> onlineAccounts = accountMapper.getLoginAccounts();
        for (OnlineAccount account :
                onlineAccounts) {
            System.out.println(account.getLast_login_date());
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

    @Test
    public void getUserTest() {
        User user = accountMapper.getUserByName("111111");
        System.out.println(user.getPassword());
        System.out.println(user.getVIP());
    }
}