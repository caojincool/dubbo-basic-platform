package com.basic.order.bean;

import java.io.Serializable;

import com.basic.framework.common.model.PageBean;

/**
 * 
 *
 * @date 2017年8月17日 下午1:06:56
 * 
 * @Description: 优先级查询参数
 *
 */
public class OrderPriorityIbean extends PageBean implements Serializable {

	private static final long serialVersionUID = -6602361712758513470L;
	
	private Integer orderPriority;//优先级

    private String orderPriorityName;//优先级名称

	public Integer getOrderPriority() {
		return orderPriority;
	}

	public void setOrderPriority(Integer orderPriority) {
		this.orderPriority = orderPriority;
	}

	public String getOrderPriorityName() {
		return orderPriorityName;
	}

	public void setOrderPriorityName(String orderPriorityName) {
		this.orderPriorityName = orderPriorityName;
	}
    

}