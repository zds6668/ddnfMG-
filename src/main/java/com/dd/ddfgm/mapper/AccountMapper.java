package com.dd.ddfgm.mapper;

import com.dd.ddfgm.entity.Account;
import com.dd.ddfgm.entity.OnlineAccount;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountMapper {
    Account getAccountInfo(@Param("accountname") String accountname);

    Integer getAccountNum();

    List<OnlineAccount> getLoginAccounts();


}
