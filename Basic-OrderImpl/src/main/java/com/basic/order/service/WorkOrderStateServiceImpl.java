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
import com.basic.order.bean.WorkOrderStateIbean;
import com.basic.order.dao.WorkOrderStateMapper;
import com.basic.order.define.BasicStateEnum;
import com.basic.order.define.PageDateTypeEnum;
import com.basic.order.model.WorkOrderState;

/**
 * 
 *
 * @date 2017年9月6日 上午11:28:12
 * @author wangkui
 * @Description: 任务单状态
 *
 */
@Service("workOrderStateService")
public class WorkOrderStateServiceImpl implements WorkOrderStateService{

	private Logger logger = LoggerFactory.getLogger(WorkOrderStateServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private WorkOrderStateMapper workOrderStateMapper;

	/* (non-Javadoc)
	 * @see com.basic.order.service.WorkOrderStateService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(String workOrderState) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey workOrderState:{}", workOrderState);
		}
		return workOrderStateMapper.deleteByPrimaryKey(workOrderState);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WorkOrderStateService#createSelective(com.basic.order.model.WorkOrderState)
	 */
	@Override
	public int createSelective(WorkOrderState record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective WorkOrderState:{}", JSON_UTILS.objectToJson(record));
		}

//		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.PF_WORK_ORDER_STATE_SEQ);
//		record.setFollowUserId(gid);
		
		return workOrderStateMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WorkOrderStateService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public WorkOrderState qryByPrimaryKey(String workOrderState) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey workOrderState:{}", workOrderState);
		}

		return workOrderStateMapper.selectByPrimaryKey(workOrderState);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WorkOrderStateService#modifyByPrimaryKeySelective(com.basic.order.model.WorkOrderState)
	 */
	@Override
	public int modifyByPrimaryKeySelective(WorkOrderState record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective WorkOrderState:{}", JSON_UTILS.objectToJson(record));
		}
		
		return workOrderStateMapper.updateByPrimaryKeySelective(record);
	}
	
	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderStateService#qryOrderStateList(com.basic.order.bean.OrderStateIbean)
	 */
	@Override
	public List<WorkOrderState> qryWorkOrderStateList(WorkOrderStateIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryOrderStateList OrderStateIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return workOrderStateMapper.selectWorkOrderStateList(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderStateService#qryOrderStateListCount(com.basic.order.bean.OrderStateIbean)
	 */
	@Override
	public long qryWorkOrderStateListCount(WorkOrderStateIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryOrderStateListCount OrderStateIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return workOrderStateMapper.selectWorkOrderStateListCount(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderStateService#modifyBatchStateByOrderStates(com.basic.order.model.OrderState)
	 */
	@Override
	public int modifyBatchStateByWorkOrderStates(WorkOrderState ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyBatchStateByOrderStates OrderState:{}", JSON_UTILS.objectToJson(ibean));
		}
		int count = 0;
		if(StringUtils.isNotBlank(ibean.getState()) 
				&& ibean.getWorkOrderStates() != null 
				&& ibean.getWorkOrderStates().length > 0){
			count = workOrderStateMapper.updateBatchStateByWorkOrderStates(ibean);
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.OrderStateService#createOrModify(com.basic.order.model.OrderState)
	 */
	@Override
	public void createOrModify(WorkOrderState ibean) {
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
