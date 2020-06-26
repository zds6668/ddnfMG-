package com.dd.ddfgm.mapper;

import com.dd.ddfgm.entity.Account;
import com.dd.ddfgm.entity.OnlineAccount;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountMapper {
    Account getAccountInfo(@Param("accountname") String accountname);

    Integer getAccountNum();

    List<OnlineAccount> getLoginAccounts();

    Integer rechargeDB(Integer uid, Integer cera);

    Integer rechargeDD(Integer uid, Integer cera_point);
}
