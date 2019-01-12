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
import org.springframework.stereotype.Service;

import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.db.domain.PageList;
import com.basic.framework.common.baseServerImpl.BaseServerImpl;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.BeanUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.oaas.bean.UserRoleIbean;
import com.basic.oaas.dao.UserRoleMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.model.UserRole;

/**
 *
 * @date 2017年8月8日 上午9:57:38
 * @author Kevin
 * @Description: 用户目录接口实现类
 * 
 */
@Service("userRoleService")
public class UserRoleServiceImpl extends BaseServerImpl<Long, UserRole> implements UserRoleService {

	private Logger logger = LoggerFactory.getLogger(UserRoleServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private UserRoleMapper userRoleMapper;
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserRoleService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long userRoleId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey userRoleId:{}", userRoleId);
		}
		return userRoleMapper.deleteByPrimaryKey(userRoleId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserRoleService#createSelective(com.basic.oaas.model.UserRole)
	 */
	@Override
	public int createSelective(UserRole record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_USER_ROLE_SEQ);
		record.setUserRoleId(gid);
		return userRoleMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserRoleService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public UserRole qryByPrimaryKey(Long userRoleId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey userRoleId:{}", userRoleId);
		}
		return userRoleMapper.selectByPrimaryKey(userRoleId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserRoleService#modifyByPrimaryKeySelective(com.basic.oaas.model.UserRole)
	 */
	@Override
	public int modifyByPrimaryKey(UserRole record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKey record:{}", JSON_UTILS.objectToJson(record));
		}
		return userRoleMapper.updateByPrimaryKey(record);

	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserRoleService#removeBatchByUserId(java.lang.Long)
	 */
	@Override
	public int removeBatchByUserId(Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeBatchByUserId record:{}", userId);
		}
		return userRoleMapper.deleteBatchByUserId(userId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserRoleService#createBatchUserRole(java.util.List)
	 */
	@Override
	public int createBatchUserRole(UserRoleIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("createBatchUserRole record:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		//先删除所有的权限在插入
		userRoleMapper.deleteBatchByUserId(ibean.getUserId());
		List<UserRole> list =ibean.getUserRoles();
		if(BeanUtils.isNotEmpty(list)){
			for (UserRole userRole : list) {
				Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_USER_ROLE_SEQ);
				userRole.setUserRoleId(gid);
				userRole.setUserId(ibean.getUserId());
				userRole.setCreateTime(new Date());
			}
		}else {
			return 0;
		}
		
		
		return userRoleMapper.insertBatchUserRole(list);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserRoleService#qryUserRoleByUserId(java.lang.Long)
	 */
	@Override
	public List<UserRole> qryUserRoleByUserId(Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryUserRoleByUserId record:{}", userId);
		}
		return userRoleMapper.selectUserRoleByUserId(userId);
	}

	@Override
	public void create(UserRole entity) {
		createSelective(entity);
	}

	@Override
	public void update(UserRole entity) {
		modifyByPrimaryKey(entity);
	}

	@Override
	public String getNamespace() {
		return UserRoleMapper.class.getName();
	}
	
	@Override
	public PageJson queryPage(DefaultQueryFilter  queryFilter) {
        return  new PageJson(new PageList<>(super.query(queryFilter)));
    }
    
	@Override
    public List<UserRole> query(DefaultQueryFilter  queryFilter) {
 		queryFilter.setPage(null);
 		return super.query(queryFilter);
 	}




}
