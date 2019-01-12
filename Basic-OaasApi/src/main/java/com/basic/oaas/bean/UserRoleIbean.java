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

import com.basic.oaas.model.UserRole;


/**
 *
 * @date 2017年8月4日 下午5:31:16
 * @author Kevin
 * @Description: 账号角色入参
 * 
 */
public class UserRoleIbean implements Serializable {

	private static final long serialVersionUID = 2040115622248169464L;

	private Long userId;
	
	private List<UserRole> userRoles;

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
	 * @return the userRoles
	 */
	public List<UserRole> getUserRoles() {
		return userRoles;
	}

	/**
	 * @param userRoles the userRoles to set
	 */
	public void setUserRoles(List<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	
}
