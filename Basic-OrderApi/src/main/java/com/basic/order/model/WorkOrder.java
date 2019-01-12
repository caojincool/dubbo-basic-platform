package com.basic.order.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @date 2017年8月17日 下午1:08:33
 * 
 * @Description: 流程任务单
 *
 */
public class WorkOrder implements Serializable {

	private static final long serialVersionUID = 6166360005101053781L;

	private Long workOrderId;

    private Long orderId;

    private Long tacheId;

    private String workOrderCode;

    private Long taskId;

    private String tacheCode;

    private Long partyId;

    private String partyType;

    private String partyName;

    private String workOrderState;
    private String workOrderStateName;//任务单状态名称
    
    private Date stateTime;

    private Date createTime;

    private Date alterTime;

    private Date overTime;

    private String workOrderType;

    private Long finishUserId;

    private String finishUserText;

    private Long finishOrgId;

    private String finishOrgName;

    private Date finishTime;

    private Date finishBusiTime;

    private Long costTime;

    private Long costWorkTime;

    private String workOrderComments;

    private String errorCode;

    private String errInfo;

    
    
    private String orderCode;
    private String orderTitle;
    private Long serviceId;
    private String serviceCode;
    private String serviceName;
    private String orderState;
    private String orderStateName;//单据状态名称
    private String orderType;
    private String orderTypeName;
    private Long createUserId;
    private String createUserText;
    
    private String tacheName;
    
    
    
    public String getTacheName() {
		return tacheName;
	}

	public void setTacheName(String tacheName) {
		this.tacheName = tacheName;
	}

	public String getOrderCode() {
		return orderCode;
	}

	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}

	public String getOrderTitle() {
		return orderTitle;
	}

	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}

	public Long getServiceId() {
		return serviceId;
	}

	public void setServiceId(Long serviceId) {
		this.serviceId = serviceId;
	}

	public String getServiceCode() {
		return serviceCode;
	}

	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	public String getOrderState() {
		return orderState;
	}

	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}

	public String getOrderStateName() {
		return orderStateName;
	}

	public void setOrderStateName(String orderStateName) {
		this.orderStateName = orderStateName;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getOrderTypeName() {
		return orderTypeName;
	}

	public void setOrderTypeName(String orderTypeName) {
		this.orderTypeName = orderTypeName;
	}

	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public String getCreateUserText() {
		return createUserText;
	}

	public void setCreateUserText(String createUserText) {
		this.createUserText = createUserText;
	}

	public Long getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(Long workOrderId) {
        this.workOrderId = workOrderId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getTacheId() {
        return tacheId;
    }

    public void setTacheId(Long tacheId) {
        this.tacheId = tacheId;
    }

    public String getWorkOrderCode() {
        return workOrderCode;
    }

    public void setWorkOrderCode(String workOrderCode) {
        this.workOrderCode = workOrderCode;
    }

    public Long getTaskId() {
        return taskId;
    }

    public void setTaskId(Long taskId) {
        this.taskId = taskId;
    }

    public String getTacheCode() {
        return tacheCode;
    }

    public void setTacheCode(String tacheCode) {
        this.tacheCode = tacheCode;
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

    public String getWorkOrderState() {
        return workOrderState;
    }

    public void setWorkOrderState(String workOrderState) {
        this.workOrderState = workOrderState;
    }

    public Date getStateTime() {
        return stateTime;
    }

    public void setStateTime(Date stateTime) {
        this.stateTime = stateTime;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getAlterTime() {
        return alterTime;
    }

    public void setAlterTime(Date alterTime) {
        this.alterTime = alterTime;
    }

    public Date getOverTime() {
        return overTime;
    }

    public void setOverTime(Date overTime) {
        this.overTime = overTime;
    }

    public String getWorkOrderType() {
        return workOrderType;
    }

    public void setWorkOrderType(String workOrderType) {
        this.workOrderType = workOrderType;
    }

    public Long getFinishUserId() {
        return finishUserId;
    }

    public void setFinishUserId(Long finishUserId) {
        this.finishUserId = finishUserId;
    }

    public String getFinishUserText() {
        return finishUserText;
    }

    public void setFinishUserText(String finishUserText) {
        this.finishUserText = finishUserText;
    }

    public Long getFinishOrgId() {
        return finishOrgId;
    }

    public void setFinishOrgId(Long finishOrgId) {
        this.finishOrgId = finishOrgId;
    }

    public String getFinishOrgName() {
        return finishOrgName;
    }

    public void setFinishOrgName(String finishOrgName) {
        this.finishOrgName = finishOrgName;
    }

    public Date getFinishTime() {
        return finishTime;
    }

    public void setFinishTime(Date finishTime) {
        this.finishTime = finishTime;
    }

    public Date getFinishBusiTime() {
        return finishBusiTime;
    }

    public void setFinishBusiTime(Date finishBusiTime) {
        this.finishBusiTime = finishBusiTime;
    }

    public Long getCostTime() {
        return costTime;
    }

    public void setCostTime(Long costTime) {
        this.costTime = costTime;
    }

    public Long getCostWorkTime() {
        return costWorkTime;
    }

    public void setCostWorkTime(Long costWorkTime) {
        this.costWorkTime = costWorkTime;
    }

    public String getWorkOrderComments() {
        return workOrderComments;
    }

    public void setWorkOrderComments(String workOrderComments) {
        this.workOrderComments = workOrderComments;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrInfo() {
        return errInfo;
    }

    public void setErrInfo(String errInfo) {
        this.errInfo = errInfo;
    }

	public String getWorkOrderStateName() {
		return workOrderStateName;
	}

	public void setWorkOrderStateName(String workOrderStateName) {
		this.workOrderStateName = workOrderStateName;
	}
}