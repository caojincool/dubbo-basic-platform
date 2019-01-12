package com.basic.framework.security.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @date 2017年6月22日 下午4:17:07
 * 
 * @Description: 在线用户信息
 *
 */
public class OnlineUser implements Serializable {

	private static final long serialVersionUID = -16099981467212410L;
	
	private String sessionId;//sessionId
	private String userName;//账号名称
	private String userAccount;//账号
	private Date creationTime;//登录时间
	private Date lastAccessedTime;//最后访问时间
	private String springSecurityLastException;//错误信息
	private Integer maxInactiveInterval;//超时时间，秒为单位
//    private String sessionId;//sessionId
//    private boolean isExpired;//是否限制登录
	
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
	public Date getLastAccessedTime() {
		return lastAccessedTime;
	}
	public void setLastAccessedTime(Date lastAccessedTime) {
		this.lastAccessedTime = lastAccessedTime;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public Date getCreationTime() {
		return creationTime;
	}
	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}
	public String getSpringSecurityLastException() {
		return springSecurityLastException;
	}
	public void setSpringSecurityLastException(String springSecurityLastException) {
		this.springSecurityLastException = springSecurityLastException;
	}
	public Integer getMaxInactiveInterval() {
		return maxInactiveInterval;
	}
	public void setMaxInactiveInterval(Integer maxInactiveInterval) {
		this.maxInactiveInterval = maxInactiveInterval;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	
}
