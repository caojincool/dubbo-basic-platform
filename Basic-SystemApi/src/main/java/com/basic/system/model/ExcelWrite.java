package com.basic.system.model;

import java.io.Serializable;
import java.util.List;

/**
 * Excel导出
 *
 * @date 2016年10月28日 下午1:37:22
 * @author lzj
 * @Description:Excel导出
 *
 */
public class ExcelWrite implements Serializable {

	private static final long serialVersionUID = -5915368639121167615L;

	private int sheetIndex;// 标签页序号
	private String sheetName;// 标签页名称
	private String javaBeanName;// javaBean名称
	private int beginRowIndex;// 开始行号
	
	private String writeType;//写入类型
	
	private List<ExcelWriteCol> colList;
	
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

	public List<ExcelWriteCol> getColList() {
		return colList;
	}

	public void setColList(List<ExcelWriteCol> colList) {
		this.colList = colList;
	}

	public int getBeginRowIndex() {
		return beginRowIndex;
	}

	public void setBeginRowIndex(int beginRowIndex) {
		this.beginRowIndex = beginRowIndex;
	}

	public String getSheetName() {
		return sheetName;
	}

	public void setSheetName(String sheetName) {
		this.sheetName = sheetName;
	}

	public String getWriteType() {
		return writeType;
	}

	public void setWriteType(String writeType) {
		this.writeType = writeType;
	}
	
}
