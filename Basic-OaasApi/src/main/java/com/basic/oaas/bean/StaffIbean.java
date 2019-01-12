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
 * @Description: 员工入参
 * 
 */
public class StaffIbean extends PageBean implements Serializable{

	private static final long serialVersionUID = 2832100068018243476L;
	
	private Long staffId;
	
	private String staffName;	//员工名称
	
	private String staffNumber;	//员工号
	
	private Long orgId;		//部门Id
	
	private boolean qrySubOrg; //是否查询下属部门

	private String  orgType;
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
	
	

	

}
