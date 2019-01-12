package com.basic.oaas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.basic.framework.common.model.Tree;

/**
 * 
 *
 * @date 2017年8月31日 上午9:21:07
 * @author Kevin
 * @Description:角色目录表
 *
 */
public class RoleCatalog implements Serializable,Tree{
	private static final long serialVersionUID = 1716169534512711030L;

	private Long catalogId;

    private String catalogName;

    private Long parentCatalogId;

    private String state;

    private String idPath;

    private Long createUserId;

    private Long modifyUserId;

    private Date modifyTime;

    private Date createTime;
    
    private String createUserName;
    
    private String modifyUserName;
    
    private List<RoleCatalog> children = new ArrayList<RoleCatalog>();
    
    /**
     * 是否父节点
     */
    private boolean isParent;
    
    public boolean isIsParent() {
		return isParent;
	}
    
    public void setParent(boolean isParent) {
		this.isParent = isParent;
	}
    
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

	@Override
	public Long getId() {
		return this.catalogId;
	}

	@Override
	public Long getParentId() {
		return this.parentCatalogId;
	}

	@Override
	public String getText() {
		return this.catalogName;
	}

	@Override
	public List getChildren() {
		return this.children;
	}

	@Override
	public void setChildren(List<Tree> list) {
		this.children = this.children;
		
	}

	@Override
	public String toString() {
		return "RoleCatalog [catalogId=" + catalogId + ", catalogName=" + catalogName + ", parentCatalogId="
				+ parentCatalogId + ", state=" + state + ", idPath=" + idPath + ", createUserId=" + createUserId
				+ ", modifyUserId=" + modifyUserId + ", modifyTime=" + modifyTime + ", createTime=" + createTime
				+ ", createUserName=" + createUserName + ", modifyUserName=" + modifyUserName + ", children=" + children
				+ "]";
	}
    
}