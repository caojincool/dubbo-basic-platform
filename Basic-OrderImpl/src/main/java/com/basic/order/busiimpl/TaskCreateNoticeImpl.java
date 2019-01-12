package com.basic.order.busiimpl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.Exceptions;
import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.LongUtils;
import com.basic.framework.workflow.busi.TaskCreateNotice;
import com.basic.order.bean.WoDispRuleInstIbean;
import com.basic.order.bean.WoDispRuleObean;
import com.basic.order.define.OrderStateEnum;
import com.basic.order.define.RuleTypeEnum;
import com.basic.order.define.WorkOrderStateEnum;
import com.basic.order.model.Order;
import com.basic.order.model.Tache;
import com.basic.order.model.WoDispRule;
import com.basic.order.model.WoDispRuleInst;
import com.basic.order.model.WorkOrder;
import com.basic.order.service.OrderService;
import com.basic.order.service.ProcessWorkOrderManageService;
import com.basic.order.service.TacheService;
import com.basic.order.service.WoDispRuleInstService;
import com.basic.order.service.WoDispRuleService;
import com.basic.order.service.WorkOrderService;

/**
 * 
 *
 * @date 2017年8月21日 上午10:33:32
 * 
 * @Description: 任务创建通知
 *
 */
@Service("taskCreateNotice")
public class TaskCreateNoticeImpl implements TaskCreateNotice{

	private static final Logger logger = LoggerFactory.getLogger(TaskCreateNoticeImpl.class);

	private static final int ERROR_RESULT_LENGTH = 256;
	
	@Autowired
	private TacheService tacheService;
	@Autowired
	private WoDispRuleService woDispRuleService;
	@Autowired
	private WoDispRuleInstService woDispRuleInstService;
	@Autowired
	private WorkOrderService workOrderService;
	@Autowired
	private ProcessWorkOrderManageService processWorkOrderManageService;
	@Autowired
	private OrderService orderService;
	
	/* (non-Javadoc)
	 * @see com.basic.framework.workflow.busi.TaskCreateNotice#taskCreate(java.lang.String, java.lang.String, java.lang.Long)
	 */
	@Override
	public void taskCreate(String taskId, String taskDefinitionKey, Long busiOrderId) {
		logger.debug("任务创建 taskId:{}, taskDefinitionKey:{}, busiOrderId:{}", taskId, taskDefinitionKey, busiOrderId);
		
		//查询环节
		Tache tache = tacheService.qryByTacheCode(taskDefinitionKey);
		//查询任务派发规则
		WoDispRule woDispRule = woDispRuleService.qryByTacheId(tache.getTacheId());
		
		//查询任务派发规则实例，获取对应的接收人、接收人类型
		WoDispRuleInstIbean ibean = new WoDispRuleInstIbean();
		ibean.setRuleId(woDispRule.getRuleId());
		ibean.setRuleType(woDispRule.getRuleType());
		ibean.setOrderId(busiOrderId);
		WoDispRuleInst woDispRuleInst = null;
		if(RuleTypeEnum.AREA.getCode().equals(woDispRule.getRuleType())){//按区域派单
			woDispRuleInst = woDispRuleInstService.qryForArea(ibean);
			
		}else if(RuleTypeEnum.ORG.getCode().equals(woDispRule.getRuleType())){//按组织派单
			woDispRuleInst = woDispRuleInstService.qryForOrg(ibean);
			
		}else if(RuleTypeEnum.USER.getCode().equals(woDispRule.getRuleType())){//按用户派单
			woDispRuleInst = woDispRuleInstService.qryForUser(ibean);
			
		}else if(RuleTypeEnum.STA.getCode().equals(woDispRule.getRuleType())){//按人员派单
			woDispRuleInst = woDispRuleInstService.qryForStaff(ibean);
			
		}else if(RuleTypeEnum.SELF.getCode().equals(woDispRule.getRuleType())){//自定义派单
			WoDispRuleObean obean = processWorkOrderManageService.callWoDispRuleComponent(busiOrderId, tache.getTacheCode(), woDispRule.getDispCode());
			if(obean != null){
				woDispRuleInst = new WoDispRuleInst();
				woDispRuleInst.setPartyId(obean.getPartyId());
				woDispRuleInst.setPartyName(obean.getPartyName());
				woDispRuleInst.setPartyType(obean.getPartyType());
			}
		}
		
		//创建流程任务单
		Date now = DateUtils.now();
		WorkOrder workOrder = new WorkOrder();
//		workOrder.setworkOrderId
		workOrder.setOrderId(busiOrderId);
		workOrder.setTacheId(tache.getTacheId());
		workOrder.setWorkOrderCode(null);
		workOrder.setTaskId(LongUtils.valueOf(taskId));
		workOrder.setTacheCode(tache.getTacheCode());
		if(woDispRuleInst != null){
			workOrder.setPartyId(woDispRuleInst.getPartyId());
			workOrder.setPartyType(woDispRuleInst.getPartyType());
			workOrder.setPartyName(woDispRuleInst.getPartyName());
			workOrder.setWorkOrderState(WorkOrderStateEnum.TEN_I.getCode());//未处理
		}else{
			workOrder.setWorkOrderState(WorkOrderStateEnum.TEN_E.getCode());//异常
			workOrder.setErrorCode(null);
			workOrder.setErrInfo("没有派单规则");
		}
		workOrder.setStateTime(now);
		workOrder.setCreateTime(now);
		workOrder.setAlterTime(null);
		workOrder.setOverTime(null);
		workOrder.setWorkOrderType(null);
		workOrder.setFinishUserId(null);
		workOrder.setFinishUserText(null);
		workOrder.setFinishOrgId(null);
		workOrder.setFinishOrgName(null);
		workOrder.setFinishTime(null);
		workOrder.setFinishBusiTime(null);
		workOrder.setCostTime(null);
		workOrder.setCostWorkTime(null);
		workOrder.setWorkOrderComments(null);
		
		try{
			workOrderService.createSelective(workOrder);
		}catch(Exception e){
			//修改单据状态为异常
			Order order = new Order();
			order.setOrderId(workOrder.getOrderId());
			order.setOrderState(OrderStateEnum.ERROR.getCode());//异常
			order.setStateTime(now);
			order.setErrorCode(null);
			String errorMsg = Exceptions.getStackTraceAsString(e, ERROR_RESULT_LENGTH);
			order.setErrInfo("创建流程任务单异常："+errorMsg);
			orderService.modifyByPrimaryKeySelective(order);
			throw new RuntimeException();
		}
		
		try{
			//调用环节自动组件
			processWorkOrderManageService.callTacheComponent(workOrder.getOrderId(), workOrder.getWorkOrderId(), workOrder.getTacheCode());
		}catch(Exception e){
			//修改流程任务单为异常
			WorkOrder errorWorkorder = new WorkOrder();
			errorWorkorder.setWorkOrderId(workOrder.getWorkOrderId());
			errorWorkorder.setWorkOrderState(WorkOrderStateEnum.TEN_E.getCode());//异常
			errorWorkorder.setErrorCode(null);
			String errorMsg = Exceptions.getStackTraceAsString(e, ERROR_RESULT_LENGTH);
			errorWorkorder.setErrInfo("调用环节自动组件异常："+errorMsg);
			workOrderService.modifyByPrimaryKeySelective(errorWorkorder);
		}
	}

}
