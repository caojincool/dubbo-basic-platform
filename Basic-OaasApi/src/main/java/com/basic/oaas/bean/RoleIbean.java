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
 * @Description: 角色入参
 * 
 */
public class RoleIbean extends PageBean implements Serializable{

	private static final long serialVersionUID = 62790799676402844L;
	
	private Long catalogId;
	
	private Long userId; 
	
	private String roleName;
	
	private boolean authorize; //授权

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
	 * @return the authorize
	 */
	public boolean isAuthorize() {
		return authorize;
	}

	/**
	 * @param authorize the authorize to set
	 */
	public void setAuthorize(boolean authorize) {
		this.authorize = authorize;
	}

	/**
	 * @return the roleName
	 */
	public String getRoleName() {
		return roleName;
	}

	/**
	 * @param roleName the roleName to set
	 */
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}

	
	
}
