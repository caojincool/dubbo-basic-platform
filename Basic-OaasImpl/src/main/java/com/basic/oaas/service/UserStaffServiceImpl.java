/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年7月5日 上午10:49:45
 * @author Kevin
 * @Description: 员工实现类
 * 
 */
package com.basic.oaas.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.baseServerImpl.BaseServerImpl;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.oaas.bean.UserStaffIbean;
import com.basic.oaas.dao.UserStaffMapper;
import com.basic.oaas.model.UserStaff;

/**
 *
 * @date 2017年7月5日 上午10:49:45
 * @author Kevin
 * @Description: UserStaffService
 * 
 */
@Service("userStaffService")
public class UserStaffServiceImpl extends BaseServerImpl<Long, UserStaff> implements UserStaffService {

	private Logger logger = LoggerFactory.getLogger(UserStaffServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private UserStaffMapper userStaffMapper;
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserStaffService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long staffJobId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey staffJobId:{}", staffJobId);
		}
		return userStaffMapper.deleteByPrimaryKey(staffJobId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserStaffService#createSelective(com.basic.oaas.model.UserStaff)
	 */
	@Override
	public int createSelective(UserStaff record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		return userStaffMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserStaffService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public UserStaff qryByPrimaryKey(Long staffJobId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey staffJobId:{}", staffJobId);
		}
		return userStaffMapper.selectByPrimaryKey(staffJobId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserStaffService#modifyByPrimaryKeySelective(com.basic.oaas.model.UserStaff)
	 */
	@Override
	public int modifyByPrimaryKeySelective(UserStaff record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective record:{}", JSON_UTILS.objectToJson(record));
		}
		return userStaffMapper.updateByPrimaryKey(record);

	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserStaffService#qryUserStaffList(com.basic.oaas.bean.UserStaffIbean)
	 */
	@Override
	public List<UserStaff> qryUserStaffList(UserStaffIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryPrivateCatalogList PrivateCatalogIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		return userStaffMapper.selectUserStaffList(ibean);
	}
	
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserStaffService#qryUserStaffListCount(com.basic.oaas.bean.UserStaffIbean)
	 */
	@Override
	public long qryUserStaffListCount(UserStaffIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryUserStaffListCount UserStaffIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		return userStaffMapper.selectUserStaffListCount(ibean);
	}
	

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserStaffService#removeBatchByUserIds(java.lang.Long)
	 */
	@Override
	public int removeBatchByUserIds(Long[] userIds) {
		if(logger.isDebugEnabled()){
			logger.debug("removeBatchByUserIds userIds:{}", JSON_UTILS.objectToJson(userIds));
		}
		
		return userStaffMapper.deleteBatchByUserIds(userIds);
	}

	@Override
	public void create(UserStaff entity) {
		this.createSelective(entity);
		
	}

	@Override
	public void update(UserStaff entity) {
		this.modifyByPrimaryKeySelective(entity);
		
	}

	@Override
	public String getNamespace() {
		return UserStaffMapper.class.getName();
	}

	




}
