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

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.security.DenyAll;

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
import com.basic.oaas.bean.UserDataInstIbean;
import com.basic.oaas.dao.UserDataInstMapper;
import com.basic.oaas.define.GidCodes;
import com.basic.oaas.model.User;
import com.basic.oaas.model.UserDataInst;

/**
 *
 * @date 2017年8月8日 上午9:57:38
 * @author Kevin
 * @Description: 账号数据权限实例
 * 
 */
@Service("userDataInstService")
public class UserDataInstServiceImpl extends BaseServerImpl<Long, UserDataInst> implements UserDataInstService {

	private Logger logger = LoggerFactory.getLogger(UserDataInstServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private UserDataInstMapper userDataInstMapper;
	@Autowired
	private UserService userService;
	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserDataInstService#removeByPrimaryKey(java.lang.Long)
	 */
	@Override
	public int removeByPrimaryKey(Long userDataInstId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeByPrimaryKey userDataInstId:{}", userDataInstId);
		}
		return userDataInstMapper.deleteByPrimaryKey(userDataInstId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserDataInstService#createSelective(com.basic.oaas.model.UserDataInst)
	 */
	@Override
	public int createSelective(UserDataInst record) {
		if (logger.isDebugEnabled()) {
			logger.debug("createSelective record:{}", JSON_UTILS.objectToJson(record));
		}
		
		Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_USER_DATA_INST_SEQ);
		record.setUserDataInstId(gid);
		return userDataInstMapper.insertSelective(record);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserDataInstService#qryByPrimaryKey(java.lang.Long)
	 */
	@Override
	public UserDataInst qryByPrimaryKey(Long userDataInstId) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryByPrimaryKey userDataInstId:{}", userDataInstId);
		}
		return userDataInstMapper.selectByPrimaryKey(userDataInstId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserDataInstService#modifyByPrimaryKeySelective(com.basic.oaas.model.UserDataInst)
	 */
	@Override
	public int modifyByPrimaryKey(UserDataInst record) {
		if (logger.isDebugEnabled()) {
			logger.debug("modifyByPrimaryKey record:{}", JSON_UTILS.objectToJson(record));
		}
		return userDataInstMapper.updateByPrimaryKeySelective(record);

	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserDataInstService#removeBatchByUserId(java.lang.Long)
	 */
	@Override
	public int removeBatchByUserId(Long userId) {
		if (logger.isDebugEnabled()) {
			logger.debug("removeBatchByUserId record:{}", userId);
		}
		return userDataInstMapper.deleteBatchByUserId(userId);
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserDataInstService#createBatchUserDataInst(java.util.List)
	 */
	@Override
	public int createBatchUserDataInst(UserDataInstIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("createBatchUserDataInst record:{}", JSON_UTILS.objectToJson(ibean));
		}
		
		//先删除所有的权限在插入
		userDataInstMapper.deleteBatchByUserId(ibean.getUserId());
		List<UserDataInst> list =ibean.getUserDataInsts();
		if (BeanUtils.isNotEmpty(list)) {
			for (UserDataInst userDataInst : list) {
				Long gid = GidClientUtils.getInstance().getGidValue(GidCodes.OAAS_USER_DATA_INST_SEQ);
				userDataInst.setUserDataInstId(gid);
				userDataInst.setUserId(ibean.getUserId());
			}
			
			
			return userDataInstMapper.insertBatchUserDataInst(list);
		}else {
			return 0;
		}
	}

	/* (non-Javadoc)
	 * @see com.basic.oaas.service.UserDataInstService#qryUserDataInstByUserId(java.lang.Long)
	 */
	@Override
	public List<UserDataInst> qryUserDataInstList(UserDataInstIbean ibean) {
		if (logger.isDebugEnabled()) {
			logger.debug("qryUserDataInstByUserId record:{}", JSON_UTILS.objectToJson(ibean));
		}
		Map<String, Object> params = new HashMap<String,Object>();
		params.put("userId", ibean.getUserId());
		params.put("companyId", ibean.getCompanyId());
		params.put("dataCode", ibean.getDataCode());
		return userDataInstMapper.selectUserDataInstList(params);
	}
	
	@Override
	public List<Long> getHasRightIds(Long userId,String scopeType){
		List<Long> ids = new ArrayList<Long>();
		User user = userService.getById(userId);
		if (user!=null) {
			if (user.getDataPrivExemption()!=null && 1==user.getDataPrivExemption()) {
				return null;
			}
			
			Map<String, Object> params = new HashMap<String,Object>();
			params.put("userId", userId);
			params.put("scopeType", scopeType);
			List<UserDataInst> list = userDataInstMapper.selectUserDataInstList(params);
			if (BeanUtils.isNotEmpty(list)) {
				for (UserDataInst inst : list) {
					ids.add(inst.getDataDataId());
				}
			}
		}
		return ids;
	}
	
	@Override
	public List<String> getHasRightCodes(Long userId,String scopeType){
		List<String> codes = new ArrayList<String>();
		User user = userService.getById(userId);
		if (user!=null) {
			if (user.getDataPrivExemption()!=null && 1==user.getDataPrivExemption()) {
				return null;
			}
			
			Map<String, Object> params = new HashMap<String,Object>();
			params.put("userId", userId);
			params.put("scopeType", scopeType);
			List<UserDataInst> list = userDataInstMapper.selectUserDataInstList(params);
			if (BeanUtils.isNotEmpty(list)) {
				for (UserDataInst inst : list) {
					codes.add(inst.getDataDataCode());
				}
			}
		}
		return codes;
	}

	@Override
	@Deprecated
	public void create(UserDataInst entity) {
		
	}

	@Override
	public void update(UserDataInst entity) {
		modifyByPrimaryKey(entity);
	}

	@Override
	public String getNamespace() {
		return UserDataInstMapper.class.getName();
	}
	
	@Override
 	public PageJson queryPage(DefaultQueryFilter  queryFilter) {
        return  new PageJson(new PageList<>(super.query(queryFilter)));
    }
    
    @Override
    public List<UserDataInst> query(DefaultQueryFilter  queryFilter) {
 		queryFilter.setPage(null);
 		return super.query(queryFilter);
 	}




}
