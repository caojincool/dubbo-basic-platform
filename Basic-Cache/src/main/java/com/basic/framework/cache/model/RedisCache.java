package com.basic.framework.cache.model;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年7月20日 下午5:04:01
 * 
 * @Description: 对应redis服务器上面的key、value，cacheCode是缓存编码
 *
 */
public class RedisCache implements Serializable{

	private static final long serialVersionUID = 2142043004807457044L;
	
	private String cacheCode;
	private String key;
	private String value;
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getCacheCode() {
		return cacheCode;
	}
	public void setCacheCode(String cacheCode) {
		this.cacheCode = cacheCode;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
