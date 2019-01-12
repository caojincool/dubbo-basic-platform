/**
 * 
 */
package com.basic.oaas.define;

/**
 * 
 *
 * @date 2017年8月28日 上午11:52:55
 * @author Kevin
 * @Description: 用户状态
 *
 */
public enum UserState {

	/**
	 * 失效
	 */
	INVALID("10X"),
	/**
	 * 正常
	 */
	NORMAL("10A"),
	/**
	 * 锁定
	 */
	LOCKED("10L");

	private String code;

	// 构造方法
	private UserState(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

}
