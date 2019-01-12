package com.basic.order.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @date 2017年8月17日 下午1:08:56
 * 
 * @Description: 任务单状态
 *
 */
public class WorkOrderState implements Serializable {

	private static final long serialVersionUID = -5955920315690395509L;

	private String workOrderState;

    private String workOrderStateName;

    private String state;

    private Long createUserId;

    private Date createTime;

    private Long modifyUserId;

    private Date modifyTime;

    private String remarks;
    
    private String[] workOrderStates;//多个任务单状态的集合
    private String pageDateType;//新增：CREATE;修改：UPDATE

    public String getWorkOrderState() {
        return workOrderState;
    }

    public void setWorkOrderState(String workOrderState) {
        this.workOrderState = workOrderState;
    }

    public String getWorkOrderStateName() {
        return workOrderStateName;
    }

    public void setWorkOrderStateName(String workOrderStateName) {
        this.workOrderStateName = workOrderStateName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

	public String[] getWorkOrderStates() {
		return workOrderStates;
	}

	public void setWorkOrderStates(String[] workOrderStates) {
		this.workOrderStates = workOrderStates;
	}

	public String getPageDateType() {
		return pageDateType;
	}

	public void setPageDateType(String pageDateType) {
		this.pageDateType = pageDateType;
	}
    
}