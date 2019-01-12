package com.basic.framework.web.model;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年6月28日 下午4:28:38
 * 
 * @Description: 用户详细信息里面的角色
 *
 */
public class Role implements Serializable {

	private static final long serialVersionUID = -8097462173233268833L;
	
	private Long roleId;//角色id
    private String roleCode;//角色编码
    private String roleName;//角色名称
    
	public Long getRoleId() {
		return roleId;
	}
	public void setRoleId(Long roleId) {
		this.roleId = roleId;
	}
	public String getRoleCode() {
		return roleCode;
	}
	public void setRoleCode(String roleCode) {
		this.roleCode = roleCode;
	}
	public String getRoleName() {
		return roleName;
	}
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
    
}