package com.basic.order.bean;

import java.io.Serializable;
import java.util.Date;
import java.util.Map;

/**
 * 
 *
 * @date 2017年8月18日 下午2:38:40
 * 
 * @Description: 任务回单参数
 *
 */
public class WorkOrderFinish implements Serializable {

	private static final long serialVersionUID = 2646190829412524712L;
	
	private Long workOrderId;//任务单标识//必填
    private Long operUserId;//操作人标识//必填
    private String operUserText;//操作人名称//必填
    private Long operOrgId;//操作人组织标识//必填
    private String operOrgName;//操作人组织名称//必填
    private Date operBusiTime;//操作业务时间//必填
    private String operComments;//操作说明//非必填
    
    private Map<String,Object> paramMap;//流程参数//非必填
    
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
	public Long getOperOrgId() {
		return operOrgId;
	}
	public void setOperOrgId(Long operOrgId) {
		this.operOrgId = operOrgId;
	}
	public String getOperOrgName() {
		return operOrgName;
	}
	public void setOperOrgName(String operOrgName) {
		this.operOrgName = operOrgName;
	}
	public Date getOperBusiTime() {
		return operBusiTime;
	}
	public void setOperBusiTime(Date operBusiTime) {
		this.operBusiTime = operBusiTime;
	}
	public Map<String,Object> getParamMap() {
		return paramMap;
	}
	public void setParamMap(Map<String,Object> paramMap) {
		this.paramMap = paramMap;
	}
    
}