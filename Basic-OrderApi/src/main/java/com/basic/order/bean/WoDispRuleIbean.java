package com.basic.order.bean;

import java.io.Serializable;

import com.basic.framework.common.model.PageBean;

/**
 * 
 *
 * @date 2017年9月6日 上午11:41:52
 * 
 * @Description: 任务派发规则查询参数
 *
 */
public class WoDispRuleIbean extends PageBean implements Serializable {

	private static final long serialVersionUID = -5775461776965097372L;
	
	private Long tacheId;//环节id
    private String ruleType;//规则类型
    private String dispCode;//派单规则组件(自定义派单规则)
    
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

}