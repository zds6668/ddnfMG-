package com.dd.ddfgm.mapper;

import com.dd.ddfgm.miaoshaproject.dao.cdkeyDOMapper;
import com.dd.ddfgm.miaoshaproject.dataobject.cdkeyDO;
import com.dd.ddfgm.miaoshaproject.dataobject.cdkeyVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


/**
 * Created by Five on 2020/8/15 0:08
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class cdkeyMapperTest {
    @Autowired
    cdkeyDOMapper cdkeyDOMapper;

    //@Test
    public void getMyCdKey() {
        cdkeyVO cdkeyVO = cdkeyDOMapper.getMyCdKey(1);
        System.out.println(cdkeyVO.getTitle());
    }
    //@Test
    public void getACdKey() {
        cdkeyDO cdkeyDO = cdkeyDOMapper.getACdKey(430);
        System.out.println(cdkeyDO.getCdkey());
    }
    @Test
    public void useACdKey() {
        Integer result = cdkeyDOMapper.useACdKey(2,"2020081400004601", 1);
        System.out.println(result);
    }
}
