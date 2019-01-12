package com.basic.order.service;

import com.basic.order.bean.WoDispRuleObean;
import com.basic.order.bean.WorkOrderDel;
import com.basic.order.bean.WorkOrderDisp;
import com.basic.order.bean.WorkOrderFinish;
import com.basic.order.bean.WorkOrderGet;

/**
 * 
 *
 * @date 2017年8月18日 下午2:36:36
 * 
 * @Description: 流程任务服务
 *
 */
public interface ProcessWorkOrderManageService {
	
	/**
	 * 
	 * @date 2017年8月18日 下午2:41:54
	 * 
	 * @Description: 任务提单
	 * @param ibean
	 * @return
	 *
	 */
	public void workOrderGet(WorkOrderGet ibean) throws Exception;
	
	/**
	 * 
	 * @date 2017年8月18日 下午3:36:26
	 * 
	 * @Description: 任务转派
	 * @param ibean
	 * @throws Exception
	 *
	 */
	public void workOrderDisp(WorkOrderDisp ibean) throws Exception;
	
	/**
	 * 
	 * @date 2017年8月18日 下午3:56:25
	 * 
	 * @Description: 任务回单
	 * @param ibean
	 * @throws Exception
	 *
	 */
	public void workOrderFinish(WorkOrderFinish ibean) throws Exception;
	
	/**
	 * 
	 * @date 2017年8月18日 下午4:52:01
	 * 
	 * @Description: 任务回退
	 * 目前的任务回退作用于单线的，多个任务并发的会发或者复杂的回退最好在流程图上实现
	 * @param ibean
	 * @throws Exception
	 *
	 */
	public void workOrderReturn(WorkOrderFinish ibean) throws Exception;
	
	/**
	 * 
	 * @date 2017年8月18日 下午5:01:00
	 * 
	 * @Description: 作废未完成任务
	 * @param ibean
	 * @throws Exception
	 *
	 */
	public void workOrderDel(WorkOrderDel ibean) throws Exception;
	
	/**
	 * 
	 * @date 2017年8月22日 上午10:31:50
	 * 
	 * @Description: 环节自动组件调用
	 * @param orderId
	 * @param workOrderId
	 * @param tacheCode
	 *
	 */
	public void callTacheComponent(Long orderId, Long workOrderId, String tacheCode);
	
	/**
	 * 
	 * @date 2017年8月22日 下午2:44:28
	 * 
	 * @Description: 自定义派单规则调用
	 * @param orderId
	 * @param tacheCode
	 * @param dispCode
	 * @return
	 *
	 */
	public WoDispRuleObean callWoDispRuleComponent(Long orderId, String tacheCode, String dispCode);
	/**
	 * 
	 * @date 2017年12月20日 下午2:53:10
	 * @author lhj
	 * @Description: 流程驳回
	 * @param ibean
	 * @throws Exception
	 *
	 */
	void workOrderToBack(WorkOrderDel ibean) throws Exception;

}
