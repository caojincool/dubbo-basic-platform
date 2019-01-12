package com.basic.order.bean;

import java.io.Serializable;
import java.util.Date;

import com.basic.framework.common.model.PageBean;

/**
 * 
 *
 * @date 2017年8月17日 下午1:05:20
 * 
 * @Description: 单据监控查询参数
 *
 */
public class OrderIbean extends PageBean implements Serializable {

	private static final long serialVersionUID = -3217991070677959990L;
	
	private String orderCode;//单据编码
    private String orderState;//单据状态
    private Date createTimeStart;//创建时间开始
    private Date createTimeEnd;//创建时间结束
    private Long createOrgId;//组织
    private Long areaId;//区域
    
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getOrderState() {
		return orderState;
	}
	public void setOrderState(String orderState) {
		this.orderState = orderState;
	}
	public Date getCreateTimeStart() {
		return createTimeStart;
	}
	public void setCreateTimeStart(Date createTimeStart) {
		this.createTimeStart = createTimeStart;
	}
	public Date getCreateTimeEnd() {
		return createTimeEnd;
	}
	public void setCreateTimeEnd(Date createTimeEnd) {
		this.createTimeEnd = createTimeEnd;
	}
	public Long getCreateOrgId() {
		return createOrgId;
	}
	public void setCreateOrgId(Long createOrgId) {
		this.createOrgId = createOrgId;
	}
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
    
}