package com.basic.framework.demo.model;

import java.io.Serializable;
import java.util.Date;

public class Demo implements Serializable {

	private static final long serialVersionUID = -441725375836369315L;
	
	private String id;
	private String name;
	private Date birthDay;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDay() {
		return birthDay;
	}

	public void setBirthDay(Date birthDay) {
		this.birthDay = birthDay;
	}

}
