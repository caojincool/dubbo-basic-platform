package com.basic.framework.processframe.model;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年7月21日 下午4:45:47
 * 
 * @Description: 进程状态
 *
 */
public class ProcessState implements Serializable{

	private static final long serialVersionUID = -8976953094874323681L;
	
	private String key;//业务名称
	private String value;//线程状态
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
