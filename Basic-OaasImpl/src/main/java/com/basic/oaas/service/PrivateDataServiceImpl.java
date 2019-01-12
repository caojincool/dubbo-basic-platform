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

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.db.domain.PageList;
import com.basic.framework.common.baseServerImpl.BaseServerImpl;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.oaas.bean.PrivateDataIbean;
import com.basic.oaas.dao.PrivateDataMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.model.PrivateData;

/**
 *
 * @date 2017年8月8日 上午9:57:38
 * @author Kevin
 * @Description: 数据权限接口实现类
 * 
 */
@Service("privateDataService")
public class PrivateDataServiceImpl extends BaseServerImpl<Long, PrivateData> implements PrivateDataService {

	private Logger logger = LoggerFactory.getLogger(PrivateDataServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private PrivateDataMapper privateDataMapper;
	@Autowired
	private PrivateDataInstService privateDataInstService;
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long dataId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey catalogId:{}", dataId);
		}
		return privateDataMapper.deleteByPrimaryKey(dataId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataService#createSelective(com.basic.oaas.model.PrivateData)
	 */
	@Override
	public int createSelective(PrivateData record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_PRIV_DATA_SEQ);
		record.setDataId(gid);
		record.setCreateTime(DateUtils.now());
		record.setState("10A");
		return privateDataMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public PrivateData qryByPrimaryKey(Long dataId) {
		if (logger.isDebugEnabled()) {}
			logger.debug("qryByPrimaryKey dataId:{}", dataId);
		
		return privateDataMapper.selectByPrimaryKey(dataId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataService#modifyByPrimaryKeySelective(com.basic.oaas.model.PrivateData)
	 */
	@Override
	public int modifyByPrimaryKeySelective(PrivateData record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective record:{}", JSON_UTILS.objectToJson(record));
		}
		record.setModifyTime(DateUtils.now());
		return privateDataMapper.updateByPrimaryKey(record);

	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataService#qryPrivateDataList(com.basic.oaas.bean.PrivateDataIbean)
	 */
	@Override
	public List<PrivateData> qryPrivateDataList(PrivateDataIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryPrivateCatalogList PrivateCatalogIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		return privateDataMapper.selectPrivateDataList(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataService#qryPrivateDataListCount(com.basic.oaas.bean.PrivateDataIbean)
	 */
	@Override
	public long qryPrivateDataListCount(PrivateDataIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryPrivateCatalogListCount PrivateCatalogIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		return privateDataMapper.selectPrivateDataListCount(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataService#createOrModify(com.basic.oaas.model.PrivateData)
	 */
	@Override
	public void createOrModify(PrivateData ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify PrivateData:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		if(ibean.getDataId() == null){//新增
			createSelective(ibean);
		}else{//修改
			modifyByPrimaryKeySelective(ibean);
		}
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataService#removeBatchByDataIds(java.lang.Long[])
	 */
	@Override
	public int removeBatchByDataIds(Long[] dataIds) {
		if(logger.isDebugEnabled()){
			logger.debug("removeBatchByDataIds dataIds:{}", JSON_UTILS.objectToJson(dataIds));
		}
		int num = privateDataMapper.updateBatchByDataIds(dataIds);
		return num;
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataService#qryPrivateDataByUserList(com.basic.oaas.bean.PrivateDataIbean)
	 */
	@Override
	public List<PrivateData> qryPrivateDataByUserList(PrivateDataIbean ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("qryPrivateDataByUserList ibean:{}", JSON_UTILS.objectToJson(ibean));
		}
		return privateDataMapper.selectPrivateDataByUserList(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataService#qryPrivateDataByUserListCount(com.basic.oaas.bean.PrivateDataIbean)
	 */
	@Override
	public long qryPrivateDataByUserListCount(PrivateDataIbean ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("qryPrivateDataByUserListCount ibean:{}", JSON_UTILS.objectToJson(ibean));
		}
		return privateDataMapper.selectPrivateDataByUserListCount(ibean);
	}

	@Override
	public void create(PrivateData entity) {
		createOrModify(entity);
	}

	@Override
	public void update(PrivateData entity) {
		createOrModify(entity);
	}

	@Override
	public String getNamespace() {
		return PrivateDataMapper.class.getName();
	}
	
	@Override
 	public PageJson queryPage(DefaultQueryFilter  queryFilter) {
        return  new PageJson(new PageList<>(super.query(queryFilter)));
    }
    
    @Override
    public List<PrivateData> query(DefaultQueryFilter  queryFilter) {
 		queryFilter.setPage(null);
 		return super.query(queryFilter);
 	}

	

}
