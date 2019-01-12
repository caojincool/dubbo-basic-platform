package com.basic.order.bean;

import java.io.Serializable;
import java.util.List;

import com.basic.order.model.OrderFollowUser;

/**
 * 
 *
 * @date 2017年8月17日 下午1:05:20
 * 
 * @Description: 创建流程单据参数
 *
 */
public class CreateOrderIbean implements Serializable {

	private static final long serialVersionUID = 7533950283887687916L;
	
	private String serviceCode;//服务编码//必填
    private Integer orderPriority;//优先级//必填
    private Long serviceOrderId;//业务单据id//必填
    private String orderType;//单据类型//必填
    private Long parentOrderId;//父单据类标识//非必填
    private Long createUserId;//创建人标识//必填
    private String createUserText;//创建人姓名//必填
    private Long createOrgId;//创建人组织//必填
    private String createOrgName;//创建人组织名称//必填
    private Long areaId;//区域标识//必填
    private String areaName;//区域名称//必填
    private Long scopeId;//范围id//非必填
    private String orderCode;//单据编码//必填
    private String orderTitle;//单据标题//必填
    private String orderComments;//单据备注//非必填
    private List<OrderFollowUser> orderFollowUserList;//关注人列表//非必填
    
	public String getServiceCode() {
		return serviceCode;
	}
	public void setServiceCode(String serviceCode) {
		this.serviceCode = serviceCode;
	}
	public Integer getOrderPriority() {
		return orderPriority;
	}
	public void setOrderPriority(Integer orderPriority) {
		this.orderPriority = orderPriority;
	}
	public String getOrderType() {
		return orderType;
	}
	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}
	public Long getParentOrderId() {
		return parentOrderId;
	}
	public void setParentOrderId(Long parentOrderId) {
		this.parentOrderId = parentOrderId;
	}
	public Long getCreateUserId() {
		return createUserId;
	}
	public void setCreateUserId(Long createUserId) {
		this.createUserId = createUserId;
	}
	public String getCreateUserText() {
		return createUserText;
	}
	public void setCreateUserText(String createUserText) {
		this.createUserText = createUserText;
	}
	public Long getCreateOrgId() {
		return createOrgId;
	}
	public void setCreateOrgId(Long createOrgId) {
		this.createOrgId = createOrgId;
	}
	public String getCreateOrgName() {
		return createOrgName;
	}
	public void setCreateOrgName(String createOrgName) {
		this.createOrgName = createOrgName;
	}
	public Long getAreaId() {
		return areaId;
	}
	public void setAreaId(Long areaId) {
		this.areaId = areaId;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public Long getScopeId() {
		return scopeId;
	}
	public void setScopeId(Long scopeId) {
		this.scopeId = scopeId;
	}
	public String getOrderCode() {
		return orderCode;
	}
	public void setOrderCode(String orderCode) {
		this.orderCode = orderCode;
	}
	public String getOrderTitle() {
		return orderTitle;
	}
	public void setOrderTitle(String orderTitle) {
		this.orderTitle = orderTitle;
	}
	public String getOrderComments() {
		return orderComments;
	}
	public void setOrderComments(String orderComments) {
		this.orderComments = orderComments;
	}
	public List<OrderFollowUser> getOrderFollowUserList() {
		return orderFollowUserList;
	}
	public void setOrderFollowUserList(List<OrderFollowUser> orderFollowUserList) {
		this.orderFollowUserList = orderFollowUserList;
	}
	public Long getServiceOrderId() {
		return serviceOrderId;
	}
	public void setServiceOrderId(Long serviceOrderId) {
		this.serviceOrderId = serviceOrderId;
	}
    
}