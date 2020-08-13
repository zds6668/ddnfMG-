package com.dd.ddfgm.miaoshaproject.service;

import com.dd.ddfgm.miaoshaproject.service.model.PromoModel;

/**
 * date 2020-08-13 21:40:59
 */
public interface PromoService {
    //根据itemid获取即将进行的或正在进行的秒杀活动
    PromoModel getPromoByItemId(Integer itemId);
}
