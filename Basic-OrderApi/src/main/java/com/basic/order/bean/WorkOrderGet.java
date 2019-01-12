package com.basic.order.bean;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年8月18日 下午2:38:40
 * 
 * @Description: 任务提单参数
 *
 */
public class WorkOrderGet implements Serializable {

	private static final long serialVersionUID = -3929873898071727589L;
	
	private Long workOrderId;//任务单标识//必填
    private Long operUserId;//操作人标识//必填
    private String operUserText;//操作人名称//必填
    private String operComments;//操作说明//非必填
    
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
	public Long getWorkOrderId() {
		return workOrderId;
	}
	public void setWorkOrderId(Long workOrderId) {
		this.workOrderId = workOrderId;
	}
    
}