package com.basic.framework.web.api;

import org.springframework.ui.Model;

import javax.servlet.http.HttpServletRequest;

/**
 * 
 *
 * @date 2017年8月28日 上午10:29:10
 * 
 * @Description: 用于加载用户的权限，包括菜单等
 *
 */
public interface UserPrivateService {

	/**
	 * 
	 * @date 2017年8月28日 上午10:31:05
	 * 
	 * @Description: 加载当前用户的目录树
	 * @param request
	 * @param model
	 *
	 */
	public void appendUserMenuData(HttpServletRequest request, Model model);

}
