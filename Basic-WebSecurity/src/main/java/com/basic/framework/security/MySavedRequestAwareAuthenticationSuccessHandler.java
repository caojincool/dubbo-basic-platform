/**
 * 
 */
package com.basic.framework.security;

import java.io.IOException;
//import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.basic.framework.security.api.User;
import com.basic.framework.security.api.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.basic.framework.common.utils.HttpUtils;
//import com.basic.framework.security.model.SessionUser;
//import com.basic.framework.security.service.RedisSessionUser;


/**
 * @author lzj
 * @desc 登录验证成功处理器
 */
public class MySavedRequestAwareAuthenticationSuccessHandler extends
		SavedRequestAwareAuthenticationSuccessHandler {
	
	private static final Logger logger = LoggerFactory.getLogger(MySavedRequestAwareAuthenticationSuccessHandler.class);
	@Autowired
	private UserService userService;
	
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	
	@Autowired
	private SessionManager sessionManager;
	
	//defaultTargetUrl属性定义在抽象类AbstractAuthenticationTargetUrlRequestHandler

//	@Autowired
//	private SessionManager sessionManager;
//	@Autowired
//	private RedisSessionUser redisSessionUser;

	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication)
			throws ServletException, IOException {
			
		super.onAuthenticationSuccess(request, response, authentication);

		try{

			HttpSession session = request.getSession();

			Object obj = session.getAttribute(SecurityCommon.SPRING_SECURITY_CONTEXT);
			SecurityContext springSecurityContext;
			if(obj!=null){
				springSecurityContext = (SecurityContext)obj;
				MyUserDetail userDetail  = (MyUserDetail) springSecurityContext.getAuthentication().getPrincipal();
				logger.info("登录成功!,userAccount:{}",userDetail.getUsername());
				User user = userService.qryUserByAccount(userDetail.getUsername());
				
				if(user !=null){
//					SessionUser sessionUser = new SessionUser();
//					sessionUser.setUserAccount(user.getUserAccount());
//					sessionUser.setUserName(user.getUserName());
//					sessionUser.setUserId(user.getUserId());
					String cIp = HttpUtils.getIpAddr(request);
					String cHostName = HttpUtils.getHostName(cIp);
					String cBrowserInfo = HttpUtils.getRequestBrowserInfo(request);
//					String cSystemInfo = HttpUtils.getRequestSystemInfo(request);
					//TODO 获取mac地址太慢，先注释掉
					//String cMacAddress = HttpUtils.getMacAddress(cIp);
					
					user.setcIp(cIp);
					user.setcHostName(cHostName);
					user.setcBrowserInfo(cBrowserInfo);
//					user.setcSystemInfo
					//user.setcMacAddress(cMacAddress);
					session.setAttribute(SecurityCommon.SESSION_USER_KEY, user);
				}
				
				//记录日志
				try{
					sessionManager.logSystemLog(request);
				} catch(Exception e) {
					//logger.error("记录日志失败 e:{}", e);
					logger.error("记录日志失败！");
				}
				
//				saveRedisSession(request, user);
				
			}
		}catch (Exception e){
			logger.error("登陆后处理失败",e);
		}
		//此处可增加自定义逻辑，用于处理登录成功后
	}
	

	/**
	 * 
	 * @date 2017年6月27日 上午11:47:26
	 * 
	 * @Description: 保存redis上面的用户session
	 *
	 */
//	@SuppressWarnings("unused")
//	private void saveRedisSession(HttpServletRequest request, User user){
//		
//		HttpSession session = request.getSession();
//		
////		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
////		WebAuthenticationDetails wauth = (WebAuthenticationDetails) auth.getDetails();
////		System.out.println("当前登录用户的ip：" + wauth.getRemoteAddress());
////		System.out.println("当前登录用户的sessionID：" + wauth.getSessionId()+"request.getSession().getId()"+request.getSession().getId()); //Principal可转换为User//这个id是服务端的sessionid，不可取
//		SessionUser sessionUser = new SessionUser();
//		sessionUser.setSessionId(session.getId());
//		sessionUser.setUserId(1L);
//		sessionUser.setUserName(user.getUserAccount());
//		sessionUser.setUserAccount(user.getUserAccount());
//		sessionUser.setClientIp(request.getRemoteAddr());
//		System.out.println("当前登录用户的iprequest.getRemoteAddr()：" + request.getRemoteAddr());
//		sessionUser.setLastAccessedTime(new Date(session.getLastAccessedTime()));
//		
//		redisSessionUser.saveSession(sessionUser);
//		
//		session.setAttribute("sessionUser", sessionUser);
//		
//	}
	
}
