package com.basic.framework.workflow.bean;

import java.io.Serializable;

/**
 * 
 *
 * @date 2017年8月8日 下午4:32:52
 * 
 * @Description: 流程查询的bean
 *
 */
public class WorkflowIbean implements Serializable {

	private static final long serialVersionUID = -4533886366744982752L;
	
	private String processInstanceId;//流程实例id
    private String processDefineKey;//流程模板编码
    
	public String getProcessInstanceId() {
		return processInstanceId;
	}
	public void setProcessInstanceId(String processInstanceId) {
		this.processInstanceId = processInstanceId;
	}
	public String getProcessDefineKey() {
		return processDefineKey;
	}
	public void setProcessDefineKey(String processDefineKey) {
		this.processDefineKey = processDefineKey;
	}
    
}