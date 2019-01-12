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
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.order.bean.OrderServiceIbean;
import com.basic.order.dao.OrderServiceMapper;
import com.basic.order.define.BasicStateEnum;
import com.basic.order.define.GidCodes;
import com.basic.order.define.PageDateTypeEnum;
import com.basic.order.model.OrderService;

/**
 * 
 *
 * @date 2017年8月17日 上午11:49:24
 * 
 * @Description: 服务
 *
 */
@Service("orderServiceService")
public class OrderServiceServiceImpl implements OrderServiceService{

	private Logger logger = LoggerFactory.getLogger(OrderServiceServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private OrderServiceMapper orderServiceMapper;

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderServiceService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long serviceId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey serviceId:{}", serviceId);
		}
		return orderServiceMapper.deleteByPrimaryKey(serviceId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderServiceService#createSelective(com.basic.order.model.OrderService)
	 */
	@Override
	public int createSelective(OrderService record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective OrderService:{}", JSON_UTILS.objectToJson(record));
		}

		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.PF_ORDER_SERVICE_SEQ);
		record.setServiceId(gid);
		
		return orderServiceMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderServiceService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public OrderService qryByPrimaryKey(Long serviceId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey serviceId:{}", serviceId);
		}

		return orderServiceMapper.selectByPrimaryKey(serviceId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderServiceService#modifyByPrimaryKeySelective(com.basic.order.model.OrderService)
	 */
	@Override
	public int modifyByPrimaryKeySelective(OrderService record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective OrderService:{}", JSON_UTILS.objectToJson(record));
		}
		
		return orderServiceMapper.updateByPrimaryKeySelective(record);
	}
	/*
	 * (non-Javadoc)
	 * @see com.basic.order.service.OrderServiceService#qryByServiceCode(java.lang.String)
	 */
	@Override
	public OrderService qryByServiceCode(String serviceCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByServiceCode serviceCode:{}", serviceCode);
		}
		return orderServiceMapper.selectByServiceCode(serviceCode);
	}
	/*
	 * (non-Javadoc)
	 * @see com.basic.order.service.OrderServiceService#modifyBatchStateByServiceIds(com.basic.order.model.OrderService)
	 */
	@Override
	public int modifyBatchStateByServiceIds(OrderService ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyBatchStateByOrderServices OrderService:{}", JSON_UTILS.objectToJson(ibean));
		}
		int count = 0;
		if(StringUtils.isNotBlank(ibean.getState()) 
				&& ibean.getServiceIds() != null 
				&& ibean.getServiceIds().length > 0){
			count = orderServiceMapper.updateBatchStateByServiceIds(ibean);
		}
		return count;
	}
	/*
	 * (non-Javadoc)
	 * @see com.basic.order.service.OrderServiceService#createOrModify(com.basic.order.model.OrderService)
	 */
	@Override
	public void createOrModify(OrderService ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify OrderService:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		Date now = DateUtils.now();
		if(PageDateTypeEnum.CREATE.getCode().equals(ibean.getPageDateType())){//新增
			ibean.setCreateTime(now);
			ibean.setState(BasicStateEnum.NORMAL.getCode());
			createSelective(ibean);
		}else{//修改
			ibean.setModifyTime(now);
			modifyByPrimaryKeySelective(ibean);
		}	
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderServiceService#qryOrderPriorityListCount(com.basic.order.bean.OrderServicebean)
	 */
	@Override
	public long qryOrderServiceListCount(OrderServiceIbean orderServicebean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryOrderServiceListCount OrderService:{}", JSON_UTILS.objectToJson(orderServicebean));
		}
		
		return orderServiceMapper.selectOrderServiceListCount(orderServicebean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderServiceService#selectOrderServiceList()
	 */
	@Override
	public List<OrderService> qryOrderServiceList(OrderServiceIbean orderServicebean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryOrderServiceListCount OrderServiceIbean:{}", JSON_UTILS.objectToJson(orderServicebean));
		}
		return orderServiceMapper.selectOrderServiceList(orderServicebean);
	}


}
