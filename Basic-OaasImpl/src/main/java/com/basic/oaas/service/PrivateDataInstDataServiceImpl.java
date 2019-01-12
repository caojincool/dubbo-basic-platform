/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月8日 上午9:55:02
 * @author Kevin
 * @Description: TODO(这里用一句话描述这个类的作用)
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
import com.basic.framework.common.utils.BeanUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.oaas.bean.PrivateDataInstDataIbean;
import com.basic.oaas.dao.PrivateDataInstDataMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.model.PrivateDataInstData;


/**
 *
 * @date 2017年8月8日 上午9:55:02
 * @author Kevin
 * @Description: 数据权限实例数据
 * 
 */
@Service("privateDataInstDataService")
public class PrivateDataInstDataServiceImpl extends BaseServerImpl<Long, PrivateDataInstData> implements PrivateDataInstDataService {

	private Logger logger = LoggerFactory.getLogger(PrivateDataInstDataServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private PrivateDataInstDataMapper privateDataInstDataMapper;

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataInstDataService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long dataInstDataId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey dataInstDataId:{}", dataInstDataId);
		}
		
		return privateDataInstDataMapper.deleteByPrimaryKey(dataInstDataId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataInstDataService#createSelective(com.basic.oaas.model.PrivateDataInstData)
	 */
	@Override
	public int createSelective(PrivateDataInstDataIbean record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		
		List<PrivateDataInstData> list = record.getPrivateDataInstDatas();
		if (BeanUtils.isNotEmpty(list)) {
			for (PrivateDataInstData pdid : list) {
				Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_PRIV_DATA_INST_DATA_SEQ);
				pdid.setDataInstDataId(gid);
			}
			
			//先删除原来的记录然后重新插入
			privateDataInstDataMapper.deleteByDataInstId(record.getDataInstId());
			
			return privateDataInstDataMapper.insertBatchDataInstData(list);
		}else {
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataInstDataService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public PrivateDataInstData qryByPrimaryKey(Long dataInstDataId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey dataInstDataId:{}", JSON_UTILS.objectToJson(dataInstDataId));
		}
		
		return privateDataInstDataMapper.selectByPrimaryKey(dataInstDataId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataInstDataService#modifyByPrimaryKeySelective(com.basic.oaas.model.PrivateDataInstData)
	 */
	@Override
	public int modifyByPrimaryKeySelective(PrivateDataInstData record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective record:{}", JSON_UTILS.objectToJson(record));
		}
		return privateDataInstDataMapper.updateByPrimaryKey(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataInstDataService#qryInstDataList(com.basic.oaas.bean.PrivateDataInstDataIbean)
	 */
	@Override
	public List<PrivateDataInstData> qryInstDataList(PrivateDataInstDataIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryInstDataList ibean:{}", JSON_UTILS.objectToJson(ibean));
		}
		return privateDataInstDataMapper.selectInstDataList(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataInstDataService#qryInstDataListCount(com.basic.oaas.bean.PrivateDataInstDataIbean)
	 */
	@Override
	public long qryInstDataListCount(PrivateDataInstDataIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryInstDataListCount ibean:{}", JSON_UTILS.objectToJson(ibean));
		}
		return privateDataInstDataMapper.selectInstDataListCount(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataInstDataService#removeBatchByDataInstDataIdsIds(java.lang.Long[])
	 */
	@Override
	public int removeBatchByDataInstDataIds(Long[] dataInstDataIds) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeBatchByDataInstDataIds dataInstDataIds:{}", JSON_UTILS.objectToJson(dataInstDataIds));
		}
		
		return privateDataInstDataMapper.deleteBatchByDataInstDataIds(dataInstDataIds);
	}

	@Override
	@Deprecated
	public void create(PrivateDataInstData entity) {
	}

	@Override
	public void update(PrivateDataInstData entity) {
		modifyByPrimaryKeySelective(entity);
	}

	@Override
	public String getNamespace() {
		return PrivateDataInstDataMapper.class.getName();
	}
	
	@Override
 	public PageJson queryPage(DefaultQueryFilter  queryFilter) {
        return  new PageJson(new PageList<>(super.query(queryFilter)));
    }
    
    @Override
    public List<PrivateDataInstData> query(DefaultQueryFilter  queryFilter) {
 		queryFilter.setPage(null);
 		return super.query(queryFilter);
 	}
  
}
