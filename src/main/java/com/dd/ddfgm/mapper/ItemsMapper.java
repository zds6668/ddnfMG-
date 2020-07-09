package com.dd.ddfgm.mapper;

import com.dd.ddfgm.entity.Item;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Five on 2020/7/10 0:36
 */
@Repository
public interface ItemsMapper {
    List<Item> getItems(Integer it_no, String it_name);
}
