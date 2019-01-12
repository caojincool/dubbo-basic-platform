package com.basic.framework.demo.model;

import java.io.Serializable;

/**
 * 用于演示缓存的使用
 *
 * @date 2016年9月3日 下午12:56:21
 * @author lzj
 * @Description: 用于演示缓存的使用
 *
 */
public class DemoCacheUser implements Serializable {

	
	private static final long serialVersionUID = 6627324508046170186L;
	
	private Long userId;
	private String userName;
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	
}
