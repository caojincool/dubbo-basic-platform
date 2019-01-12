/**
 * 
 */
package com.basic.framework.web;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.common.utils.result.HttpEntity;
import com.basic.framework.common.utils.result.HttpStatus;
import com.basic.oaas.model.UserDetail;

/**
 * @author YeRunhua
 *
 */
@Controller
public class LoginController extends BaseController {
	
	private static Logger logger = LoggerFactory.getLogger(LoginController.class);
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
//	@Autowired
//	private UserService userService;
	
	@RequestMapping(value = "/login")
	public ModelAndView display(HttpServletRequest request, HttpServletResponse response) {
		logger.debug("display 访问登录页面");
		ModelAndView page = new ModelAndView("/login");
//		if(this.hasCookie(request)) {//已登录
		//单点登录情况下，不为空
		UserDetail userDetail = this.getSession(request);
		if(userDetail!=null){
			logger.debug("display redirect:/index.do");
			page = new ModelAndView("redirect:/index.do");
		} else {//未登录
			try {
				Object obj = request.getParameter("message");
				if(request.getParameter("message") != null) {
					logger.debug(obj.toString());
					page.addObject("message", URLDecoder.decode(request.getParameter("message").toString(), "UTF-8"));
				}
			} catch (UnsupportedEncodingException e) {
				logger.error("转码异常：" + e.getLocalizedMessage(), e);
			}
		}
		
//		try {
//			Object obj = request.getParameter("message");
//			if(request.getParameter("message") != null) {
//				logger.debug(obj.toString());
//				page.addObject("message", URLDecoder.decode(request.getParameter("message").toString(), "UTF-8"));
//			}
//		} catch (UnsupportedEncodingException e) {
//			logger.error("转码异常：" + e.getLocalizedMessage(), e);
//		}
		return page;
	}
	
	@RequestMapping(value = "/testLogin")
	@ResponseBody
	public HttpEntity testLogin(HttpServletRequest request, HttpServletResponse response) {
		//单点登录情况下，不为空
		UserDetail userDetail = this.getSession(request);
		if(userDetail!=null){
			return new HttpEntity(HttpStatus.OK,true,"请求成功",userDetail);
		} else {//未登录
			return new HttpEntity(HttpStatus.OK,false,"请求成功","去登陆页面登陆吧");
		}
		
	}
	
	
	
	
	/**
	 * 判断浏览器中是否有保存名为SPRING_SECURITY_REMEMBER_ME_COOKIE的Cookie
	 * 
	 * @return
	 */
	public boolean hasCookie(HttpServletRequest request) {
		boolean result = false;
		Cookie[] cookies = request.getCookies();
		
		logger.debug("hasCookie cookies:{}", JSON_UTILS.objectToJson(cookies));
		
		if(cookies != null) {
			if(cookies != null && cookies.length > 0) {
				for (int i = 0; i < cookies.length; i++) {
					if("SPRING_SECURITY_REMEMBER_ME_COOKIE".equals(cookies[i].getName())) {
						result = true;
						break;
					}
				}
			}
		}
		return result;
	}
	
}
