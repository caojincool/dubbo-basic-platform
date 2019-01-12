package com.basic.order.service;

import com.basic.order.model.OrderButton;

/**
 * 
 *
 * @date 2017年8月17日 上午11:46:50
 * 
 * @Description: 单据按钮
 *
 */
public interface OrderButtonService {
	
	/**
	 * 
	 * @date 2017年8月17日 上午11:47:07
	 * 
	 * @Description: 根据主键删除
	 * @param orderButtonId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long orderButtonId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:09
	 * 
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(OrderButton record);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:19
	 * 
	 * @Description: 根据主键查询
	 * @param orderButtonId
	 * @return
	 *
	 */
	public OrderButton qryByPrimaryKey(Long orderButtonId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:34
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(OrderButton record);
    
}
