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

import java.text.DecimalFormat;
import java.util.Date;
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
import com.basic.framework.common.utils.BeanUtils;
import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.oaas.bean.RoleIbean;
import com.basic.oaas.dao.RoleMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.model.Org;
import com.basic.oaas.model.Role;
import com.basic.oaas.model.Staff;

/**
 *
 * @date 2017年8月8日 上午9:57:38
 * @author Kevin
 * @Description: 菜单接口实现类
 * 
 */
@Service("roleService")
public class RoleServiceImpl extends BaseServerImpl<Long, Role> implements RoleService {

	private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private RoleMapper roleMapper;
	
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.RoleService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long roleId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey roleId:{}", roleId);
		}
		return roleMapper.deleteByPrimaryKey(roleId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.RoleService#createSelective(com.basic.oaas.model.Private)
	 */
	@Override
	public int createSelective(Role record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_ROLE_SEQ);
		record.setRoleId(gid);
		record.setCreateTime(DateUtils.now());
		record.setState("10A");
		if (record.getOpenStatus()==null || record.getOpenStatus() == 1) {
			record.setOpenStatus(1);
			record.setOpenDate(new Date());
		}else if(record.getOpenStatus() == 0) {
			record.setStopDate(new Date());
		}
		//生成编码
		Map<String, Object> params = new HashMap<String,Object>();
		int count = roleMapper.countRole(params);
		record.setRoleCode("R"+new DecimalFormat("000").format(count+1));
		return roleMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.RoleService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public Role qryByPrimaryKey(Long roleId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey roleId:{}", roleId);
		}
		return roleMapper.selectByPrimaryKey(roleId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.RoleService#modifyByPrimaryKeySelective(com.basic.oaas.model.Private)
	 */
	@Override
	public int modifyByPrimaryKeySelective(Role record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKeySelective record:{}", JSON_UTILS.objectToJson(record));
		}
		record.setModifyTime(DateUtils.now());
		Role recordDb = roleMapper.selectByPrimaryKey(record.getRoleId());
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
		return roleMapper.updateByPrimaryKey(record);

	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.RoleService#qryPrivateList(com.basic.oaas.bean.PrivateIbean)
	 */
	@Override
	public List<Role> qryRoleList(RoleIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryRoleList RoleIbean:{}", JSON_UTILS.objectToJson(ibean));
		}	
		return roleMapper.selectRoleList(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.RoleService#qryPrivateListCount(com.basic.oaas.bean.PrivateIbean)
	 */
	@Override
	public long qryRoleListCount(RoleIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryRoleListCount RoleIbean:{}", JSON_UTILS.objectToJson(ibean));
		}
		return roleMapper.selectRoleListCount(ibean);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.RoleService#createOrModify(com.basic.oaas.model.Private)
	 */
	@Override
	public void createOrModify(Role ibean) {
		if(logger.isDebugEnabled()){
			logger.debug("createOrModify Role:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		if(ibean.getRoleId() == null){//新增
			createSelective(ibean);
		}else{//修改
			modifyByPrimaryKeySelective(ibean);
		}
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.RoleService#removeBatchByroleIds(java.lang.Long[])
	 */
	@Override
	public int removeBatchByRoleIds(Long[] roleIds) {
		if(logger.isDebugEnabled()){
			logger.debug("removeBatchByRoleIds roleIds:{}", JSON_UTILS.objectToJson(roleIds));
		}
		return roleMapper.updateBatchByRoleIds(roleIds);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.RoleService#qryRoleByUsername(java.lang.String)
	 */
	@Override
	public List<Role> qryRoleByUsername(String username) {
		if(logger.isDebugEnabled()){
			logger.debug("qryRoleByUsername username:{}", JSON_UTILS.objectToJson(username));
		}
		return roleMapper.selectRoleByUsername(username);
	}

	@Override
	public void create(Role entity) {
		createOrModify(entity);
	}

	@Override
	public void update(Role entity) {
		createOrModify(entity);
	}

	@Override
	public String getNamespace() {
		return RoleMapper.class.getName();
	}
	
	@Override
	public PageJson queryPage(DefaultQueryFilter queryFilter) {
		return new PageJson(new PageList<>(super.query(queryFilter)));
	}
	
	@Override
	public List<Role> query(DefaultQueryFilter queryFilter) {
		queryFilter.setPage(null);
 		return super.query(queryFilter);
	}

}
