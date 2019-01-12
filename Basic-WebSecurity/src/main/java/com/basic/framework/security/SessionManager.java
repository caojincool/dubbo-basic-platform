package com.basic.framework.security;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import com.basic.framework.security.api.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;

import com.basic.framework.common.utils.datatype.DateUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.log.client.LogClient;
import com.basic.log.define.LogTypeEnum;
import com.basic.log.model.SystemLog;

/**
 * session管理
 *
 * @date 2016年10月27日 下午3:34:22
 * @author lzj
 * @Description: session管理
 *
 */
@Component("sessionManager")
public class SessionManager {

//	private static final Logger logger = LoggerFactory.getLogger(SessionManager.class);
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private LogClient logClient;
	
	/**
	 * 获取spring安全上下文
	 * 
	 * @date 2016年10月27日 下午3:36:52
	 * @author lzj
	 * @Description: 获取spring安全上下文
	 * @param session
	 * @return
	 *
	 */
	public SecurityContext getSpringSecurityContext(HttpSession session) {
		SecurityContext springSecurityContext = null;
		Object obj = session.getAttribute(SecurityCommon.SPRING_SECURITY_CONTEXT);
		if (obj != null) {
			springSecurityContext = (SecurityContext) obj;
		}
		return springSecurityContext;
	}

	/**
	 * 设置sessionUser
	 * 
	 * @date 2016年10月27日 下午3:40:46
	 * @author lzj
	 * @param session
	 * @param sessionUser
	 *
	 */
	public void setSessionUser(HttpSession session, User sessionUser) {
		session.setAttribute(SecurityCommon.SESSION_USER_KEY, sessionUser);
		return;
	}

	/**
	 * 在Session中获取sessionUser
	 * 
	 * @date 2016年10月27日 下午3:42:31
	 * @author lzj
	 * @param session
	 * @return
	 *
	 */
	public User getSessionUser(HttpSession session) {
		User user = null;
		Object obj = session.getAttribute(SecurityCommon.SESSION_USER_KEY);
		if (obj != null) {
			user = (User) obj;
		}
		return user;
	}

//	/**
//	 * 设置菜单列表
//	 * 
//	 * @date 2016年10月27日 下午3:45:21
//	 * @author lzj
//	 * @param session
//	 * @param menuList
//	 *
//	 */
//	public void setSessionMenuList(HttpSession session, List<OaasPrivateMenu> menuList) {
//		session.setAttribute(SecurityCommon.SESSION_MENU_LIST, menuList);
//		return;
//	}
//
//	/**
//	 * 移除菜单列表
//	 * 
//	 * @date 2016年10月27日 下午3:46:02
//	 * @author lzj
//	 * @param session
//	 *
//	 */
//	public void removeSessionMenuList(HttpSession session) {
//		session.removeAttribute(SecurityCommon.SESSION_MENU_LIST);
//		return;
//	}
//
//	/**
//	 * 获取菜单列表
//	 * 
//	 * @date 2016年10月27日 下午3:47:11
//	 * @author lzj
//	 * @param session
//	 *
//	 */
//	@SuppressWarnings("unchecked")
//	public List<OaasPrivateMenu> getSessionMenuList(HttpSession session) {
//		List<OaasPrivateMenu> menuList = null;
//		Object obj = session.getAttribute(SecurityCommon.SESSION_MENU_LIST);
//		if(obj!=null){
//			menuList = (List<OaasPrivateMenu>) obj;
//		}
//		return menuList;
//	}

	/**
	 * 
	 * @date 2017年10月16日 上午10:41:47
	 * 
	 * @Description: 记录系统日志
	 * @param request
	 *
	 */
	public void logSystemLog(HttpServletRequest request){
		String requestUrl = request.getRequestURI();
		requestUrl = requestUrl.substring(requestUrl.indexOf("/",1)+1);//因数据库不在写工程名称，把工程名称和/去掉
		String requestContent = JSON_UTILS.objectToJson(request.getParameterMap());
		String logType = null;
		User user = getSessionUser(request.getSession());
		if(user != null){
			Long userId = user.getUserId();
			Date now = DateUtils.now();
//			String custId = HttpUtils.getIpAddr(request);
			String custId = user.getcIp();
			SystemLog systemLog = new SystemLog();
//			systemLog.setlogId
			systemLog.setRequestUrl(requestUrl);
			systemLog.setRequestContent(requestContent);
			systemLog.setLogType(logType);
			systemLog.setUserId(userId);
			systemLog.setCreateTime(now);
			systemLog.setCustId(custId);
			if(requestUrl.indexOf("login/login") != -1){//登录
				logType = LogTypeEnum.LOGIN.getCode();
			}else if(requestUrl.indexOf("menuPage/") != -1){//菜单
				logType = LogTypeEnum.MENU.getCode();
			}else if(requestUrl.indexOf("funcPage/") != -1){//操作
				logType = LogTypeEnum.FUNC.getCode();
			}
			systemLog.setLogType(logType);
			logClient.sendLog(systemLog);
		}
	}
	
}
