package com.basic.oaas.model;

import java.io.Serializable;
import java.util.Date;


public class Job implements Serializable{
	
	private static final long serialVersionUID = 6477963734398928476L;

	private Long jobId;

    private String jobCode;

    private String jobName;

    private Long orgId;

    private Long displayIndex;

    private String state;

    private Long createUserId;

    private Date createTime;

    private Long modifyUserId;

    private Date modifyTime;

    private String remarks;

    private String jobType;
    
    
    private String createUserName; //创建用户
    
    private String modifyUserName; //修改用户名
    
    
    private String orgName; //部门名称
    
    private String orgCode;//部门编码

    private String orgCodePath;//部门编码路径
    
	private String orgNamePath;//部门名称路径
	
	private Long areaId;//区域标识
	
	private String areaCode;//区域编码
	
	private String areaName;//区域名称
	
	private String areaCodePath;//区域编码路径
	
	private String areaNamePath;//区域名称路径
	
	private Integer defaultJob; //默认职位
	
	private Long parentOrgId; //上级部门
	
	private Long parentAreaId;//上级区域
	
	private Integer source; //来源,1-EIP同步，2-系统维护
	
	/**
     * 同步时间
     */
    private Date syncDate;
	
    
    public Date getSyncDate() {
		return syncDate;
	}

	public void setSyncDate(Date syncDate) {
		this.syncDate = syncDate;
	}

	public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getJobName() {
        return jobName;
    }

    public void setJobName(String jobName) {
        this.jobName = jobName;
    }

    public Long getOrgId() {
        return orgId;
    }

    public void setOrgId(Long orgId) {
        this.orgId = orgId;
    }

    public Long getDisplayIndex() {
        return displayIndex;
    }

    public void setDisplayIndex(Long displayIndex) {
        this.displayIndex = displayIndex;
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

    public String getJobType() {
        return jobType;
    }

    public void setJobType(String jobType) {
        this.jobType = jobType;
    }

	/**
	 * @return the orgName
	 */
	public String getOrgName() {
		return orgName;
	}

	/**
	 * @param orgName the orgName to set
	 */
	public void setOrgName(String orgName) {
		this.orgName = orgName;
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
	 * @return the orgCode
	 */
	public String getOrgCode() {
		return orgCode;
	}

	/**
	 * @param orgCode the orgCode to set
	 */
	public void setOrgCode(String orgCode) {
		this.orgCode = orgCode;
	}

	/**
	 * @return the orgCodePath
	 */
	public String getOrgCodePath() {
		return orgCodePath;
	}

	/**
	 * @param orgCodePath the orgCodePath to set
	 */
	public void setOrgCodePath(String orgCodePath) {
		this.orgCodePath = orgCodePath;
	}

	/**
	 * @return the orgNamePath
	 */
	public String getOrgNamePath() {
		return orgNamePath;
	}

	/**
	 * @param orgNamePath the orgNamePath to set
	 */
	public void setOrgNamePath(String orgNamePath) {
		this.orgNamePath = orgNamePath;
	}

	/**
	 * @return the areaId
	 */
	public Long getAreaId() {
		return areaId;
	}

	/**
	 * @param areaId the areaId to set
	 */
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}

	/**
	 * @return the areaCode
	 */
	public String getAreaCode() {
		return areaCode;
	}

	/**
	 * @param areaCode the areaCode to set
	 */
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
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
	 * @return the areaCodePath
	 */
	public String getAreaCodePath() {
		return areaCodePath;
	}

	/**
	 * @param areaCodePath the areaCodePath to set
	 */
	public void setAreaCodePath(String areaCodePath) {
		this.areaCodePath = areaCodePath;
	}

	/**
	 * @return the areaNamePath
	 */
	public String getAreaNamePath() {
		return areaNamePath;
	}

	/**
	 * @param areaNamePath the areaNamePath to set
	 */
	public void setAreaNamePath(String areaNamePath) {
		this.areaNamePath = areaNamePath;
	}

	/**
	 * @return the defaultJob
	 */
	public Integer getDefaultJob() {
		return defaultJob;
	}

	/**
	 * @param defaultJob the defaultJob to set
	 */
	public void setDefaultJob(Integer defaultJob) {
		this.defaultJob = defaultJob;
	}

	/**
	 * @return the parentOrgId
	 */
	public Long getParentOrgId() {
		return parentOrgId;
	}

	/**
	 * @param parentOrgId the parentOrgId to set
	 */
	public void setParentOrgId(Long parentOrgId) {
		this.parentOrgId = parentOrgId;
	}

	/**
	 * @return the parentAreaId
	 */
	public Long getParentAreaId() {
		return parentAreaId;
	}

	/**
	 * @param parentAreaId the parentAreaId to set
	 */
	public void setParentAreaId(Long parentAreaId) {
		this.parentAreaId = parentAreaId;
	}
    
    
}