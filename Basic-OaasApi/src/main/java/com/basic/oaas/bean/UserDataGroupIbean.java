/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月4日 下午5:31:16
 * @author Kevin
 * @Description: 账号数据权限分组入参
 * 
 */
package com.basic.oaas.bean;

import java.io.Serializable;
import java.util.List;

import com.basic.oaas.model.UserDataGroup;


/**
 *
 * @date 2017年8月4日 下午5:31:16
 * @author Kevin
 * @Description: 账号数据权限分组入参
 * 
 */
public class UserDataGroupIbean implements Serializable {

	private static final long serialVersionUID = 2040115622248169464L;

	private Long userId;
	
	private List<UserDataGroup> userDataGroups;

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
	 * @return the userDataGroups
	 */
	public List<UserDataGroup> getUserDataGroups() {
		return userDataGroups;
	}

	/**
	 * @param userDataGroups the userDataGroups to set
	 */
	public void setUserDataGroups(List<UserDataGroup> userDataGroups) {
		this.userDataGroups = userDataGroups;
	}

	
	
}
