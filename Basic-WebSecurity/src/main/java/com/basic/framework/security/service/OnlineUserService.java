package com.basic.framework.security.service;

import java.util.List;

import com.basic.framework.security.model.OnlineUser;

/**
 * 
 *
 * @date 2017年6月28日 下午2:56:16
 * 
 * @Description: 在线用户操作
 *
 */
public interface OnlineUserService {

	/**
	 * 
	 * @date 2017年6月28日 下午2:57:51
	 * 
	 * @Description: 查询所有在线用户
	 * @return
	 *
	 */
	public List<OnlineUser> qryAllOnlineUser(OnlineUser ibean);
	
	/**
	 * 
	 * @date 2017年6月28日 下午2:59:51
	 * 
	 * @Description: 踢出一个在线用户
	 * @param ibean
	 *
	 */
	public void removeOnlineUser(OnlineUser ibean);
}
