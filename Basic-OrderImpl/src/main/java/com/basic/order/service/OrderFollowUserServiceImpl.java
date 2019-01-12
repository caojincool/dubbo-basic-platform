package com.basic.order.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.order.bean.WorkOrderIbean;
import com.basic.order.dao.OrderFollowUserMapper;
import com.basic.order.define.GidCodes;
import com.basic.order.model.OrderFollowUser;

/**
 * 
 *
 * @date 2017年8月17日 上午11:49:24
 * 
 * @Description: 流程关注人
 *
 */
@Service("orderFollowUserService")
public class OrderFollowUserServiceImpl implements OrderFollowUserService{

	private Logger logger = LoggerFactory.getLogger(OrderFollowUserServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private OrderFollowUserMapper orderFollowUserMapper;

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderFollowUserService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long followUserId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey followUserId:{}", followUserId);
		}
		return orderFollowUserMapper.deleteByPrimaryKey(followUserId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderFollowUserService#createSelective(com.basic.order.model.OrderFollowUser)
	 */
	@Override
	public int createSelective(OrderFollowUser record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective OrderFollowUser:{}", JSON_UTILS.objectToJson(record));
		}

		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OF_ORDER_FOLLOW_USER_SEQ);
		record.setFollowUserId(gid);
		
		return orderFollowUserMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderFollowUserService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public OrderFollowUser qryByPrimaryKey(Long followUserId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey followUserId:{}", followUserId);
		}

		return orderFollowUserMapper.selectByPrimaryKey(followUserId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderFollowUserService#modifyByPrimaryKeySelective(com.basic.order.model.OrderFollowUser)
	 */
	@Override
	public int modifyByPrimaryKeySelective(OrderFollowUser record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective OrderFollowUser:{}", JSON_UTILS.objectToJson(record));
		}
		
		return orderFollowUserMapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderFollowUserService#qryOrderFollowUserList(com.basic.order.bean.WorkOrderIbean)
	 */
	@Override
	public List<OrderFollowUser> qryOrderFollowUserList(WorkOrderIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryOrderFollowUserList WorkOrderIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		List<OrderFollowUser> list = null;
		if(ibean.getOrderId() != null){
			list = orderFollowUserMapper.selectOrderFollowUserList(ibean);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderFollowUserService#createBatchOrderFollowUser(java.util.List)
	 */
	@Override
	public int createBatchOrderFollowUser(List<OrderFollowUser> list) {
		if (logger.isDebugEnabled()) {
			logger.debug("createBatchOrderFollowUser List<OrderFollowUser>:{}", JSON_UTILS.objectToJson(list));
		}
		
		int count = 0;
		if(list != null && list.size() > 0){
			count = orderFollowUserMapper.insertBatchOrderFollowUser(list);
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderFollowUserService#removeBatchByFollowUserIds(java.lang.Long[])
	 */
	@Override
	public int removeBatchByFollowUserIds(Long[] followUserIds) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeBatchByFollowUserIds Long[]:{}", JSON_UTILS.objectToJson(followUserIds));
		}
		
		int count = 0;
		if(followUserIds != null && followUserIds.length > 0){
			count = orderFollowUserMapper.deleteBatchByFollowUserIds(followUserIds);
		}
		return count;
	}

}
