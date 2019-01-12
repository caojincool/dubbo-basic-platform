package com.basic.framework.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.web.BaseController;

/**
 * 标签页
 *
 * @date 2016年8月18日 下午4:27:44
 * @author lzj
 * @Description: TODO(这里用一句话描述这个类的作用)
 *
 */
@Controller
@RequestMapping(value = DemoTabController.REQUEST_PATH)
public class DemoTabController extends BaseController {

	protected static final String REQUEST_PATH = "/demo";

	private static Logger logger = LoggerFactory.getLogger(DemoTabController.class);
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	/**
	 * 标签页
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/menuPage/demoTab")
	public ModelAndView demoBlank(HttpServletRequest request,HttpServletResponse response, Model model) {
		long t0 = System.currentTimeMillis();
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoFile getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeMenuPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/demo/demoTab");
		
		//业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);
		
		long t1 = System.currentTimeMillis();
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoFile getParameterMap:{},耗时:{}ms", JSON_UTILS.objectToJson(request.getParameterMap()),(t1-t0));
		}
		
		return pageView;
	}
}
