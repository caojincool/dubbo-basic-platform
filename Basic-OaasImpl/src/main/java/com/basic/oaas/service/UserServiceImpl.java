/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年7月5日 上午10:49:45
 * @author Kevin
 * @Description: UserService实现类
 * 
 */
package com.basic.oaas.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.basic.framework.cache.CacheRedis;
import com.basic.framework.cache.model.RedisCache;
import com.basic.framework.common.api.db.domain.PageJson;
import com.basic.framework.common.api.db.domain.PageList;
import com.basic.framework.common.baseServerImpl.BaseServerImpl;
import com.basic.framework.common.model.DefaultQueryFilter;
import com.basic.framework.common.utils.BeanUtils;
import com.basic.framework.common.utils.EncryptUtil;
import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.oaas.bean.UserIBean;
import com.basic.oaas.dao.UserMapper;
import com.basic.oaas.dao.UserStaffMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.model.Job;
import com.basic.oaas.model.Org;
import com.basic.oaas.model.User;

/**
 *
 * @date 2017年7月5日 上午10:49:45
 * @author Kevin
 * @Description: UserService
 * 
 */
@Service("userService")
public class UserServiceImpl extends BaseServerImpl<Long, User> implements UserService {

	private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);
	
	private JsonUtils jsonUtils = JsonUtils.getInstance();
	
	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private UserStaffMapper userStaffMapper;
	
	@Autowired
	private  CacheRedis<?, ?> cacheRedis;
	
	@Value( "${userDetailCache}" )
	private String userDetailCache;
	
	@Value( "${tokenCache}" )
	private String tokenCache;
	
	 
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserService#addSelective(com.basic.oaas.model.User)
	 */
	@Override
	public User createSelective(User user) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective User:{}", jsonUtils.objectToJson(user));
		}

		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_USER_SEQ);
		user.setUserId(gid);
		user.setState("10A");
		user.setUserPassword(EncryptUtil.encryptSha256(user.getUserPassword()));
		
		if (user.getOpenStatus()==null || user.getOpenStatus() == 1) {
			user.setOpenStatus(1);
			user.setOpenDate(new Date());
		}else if(user.getOpenStatus() == 0) {
			user.setStopDate(new Date());
		}
		//user.setCreateUserId();
		user.setCreateTime(DateUtils.now());
		userMapper.insertSelective(user);
		return user;
	}
	
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserService#qryUserById(java.lang.Long)
	 */
	@Override
	public User qryUserById(Long userId) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("qryUserById userId:{}", jsonUtils.objectToJson(userId));
		}
		User user = null;
		Object obj = null;
		try {
			obj = cacheRedis.getObject( userDetailCache, userId.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (obj==null) {
			user = userMapper.selectByPrimaryKey(userId);
		}else {
			user = (User)obj;
		}
		return user;
	}
	
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserService#modifyByPrimaryKey(com.basic.oaas.model.User)
	 */
	@Override
	public int modifyByPrimaryKey(User user) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKey User:{}", jsonUtils.objectToJson(user));
		}
		
		user.setModifyTime(DateUtils.now());
		
		User db = userMapper.selectByPrimaryKey(user.getUserId());
		if (db!=null) {
			//与数据库不一致才修改
			if ((user.getOpenStatus()==null || user.getOpenStatus() == 1)
					&& db.getOpenStatus()==0) {
				user.setOpenStatus(1);
				user.setOpenDate(new Date());
			}else if(user.getOpenStatus()!=null
					&& user.getOpenStatus() == 0 
					&& db.getOpenStatus()==1) {
				user.setStopDate(new Date());
				//停用删除当前已登录的帐号缓存，不允许再操作
				List<User> caches = cacheRedis.getAllByCode(tokenCache);
				if (BeanUtils.isNotEmpty(caches)) {
					for (User cache : caches) {
						if (user.getUserId().equals(cache.getUserId())) {
							cacheRedis.removeByKey(tokenCache, cache.getCurrentIp()+"*"+user.getUsername());
						}
					}
				}
			}
		}
		return userMapper.updateByPrimaryKey(user);
	}
	
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserService#removeByPrimarykey(java.lang.Long)
	 */
	@Override
	public int removeByPrimarykey(Long userId) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimarykey userId:{}", jsonUtils.objectToJson(userId));
		}
		
		return userMapper.deleteByPrimaryKey(userId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserService#qryUserByUsername(java.lang.String)
	 */
	@Override
	public User qryUserByUsername(String username) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("qryUserByUsername username:{}", jsonUtils.objectToJson(username));
		}
		
		return userMapper.selectByUsername(username);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserService#qryUserList(com.basic.oaas.bean.UserIBean)
	 */
	@Override
	public List<User> qryUserList(UserIBean userIBean) {

		if (logger.isDebugEnabled()) {
			logger.debug("qryUserList userIBean:{}", jsonUtils.objectToJson(userIBean));
		}
		
		return userMapper.selectUserList(userIBean);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserService#qryUserCount(com.basic.oaas.bean.UserIBean)
	 */
	@Override
	public long qryUserCount(UserIBean userIBean) {
		
		if (logger.isDebugEnabled()) {
			logger.debug("qryUserCount userIBean:{}", jsonUtils.objectToJson(userIBean));
		}
		
		long count = 0;
		
		count = userMapper.selectUserCount(userIBean);
		
		return count;
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserService#removeBatchByUserIds(java.lang.Long[])
	 */
	@Override
	public int removeBatchByUserIds(Long[] userIds) {
		
		if(logger.isDebugEnabled()){
			logger.debug("removeBatchByUserIds userIds:{}", jsonUtils.objectToJson(userIds));
		}
		
		int count = 0;
		if(userIds != null && userIds.length > 0){
			count = userMapper.updateBatchByUserIds(userIds);
			for (Long userId : userIds) {
				cacheRedis.removeByKey(userDetailCache, userId.toString());
			}
		}
		return count;
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserService#qryByOrgId(java.lang.Long)
	 */
	@Override
	public List<User> qryByOrgId(Long orgId) {
		if(logger.isDebugEnabled()){
			logger.debug("qryByOrgId orgId:{}", orgId);
		}
		
		List<User> list = null;
		if(null != orgId){
			list = userMapper.selectByOrgId(orgId);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserService#qryByStaffId(java.lang.Long)
	 */
	@Override
	public User qryByStaffId(Long staffId) {
		if(logger.isDebugEnabled()){
			logger.debug("qryByStaffId staffId:{}", staffId);
		}
		
		User user = null;
		if(null != staffId){
			//一个员工对应多个帐号，这里只拿一个不止有何用意，本方法没有被引用先注释
			//user = userMapper.selectByStaffId(staffId);
		}
		return user;
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserService#qryByJobId(java.lang.Long)
	 */
	@Override
	public List<User> qryByJobId(Long jobId) {
		if(logger.isDebugEnabled()){
			logger.debug("qryByJobId jobId:{}", jobId);
		}
		
		List<User> list = null;
		if(null != jobId){
			list = userMapper.selectByJobId(jobId);
		}
		return list;
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserService#qryByRoleId(java.lang.Long)
	 */
	@Override
	public List<User> qryByRoleId(Long roleId) {
		if(logger.isDebugEnabled()){
			logger.debug("qryByRoleId roleId:{}", roleId);
		}
		
		List<User> list = null;
		if(null != roleId){
			list = userMapper.selectByRoleId(roleId);
		}
		return list;
	}

	@Override
	public void create(User entity) {
		this.createSelective(entity);
	}

	@Override
	public void update(User entity) {
		this.modifyByPrimaryKey(entity);
		
	}

	@Override
	public String getNamespace() {
		return UserMapper.class.getName();
	}
	
	@Override
	public PageJson queryPage(DefaultQueryFilter queryFilter) {
        return new PageJson(new PageList<>(super.query(queryFilter)));
    }
    
	@Override
    public List<User> query(DefaultQueryFilter  queryFilter) {
 		queryFilter.setPage(null);
 		return super.query(queryFilter);
 	}

	@Override
	public List<User> getAvaliableAccounts(Map<String,Object> params) {
		return userMapper.getAvaliableAccounts(params);
	}

	@Override
	public List<User> getBindingAccounts(Map<String, Object> params) {
		return userMapper.getBindingAccounts(params);
	}

	@Override
	public User getById(Long id) {
		return userMapper.getById(id);
	}

	@Override
	public int changePwd(User record) {
		return userMapper.changePwd(record);
	}

	@Override
	public List<User> selectByOrgIds(List<Long> orgIds) {
		return userMapper.selectByOrgIds(orgIds);
	}

    @Override
    public  List<String> getOutSideCode(Long userId) {
        return userMapper.getOutSideCode(userId);
    }

}
