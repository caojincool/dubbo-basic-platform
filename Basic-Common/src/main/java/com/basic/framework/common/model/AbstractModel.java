package com.basic.framework.common.model;


import com.basic.framework.common.api.BaseModel;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlTransient;
import java.sql.Timestamp;
import java.util.Date;

/**
 * <pre>
 * 描述：实体的基础类
 * 构建组：x5-base-core
 * 作者：csx
 * 邮箱:chensx@jee-soft.cn
 * 日期:2013-12-20-下午8:55:18
 * 版权：company
 * </pre>
 */
@XmlAccessorType(XmlAccessType.NONE)
public abstract class AbstractModel<T> implements BaseModel {

	/**
	 * 创建人
	 */
	protected Long createUserId;
	


	/**
	 * 创建时间
	 */
	protected Date createTime;


	/**
	 * 更新人
	 */
	protected Long modifyUserId;
	


	/**
	 * 更新时间
	 */
	protected Date modifyTime;


	/**
	 * 账套id
	 */
	protected Long accountSet;


	/**
	 * 10A 有效 ,10X 无效,默认是有效
	 */
	protected String state;
	
	/**
	 * 创建人名字
	 */
	protected String createUserName;

	/**
	 * 更新人名字
	 */
	protected String modifyUserName;


	/**
	 * 备用1
	 */
	protected String backup1;


	/**
	 * 备用2
	 */
	protected String backup2;


	/**
	 * 备用3
	 */
	protected String backup3;


	/**
	 * 备用4
	 */
	protected String backup4;


	/**
	 * 备用5
	 */
	protected String backup5;


	public Long getCreateUserId() {
		return createUserId;
	}

	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Long getModifyUserId() {
		return modifyUserId;
	}

	public void setModifyUserId(Long modifyUserId) {
		this.modifyUserId = modifyUserId;
	}

	public Date getModifyTime() {
		return modifyTime;
	}

	public void setModifyTime(Date modifyTime) {
		this.modifyTime = modifyTime;
	}

	public Long getAccountSet() {
		return accountSet;
	}

	public void setAccountSet(Long accountSet) {
		this.accountSet = accountSet;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getBackup1() {
		return backup1;
	}

	public void setBackup1(String backup1) {
		this.backup1 = backup1;
	}

	public String getBackup2() {
		return backup2;
	}

	public void setBackup2(String backup2) {
		this.backup2 = backup2;
	}

	public String getBackup3() {
		return backup3;
	}

	public void setBackup3(String backup3) {
		this.backup3 = backup3;
	}

	public String getBackup4() {
		return backup4;
	}

	public void setBackup4(String backup4) {
		this.backup4 = backup4;
	}

	public String getBackup5() {
		return backup5;
	}

	public void setBackup5(String backup5) {
		this.backup5 = backup5;
	}

	public String getCreateUserName() {
		return createUserName;
	}

	public void setCreateUserName(String createUserName) {
		this.createUserName = createUserName;
	}

	public String getModifyUserName() {
		return modifyUserName;
	}

	public void setModifyUserName(String modifyUserName) {
		this.modifyUserName = modifyUserName;
	}




}
