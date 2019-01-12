package com.basic.order.bean;

import java.io.Serializable;
import java.util.Map;

/**
 * 
 *
 * @date 2017年8月17日 下午1:05:20
 * 
 * @Description: 启动流程参数
 *
 */
public class ProcessStartIbean implements Serializable {

	private static final long serialVersionUID = -5614722717302890588L;
	
	private Long orderId;//单据标识//必填
    private Long operUserId;//操作人标识//必填
    private String operUserText;//操作人名称//必填
    private String operComments;//操作说明//非必填
    
    private Map<String,Object> paramMap;//流程参数//非必填
    
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getOperUserId() {
		return operUserId;
	}
	public void setOperUserId(Long operUserId) {
		this.operUserId = operUserId;
	}
	public String getOperUserText() {
		return operUserText;
	}
	public void setOperUserText(String operUserText) {
		this.operUserText = operUserText;
	}
	public String getOperComments() {
		return operComments;
	}
	public void setOperComments(String operComments) {
		this.operComments = operComments;
	}
	public Map<String,Object> getParamMap() {
		return paramMap;
	}
	public void setParamMap(Map<String,Object> paramMap) {
		this.paramMap = paramMap;
	}
    
}