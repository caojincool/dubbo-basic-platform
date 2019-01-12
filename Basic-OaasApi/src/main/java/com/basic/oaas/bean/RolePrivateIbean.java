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

import com.basic.oaas.model.RolePrivate;


/**
 *
 * @date 2017年8月4日 下午5:31:16
 * @author Kevin
 * @Description: 角色权限入参
 * 
 */
public class RolePrivateIbean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long roleId;
	
	private List<RolePrivate> rolePrivates;

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
	 * @return the rolePrivates
	 */
	public List<RolePrivate> getRolePrivates() {
		return rolePrivates;
	}

	/**
	 * @param rolePrivates the rolePrivates to set
	 */
	public void setRolePrivates(List<RolePrivate> rolePrivates) {
		this.rolePrivates = rolePrivates;
	}

}
