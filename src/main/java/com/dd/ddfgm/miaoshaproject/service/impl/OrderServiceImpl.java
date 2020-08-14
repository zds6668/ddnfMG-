package com.dd.ddfgm.miaoshaproject.service.impl;

import com.dd.ddfgm.entity.Account;
import com.dd.ddfgm.miaoshaproject.dao.OrderDOMapper;
import com.dd.ddfgm.miaoshaproject.dao.SequenceDOMapper;
import com.dd.ddfgm.miaoshaproject.dao.cdkeyDOMapper;
import com.dd.ddfgm.miaoshaproject.dataobject.OrderDO;
import com.dd.ddfgm.miaoshaproject.dataobject.SequenceDO;
import com.dd.ddfgm.miaoshaproject.dataobject.cdkeyDO;
import com.dd.ddfgm.miaoshaproject.error.BusinessException;
import com.dd.ddfgm.miaoshaproject.error.EmBusinessError;
import com.dd.ddfgm.miaoshaproject.service.ItemService;
import com.dd.ddfgm.miaoshaproject.service.OrderService;
import com.dd.ddfgm.miaoshaproject.service.model.ItemModel;
import com.dd.ddfgm.miaoshaproject.service.model.OrderModel;
import com.dd.ddfgm.service.AccountService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * date 2020-08-13 21:40:59
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    cdkeyDOMapper cdkeyDOMapper;

    @Autowired
    AccountService accountService;

    @Autowired
    private SequenceDOMapper sequenceDOMapper;

    @Autowired
    private ItemService itemService;

    @Autowired
    private OrderDOMapper orderDOMapper;

    @Override
    @Transactional
    public OrderModel createOrder(String userId, Integer itemId, Integer promoId, Integer amount) throws BusinessException {
        //1.校验下单状态,下单的商品是否存在，用户是否合法，购买数量是否正确
        ItemModel itemModel = itemService.getItemById(itemId);
        if(itemModel == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"商品信息不存在");
        }

        Account account = accountService.getAccountInfo(userId);
        if(account == null){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"用户信息不存在");
        }
        if(amount <= 0 || amount > 99){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"数量信息不正确");
        }

        //校验活动信息
        if(promoId != null){
            //（1）校验对应活动是否存在这个适用商品
            if(promoId.intValue() != itemModel.getPromoModel().getId()){
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"活动信息不正确");
                //（2）校验活动是否正在进行中
            }else if(itemModel.getPromoModel().getStatus().intValue() != 2) {
                throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"活动信息还未开始");
            }
        }

        //2.落单减库存
        boolean result = itemService.decreaseStock(itemId,amount);
        if(!result){
            throw new BusinessException(EmBusinessError.STOCK_NOT_ENOUGH);
        }




        // 检查是否有可用的cdkey
        cdkeyDO aCdKey = cdkeyDOMapper.getACdKey(itemModel.getDescription());
        if (null == aCdKey) {
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"没有可用的cdkey了");
        }


        //3.订单入库
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(account.getUID());
        orderModel.setItemId(itemModel.getDescription());
        orderModel.setAmount(amount);
        if(promoId != null){
            orderModel.setItemPrice(itemModel.getPromoModel().getPromoItemPrice());
        }else{
            orderModel.setItemPrice(itemModel.getPrice());
        }
        orderModel.setPromoId(promoId);
        orderModel.setOrderPrice(orderModel.getItemPrice().multiply(new BigDecimal(amount)));

        // 检查用户是否有足够的点卷购买,如果订单金额高于用户D点
        if (orderModel.getOrderPrice().compareTo(BigDecimal.valueOf(account.getCera_point())) != -1)
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"D点不够购买该商品");
        // 扣减用户的D点
        int rechargeDDresult = accountService.rechargeDD(account.getAccountname(), (orderModel.getOrderPrice().intValue() * -1));
        if(rechargeDDresult <= 0){
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"D点扣除失败，看下是不是D点不够购买");
        }

        //生成交易流水号,订单号
        orderModel.setId(generateOrderNo());
        OrderDO orderDO = convertFromOrderModel(orderModel);
        orderDOMapper.insertSelective(orderDO);

        //加上商品的销量
        itemService.increaseSales(itemId,amount);

        // 记录该cdkey已经使用
        int useACdKeyresult = cdkeyDOMapper.useACdKey(aCdKey.getId(), orderModel.getId(), account.getUID());
        if (useACdKeyresult <= 0)
            throw new BusinessException(EmBusinessError.PARAMETER_VALIDATION_ERROR,"分配cdkey出错");

        //4.返回前端
        return orderModel;
    }


    @Transactional(propagation = Propagation.REQUIRES_NEW)
    String generateOrderNo(){
        //订单号有16位
        StringBuilder stringBuilder = new StringBuilder();
        //前8位为时间信息，年月日
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-","");
        stringBuilder.append(nowDate);

        //中间6位为自增序列
        //获取当前sequence
        int sequence = 0;
        SequenceDO sequenceDO =  sequenceDOMapper.getSequenceByName("order_info");
        sequence = sequenceDO.getCurrentValue();
        sequenceDO.setCurrentValue(sequenceDO.getCurrentValue() + sequenceDO.getStep());
        sequenceDOMapper.updateByPrimaryKeySelective(sequenceDO);
        String sequenceStr = String.valueOf(sequence);
        for(int i = 0; i < 6-sequenceStr.length();i++){
            stringBuilder.append(0);
        }
        stringBuilder.append(sequenceStr);


        //最后2位为分库分表位,暂时写死
        stringBuilder.append("00");

        return stringBuilder.toString();
    }
    private OrderDO convertFromOrderModel(OrderModel orderModel){
        if(orderModel == null){
            return null;
        }
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderModel,orderDO);
        orderDO.setItemPrice(orderModel.getItemPrice().doubleValue());
        orderDO.setOrderPrice(orderModel.getOrderPrice().doubleValue());
        return orderDO;
    }
}
