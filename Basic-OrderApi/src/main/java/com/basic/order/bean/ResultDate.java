package com.basic.order.bean;

import java.io.Serializable;
import java.util.List;

public class ResultDate implements Serializable {

	private static final long serialVersionUID = 6454769861578992102L;
			
	
	private String result;
	
	private String message;
	
	List<ResultDate> data;
	
	

	public List<ResultDate> getData() {
		return data;
	}

	public void setData(List<ResultDate> data) {
		this.data = data;
	}

	public String getResult() {
		return result;
	}

	public void setResult(String result) {
		this.result = result;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	
	
}
