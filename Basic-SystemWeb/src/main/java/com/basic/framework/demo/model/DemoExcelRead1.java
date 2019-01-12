package com.basic.framework.demo.model;

import java.io.Serializable;
import java.util.Date;


public class DemoExcelRead1 implements Serializable{

	private static final long serialVersionUID = -2039283793344844217L;
	
	private Long index;
	private String productInstCode;
	private Date createTime;

	public Long getIndex() {
		return index;
	}

	public void setIndex(Long index) {
		this.index = index;
	}

	public String getProductInstCode() {
		return productInstCode;
	}

	public void setProductInstCode(String productInstCode) {
		this.productInstCode = productInstCode;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

}
