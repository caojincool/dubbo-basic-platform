/**
 * 
 */
package com.basic.framework.security;

import org.springframework.security.web.authentication.logout.LogoutFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

/**
 * @author lzj
 * @desc 自定义退出过滤器
 */
public class MyLogoutFilter extends LogoutFilter {

	public MyLogoutFilter(LogoutSuccessHandler logoutSuccessHandler,
			LogoutHandler[] handlers){
		super(logoutSuccessHandler, handlers);
	}
	
	public MyLogoutFilter(String logoutSuccessUrl, LogoutHandler[] handlers) {
		super(logoutSuccessUrl, handlers);
	}

}
