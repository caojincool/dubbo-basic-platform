/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月16日 上午11:10:29
 * @author Kevin
 * @Description: 
 * 
 */
package com.basic.oaas.bean;

import java.io.Serializable;

import com.basic.framework.common.model.PageBean;

/**
 *
 * @date 2017年8月16日 上午11:10:29
 * @author Kevin
 * @Description: 员工职位
 * 
 */
public class StaffJobIbean extends PageBean  implements Serializable{

	private static final long serialVersionUID = 2832100068018243476L;
	
	private Long staffId;
	
	private Long jobId;
	
	private String staffName;	//员工名称
	
	private String staffNumber;	//员工号
	
	private Long orgId;			//部门Id
	
	private boolean qrySubOrg;  //是否查询下属部门
	
	private Integer isDefault; //是否默认
	
	

	public Integer getIsDefault() {
		return isDefault;
	}

	public void setIsDefault(Integer isDefault) {
		this.isDefault = isDefault;
	}

	/**
	 * @return the staffId
	 */
	public Long getStaffId() {
		return staffId;
	}

	/**
	 * @param staffId the staffId to set
	 */
	public void setStaffId(Long staffId) {
		this.staffId = staffId;
	}

	/**
	 * @return the staffName
	 */
	public String getStaffName() {
		return staffName;
	}

	/**
	 * @param staffName the staffName to set
	 */
	public void setStaffName(String staffName) {
		this.staffName = staffName;
	}

	/**
	 * @return the staffNumber
	 */
	public String getStaffNumber() {
		return staffNumber;
	}

	/**
	 * @param staffNumber the staffNumber to set
	 */
	public void setStaffNumber(String staffNumber) {
		this.staffNumber = staffNumber;
	}

	/**
	 * @return the orgId
	 */
	public Long getOrgId() {
		return orgId;
	}

	/**
	 * @param orgId the orgId to set
	 */
	public void setOrgId(Long orgId) {
		this.orgId = orgId;
	}

	/**
	 * @return the qrySubOrg
	 */
	public boolean isQrySubOrg() {
		return qrySubOrg;
	}

	/**
	 * @param qrySubOrg the qrySubOrg to set
	 */
	public void setQrySubOrg(boolean qrySubOrg) {
		this.qrySubOrg = qrySubOrg;
	}

	/**
	 * @return the jobId
	 */
	public Long getJobId() {
		return jobId;
	}

	/**
	 * @param jobId the jobId to set
	 */
	public void setJobId(Long jobId) {
		this.jobId = jobId;
	}
	
	

	

}
