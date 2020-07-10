package com.dd.ddfgm.entity;

import lombok.Data;

@Data
public class OnlineAccount {
    private Integer m_id;
    private Integer charac_no;
    private Integer job;
    private Integer grow_type;
    private Integer lev;;
    private String accountname;
    private String charac_name;
    private String GameCareer;
    String last_login_date;
    String login_ip;
    Integer m_channel_no;
}
