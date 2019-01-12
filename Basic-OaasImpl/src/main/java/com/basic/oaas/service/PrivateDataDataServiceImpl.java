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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.basic.oaas.bean.PrivateDataDataIbean;
import com.basic.oaas.dao.PrivateDataDataMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.model.PrivateDataData;


/**
 *
 * @date 2017年8月8日 上午9:55:02
 * @author Kevin
 * @Description: 数据权限数据
 * 
 */
@Service("privateDataDataService")
public class PrivateDataDataServiceImpl extends BaseServerImpl<Long, PrivateDataData> implements PrivateDataDataService {

	private Logger logger = LoggerFactory.getLogger(PrivateDataDataServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private PrivateDataDataMapper privateDataDataMapper;
	
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataDataService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long dataDataId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey dataDataId:{}", dataDataId);
		}
		
		return privateDataDataMapper.deleteByPrimaryKey(dataDataId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataDataService#createSelective(com.basic.oaas.model.PrivateDataData)
	 */
	@Override
	public int createSelective(PrivateDataData record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_PRIV_DATA_GRP_INST_SEQ);
		record.setDataDataId(gid);
		return privateDataDataMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataDataService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public PrivateDataData qryByPrimaryKey(Long dataDataId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey record:{}", JSON_UTILS.objectToJson(dataDataId));
		}
		
		return privateDataDataMapper.selectByPrimaryKey(dataDataId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataDataService#modifyByPrimaryKeySelective(com.basic.oaas.model.PrivateDataData)
	 */
	@Override
	public int modifyByPrimaryKeySelective(PrivateDataData record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		return privateDataDataMapper.updateByPrimaryKey(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataDataService#qryPrivateDataDataList(com.basic.oaas.bean.PrivateDataDataIbean)
	 */
	@Override
	public List<PrivateDataData> qryPrivateDataDataList(PrivateDataDataIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryPrivateDataDataList ibean:{}", JSON_UTILS.objectToJson(ibean));
		}
		return privateDataDataMapper.selectPrivateDataDataList(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataDataService#qryPrivateDataDataListCount(com.basic.oaas.bean.PrivateDataDataIbean)
	 */
	@Override
	public long qryPrivateDataDataListCount(PrivateDataDataIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryPrivateDataDataListCount ibean:{}", JSON_UTILS.objectToJson(ibean));
		}
		return privateDataDataMapper.selectPrivateDataDataListCount(ibean);
	}

	@Override
	public void create(PrivateDataData entity) {
		createSelective(entity);
	}

	@Override
	public void update(PrivateDataData entity) {
		modifyByPrimaryKeySelective(entity);
	}

	@Override
	public String getNamespace() {
		return PrivateDataDataMapper.class.getName();
	}

	@Override
 	public PageJson queryPage(DefaultQueryFilter  queryFilter) {
        return  new PageJson(new PageList<>(super.query(queryFilter)));
    }
    
    @Override
    public List<PrivateDataData> query(DefaultQueryFilter  queryFilter) {
 		queryFilter.setPage(null);
 		return super.query(queryFilter);
 	}

	@Override
	public int deleteBySource(Long sourceId,String sourceType) {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("sourceId", sourceId);
		params.put("sourceType", sourceType);
		return privateDataDataMapper.deleteBySource(params);
	}

	@Override
	public PrivateDataData getBySource(Long sourceId, String sourceType) {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("sourceId", sourceId);
		params.put("sourceType", sourceType);
		return privateDataDataMapper.getBySource(params);
	}
	
	
}
