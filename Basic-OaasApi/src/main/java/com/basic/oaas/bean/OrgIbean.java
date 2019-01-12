/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月14日 上午10:07:18
 * @author Kevin
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 */
package com.basic.oaas.bean;

import java.io.Serializable;

/**
 *
 * @date 2017年8月14日 上午10:07:18
 * @author Kevin
 * @Description: 部门表入参
 * 
 */
public class OrgIbean implements Serializable {

	private static final long serialVersionUID = -6137197594810981408L;

	private Long orgId;
	
	private Long userId;
	
	private Long parentOrgId;
	
	private String orgType;

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
	 * @return the parentOrgId
	 */
	public Long getParentOrgId() {
		return parentOrgId;
	}

	/**
	 * @param parentOrgId the parentOrgId to set
	 */
	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	/**
	 * @return the orgType
	 */
	public String getOrgType() {
		return orgType;
	}

	/**
	 * @param orgType the orgType to set
	 */
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}
	
	
	
}
