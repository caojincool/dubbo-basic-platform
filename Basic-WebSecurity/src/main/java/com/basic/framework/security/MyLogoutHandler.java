/**
 * 
 */
package com.basic.framework.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
//import javax.servlet.http.HttpSession;

//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
//import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.web.authentication.logout.LogoutHandler;

//import com.basic.framework.security.model.SessionUser;
//import com.basic.framework.security.service.RedisSessionUser;

/**
 * @author lzj
 * @desc 自定义退出登录后追加处理
 */
public class MyLogoutHandler implements LogoutHandler {

//	@Autowired
//	private RedisSessionUser redisSessionUser;
//	@Autowired    
//	private SessionRegistry sessionRegistry;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.logout.LogoutHandler#logout(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void logout(HttpServletRequest request,
			HttpServletResponse response, Authentication authentication) {
		
		System.out.println("退出！！！");
//		sessionRegistry.removeSessionInformation(request.getSession().getId());
//		removeRedisSession(request);
	}
	
	/**
	 * 
	 * @date 2017年6月27日 上午11:44:26
	 * 
	 * @Description: 删除redis上面的用户session
	 *
	 */
//	@SuppressWarnings("unused")
//	private void removeRedisSession(HttpServletRequest request){
//		HttpSession session = request.getSession();
//		
//		if(session != null){
//			SessionUser sessionUser = new SessionUser();
//			sessionUser.setSessionId(session.getId());
//			redisSessionUser.removeSession(sessionUser);
//		}
//	}
}
