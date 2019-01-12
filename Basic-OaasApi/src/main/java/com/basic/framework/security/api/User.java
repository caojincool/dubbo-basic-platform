package com.basic.framework.security.api;

import java.io.Serializable;

/**
 * Created by lzj on 2017/6/2.
 */
public class User implements Serializable {

	private static final long serialVersionUID = 3405214249025997597L;
	
	private Long userId;
	private String userName;
	private String userAccount;
    private String userPassword;
    private Long accountSetId;
    private String userText;
    private Integer userType;
    private boolean enables ;//用户账号是否失效 true:生效，false:失效
    private boolean accountNonExpired ;//用户账号是否过期 true:未过期，false:过期
    private boolean credentialsNonExpired ;//用户凭证是否过期 true:未过期，false:过期
    private boolean accountNonLocked ;//用户账号是否锁定 true:未锁定，false:已锁定
    
    private String cIp;//客户端ip
    private String cHostName;//客户端主机名称
    private String cBrowserInfo;//客户端浏览器
//    private String cSystemInfo;//客户端系统
    private String cMacAddress;//客户端MAC地址

    public String getcIp() {
		return cIp;
	}

	public void setcIp(String cIp) {
		this.cIp = cIp;
	}

	public String getcHostName() {
		return cHostName;
	}

	public void setcHostName(String cHostName) {
		this.cHostName = cHostName;
	}

	public String getcBrowserInfo() {
		return cBrowserInfo;
	}

	public void setcBrowserInfo(String cBrowserInfo) {
		this.cBrowserInfo = cBrowserInfo;
	}

	public String getcMacAddress() {
		return cMacAddress;
	}

	public void setcMacAddress(String cMacAddress) {
		this.cMacAddress = cMacAddress;
	}

	public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    
	public Long getAccountSetId() {
		return accountSetId;
	}

	
	public void setAccountSetId( Long accountSetId ) {
		this.accountSetId = accountSetId;
	}

	
	public String getUserText() {
		return userText;
	}

	
	public void setUserText( String userText ) {
		this.userText = userText;
	}

	
	public Integer getUserType() {
		return userType;
	}

	
	public void setUserType( Integer userType ) {
		this.userType = userType;
	}

	public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    public boolean isEnables() {
        return enables;
    }

    public void setEnables(boolean enables) {
        this.enables = enables;
    }

    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
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
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}
    
}
