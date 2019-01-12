package com.basic.order.busi;

import com.basic.order.bean.WoDispRuleObean;

/**
 * 
 *
 * @date 2017年8月21日 上午11:39:58
 * 
 * @Description: 自定义派单规则
 *
 */
public interface WoDispRuleComponent {
	
	/**
	 * 
	 * @date 2017年8月21日 上午11:41:03
	 * 
	 * @Description: 自定义派单规则实现
	 * @param orderId
	 * @param orderState
	 *
	 */
	public WoDispRuleObean execComponent(Long orderId, String tacheCode);
}
