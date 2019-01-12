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
public class OrderServicebean extends PageBean implements Serializable {

	
	/**
	 * TODO(这里用一句话描述这个类的作用)
	 */
	private static final long serialVersionUID = 7463229383531349605L;
	private Long serviceCode;
	
	private String serviceName;
	
	/**
	 * @return the serviceName
	 */
	public String getServiceName() {
		return serviceName;
	}
	/**
	 * @param serviceName the serviceName to set
	 */
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	/**
	 * @return the serviceCode
	 */
	public Long getServiceCode() {
		return serviceCode;
	}
	/**
	 * @param serviceCode the serviceCode to set
	 */
	public void setServiceCode(Long serviceCode) {
		this.serviceCode = serviceCode;
	}//单据类型编码

	

}