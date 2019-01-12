package com.basic.oaas.model;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年9月7日 下午4:04:50
 * @author Kevin
 * @Description: 数据权限范围类型
 *
 */
public class DataScopeType  implements Serializable{
	
	private static final long serialVersionUID = -6174515872726897073L;

	private String scopeType;

    private String scopeTypeName;

    private String dataFlag;

    private String dataUrl;

    public String getScopeType() {
        return scopeType;
    }

    public void setScopeType(String scopeType) {
        this.scopeType = scopeType;
    }

    public String getScopeTypeName() {
        return scopeTypeName;
    }

    public void setScopeTypeName(String scopeTypeName) {
        this.scopeTypeName = scopeTypeName;
    }

    public String getDataFlag() {
        return dataFlag;
    }

    public void setDataFlag(String dataFlag) {
        this.dataFlag = dataFlag;
    }

    public String getDataUrl() {
        return dataUrl;
    }

    public void setDataUrl(String dataUrl) {
        this.dataUrl = dataUrl;
    }
}