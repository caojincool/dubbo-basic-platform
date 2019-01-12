package com.basic.order.service;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.order.bean.OrderStateComponentIbean;
import com.basic.order.dao.OrderStateComponentMapper;
import com.basic.order.define.BasicStateEnum;
import com.basic.order.define.GidCodes;
import com.basic.order.define.PageDateTypeEnum;
import com.basic.order.model.OrderStateComponent;

/**
 * 
 *
 * @date 2017年8月17日 上午11:49:24
 * 
 * @Description: 单据状态变更组件
 *
 */
@Service("orderStateComponentService")
public class OrderStateComponentServiceImpl implements OrderStateComponentService{

	private Logger logger = LoggerFactory.getLogger(OrderStateComponentServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private OrderStateComponentMapper orderStateComponentMapper;

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderStateComponentService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long componentId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey componentId:{}", componentId);
		}
		return orderStateComponentMapper.deleteByPrimaryKey(componentId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderStateComponentService#createSelective(com.basic.order.model.OrderStateComponent)
	 */
	@Override
	public int createSelective(OrderStateComponent record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective OrderStateComponent:{}", JSON_UTILS.objectToJson(record));
		}

		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.PF_ORDER_STATE_COMPONENT_SEQ);
		record.setComponentId(gid);
		return orderStateComponentMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderStateComponentService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public OrderStateComponent qryByPrimaryKey(Long componentId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey componentId:{}", componentId);
		}

		return orderStateComponentMapper.selectByPrimaryKey(componentId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderStateComponentService#modifyByPrimaryKeySelective(com.basic.order.model.OrderStateComponent)
	 */
	@Override
	public int modifyByPrimaryKeySelective(OrderStateComponent record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective OrderStateComponent:{}", JSON_UTILS.objectToJson(record));
		}
		
		return orderStateComponentMapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderStateComponentService#qryByOrderState(java.lang.String)
	 */
	@Override
	public List<OrderStateComponent> qryByOrderState(String orderState) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByOrderState orderState:{}", orderState);
		}

		List<OrderStateComponent> list = null;
		if(StringUtils.isNotBlank(orderState)){
			list = orderStateComponentMapper.selectByOrderState(orderState);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderStateComponentService#qryOrderStateList(com.basic.order.bean.OrderStateComponentIbean)
	 */
	@Override
	public List<OrderStateComponent> qryOrderStateComponentList(OrderStateComponentIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryOrderStateComponentList OrderStateComponentIbean:{}", JSON_UTILS.objectToJson(ibean));
		}

		return orderStateComponentMapper.selectOrderStateComponentList(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderStateComponentService#qryOrderStateListCount(com.basic.order.bean.OrderStateComponentIbean)
	 */
	@Override
	public long qryOrderStateComponentListCount(OrderStateComponentIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryOrderStateComponentListCount OrderStateComponentIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		return orderStateComponentMapper.selectOrderStateComponentListCount(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderStateComponentService#createOrModify(com.basic.order.model.OrderStateComponent)
	 */
	@Override
	public void createOrModify(OrderStateComponent ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify OrderPriority:{}", JSON_UTILS.objectToJson(ibean));
		}
		//新增
		if(PageDateTypeEnum.CREATE.getCode().equals(ibean.getPageDateType())){
			ibean.setComponentState(BasicStateEnum.NORMAL.getCode());
			createSelective(ibean);
		}else{
			//修改
			modifyByPrimaryKeySelective(ibean);
		}	
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderStateComponentService#modifyBatchStateByCompnentIds(com.basic.order.model.OrderStateComponent)
	 */
	@Override
	public int modifyBatchStateByComponentIds(OrderStateComponent ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("modifyBatchStateByCompnentIds OrderStateComponent:{}", JSON_UTILS.objectToJson(ibean));
		}
		return orderStateComponentMapper.updateBatchStateByComponentIds(ibean);
	}

}
