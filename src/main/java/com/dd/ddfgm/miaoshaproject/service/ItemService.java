package com.dd.ddfgm.miaoshaproject.service;

import com.dd.ddfgm.miaoshaproject.error.BusinessException;
import com.dd.ddfgm.miaoshaproject.service.model.ItemModel;

import java.util.List;

/**
 * date 2020-08-13 21:40:59
 */
public interface ItemService {

    //创建商品
    ItemModel createItem(ItemModel itemModel) throws BusinessException;

    //商品列表浏览
    List<ItemModel> listItem();

    //商品详情浏览
    ItemModel getItemById(Integer id);

    //库存扣减
    boolean decreaseStock(Integer itemId,Integer amount)throws BusinessException;

    //商品销量增加
    void increaseSales(Integer itemId,Integer amount)throws BusinessException;

}
