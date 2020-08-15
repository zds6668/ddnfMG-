package com.dd.ddfgm.mapper;

import com.dd.ddfgm.miaoshaproject.dao.cdkeyDOMapper;
import com.dd.ddfgm.miaoshaproject.dataobject.cdkeyDO;
import com.dd.ddfgm.miaoshaproject.dataobject.cdkeyVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;


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
    }
    //@Test
    public void getACdKey() {
        cdkeyDO cdkeyDO = cdkeyDOMapper.getACdKey(430);
        System.out.println(cdkeyDO.getCdkey());
    }
    //@Test
    public void useACdKey() {
        Integer result = cdkeyDOMapper.useACdKey(2,"2020081400004602", 1);
        System.out.println(result);
    }

    @Test
    public void batchInsertCdkey() {
        String path = "C:\\Users\\dqw66\\Desktop\\CdKey.txt";
        ArrayList<String> arrayList = new ArrayList<>();
        try {
            FileReader fr = new FileReader(path);
            BufferedReader bf = new BufferedReader(fr);
            String str;
            // 按行读取字符串
            while ((str = bf.readLine()) != null) {
                arrayList.add(str);
            }
            bf.close();
            fr.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        String[] arr;
        for (String s : arrayList) {
            arr = s.split(" ");
            cdkeyDO cdkeyDO = new cdkeyDO();
            cdkeyDO.setItemid(Integer.parseInt(arr[0]));
            cdkeyDO.setCdkey(arr[1]);
            cdkeyDOMapper.insertCdkey(cdkeyDO);
        }
    }
}
