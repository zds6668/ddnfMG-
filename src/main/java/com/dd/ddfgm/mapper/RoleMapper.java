package com.dd.ddfgm.mapper;

import com.dd.ddfgm.entity.EmailPojo;
import com.dd.ddfgm.entity.RankDTO;
import com.dd.ddfgm.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface RoleMapper {
    Role getRole(@Param("charac_name") String charac_name);

    List<Role> getRoles(@Param("uid") Integer uid);

    int getNextMailId();

    int addEmail(EmailPojo emailPojo);

    List<RankDTO> getRankList();
}
