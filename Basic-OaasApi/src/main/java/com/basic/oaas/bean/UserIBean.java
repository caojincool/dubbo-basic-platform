/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年7月20日 下午5:46:45
 * @author Kevin
 * @Description:
 * 
 */
package com.basic.oaas.bean;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.basic.framework.common.model.PageBean;

/**
 *
 * @date 2017年7月20日 下午5:46:45
 * @author Kevin
 * 
 */
public class UserIBean  extends PageBean  {
	
	private static final long serialVersionUID = -5712175069558138056L;

	private Long orgId;   			//部门Id
	
	private String username;		//登录账号
	
	private String userText;		//账号名称
	
	private Long staffId; // 员工Id
	
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date beginExpireTime;	//账号失效时间
	
	private Date endExpireTime;		//账号失效时间

	/**
	 * @return the orgId
	 */
	public Long getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the userText
	 */
	public String getUserText() {
		return userText;
	}

	/**
	 * @param userText the userText to set
	 */
	public void setUserText(String userText) {
		this.userText = userText;
	}

	/**
	 * @return the beginExpireTime
	 */
	public Date getBeginExpireTime() {
		return beginExpireTime;
	}

	/**
	 * @param beginExpireTime the beginExpireTime to set
	 */
	public void setBeginExpireTime(Date beginExpireTime) {
		this.beginExpireTime = beginExpireTime;
	}

	/**
	 * @return the endExpireTime
	 */
	public Date getEndExpireTime() {
		return endExpireTime;
	}

	/**
	 * @param endExpireTime the endExpireTime to set
	 */
	public void setEndExpireTime(Date endExpireTime) {
		this.endExpireTime = endExpireTime;
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
