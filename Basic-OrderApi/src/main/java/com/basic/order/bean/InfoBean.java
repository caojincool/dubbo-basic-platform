package com.basic.order.bean;

import java.io.Serializable;

public class InfoBean implements Serializable {

	private static final long serialVersionUID = 1261364606522573895L;
	
	private Long outId;
	
	private String subject;
	
	private String ownerAccount;
	
	private String content;
	
	private String receiverAccount;
	
	private String systemCode;
	
	private String receiveOrg;
	
	private Long  thirdId;
	
	

	public Long getThirdId() {
		return thirdId;
	}

	public void setThirdId(Long thirdId) {
		this.thirdId = thirdId;
	}

	public String getReceiveOrg() {
		return receiveOrg;
	}

	public void setReceiveOrg(String receiveOrg) {
		this.receiveOrg = receiveOrg;
	}

	public Long getOutId() {
		return outId;
	}

	public void setOutId(Long outId) {
		this.outId = outId;
	}

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getOwnerAccount() {
		return ownerAccount;
	}

	public void setOwnerAccount(String ownerAccount) {
		this.ownerAccount = ownerAccount;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public String getReceiverAccount() {
		return receiverAccount;
	}

	public void setReceiverAccount(String receiverAccount) {
		this.receiverAccount = receiverAccount;
	}

	public String getSystemCode() {
		return systemCode;
	}

	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
	
	

}
