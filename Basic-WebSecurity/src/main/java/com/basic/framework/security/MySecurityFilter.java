/**
 * 
 */
package com.basic.framework.security;

import java.io.IOException;
//import java.util.Date;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

//import com.basic.framework.security.model.SessionUser;
//import com.basic.framework.security.service.RedisSessionUser;

/**
 * @author lzj
 * @desc 自定义权限认证过滤器
 */
public class MySecurityFilter extends AbstractSecurityInterceptor implements Filter {
	
	private static final Logger logger = LoggerFactory.getLogger(MySecurityFilter.class);
	
//	private static final JsonUtils jsonUtils = JsonUtils.getInstance();
	
//	@Autowired
//	private RedisSessionUser redisSessionUser;
	@Autowired
	private SessionManager sessionManager;
	
	//authenticationManager\accessDecisionManager\securityMetadataSource，前两个在AbstractSecurityInterceptor中已经定义
	private FilterInvocationSecurityMetadataSource securityMetadataSource;
	
	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return securityMetadataSource;
	}

	public void setSecurityMetadataSource(
			FilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}

	@Override
	public void destroy() {

	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain arg2) throws IOException, ServletException {
		FilterInvocation fi = new FilterInvocation(arg0, arg1, arg2);
		invoke(fi);
	}

	private void invoke(FilterInvocation fi) throws IOException, ServletException {
		logger.info("授权认证");
		InterceptorStatusToken token = null;
		try{ 
			token = super.beforeInvocation(fi);
//			updateRedisSession(fi);
		} catch(RuntimeException e) {
			logger.info("授权失败：" + e.getLocalizedMessage());
			throw e;
		}
		
		//记录日志
		//没登录的过滤是没有用户session的
		try{
			HttpServletRequest request = fi.getRequest();
			sessionManager.logSystemLog(request);
		} catch(Exception e) {
			//logger.error("记录日志失败 e:{}", e);
			logger.error("记录日志失败！");
		}
		
		try {
			//获取过滤链执行下一个过滤器
			fi.getChain().doFilter(fi.getRequest(), fi.getResponse());
		} finally {
			super.afterInvocation(token, null);
		}
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {

	}

	@Override
	public Class<?> getSecureObjectClass() {
		//下面的MyAccessDecisionManager的supports方面必须放回true，否则会提醒类型错误
		return FilterInvocation.class;
	}

	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		return this.securityMetadataSource;
	}


	/**
	 * 
	 * @date 2017年6月27日 上午11:37:45
	 * 
	 * @Description: 更新redis上面的用户session
	 *
	 */
//	@SuppressWarnings("unused")
//	private void updateRedisSession(FilterInvocation fi){
//		HttpServletRequest request = fi.getRequest();
//		HttpSession session = request.getSession();
//
//		Object obj = request.getSession().getAttribute("sessionUser");
//
//		if(null != obj){
//			SessionUser user = (SessionUser) obj;
//			
////			Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////			WebAuthenticationDetails wauth = (WebAuthenticationDetails) auth.getDetails();
////			System.out.println("当前登录用户的ip：" + wauth.getRemoteAddress());
////			System.out.println("当前登录用户的sessionID：" + wauth.getSessionId()+"request.getSession().getId()"+request.getSession().getId()); //Principal可转换为User//这个id是服务端的sessionid，不可取
//			SessionUser sessionUser = new SessionUser();
//			sessionUser.setSessionId(session.getId());
//			sessionUser.setUserId(1L);
//			sessionUser.setUserName(user.getUserAccount());
//			sessionUser.setUserAccount(user.getUserAccount());
//			sessionUser.setClientIp(request.getRemoteAddr());
//			System.out.println("当前登录用户的iprequest.getRemoteAddr()：" + request.getRemoteAddr());
//			sessionUser.setLastAccessedTime(new Date(session.getLastAccessedTime()));
//			
//			redisSessionUser.saveSession(sessionUser);
//		}
//	}
	
}
