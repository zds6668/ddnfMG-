package com.dd.ddfgm.entity;

import lombok.Data;

import java.util.Date;

@Data
public class Role {
    private Integer m_id;
    private Integer charac_no;
    private Integer job;
    private Integer grow_type;
    private Integer lev;
    private String charac_name;
    private String create_time;
    private String GameCareer;
}
