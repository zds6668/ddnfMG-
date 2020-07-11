package com.dd.ddfgm.service.impl;

import com.dd.ddfgm.entity.EmailPojo;
import com.dd.ddfgm.mapper.RoleMapper;
import com.dd.ddfgm.service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Five on 2020/7/12 1:36
 */
@Service
public class EmailServiceImpl implements EmailService {
    @Autowired
    RoleMapper roleMapper;

    @Override
    public int addEmail(List<EmailPojo> emailPojoList) {
        int res;
        if (emailPojoList == null || emailPojoList.size() == 0)
            res = -1;
        else {
            int nextId = roleMapper.getNextMailId();
            res = 0;
            for (EmailPojo emailPojo : emailPojoList) {
                emailPojo.setOcc_time((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
                emailPojo.setLetter_id(nextId++);
                roleMapper.addEmail(emailPojo);
                res++;
            }
        }
        return res;
    }
}
