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
import com.basic.order.bean.WoDispRuleIbean;
import com.basic.order.dao.WoDispRuleMapper;
import com.basic.order.define.BasicStateEnum;
import com.basic.order.define.GidCodes;
import com.basic.order.define.PageDateTypeEnum;
import com.basic.order.model.WoDispRule;

/**
 * 
 *
 * @date 2017年8月17日 上午11:49:24
 * 
 * @Description: 任务派发规则
 *
 */
@Service("woDispRuleService")
public class WoDispRuleServiceImpl implements WoDispRuleService{

	private Logger logger = LoggerFactory.getLogger(WoDispRuleServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private WoDispRuleMapper woDispRuleMapper;

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoDispRuleService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long ruleId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey ruleId:{}", ruleId);
		}
		return woDispRuleMapper.deleteByPrimaryKey(ruleId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoDispRuleService#createSelective(com.basic.order.model.WoDispRule)
	 */
	@Override
	public int createSelective(WoDispRule record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective WoDispRule:{}", JSON_UTILS.objectToJson(record));
		}

		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.PF_WO_DISP_RULE_SEQ);
		record.setRuleId(gid);
		
		return woDispRuleMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoDispRuleService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public WoDispRule qryByPrimaryKey(Long ruleId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey ruleId:{}", ruleId);
		}

		return woDispRuleMapper.selectByPrimaryKey(ruleId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoDispRuleService#modifyByPrimaryKeySelective(com.basic.order.model.WoDispRule)
	 */
	@Override
	public int modifyByPrimaryKeySelective(WoDispRule record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective WoDispRule:{}", JSON_UTILS.objectToJson(record));
		}
		
		return woDispRuleMapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoDispRuleService#qryByTacheId(java.lang.Long)
	 */
	@Override
	public WoDispRule qryByTacheId(Long tacheId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByTacheId tacheId:{}", tacheId);
		}

		WoDispRule item = null;
		if(tacheId != null){
			item = woDispRuleMapper.selectByTacheId(tacheId);
		}
		return item;
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoDispRuleService#qryWoDispRuleList(com.basic.order.bean.WoDispRuleIbean)
	 */
	@Override
	public List<WoDispRule> qryWoDispRuleList(WoDispRuleIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryWoDispRuleList WoDispRuleIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return woDispRuleMapper.selectWoDispRuleList(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoDispRuleService#qryWoDispRuleListCount(com.basic.order.bean.WoDispRuleIbean)
	 */
	@Override
	public long qryWoDispRuleListCount(WoDispRuleIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryWoDispRuleListCount WoDispRuleIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return woDispRuleMapper.selectWoDispRuleListCount(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoDispRuleService#modifyBatchStateByRuleIds(com.basic.order.model.WoDispRule)
	 */
	@Override
	public int modifyBatchStateByRuleIds(WoDispRule ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyBatchStateByRuleIds WoDispRule:{}", JSON_UTILS.objectToJson(ibean));
		}
		int count = 0;
		if(StringUtils.isNotBlank(ibean.getState()) 
				&& ibean.getRuleIds() != null 
				&& ibean.getRuleIds().length > 0){
			count = woDispRuleMapper.updateBatchStateByRuleIds(ibean);
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoDispRuleService#createOrModify(com.basic.order.model.WoDispRule)
	 */
	@Override
	public void createOrModify(WoDispRule ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify WoDispRule:{}", JSON_UTILS.objectToJson(ibean));
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
