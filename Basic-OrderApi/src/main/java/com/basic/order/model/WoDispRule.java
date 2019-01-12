package com.basic.order.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @date 2017年8月17日 下午1:08:05
 * 
 * @Description: 任务派发规则
 *
 */
public class WoDispRule implements Serializable {

	private static final long serialVersionUID = 343880647926768989L;

	private Long ruleId;

    private Long tacheId;

    private String ruleType;

    private String dispCode;

    private String state;

    private Long createUserId;

    private Date createTime;

    private Long modifyUserId;

    private Date modifyTime;

    private String remarks;

    private Long[] ruleIds;//任务派发规则id集合
    private String pageDateType;//新增：CREATE;修改：UPDATE
    private String tacheName;//环节名称
    
    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public Long getTacheId() {
        return tacheId;
    }

    public void setTacheId(Long tacheId) {
        this.tacheId = tacheId;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getDispCode() {
        return dispCode;
    }

    public void setDispCode(String dispCode) {
        this.dispCode = dispCode;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

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

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

	public Long[] getRuleIds() {
		return ruleIds;
	}

	public void setRuleIds(Long[] ruleIds) {
		this.ruleIds = ruleIds;
	}

	public String getPageDateType() {
		return pageDateType;
	}

	public void setPageDateType(String pageDateType) {
		this.pageDateType = pageDateType;
	}

	public String getTacheName() {
		return tacheName;
	}

	public void setTacheName(String tacheName) {
		this.tacheName = tacheName;
	}
}