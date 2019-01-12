package com.basic.order.dao;

import java.util.List;

import com.basic.order.bean.OrderPriorityIbean;
import com.basic.order.model.OrderPriority;

public interface OrderPriorityMapper {
    int deleteByPrimaryKey(Integer orderPriority);

    int insertSelective(OrderPriority record);

    OrderPriority selectByPrimaryKey(Integer orderPriority);

    int updateByPrimaryKeySelective(OrderPriority record);
    
    /**
     * 
     * @date 2017年8月30日 下午4:09:59
     * 
     * @Description: 根据参数查询
     * @param ibean
     * @return
     *
     */
    public List<OrderPriority> selectOrderPriorityList(OrderPriorityIbean ibean);
    
    /**
     * 
     * @date 2017年8月30日 下午4:10:08
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long selectOrderPriorityListCount(OrderPriorityIbean ibean);
    
    /**
     * 
     * @date 2017年8月30日 下午4:18:13
     * 
     * @Description: 批量修改状态
     * @param ibean
     * @return
     *
     */
    public int updateBatchStateByOrderPrioritys(OrderPriority ibean);
}