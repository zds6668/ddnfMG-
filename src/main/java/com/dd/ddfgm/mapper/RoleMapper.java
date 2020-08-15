package com.dd.ddfgm.mapper;

import com.dd.ddfgm.entity.EmailPojo;
import com.dd.ddfgm.entity.RankDTO;
import com.dd.ddfgm.entity.Role;
import com.dd.ddfgm.miaoshaproject.controller.viewobject.RolePkVO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

@Repository
public interface RoleMapper {
    Role getRole(@Param("charac_name") String charac_name);

    List<Role> getRoles(@Param("uid") Integer uid);

    List<RolePkVO> getRolesPk(@Param("uid") Integer uid, @Param("charac_no") Integer charac_no);

    Integer setPkWin(@Param("charac_no") Integer charac_no, @Param("win") Integer win,
                     @Param("pvp_count") Integer pvp_count, @Param("play_count") Integer play_count);

    int getNextMailId();

    int addEmail(EmailPojo emailPojo);

    List<RankDTO> getRankList();
}
