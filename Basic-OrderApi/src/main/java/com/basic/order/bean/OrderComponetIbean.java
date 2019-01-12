package com.basic.order.bean;

import java.io.Serializable;

import com.basic.framework.common.model.PageBean;

/**
 * 
 *
 * @date 2017年9月4日 上午10:53:09
 * @author wangkui
 * @Description: TODO 组件管理
 *
 */
public class OrderComponetIbean extends PageBean implements Serializable {

	private static final long serialVersionUID = -6602361712758513470L;
	
	private String componentName;//组件名称

    private String  componentCode;//组件编码

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

    
}