/**
 * 描述：TODO
 * 包名：com.hotent.base.api.query
 * 文件名：QueryOP.java
 * 作者：win-mailto:chensx@jee-soft.cn
 * 日期2014-1-3-上午11:17:00
 *  2014company
 * 
 */
package com.basic.framework.common.api.query;

import java.io.Serializable;

/**
 * <pre> 
 * 描述：查询字段和值的操作类型枚举
 * 构建组：x5-base-api
 * 作者：Winston Yan
 * 邮箱：yancm@jee-soft.cn
 * 日期：2014-1-3-上午11:17:00
 * 版权：company
 * </pre>
 */
public enum QueryOP implements Serializable {
	/**
	 * 等于
	 */
	EQUAL("EQ","=","等于"),
	/**
	 * 等于忽略大小写
	 */
	EQUAL_IGNORE_CASE("EIC","=","等于忽略大小写"),
	/**
	 * 小于
	 */
	LESS("LT","<","小于"),
	/**
	 * 大于
	 */
	GREAT("GT",">","大于"),
	/**
	 * 小于等于
	 */
	LESS_EQUAL("LE","<=","小于等于"),
	/**
	 * 大于等于
	 */
	GREAT_EQUAL("GE",">=","大于等于"),
	/**
	 * 不等于
	 */
	NOT_EQUAL("NE","!=","不等于"),
	/**
	 * 不相似
	 */
	NOT_LIKE("NLK","not like","不相似"),
	/**
	 * 相似
	 */
	LIKE("LK","like","相似"),
	/**
	 * 左相似
	 */
	LEFT_LIKE("LFK","like","左相似"),
	/**
	 * 右相似
	 */
	RIGHT_LIKE("RHK","like","右相似"),
	/**
	 * 为空
	 */
	IS_NULL("ISNULL","is null","为空"),
	/**
	 * 非空
	 */
	NOTNULL("NOTNULL","is not null","非空"),
	/**
	 * 在...中
	 */
	IN("IN","in","在...中"),
	/**
	 * 不在...中
	 */
	NOTIN("NOTIN","not in","不在...中"),
	/**
	 * 在...之间
	 */
	BETWEEN("BETWEEN","between","在...之间");
	private String val;
	private String op;
	private String desc;
	QueryOP(String val,String op,String desc){
		this.val = val;
		this.op =op;
		this.desc =desc;
	}
	public String value(){
		return val;
	}
	public String op(){
		return op;
	}
	public String desc(){
		return desc;
	}
	/**
	 * 根据运算符获取QueryOp
	 * @param op
	 * @return 
	 * QueryOP
	 * @exception 
	 * @since  1.0.0
	 */
	public static QueryOP getByOP(String op){
		for(QueryOP queryOP:values()){
			if(queryOP.op().equals(op)){
				return queryOP;
			}
		}
		return null;
	}
	
	public static QueryOP getByVal(String val){
		for(QueryOP queryOP:values()){
			if(queryOP.val.equals(val)){
				return queryOP;
			}
		}
		return null;
	}
	
}
