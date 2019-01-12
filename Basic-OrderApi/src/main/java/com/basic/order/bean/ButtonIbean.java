package com.basic.order.bean;

import java.io.Serializable;

import com.basic.framework.common.model.PageBean;

/**
 * 
 *
 * @date 2017年9月13日 上午10:37:09
 * 
 * @Description: 按钮查询参数
 *
 */
public class ButtonIbean extends PageBean implements Serializable {

	private static final long serialVersionUID = -5757834237546134048L;
	
	private String orderType;//单据类型编码
	private Long tacheId;//环节标识
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public Long getTacheId() {
		return tacheId;
	}
	public void setTacheId(Long tacheId) {
		this.tacheId = tacheId;
	}
	
}