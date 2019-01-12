package com.basic.oaas.model;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年9月7日 下午2:15:06
 * @author Kevin
 * @Description: 数据权限表
 *
 */
public class PrivateDataInst implements Serializable {
	

	private static final long serialVersionUID = 3700144465731451835L;

	private Long dataInstId;

    private Long dataId;

    private String dataInstName;
    
    private String dataCode;  //数据权限编码

    private String dataName; //数据额权限名
    
    private String dataURL;  //数据来源URL
    
    private String scopeTypeName; // 范围类型名称

    public Long getDataInstId() {
        return dataInstId;
    }

    public void setDataInstId(Long dataInstId) {
        this.dataInstId = dataInstId;
    }

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public String getDataInstName() {
        return dataInstName;
    }

    public void setDataInstName(String dataInstName) {
        this.dataInstName = dataInstName;
    }

	/**
	 * @return the dataCode
	 */
	public String getDataCode() {
		return dataCode;
	}

	/**
	 * @param dataCode the dataCode to set
	 */
	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}

	/**
	 * @return the dataName
	 */
	public String getDataName() {
		return dataName;
	}

	/**
	 * @param dataName the dataName to set
	 */
	public void setDataName(String dataName) {
		this.dataName = dataName;
	}

	/**
	 * @return the dataURL
	 */
	public String getDataURL() {
		return dataURL;
	}

	/**
	 * @param dataURL the dataURL to set
	 */
	public void setDataURL(String dataURL) {
		this.dataURL = dataURL;
	}

	/**
	 * @return the scopeTypeName
	 */
	public String getScopeTypeName() {
		return scopeTypeName;
	}

	/**
	 * @param scopeTypeName the scopeTypeName to set
	 */
	public void setScopeTypeName(String scopeTypeName) {
		this.scopeTypeName = scopeTypeName;
	}
    
    
}