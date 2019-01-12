package com.basic.order.model;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年8月17日 下午1:06:40
 * 
 * @Description: 单据状态变更组件
 *
 */
public class OrderStateComponent implements Serializable {

	private static final long serialVersionUID = -4811987595813647798L;

	private Long componentId;

    private String orderState;
    private String orderStateName;//状态名称
    
    private String componentCode;

    private String componentName;

    private Integer execIndex;

    private String componentState;

    private Long [] componentIds;
    private String pageDateType;//新增：CREATE;修改：UPDATE

	/**
	 * @return the componentIds
	 */
	public Long[] getComponentIds() {
		return componentIds;
	}

	/**
	 * @param componentIds the componentIds to set
	 */
	public void setComponentIds(Long[] componentIds) {
		this.componentIds = componentIds;
	}

	/**
	 * @return the pageDateType
	 */
	public String getPageDateType() {
		return pageDateType;
	}

	/**
	 * @param pageDateType the pageDateType to set
	 */
	public void setPageDateType(String pageDateType) {
		this.pageDateType = pageDateType;
	}

	public Long getComponentId() {
        return componentId;
    }

    public void setComponentId(Long componentId) {
        this.componentId = componentId;
    }

    public String getOrderState() {
        return orderState;
    }

    public void setOrderState(String orderState) {
        this.orderState = orderState;
    }

    public String getComponentCode() {
        return componentCode;
    }

    public void setComponentCode(String componentCode) {
        this.componentCode = componentCode;
    }

    public String getComponentName() {
        return componentName;
    }

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public Integer getExecIndex() {
        return execIndex;
    }

    public void setExecIndex(Integer execIndex) {
        this.execIndex = execIndex;
    }

    public String getComponentState() {
        return componentState;
    }

    public void setComponentState(String componentState) {
        this.componentState = componentState;
    }

	public String getOrderStateName() {
		return orderStateName;
	}

	public void setOrderStateName(String orderStateName) {
		this.orderStateName = orderStateName;
	}
}