package com.basic.order.service;

import java.util.List;

import com.basic.order.bean.OrderServiceIbean;
import com.basic.order.model.OrderService;

/**
 * 
 *
 * @date 2017年8月17日 上午11:46:50
 * 
 * @Description: 服务
 *
 */
public interface OrderServiceService {
	
	/**
	 * 
	 * @date 2017年8月17日 上午11:47:07
	 * 
	 * @Description: 根据主键删除
	 * @param serviceId
	 * @return
	 *
	 */
	public int removeByPrimaryKey(Long serviceId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:09
	 * 
	 * @Description: 新增一条数据
	 * @param record
	 * @return
	 *
	 */
	public int createSelective(OrderService record);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:19
	 * 
	 * @Description: 根据主键查询
	 * @param serviceId
	 * @return
	 *
	 */
	public OrderService qryByPrimaryKey(Long serviceId);

	/**
	 * 
	 * @date 2017年8月17日 上午11:48:34
	 * 
	 * @Description: 根据主键修改
	 * @param record
	 * @return
	 *
	 */
	public int modifyByPrimaryKeySelective(OrderService record);
	
    /**
     * 
     * @date 2017年8月17日 下午3:15:49
     * 
     * @Description: 根据服务编码查询服务
     * @param serviceCode
     * @return
     *
     */
    public OrderService qryByServiceCode(String serviceCode);
    

	/**
	 * @date 2017年9月5日 下午4:27:55
	 * @author lihaijun
	 * @Description: 批量更新
	 * @param ibean
	 * 
	 */
	public int modifyBatchStateByServiceIds(OrderService ibean);

	/**
	 * @date 2017年9月5日 下午11:33:23
	 * @author lihaijun
	 * @Description: 查询总数
	 * @param orderServicebean
	 * @return
	 * 
	 */
	public long qryOrderServiceListCount(OrderServiceIbean orderServicebean);

	/**
	 * @date 2017年9月6日 上午10:12:05
	 * @author lihaijun
	 * @Description: 添加或更新
	 * @param bean
	 * 
	 */
	public void createOrModify(OrderService bean);


	/**
	 * @date 2017年9月6日 下午2:18:47
	 * @author lihaijun
	 * @Description: 根据自定义查询条件查询服务列表
	 * @param orderServicebean
	 * @return
	 * 
	 */
	List<OrderService> qryOrderServiceList(OrderServiceIbean orderServicebean);

}
