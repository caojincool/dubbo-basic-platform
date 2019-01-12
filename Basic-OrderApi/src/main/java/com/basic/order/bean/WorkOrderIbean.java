package com.basic.order.bean;

import java.io.Serializable;

import com.basic.framework.common.model.PageBean;

/**
 * 
 *
 * @date 2017年8月17日 下午1:05:20
 * 
 * @Description: 流程任务单查询参数
 *
 */
public class WorkOrderIbean extends PageBean implements Serializable {

	private static final long serialVersionUID = 7617898585740706824L;
	
	private Long orderId;//单据id
	private Long workOrderId;//任务id
	
	
	private Long staffId;//员工id
	private Long userId;//用户id
	private Long jobId;//岗位id
	private Long orgId;//部门id
	private String orderCode;//单据编码
	private String workOrderCode;//任务编码
	
	public Long getOrderId() {
		return orderId;
	}
	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}
	public Long getWorkOrderId() {
		return workOrderId;
	}
	public void setWorkOrderId(Long workOrderId) {
		this.workOrderId = workOrderId;
	}
	public Long getStaffId() {
		return staffId;
	}
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public Long getJobId() {
		return jobId;
	}
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	public Long getOrgId() {
		return orgId;
	}
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getWorkOrderCode() {
		return workOrderCode;
	}
	public void setWorkOrderCode(String workOrderCode) {
		this.workOrderCode = workOrderCode;
	}
    
}