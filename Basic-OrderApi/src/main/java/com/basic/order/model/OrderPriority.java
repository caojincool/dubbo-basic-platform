package com.basic.order.model;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 *
 * @date 2017年8月17日 下午1:05:59
 * 
 * @Description: 优先级
 *
 */
public class OrderPriority implements Serializable {

	private static final long serialVersionUID = -7442702138301587160L;

	private Integer orderPriority;

    private String orderPriorityName;

    private String state;

    private Long createUserId;

    private Date createTime;

    private Long modifyUserId;

    private Date modifyTime;

    private String remarks;
    
    private String[] orderPrioritys;//多个优先级的集合
    private String pageDateType;//新增：CREATE;修改：UPDATE

    public Integer getOrderPriority() {
        return orderPriority;
    }

    public void setOrderPriority(Integer orderPriority) {
        this.orderPriority = orderPriority;
    }

    public String getOrderPriorityName() {
        return orderPriorityName;
    }

    public void setOrderPriorityName(String orderPriorityName) {
        this.orderPriorityName = orderPriorityName;
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

	public String[] getOrderPrioritys() {
		return orderPrioritys;
	}

	public void setOrderPrioritys(String[] orderPrioritys) {
		this.orderPrioritys = orderPrioritys;
	}

	public String getPageDateType() {
		return pageDateType;
	}

	public void setPageDateType(String pageDateType) {
		this.pageDateType = pageDateType;
	}
    
}