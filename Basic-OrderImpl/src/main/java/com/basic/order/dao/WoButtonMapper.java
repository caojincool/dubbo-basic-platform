package com.basic.order.dao;

import com.basic.order.model.WoButton;

public interface WoButtonMapper {
    int deleteByPrimaryKey(Long woButtonId);

    int insertSelective(WoButton record);

    WoButton selectByPrimaryKey(Long woButtonId);

    int updateByPrimaryKeySelective(WoButton record);
}