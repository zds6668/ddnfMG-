package com.dd.ddfgm.miaoshaproject.dao;

import com.dd.ddfgm.miaoshaproject.dataobject.cdkeyDO;
import com.dd.ddfgm.miaoshaproject.dataobject.cdkeyVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface cdkeyDOMapper {

    List<cdkeyVO> getMyCdKey(@Param("uid") Integer uid);
    cdkeyDO getACdKey(@Param("itemid") Integer itemid);
    Integer useACdKey(@Param("id") Integer id, @Param("orderid") String orderid, @Param("userid") Integer userid);
}