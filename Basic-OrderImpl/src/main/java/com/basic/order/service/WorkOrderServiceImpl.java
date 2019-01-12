package com.basic.order.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.order.bean.WorkOrderIbean;
import com.basic.order.dao.WorkOrderMapper;
import com.basic.order.define.GidCodes;
import com.basic.order.model.WorkOrder;

/**
 * 
 *
 * @date 2017年8月17日 上午11:49:24
 * 
 * @Description: 流程任务单
 *
 */
@Service("workOrderService")
public class WorkOrderServiceImpl implements WorkOrderService{

	private Logger logger = LoggerFactory.getLogger(WorkOrderServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private WorkOrderMapper workOrderMapper;

	/* (non-Javadoc)
	 * @see com.basic.order.service.WorkOrderService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long workOrderId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey workOrderId:{}", workOrderId);
		}
		return workOrderMapper.deleteByPrimaryKey(workOrderId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WorkOrderService#createSelective(com.basic.order.model.WorkOrder)
	 */
	@Override
	public int createSelective(WorkOrder record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective WorkOrder:{}", JSON_UTILS.objectToJson(record));
		}

		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OF_WORK_ORDER_SEQ);
		record.setWorkOrderId(gid);
		
		return workOrderMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WorkOrderService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public WorkOrder qryByPrimaryKey(Long workOrderId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey workOrderId:{}", workOrderId);
		}

		return workOrderMapper.selectByPrimaryKey(workOrderId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WorkOrderService#modifyByPrimaryKeySelective(com.basic.order.model.WorkOrder)
	 */
	@Override
	public int modifyByPrimaryKeySelective(WorkOrder record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective WorkOrder:{}", JSON_UTILS.objectToJson(record));
		}
		
		return workOrderMapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WorkOrderService#qryByOrderId(java.lang.Long)
	 */
	@Override
	public List<WorkOrder> qryByOrderId(Long orderId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByOrderId orderId:{}", orderId);
		}

		List<WorkOrder> list = null;
		if(orderId != null){
			list = workOrderMapper.selectByOrderId(orderId);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WorkOrderService#qryWorkOrderList(com.basic.order.bean.WorkOrderIbean)
	 */
	@Override
	public List<WorkOrder> qryWorkOrderList(WorkOrderIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryWorkOrderList WorkOrderIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return workOrderMapper.selectWorkOrderList(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WorkOrderService#qryWorkOrderListCount(com.basic.order.bean.WorkOrderIbean)
	 */
	@Override
	public long qryWorkOrderListCount(WorkOrderIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryWorkOrderListCount WorkOrderIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return workOrderMapper.selectWorkOrderListCount(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WorkOrderService#qryByWorkOrderId(java.lang.Long)
	 */
	@Override
	public WorkOrder qryByWorkOrderId(Long workOrderId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByWorkOrderId workOrderId:{}", workOrderId);
		}

		return workOrderMapper.selectByWorkOrderId(workOrderId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WorkOrderService#qryPartyWorkOrder(com.basic.order.bean.WorkOrderIbean)
	 */
	@Override
	public List<WorkOrder> qryPartyWorkOrder(WorkOrderIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryPartyWorkOrder WorkOrderIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return workOrderMapper.selectPartyWorkOrder(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WorkOrderService#qryPartyWorkOrderCount(com.basic.order.bean.WorkOrderIbean)
	 */
	@Override
	public long qryPartyWorkOrderCount(WorkOrderIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryPartyWorkOrderCount WorkOrderIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return workOrderMapper.selectPartyWorkOrderCount(ibean);
	}

	@Override
	public List<WorkOrder> selectWorkOrderByTargetCode(WorkOrder workOrder) {
		if (logger.isDebugEnabled()) {
			logger.debug("selectWorkOrderByTargetCode WorkOrder:{}", JSON_UTILS.objectToJson(workOrder));
		}
		return workOrderMapper.selectWorkOrderByTargetCode(workOrder);
	}

}
