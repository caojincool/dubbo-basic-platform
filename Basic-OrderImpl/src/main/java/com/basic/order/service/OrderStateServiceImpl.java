package com.basic.order.service;

import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.order.bean.OrderStateIbean;
import com.basic.order.dao.OrderStateMapper;
import com.basic.order.define.BasicStateEnum;
import com.basic.order.define.PageDateTypeEnum;
import com.basic.order.model.OrderState;

/**
 * 
 *
 * @date 2017年8月17日 上午11:49:24
 * 
 * @Description: 单据状态
 *
 */
@Service("orderStateService")
public class OrderStateServiceImpl implements OrderStateService{

	private Logger logger = LoggerFactory.getLogger(OrderStateServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private OrderStateMapper orderStateMapper;

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderStateService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(String orderState) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey orderState:{}", orderState);
		}
		return orderStateMapper.deleteByPrimaryKey(orderState);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderStateService#createSelective(com.basic.order.model.OrderState)
	 */
	@Override
	public int createSelective(OrderState record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective OrderState:{}", JSON_UTILS.objectToJson(record));
		}

//		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.PF_ORDER_STATE_SEQ);
//		record.setFollowUserId(gid);
		
		return orderStateMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderStateService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public OrderState qryByPrimaryKey(String orderState) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey orderState:{}", orderState);
		}

		return orderStateMapper.selectByPrimaryKey(orderState);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderStateService#modifyByPrimaryKeySelective(com.basic.order.model.OrderState)
	 */
	@Override
	public int modifyByPrimaryKeySelective(OrderState record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective OrderState:{}", JSON_UTILS.objectToJson(record));
		}
		
		return orderStateMapper.updateByPrimaryKeySelective(record);
	}
	
	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderStateService#qryOrderStateList(com.basic.order.bean.OrderStateIbean)
	 */
	@Override
	public List<OrderState> qryOrderStateList(OrderStateIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryOrderStateList OrderStateIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return orderStateMapper.selectOrderStateList(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderStateService#qryOrderStateListCount(com.basic.order.bean.OrderStateIbean)
	 */
	@Override
	public long qryOrderStateListCount(OrderStateIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryOrderStateListCount OrderStateIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return orderStateMapper.selectOrderStateListCount(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderStateService#modifyBatchStateByOrderStates(com.basic.order.model.OrderState)
	 */
	@Override
	public int modifyBatchStateByOrderStates(OrderState ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyBatchStateByOrderStates OrderState:{}", JSON_UTILS.objectToJson(ibean));
		}
		int count = 0;
		if(StringUtils.isNotBlank(ibean.getState()) 
				&& ibean.getOrderStates() != null 
				&& ibean.getOrderStates().length > 0){
			count = orderStateMapper.updateBatchStateByOrderStates(ibean);
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderStateService#createOrModify(com.basic.order.model.OrderState)
	 */
	@Override
	public void createOrModify(OrderState ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify OrderState:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		Date now = DateUtils.now();
//		if(StringUtils.isBlank(ibean.getOrderState())){//新增
		if(PageDateTypeEnum.CREATE.getCode().equals(ibean.getPageDateType())){//新增
			ibean.setCreateTime(now);
			ibean.setState(BasicStateEnum.NORMAL.getCode());
			createSelective(ibean);
		}else{//修改
			ibean.setModifyTime(now);
			modifyByPrimaryKeySelective(ibean);
		}	
	}

}
