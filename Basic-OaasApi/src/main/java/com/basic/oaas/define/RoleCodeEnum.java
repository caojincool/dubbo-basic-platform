package com.basic.oaas.define;

/**
 * 
 *
 * @date 2017年4月20日 下午4:57:50
 * 
 * @Description: 角色编码定义
 *
 */
public enum RoleCodeEnum {

	/**
	 * 超级管理员角色
	 */
	SUPERADMINROLE("superadminRole","超级管理员角色"),
	;
	private String code;
	private String comment;
	
	//构造方法
	private RoleCodeEnum(String code, String comment) {
		this.code = code;
		this.comment = comment;
	}
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
}
