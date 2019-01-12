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
 * @Description: 数据权限数据入参
 * 
 */
public class PrivateDataDataIbean extends PageBean implements Serializable{

	private static final long serialVersionUID = 4531686136590734507L;

	private String scopeType; //权限范围类型

	/**
	 * @return the scopeType
	 */
	public String getScopeType() {
		return scopeType;
	}

	/**
	 * @param scopeType the scopeType to set
	 */
	public void setScopeType(String scopeType) {
		this.scopeType = scopeType;
	}
	
	
	
}
