package com.basic.order.service;

import java.util.List;

import com.basic.order.bean.OrderTypeIbean;
import com.basic.order.model.OrderType;

/**
 * 
 *
 * @date 2017年8月17日 上午11:46:50
 * 
 * @Description: 单据类型
 *
 */
public interface OrderTypeService {
	
	/**
	 * 
	 * @date 2017年8月17日 上午11:47:07
	 * 
	 * @Description: 根据主键删除
	 * @param orderType
	 * @return
	 *
	 */
	public int removeByPrimaryKey(String orderType);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:09
	 * 
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(OrderType record);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:19
	 * 
	 * @Description: 根据主键查询
	 * @param orderType
	 * @return
	 *
	 */
	public OrderType qryByPrimaryKey(String orderType);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:34
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(OrderType record);
	
    /**
     * 
     * @date 2017年8月30日 下午4:09:59
     * 
     * @Description: 根据参数查询
     * @param ibean
     * @return
     *
     */
    public List<OrderType> qryOrderTypeList(OrderTypeIbean ibean);
    
    /**
     * 
     * @date 2017年8月30日 下午4:10:08
     * 
     * @Description: 根据参数查询的总数
     * @param ibean
     * @return
     *
     */
    public long qryOrderTypeListCount(OrderTypeIbean ibean);
    
    /**
     * 
     * @date 2017年8月30日 下午4:18:13
     * 
     * @Description: 批量修改状态
     * @param ibean
     * @return
     *
     */
    public int modifyBatchStateByOrderTypes(OrderType ibean);
    
    /**
     * 
     * @date 2017年8月30日 下午4:21:43
     * 
     * @Description: 处理新增修改操作
     * @param ibean
     *
     */
    public void createOrModify(OrderType ibean);
}
