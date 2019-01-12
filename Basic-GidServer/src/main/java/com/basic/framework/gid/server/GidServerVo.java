/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2015年12月3日 下午3:17:10
 * @author lzj
 * @Description: GidVo
 * 
 */
package com.basic.framework.gid.server;

/**
 *
 * @date 2015年12月3日 下午3:17:10
 * @author lzj
 * @Description: GidVo
 * 
 */
public class GidServerVo {

	private String gidCode;//
	private String gidName;
	private int isUse;// 是否使用 1使用 0不使用
	private long currValue;// 当前值
	private int cacheSize;// 缓存池大小
	private int increamentBy;// 步长
	private int clientCacheSize;//客户端的步长（容量）

	public String getGidCode() {
		return gidCode;
	}

	public void setGidCode(String gidCode) {
		this.gidCode = gidCode;
	}

	public String getGidName() {
		return gidName;
	}

	public void setGidName(String gidName) {
		this.gidName = gidName;
	}

	public int getIsUse() {
		return isUse;
	}

	public void setIsUse(int isUse) {
		this.isUse = isUse;
	}

	public long getCurrValue() {
		return currValue;
	}

	public void setCurrValue(long currValue) {
		this.currValue = currValue;
	}

	public int getCacheSize() {
		return cacheSize;
	}

	public void setCacheSize(int cacheSize) {
		this.cacheSize = cacheSize;
	}

	public int getIncreamentBy() {
		return increamentBy;
	}

	public void setIncreamentBy(int increamentBy) {
		this.increamentBy = increamentBy;
	}

	/**
	 * @return the clientCacheSize
	 */
	public int getClientCacheSize() {
		return clientCacheSize;
	}

	/**
	 * @param clientCacheSize the clientCacheSize to set
	 */
	public void setClientCacheSize(int clientCacheSize) {
		this.clientCacheSize = clientCacheSize;
	}

	
	
}
