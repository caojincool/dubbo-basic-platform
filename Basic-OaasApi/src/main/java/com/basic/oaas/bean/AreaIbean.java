/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月4日 下午5:31:16
 * @author Kevin
 * @Description: 区域bean
 * 
 */
package com.basic.oaas.bean;

import java.io.Serializable;


/**
 *
 * @date 2017年8月4日 下午5:31:16
 * @author Kevin
 * @Description: 区域入参
 * 
 */
public class AreaIbean implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long areaId;
	
	private Long userId;
	
	private Long parentAreaId;
	
	private String areaType; //区域类型   全部区域ALLAREA、 当前区域的子区域 SUBAREA 、当前区域 CURAREA
	

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
	 * @return the areaType
	 */
	public String getAreaType() {
		return areaType;
	}

	/**
	 * @param areaType the areaType to set
	 */
	public void setAreaType(String areaType) {
		this.areaType = areaType;
	}
	
	
}
