package com.basic.system.model;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年8月2日 下午2:18:29
 * 
 * @Description: 字典表
 *
 */
public class TableDict implements Serializable{

	private static final long serialVersionUID = 967511338885954550L;
	
	private Long dictId;//标识
    private String tableCode;//表编码
    private String tableName;//表名(中文)
    private String colCode;//字段名编码
    private String colName;//字段名(中文)
    private String colValue;//字段值
    private String colText;//字段值(中文)
    private String remarks;//备注
    
	public Long getDictId() {
		return dictId;
	}
	public void setDictId(Long dictId) {
		this.dictId = dictId;
	}
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
	public String getColValue() {
		return colValue;
	}
	public void setColValue(String colValue) {
		this.colValue = colValue;
	}
	public String getColText() {
		return colText;
	}
	public void setColText(String colText) {
		this.colText = colText;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}