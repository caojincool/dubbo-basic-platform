package com.basic.order.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.workflow.client.WorkflowClient;
import com.basic.order.bean.CreateOrderIbean;
import com.basic.order.bean.ProcessJumpIbean;
import com.basic.order.bean.ProcessStartIbean;
import com.basic.order.bean.WorkOrderDel;
import com.basic.order.bean.WorkOrderFinish;
import com.basic.order.busi.OrderStateChgComponent;
import com.basic.order.define.OrderOperTypeEnum;
import com.basic.order.define.OrderStateEnum;
import com.basic.order.define.WorkOrderStateEnum;
import com.basic.order.model.Order;
import com.basic.order.model.OrderFollowUser;
import com.basic.order.model.OrderOper;
import com.basic.order.model.OrderPriority;
import com.basic.order.model.OrderStateComponent;
import com.basic.order.model.OrderType;
import com.basic.order.model.ProcessRule;
import com.basic.order.model.WorkOrder;

/**
 * 
 *
 * @date 2017年8月17日 上午11:49:24
 * 
 * @Description: 流程单据服务
 *
 */

@Transactional(rollbackFor=Exception.class)
@Service("processOrderManageService")
public class ProcessOrderManageServiceImpl implements ProcessOrderManageService, ApplicationContextAware{

