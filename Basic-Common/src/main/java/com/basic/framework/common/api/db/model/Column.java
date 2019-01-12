package com.basic.framework.common.api.db.model;

/**
 * 列对象。 用于产生数据库列。
 * 
 * <pre>
 * 构建组：x5-base-api
 * 作者：hugh zhuang
 * 邮箱:zhuangxh@jee-soft.cn
 * 日期:2014-01-22-上午11:35:40
 * 版权：company
 * </pre>
 */
public interface Column {

	// 字段常用变量
	/** 字符串 */
	String COLUMN_TYPE_VARCHAR = "varchar";
	/** 大文本 */
	String COLUMN_TYPE_CLOB = "clob";
	/** 数字 */
	String COLUMN_TYPE_NUMBER = "number";
	/** 整型 */
	String COLUMN_TYPE_INT = "int";
	/** 日期 */
	String COLUMN_TYPE_DATE = "date";

	/**
	 * 列名
	 * 
	 * @return
	 */
	public String getFieldName();

	/**
	 * 列注释
	 * 
	 * @return
	 */
	public String getComment();

	/**
	 * 是否主键
	 * 
	 * @return
	 */
	public boolean getIsPk();

	/**
	 * 是否可为空
	 * 
	 * @return
	 */
	public boolean getIsNull();

	/**
	 * 列类型
	 * 
	 * @return
	 */
	public String getColumnType();
	
	/**
	 * 列实际类型
	 * 
	 * @return
	 */
	public String getFcolumnType();

	/**
	 * 字符串长度
	 * 
	 * @return
	 */
	public int getCharLen();

	/**
	 * 整数位长度
	 * 
	 * @return
	 */
	public int getIntLen();

	/**
	 * 小数位
	 * 
	 * @return
	 */
	public int getDecimalLen();

	/**
	 * 默认值
	 * 
	 * @return
	 */
	public String getDefaultValue();

	/**
	 * 表名
	 * 
	 * @return
	 */
	public String getTableName();

	/**
	 * 设置 列名
	 * 
	 * @param name
	 */
	public void setFieldName(String name);

	/**
	 * 设置类类型
	 * 
	 * @param columnType
	 */
	public void setColumnType(String columnType);
	/**
	 * 设置类实际类型
	 * 
	 * @param columnType
	 */
	public void setFcolumnType(String columnType);

	/**
	 * 设置列注释
	 * 
	 * @param comment
	 */
	public void setComment(String comment);

	/**
	 * 设置 是否为空
	 * 
	 * @param isNull
	 */
	public void setIsNull(boolean isNull);

	/**
	 * 设置是否是主键
	 * 
	 * @param isPk
	 */
	public void setIsPk(boolean isPk);

	/**
	 * 设置字符串长度
	 * 
	 * @param charLen
	 */
	public void setCharLen(int charLen);

	/**
	 * 设置 整数的长度
	 * 
	 * @param intLen
	 */
	public void setIntLen(int intLen);

	/**
	 * 设置 小数长度
	 * 
	 * @param decimalLen
	 */
	public void setDecimalLen(int decimalLen);

	/**
	 * 默认值
	 * 
	 * @param defaultValue
	 */
	public void setDefaultValue(String defaultValue);

	/**
	 * 表名
	 * 
	 * @param tableName
	 */
	public void setTableName(String tableName);

	int getIsRequired();

	void setIsRequired(int isRequired);

}
