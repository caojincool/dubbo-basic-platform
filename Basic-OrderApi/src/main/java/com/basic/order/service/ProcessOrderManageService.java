package com.basic.order.service;

import com.basic.order.bean.CreateOrderIbean;
import com.basic.order.bean.ProcessJumpIbean;
import com.basic.order.bean.ProcessStartIbean;

/**
 * 
 *
 * @date 2017年8月17日 下午2:30:35
 * 
 * @Description: 流程单据服务
 *
 */
public interface ProcessOrderManageService {
	
	/**
	 * 
	 * @date 2017年8月17日 下午2:52:42
	 * 
	 * @Description: 创建流程单据
	 * @param ibean
	 * @return
	 *
	 */
	public Long createOrder(CreateOrderIbean ibean);
	
	/**
	 * 
	 * @date 2017年8月17日 下午5:02:57
	 * 
	 * @Description: 启动流程
	 * @param ibean
	 *
	 */
	public void processStart(ProcessStartIbean ibean) throws Exception;
	
	/**
	 * 
	 * @date 2017年8月18日 上午9:49:22
	 * 
	 * @Description: 流程重置
	 * @param ibean
	 * @throws Exception
	 *
	 */
	public void processReset(ProcessStartIbean ibean) throws Exception;
	
	/**
	 * 
	 * @date 2017年8月18日 上午9:49:22
	 * 
	 * @Description: 流程跳转
	 * @param ibean
	 * @throws Exception
	 *
	 */
	public void processJump(ProcessJumpIbean ibean) throws Exception;
	
	/**
	 * 
	 * @date 2017年8月18日 上午11:33:36
	 * 
	 * @Description: 单据作废
	 * @param ibean
	 * @throws Exception
	 *
	 */
	public void removeOrder(ProcessStartIbean ibean) throws Exception;
	
	/**
	 * 
	 * @date 2017年8月21日 下午12:24:06
	 * 
	 * @Description: 单据状态变更组件调用
	 * @param orderId
	 * @param orderState
	 *
	 */
	public void callOrderStateChgComponent(Long orderId, String orderState);
}
