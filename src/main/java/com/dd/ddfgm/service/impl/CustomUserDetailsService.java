package com.dd.ddfgm.service.impl;

import com.dd.ddfgm.entity.User;
import com.dd.ddfgm.mapper.AccountMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Five on 2020/7/8 1:08
 */
@Service
public class CustomUserDetailsService implements UserDetailsService { //自定义UserDetailsService 接口

    @Autowired
    AccountMapper accountMapper;

    //重写loadUserByUsername 方法获得 userdetails 类型用户
    @Override
    public UserDetails loadUserByUsername(String username) {

        User user = accountMapper.getUserByName(username);
        if (user == null) {
            throw new UsernameNotFoundException("用户名或密码错误");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        //用于添加用户的权限。只要把用户权限添加到authorities 就万事大吉。
        System.out.println(user.getVIP());
        if (!"".equals(user.getVIP())) {
            authorities.add(new SimpleGrantedAuthority("VIP"));
        }
        else {
            authorities.add(new SimpleGrantedAuthority("user"));
        }
        return new org.springframework.security.core.userdetails.User(user.getAccountname(),
                user.getPassword(), authorities);
    }
}
