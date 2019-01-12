package com.basic.order.service;

import com.basic.order.model.WoButton;

/**
 * 
 *
 * @date 2017年8月17日 上午11:46:50
 * 
 * @Description: 任务按钮
 *
 */
public interface WoButtonService {
	
	/**
	 * 
	 * @date 2017年8月17日 上午11:47:07
	 * 
	 * @Description: 根据主键删除
	 * @param woButtonId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long woButtonId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:09
	 * 
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(WoButton record);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:19
	 * 
	 * @Description: 根据主键查询
	 * @param woButtonId
	 * @return
	 *
	 */
	public WoButton qryByPrimaryKey(Long woButtonId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:34
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(WoButton record);

}
