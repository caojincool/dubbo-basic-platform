package com.basic.order.service;

import java.util.Date;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.workflow.client.WorkflowClient;
import com.basic.order.bean.WoDispRuleObean;
import com.basic.order.bean.WorkOrderDel;
import com.basic.order.bean.WorkOrderDisp;
import com.basic.order.bean.WorkOrderFinish;
import com.basic.order.bean.WorkOrderGet;
import com.basic.order.busi.TacheComponent;
import com.basic.order.busi.WoDispRuleComponent;
import com.basic.order.define.PartyTypeEnum;
import com.basic.order.define.WorkOrderOperTypeEnum;
import com.basic.order.define.WorkOrderStateEnum;
import com.basic.order.model.Order;
import com.basic.order.model.Tache;
import com.basic.order.model.WoComponent;
import com.basic.order.model.WorkOrder;
import com.basic.order.model.WorkOrderOper;

/**
 * 
 *
 * @date 2017年8月18日 下午2:43:55
 * 
 * @Description: 流程任务服务
 *
 */
@Transactional(rollbackFor=Exception.class)
@Service("processWorkOrderManageService")
public class ProcessWorkOrderManageServiceImpl implements ProcessWorkOrderManageService, ApplicationContextAware{

	private Logger logger = LoggerFactory.getLogger(ProcessWorkOrderManageServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();

	@Autowired
	private WorkOrderService workOrderService;
	@Autowired
	private WorkOrderOperService workOrderOperService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private WorkflowClient workflowClient;
	@Autowired
	private WoComponentService woComponentService;
	@Autowired
	private TacheService tacheService;
	@Autowired
	private ApplicationContext applicationContext;
	@Autowired
	private	ProcessOrderManageService processOrderManageService;
	/* (non-Javadoc)
	 * @see com.basic.order.service.ProcessWorkOrderManageService#workOrderGet(com.basic.order.bean.WorkOrderGet)
	 */
	@Transactional(propagation=Propagation.REQUIRED,readOnly=false,rollbackFor=Exception.class)
	@Override
	public void workOrderGet(WorkOrderGet ibean) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("workOrderGet WorkOrderGet:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		Long workOrderId = ibean.getWorkOrderId();
	    Long operUserId = ibean.getOperUserId();
	    String operUserText = ibean.getOperUserText();
	    String operComments = ibean.getOperComments();
//	    Map<String,Object> paramMap = ibean.getParamMap();
	    
		if(null == workOrderId){
			throw new NullPointerException("不允许为空:"+workOrderId);
		}
		if(null == operUserId || StringUtils.isBlank(operUserText)){
			throw new NullPointerException("不允许为空:"+operUserId+","+operUserText);
		}
		
		WorkOrder dbWorkorder = workOrderService.qryByPrimaryKey(workOrderId);
		if(!WorkOrderStateEnum.TEN_I.getCode().equals(dbWorkorder.getWorkOrderState())){//非未处理
			throw new Exception("该任务单不是未处理状态:"+dbWorkorder.getWorkOrderState());
		}
		
		Date now = DateUtils.now();
		//更新OF_WORK_ORDER(流程任务单)表
		WorkOrder workOrder = new WorkOrder();
		workOrder.setWorkOrderId(workOrderId);
		workOrder.setPartyId(operUserId);
		workOrder.setPartyName(operUserText);
		workOrder.setPartyType(PartyTypeEnum.USER.getCode());
		workOrder.setStateTime(now);
		workOrder.setWorkOrderState(WorkOrderStateEnum.TEN_G.getCode());//已提单
		workOrderService.modifyByPrimaryKeySelective(workOrder);
		
		//写入任务操作详情
		WorkOrderOper workOrderOper = new WorkOrderOper();
//		workOrderOper.setoperId
		workOrderOper.setWorkOrderId(workOrderId);
		workOrderOper.setOperType(WorkOrderOperTypeEnum.WO_GET.getCode());
		workOrderOper.setOperUserId(operUserId);
		workOrderOper.setOperUserText(operUserText);
		workOrderOper.setOperTime(now);
		workOrderOper.setOperInfo(WorkOrderOperTypeEnum.WO_GET.getComment());
		workOrderOper.setOperComments(operComments);
		workOrderOperService.createSelective(workOrderOper);
		
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.ProcessWorkOrderManageService#workOrderDisp(com.basic.order.bean.WorkOrderDisp)
	 */
	@Override
	public void workOrderDisp(WorkOrderDisp ibean) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("workOrderDisp WorkOrderDisp:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		Long workOrderId = ibean.getWorkOrderId();
	    Long operUserId = ibean.getOperUserId();
	    String operUserText = ibean.getOperUserText();
	    String operComments = ibean.getOperComments();
	    Long partyId = ibean.getPartyId();
	    String partyName = ibean.getPartyName();
	    String partyType = ibean.getPartyType();
	    
		if(null == workOrderId){
			throw new NullPointerException("不允许为空:"+workOrderId);
		}
		if(null == operUserId || StringUtils.isBlank(operUserText)){
			throw new NullPointerException("不允许为空:"+operUserId+","+operUserText);
		}
		if(null == partyId || StringUtils.isBlank(partyName) || StringUtils.isBlank(partyType)){
			throw new NullPointerException("不允许为空:"+partyId+","+partyName+","+partyType);
		}
		
		WorkOrder dbWorkorder = workOrderService.qryByPrimaryKey(workOrderId);
		if(!WorkOrderStateEnum.TEN_I.getCode().equals(dbWorkorder.getWorkOrderState())
				&&!WorkOrderStateEnum.TEN_G.getCode().equals(dbWorkorder.getWorkOrderState())){//非未处理
			throw new Exception("该任务单不是未处理状态，也不是已提单状态:"+dbWorkorder.getWorkOrderState());
		}
		
		Date now = DateUtils.now();
		//更新OF_WORK_ORDER(流程任务单)表
		WorkOrder workOrder = new WorkOrder();
		workOrder.setWorkOrderId(workOrderId);
		workOrder.setPartyId(partyId);
		workOrder.setPartyName(partyName);
		workOrder.setPartyType(partyType);
		workOrder.setStateTime(now);
		workOrder.setWorkOrderState(WorkOrderStateEnum.TEN_I.getCode());//未处理
		workOrderService.modifyByPrimaryKeySelective(workOrder);
		
		//写入任务操作详情
		WorkOrderOper workOrderOper = new WorkOrderOper();
//		workOrderOper.setoperId
		workOrderOper.setWorkOrderId(workOrderId);
		workOrderOper.setOperType(WorkOrderOperTypeEnum.WO_DISP.getCode());
		workOrderOper.setOperUserId(operUserId);
		workOrderOper.setOperUserText(operUserText);
		workOrderOper.setOperTime(now);
		String msg = "，原处理人："+dbWorkorder.getPartyName()+"，新处理人："+partyName;
		workOrderOper.setOperInfo(WorkOrderOperTypeEnum.WO_DISP.getComment()+msg);
		workOrderOper.setOperComments(operComments);
		workOrderOperService.createSelective(workOrderOper);
		
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.ProcessWorkOrderManageService#workOrderFinish(com.basic.order.bean.WorkOrderFinish)
	 */
	@Override
	public void workOrderFinish(WorkOrderFinish ibean) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("workOrderFinish WorkOrderFinish:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		Long workOrderId = ibean.getWorkOrderId();
	    Long operUserId = ibean.getOperUserId();
	    String operUserText = ibean.getOperUserText();
	    String operComments = ibean.getOperComments();
	    Long operOrgId = ibean.getOperOrgId();
	    String operOrgName = ibean.getOperOrgName();
	    Date operBusiTime = ibean.getOperBusiTime();
	    
	    Map<String,Object> paramMap = ibean.getParamMap();
	    
		if(null == workOrderId){
			throw new NullPointerException("不允许为空:"+workOrderId);
		}
		if(null == operUserId || StringUtils.isBlank(operUserText)){
			throw new NullPointerException("不允许为空:"+operUserId+","+operUserText);
		}
		if(null == operOrgId || StringUtils.isBlank(operOrgName)){
			throw new NullPointerException("不允许为空:"+operOrgId+","+operOrgName);
		}
		if(null == operBusiTime){
			throw new NullPointerException("不允许为空:"+operBusiTime);
		}
		
		WorkOrder dbWorkorder = workOrderService.qryByPrimaryKey(workOrderId);
		if(!WorkOrderStateEnum.TEN_I.getCode().equals(dbWorkorder.getWorkOrderState())
				&&!WorkOrderStateEnum.TEN_G.getCode().equals(dbWorkorder.getWorkOrderState())){//非未处理
			throw new Exception("该任务单不是未处理状态，也不是已提单状态:"+dbWorkorder.getWorkOrderState());
		}
		
		Date now = DateUtils.now();
		//更新OF_WORK_ORDER(流程任务单)表
		WorkOrder workOrder = new WorkOrder();
		workOrder.setWorkOrderId(workOrderId);
		workOrder.setFinishUserId(operUserId);
		workOrder.setFinishUserText(operUserText);
		workOrder.setFinishOrgId(operOrgId);
		workOrder.setFinishOrgName(operOrgName);
		workOrder.setFinishTime(now);
		workOrder.setWorkOrderComments(operComments);
		workOrder.setFinishBusiTime(operBusiTime);
		workOrder.setStateTime(now);
		workOrder.setWorkOrderState(WorkOrderStateEnum.TEN_F.getCode());//已完成
		workOrderService.modifyByPrimaryKeySelective(workOrder);

		//写入任务操作详情
		WorkOrderOper workOrderOper = new WorkOrderOper();
//		workOrderOper.setoperId
		workOrderOper.setWorkOrderId(workOrderId);
		workOrderOper.setOperType(WorkOrderOperTypeEnum.WO_FINISH.getCode());
		workOrderOper.setOperUserId(operUserId);
		workOrderOper.setOperUserText(operUserText);
		workOrderOper.setOperTime(now);
		workOrderOper.setOperInfo(WorkOrderOperTypeEnum.WO_FINISH.getComment());
		workOrderOper.setOperComments(operComments);
		workOrderOperService.createSelective(workOrderOper);
		
		Order dbOrder = orderService.qryByPrimaryKey(dbWorkorder.getOrderId());
		workflowClient.taskComplete(null, dbOrder.getProcessInstanceId()+"", dbWorkorder.getTaskId()+"", paramMap);
		try {
			//流程完成系统通知
			//sysNoticeService.finishSysNotice(workOrderId,operComments);
		} catch (Exception e) {
			e.printStackTrace();
		}
		//更新流程可审批状态
		//sysNoticeService.updateSysNotice(db_workOrder.getOrderId(),db_workOrder.getTacheCode());
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.ProcessWorkOrderManageService#workOrderReturn(com.basic.order.bean.WorkOrderFinish)
	 */
	@Override
	public void workOrderReturn(WorkOrderFinish ibean) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("workOrderReturn WorkOrderFinish:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		Long workOrderId = ibean.getWorkOrderId();
	    Long operUserId = ibean.getOperUserId();
	    String operUserText = ibean.getOperUserText();
	    String operComments = ibean.getOperComments();
	    Long operOrgId = ibean.getOperOrgId();
	    String operOrgName = ibean.getOperOrgName();
	    Date operBusiTime = ibean.getOperBusiTime();
	    
	    Map<String,Object> paramMap = ibean.getParamMap();
	    
		if(null == workOrderId){
			throw new NullPointerException("不允许为空:"+workOrderId);
		}
		if(null == operUserId || StringUtils.isBlank(operUserText)){
			throw new NullPointerException("不允许为空:"+operUserId+","+operUserText);
		}
		if(null == operOrgId || StringUtils.isBlank(operOrgName)){
			throw new NullPointerException("不允许为空:"+operOrgId+","+operOrgName);
		}
		if(null == operBusiTime){
			throw new NullPointerException("不允许为空:"+operBusiTime);
		}
		
		WorkOrder dbWorkorder = workOrderService.qryByPrimaryKey(workOrderId);
		if(!WorkOrderStateEnum.TEN_I.getCode().equals(dbWorkorder.getWorkOrderState())
				&&!WorkOrderStateEnum.TEN_G.getCode().equals(dbWorkorder.getWorkOrderState())){//非未处理
			throw new Exception("该任务单不是未处理状态，也不是已提单状态:"+dbWorkorder.getWorkOrderState());
		}
		
		Date now = DateUtils.now();
		//更新OF_WORK_ORDER(流程任务单)表
		WorkOrder workOrder = new WorkOrder();
		workOrder.setWorkOrderId(workOrderId);
		workOrder.setFinishUserId(operUserId);
		workOrder.setFinishUserText(operUserText);
		workOrder.setFinishOrgId(operOrgId);
		workOrder.setFinishOrgName(operOrgName);
		workOrder.setFinishTime(now);
		workOrder.setFinishBusiTime(operBusiTime);
		workOrder.setStateTime(now);
		workOrder.setWorkOrderState(WorkOrderStateEnum.TEN_F.getCode());//已完成
		workOrderService.modifyByPrimaryKeySelective(workOrder);
		
		//调用作废未完成任务，作废其它任务	OPER_COMMENTS：回退，任务标识：？？
		//一般作用于单线的，只会有一个任务
		WorkOrderDel delIbean = new WorkOrderDel();
		delIbean.setOrderId(dbWorkorder.getOrderId());
		delIbean.setOperUserId(operUserId);
		delIbean.setOperUserText(operUserText);
		String msg = "回退，任务标识："+workOrderId;
		delIbean.setOperComments(msg);
		workOrderDel(delIbean);
		
		//写入任务操作详情
		WorkOrderOper workOrderOper = new WorkOrderOper();
//		workOrderOper.setoperId
		workOrderOper.setWorkOrderId(workOrderId);
		workOrderOper.setOperType(WorkOrderOperTypeEnum.WO_RETURN.getCode());
		workOrderOper.setOperUserId(operUserId);
		workOrderOper.setOperUserText(operUserText);
		workOrderOper.setOperTime(now);
		workOrderOper.setOperInfo(WorkOrderOperTypeEnum.WO_RETURN.getComment());
		workOrderOper.setOperComments(operComments);
		workOrderOperService.createSelective(workOrderOper);
		
		Order dbOrder = orderService.qryByPrimaryKey(dbWorkorder.getOrderId());
		workflowClient.taskRollback(null, dbOrder.getProcessInstanceId()+"", dbWorkorder.getTaskId()+"", paramMap);
		
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.ProcessWorkOrderManageService#workOrderDel(com.basic.order.bean.WorkOrderGet)
	 */
	@Override
	public void workOrderDel(WorkOrderDel ibean) throws Exception {
		if (logger.isDebugEnabled()) {
			logger.debug("workOrderDel WorkOrderGet:{}", JSON_UTILS.objectToJson(ibean));
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
		
		Date now = DateUtils.now();
		List<WorkOrder> dbWorkOrders = workOrderService.qryByOrderId(orderId);
		if(dbWorkOrders != null && dbWorkOrders.size() > 0){
			for(WorkOrder item : dbWorkOrders){
				//更新OF_WORK_ORDER(流程任务单)表
				if(ibean.getTacheCode()==null||item.getTacheCode().equals(ibean.getTacheCode())) {
					WorkOrder workOrder = new WorkOrder();
					workOrder.setWorkOrderId(item.getWorkOrderId());
					workOrder.setPartyId(operUserId);
					workOrder.setPartyName(operUserText);
					workOrder.setPartyType(PartyTypeEnum.USER.getCode());
					workOrder.setStateTime(now);
					workOrder.setWorkOrderComments(operComments);
					workOrder.setWorkOrderState(WorkOrderStateEnum.TEN_X.getCode());//作废
					workOrderService.modifyByPrimaryKeySelective(workOrder);
					
					//写入任务操作详情
					WorkOrderOper workOrderOper = new WorkOrderOper();
	//				workOrderOper.setoperId
					workOrderOper.setWorkOrderId(item.getWorkOrderId());
					workOrderOper.setOperType(WorkOrderOperTypeEnum.WO_DEL.getCode());
					workOrderOper.setOperUserId(operUserId);
					workOrderOper.setOperUserText(operUserText);
					workOrderOper.setOperTime(now);
					workOrderOper.setOperInfo(WorkOrderOperTypeEnum.WO_DEL.getComment());
					workOrderOper.setOperComments(operComments);
					workOrderOperService.createSelective(workOrderOper);
				}
			}
		}
		
		
	}
	
	@Override
	public void workOrderToBack(WorkOrderDel ibean)
		        throws Exception
		    {
		        if(logger.isDebugEnabled()) {
                    logger.debug("workOrderDel WorkOrderGet:{}", JSON_UTILS.objectToJson(ibean));
                }
		        Long orderId = ibean.getOrderId();
		        Long operUserId = ibean.getOperUserId();
		        String operUserText = ibean.getOperUserText();
		        String operComments = ibean.getOperComments();
		        if(orderId == null) {
                    throw new NullPointerException((new StringBuilder("\u4E0D\u5141\u8BB8\u4E3A\u7A7A:")).append(orderId).toString());
                }
		        if(operUserId == null || StringUtils.isBlank(operUserText)) {
                    throw new NullPointerException((new StringBuilder("\u4E0D\u5141\u8BB8\u4E3A\u7A7A:")).append(operUserId).append(",").append(operUserText).toString());
                }
		        Date now = DateUtils.now();
		    	List<WorkOrder> dbWorkOrders = workOrderService.qryByOrderId(orderId);
		        if(dbWorkOrders != null && dbWorkOrders.size() > 0)
		        {
		            WorkOrderOper workOrderOper;
		            for(WorkOrder item : dbWorkOrders){
		                WorkOrder workOrder = new WorkOrder();
		                workOrder.setWorkOrderId(item.getWorkOrderId());
		                workOrder.setFinishUserId(operUserId);
		                workOrder.setFinishUserText(operUserText);
		                workOrder.setPartyType(PartyTypeEnum.USER.getCode());
		                workOrder.setStateTime(now);
		                workOrder.setFinishTime(now);
		                workOrder.setFinishBusiTime(now);
		              //处理多节点并行，审批人显示驳回，未审批人显示作废
						if(ibean.getTacheCode()==null||item.getTacheCode().equals(ibean.getTacheCode())) {
							workOrder.setWorkOrderState(WorkOrderStateEnum.TEN_B.getCode());//驳回
							workOrder.setPartyId(operUserId);
							workOrder.setPartyName(operUserText);
							workOrder.setWorkOrderComments(operComments);
			                workOrderService.modifyByPrimaryKeySelective(workOrder);
			                //写入任务操作详情
			                workOrderOper = new WorkOrderOper();
			                workOrderOper.setWorkOrderId(item.getWorkOrderId());
			                workOrderOper.setOperType(WorkOrderOperTypeEnum.WO_DEL.getCode());
			                workOrderOper.setOperUserId(operUserId);
			                workOrderOper.setOperUserText(operUserText);
			                workOrderOper.setOperTime(now);
			                workOrderOper.setOperInfo(WorkOrderOperTypeEnum.WO_DEL.getComment());
			                workOrderOper.setOperComments(operComments);
						}
//			            }else {
//							ProcessJumpIbean processJumpIbean = new ProcessJumpIbean();
//							processJumpIbean.setOrderId(item.getOrderId());
//							processJumpIbean.setOperUserId(ibean.getOperUserId());
//							processJumpIbean.setOperUserText(ibean.getOperUserText());
//							processJumpIbean.setOperComments(ibean.getOperComments());
//							processJumpIbean.setTargetTacheCode(item.getTacheCode());
//							processOrderManageService.processCancel(processJumpIbean);
//						}
		            }

		        }
		    }

	/* (non-Javadoc)
	 * @see com.basic.order.service.ProcessWorkOrderManageService#callTacheComponent(java.lang.Long, java.lang.Long, java.lang.String)
	 */
	@Override
	public void callTacheComponent(Long orderId, Long workOrderId, String tacheCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("callTacheComponent orderId:{},workOrderId:{},tacheCode:{}", orderId, workOrderId, tacheCode);
		}
		if(null == orderId){
			throw new NullPointerException("不允许为空:"+orderId);
		}
		if(null == workOrderId){
			throw new NullPointerException("不允许为空:"+workOrderId);
		}
		if(StringUtils.isBlank(tacheCode)){
			throw new NullPointerException("不允许为空:"+tacheCode);
		}
		//查询环节
		Tache tache = tacheService.qryByTacheCode(tacheCode);
		List<WoComponent> dbWoComponents = woComponentService.qryByTacheId(tache.getTacheId());
		if(dbWoComponents != null && dbWoComponents.size() > 0){
			for(WoComponent item : dbWoComponents){
				TacheComponent tacheComponent = applicationContext.getBean(item.getComponentCode(), TacheComponent.class);
				tacheComponent.execComponent(orderId, workOrderId, tacheCode);
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

	/* (non-Javadoc)
	 * @see com.basic.order.service.ProcessWorkOrderManageService#callWoDispRuleComponent(java.lang.Long, java.lang.String, java.lang.String)
	 */
	@Override
	public WoDispRuleObean callWoDispRuleComponent(Long orderId, String tacheCode, String dispCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("callWoDispRuleComponent orderId:{},tacheCode:{},dispCode:{}", orderId, tacheCode, dispCode);
		}
		if(null == orderId){
			throw new NullPointerException("不允许为空:"+orderId);
		}
		if(StringUtils.isBlank(tacheCode)){
			throw new NullPointerException("不允许为空:"+tacheCode);
		}
		if(StringUtils.isBlank(dispCode)){
			throw new NullPointerException("不允许为空:"+dispCode);
		}
		
		WoDispRuleComponent woDispRuleComponent = applicationContext.getBean(dispCode, WoDispRuleComponent.class);
		WoDispRuleObean obean = woDispRuleComponent.execComponent(orderId, tacheCode);
		return obean;
	}

	

}
