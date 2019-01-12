package com.basic.order.dao;

import java.util.List;

import com.basic.order.bean.WorkOrderIbean;
import com.basic.order.model.OrderOper;

public interface OrderOperMapper {
    int deleteByPrimaryKey(Long operId);

    int insertSelective(OrderOper record);

    OrderOper selectByPrimaryKey(Long operId);

    int updateByPrimaryKeySelective(OrderOper record);
    
    /**
     * 
     * @date 2017年9月8日 下午2:24:32
     * 
     * @Description: 根据参数查询
     * @param ibean
     * @return
     *
     */
    public List<OrderOper> selectOrderOperList(WorkOrderIbean ibean);
}