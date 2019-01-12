
package com.basic.oaas.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @date 2017年8月31日 上午9:20:18
 * @author Kevin
 * @Description: 角色表
 *
 */
public class Role implements Serializable{
	
	private static final long serialVersionUID = -1716294852077464597L;

	private Long roleId;

    private Long catalogId;

    private String roleCode;

    private String roleName;

    private Long displayIndex;

    private String state;

    private Long createUserId;

    private Date createTime;

    private Long modifyUserId;

    private Date modifyTime;

    private String remarks;
    
    private String createUserName;
    
    private String modifyUserName;
    
    private String catalogName;
    
    /**
     * 启用状态，0-不启用，1-启用
     */
    private Integer openStatus;
    
    /**
     * 启用日期
     */
    private Date openDate;
    
    /**
     * 停用日期
     */
    private Date stopDate;

    
    public Integer getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(Integer openStatus) {
		this.openStatus = openStatus;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
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

    public Long getDisplayIndex() {
        return displayIndex;
    }

    public void setDisplayIndex(Long displayIndex) {
        this.displayIndex = displayIndex;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

	/**
	 * @return the createUserName
	 */
	public String getCreateUserName() {
		return createUserName;
	}

	/**
	 * @param createUserName the createUserName to set
	 */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	/**
	 * @return the modifyUserName
	 */
	public String getModifyUserName() {
		return modifyUserName;
	}

	/**
	 * @param modifyUserName the modifyUserName to set
	 */
	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	/**
	 * @return the catalogName
	 */
	public String getCatalogName() {
		return catalogName;
	}

	/**
	 * @param catalogName the catalogName to set
	 */
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Role) {
			Role role = (Role) obj;
			if (this.roleCode.equals(role.getRoleCode())
					&& this.roleId.equals(role.getRoleId())) {
				return true;
			}
		}
		return false;
	}
	
	

	@Override
	public int hashCode() {
		return super.hashCode();
	}

	@Override
	public String toString() {
		return "Role [roleId=" + roleId + ", catalogId=" + catalogId + ", roleCode=" + roleCode + ", roleName="
				+ roleName + ", displayIndex=" + displayIndex + ", state=" + state + ", createUserId=" + createUserId
				+ ", createTime=" + createTime + ", modifyUserId=" + modifyUserId + ", modifyTime=" + modifyTime
				+ ", remarks=" + remarks + ", createUserName=" + createUserName + ", modifyUserName=" + modifyUserName
				+ ", catalogName=" + catalogName + ", openStatus=" + openStatus + ", openDate=" + openDate
				+ ", stopDate=" + stopDate + "]";
	}
    
    
}