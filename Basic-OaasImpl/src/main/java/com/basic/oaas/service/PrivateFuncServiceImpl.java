/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月8日 上午9:57:38
 * @author Kevin
 * @Description: 
 * 
 */
package com.basic.oaas.service;

import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.basic.framework.cache.CacheRedis;
import com.basic.framework.cache.model.RedisCache;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.db.domain.PageList;
import com.basic.framework.common.baseServerImpl.BaseServerImpl;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.BeanUtils;
import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.oaas.bean.PrivateFuncIbean;
import com.basic.oaas.dao.PrivateFuncMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.define.PrivateType;
import com.basic.oaas.model.Private;
import com.basic.oaas.model.PrivateFunc;
import com.basic.oaas.model.PrivateMenu;

/**
 *
 * @date 2017年8月8日 上午9:57:38
 * @author Kevin
 * @Description: 功能按钮接口实现类
 * 
 */
@Service("privateFuncService")
public class PrivateFuncServiceImpl extends BaseServerImpl<Long, PrivateFunc> implements PrivateFuncService {

	private Logger logger = LoggerFactory.getLogger(PrivateFuncServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private PrivateFuncMapper privateFuncMapper;
	
	@Autowired
	private PrivateService privateService;
	
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long funcId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey catalogId:{}", funcId);
		}
		return privateFuncMapper.deleteByPrimaryKey(funcId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateService#createSelective(com.basic.oaas.model.Private)
	 */
	@Override
	public int createSelective(PrivateFunc record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective record:{}", JSON_UTILS.objectToJson(record));
		}
		Private ibean = new Private();
		ibean.setPrivateName(record.getFuncName());
		ibean.setPrivateCode(record.getFuncCode());
		ibean.setPrivateType(PrivateType.FUNC.getCode());
		ibean.setCreateTime(DateUtils.now());
		ibean.setCreateUserId(record.getCreateUserId());
		ibean.setState("10A");
		privateService.createSelective(ibean);
		
		
		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_PRIV_FUNC_SEQ);
		record.setFuncId(gid);
		record.setCreateTime(DateUtils.now());
		record.setPrivateId(ibean.getPrivateId());
		if (record.getOpenStatus()==null || record.getOpenStatus() == 1) {
			record.setOpenStatus(1);
			record.setOpenDate(new Date());
		}else if(record.getOpenStatus() == 0) {
			record.setStopDate(new Date());
		}
		
		return privateFuncMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public PrivateFunc qryByPrimaryKey(Long funcId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey funcId:{}", funcId);
		}
		return privateFuncMapper.selectByPrimaryKey(funcId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateService#modifyByPrimaryKeySelective(com.basic.oaas.model.Private)
	 */
	@Override
	public int modifyByPrimaryKeySelective(PrivateFunc record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective record:{}", JSON_UTILS.objectToJson(record));
		}
		record.setModifyTime(DateUtils.now());
		PrivateFunc recordDb = this.qryByPrimaryKey(record.getFuncId());
		if (recordDb!=null) {
			//与数据库不一致才修改
			if ((record.getOpenStatus()==null || record.getOpenStatus() == 1)
					&& recordDb.getOpenStatus()==0) {
				record.setOpenStatus(1);
				record.setOpenDate(new Date());
			}else if(record.getOpenStatus()!=null
					&& record.getOpenStatus() == 0 
					&& recordDb.getOpenStatus()==1) {
				record.setStopDate(new Date());
			}
		}
		
		Private priv = privateService.qryByPrimaryKey(record.getPrivateId());
		if (priv!=null) {
			priv.setModifyUserId(record.getModifyUserId());
			priv.setPrivateName(record.getFuncName());
			privateService.modifyByPrimaryKeySelective(priv);
		}
		
		return privateFuncMapper.updateByPrimaryKey(record);

	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateService#createOrModify(com.basic.oaas.model.Private)
	 */
	@Override
	public void createOrModify(PrivateFunc ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify Private:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		if(BeanUtils.isEmpty(ibean.getFuncId())){//新增
			createSelective(ibean);
		}else{//修改
			modifyByPrimaryKeySelective(ibean);
		}
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateService#removeBatchByfuncIds(java.lang.Long[])
	 */
	@Override
	public int removeBatchByPrivateFuncIds(Long[] funcIds) {
		if(logger.isDebugEnabled()){
			logger.debug("removeBatchByPrivateIds funcIds:{}", JSON_UTILS.objectToJson(funcIds));
		}
		privateService.removeBatchByFuncIds(funcIds);
		return privateFuncMapper.updateBatchByFuncIds(funcIds);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateFuncService#selectFuncByFuncCode(java.util.List)
	 */
	@Override
	public List<PrivateFunc> qryFuncByFuncCode(String[] funcCode) {
		if(logger.isDebugEnabled()){
			logger.debug("selectFuncByFuncCode funcCode:{}", JSON_UTILS.objectToJson(funcCode));
		}
		return privateFuncMapper.selectFuncByFuncCode(funcCode) ;
	}

	@Override
	public void create(PrivateFunc entity) {
		this.createOrModify(entity);
	}

	@Override
	public void update(PrivateFunc entity) {
		this.createOrModify(entity);
	}

	@Override
	public String getNamespace() {
		return PrivateFuncMapper.class.getName();
	}
	
	@Override
	public PageJson queryPage(DefaultQueryFilter  queryFilter) {
        return  new PageJson(new PageList<>(super.query(queryFilter)));
    }
    
	@Override
    public List<PrivateFunc> query(DefaultQueryFilter  queryFilter) {
 		queryFilter.setPage(null);
 		return super.query(queryFilter);
 	}
	

}
