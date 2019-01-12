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
import com.basic.order.bean.OrderPriorityIbean;
import com.basic.order.dao.OrderPriorityMapper;
import com.basic.order.define.BasicStateEnum;
import com.basic.order.define.PageDateTypeEnum;
import com.basic.order.model.OrderPriority;

/**
 * 
 *
 * @date 2017年8月17日 上午11:49:24
 * 
 * @Description: 优先级
 *
 */
@Service("orderPriorityService")
public class OrderPriorityServiceImpl implements OrderPriorityService{

	private Logger logger = LoggerFactory.getLogger(OrderPriorityServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private OrderPriorityMapper orderPriorityMapper;

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderPriorityService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Integer orderPriority) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey orderPriority:{}", orderPriority);
		}
		return orderPriorityMapper.deleteByPrimaryKey(orderPriority);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderPriorityService#createSelective(com.basic.order.model.OrderPriority)
	 */
	@Override
	public int createSelective(OrderPriority record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective OrderPriority:{}", JSON_UTILS.objectToJson(record));
		}

//		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.PF_ORDER_PRIORITY_SEQ);
//		record.setFollowUserId(gid);
		
		return orderPriorityMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderPriorityService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public OrderPriority qryByPrimaryKey(Integer orderPriority) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey orderPriority:{}", orderPriority);
		}

		return orderPriorityMapper.selectByPrimaryKey(orderPriority);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderPriorityService#modifyByPrimaryKeySelective(com.basic.order.model.OrderPriority)
	 */
	@Override
	public int modifyByPrimaryKeySelective(OrderPriority record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective OrderPriority:{}", JSON_UTILS.objectToJson(record));
		}
		
		return orderPriorityMapper.updateByPrimaryKeySelective(record);
	}
	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderPriorityService#qryOrderPriorityList(com.basic.order.bean.OrderPriorityIbean)
	 */
	@Override
	public List<OrderPriority> qryOrderPriorityList(OrderPriorityIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryOrderPriorityList OrderPriorityIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return orderPriorityMapper.selectOrderPriorityList(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderPriorityService#qryOrderPriorityListCount(com.basic.order.bean.OrderPriorityIbean)
	 */
	@Override
	public long qryOrderPriorityListCount(OrderPriorityIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryOrderPriorityListCount OrderPriorityIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return orderPriorityMapper.selectOrderPriorityListCount(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderPriorityService#modifyBatchStateByOrderPrioritys(com.basic.order.model.OrderPriority)
	 */
	@Override
	public int modifyBatchStateByOrderPrioritys(OrderPriority ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyBatchStateByOrderPrioritys OrderPriority:{}", JSON_UTILS.objectToJson(ibean));
		}
		int count = 0;
		if(StringUtils.isNotBlank(ibean.getState()) 
				&& ibean.getOrderPrioritys() != null 
				&& ibean.getOrderPrioritys().length > 0){
			count = orderPriorityMapper.updateBatchStateByOrderPrioritys(ibean);
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderPriorityService#createOrModify(com.basic.order.model.OrderPriority)
	 */
	@Override
	public void createOrModify(OrderPriority ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify OrderPriority:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		Date now = DateUtils.now();
//		if(StringUtils.isBlank(ibean.getOrderPriority())){//新增
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
