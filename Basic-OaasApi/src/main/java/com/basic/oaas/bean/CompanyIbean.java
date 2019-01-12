/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月8日 上午10:02:04
 * @author Kevin
 * @Description: 公司信息bean
 * 
 */
package com.basic.oaas.bean;

import java.io.Serializable;

import com.basic.framework.common.model.PageBean;

/**
 * 
 *
 * @date 2017年11月6日 下午2:39:26
 * @author Kevin
 * @Description: 公司信息表参数
 *
 */
public class CompanyIbean extends PageBean implements Serializable{

	private static final long serialVersionUID = 6185644516543706236L;

	private String fullName;  	//公司全称

    private String chineseName; //公司中文名

    private String englishName; //公司英文名

	/**
	 * @return the fullName
	 */
	public String getFullName() {
		return fullName;
	}

	/**
	 * @param fullName the fullName to set
	 */
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	/**
	 * @return the chineseName
	 */
	public String getChineseName() {
		return chineseName;
	}

	/**
	 * @param chineseName the chineseName to set
	 */
	public void setChineseName(String chineseName) {
		this.chineseName = chineseName;
	}

	/**
	 * @return the englishName
	 */
	public String getEnglishName() {
		return englishName;
	}

	/**
	 * @param englishName the englishName to set
	 */
	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}
	
    
}
