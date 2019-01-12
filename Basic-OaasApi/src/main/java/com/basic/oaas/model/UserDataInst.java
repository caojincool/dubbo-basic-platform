package com.basic.oaas.model;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年9月17日 下午9:23:56
 * @author Kevin
 * @Description: 账号数据权限实例
 *
 */
public class UserDataInst implements Serializable{
	private static final long serialVersionUID = -412773183653420618L;

	private Long userDataInstId;

    private Long userId;

    private Long dataInstId;
    
    private String dataInstName; // 实例名称
    
    private String dataCode; 	//数据权限编码
    
    private String dataName;   	//数据权限名称
    
    private Long dataDataId;    //数据标识

    private String dataDataCode;//数据编码

    private String dataDataName;//数据名称

    private Long parentId;		//数据父标识

    private String scopeType;	//数据范围类型

    private String sourceType;	//数据来源类型

    private Long sourceId; 		//数据Id
    
    private String scopeTypeName; //范围类型名称
    

    public Long getUserDataInstId() {
        return userDataInstId;
    }

    public void setUserDataInstId(Long userDataInstId) {
        this.userDataInstId = userDataInstId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getDataInstId() {
        return dataInstId;
    }

    public void setDataInstId(Long dataInstId) {
        this.dataInstId = dataInstId;
    }

	/**
	 * @return the dataInstName
	 */
	public String getDataInstName() {
		return dataInstName;
	}

	/**
	 * @param dataInstName the dataInstName to set
	 */
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
	 * @return the dataDataId
	 */
	public Long getDataDataId() {
		return dataDataId;
	}

	/**
	 * @param dataDataId the dataDataId to set
	 */
	public void setDataDataId(Long dataDataId) {
		this.dataDataId = dataDataId;
	}

	/**
	 * @return the dataDataCode
	 */
	public String getDataDataCode() {
		return dataDataCode;
	}

	/**
	 * @param dataDataCode the dataDataCode to set
	 */
	public void setDataDataCode(String dataDataCode) {
		this.dataDataCode = dataDataCode;
	}

	/**
	 * @return the dataDataName
	 */
	public String getDataDataName() {
		return dataDataName;
	}

	/**
	 * @param dataDataName the dataDataName to set
	 */
	public void setDataDataName(String dataDataName) {
		this.dataDataName = dataDataName;
	}

	/**
	 * @return the parentId
	 */
	public Long getParentId() {
		return parentId;
	}

	/**
	 * @param parentId the parentId to set
	 */
	public void setParentId(Long parentId) {
		this.parentId = parentId;
	}

	/**
	 * @return the scopeType
	 */
	public String getScopeType() {
		return scopeType;
	}

	/**
	 * @param scopeType the scopeType to set
	 */
	public void setScopeType(String scopeType) {
		this.scopeType = scopeType;
	}

	/**
	 * @return the sourceType
	 */
	public String getSourceType() {
		return sourceType;
	}

	/**
	 * @param sourceType the sourceType to set
	 */
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	/**
	 * @return the sourceId
	 */
	public Long getSourceId() {
		return sourceId;
	}

	/**
	 * @param sourceId the sourceId to set
	 */
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