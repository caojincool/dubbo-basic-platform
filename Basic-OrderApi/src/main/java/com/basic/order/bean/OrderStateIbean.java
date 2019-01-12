package com.basic.order.bean;

import java.io.Serializable;

import com.basic.framework.common.model.PageBean;

/**
 * 
 *
 * @date 2017年8月17日 下午1:06:56
 * 
 * @Description: 单据状态查询参数
 *
 */
public class OrderStateIbean extends PageBean implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8879965778790000791L;

	private String orderState;//单据状态

    private String orderStateName;//单据状态名称

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getOrderStateName() {
		return orderStateName;
	}

	public void setOrderStateName(String orderStateName) {
		this.orderStateName = orderStateName;
	}
    
    
}