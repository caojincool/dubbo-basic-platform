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
 * @Description: 权限入参
 * 
 */
public class PrivateIbean extends PageBean implements Serializable{

	private static final long serialVersionUID = 62790799676402844L;
	
	private Long catalogId;
	
	private Long userId;
	
	private Long roleId;
	
	private String privateName;
	
	private String privateType;
	
	private Long menuId;

	private boolean authorize;
	
	public Long getMenuId() {
		return menuId;
	}

	public void setMenuId(Long menuId) {
		this.menuId = menuId;
	}

	public String getPrivateType() {
		return privateType;
	}

	public void setPrivateType(String privateType) {
		this.privateType = privateType;
	}

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
	 * @return the roleId
	 */
	public Long getRoleId() {
		return roleId;
	}

	/**
	 * @param roleId the roleId to set
	 */
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
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
	 * @return the privateName
	 */
	public String getPrivateName() {
		return privateName;
	}

	/**
	 * @param privateName the privateName to set
	 */
	public void setPrivateName(String privateName) {
		this.privateName = privateName;
	}
	
	

}
