package com.basic.order.busiimpl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.LongUtils;
import com.basic.framework.workflow.busi.ProcessStartNotice;
import com.basic.order.define.OrderStateEnum;
import com.basic.order.model.Order;
import com.basic.order.service.OrderService;
import com.basic.order.service.ProcessOrderManageService;

/**
 * 
 *
 * @date 2017年8月21日 上午10:33:17
 * 
 * @Description: 流程启动通知
 *
 */
@Service("processStartNotice")
public class ProcessStartNoticeImpl implements ProcessStartNotice{

	private static final Logger logger = LoggerFactory.getLogger(ProcessStartNoticeImpl.class);

	@Autowired
	private OrderService orderService;
	@Autowired
	private ProcessOrderManageService processOrderManageService;
	
	/* (non-Javadoc)
	 * @see com.basic.framework.workflow.busi.ProcessStartNotice#processStart(java.lang.Long, java.lang.String)
	 */
	@Override
	public void processStart(Long busiOrderId, String processInstanceId) {
		logger.debug("流程启动 busiOrderId:{}, processInstanceId:{}", busiOrderId, processInstanceId);
		
		//更新OF_ORDER，状态正常执行中，流程实例ID
		Date now = DateUtils.now();
		Order order = new Order();
		order.setOrderId(busiOrderId);
		order.setProcessInstanceId(LongUtils.valueOf(processInstanceId));
		order.setOrderState(OrderStateEnum.NORMAL.getCode());//正常执行中
		order.setStateTime(now);
		orderService.modifyByPrimaryKeySelective(order);
		
		//调用状态组件
		processOrderManageService.callOrderStateChgComponent(order.getOrderId(), order.getOrderState());
		
	}

}
