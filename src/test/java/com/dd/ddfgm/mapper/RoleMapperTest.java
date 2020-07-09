package com.dd.ddfgm.mapper;

import com.dd.ddfgm.entity.Item;
import com.dd.ddfgm.entity.Role;
import com.dd.ddfgm.enums.JobsEnum;
import com.dd.ddfgm.utils.EnumUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.UnsupportedEncodingException;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
public class RoleMapperTest {
    @Autowired
    private RoleMapper roleMapper;

    @Autowired
    private ItemsMapper itemsMapper;

    @Test
    public void getRole() throws UnsupportedEncodingException {
        System.out.println(new String("晶体".getBytes("UTF-8"), "ISO-8859-1"));
        List<Item> items = itemsMapper.getItems(null, new String("晶体".getBytes("UTF-8"), "ISO-8859-1"));
        for (Item item : items) {
            System.out.println(item.getId());
        }
    }

    public void getRoles() {
        List<Role> roles = roleMapper.getRoles(1);
        System.out.println(roles.size());
        for (Role role:roles) {
            String job = role.getJob() + "_" +  role.getGrow_type();
            System.out.println(job);
            System.out.println(EnumUtil.getByCode(job, JobsEnum.class).getGameCareer());
        }

    }
}