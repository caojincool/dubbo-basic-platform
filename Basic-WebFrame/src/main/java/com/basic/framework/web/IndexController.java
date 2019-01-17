package com.basic.framework.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.basic.framework.common.utils.datatype.JsonUtils;


/**
 * 主页
 * @author lzj
 *
 */
@Controller
public class IndexController  extends BaseController {


	private static Logger logger = LoggerFactory.getLogger(IndexController.class);
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	/**
	 * 主页
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/index")
	public ModelAndView index(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  index getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeMenuPageAction(request, response, model, null);

		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/index");
		
//		UserDetail user = new UserDetail();
//		user.setUserId(1L);
//		Role role = new Role();
//		role.setRoleCode("superadminRole");
//		role.setRoleId(1L);
//		role.setRoleName("超级管理员");
//		
//		List<Role> roles = new ArrayList<Role>();
//		roles.add(role);
//		user.setRoles(roles);
		
//		pageView.addObject("mySessionUser", JSON_UTILS.objectToJson(user));
		
//		SessionUser sessionUser = this.getSession(request);
//		if(sessionUser!=null){
//			pageView.addObject("mySessionUser", JsonUtils.toJsonStringNoException(sessionUser));
//		}else{
//			pageView = new ModelAndView("redirect:/login.do");
//		}
		

		
		//业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);
		
		
		return pageView;
	}
}
