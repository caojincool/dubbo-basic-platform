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
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.oaas.bean.RolePrivateIbean;
import com.basic.oaas.dao.RolePrivateMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.model.RolePrivate;
import com.basic.oaas.model.User;

/**
 *
 * @date 2017年8月8日 上午9:57:38
 * @author Kevin
 * @Description: 角色权限接口实现类
 * 
 */
@Service("rolePrivateService")
public class RolePrivateServiceImpl extends BaseServerImpl<Long, RolePrivate> implements RolePrivateService {

	private Logger logger = LoggerFactory.getLogger(RolePrivateServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private RolePrivateMapper rolePrivateMapper;
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.RolePrivateService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long rolePrivateId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey rolePrivateId:{}", rolePrivateId);
		}
		return rolePrivateMapper.deleteByPrimaryKey(rolePrivateId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.RolePrivateService#createSelective(com.basic.oaas.model.RolePrivate)
	 */
	@Override
	public int createSelective(RolePrivate record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_ROLE_PRIVATE_SEQ);
		record.setRolePrivateId(gid);
		return rolePrivateMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.RolePrivateService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public RolePrivate qryByPrimaryKey(Long rolePrivateId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey rolePrivateId:{}", rolePrivateId);
		}
		return rolePrivateMapper.selectByPrimaryKey(rolePrivateId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.RolePrivateService#modifyByPrimaryKeySelective(com.basic.oaas.model.RolePrivate)
	 */
	@Override
	public int modifyByPrimaryKey(RolePrivate record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKey record:{}", JSON_UTILS.objectToJson(record));
		}
		return rolePrivateMapper.updateByPrimaryKey(record);

	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.RolePrivateService#removeBatchByRoleId(java.lang.Long)
	 */
	@Override
	public int removeBatchByRoleId(Long roleId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeBatchByRoleId record:{}", roleId);
		}
		return rolePrivateMapper.deleteBatchByRoleId(roleId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.RolePrivateService#createBatchRolePrivate(java.util.List)
	 */
	@Override
	public int createBatchRolePrivate(RolePrivateIbean ibean,User user) {
		if (logger.isDebugEnabled()) {
			logger.debug("createBatchRolePrivate record:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		List<RolePrivate> list =ibean.getRolePrivates();
		
		if(list == null || list.size() < 1){
			return 0;
		}
		
		for (RolePrivate rolePrivate : list) {
			Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_ROLE_PRIVATE_SEQ);
			rolePrivate.setRolePrivateId(gid);
			rolePrivate.setRoleId(ibean.getRoleId());
			rolePrivate.setCreateUserId(user.getUserId());
			rolePrivate.setCreateUserName(user.getUserText());
			rolePrivate.setCreateTime(new Date());
		}
		return rolePrivateMapper.insertBatchRolePrivate(list);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.RolePrivateService#qryRolePrivateByRoleId(java.lang.Long)
	 */
	@Override
	public List<RolePrivate> qryRolePrivateByRoleId(Long roleId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryRolePrivateByRoleId record:{}", roleId);
		}
		return rolePrivateMapper.selectRolePrivateByRoleId(roleId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.RolePrivateService#removeBatchByRolePrivateIds(java.lang.Long[])
	 */
	@Override
	public int removeBatchByRolePrivateIds(Long[] rolePrivateIds) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeBatchByRolePrivateIds rolePrivateIds:{}", JSON_UTILS.objectToJson(rolePrivateIds));
		}
		return rolePrivateMapper.deleteBatchByRolePrivateIds(rolePrivateIds);
	}

	@Override
	public void create(RolePrivate entity) {
		createSelective(entity);
	}

	@Override
	public void update(RolePrivate entity) {
		modifyByPrimaryKey(entity);
	}

	@Override
	public String getNamespace() {
		return RolePrivateMapper.class.getName();
	}
	
	@Override
	public PageJson queryPage(DefaultQueryFilter  queryFilter) {
        return  new PageJson(new PageList<>(super.query(queryFilter)));
    }
    
	@Override
    public List<RolePrivate> query(DefaultQueryFilter  queryFilter) {
 		queryFilter.setPage(null);
 		return super.query(queryFilter);
 	}




}
