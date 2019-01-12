/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月21日 下午4:55:06
 * @author Kevin
 * @Description: 
 * 
 */
package com.basic.oaas.bean;

import java.io.Serializable;
import java.util.List;

import com.basic.framework.common.model.PageBean;
import com.basic.oaas.model.UserStaff;

/**
 *
 * @date 2017年8月21日 下午4:55:06
 * @author Kevin
 * @Description: 员工账号关系表
 * 
 */
public class UserStaffIbean extends PageBean implements Serializable {

	private static final long serialVersionUID = -89884411025937756L;
	
	private Long staffId;
	
	private Long userId;
	
	private List<UserStaff> userStaffs;

	public List<UserStaff> getUserStaffs() {
		return userStaffs;
	}

	public void setUserStaffs(List<UserStaff> userStaffs) {
		this.userStaffs = userStaffs;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the staffId
	 */
	public Long getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId the staffId to set
	 */
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}
	
	
	
}
