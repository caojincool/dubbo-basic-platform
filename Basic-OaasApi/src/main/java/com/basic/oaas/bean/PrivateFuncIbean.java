/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月8日 上午10:02:04
 * @author Kevin
 * @Description: 
 * 
 */
package com.basic.oaas.bean;

import java.io.Serializable;

import com.basic.framework.common.model.PageBean;

/**
 *
 * @date 2017年8月8日 上午10:02:04
 * @author Kevin
 * @Description: 功能按钮入参
 * 
 */
public class PrivateFuncIbean extends PageBean implements Serializable{

	private static final long serialVersionUID = 62790799676402844L;
	
	private Long catalogId;
	
	private String funcName;

	/**
	 * @return the catalogId
	 */
	public Long getCatalogId() {
		return catalogId;
	}

	/**
	 * @param catalogId the catalogId to set
	 */
	public void setCatalogId(Long catalogId) {
		this.catalogId = catalogId;
	}

	/**
	 * @return the funcName
	 */
	public String getFuncName() {
		return funcName;
	}

	/**
	 * @param funcName the funcName to set
	 */
	public void setFuncName(String funcName) {
		this.funcName = funcName;
	}

	
	
}
