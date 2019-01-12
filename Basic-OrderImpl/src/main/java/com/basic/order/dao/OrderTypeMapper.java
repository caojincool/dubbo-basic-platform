package com.basic.order.dao;

import java.util.List;

import com.basic.order.bean.OrderTypeIbean;
import com.basic.order.model.OrderType;

public interface OrderTypeMapper {
    int deleteByPrimaryKey(String orderType);

    int insertSelective(OrderType record);

    OrderType selectByPrimaryKey(String orderType);

    int updateByPrimaryKeySelective(OrderType record);
    
    /**
     * 
     * @date 2017年8月30日 下午4:09:59
     * 
     * @Description: 根据参数查询
     * @param ibean
     * @return
     *
     */
    public List<OrderType> selectOrderTypeList(OrderTypeIbean ibean);
    
    /**
     * 
     * @date 2017年8月30日 下午4:10:08
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long selectOrderTypeListCount(OrderTypeIbean ibean);
    
    /**
     * 
     * @date 2017年8月30日 下午4:18:13
     * 
     * @Description: 批量修改状态
     * @param ibean
     * @return
     *
     */
    public int updateBatchStateByOrderTypes(OrderType ibean);
    
}