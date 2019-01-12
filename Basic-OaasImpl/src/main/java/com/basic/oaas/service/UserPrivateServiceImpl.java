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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.basic.framework.common.utils.BeanUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.gid.utils.sequence.GidClientUtils;
import com.basic.oaas.bean.UserPrivateIbean;
import com.basic.oaas.dao.UserPrivateMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.model.UserPrivate;

/**
 *
 * @date 2017年8月8日 上午9:57:38
 * @author Kevin
 * @Description: 用户目录接口实现类
 * 
 */
@Service("userPrivateService")
public class UserPrivateServiceImpl implements UserPrivateService {

	private Logger logger = LoggerFactory.getLogger(UserPrivateServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private UserPrivateMapper userPrivateMapper;
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserPrivateService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long userPrivateId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey userPrivateId:{}", userPrivateId);
		}
		return userPrivateMapper.deleteByPrimaryKey(userPrivateId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserPrivateService#createSelective(com.basic.oaas.model.UserPrivate)
	 */
	@Override
	public int createSelective(UserPrivate record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_USER_PRIVATE_SEQ);
		record.setUserPrivateId(gid);
		return userPrivateMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserPrivateService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public UserPrivate qryByPrimaryKey(Long userPrivateId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey userPrivateId:{}", userPrivateId);
		}
		return userPrivateMapper.selectByPrimaryKey(userPrivateId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserPrivateService#modifyByPrimaryKeySelective(com.basic.oaas.model.UserPrivate)
	 */
	@Override
	public int modifyByPrimaryKey(UserPrivate record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKey record:{}", JSON_UTILS.objectToJson(record));
		}
		return userPrivateMapper.updateByPrimaryKey(record);

	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserPrivateService#removeBatchByUserId(java.lang.Long)
	 */
	@Override
	public int removeBatchByUserId(Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeBatchByUserId record:{}", userId);
		}
		return userPrivateMapper.deleteBatchByUserId(userId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserPrivateService#createBatchUserPrivate(java.util.List)
	 */
	@Override
	public int createBatchUserPrivate(UserPrivateIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("createBatchUserPrivate record:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		//先删除所有的权限在插入
		//userPrivateMapper.deleteBatchByUserId(ibean.getUserId());
		
		List<UserPrivate> list =ibean.getUserPrivates();
		if (BeanUtils.isNotEmpty(list)) {
			for (int i = list.size()-1; i >=0; i--) {
				UserPrivate userPrivate = list.get(i);
				Map<String, Object> params = new HashMap<String,Object>();
				params.put("privateId", userPrivate.getPrivateId());
				params.put("userId", ibean.getUserId());
				if (userPrivateMapper.checkExist(params)>0) {
					list.remove(userPrivate);
				}else {
					Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_USER_PRIVATE_SEQ);
					userPrivate.setUserPrivateId(gid);
					userPrivate.setUserId(ibean.getUserId());
					userPrivate.setCreateTime(new Date());
				}
			}
		}
		if (BeanUtils.isNotEmpty(list)) {
			return userPrivateMapper.insertBatchUserPrivate(list);
		} else {
			return 0;
		}
		
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserPrivateService#qryUserPrivateByUserId(java.lang.Long)
	 */
	@Override
	public List<UserPrivate> qryUserPrivateByUserId(Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryUserPrivateByUserId record:{}", userId);
		}
		return userPrivateMapper.selectUserPrivateByUserId(userId);
	}

	@Override
	public int deleteBatchByIds(Long userId,Long[] ids) {
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("userId", userId);
		params.put("ids", ids);
		return userPrivateMapper.deleteBatchByIds(params);
	}




}
