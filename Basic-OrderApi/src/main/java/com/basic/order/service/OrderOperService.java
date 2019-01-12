package com.basic.order.service;

import java.util.List;

import com.basic.order.bean.WorkOrderIbean;
import com.basic.order.model.OrderOper;

/**
 * 
 *
 * @date 2017年8月17日 上午11:46:50
 * 
 * @Description: 流程单据操作记录
 *
 */
public interface OrderOperService {
	
	/**
	 * 
	 * @date 2017年8月17日 上午11:47:07
	 * 
	 * @Description: 根据主键删除
	 * @param operId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long operId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:09
	 * 
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(OrderOper record);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:19
	 * 
	 * @Description: 根据主键查询
	 * @param operId
	 * @return
	 *
	 */
	public OrderOper qryByPrimaryKey(Long operId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:34
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(OrderOper record);
	
    /**
     * 
     * @date 2017年9月8日 下午2:24:32
     * 
     * @Description: 根据参数查询
     * @param ibean
     * @return
     *
     */
    public List<OrderOper> qryOrderOperList(WorkOrderIbean ibean);
    
}
