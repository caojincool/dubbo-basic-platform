package com.basic.framework.demo.model;

import java.io.Serializable;
import java.util.Date;

public class DemoExcelRead2 implements Serializable{

	private static final long serialVersionUID = 3273256327372357092L;
	
	private Long index;
	private String productInstCode;
	private Integer count;
	
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

	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

}
