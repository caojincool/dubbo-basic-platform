package com.basic.oaas.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.basic.framework.common.model.TreeBean;

/**
 * 
 *
 * @date 2017年8月29日 上午10:48:07
 * @author Kevin
 * @Description: 菜单目录
 *
 */
public class PrivateMenuCatalog extends TreeBean implements Serializable {
	private static final long serialVersionUID = -6654121945817741505L;

	private Long catalogId;

    private String catalogName;

    private Long parentCatalogId;

    private String state;

    private String idPath;

    private Long createUserId;

    private Date createTime;

    private Long modifyUserId;

    private Date modifyTime;
    
    private String createUserName;
    
    private String modifyUserName;
    
    private String catalogUrl; //新增加一个url
    
    private String imgUrl;
    
    private List<PrivateMenuCatalog> childCatalog; // 目录
    
    private List<PrivateMenu> childMenu; // 菜单
    
    private List<Object> childObj; //子对象
    
    private Long displayIndex; // 显示顺序

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

	/**
	 * @return the childCatalog
	 */
	public List<PrivateMenuCatalog> getChildCatalog() {
		return childCatalog;
	}

	/**
	 * @param childCatalog the childCatalog to set
	 */
	public void setChildCatalog(List<PrivateMenuCatalog> childCatalog) {
		this.childCatalog = childCatalog;
	}

	/**
	 * @return the childMenu
	 */
	public List<PrivateMenu> getChildMenu() {
		return childMenu;
	}

	/**
	 * @param childMenu the childMenu to set
	 */
	public void setChildMenu(List<PrivateMenu> childMenu) {
		this.childMenu = childMenu;
	}

	/**
	 * @return the displayIndex
	 */
	public Long getDisplayIndex() {
		return displayIndex;
	}

	/**
	 * @param displayIndex the displayIndex to set
	 */
	public void setDisplayIndex(Long displayIndex) {
		this.displayIndex = displayIndex;
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

	public String getCatalogUrl() {
		return catalogUrl;
	}

	public void setCatalogUrl(String catalogUrl) {
		this.catalogUrl = catalogUrl;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.catalogName;
	}


}