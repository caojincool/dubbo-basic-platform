/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年9月13日 上午10:23:42
 * @author Kevin
 * @Description: 
 * 
 */
package com.basic.oaas.bean;

import java.io.Serializable;

import com.basic.framework.common.model.PageBean;

/**
 *
 * @date 2017年9月13日 上午10:23:42
 * @author Kevin
 * @Description: 数据权限分组
 * 
 */
public class PrivateDataGroupIbean extends PageBean implements Serializable{

	private static final long serialVersionUID = -1040976412003205616L;
	
	private Long userId;

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	
	

}
