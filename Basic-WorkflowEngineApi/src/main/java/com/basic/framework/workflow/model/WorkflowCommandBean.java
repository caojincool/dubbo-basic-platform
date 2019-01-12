package com.basic.framework.workflow.model;

import java.util.Date;
import java.util.Map;

/**
 * 工作流指令，由工作流客户端使用
 * Created by lzj on 2017/7/21.
 */
public class WorkflowCommandBean {

    private Long commandId;
    private Long serialNo;
    private String processDefineKey;
    private Long busiOrderId;
    private String taskId;
    private String commandType;
    private String processInstanceId;
    private String targetTaskDefinitionKey;
    private Map<String,Object> paramMap;
    private Date createTime;
    private String errorCode;
    private String sendFlag;
    private String sendResult;

    public Long getCommandId() {
        return commandId;
    }

    public void setCommandId(Long commandId) {
        this.commandId = commandId;
    }

    public Long getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(Long serialNo) {
        this.serialNo = serialNo;
    }

    public String getProcessDefineKey() {
        return processDefineKey;
    }

    public void setProcessDefineKey(String processDefineKey) {
        this.processDefineKey = processDefineKey;
    }

    public Long getBusiOrderId() {
        return busiOrderId;
    }

    public void setBusiOrderId(Long busiOrderId) {
        this.busiOrderId = busiOrderId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getCommandType() {
        return commandType;
    }

    public void setCommandType(String commandType) {
        this.commandType = commandType;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getTargetTaskDefinitionKey() {
        return targetTaskDefinitionKey;
    }

    public void setTargetTaskDefinitionKey(String targetTaskDefinitionKey) {
        this.targetTaskDefinitionKey = targetTaskDefinitionKey;
    }

    public Map<String, Object> getParamMap() {
        return paramMap;
    }

    public void setParamMap(Map<String, Object> paramMap) {
        this.paramMap = paramMap;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getSendFlag() {
        return sendFlag;
    }

    public void setSendFlag(String sendFlag) {
        this.sendFlag = sendFlag;
    }

    public String getSendResult() {
        return sendResult;
    }

    public void setSendResult(String sendResult) {
        this.sendResult = sendResult;
    }
}
