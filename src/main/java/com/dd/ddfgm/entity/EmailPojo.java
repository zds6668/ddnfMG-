package com.dd.ddfgm.entity;

import lombok.Data;

/**
 * Created by Five on 2020/7/12 0:46
 */
@Data
public class EmailPojo {
    Integer postal_id;
    String occ_time;
    String send_charac_name;
    Integer receive_charac_no;
    // 红字 四属性之一
    Integer amplify_option;
    // 红字附加值
    Integer amplify_value;
    // 锻造
    Integer seperate_upgrade;
    // 封装
    Integer seal_flag;
    Integer item_id;
    // 数量
    Integer add_info;
    // 强化
    Integer upgrade;
    // 金币
    Integer gold;
    Integer letter_id;
}
