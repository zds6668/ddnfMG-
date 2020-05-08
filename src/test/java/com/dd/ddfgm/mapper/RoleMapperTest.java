package com.dd.ddfgm.mapper;

import com.dd.ddfgm.entity.Role;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleMapperTest {
    @Autowired
    private RoleMapper roleMapper;

    @Test
    public void getRole() {
        Role role = roleMapper.getRole("阿斯頓");
        System.out.println(role.getLev());
    }

    @Test
    public void getRoles() {
        List<Role> roles = roleMapper.getRoles(1);
        System.out.println(roles.size());
        for (Role role:roles) {
            System.out.println(role.getCharac_name());
        }

    }
}