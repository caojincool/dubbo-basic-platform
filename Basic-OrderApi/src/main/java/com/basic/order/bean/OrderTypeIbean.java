package com.basic.order.bean;

import java.io.Serializable;

import com.basic.framework.common.model.PageBean;

/**
 * 
 *
 * @date 2017年8月17日 下午1:06:56
 * 
 * @Description: 单据类型查询参数
 *
 */
public class OrderTypeIbean extends PageBean implements Serializable {

	private static final long serialVersionUID = -6602361712758513470L;
	
	private String orderType;//单据类型编码
    private String orderTypeName;//单据类型名称
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public String getOrderTypeName() {
		return orderTypeName;
	}
	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}

}