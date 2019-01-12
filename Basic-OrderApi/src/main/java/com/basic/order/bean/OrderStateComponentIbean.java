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
public class OrderStateComponentIbean extends PageBean implements Serializable {


	/**
	 * 承接表PF_ORDER_STATE_COMPONENT 部分数据
	 */
	private static final long serialVersionUID = 1L;

	private String componentCode;

    private String componentName;

	/**
	 * @return the componentCode
	 */
	public String getComponentCode() {
		return componentCode;
	}

	/**
	 * @param componentCode the componentCode to set
	 */
	public void setComponentCode(String componentCode) {
		this.componentCode = componentCode;
	}

	/**
	 * @return the componentName
	 */
	public String getComponentName() {
		return componentName;
	}

	/**
	 * @param componentName the componentName to set
	 */
	public void setComponentName(String componentName) {
		this.componentName = componentName;
	}
	    

    
}