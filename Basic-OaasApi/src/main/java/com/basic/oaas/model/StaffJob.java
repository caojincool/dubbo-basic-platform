package com.basic.oaas.model;

import java.io.Serializable;

public class StaffJob implements Serializable{
	
	private static final long serialVersionUID = -3188553123242381629L;

	private Long staffJobId;

    private Long jobId;

    private Long staffId;

    private Integer defaultJob;
    
    private String orgName; // 部门名称
    
    private String jobName; //职位名称
    
    private Integer source;//来源,1-EIP同步，2-系统维护

    
    public Integer getSource() {
		return source;
	}

	public void setSource(Integer source) {
		this.source = source;
	}

	public Long getStaffJobId() {
        return staffJobId;
    }

    public void setStaffJobId(Long staffJobId) {
        this.staffJobId = staffJobId;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public Long getStaffId() {
        return staffId;
    }

    public void setStaffId(Long staffId) {
        this.staffId = staffId;
    }

    public Integer getDefaultJob() {
        return defaultJob;
    }

    public void setDefaultJob(Integer defaultJob) {
        this.defaultJob = defaultJob;
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
	 * @return the jobName
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * @param jobName the jobName to set
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
    
}