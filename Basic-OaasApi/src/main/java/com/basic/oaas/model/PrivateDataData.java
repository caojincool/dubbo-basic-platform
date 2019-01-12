package com.basic.oaas.model;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年9月8日 上午11:50:13
 * @author Kevin
 * @Description: 数据权限数据
 *
 */
public class PrivateDataData implements Serializable {
	
	private static final long serialVersionUID = -8547043475696140619L;

	private Long dataDataId;

    private String dataDataCode;

    private String dataDataName;

    private Long parentId;

    private String scopeType;

    private String sourceType;

    private Long sourceId; 
    
    private String scopeTypeName; //类型名称

    public Long getDataDataId() {
        return dataDataId;
    }

    public void setDataDataId(Long dataDataId) {
        this.dataDataId = dataDataId;
    }

    public String getDataDataCode() {
        return dataDataCode;
    }

    public void setDataDataCode(String dataDataCode) {
        this.dataDataCode = dataDataCode;
    }

    public String getDataDataName() {
        return dataDataName;
    }

    public void setDataDataName(String dataDataName) {
        this.dataDataName = dataDataName;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public String getScopeType() {
        return scopeType;
    }

    public void setScopeType(String scopeType) {
        this.scopeType = scopeType;
    }

    public String getSourceType() {
        return sourceType;
    }

    public void setSourceType(String sourceType) {
        this.sourceType = sourceType;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
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