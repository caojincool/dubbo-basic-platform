package com.basic.oaas.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @date 2017年9月7日 下午2:15:06
 * @author Kevin
 * @Description: 数据权限表
 *
 */
public class PrivateData implements Serializable {
	
	private static final long serialVersionUID = -9090677445572898344L;

	private Long dataId;

    private String scopeType;

    private String dataCode;

    private String dataName;

    private String state;

    private Long createUserId;

    private Date createTime;

    private Long modifyUserId;

    private Date modifyTime;

    private String remarks;
    
    private String createUserName; //创建用户
    
    private String modifyUserName; //修改用户名
    
    private String dataURL;  //数据来源URL
    
    private String scopeTypeName; // 范围类型名称
    
    private Long dataGroupInstId; //数据分组实例Id
    
    private Long dataInstId; 	//数据实例Id
    
    private String dataSource; //数据来源

    public Long getDataId() {
        return dataId;
    }

    public void setDataId(Long dataId) {
        this.dataId = dataId;
    }

    public String getScopeType() {
        return scopeType;
    }

    public void setScopeType(String scopeType) {
        this.scopeType = scopeType;
    }

    public String getDataCode() {
        return dataCode;
    }

    public void setDataCode(String dataCode) {
        this.dataCode = dataCode;
    }

    public String getDataName() {
        return dataName;
    }

    public void setDataName(String dataName) {
        this.dataName = dataName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getModifyUserId() {
        return modifyUserId;
    }

    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

	/**
	 * @return the createUserName
	 */
	public String getCreateUserName() {
		return createUserName;
	}

	/**
	 * @param createUserName the createUserName to set
	 */
	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	/**
	 * @return the modifyUserName
	 */
	public String getModifyUserName() {
		return modifyUserName;
	}

	/**
	 * @param modifyUserName the modifyUserName to set
	 */
	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
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

	/**
	 * @return the dataGroupInstId
	 */
	public Long getDataGroupInstId() {
		return dataGroupInstId;
	}

	/**
	 * @param dataGroupInstId the dataGroupInstId to set
	 */
	public void setDataGroupInstId(Long dataGroupInstId) {
		this.dataGroupInstId = dataGroupInstId;
	}

	/**
	 * @return the dataInstId
	 */
	public Long getDataInstId() {
		return dataInstId;
	}

	/**
	 * @param dataInstId the dataInstId to set
	 */
	public void setDataInstId(Long dataInstId) {
		this.dataInstId = dataInstId;
	}

	/**
	 * @return the dataSource
	 */
	public String getDataSource() {
		return dataSource;
	}

	/**
	 * @param dataSource the dataSource to set
	 */
	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	
	
    
}