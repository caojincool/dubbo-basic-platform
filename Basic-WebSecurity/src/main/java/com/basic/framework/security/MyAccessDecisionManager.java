/**
 * 
 */
package com.basic.framework.security;

import java.util.Collection;
import java.util.Iterator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;


/**
 * @author lzj
 * @desc 自定义spring security决策器，判断用户是否拥有所请求资源的权限
 */
public class MyAccessDecisionManager implements AccessDecisionManager {

	private static final Logger logger = LoggerFactory.getLogger(MyAccessDecisionManager.class);
	
	@Override
	public void decide(Authentication authentication, Object obj,
			Collection<ConfigAttribute> configAttributes) throws AccessDeniedException,
			InsufficientAuthenticationException {
		// 请求所需要的权限
		Iterator<ConfigAttribute> iterator = configAttributes.iterator();
		while (iterator.hasNext()) {
			ConfigAttribute configAttribute = iterator.next();
			// 访问所请求资源所需要的权限
			String needPermission = configAttribute.getAttribute();

			logger.info("需要的权限:{} " , needPermission);

			if("NONE".equals(needPermission)){
				return;
			}
			// 用户所拥有的权限authentication
			for (GrantedAuthority ga : authentication.getAuthorities()) {
				logger.debug("当前操作人拥有的权限：{}",ga.getAuthority());
				if (needPermission.equals(ga.getAuthority())) {

					return ;
				}
			}
		}
		logger.info("没有权限访问！");

			// 没有权限
		throw new AccessDeniedException("没有权限访问！");
	}

	@Override
	public boolean supports(ConfigAttribute arg0) {
		return true;
	}

	@Override
	public boolean supports(Class<?> arg0) {
		return true;
	}

}
