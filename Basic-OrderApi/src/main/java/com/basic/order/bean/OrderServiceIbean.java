package com.basic.order.bean;

import java.io.Serializable;

import com.basic.framework.common.model.PageBean;

/**
 * 
 *
 * @date 2017年9月7日 上午9:06:28
 * @author lihaijun
 * @Description: 服务查询bean
 *
 */
public class OrderServiceIbean extends PageBean implements Serializable {
	
	private static final long serialVersionUID = 7319779949852713390L;

	private Long serviceCode;
	
	private String serviceName;
	
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	public Long getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(Long serviceCode) {
		this.serviceCode = serviceCode;
	}

	

}