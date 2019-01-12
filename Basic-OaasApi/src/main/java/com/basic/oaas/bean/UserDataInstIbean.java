/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月4日 下午5:31:16
 * @author Kevin
 * @Description: 
 * 
 */
package com.basic.oaas.bean;

import java.io.Serializable;
import java.util.List;

import com.basic.oaas.model.UserDataInst;


/**
 *
 * @date 2017年8月4日 下午5:31:16
 * @author Kevin
 * @Description: 账号数据实例入参
 * 
 */
public class UserDataInstIbean implements Serializable {

	private static final long serialVersionUID = 2040115622248169464L;

	private Long userId;
	
	private List<UserDataInst> userDataInsts;
	
	private String dataCode;  //数据权限编码
	
	private Long companyId;

	/**
	 * @return the userId
	 */
	public Long getUserId() {
		return userId;
	}

	/**
	 * @param userId the userId to set
	 */
	public void setUserId(Long userId) {
		this.userId = userId;
	}

	/**
	 * @return the userDataInsts
	 */
	public List<UserDataInst> getUserDataInsts() {
		return userDataInsts;
	}

	/**
	 * @param userDataInsts the userDataInsts to set
	 */
	public void setUserDataInsts(List<UserDataInst> userDataInsts) {
		this.userDataInsts = userDataInsts;
	}

	/**
	 * @return the dataCode
	 */
	public String getDataCode() {
		return dataCode;
	}

	/**
	 * @param dataCode the dataCode to set
	 */
	public void setDataCode(String dataCode) {
		this.dataCode = dataCode;
	}

	public Long getCompanyId() {
		return companyId;
	}

	public void setCompanyId(Long companyId) {
		this.companyId = companyId;
	}
	
	
	
}
