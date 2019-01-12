package com.basic.oaas.model;

import java.io.Serializable;

public class UserStaff implements Serializable {
	
	private static final long serialVersionUID = -3539527908614412882L;

	private Long userId;

    private Long staffId;
    
    private String username; //登陆账号
    
    private String userText;  //用户名
    
    private Integer source;//来源,1-EIP同步，2-系统维护
    
    private Integer type;//0 - 员工,1 - 客户,2 - 供应商

    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
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
    
    
}