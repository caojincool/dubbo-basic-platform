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
import com.basic.order.bean.ProcessRuleIbean;
import com.basic.order.dao.ProcessRuleMapper;
import com.basic.order.define.BasicStateEnum;
import com.basic.order.define.GidCodes;
import com.basic.order.define.PageDateTypeEnum;
import com.basic.order.model.ProcessRule;

/**
 * 
 *
 * @date 2017年8月17日 上午11:49:24
 * 
 * @Description: 流程适配规则
 *
 */
@Service("processRuleService")
public class ProcessRuleServiceImpl implements ProcessRuleService{

	private Logger logger = LoggerFactory.getLogger(ProcessRuleServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private ProcessRuleMapper processRuleMapper;

	/* (non-Javadoc)
	 * @see com.basic.order.service.ProcessRuleService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long ruleId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey ruleId:{}", ruleId);
		}
		return processRuleMapper.deleteByPrimaryKey(ruleId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.ProcessRuleService#createSelective(com.basic.order.model.ProcessRule)
	 */
	@Override
	public int createSelective(ProcessRule record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective ProcessRule:{}", JSON_UTILS.objectToJson(record));
		}

		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.PF_PROCESS_RULE_SEQ);
		record.setRuleId(gid);
		
		return processRuleMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.ProcessRuleService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public ProcessRule qryByPrimaryKey(Long ruleId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey ruleId:{}", ruleId);
		}

		return processRuleMapper.selectByPrimaryKey(ruleId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.ProcessRuleService#modifyByPrimaryKeySelective(com.basic.order.model.ProcessRule)
	 */
	@Override
	public int modifyByPrimaryKeySelective(ProcessRule record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective ProcessRule:{}", JSON_UTILS.objectToJson(record));
		}
		
		return processRuleMapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.ProcessRuleService#qryByOrderId(java.lang.Long)
	 */
	@Override
	public ProcessRule qryByOrderId(Long orderId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByOrderId orderId:{}", orderId);
		}
		
		ProcessRule item = null;
		if(orderId != null){
			item = processRuleMapper.selectByOrderId(orderId);
		}
		return item;
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.ProcessRuleService#modifyBatchStateByRuleIds(com.basic.order.model.ProcessRule)
	 */
	@Override
	public int modifyBatchStateByRuleIds(ProcessRule ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyBatchStateByOrderServices OrderService:{}", JSON_UTILS.objectToJson(ibean));
		}
		int count = 0;
		if(StringUtils.isNotBlank(ibean.getState()) 
				&& ibean.getRuleIds() != null 
				&& ibean.getRuleIds().length > 0){
			count = processRuleMapper.updateBatchStateByRuleIds(ibean);
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.ProcessRuleService#createOrModify(com.basic.order.model.ProcessRule)
	 */
	@Override
	public void createOrModify(ProcessRule bean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify OrderService:{}", JSON_UTILS.objectToJson(bean));
		}
		
		Date now = DateUtils.now();
		if(PageDateTypeEnum.CREATE.getCode().equals(bean.getPageDateType())){//新增
			bean.setCreateTime(now);
			bean.setState(BasicStateEnum.NORMAL.getCode());
			createSelective(bean);
		}else{//修改
			bean.setModifyTime(now);
			modifyByPrimaryKeySelective(bean);
		}	
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.ProcessRuleService#qryProcessRuleList(com.basic.order.bean.ProcessRuleIbean)
	 */
	@Override
	public List<ProcessRule> qryProcessRuleList(
			ProcessRuleIbean processRulebean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryProcessRuleList ProcessRuleIbean:{}", JSON_UTILS.objectToJson(processRulebean));
		}
		return processRuleMapper.selectProcessRuleList(processRulebean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.ProcessRuleService#qryProcessRuleListCount(com.basic.order.bean.ProcessRuleIbean)
	 */
	@Override
	public long qryProcessRuleListCount(ProcessRuleIbean processRulebean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryProcessRuleListCount ProcessRuleIbean:{}", JSON_UTILS.objectToJson(processRulebean));
		}
		
		return processRuleMapper.selectProcessRuleListCount(processRulebean);
	}



}
