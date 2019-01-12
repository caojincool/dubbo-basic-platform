package com.basic.order.bean;

import java.io.Serializable;

import com.basic.framework.common.model.PageBean;

/**
 * 
 *
 * @date 2017年8月22日 上午9:40:06
 * 
 * @Description: 查询任务派发规则实例页面的参数
 *
 */
public class WoDispRuleInstPageIbean extends PageBean implements Serializable {

	private static final long serialVersionUID = -8066788150374503582L;
	
	private Long ruleId;//规则id
//    private String ruleType;//规则类型
    private String partyType;//接收人类型
    private String partyName;//接收人名称
    
	public Long getRuleId() {
		return ruleId;
	}
	public void setRuleId(Long ruleId) {
		this.ruleId = ruleId;
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
    

}