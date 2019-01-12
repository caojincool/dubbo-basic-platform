package com.basic.order.bean;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年8月18日 下午2:38:40
 * 
 * @Description: 任务转派参数
 *
 */
public class WorkOrderDisp implements Serializable {

	private static final long serialVersionUID = 2646190829412524712L;
	
	private Long workOrderId;//任务单标识//必填
    private Long operUserId;//操作人标识//必填
    private String operUserText;//操作人名称//必填
    private String operComments;//操作说明//非必填
    private Long partyId;//派单目标标识//必填
    private String partyName;//派单目标名称//必填
    private String partyType;//派单目标类型//必填
    
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
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getPartyType() {
		return partyType;
	}
	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
    
}