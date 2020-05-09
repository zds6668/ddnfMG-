package com.dd.ddfgm.dao;

import com.dd.ddfgm.entity.Account;
import com.dd.ddfgm.mapper.AccountMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountMapperTest {
    @Autowired
    AccountMapper accountMapper;


    @Test
    public void getAccountNum() {
        System.out.println(accountMapper.getAccountNum());
    }

    @Test
    public void getAccountInfo() {
        Account account = accountMapper.getAccountInfo("111111");
        System.out.println(account.getAccountname());
        System.out.println(account.getCera());
        System.out.println(account.getQq());
    }
}