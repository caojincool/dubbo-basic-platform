package com.basic.oaas.model;

import java.io.Serializable;
import java.util.Date;

public class User implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private Long userId;

    private String userText;

    private String userPassword;

    private String username;

    private Date expireTime;

    private String state;

    private Long createUserId;

    private Date createTime;

    private Long modifyUserId;

    private Date modifyTime;

    private String remarks;
    
    private String createUserName;  // 创建用户名
    
    private String modifyUserName;	// 修改用户名
    
    private String newPassword;
    
    private Integer userType; //帐号类型，0-内部帐号，1-外部帐号
    
    private Integer source;
    
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
    
    
    private Long accountSetId;//账套id，供查询使用
    
    private String currentIp;//当前登陆ip 供查询使用
    
    /**
     * 数据权限豁免
     */
    private Integer dataPrivExemption;
    
    public Integer getDataPrivExemption() {
		return dataPrivExemption;
	}

	public void setDataPrivExemption(Integer dataPrivExemption) {
		this.dataPrivExemption = dataPrivExemption;
	}

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

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getModifyUserName() {
		return modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Integer getUserType() {
		return userType;
	}

	public void setUserType(Integer userType) {
		this.userType = userType;
	}

	public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getUserText() {
        return userText;
    }

    public void setUserText(String userText) {
        this.userText = userText;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Date getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(Date expireTime) {
        this.expireTime = expireTime;
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
	 * @return the newPassword
	 */
	public String getNewPassword() {
		return newPassword;
	}

	/**
	 * @param newPassword the newPassword to set
	 */
	public void setNewPassword(String newPassword) {
		this.newPassword = newPassword;
	}

	
	public Long getAccountSetId() {
		return accountSetId;
	}

	
	public void setAccountSetId( Long accountSetId ) {
		this.accountSetId = accountSetId;
	}

	
	public String getCurrentIp() {
		return currentIp;
	}

	
	public void setCurrentIp( String currentIp ) {
		this.currentIp = currentIp;
	}

	
}