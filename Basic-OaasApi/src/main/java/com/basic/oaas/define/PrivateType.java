package com.basic.oaas.define;

/**
 * 
 *
 * @date 2017年8月25日 下午2:11:02
 * @author Kevin
 * @Description: 权限类型
 *
 */
public enum PrivateType {
	
	/**
	 * 菜单权限
	 */
	MENU("MENU","菜单"),
	
	/**
	 * 功能按钮权限
	 */
	FUNC("FUNC","功能按钮"),
	
	/**
	 * 字段属性可见权限
	 */
	ATTR("ATTR","字段属性"),
	
	/**
	 * 特殊权限
	 */
	SPECIAL("SPECIAL","特殊权限"),
	
	/**
	 * 数据权限范围，组织
	 */
	SCOPE_TYPE_ORG("ORG","组织"),
	/**
	 * 数据权限范围，仓库
	 */
	SCOPE_TYPE_WAREHOUSE("WAREHOUSE","仓库"),
	/**
	 * 数据权限范围，机台
	 */
	SCOPE_TYPE_MACHINE("MACHINE","机台"),
	/**
	 * 数据权限范围，生产车间
	 */
	SCOPE_TYPE_PROWORKSHOP("PROWORKSHOP","生产车间")
	;
	
	private String code;
	private String comment;
	
	//构造方法
	private PrivateType(String code, String comment) {
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
