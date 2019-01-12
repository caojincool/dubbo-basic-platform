package com.basic.order.busi;

/**
 * 
 *
 * @date 2017年8月21日 上午11:39:58
 * 
 * @Description: 单据状态变更组件
 *
 */
public interface OrderStateChgComponent {
	
	/**
	 * 
	 * @date 2017年8月21日 上午11:41:03
	 * 
	 * @Description: 单据状态变更组件实现
	 * @param orderId
	 * @param orderState
	 *
	 */
	public void execComponent(Long orderId, String orderState);
}
