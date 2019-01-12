package com.basic.order.dao;

import java.util.List;

import com.basic.order.bean.OrderServiceIbean;
import com.basic.order.model.OrderService;

public interface OrderServiceMapper {
    int deleteByPrimaryKey(Long serviceId);

    int insertSelective(OrderService record);

    OrderService selectByPrimaryKey(Long serviceId);

    int updateByPrimaryKeySelective(OrderService record);
    
    /**
     * 
     * @date 2017年8月17日 下午3:15:49
     * 
     * @Description: 根据服务编码查询服务
     * @param serviceCode
     * @return
     *
     */
    public OrderService selectByServiceCode(String serviceCode);


	/**
	 * @date 2017年9月5日 下午4:30:39
	 * @author lihaijun
	 * @Description: 批量更新
	 * @param ibean
	 * @return
	 * 
	 */
	int updateBatchStateByServiceIds(OrderService ibean);

	/**
	 * @date 2017年9月5日 下午11:34:36
	 * @author lihaijun
	 * @Description: 查询服务的总数据
	 * @param orderServicebean
	 * @return
	 * 
	 */
	long selectOrderServiceListCount(OrderServiceIbean orderServicebean);

	/**
	 * @date 2017年9月6日 下午2:15:07
	 * @author lihaijun
	 * @Description: 查询服务
	 * @return
	 * 
	 */
	List<OrderService> selectOrderServiceList(OrderServiceIbean orderServicebean);
}