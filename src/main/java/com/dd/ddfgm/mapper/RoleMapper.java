package com.dd.ddfgm.mapper;

import com.dd.ddfgm.entity.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RoleMapper {
    Role getRole(@Param("charac_name") String charac_name);

    List<Role> getRoles(@Param("uid") Integer uid);
}
