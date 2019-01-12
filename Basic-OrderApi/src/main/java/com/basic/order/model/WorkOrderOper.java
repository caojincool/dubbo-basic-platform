package com.basic.order.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @date 2017年8月17日 下午1:08:46
 * 
 * @Description: 流程任务操作记录
 *
 */
public class WorkOrderOper implements Serializable {

	private static final long serialVersionUID = -3523896799916271294L;

	private Long operId;

    private Long workOrderId;

    private String operType;

    private Long operUserId;

    private String operUserText;

    private Date operTime;

    private String operInfo;

    private String operComments;

    public Long getOperId() {
        return operId;
    }

    public void setOperId(Long operId) {
        this.operId = operId;
    }

    public Long getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(Long workOrderId) {
        this.workOrderId = workOrderId;
    }

    public String getOperType() {
        return operType;
    }

    public void setOperType(String operType) {
        this.operType = operType;
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

    public Date getOperTime() {
        return operTime;
    }

    public void setOperTime(Date operTime) {
        this.operTime = operTime;
    }

    public String getOperInfo() {
        return operInfo;
    }

    public void setOperInfo(String operInfo) {
        this.operInfo = operInfo;
    }

    public String getOperComments() {
        return operComments;
    }

    public void setOperComments(String operComments) {
        this.operComments = operComments;
    }
}