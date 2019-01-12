package com.basic.oaas.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.basic.framework.common.model.AbstractModel;
import com.basic.framework.common.model.Tree;
import com.basic.framework.common.model.TreeBean;

/**
 * 
 *
 * @date 2017年8月14日 上午10:05:43
 * @author Kevin
 * @Description: 部门表
 *
 */
public class Org extends AbstractModel<Long> implements Serializable,Tree {
	
	private static final long serialVersionUID = 2L;

	private Long orgId;

    private String orgCode;

    private String orgAbsName;
    
    private String orgEngName;
    
    private String orgName;

    private Long parentOrgId;

    private Long areaId;

    private Long displayIndex;

    private String idPath;

    private String namePath;

    private String codePath;

    private String remarks;
    
    private String orgType;
    
    /**
     * 区域名，非数据库字段
     */
    private String areaName; 	   //所属区域名
    
    /**
     * 公司ID，非数据库字段
     */
    private Long companyId; 	// 公司信息Id
    
    /**
     * 公司，非数据库字段
     */
    private String company;
    
    /**
     * 来源,1-EIP同步，2-系统维护
     */
    private Integer source;
    
    /**
     * 部门管理员
     */
    private String orgAdmin;
    
    /**
     * 部门管理员名称，非数据库字段
     */
    private String orgAdminName;
    
    /**
     * 部门管理员手机，非数据库字段
     */
    private String orgAdminMobile;
    
    /**
     * 部门管理员职务（非岗位），非数据库字段
     */
    private String orgAdminPost;
    
    /**
     * 启用状态，0-不启用，1-启用
     */
    private Integer openStatus;
    
    /**
     * 启用日期
     */
    private Date openDate;
    
    /**
     * 停用日期
     */
    private Date stopDate;
    
    /**
     * 同步时间
     */
    private Date syncDate;
    
    /**
     * 父部门编码，非数据库字段
     */
    private String parentOrgCode;
    
    /**
     * 父部门名称，非数据库字段
     */
    private String parentOrgAbsName;
    
    
    
    /**
     * 子节点
     */
    private List<Org> children = new ArrayList<Org>();
    
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

	public String getOrgEngName() {
		return orgEngName;
	}

	public void setOrgEngName(String orgEngName) {
		this.orgEngName = orgEngName;
	}

	public Date getSyncDate() {
		return syncDate;
	}

	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}

	public String getCompany() {
		return company;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getParentOrgCode() {
		return parentOrgCode;
	}

	public void setParentOrgCode(String parentOrgCode) {
		this.parentOrgCode = parentOrgCode;
	}

	public String getParentOrgAbsName() {
		return parentOrgAbsName;
	}

	public void setParentOrgAbsName(String parentOrgAbsName) {
		this.parentOrgAbsName = parentOrgAbsName;
	}

	public String getOrgAbsName() {
		return orgAbsName;
	}

	public void setOrgAbsName(String orgAbsName) {
		this.orgAbsName = orgAbsName;
	}

	public String getOrgAdmin() {
		return orgAdmin;
	}

	public void setOrgAdmin(String orgAdmin) {
		this.orgAdmin = orgAdmin;
	}

	public String getOrgAdminName() {
		return orgAdminName;
	}

	public void setOrgAdminName(String orgAdminName) {
		this.orgAdminName = orgAdminName;
	}

	public String getOrgAdminMobile() {
		return orgAdminMobile;
	}

	public void setOrgAdminMobile(String orgAdminMobile) {
		this.orgAdminMobile = orgAdminMobile;
	}

	public String getOrgAdminPost() {
		return orgAdminPost;
	}

	public void setOrgAdminPost(String orgAdminPost) {
		this.orgAdminPost = orgAdminPost;
	}

	public Integer getOpenStatus() {
		return openStatus;
	}

	public void setOpenStatus(Integer openStatus) {
		this.openStatus = openStatus;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}

	public Date getStopDate() {
		return stopDate;
	}

	public void setStopDate(Date stopDate) {
		this.stopDate = stopDate;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public String getOrgCode() {
        return orgCode;
    }

    public void setOrgCode(String orgCode) {
        this.orgCode = orgCode;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public Long getParentOrgId() {
        return parentOrgId;
    }

    public void setParentOrgId(Long parentOrgId) {
        this.parentOrgId = parentOrgId;
    }

    public Long getAreaId() {
        return areaId;
    }

    public void setAreaId(Long areaId) {
        this.areaId = areaId;
    }

    public Long getDisplayIndex() {
        return displayIndex;
    }

    public void setDisplayIndex(Long displayIndex) {
        this.displayIndex = displayIndex;
    }

    public String getIdPath() {
        return idPath;
    }

    public void setIdPath(String idPath) {
        this.idPath = idPath;
    }

    public String getNamePath() {
        return namePath;
    }

    public void setNamePath(String namePath) {
        this.namePath = namePath;
    }

    public String getCodePath() {
        return codePath;
    }

    public void setCodePath(String codePath) {
        this.codePath = codePath;
    }

    @Override
    public String getState() {
        return state;
    }

    @Override
    public void setState(String state) {
        this.state = state;
    }

    @Override
    public Long getCreateUserId() {
        return createUserId;
    }

    @Override
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Long getModifyUserId() {
        return modifyUserId;
    }

    @Override
    public void setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
    }

    @Override
    public Date getModifyTime() {
        return modifyTime;
    }

    @Override
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
	 * @return the orgType
	 */
	public String getOrgType() {
		return orgType;
	}

	/**
	 * @param orgType the orgType to set
	 */
	public void setOrgType(String orgType) {
		this.orgType = orgType;
	}

	/**
	 * @return the createUserName
	 */
	@Override
    public String getCreateUserName() {
		return createUserName;
	}

	/**
	 * @param createUserName the createUserName to set
	 */
	@Override
    public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	/**
	 * @return the modifyUserName
	 */
	@Override
    public String getModifyUserName() {
		return modifyUserName;
	}

	/**
	 * @param modifyUserName the modifyUserName to set
	 */
	@Override
    public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}

	/**
	 * @return the areaName
	 */
	public String getAreaName() {
		return areaName;
	}

	/**
	 * @param areaName the areaName to set
	 */
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	/**
	 * @return the companyId
	 */
	public Long getCompanyId() {
		return companyId;
	}

	/**
	 * @param companyId the companyId to set
	 */
	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}

	@Override
	public Long getId() {
		return this.orgId;
	}

	@Override
	public Long getParentId() {
		return this.parentOrgId;
	}

	@Override
	public String getText() {
		return this.orgName;
	}

	@Override
	public List getChildren() {
		return this.children;
	}

	@Override
	public void setChildren(List<Tree> list) {
		this.children = this.children;
	}

    
}