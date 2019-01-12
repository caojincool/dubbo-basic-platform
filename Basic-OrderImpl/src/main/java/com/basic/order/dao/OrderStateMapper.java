package com.basic.order.dao;

import java.util.List;

import com.basic.order.bean.OrderStateIbean;
import com.basic.order.model.OrderState;

public interface OrderStateMapper {
    int deleteByPrimaryKey(String orderState);

    int insertSelective(OrderState record);

    OrderState selectByPrimaryKey(String orderState);

    int updateByPrimaryKeySelective(OrderState record);
    
    /**
     * 
     * @date 2017年8月30日 下午4:09:59
     * 
     * @Description: 根据参数查询
     * @param ibean
     * @return
     *
     */
    public List<OrderState> selectOrderStateList(OrderStateIbean ibean);
    
    /**
     * 
     * @date 2017年8月30日 下午4:10:08
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long selectOrderStateListCount(OrderStateIbean ibean);
    
    /**
     * 
     * @date 2017年8月30日 下午4:18:13
     * 
     * @Description: 批量修改状态
     * @param ibean
     * @return
     *
     */
    public int updateBatchStateByOrderStates(OrderState ibean);
}