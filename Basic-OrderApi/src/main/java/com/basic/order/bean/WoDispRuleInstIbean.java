package com.basic.order.bean;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年8月22日 上午9:40:06
 * 
 * @Description: 查询任务派发规则实例参数
 *
 */
public class WoDispRuleInstIbean implements Serializable {

	private static final long serialVersionUID = 2336721158949374068L;
	
	private Long ruleId;
    private String ruleType;
//    private String conditionId;
    private Long orderId;
    
	public String getRuleType() {
		return ruleType;
	}
	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}
	public Long getRuleId() {
		return ruleId;
	}
	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
	}
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

}