package com.basic.system.model;

import java.io.Serializable;

/**
 * Excel导出列描述
 *
 * @date 2016年10月28日 下午1:39:21
 * @author lzj
 * @Description: 
 *
 */
public class ExcelWriteCol implements Serializable {

	private static final long serialVersionUID = 1055983103641781808L;
	private int colIndex;//序号
	private String colCode;//编码
	private String colType;//类型
	private boolean mergeFlag;//合并标识
	
	public int getColIndex() {
		return colIndex;
	}
	public void setColIndex(int colIndex) {
		this.colIndex = colIndex;
	}
	public String getColCode() {
		return colCode;
	}
	public void setColCode(String colCode) {
		this.colCode = colCode;
	}
	public String getColType() {
		return colType;
	}
	public void setColType(String colType) {
		this.colType = colType;
	}
	public boolean isMergeFlag() {
		return mergeFlag;
	}
	public void setMergeFlag(boolean mergeFlag) {
		this.mergeFlag = mergeFlag;
	}
	
	
}
