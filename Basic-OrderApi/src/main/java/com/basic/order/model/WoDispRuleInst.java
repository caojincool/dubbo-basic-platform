package com.basic.order.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @date 2017年8月17日 下午1:08:18
 * 
 * @Description: 任务派发规则实例
 *
 */
public class WoDispRuleInst implements Serializable {

	private static final long serialVersionUID = -8587811228188355401L;

	private Long ruleInstId;

    private Long ruleId;

    private String ruleType;

    private String conditionId;

    private Long partyId;

    private String partyType;

    private String partyName;

    private String state;

    private Long createUserId;

    private Date createTime;

    private Long modifyUserId;

    private Date modifyTime;

    private String remarks;

    private Long[] ruleInstIds;//任务派发规则实例id集合
    private String pageDateType;//新增：CREATE;修改：UPDATE
    private String tacheName;//环节名称
    
    public Long getRuleInstId() {
        return ruleInstId;
    }

    public void setRuleInstId(Long ruleInstId) {
        this.ruleInstId = ruleInstId;
    }

    public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getConditionId() {
        return conditionId;
    }

    public void setConditionId(String conditionId) {
        this.conditionId = conditionId;
    }

    public Long getPartyId() {
        return partyId;
    }

    public void setPartyId(Long partyId) {
        this.partyId = partyId;
    }

    public String getPartyType() {
        return partyType;
    }

    public void setPartyType(String partyType) {
        this.partyType = partyType;
    }

    public String getPartyName() {
        return partyName;
    }

    public void setPartyName(String partyName) {
        this.partyName = partyName;
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

	public Long[] getRuleInstIds() {
		return ruleInstIds;
	}

	public void setRuleInstIds(Long[] ruleInstIds) {
		this.ruleInstIds = ruleInstIds;
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