	private Logger logger = LoggerFactory.getLogger(ProcessOrderManageServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private OrderService orderService;
	@Autowired
	private OrderServiceService orderServiceService;
	@Autowired
	private OrderPriorityService orderPriorityService;
	@Autowired
	private OrderTypeService orderTypeService;
	@Autowired
	private OrderFollowUserService orderFollowUserService;
	@Autowired
	private ProcessRuleService processRuleService;
	@Autowired
	private WorkflowClient workflowClient;
	@Autowired
	private OrderOperService orderOperService;
	@Autowired
	private ProcessWorkOrderManageService processWorkOrderManageService;
	@Autowired
	private WorkOrderService workOrderService;
	@Autowired
	private OrderStateComponentService orderStateComponentService;
	@Autowired
	private ApplicationContext applicationContext;
	@Resource(name="transactionManager")  
    private DataSourceTransactionManager transactionManager;  
	

	/* (non-Javadoc)
	 * @see com.basic.order.service.ProcessOrderManageService#createOrder(com.basic.order.bean.CreateOrder)
	 */
	@Override
	public Long createOrder(CreateOrderIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("createOrder CreateOrder:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		if(StringUtils.isBlank(ibean.getServiceCode())){
			throw new NullPointerException("不允许为空:"+ibean.getServiceCode());
		}
		if(null == ibean.getOrderPriority()){
			throw new NullPointerException("不允许为空:"+ibean.getOrderPriority());
		}
		if(null == ibean.getServiceOrderId()){
			throw new NullPointerException("不允许为空:"+ibean.getServiceOrderId());
		}
		if(StringUtils.isBlank(ibean.getOrderType())){
			throw new NullPointerException("不允许为空:"+ibean.getOrderType());
		}
		if(null == ibean.getCreateUserId() || StringUtils.isBlank(ibean.getCreateUserText())){
			throw new NullPointerException("不允许为空:"+ibean.getCreateUserId()+","+ibean.getCreateUserText());
		}
		if(null == ibean.getCreateOrgId() || StringUtils.isBlank(ibean.getCreateOrgName())){
			throw new NullPointerException("不允许为空:"+ibean.getCreateOrgId()+","+ibean.getCreateOrgName());
		}
		if(null == ibean.getAreaId() || StringUtils.isBlank(ibean.getAreaName())){
			throw new NullPointerException("不允许为空:"+ibean.getAreaId()+","+ibean.getAreaName());
		}
		if(StringUtils.isBlank(ibean.getOrderTitle())){
			throw new NullPointerException("不允许为空:"+ibean.getOrderTitle());
		}
		if(StringUtils.isBlank(ibean.getOrderCode())){
			throw new NullPointerException("不允许为空:"+ibean.getOrderCode());
		}
		
		Date now = DateUtils.now();
		//服务
		com.basic.order.model.OrderService orderServiceBean = orderServiceService.qryByServiceCode(ibean.getServiceCode());
		//优先级
		OrderPriority orderPriority = orderPriorityService.qryByPrimaryKey(ibean.getOrderPriority());
		//单据类型
		OrderType orderType = orderTypeService.qryByPrimaryKey(ibean.getOrderType());
		//父单据标识
		Order parentOrder = null;
		if(ibean.getParentOrderId() != null){
			parentOrder = orderService.qryByPrimaryKey(ibean.getParentOrderId());
			if(parentOrder == null){
				throw new NullPointerException("找不到对应的父单据,parentOrderId:"+ibean.getParentOrderId());
			}
		}
		
		
		Order order = new Order();
//		order.setorderId
		order.setOrderCode(ibean.getOrderCode());
		order.setOrderTitle(ibean.getOrderTitle());
		order.setProcessInstanceId(null);
		order.setServiceId(orderServiceBean.getServiceId());
		order.setServiceCode(orderServiceBean.getServiceCode());
		order.setServiceName(orderServiceBean.getServiceName());
		order.setScopeId(ibean.getScopeId());//范围标识
		order.setCreateTime(now);
//		order.setstateTime
		order.setOrderState(OrderStateEnum.NOT_START.getCode());//未启动
		order.setOrderPriority(orderPriority.getOrderPriority());
		order.setOrderPriorityName(orderPriority.getOrderPriorityName());
		order.setServiceOrderId(ibean.getServiceOrderId());
		order.setOrderType(orderType.getOrderType());
		order.setOrderTypeName(orderType.getOrderTypeName());
		if(parentOrder != null){
			order.setParentOrderId(parentOrder.getOrderId());
		}
		order.setCreateUserId(ibean.getCreateUserId());
		order.setCreateUserText(ibean.getCreateUserText());
		order.setCreateOrgId(ibean.getCreateOrgId());
		order.setCreateOrgName(ibean.getCreateOrgName());
		order.setAreaId(ibean.getAreaId());
		order.setAreaName(ibean.getAreaName());
//		order.setcostTime
//		order.setcostWorkTime
//		order.seterrorCode
//		order.seterrInfo
		order.setOrderComments(ibean.getOrderComments());
		orderService.createSelective(order);
		
		Long orderId = order.getOrderId();
		
		//流程关注人
		if(ibean.getOrderFollowUserList() != null && ibean.getOrderFollowUserList().size() > 0){
			for(OrderFollowUser item : ibean.getOrderFollowUserList()){
				OrderFollowUser orderFollowUser = new OrderFollowUser();
//				orderFollowUser.setfollowUserId
				orderFollowUser.setOrderId(orderId);
				orderFollowUser.setUserId(item.getUserId());//必填
				orderFollowUser.setUserText(item.getUserText());//必填
				orderFollowUser.setCreateTime(now);
				orderFollowUserService.createSelective(orderFollowUser);
			}
		}
		//调用状态组件
		callOrderStateChgComponent(orderId, order.getOrderState());
		
		return orderId;
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.ProcessOrderManageService#processStart(com.basic.order.bean.ProcessStartIbean)
	 */
	@Override
	@Transactional(propagation=Propagation.REQUIRES_NEW,readOnly=false,rollbackFor=Exception.class)
	public void processStart(ProcessStartIbean ibean) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("processStart ProcessStartIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		Long orderId = ibean.getOrderId();
	    Long operUserId = ibean.getOperUserId();
	    String operUserText = ibean.getOperUserText();
	    String operComments = ibean.getOperComments();
	    Map<String,Object> paramMap = ibean.getParamMap();
	    
		if(null == orderId){
			throw new NullPointerException("不允许为空:"+orderId);
		}
		if(null == operUserId || StringUtils.isBlank(operUserText)){
			throw new NullPointerException("不允许为空:"+operUserId+","+operUserText);
		}
		
		Order dbOrder = orderService.qryByPrimaryKey(orderId);
		if(!OrderStateEnum.NOT_START.getCode().equals(dbOrder.getOrderState())){//非未启动
			throw new Exception("该单据不是未启动状态:"+dbOrder.getOrderState());
		}
		
		ProcessRule processRule = processRuleService.qryByOrderId(orderId);
		if(processRule == null){
			throw new NullPointerException("找不到流程适配规则"+orderId);
		}
		//启动流程
		workflowClient.processStart(null, processRule.getProcessDefineKey(), orderId, paramMap);
		
		//修改单据状态为启动中
		Date now = DateUtils.now();
		Order order = new Order();
		order.setOrderId(orderId);
		order.setOrderState(OrderStateEnum.STARTING.getCode());//启动中
		order.setStateTime(now);
		orderService.modifyByPrimaryKeySelective(order);
		
		OrderOper orderOper = new OrderOper();
//		orderOper.setoperId
		orderOper.setOrderId(orderId);
		orderOper.setOperType(OrderOperTypeEnum.FLOW_START.getCode());
		orderOper.setOperUserId(operUserId);
		orderOper.setOperUserText(operUserText);
		orderOper.setOperTime(now);
		orderOper.setOperInfo(OrderOperTypeEnum.FLOW_START.getComment());
		orderOper.setOperComments(operComments);
		orderOperService.createSelective(orderOper);
		
		//调用状态组件
		callOrderStateChgComponent(orderId, order.getOrderState());
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.ProcessOrderManageService#processReset(com.basic.order.bean.ProcessStartIbean)
	 */
	@Override
	public void processReset(ProcessStartIbean ibean) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("processReset ProcessStartIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		Long orderId = ibean.getOrderId();
	    Long operUserId = ibean.getOperUserId();
	    String operUserText = ibean.getOperUserText();
	    String operComments = ibean.getOperComments();
	    Map<String,Object> paramMap = ibean.getParamMap();
	    
		if(null == orderId){
			throw new NullPointerException("不允许为空:"+orderId);
		}
		if(null == operUserId || StringUtils.isBlank(operUserText)){
			throw new NullPointerException("不允许为空:"+operUserId+","+operUserText);
		}
		
		//调用作废未完成任务服务 	OPER_COMMENTS：流程重置
		WorkOrderDel delIbean = new WorkOrderDel();
		delIbean.setOrderId(orderId);
		delIbean.setOperUserId(operUserId);
		delIbean.setOperUserText(operUserText);
		String msg = "流程重置，单据id："+orderId;
		delIbean.setOperComments(msg);
		processWorkOrderManageService.workOrderDel(delIbean);
		
		ProcessRule processRule = processRuleService.qryByOrderId(orderId);
		if(processRule == null){
			throw new NullPointerException("找不到流程适配规则"+orderId);
		}
		//启动流程
		workflowClient.processStart(null, processRule.getProcessDefineKey(), orderId, paramMap);
		
		//修改单据状态为启动中
		Date now = DateUtils.now();
		Order order = new Order();
		order.setOrderId(orderId);
		order.setOrderState(OrderStateEnum.STARTING.getCode());//启动中
		order.setStateTime(now);
		orderService.modifyByPrimaryKeySelective(order);
		
		OrderOper orderOper = new OrderOper();
//		orderOper.setoperId
		orderOper.setOrderId(orderId);
		orderOper.setOperType(OrderOperTypeEnum.FLOW_RESET.getCode());
		orderOper.setOperUserId(operUserId);
		orderOper.setOperUserText(operUserText);
		orderOper.setOperTime(now);
		String msg2 = "，";
		List<WorkOrder> dbWorkorders = workOrderService.qryByOrderId(orderId);
		if(dbWorkorders != null && dbWorkorders.size() > 0){
			for(WorkOrder item : dbWorkorders){
				msg2 = msg2+"当前环节："+item.getTacheCode()+"，任务标识："+item.getWorkOrderId()+"!";
			}
		}
		
		orderOper.setOperInfo(OrderOperTypeEnum.FLOW_RESET.getComment()+msg2);
		orderOper.setOperComments(operComments);
		orderOperService.createSelective(orderOper);
		
		//调用状态组件
		callOrderStateChgComponent(orderId, order.getOrderState());
		
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.ProcessOrderManageService#processJump(com.basic.order.bean.ProcessStartIbean)
	 */
	@Override
	public void processJump(ProcessJumpIbean ibean) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("processJump ProcessStartIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		Long orderId = ibean.getOrderId();
	    Long operUserId = ibean.getOperUserId();
	    String operUserText = ibean.getOperUserText();
	    String operComments = ibean.getOperComments();
	    String targetTacheCode = ibean.getTargetTacheCode();
	    Map<String,Object> paramMap = ibean.getParamMap();
			
		if(null == orderId){
			throw new NullPointerException("不允许为空:"+orderId);
		}
		if(null == operUserId || StringUtils.isBlank(operUserText)){
			throw new NullPointerException("不允许为空:"+operUserId+","+operUserText);
		}
		if(StringUtils.isBlank(operUserText)){
			throw new NullPointerException("不允许为空:"+targetTacheCode);
		}
		Long[] workOrderId = null;
		//先查出来，下面再作废
		Order dbOrder = orderService.qryByPrimaryKey(orderId);
		//多个任务的话，只取任何一个未处理或处理中的任务
		List<WorkOrder> dbWorkOrders = workOrderService.qryByOrderId(orderId);
		WorkOrder dbWorkorder = null;
		if(dbWorkOrders != null && dbWorkOrders.size() > 0){
			 for(WorkOrder item : dbWorkOrders){
				 String code = item.getTacheCode();
				 String taCode = ibean.getTacheCode();
				   
				 /**
				  * //处理多节点并行
				  * 不是操作人的先进行回单处理，再修改状态为作废
				  */
				 if(taCode!=null&&!code.equals(taCode)) {
					 WorkOrderFinish workOrderFinish = new WorkOrderFinish();
					 workOrderFinish.setOperOrgId(ibean.getOrderId());
					 workOrderFinish.setOperUserId(ibean.getOperUserId());
					 workOrderFinish.setOperUserText(ibean.getOperUserText());
					 workOrderFinish.setWorkOrderId(item.getWorkOrderId());
					 workOrderFinish.setOperOrgName("1");
					 workOrderFinish.setOperBusiTime(DateUtils.now());
					 workOrderFinish.setOperOrgId(-1L);
					 processWorkOrderManageService.workOrderFinish(workOrderFinish);
					 item.setWorkOrderState(WorkOrderStateEnum.TEN_D.getCode());//已完成
					 workOrderService.modifyByPrimaryKeySelective(item);
					 //系统通知
					 //sysNoticeService.updateSysNotice(orderId, code);
				 
				 }else {
					 dbWorkorder = item;
					 try {
						//系统通知
						 //sysNoticeService.updateSysNotice(orderId, taCode);//更新当前操作人状态
						 WorkOrder workOrder = new WorkOrder();
						 workOrder.setOrderId(orderId);
						 workOrder.setTacheCode(targetTacheCode);
						 List<WorkOrder> woList = workOrderService.selectWorkOrderByTargetCode(workOrder);
						 if(woList!=null && woList.size() > 0) {
							 WorkOrder wo = woList.get(0);
							// sysNoticeService.sysNoticeToOa(orderId, wo.getPartyId(), wo.getPartyName(), targetTacheCode);//通知跳转人 
						 }
					} catch (Exception e) {
						e.printStackTrace();
					}
					 
				 }
			 }
			
		}
		
		//调用作废未完成任务服务 		OPER_COMMENTS：流程跳转
		WorkOrderDel delIbean = new WorkOrderDel();
		delIbean.setOrderId(orderId);
		delIbean.setOperUserId(operUserId);
		delIbean.setOperUserText(operUserText);
		delIbean.setTacheCode(ibean.getTacheCode());
		String msg = "流程跳转，单据id："+orderId;
		delIbean.setOperComments(operComments);
		processWorkOrderManageService.workOrderToBack(delIbean);
		
		//流程跳转
		workflowClient.processJump(null, dbOrder.getProcessInstanceId()+"", dbWorkorder.getTaskId()+"", targetTacheCode, paramMap);

		//记录流程单据操作记录
		Date now = DateUtils.now();
		OrderOper orderOper = new OrderOper();
//		orderOper.setoperId
		orderOper.setOrderId(orderId);
		orderOper.setOperType(OrderOperTypeEnum.FLOW_JUMP.getCode());
		orderOper.setOperUserId(operUserId);
		orderOper.setOperUserText(operUserText);
		orderOper.setOperTime(now);
		//	OPER_INFO:流程跳转，原环节，原任务标识，目标环节
		String msg2 = "，原环节："+dbWorkorder.getTacheCode()+"，原任务标识："+dbWorkorder.getWorkOrderId()+"，目标环节："+targetTacheCode;
		orderOper.setOperInfo(OrderOperTypeEnum.FLOW_JUMP.getComment()+msg2);
		orderOper.setOperComments(operComments);
		orderOperService.createSelective(orderOper);
		
		//调用状态组件
		callOrderStateChgComponent(dbOrder.getOrderId(), dbOrder.getOrderState());
	}
	
	/* (non-Javadoc)
	 * @see com.basic.order.service.ProcessOrderManageService#removeOrder(com.basic.order.bean.ProcessStartIbean)
	 */
	@Override
	public void removeOrder(ProcessStartIbean ibean) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("removeOrder ProcessStartIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		Long orderId = ibean.getOrderId();
	    Long operUserId = ibean.getOperUserId();
	    String operUserText = ibean.getOperUserText();
	    String operComments = ibean.getOperComments();
//	    Map<String,Object> paramMap = ibean.getParamMap();
	    
		if(null == orderId){
			throw new NullPointerException("不允许为空:"+orderId);
		}
		if(null == operUserId || StringUtils.isBlank(operUserText)){
			throw new NullPointerException("不允许为空:"+operUserId+","+operUserText);
		}
		
		//调用作废未完成任务服务 OPER_COMMENTS：单据作废
		WorkOrderDel delIbean = new WorkOrderDel();
		delIbean.setOrderId(orderId);
		delIbean.setOperUserId(operUserId);
		delIbean.setOperUserText(operUserText);
//		String msg = "单据作废，单据id："+orderId;
//		delIbean.setOperComments(msg);
		delIbean.setOperComments(operComments);
		processWorkOrderManageService.workOrderDel(delIbean);
		
		Order dbOrder = orderService.qryByPrimaryKey(orderId);
		//流程作废
		workflowClient.processSuspend(null, dbOrder.getProcessInstanceId()+"");
		
		//修改单据状态为已作废
		Date now = DateUtils.now();
		Order order = new Order();
		order.setOrderId(orderId);
		order.setOrderState(OrderStateEnum.INVALID.getCode());//已作废
		order.setStateTime(now);
		orderService.modifyByPrimaryKeySelective(order);
		
		//记录流程单据操作记录
		OrderOper orderOper = new OrderOper();
//		orderOper.setoperId
		orderOper.setOrderId(orderId);
		orderOper.setOperType(OrderOperTypeEnum.ORDER_DEL.getCode());
		orderOper.setOperUserId(operUserId);
		orderOper.setOperUserText(operUserText);
		orderOper.setOperTime(now);
		orderOper.setOperInfo(OrderOperTypeEnum.ORDER_DEL.getComment());
		orderOper.setOperComments(operComments);
		orderOperService.createSelective(orderOper);
		
		//调用状态组件
		callOrderStateChgComponent(orderId, order.getOrderState());
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.ProcessOrderManageService#callOrderStateChgComponent(java.lang.Long, java.lang.String)
	 */
	@Override
	public void callOrderStateChgComponent(Long orderId, String orderState) {
		if (logger.isDebugEnabled()) {
			logger.debug("callOrderStateChgComponent orderId:{},orderState:{}", orderId, orderState);
		}
		if(null == orderId){
			throw new NullPointerException("不允许为空:"+orderId);
		}
		if(StringUtils.isBlank(orderState)){
			throw new NullPointerException("不允许为空:"+orderState);
		}
		List<OrderStateComponent> dbOrderStateComponents = orderStateComponentService.qryByOrderState(orderState);
		if(dbOrderStateComponents != null && dbOrderStateComponents.size() > 0){
			for(OrderStateComponent item : dbOrderStateComponents){
				OrderStateChgComponent orderStateChgComponent = applicationContext.getBean(item.getComponentCode(), OrderStateChgComponent.class);
				orderStateChgComponent.execComponent(orderId, orderState);
			}
		}
	}

	/* (non-Javadoc)
	 * @see org.springframework.context.ApplicationContextAware#setApplicationContext(org.springframework.context.ApplicationContext)
	 */
	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		this.applicationContext = applicationContext;
	}

}
