package com.basic.order.bean;

import java.io.Serializable;

import com.basic.framework.common.model.PageBean;

/**
 * 
 *
 * @date 2017年9月7日 上午9:06:28
 * @author lihaijun
 * @Description: 流程适配规则bean
 *
 */
public class ProcessRuleIbean extends PageBean implements Serializable {
	

	private static final long serialVersionUID = 3831894125479894831L;
	
	private String serviceCode;
	
	private String processDefineKey;

	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public String getProcessDefineKey() {
		return processDefineKey;
	}
	public void setProcessDefineKey(String processDefineKey) {
		this.processDefineKey = processDefineKey;
	}
	
	
	

	

}