package com.basic.oaas.model;

import java.io.Serializable;
import java.util.Date;

import com.basic.framework.common.model.TreeBean;

/**
 * 
 *
 * @date 2017年8月8日 上午9:46:52
 * @author Kevin
 * @Description: 权限目录
 *
 */
public class PrivateCatalog extends TreeBean implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long catalogId;

    private String catalogName;

    private Long parentCatalogId;

    private String state;

    private String idPath;

    private Long createUserId;

    private Date createTime;

    private Long modifyUserId;

    private Date modifyTime;
    
    private String createUserName; //创建用户
    
    private String modifyUserName; //修改用户名

    public Long getCatalogId() {
        return catalogId;
    }

    public void setCatalogId(Long catalogId) {
        this.catalogId = catalogId;
    }

    public String getCatalogName() {
        return catalogName;
    }

    public void setCatalogName(String catalogName) {
        this.catalogName = catalogName;
    }

    public Long getParentCatalogId() {
        return parentCatalogId;
    }

    public void setParentCatalogId(Long parentCatalogId) {
        this.parentCatalogId = parentCatalogId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getIdPath() {
        return idPath;
    }

    public void setIdPath(String idPath) {
        this.idPath = idPath;
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
    
}