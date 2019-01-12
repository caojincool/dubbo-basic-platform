package com.basic.system.bean;

import java.io.Serializable;

import com.basic.framework.common.model.PageBean;

/**
 * 
 *
 * @date 2017年8月2日 下午2:24:05
 * 
 * @Description: 字典表的查询参数
 *
 */
public class TableDictIbean extends PageBean implements Serializable {

	private static final long serialVersionUID = -1942235519899562549L;

	private String tableCode;//表编码

    private String tableName;//表名称

    private String colCode;//字段编码

    private String colName;//字段名称

	public String getTableCode() {
		return tableCode;
	}

	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}

	public String getTableName() {
		return tableName;
	}

	public void setTableName(String tableName) {
		this.tableName = tableName;
	}

	public String getColCode() {
		return colCode;
	}

	public void setColCode(String colCode) {
		this.colCode = colCode;
	}

	public String getColName() {
		return colName;
	}

	public void setColName(String colName) {
		this.colName = colName;
	}
    
}
