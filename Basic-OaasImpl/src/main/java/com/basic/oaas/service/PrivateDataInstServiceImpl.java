/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月8日 上午9:55:02
 * @author Kevin
 * @Description: 
 * 
 */
package com.basic.oaas.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.db.domain.PageList;
import com.basic.framework.common.baseServerImpl.BaseServerImpl;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.oaas.bean.PrivateDataInstIbean;
import com.basic.oaas.dao.PrivateDataInstMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.model.PrivateDataInst;


/**
 *
 * @date 2017年8月8日 上午9:55:02
 * @author Kevin
 * @Description: 数据权限实例
 * 
 */
@Service("privateDataInstService")
public class PrivateDataInstServiceImpl extends BaseServerImpl<Long, PrivateDataInst> implements PrivateDataInstService {

	private Logger logger = LoggerFactory.getLogger(PrivateDataInstServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private PrivateDataInstMapper privateDataInstMapper;
	
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataInstService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long dataInstId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey dataInstId:{}", dataInstId);
		}
		
		return privateDataInstMapper.deleteByPrimaryKey(dataInstId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataInstService#createSelective(com.basic.oaas.model.PrivateDataInst)
	 */
	@Override
	public int createSelective(PrivateDataInst record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_PRIV_DATA_INST_SEQ);
		record.setDataInstId(gid);
		return privateDataInstMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataInstService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public PrivateDataInst qryByPrimaryKey(Long dataInstId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey record:{}", JSON_UTILS.objectToJson(dataInstId));
		}
		
		return privateDataInstMapper.selectByPrimaryKey(dataInstId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataInstService#modifyByPrimaryKeySelective(com.basic.oaas.model.PrivateDataInst)
	 */
	@Override
	public int modifyByPrimaryKeySelective(PrivateDataInst record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		return privateDataInstMapper.updateByPrimaryKeySelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataInstService#qryPrivateDataInstList(com.basic.oaas.bean.PrivateDataInstIbean)
	 */
	@Override
	public List<PrivateDataInst> qryPrivateDataInstList(PrivateDataInstIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryPrivateDataInstList ibean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return privateDataInstMapper.selectPrivateDataInstList(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataInstService#qryPrivateDataInstListCount(com.basic.oaas.bean.PrivateDataInstIbean)
	 */
	@Override
	public long qryPrivateDataInstListCount(PrivateDataInstIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryPrivateDataInstListCount ibean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return privateDataInstMapper.selectPrivateDataInstListCount(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataInstService#removeBatchByDataInstIds(java.lang.Long[])
	 */
	@Override
	public int removeBatchByDataInstIds(Long[] dataInstIds) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeBatchByDataInstIds dataInstIds:{}", JSON_UTILS.objectToJson(dataInstIds));
		}
		
		return privateDataInstMapper.deleteBatchByDataInstIds(dataInstIds);
	}

	@Override
	public void create(PrivateDataInst entity) {
		createSelective(entity);
	}

	@Override
	public void update(PrivateDataInst entity) {
		modifyByPrimaryKeySelective(entity);
	}

	@Override
	public String getNamespace() {
		return PrivateDataInstMapper.class.getName();
	}
	
	@Override
 	public PageJson queryPage(DefaultQueryFilter  queryFilter) {
        return  new PageJson(new PageList<>(super.query(queryFilter)));
    }
    
    @Override
    public List<PrivateDataInst> query(DefaultQueryFilter  queryFilter) {
 		queryFilter.setPage(null);
 		return super.query(queryFilter);
 	}
    
  
}
