package com.basic.order.dao;

import java.util.List;

import com.basic.order.bean.OrderIbean;
import com.basic.order.model.Order;

public interface OrderMapper {
    int deleteByPrimaryKey(Long orderId);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long orderId);

    int updateByPrimaryKeySelective(Order record);
    
    /**
     * 
     * @date 2017年9月4日 下午3:59:35
     * 
     * @Description: 根据参数查询
     * @param ibean
     * @return
     *
     */
    public List<Order> selectOrderList(OrderIbean ibean);
    
    /**
     * 
     * @date 2017年9月4日 下午4:00:06
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long selectOrderListCount(OrderIbean ibean);
    
    /**
     * 
     * @date 2017年9月11日 上午10:25:24
     * 
     * @Description: 根据单据id查询单据详情
     * @param orderId
     * @return
     *
     */
    public Order selectByOrderId(Long orderId);
}