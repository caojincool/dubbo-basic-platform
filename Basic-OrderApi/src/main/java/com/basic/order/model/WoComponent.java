package com.basic.order.model;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年8月17日 下午1:07:47
 * 
 * @Description: 任务组件
 *
 */
public class WoComponent implements Serializable {

	private static final long serialVersionUID = 3944967780459491874L;

	private Long componentId;

    private Long tacheId;
    private String tacheName;//环节名称
    
    private String componentCode;

    private String componentName;

    private Integer execIndex;

    private String componentState;

    private String pageDateType;//新增：CREATE;修改：UPDATE
    private Long[] componentIds;
    
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

    public Long getTacheId() {
        return tacheId;
    }

    public void setTacheId(Long tacheId) {
        this.tacheId = tacheId;
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

	public String getTacheName() {
		return tacheName;
	}

	public void setTacheName(String tacheName) {
		this.tacheName = tacheName;
	}

	
}