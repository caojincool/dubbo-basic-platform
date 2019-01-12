package com.basic.framework.web.model;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年6月28日 下午4:16:41
 * 
 * @Description: 用户详细信息里面的岗位-部门-区域
 *
 */
public class JobOrgArea implements Serializable {

	private static final long serialVersionUID = -9189822637618958027L;

	private Long jobId;//岗位ID
	private String jobCode;//岗位编码
	private String jobName;//岗位名称
	private Integer defaultJob;//默认职位:1 默认职位 0 非默认职位
    
	private Long orgId;//部门标识
	private String orgCode;//部门编码
	private String orgName;//部门名称
	private String orgCodePath;//部门编码路径
	private String orgNamePath;//部门名称路径
	
	private Long areaId;//区域标识
	private String areaCode;//区域编码
	private String areaName;//区域名称
	private String areaCodePath;//区域编码路径
	private String areaNamePath;//区域名称路径
	
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
	public String getOrgCodePath() {
		return orgCodePath;
	}
	public void setOrgCodePath(String orgCodePath) {
		this.orgCodePath = orgCodePath;
	}
	public String getOrgNamePath() {
		return orgNamePath;
	}
	public void setOrgNamePath(String orgNamePath) {
		this.orgNamePath = orgNamePath;
	}
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAreaCodePath() {
		return areaCodePath;
	}
	public void setAreaCodePath(String areaCodePath) {
		this.areaCodePath = areaCodePath;
	}
	public String getAreaNamePath() {
		return areaNamePath;
	}
	public void setAreaNamePath(String areaNamePath) {
		this.areaNamePath = areaNamePath;
	}
	public Integer getDefaultJob() {
		return defaultJob;
	}
	public void setDefaultJob(Integer defaultJob) {
		this.defaultJob = defaultJob;
	}
	
}
