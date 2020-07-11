package com.dd.ddfgm.service;

import com.dd.ddfgm.entity.EmailPojo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Five on 2020/7/12 1:35
 */
public interface EmailService {
    int addEmail(List<EmailPojo> emailPojoList);
}
