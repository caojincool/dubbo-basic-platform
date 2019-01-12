package com.basic.framework.quartz.model;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年8月2日 上午10:09:15
 * 
 * @Description: 定时器任务
 *
 */
public class ScheduleJob implements Serializable {

	private static final long serialVersionUID = 5998229016426009570L;
	
	private String jobName;//任务名
	private String jobGroup;//任务组
	private String jobStatus;//状态
	private String cronExpression;//时间表达式
	private String remarks;//备注
	
	public String getJobName() {
		return jobName;
	}
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}
	public String getJobGroup() {
		return jobGroup;
	}
	public void setJobGroup(String jobGroup) {
		this.jobGroup = jobGroup;
	}
	public String getJobStatus() {
		return jobStatus;
	}
	public void setJobStatus(String jobStatus) {
		this.jobStatus = jobStatus;
	}
	public String getCronExpression() {
		return cronExpression;
	}
	public void setCronExpression(String cronExpression) {
		this.cronExpression = cronExpression;
	}
	public String getRemarks() {
		return remarks;
	}
	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}
	
}
