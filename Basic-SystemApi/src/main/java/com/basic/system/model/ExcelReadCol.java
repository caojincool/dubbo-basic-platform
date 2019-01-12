package com.basic.system.model;

import java.io.Serializable;

public class ExcelReadCol implements Serializable {

	private static final long serialVersionUID = 1055983103641781808L;
	private int colIndex;
	private String colCode;
	private String colType;
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
}
