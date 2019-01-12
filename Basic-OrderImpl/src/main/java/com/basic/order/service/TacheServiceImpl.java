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
import com.basic.order.bean.TacheIbean;
import com.basic.order.dao.TacheMapper;
import com.basic.order.define.BasicStateEnum;
import com.basic.order.define.GidCodes;
import com.basic.order.define.PageDateTypeEnum;
import com.basic.order.model.Tache;

/**
 * 
 *
 * @date 2017年8月17日 上午11:49:24
 * 
 * @Description: 环节
 *
 */
@Service("tacheService")
public class TacheServiceImpl implements TacheService{

	private Logger logger = LoggerFactory.getLogger(TacheServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private TacheMapper tacheMapper;

	/* (non-Javadoc)
	 * @see com.basic.order.service.TacheService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long tacheId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey tacheId:{}", tacheId);
		}
		return tacheMapper.deleteByPrimaryKey(tacheId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.TacheService#createSelective(com.basic.order.model.Tache)
	 */
	@Override
	public int createSelective(Tache record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective Tache:{}", JSON_UTILS.objectToJson(record));
		}

		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.PF_TACHE_SEQ);
		record.setTacheId(gid);
		
		return tacheMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.TacheService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public Tache qryByPrimaryKey(Long tacheId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey tacheId:{}", tacheId);
		}

		return tacheMapper.selectByPrimaryKey(tacheId);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.TacheService#modifyByPrimaryKeySelective(com.basic.order.model.Tache)
	 */
	@Override
	public int modifyByPrimaryKeySelective(Tache record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective Tache:{}", JSON_UTILS.objectToJson(record));
		}
		
		return tacheMapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.TacheService#qryByTacheCode(java.lang.String)
	 */
	@Override
	public Tache qryByTacheCode(String tacheCode) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByTacheCode tacheCode:{}", tacheCode);
		}

		Tache item = null;
		if(StringUtils.isNotBlank(tacheCode)){
			item = tacheMapper.selectByTacheCode(tacheCode);
		}
		return item;
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.TacheService#qryTacheList(com.basic.order.bean.TacheIbean)
	 */
	@Override
	public List<Tache> qryTacheList(TacheIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryTacheList TacheIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return tacheMapper.selectTacheList(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.TacheService#qryTacheListCount(com.basic.order.bean.TacheIbean)
	 */
	@Override
	public long qryTacheListCount(TacheIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryTacheListCount TacheIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return tacheMapper.selectTacheListCount(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.TacheService#modifyBatchStateByTacheIds(com.basic.order.model.Tache)
	 */
	@Override
	public int modifyBatchStateByTacheIds(Tache ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyBatchStateByTacheIds OrderType:{}", JSON_UTILS.objectToJson(ibean));
		}
		int count = 0;
		if(StringUtils.isNotBlank(ibean.getState()) 
				&& ibean.getTacheIds() != null 
				&& ibean.getTacheIds().length > 0){
			count = tacheMapper.updateBatchStateByTacheIds(ibean);
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see com.basic.order.service.TacheService#createOrModify(com.basic.order.model.Tache)
	 */
	@Override
	public void createOrModify(Tache ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify Tache:{}", JSON_UTILS.objectToJson(ibean));
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
