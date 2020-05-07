package com.dd.ddfgm.dao;

import com.dd.ddfgm.entity.Acount;
import org.apache.ibatis.annotations.Mapper;

public interface AcountMapper {
    Acount findAcount(Integer UID);

    Integer getAcountNum();
}
