package com.basic.framework.demo.service;

import javax.servlet.http.HttpServletRequest;

import com.basic.framework.web.api.UserDetailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.web.model.Role;
import com.basic.oaas.model.UserDetail;

/**
 * 
 *
 * @date 2017年6月28日 下午4:32:41
 * 
 * @Description: 用户详细信息
 *
 */
//@Service("userDetailService")
public class UserDetailServiceImpl implements UserDetailService {
    
	private Logger logger = LoggerFactory.getLogger(UserDetailServiceImpl.class);

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	
	/* (non-Javadoc)
	 * @see com.basic.framework.web.api.UserDetailService#qryUserDetail(java.lang.Long, java.lang.Long)
	 */
	@Override
	public UserDetail qryUserDetail(Long userId, Long companyId) {
		// TODO Auto-generated method stub
		return null;
	}

	/* (non-Javadoc)
	 * @see com.basic.framework.web.api.UserDetailService#qryUserDetailByRequest(javax.servlet.http.HttpServletRequest)
	 */
	@Override
	public UserDetail qryUserDetailByRequest(HttpServletRequest request) {
		
		
		UserDetail user = new UserDetail();
		user.setUserId(1L);
		Role role = new Role();
		role.setRoleCode("superadminRole");
		role.setRoleId(1L);
		role.setRoleName("超级管理员");
		
		return user;
	}


}
