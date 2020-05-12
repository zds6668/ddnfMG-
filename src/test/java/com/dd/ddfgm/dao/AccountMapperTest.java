package com.dd.ddfgm.dao;

import com.dd.ddfgm.entity.Account;
import com.dd.ddfgm.entity.Role;
import com.dd.ddfgm.mapper.AccountMapper;
import com.dd.ddfgm.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AccountMapperTest {
    @Autowired
    AccountMapper accountMapper;
    @Autowired
    AccountService accountService;


    @Test
    public void getAccountNum() {
        System.out.println(accountMapper.getAccountNum());
    }

    @Test
    public void getAccountInfo() {
        List<Role> roles = accountService.getRoles(1);
        for (Role role :
                roles) {
            System.out.println(role.getGameCareer());
        }
    }
}