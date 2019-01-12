package com.basic.framework.web.api;

import com.basic.oaas.model.UserDetail;

import javax.servlet.http.HttpServletRequest;


/**
 * 
 *
 * @date 2017年6月28日 下午3:51:26
 * 
 * @Description: 用户详细信息
 *
 */
public interface UserDetailService {

	/**
	 * 
	 * @date 2017年6月28日 下午4:34:24
	 * 
	 * @Description: 根据userId,companyId查询该用户的详细信息
	 * @param userId
	 * @return
	 *
	 */
	public UserDetail qryUserDetail(Long userId, Long companyId);
	
	public UserDetail qryUserDetailByRequest(HttpServletRequest request);
	
}
