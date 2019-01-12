package com.basic.order.service;

import java.util.List;

import com.basic.order.bean.OrderStateComponentIbean;
import com.basic.order.model.OrderStateComponent;

/**
 * 
 *
 * @date 2017年8月17日 上午11:46:50
 * 
 * @Description: 单据状态变更组件
 *
 */
public interface OrderStateComponentService {
	
	/**
	 * 
	 * @date 2017年8月17日 上午11:47:07
	 * 
	 * @Description: 根据主键删除
	 * @param componentId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long componentId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:09
	 * 
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(OrderStateComponent record);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:19
	 * 
	 * @Description: 根据主键查询
	 * @param componentId
	 * @return
	 *
	 */
	public OrderStateComponent qryByPrimaryKey(Long componentId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:34
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(OrderStateComponent record);
	
    /**
     * 
     * @date 2017年8月21日 下午12:09:38
     * 
     * @Description: 根据状态查询有效的组件，按升序
     * @param orderState
     * @return
     *
     */
    public List<OrderStateComponent> qryByOrderState(String orderState);

	/**
	 * @date 2017年9月5日 下午5:39:55
	 * @author wangkui
	 * @Description: 根据参数查询
	 * @param ibean
	 * @return
	 * 
	 */
	public List<OrderStateComponent> qryOrderStateComponentList(OrderStateComponentIbean ibean);

	/**
	 * @date 2017年9月5日 下午5:41:23
	 * @author wangkui
	 * @Description: 根据参数查询的总数
	 * @param ibean
	 * @return
	 * 
	 */
	public long qryOrderStateComponentListCount(OrderStateComponentIbean ibean);

	/**
	 * @date 2017年9月5日 下午5:42:11
	 * @author wangkui
	 * @Description: 
	 * @param ibean
	 * 
	 */
	public void createOrModify(OrderStateComponent ibean);

	/**
	 * @date 2017年9月5日 下午5:47:39
	 * @author wangkui
	 * @Description: 
	 * @param ibean
	 * 
	 */
	public int modifyBatchStateByComponentIds(OrderStateComponent ibean);
}
