package com.basic.order.busiimpl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.workflow.busi.ProcessCompleteNotice;
import com.basic.order.define.OrderStateEnum;
import com.basic.order.model.Order;
import com.basic.order.service.OrderService;
import com.basic.order.service.ProcessOrderManageService;

/**
 * 
 *
 * @date 2017年8月21日 上午10:32:25
 * 
 * @Description: 流程报竣通知
 *
 */
@Service("processCompleteNotice")
public class ProcessCompleteNoticeImpl implements ProcessCompleteNotice{

	private static final Logger logger = LoggerFactory.getLogger(ProcessCompleteNoticeImpl.class);

	@Autowired
	private OrderService orderService;
	@Autowired
	private ProcessOrderManageService processOrderManageService;
	
	/* (non-Javadoc)
	 * @see com.basic.framework.workflow.busi.ProcessCompleteNotice#processComplete(java.lang.Long, java.lang.String)
	 */
	@Override
	public void processComplete(Long busiOrderId, String processInstanceId) {
		logger.debug("流程报竣 busiOrderId:{}, processInstanceId:{}", busiOrderId, processInstanceId);
		
		//更新OF_ORDER，状态为已完成
		Date now = DateUtils.now();
		Order order = new Order();
		order.setOrderId(busiOrderId);
		order.setOrderState(OrderStateEnum.FINISH.getCode());//竣工
		order.setStateTime(now);
		orderService.modifyByPrimaryKeySelective(order);
		
		//调用状态组件
		processOrderManageService.callOrderStateChgComponent(order.getOrderId(), order.getOrderState());
		
	}

}
