package com.basic.order.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.order.bean.WorkOrderIbean;
import com.basic.order.dao.OrderOperMapper;
import com.basic.order.define.GidCodes;
import com.basic.order.model.OrderOper;

/**
 * 
 *
 * @date 2017年8月17日 上午11:49:24
 * 
 * @Description: 流程单据操作记录
 *
 */
@Service("orderOperService")
public class OrderOperServiceImpl implements OrderOperService{

	private Logger logger = LoggerFactory.getLogger(OrderOperServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private OrderOperMapper orderOperMapper;

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderOperService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long operId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey operId:{}", operId);
		}
		return orderOperMapper.deleteByPrimaryKey(operId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderOperService#createSelective(com.basic.order.model.OrderOper)
	 */
	@Override
	public int createSelective(OrderOper record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective OrderOper:{}", JSON_UTILS.objectToJson(record));
		}

		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OF_ORDER_OPER_SEQ);
		record.setOperId(gid);
		
		return orderOperMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderOperService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public OrderOper qryByPrimaryKey(Long operId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey operId:{}", operId);
		}

		return orderOperMapper.selectByPrimaryKey(operId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderOperService#modifyByPrimaryKeySelective(com.basic.order.model.OrderOper)
	 */
	@Override
	public int modifyByPrimaryKeySelective(OrderOper record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective OrderOper:{}", JSON_UTILS.objectToJson(record));
		}
		
		return orderOperMapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderOperService#qryOrderOperList(com.basic.order.bean.WorkOrderIbean)
	 */
	@Override
	public List<OrderOper> qryOrderOperList(WorkOrderIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryOrderOperList WorkOrderIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		List<OrderOper> list = null;
		if(ibean.getOrderId() != null){
			list = orderOperMapper.selectOrderOperList(ibean);
		}
		return list;
	}

}
