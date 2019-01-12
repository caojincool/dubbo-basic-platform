package com.basic.order.busi;

/**
 * 
 *
 * @date 2017年8月21日 上午11:39:58
 * 
 * @Description: 环节自动组件
 *
 */
public interface TacheComponent {
	
	/**
	 * 
	 * @date 2017年8月21日 上午11:41:03
	 * 
	 * @Description: 环节自动组件实现
	 * @param orderId
	 * @param orderState
	 *
	 */
	public void execComponent(Long orderId, Long workOrderId, String tacheCode);
}
