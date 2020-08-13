package com.dd.ddfgm.miaoshaproject.controller;

import com.dd.ddfgm.entity.Account;
import com.dd.ddfgm.miaoshaproject.error.BusinessException;
import com.dd.ddfgm.miaoshaproject.error.EmBusinessError;
import com.dd.ddfgm.miaoshaproject.response.CommonReturnType;
import com.dd.ddfgm.miaoshaproject.service.OrderService;
import com.dd.ddfgm.miaoshaproject.service.model.OrderModel;
import com.dd.ddfgm.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * date 2020-08-13 21:40:59
 */
@Controller("order")
@RequestMapping("/order")
@CrossOrigin(origins = {"*"},allowCredentials = "true")
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private AccountService accountService;

    //封装下单请求
    @RequestMapping(value = "/createorder",method = {RequestMethod.POST},consumes={CONTENT_TYPE_FORMED})
    @ResponseBody
    public CommonReturnType createOrder(@RequestParam(name="itemId")Integer itemId,
                                        @RequestParam(name="amount")Integer amount,
                                        @RequestParam(name="promoId",required = false)Integer promoId,
                                        HttpServletRequest httpServletRequest) throws BusinessException {

        Boolean isLogin = (Boolean) httpServletRequest.getSession().getAttribute("IS_LOGIN");
        if(isLogin == null || !isLogin.booleanValue()){
            throw new BusinessException(EmBusinessError.USER_NOT_LOGIN,"用户还未登陆，不能下单");
        }

        //获取用户的登陆信息
        Account account = accountService.getAccountInfo(httpServletRequest.getUserPrincipal().getName());

        OrderModel orderModel = orderService.createOrder(account.getUID(),itemId,promoId,amount);

        return CommonReturnType.create(null);
    }
}
