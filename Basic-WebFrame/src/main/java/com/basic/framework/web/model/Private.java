package com.basic.framework.web.model;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年6月28日 下午4:28:15
 * 
 * @Description: 用户详细信息里面的权限
 *
 */
public class Private implements Serializable {

	private static final long serialVersionUID = -5505235102947264199L;
	
	private Long privateId;//权限id
    private String privateType;//权限类型
    private String privateCode;//权限编码
    private String privateName;//权限名称
    
	public Long getPrivateId() {
		return privateId;
	}
	public void setPrivateId(Long privateId) {
		this.privateId = privateId;
	}
	public String getPrivateType() {
		return privateType;
	}
	public void setPrivateType(String privateType) {
		this.privateType = privateType;
	}
	public String getPrivateCode() {
		return privateCode;
	}
	public void setPrivateCode(String privateCode) {
		this.privateCode = privateCode;
	}
	public String getPrivateName() {
		return privateName;
	}
	public void setPrivateName(String privateName) {
		this.privateName = privateName;
	}

}