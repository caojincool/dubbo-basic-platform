/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月4日 下午5:31:16
 * @author Kevin
 * @Description: 
 * 
 */
package com.basic.oaas.bean;

import java.io.Serializable;
import java.util.List;

import com.basic.oaas.model.UserPrivate;


/**
 *
 * @date 2017年8月4日 下午5:31:16
 * @author Kevin
 * @Description: 账号权限入参
 * 
 */
public class UserPrivateIbean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long userId;
	
	private List<UserPrivate> userPrivates;

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

	/**
	 * @return the userPrivates
	 */
	public List<UserPrivate> getUserPrivates() {
		return userPrivates;
	}

	/**
	 * @param userPrivates the userPrivates to set
	 */
	public void setUserPrivates(List<UserPrivate> userPrivates) {
		this.userPrivates = userPrivates;
	}
	
}
