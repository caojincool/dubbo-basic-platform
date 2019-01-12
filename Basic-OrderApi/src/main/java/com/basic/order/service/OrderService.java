package com.basic.order.service;

import java.util.List;

import com.basic.order.bean.OrderIbean;
import com.basic.order.model.Order;

/**
 * 
 *
 * @date 2017年8月17日 上午11:46:50
 * 
 * @Description: 流程单据
 *
 */
public interface OrderService {
	
	 int updateByPrimaryKeySelective(Order record);
	
	/**
	 * 
	 * @date 2017年8月17日 上午11:47:07
	 * 
	 * @Description: 根据主键删除
	 * @param orderId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long orderId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:09
	 * 
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(Order record);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:19
	 * 
	 * @Description: 根据主键查询
	 * @param orderId
	 * @return
	 *
	 */
	public Order qryByPrimaryKey(Long orderId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:34
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(Order record);
	
    /**
     * 
     * @date 2017年9月4日 下午3:59:35
     * 
     * @Description: 根据参数查询
     * @param ibean
     * @return
     *
     */
    public List<Order> qryOrderList(OrderIbean ibean);
    
    /**
     * 
     * @date 2017年9月4日 下午4:00:06
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long qryOrderListCount(OrderIbean ibean);
    
    /**
     * 
     * @date 2017年9月11日 上午10:25:24
     * 
     * @Description: 根据单据id查询单据详情
     * @param orderId
     * @return
     *
     */
    public Order qryByOrderId(Long orderId);
    
}
