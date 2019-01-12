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
import com.basic.order.bean.WoDispRuleInstIbean;
import com.basic.order.bean.WoDispRuleInstPageIbean;
import com.basic.order.dao.WoDispRuleInstMapper;
import com.basic.order.define.BasicStateEnum;
import com.basic.order.define.GidCodes;
import com.basic.order.define.PageDateTypeEnum;
import com.basic.order.model.WoDispRuleInst;

/**
 * 
 *
 * @date 2017年8月17日 上午11:49:24
 * 
 * @Description: 任务派发规则实例
 *
 */
@Service("woDispRuleInstService")
public class WoDispRuleInstServiceImpl implements WoDispRuleInstService{

	private Logger logger = LoggerFactory.getLogger(WoDispRuleInstServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private WoDispRuleInstMapper woDispRuleInstMapper;

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoDispRuleInstService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long ruleInstId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey ruleInstId:{}", ruleInstId);
		}
		return woDispRuleInstMapper.deleteByPrimaryKey(ruleInstId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoDispRuleInstService#createSelective(com.basic.order.model.WoDispRuleInst)
	 */
	@Override
	public int createSelective(WoDispRuleInst record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective WoDispRuleInst:{}", JSON_UTILS.objectToJson(record));
		}

		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.PF_WO_DISP_RULE_INST_SEQ);
		record.setRuleInstId(gid);
		
		return woDispRuleInstMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoDispRuleInstService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public WoDispRuleInst qryByPrimaryKey(Long ruleInstId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey ruleInstId:{}", ruleInstId);
		}

		return woDispRuleInstMapper.selectByPrimaryKey(ruleInstId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoDispRuleInstService#modifyByPrimaryKeySelective(com.basic.order.model.WoDispRuleInst)
	 */
	@Override
	public int modifyByPrimaryKeySelective(WoDispRuleInst record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective WoDispRuleInst:{}", JSON_UTILS.objectToJson(record));
		}
		
		return woDispRuleInstMapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoDispRuleInstService#qryForArea(com.basic.order.bean.WoDispRuleInstIbean)
	 */
	@Override
	public WoDispRuleInst qryForArea(WoDispRuleInstIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryForArea WoDispRuleInstIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return woDispRuleInstMapper.selectForArea(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoDispRuleInstService#qryForOrg(com.basic.order.bean.WoDispRuleInstIbean)
	 */
	@Override
	public WoDispRuleInst qryForOrg(WoDispRuleInstIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryForOrg WoDispRuleInstIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return woDispRuleInstMapper.selectForOrg(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoDispRuleInstService#qryForUser(com.basic.order.bean.WoDispRuleInstIbean)
	 */
	@Override
	public WoDispRuleInst qryForUser(WoDispRuleInstIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryForUser WoDispRuleInstIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return woDispRuleInstMapper.selectForUser(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoDispRuleInstService#qryForStaff(com.basic.order.bean.WoDispRuleInstIbean)
	 */
	@Override
	public WoDispRuleInst qryForStaff(WoDispRuleInstIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryForStaff WoDispRuleInstIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return woDispRuleInstMapper.selectForStaff(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoDispRuleInstService#qryWoDispRuleInstList(com.basic.order.bean.WoDispRuleInstPageIbean)
	 */
	@Override
	public List<WoDispRuleInst> qryWoDispRuleInstList(WoDispRuleInstPageIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryWoDispRuleInstList WoDispRuleInstPageIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return woDispRuleInstMapper.selectWoDispRuleInstList(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoDispRuleInstService#qryWoDispRuleInstListCount(com.basic.order.bean.WoDispRuleInstPageIbean)
	 */
	@Override
	public long qryWoDispRuleInstListCount(WoDispRuleInstPageIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryWoDispRuleInstListCount WoDispRuleInstPageIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return woDispRuleInstMapper.selectWoDispRuleInstListCount(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoDispRuleInstService#modifyBatchStateByRuleInstIds(com.basic.order.model.WoDispRuleInst)
	 */
	@Override
	public int modifyBatchStateByRuleInstIds(WoDispRuleInst ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyBatchStateByRuleInstIds WoDispRuleInst:{}", JSON_UTILS.objectToJson(ibean));
		}
		int count = 0;
		if(StringUtils.isNotBlank(ibean.getState()) 
				&& ibean.getRuleInstIds() != null 
				&& ibean.getRuleInstIds().length > 0){
			count = woDispRuleInstMapper.updateBatchStateByRuleInstIds(ibean);
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.WoDispRuleInstService#createOrModify(com.basic.order.model.WoDispRuleInst)
	 */
	@Override
	public void createOrModify(WoDispRuleInst ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify WoDispRuleInst:{}", JSON_UTILS.objectToJson(ibean));
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
