package com.dd.ddfgm.service.impl;

import com.dd.ddfgm.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by Five on 2020/6/27 1:09
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountServiceImplTest {
    @Autowired
    AccountService accountService;

    void getAccountInfo() {
    }

    void getAccountNum() {
    }

    void getLoginAccounts() {
    }

    void getRole() {
    }

    void getRoles() {
    }

    @Test
    public void rechargeDB() {
        accountService.rechargeDB("111111",1);
    }

    public void rechargeDD() {
    }
}