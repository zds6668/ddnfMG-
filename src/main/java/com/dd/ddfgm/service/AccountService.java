package com.dd.ddfgm.service;

import com.dd.ddfgm.entity.*;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AccountService {
    User getUserByName(String accountname);

    public Account getAccountInfo(String accountname);

    Integer getAccountNum();

    Integer getLoginAccounts();

    List<OnlineAccount> getOnlineAccounts();

    List<OnlineAccount> getAllAccounts();
    List<OnlineAccount> getOnlineAccountsNoHidden();

    List<OnlineAccount> getAllAccountsNoHidden();

    Role getRole(String charac_name);

    List<Role> getRoles(Integer uid);

    Integer rechargeDB(String accountname, Integer cera);

    Integer rechargeDD(String accountname, Integer cera_point);

    List<RankDTO> getRankList();
}
