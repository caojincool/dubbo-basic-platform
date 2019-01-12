package com.basic.framework.web.model;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年8月22日 下午5:35:50
 * 
 * @Description: 验证某个表字段的值是否表内唯一，请求参数
 *
 */
public class VerifyUniqueCode implements Serializable{

	private static final long serialVersionUID = 8759467034749026427L;
	
	private String tableCode;//表编码
    private String colCode;//字段名编码
    private String colValue;//字段值
	public String getTableCode() {
		return tableCode;
	}
	public void setTableCode(String tableCode) {
		this.tableCode = tableCode;
	}
	public String getColCode() {
		return colCode;
	}
	public void setColCode(String colCode) {
		this.colCode = colCode;
	}
	public String getColValue() {
		return colValue;
	}
	public void setColValue(String colValue) {
		this.colValue = colValue;
	}
    
}