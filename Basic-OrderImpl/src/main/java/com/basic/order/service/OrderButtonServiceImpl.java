package com.basic.order.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.order.dao.OrderButtonMapper;
import com.basic.order.define.GidCodes;
import com.basic.order.model.OrderButton;

/**
 * 
 *
 * @date 2017年8月17日 上午11:49:24
 * 
 * @Description: 单据按钮
 *
 */
@Service("orderButtonService")
public class OrderButtonServiceImpl implements OrderButtonService{

	private Logger logger = LoggerFactory.getLogger(OrderButtonServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private OrderButtonMapper orderButtonMapper;

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderButtonService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long orderButtonId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey orderButtonId:{}", orderButtonId);
		}
		return orderButtonMapper.deleteByPrimaryKey(orderButtonId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderButtonService#createSelective(com.basic.order.model.OrderButton)
	 */
	@Override
	public int createSelective(OrderButton record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective OrderButton:{}", JSON_UTILS.objectToJson(record));
		}

		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.PF_ORDER_BUTTON_SEQ);
		record.setOrderButtonId(gid);
		
		return orderButtonMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderButtonService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public OrderButton qryByPrimaryKey(Long orderButtonId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey orderButtonId:{}", orderButtonId);
		}

		return orderButtonMapper.selectByPrimaryKey(orderButtonId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderButtonService#modifyByPrimaryKeySelective(com.basic.order.model.OrderButton)
	 */
	@Override
	public int modifyByPrimaryKeySelective(OrderButton record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective OrderButton:{}", JSON_UTILS.objectToJson(record));
		}
		
		return orderButtonMapper.updateByPrimaryKeySelective(record);
	}

}
