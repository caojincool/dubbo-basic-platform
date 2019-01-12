package com.basic.system.model;

import java.io.Serializable;
import java.util.List;

public class ExcelRead implements Serializable {

	private static final long serialVersionUID = -5915368639121167615L;
	private int sheetIndex;
	private String javaBeanName;
	private int beginRowIndex;
	private List<ExcelReadCol> colList;
	
	public int getSheetIndex() {
		return sheetIndex;
	}
	public void setSheetIndex(int sheetIndex) {
		this.sheetIndex = sheetIndex;
	}
	public String getJavaBeanName() {
		return javaBeanName;
	}
	public void setJavaBeanName(String javaBeanName) {
		this.javaBeanName = javaBeanName;
	}
	public List<ExcelReadCol> getColList() {
		return colList;
	}
	public void setColList(List<ExcelReadCol> colList) {
		this.colList = colList;
	}
	public int getBeginRowIndex() {
		return beginRowIndex;
	}
	public void setBeginRowIndex(int beginRowIndex) {
		this.beginRowIndex = beginRowIndex;
	}
}

