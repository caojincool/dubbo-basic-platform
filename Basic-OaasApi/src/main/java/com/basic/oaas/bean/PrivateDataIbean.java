/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月8日 上午10:02:04
 * @author Kevin
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 */
package com.basic.oaas.bean;

import java.io.Serializable;

import com.basic.framework.common.model.PageBean;

/**
 * 
 * <pre> 
 * @Description：数据权限分组 控制器类
 * @author:lengzj
 * @email:mail@com
 * @date:2018-06-27 16:21:49
 * @copyright：companyName
 * </pre>
 */
public class PrivateDataIbean extends PageBean implements Serializable{

	private static final long serialVersionUID = 4531686136590734507L;

	
	private String dataCode;
	
	private Long dataGroupId;  //权限分组Id
	
	private Long userId; 


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


	/**
	 * @return the dataGroupId
	 */
	public Long getDataGroupId() {
		return dataGroupId;
	}


	/**
	 * @param dataGroupId the dataGroupId to set
	 */
	public void setDataGroupId(Long dataGroupId) {
		this.dataGroupId = dataGroupId;
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
