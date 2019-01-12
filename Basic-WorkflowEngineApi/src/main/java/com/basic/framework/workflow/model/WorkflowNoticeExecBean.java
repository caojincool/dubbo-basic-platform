package com.basic.framework.workflow.model;

import java.util.Date;

/**
 * Created by lzj on 2017/7/24.
 */
public class WorkflowNoticeExecBean {

    private Long execId;
    private Long noticeId;
    private Date createTime;
    private String noticeType;
    private Long busiOrderId;
    private String processInstanceId;
    private String taskId;
    private String taskDefinitionKey;
    private String errorCode;
    private String execFlag;
    private String execResult;

    public Long getExecId() {
        return execId;
    }

    public void setExecId(Long execId) {
        this.execId = execId;
    }

    public Long getNoticeId() {
        return noticeId;
    }

    public void setNoticeId(Long noticeId) {
        this.noticeId = noticeId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getNoticeType() {
        return noticeType;
    }

    public void setNoticeType(String noticeType) {
        this.noticeType = noticeType;
    }

    public Long getBusiOrderId() {
        return busiOrderId;
    }

    public void setBusiOrderId(Long busiOrderId) {
        this.busiOrderId = busiOrderId;
    }

    public String getProcessInstanceId() {
        return processInstanceId;
    }

    public void setProcessInstanceId(String processInstanceId) {
        this.processInstanceId = processInstanceId;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskDefinitionKey() {
        return taskDefinitionKey;
    }

    public void setTaskDefinitionKey(String taskDefinitionKey) {
        this.taskDefinitionKey = taskDefinitionKey;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getExecFlag() {
        return execFlag;
    }

    public void setExecFlag(String execFlag) {
        this.execFlag = execFlag;
    }

    public String getExecResult() {
        return execResult;
    }

    public void setExecResult(String execResult) {
        this.execResult = execResult;
    }
}
