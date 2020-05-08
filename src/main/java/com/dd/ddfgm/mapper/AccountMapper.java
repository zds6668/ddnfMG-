package com.dd.ddfgm.mapper;

import com.dd.ddfgm.entity.Account;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

public interface AccountMapper {
    Account getAccountInfo(@Param("accountname") String accountname);

    Integer getAccountNum();
}
