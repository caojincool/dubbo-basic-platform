package com.basic.order.bean;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年8月18日 下午2:38:40
 * 
 * @Description: 作废未完成任务参数
 *
 */
public class WorkOrderDel implements Serializable {

	private static final long serialVersionUID = -3929873898071727589L;
	
	private Long orderId;//单据标识//必填
    private Long operUserId;//操作人标识//必填
    private String operUserText;//操作人名称//必填
    private String operComments;//操作说明//非必填
    private String tacheCode;//操作编码//非必填
    
    
	public String getTacheCode() {
		return tacheCode;
	}
	public void setTacheCode(String tacheCode) {
		this.tacheCode = tacheCode;
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
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
    
}