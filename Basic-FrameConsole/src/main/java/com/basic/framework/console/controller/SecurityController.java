package com.basic.framework.console.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.RedisTemplate;
//import org.springframework.security.core.session.SessionRegistry;
//import org.springframework.session.data.redis.RedisOperationsSessionRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.basic.framework.common.model.DealResult;
//import com.basic.framework.cache.CacheRedis;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.security.model.OnlineUser;
import com.basic.framework.security.service.OnlineUserService;
import com.basic.framework.web.BaseController;

/**
 * 
 *
 * @date 2017年6月22日 下午6:08:50
 * 
 * @Description: 操作在线用户
 *
 */
@Controller
@RequestMapping(value = SecurityController.REQUEST_PATH)
public class SecurityController extends BaseController {

	protected static final String REQUEST_PATH = "/security";

	private static Logger logger = LoggerFactory.getLogger(SecurityController.class);
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	@Autowired
	private OnlineUserService onlineUserService;
//	@Autowired
//	private RedisOperationsSessionRepository sessionRepository;
//	@Autowired    
//	private SessionRegistry sessionRegistry;
//	@Autowired
//	private CacheRedis<?, ?> cacheRedis;
	
	/**
	 * 
	 * @date 2017年6月22日 下午6:09:06
	 * 
	 * @Description: 在线用户页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/menuPage/onlineUser")
	public ModelAndView onlineUser(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeMenuPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/security/onlineUser");
		
		//业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);
		
		return pageView;
	}

	/**
	 * 
	 * @date 2017年6月22日 下午6:09:38
	 * 
	 * @Description: 查询在线用户
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/qryOnlineUser", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String qryOnlineUser(OnlineUser ibean,HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  onlineUser getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		List<OnlineUser> onlineUsers = onlineUserService.qryAllOnlineUser(ibean);
		Integer page = null;
		Integer total = null;
		Integer records = null;
		json = this.toDataGridJson(page, total, records, onlineUsers);
		
		//业务逻辑结束

		this.doAfterFuncAction(request, response, model, null);
		return json;
	}
	
	/**
	 * 
	 * @date 2017年6月22日 下午6:10:00
	 * 
	 * @Description: 踢出在线用户
	 * @param onlineUser
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/logoutUser", produces = { "application/json;charset=UTF-8" })
	@ResponseBody
	public String logoutUser(OnlineUser ibean,HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  onlineUser getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		DealResult dealResult = new DealResult();
		
		try {
			if(ibean != null){
				onlineUserService.removeOnlineUser(ibean);
				
				dealResult.setReturnCode(DealResult.SUCCESS);
				dealResult.setReturnMsg(null);
				dealResult.setReturnData(null);
			}
		}catch (Exception e) {
			logger.error("踢出用户失败:{}", e);
			
			dealResult.setReturnCode(DealResult.FAILURE);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(null);
		}
		
		json = JSON_UTILS.objectToJson(dealResult);
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		return json;
	}

}
