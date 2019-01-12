package com.basic.order.dao;

import com.basic.order.model.OrderButton;

public interface OrderButtonMapper {
    int deleteByPrimaryKey(Long orderButtonId);

    int insertSelective(OrderButton record);

    OrderButton selectByPrimaryKey(Long orderButtonId);

    int updateByPrimaryKeySelective(OrderButton record);
}