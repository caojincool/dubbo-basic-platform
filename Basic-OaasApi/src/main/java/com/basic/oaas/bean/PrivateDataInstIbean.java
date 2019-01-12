/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月16日 上午11:10:29
 * @author Kevin
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 */
package com.basic.oaas.bean;

import java.io.Serializable;

import com.basic.framework.common.model.PageBean;

/**
 *
 * @date 2017年8月16日 上午11:10:29
 * @author Kevin
 * @Description: 数据权限实例入参
 * 
 */
public class PrivateDataInstIbean extends PageBean implements Serializable{

	private static final long serialVersionUID = 2832100068018243476L;
	
	private String dataId; //数据权限Id
	
	private Long userId;
	

	/**
	 * @return the dataId
	 */
	public String getDataId() {
		return dataId;
	}

	/**
	 * @param dataId the dataId to set
	 */
	public void setDataId(String dataId) {
		this.dataId = dataId;
	}

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
	
	
}
