package com.basic.framework.common.api.query;

/**
 * <pre> 
 * 描述：查询字段之间的关系枚举。
 * 构建组：x5-base-api
 * 作者：Winston Yan
 * 邮箱：yancm@jee-soft.cn
 * 日期：2014-1-3-上午11:37:18
 * 版权：company
 * </pre>
 */
public enum FieldRelation {
	/**
	 * 与
	 */
	AND("AND"),
	/**
	 * 或
	 */
	OR("OR"),
	/**
	 * 非
	 */
	NOT("NOT");
	private String val;
	FieldRelation(String val) {
			this.val = val;
	}
	public String value(){
		return val;
	}
}
