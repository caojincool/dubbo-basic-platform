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
import com.basic.framework.common.utils.BeanUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.oaas.bean.UserDataGroupIbean;
import com.basic.oaas.dao.UserDataGroupMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.model.UserDataGroup;

/**
 *
 * @date 2017年8月8日 上午9:57:38
 * @author Kevin
 * @Description: 账号数据权限实例
 * 
 */
@Service("userDataGroupService")
public class UserDataGroupServiceImpl extends BaseServerImpl<Long, UserDataGroup> implements UserDataGroupService {

	private Logger logger = LoggerFactory.getLogger(UserDataGroupServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private UserDataGroupMapper userDataGroupMapper;
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserDataGroupService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long userDataGroupId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey userDataGroupId:{}", userDataGroupId);
		}
		return userDataGroupMapper.deleteByPrimaryKey(userDataGroupId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserDataGroupService#createSelective(com.basic.oaas.model.UserDataGroup)
	 */
	@Override
	public int createSelective(UserDataGroup record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_USER_DATA_GRP_SEQ);
		record.setUserDataGrpId(gid);
		return userDataGroupMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserDataGroupService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public UserDataGroup qryByPrimaryKey(Long userDataGroupId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey userDataGroupId:{}", userDataGroupId);
		}
		return userDataGroupMapper.selectByPrimaryKey(userDataGroupId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserDataGroupService#modifyByPrimaryKeySelective(com.basic.oaas.model.UserDataGroup)
	 */
	@Override
	public int modifyByPrimaryKey(UserDataGroup record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKey record:{}", JSON_UTILS.objectToJson(record));
		}
		return userDataGroupMapper.updateByPrimaryKeySelective(record);

	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserDataGroupService#removeBatchByUserId(java.lang.Long)
	 */
	@Override
	public int removeBatchByUserId(Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeBatchByUserId record:{}", userId);
		}
		return userDataGroupMapper.deleteBatchByUserId(userId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserDataGroupService#createBatchUserDataGroup(java.util.List)
	 */
	@Override
	public int createBatchUserDataGroup(UserDataGroupIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("createBatchUserDataGroup record:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		//先删除所有的权限在插入
		userDataGroupMapper.deleteBatchByUserId(ibean.getUserId());
		List<UserDataGroup> list =ibean.getUserDataGroups();
		if (BeanUtils.isNotEmpty(list)) {
			for (UserDataGroup userDataGroup : list) {
				Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_USER_DATA_GRP_SEQ);
				userDataGroup.setUserDataGrpId(gid);
				userDataGroup.setUserId(ibean.getUserId());
			}
			return userDataGroupMapper.insertBatchUserDataGroup(list);
		}else {
			return 0;
		}
	}

	@Override
	public void create(UserDataGroup entity) {
		createSelective(entity);
	}

	@Override
	public void update(UserDataGroup entity) {
		modifyByPrimaryKey(entity);
	}

	@Override
	public String getNamespace() {
		return UserDataGroupMapper.class.getName();
	}
	
	@Override
 	public PageJson queryPage(DefaultQueryFilter  queryFilter) {
        return  new PageJson(new PageList<>(super.query(queryFilter)));
    }
    
    @Override
    public List<UserDataGroup> query(DefaultQueryFilter  queryFilter) {
 		queryFilter.setPage(null);
 		return super.query(queryFilter);
 	}
}
