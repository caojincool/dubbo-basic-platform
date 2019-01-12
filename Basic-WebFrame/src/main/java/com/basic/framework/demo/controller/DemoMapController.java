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

@Controller
@RequestMapping(value = DemoMapController.REQUEST_PATH)
public class DemoMapController extends BaseController {

	protected static final String REQUEST_PATH = "/demo";

	private static Logger logger = LoggerFactory.getLogger(DemoMapController.class);
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	/**
	 * 演示页
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/menuPage/demoMap")
	public ModelAndView demoBlank(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoFile getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeMenuPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/demo/demoMap");
		
		//业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);
		
		return pageView;
	}
	
}
