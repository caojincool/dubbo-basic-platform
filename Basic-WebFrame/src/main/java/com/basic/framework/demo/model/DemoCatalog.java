package com.basic.framework.demo.model;

import java.io.Serializable;
import java.util.List;

public class DemoCatalog extends TreeBean  implements Serializable {
 
	private static final long serialVersionUID = 1600804438578671247L;


    private String catalogName;

    private Long privateId;
    
    private List<Object> childObj;

	/**
	 * @return the catalogName
	 */
	public String getCatalogName() {
		return catalogName;
	}

	/**
	 * @param catalogName the catalogName to set
	 */
	public void setCatalogName(String catalogName) {
		this.catalogName = catalogName;
	}

	/**
	 * @return the privateId
	 */
	public Long getPrivateId() {
		return privateId;
	}

	/**
	 * @param privateId the privateId to set
	 */
	public void setPrivateId(Long privateId) {
		this.privateId = privateId;
	}

	/**
	 * @return the childObj
	 */
	public List<Object> getChildObj() {
		return childObj;
	}

	/**
	 * @param childObj the childObj to set
	 */
	public void setChildObj(List<Object> childObj) {
		this.childObj = childObj;
	}


    
    
}