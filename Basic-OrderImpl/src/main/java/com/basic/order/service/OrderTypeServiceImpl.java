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
import com.basic.order.bean.OrderTypeIbean;
import com.basic.order.dao.OrderTypeMapper;
import com.basic.order.define.BasicStateEnum;
import com.basic.order.define.PageDateTypeEnum;
import com.basic.order.model.OrderType;

/**
 * 
 *
 * @date 2017年8月17日 上午11:49:24
 * 
 * @Description: 单据类型
 *
 */
@Service("orderTypeService")
public class OrderTypeServiceImpl implements OrderTypeService{

	private Logger logger = LoggerFactory.getLogger(OrderTypeServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private OrderTypeMapper orderTypeMapper;

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderTypeService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(String orderType) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey orderType:{}", orderType);
		}
		return orderTypeMapper.deleteByPrimaryKey(orderType);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderTypeService#createSelective(com.basic.order.model.OrderType)
	 */
	@Override
	public int createSelective(OrderType record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective OrderType:{}", JSON_UTILS.objectToJson(record));
		}

//		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.PF_ORDER_TYPE_SEQ);
//		record.setFollowUserId(gid);
		
		return orderTypeMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderTypeService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public OrderType qryByPrimaryKey(String orderType) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey orderType:{}", orderType);
		}

		return orderTypeMapper.selectByPrimaryKey(orderType);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderTypeService#modifyByPrimaryKeySelective(com.basic.order.model.OrderType)
	 */
	@Override
	public int modifyByPrimaryKeySelective(OrderType record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective OrderType:{}", JSON_UTILS.objectToJson(record));
		}
		
		return orderTypeMapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderTypeService#qryOrderTypeList(com.basic.order.bean.OrderTypeIbean)
	 */
	@Override
	public List<OrderType> qryOrderTypeList(OrderTypeIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryOrderTypeList OrderTypeIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return orderTypeMapper.selectOrderTypeList(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderTypeService#qryOrderTypeListCount(com.basic.order.bean.OrderTypeIbean)
	 */
	@Override
	public long qryOrderTypeListCount(OrderTypeIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryOrderTypeListCount OrderTypeIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return orderTypeMapper.selectOrderTypeListCount(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderTypeService#modifyBatchStateByOrderTypes(com.basic.order.model.OrderType)
	 */
	@Override
	public int modifyBatchStateByOrderTypes(OrderType ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyBatchStateByOrderTypes OrderType:{}", JSON_UTILS.objectToJson(ibean));
		}
		int count = 0;
		if(StringUtils.isNotBlank(ibean.getState()) 
				&& ibean.getOrderTypes() != null 
				&& ibean.getOrderTypes().length > 0){
			count = orderTypeMapper.updateBatchStateByOrderTypes(ibean);
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderTypeService#createOrModify(com.basic.order.model.OrderType)
	 */
	@Override
	public void createOrModify(OrderType ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify OrderType:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		Date now = DateUtils.now();
//		if(StringUtils.isBlank(ibean.getOrderType())){//新增
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
