package com.basic.order.bean;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年8月21日 上午11:46:19
 * 
 * @Description: 自定义派单规则输出参数
 *
 */
public class WoDispRuleObean implements Serializable {

	private static final long serialVersionUID = -2836901400558779142L;
	
	private Long partyId;//执行人标识
    private String partyName;//执行人名称
    private String partyType;//执行人类型
    
	public Long getPartyId() {
		return partyId;
	}
	public void setPartyId(Long partyId) {
		this.partyId = partyId;
	}
	public String getPartyName() {
		return partyName;
	}
	public void setPartyName(String partyName) {
		this.partyName = partyName;
	}
	public String getPartyType() {
		return partyType;
	}
	public void setPartyType(String partyType) {
		this.partyType = partyType;
	}

}