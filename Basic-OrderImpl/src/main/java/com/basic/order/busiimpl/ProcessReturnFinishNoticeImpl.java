package com.basic.order.busiimpl;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.workflow.busi.ProcessReturnFinishNotice;
import com.basic.order.define.OrderStateEnum;
import com.basic.order.model.Order;
import com.basic.order.service.OrderService;
import com.basic.order.service.ProcessOrderManageService;

/**
 * 
 *
 * @date 2017年8月21日 上午10:32:57
 * 
 * @Description: 流程回退完成通知
 *
 */
@Service("processReturnFinishNotice")
public class ProcessReturnFinishNoticeImpl implements ProcessReturnFinishNotice{

	private static final Logger logger = LoggerFactory.getLogger(ProcessReturnFinishNoticeImpl.class);

	@Autowired
	private OrderService orderService;
	@Autowired
	private ProcessOrderManageService processOrderManageService;
	
	/* (non-Javadoc)
	 * @see com.basic.framework.workflow.busi.ProcessBackNotice#processBack(java.lang.Long, java.lang.String)
	 */
	@Override
	public void processReturnFinish(Long busiOrderId, String processInstanceId) {
		logger.debug("流程回退完成通知 busiOrderId:{}, processInstanceId:{}", busiOrderId, processInstanceId);
		
		//更新OF_ORDER，状态为已回退
		Date now = DateUtils.now();
		Order order = new Order();
		order.setOrderId(busiOrderId);
		order.setOrderState(OrderStateEnum.RETURN.getCode());//已回退
		order.setStateTime(now);
		orderService.modifyByPrimaryKeySelective(order);
		
		//调用状态组件
		processOrderManageService.callOrderStateChgComponent(order.getOrderId(), order.getOrderState());
		
	}

}
