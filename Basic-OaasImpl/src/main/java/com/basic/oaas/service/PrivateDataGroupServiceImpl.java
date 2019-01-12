/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年8月8日 上午9:57:38
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
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.oaas.bean.PrivateDataGroupIbean;
import com.basic.oaas.dao.PrivateDataGroupMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.model.PrivateDataGroup;

/**
 *
 * @date 2017年8月8日 上午9:57:38
 * @author Kevin
 * @Description: 数据权限分组接口实现类
 * 
 */
@Service("privateDataGroupService")
public class PrivateDataGroupServiceImpl extends BaseServerImpl<Long, PrivateDataGroup> implements PrivateDataGroupService {

	private Logger logger = LoggerFactory.getLogger(PrivateDataGroupServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private PrivateDataGroupMapper privateDataGroupMapper;
	
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataGroupService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long dataGroupId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey dataGroupId:{}", dataGroupId);
		}
		return privateDataGroupMapper.deleteByPrimaryKey(dataGroupId);
	}
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataGroupService#createSelective(com.basic.oaas.model.PrivateDataGroup)
	 */
	@Override
	public int createSelective(PrivateDataGroup record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_PRIV_DATA_GROUP_SEQ);
		record.setDataGroupId(gid);
		return privateDataGroupMapper.insertSelective(record);
	}
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataGroupService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public PrivateDataGroup qryByPrimaryKey(Long dataGroupId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey dataGroupId:{}", JSON_UTILS.objectToJson(dataGroupId));
		}
		return privateDataGroupMapper.selectByPrimaryKey(dataGroupId);
	}
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataGroupService#modifyByPrimaryKeySelective(com.basic.oaas.model.PrivateDataGroup)
	 */
	@Override
	public int modifyByPrimaryKeySelective(PrivateDataGroup record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective record:{}", JSON_UTILS.objectToJson(record));
		}
		return privateDataGroupMapper.updateByPrimaryKey(record);
	}
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataGroupService#qryPrivateDataGroupList(com.basic.oaas.bean.PrivateDataGroupIbean)
	 */
	@Override
	public List<PrivateDataGroup> qryPrivateDataGroupList(PrivateDataGroupIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryPrivateDataGroupList ibean:{}", JSON_UTILS.objectToJson(ibean));
		}
		return privateDataGroupMapper.selectPrivateDataGroupList(ibean);
	}
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataGroupService#qryPrivateDataGroupListCount(com.basic.oaas.bean.PrivateDataGroupIbean)
	 */
	@Override
	public long qryPrivateDataGroupListCount(PrivateDataGroupIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryPrivateDataGroupListCount ibean:{}", JSON_UTILS.objectToJson(ibean));
		}
		return privateDataGroupMapper.selectPrivateDataGroupListCount(ibean);
	}
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataGroupService#createOrModify(com.basic.oaas.model.PrivateData)
	 */
	@Override
	public PrivateDataGroup createOrModify(PrivateDataGroup ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("createOrModify ibean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		if(ibean.getDataGroupId() == null){
			createSelective(ibean);
		}else{
			modifyByPrimaryKeySelective(ibean);
		}
		return ibean;
		
	}
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.PrivateDataGroupService#removeBatchByDataGroupIds(java.lang.Long[])
	 */
	@Override
	public int removeBatchByDataGroupIds(Long[] dataGroupIds) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeBatchByDataGroupIds dataGroupIds:{}", JSON_UTILS.objectToJson(dataGroupIds));
		}
		return privateDataGroupMapper.deleteBatchByDataGroupIds(dataGroupIds);
	}
	@Override
	public void create(PrivateDataGroup entity) {
		createOrModify(entity);
	}
	@Override
	public void update(PrivateDataGroup entity) {
		createOrModify(entity);
	}
	@Override
	public String getNamespace() {
		return PrivateDataGroupMapper.class.getName();
	}
	
	@Override
 	public PageJson queryPage(DefaultQueryFilter  queryFilter) {
        return  new PageJson(new PageList<>(super.query(queryFilter)));
    }
    
    @Override
    public List<PrivateDataGroup> query(DefaultQueryFilter  queryFilter) {
 		queryFilter.setPage(null);
 		return super.query(queryFilter);
 	}

}
