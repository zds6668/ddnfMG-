package com.dd.ddfgm.controller;

import com.dd.ddfgm.entity.Item;
import com.dd.ddfgm.mapper.ItemsMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * Created by Five on 2020/7/9 23:07
 */
@Controller
public class GmToolsController {
    @Autowired
    ItemsMapper itemsMapper;
    private ModelAndView modelAndView ;

/*    @RequestMapping({"/toItem"})
    public String toItem(){
        return "item";
    }*/

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
}
