package com.basic.order.bean;

import java.io.Serializable;

import com.basic.framework.common.model.PageBean;

/**
 * 
 *
 * @date 2017年8月17日 下午1:06:56
 * 
 * @Description: 单据状态查询参数
 *
 */
public class WorkOrderStateIbean extends PageBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8325285929990167062L;

	private String workOrderState;//任务单状态

    private String workOrderStateName;//任务单状态名称

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
    
    
	
}