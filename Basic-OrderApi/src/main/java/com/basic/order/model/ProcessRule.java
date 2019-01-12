package com.basic.order.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @date 2017年8月17日 下午1:07:12
 * 
 * @Description: 流程适配规则
 *
 */
public class ProcessRule implements Serializable {

	private static final long serialVersionUID = -4209509295606057090L;

	private Long ruleId;

    private Long serviceId;

    private String serviceCode;

    private String processDefineKey;

    private Long extendRuleId;

    private String state;

    private Long createUserId;

    private Date createTime;

    private Long modifyUserId;

    private Date modifyTime;

    private String remarks;
    
    private Long[] ruleIds;//多个流程适配的集合
    
    private String pageDateType;//新增：CREATE;修改：UPDATE
    
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

	public Long getRuleId() {
        return ruleId;
    }

    public void setRuleId(Long ruleId) {
        this.ruleId = ruleId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public String getServiceCode() {
        return serviceCode;
    }

    public void setServiceCode(String serviceCode) {
        this.serviceCode = serviceCode;
    }

    public String getProcessDefineKey() {
        return processDefineKey;
    }

    public void setProcessDefineKey(String processDefineKey) {
        this.processDefineKey = processDefineKey;
    }

    public Long getExtendRuleId() {
        return extendRuleId;
    }

    public void setExtendRuleId(Long extendRuleId) {
        this.extendRuleId = extendRuleId;
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
}