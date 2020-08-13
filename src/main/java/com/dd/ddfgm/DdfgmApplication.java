package com.dd.ddfgm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan(basePackages = {"com.dd.ddfgm.mapper", "com.dd.ddfgm.miaoshaproject.dao"})
public class DdfgmApplication {

    public static void main(String[] args) {
        SpringApplication.run(DdfgmApplication.class, args);
    }

}
