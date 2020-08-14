package com.dd.ddfgm.miaoshaproject.controller;

import com.dd.ddfgm.entity.Account;
import com.dd.ddfgm.enums.operatStatus;
import com.dd.ddfgm.miaoshaproject.dao.cdkeyDOMapper;
import com.dd.ddfgm.miaoshaproject.dataobject.cdkeyVO;
import com.dd.ddfgm.miaoshaproject.error.BusinessException;
import com.dd.ddfgm.miaoshaproject.error.EmBusinessError;
import com.dd.ddfgm.miaoshaproject.response.CommonReturnType;
import com.dd.ddfgm.miaoshaproject.service.OrderService;
import com.dd.ddfgm.miaoshaproject.service.model.OrderModel;
import com.dd.ddfgm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

/**
 * date 2020-08-13 21:40:59
 */
@Controller("order")
@RequestMapping("/order")
@CrossOrigin(origins = {"*"}, allowCredentials = "true")
public class OrderController extends BaseController {
    @Autowired
    cdkeyDOMapper cdkeyDOMapper;

    @Autowired
    private OrderService orderService;

    @Autowired
    private AccountService accountService;

    //封装下单请求
    @RequestMapping(value = "/createorder", method = {RequestMethod.POST}, consumes = {CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createOrder(@RequestParam(name = "itemId") Integer itemId,
                                        @RequestParam(name = "amount") Integer amount,
                                        @RequestParam(name = "promoId", required = false) Integer promoId,
                                        HttpServletRequest httpServletRequest) throws BusinessException {

        //获取用户的登陆信息
        Account account = accountService.getAccountInfo(httpServletRequest.getUserPrincipal().getName());

        OrderModel orderModel = orderService.createOrder(account.getAccountname(), itemId, promoId, amount);

        return CommonReturnType.create(null);
    }

    //查看买到的所有订单
    @RequestMapping(value = "/allorder")
    public ModelAndView allOrder(HttpServletRequest httpServletRequest,
                                 Map<String, Object> map) throws BusinessException {

        //获取用户的登陆信息
        Account account = accountService.getAccountInfo(httpServletRequest.getUserPrincipal().getName());
        List<cdkeyVO> myCdKey = cdkeyDOMapper.getMyCdKey(account.getUID());
        if (myCdKey == null || myCdKey.size() == 0) {
            map.put("status", operatStatus.FAILED.getStatus());
        } else {
            map.put("status", operatStatus.FAILED.getStatus());
            map.put("myCdKey", myCdKey);
        }
        return new ModelAndView("/miaosha/myorder", map);
    }
}
