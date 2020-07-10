package com.dd.ddfgm.entity;

import lombok.Data;

/**
 * Created by Five on 2020/7/10 19:56
 */
@Data
public class ActivityLog {
    Integer log_id;
    Integer event_type;
    String log_name;
    Integer parameter1;
}
