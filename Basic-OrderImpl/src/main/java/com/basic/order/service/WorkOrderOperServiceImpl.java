package com.basic.order.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.order.bean.WorkOrderIbean;
import com.basic.order.dao.WorkOrderOperMapper;
import com.basic.order.define.GidCodes;
import com.basic.order.model.WorkOrderOper;

/**
 * 
 *
 * @date 2017年8月17日 上午11:49:24
 * 
 * @Description: 流程任务操作记录
 *
 */
@Service("workOrderOperService")
public class WorkOrderOperServiceImpl implements WorkOrderOperService{

	private Logger logger = LoggerFactory.getLogger(WorkOrderOperServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private WorkOrderOperMapper workOrderOperMapper;

	/* (non-Javadoc)
	 * @see com.basic.order.service.WorkOrderOperService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long operId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey operId:{}", operId);
		}
		return workOrderOperMapper.deleteByPrimaryKey(operId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WorkOrderOperService#createSelective(com.basic.order.model.WorkOrderOper)
	 */
	@Override
	public int createSelective(WorkOrderOper record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective WorkOrderOper:{}", JSON_UTILS.objectToJson(record));
		}

		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OF_WORK_ORDER_OPER_SEQ);
		record.setOperId(gid);
		
		return workOrderOperMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WorkOrderOperService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public WorkOrderOper qryByPrimaryKey(Long operId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey operId:{}", operId);
		}

		return workOrderOperMapper.selectByPrimaryKey(operId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WorkOrderOperService#modifyByPrimaryKeySelective(com.basic.order.model.WorkOrderOper)
	 */
	@Override
	public int modifyByPrimaryKeySelective(WorkOrderOper record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective WorkOrderOper:{}", JSON_UTILS.objectToJson(record));
		}
		
		return workOrderOperMapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WorkOrderOperService#qryWorkOrderOperList(com.basic.order.bean.WorkOrderIbean)
	 */
	@Override
	public List<WorkOrderOper> qryWorkOrderOperList(WorkOrderIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryWorkOrderOperList WorkOrderIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		List<WorkOrderOper> list = null;
		if(ibean.getWorkOrderId() != null){
			list = workOrderOperMapper.selectWorkOrderOperList(ibean);
		}
		return list;
	}

}
