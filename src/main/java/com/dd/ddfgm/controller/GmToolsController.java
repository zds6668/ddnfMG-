package com.dd.ddfgm.controller;

import com.dd.ddfgm.entity.EmailPojo;
import com.dd.ddfgm.entity.Item;
import com.dd.ddfgm.mapper.ItemsMapper;
import com.dd.ddfgm.service.EmailService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Five on 2020/7/9 23:07
 */
@Controller
public class GmToolsController {
    @Autowired
    ItemsMapper itemsMapper;

    @Autowired
    EmailService emailService;

    @RequestMapping({"/toItem"})
    public ModelAndView getItems(@RequestParam(required = true, defaultValue = "1") Integer page, HttpServletRequest request) {
        PageHelper.startPage(page, 4000);
        List<Item> itemList = itemsMapper.getItems(null, null);
        ModelAndView modelAndView = new ModelAndView("item");
        PageInfo<Item> pageInfo = new PageInfo<>(itemList);
        modelAndView.addObject("items", itemList);
        modelAndView.addObject("page", pageInfo);
        return modelAndView;
    }

    @PostMapping({"/addEmail"})
    @ResponseBody
    public Map<String, Integer> addEmail(@RequestParam(required = true,value = "receive_charac_no") Integer receive_charac_no,
                           @RequestParam("amplify_option") Integer amplify_option,
                           @RequestParam("amplify_value") Integer amplify_value,
                           @RequestParam("seperate_upgrade") Integer seperate_upgrade,
                           @RequestParam("seal_flag") Integer seal_flag,
                           @RequestParam(required = true, value = "item_id") Integer item_id,
                           @RequestParam(required = true, value = "add_info") Integer add_info,
                           @RequestParam("upgrade") Integer upgrade,
                           @RequestParam("gold") Integer gold) {
        Map<String, Integer> map = new HashMap<>();
        if (receive_charac_no == null || item_id == null || add_info == null) {
            map.put("status", -1);
            map.put("res", -1);
            return map;
        }
        List<EmailPojo> emailPojoList = new ArrayList<>();
        EmailPojo emailPojo = new EmailPojo();
        emailPojo.setReceive_charac_no(receive_charac_no);
        emailPojo.setAmplify_option(amplify_option);
        emailPojo.setAmplify_value(amplify_value);
        emailPojo.setSeperate_upgrade(seperate_upgrade);
        emailPojo.setSeal_flag(seal_flag);
        emailPojo.setItem_id(item_id);
        emailPojo.setAdd_info(add_info);
        emailPojo.setUpgrade(upgrade);
        emailPojo.setGold(gold);
        emailPojoList.add(emailPojo);
        int res = emailService.addEmail(emailPojoList);
        map.put("res",res);
        map.put("status",0);
        return map;
    }
}
