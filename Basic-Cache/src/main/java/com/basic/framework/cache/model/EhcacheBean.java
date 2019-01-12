package com.basic.framework.cache.model;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年8月7日 上午11:01:28
 * 
 * @Description: ehcache
 *
 */
public class EhcacheBean implements Serializable {
	
	private static final long serialVersionUID = 7778151809168197502L;

	private String cacheName;
	
	private int cacheSize;

	public String getCacheName() {
		return cacheName;
	}

	public void setCacheName(String cacheName) {
		this.cacheName = cacheName;
	}

	public int getCacheSize() {
		return cacheSize;
	}

	public void setCacheSize(int cacheSize) {
		this.cacheSize = cacheSize;
	}
	
	
	
}
