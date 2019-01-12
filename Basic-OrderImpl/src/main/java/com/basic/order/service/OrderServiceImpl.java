package com.basic.order.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.order.bean.OrderIbean;
import com.basic.order.dao.OrderMapper;
import com.basic.order.define.GidCodes;
import com.basic.order.model.Order;

/**
 * 
 *
 * @date 2017年8月17日 上午11:49:24
 * 
 * @Description: 流程单据
 *
 */
@Service("orderService")
public class OrderServiceImpl implements OrderService{

	private Logger logger = LoggerFactory.getLogger(OrderServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private OrderMapper orderMapper;

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long orderId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey orderId:{}", orderId);
		}
		return orderMapper.deleteByPrimaryKey(orderId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderService#createSelective(com.basic.order.model.Order)
	 */
	@Override
	public int createSelective(Order record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective Order:{}", JSON_UTILS.objectToJson(record));
		}

		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OF_ORDER_SEQ);
		record.setOrderId(gid);
		
		return orderMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public Order qryByPrimaryKey(Long orderId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey orderId:{}", orderId);
		}

		return orderMapper.selectByPrimaryKey(orderId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderService#modifyByPrimaryKeySelective(com.basic.order.model.Order)
	 */
	@Override
	public int modifyByPrimaryKeySelective(Order record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective Order:{}", JSON_UTILS.objectToJson(record));
		}
		
		return orderMapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderService#qryOrderList(com.basic.order.bean.OrderIbean)
	 */
	@Override
	public List<Order> qryOrderList(OrderIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryOrderList OrderIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return orderMapper.selectOrderList(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderService#qryOrderListCount(com.basic.order.bean.OrderIbean)
	 */
	@Override
	public long qryOrderListCount(OrderIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryOrderListCount OrderIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return orderMapper.selectOrderListCount(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderService#qryByOrderId(java.lang.Long)
	 */
	@Override
	public Order qryByOrderId(Long orderId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByOrderId orderId:{}", orderId);
		}

		return orderMapper.selectByOrderId(orderId);
	}

	@Override
	public int updateByPrimaryKeySelective(Order record) {
		if (logger.isDebugEnabled()) {
			logger.debug("updateByPrimaryKeySelective Order:{}", record);
		}
		return orderMapper.updateByPrimaryKeySelective(record);
	}

}
