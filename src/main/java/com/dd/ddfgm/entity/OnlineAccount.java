package com.dd.ddfgm.entity;

import lombok.Data;

@Data
public class OnlineAccount {
    private Integer m_id;
    private String accountname;
    String last_login_date;
    String login_ip;
    Integer m_channel_no;
}
