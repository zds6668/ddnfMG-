package com.dd.ddfgm.entity;

import lombok.Data;
import org.springframework.data.annotation.Id;

/**
 * Created by Five on 2020/7/9 23:55
 */
@Data
public class Item {
    @Id
    private Integer id;
    private Integer it_no;
    private String it_name;
}
