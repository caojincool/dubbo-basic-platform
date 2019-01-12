package com.basic.order.dao;

import java.util.List;

import com.basic.order.bean.OrderStateComponentIbean;
import com.basic.order.model.OrderStateComponent;

public interface OrderStateComponentMapper {
    int deleteByPrimaryKey(Long componentId);

    int insertSelective(OrderStateComponent record);

    OrderStateComponent selectByPrimaryKey(Long componentId);

    int updateByPrimaryKeySelective(OrderStateComponent record);
    
    /**
     * 
     * @date 2017年8月21日 下午12:09:38
     * 
     * @Description: 根据状态查询有效的组件，按升序
     * @param orderState
     * @return
     *
     */
    public List<OrderStateComponent> selectByOrderState(String orderState);

	/**
	 * @date 2017年9月5日 下午6:09:32
	 * @author wangkui
	 * @Description: 根据条件查记录
	 * @param ibean
	 * @return
	 * 
	 */
	public List<OrderStateComponent> selectOrderStateComponentList(OrderStateComponentIbean ibean);

	/**
	 * @date 2017年9月6日 上午9:31:37
	 * @author wangkui
	 * @Description: 查询的总条数
	 * @param ibean
	 * @return
	 * 
	 */
	public long selectOrderStateComponentListCount(OrderStateComponentIbean ibean);

	/**
	 * @date 2017年9月6日 上午9:32:33
	 * @author wangkui
	 * @Description: 单个或者批量删除数据行
	 * @param ibean
	 * @return
	 * 
	 */
	public int updateBatchStateByComponentIds(OrderStateComponent ibean);
}