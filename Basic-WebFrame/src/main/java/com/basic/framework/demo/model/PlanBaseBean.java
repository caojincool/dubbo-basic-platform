package com.basic.framework.demo.model;

import java.util.Date;

public class PlanBaseBean {
    private Long planId;

    private String planName;

    private Date planBeginTime;

    private Date planEndTime;

    private String state;

    private Date stateTime;

    private Long createStaff;

    private Date createTime;

    private Long execStaff;

    private Long modifyStaff;

    private String taskId;

    public Long getPlanId() {
        return planId;
    }

    public void setPlanId(Long planId) {
        this.planId = planId;
    }

    public String getPlanName() {
        return planName;
    }

    public void setPlanName(String planName) {
        this.planName = planName == null ? null : planName.trim();
    }

    public Date getPlanBeginTime() {
        return planBeginTime;
    }

    public void setPlanBeginTime(Date planBeginTime) {
        this.planBeginTime = planBeginTime;
    }

    public Date getPlanEndTime() {
        return planEndTime;
    }

    public void setPlanEndTime(Date planEndTime) {
        this.planEndTime = planEndTime;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state == null ? null : state.trim();
    }

    public Date getStateTime() {
        return stateTime;
    }

    public void setStateTime(Date stateTime) {
        this.stateTime = stateTime;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getExecStaff() {
        return execStaff;
    }

    public void setExecStaff(Long execStaff) {
        this.execStaff = execStaff;
    }

    public Long getModifyStaff() {
        return modifyStaff;
    }

    public void setModifyStaff(Long modifyStaff) {
        this.modifyStaff = modifyStaff;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null ? null : taskId.trim();
    }
}