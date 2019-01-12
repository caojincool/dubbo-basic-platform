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
 * 空白页controller
 *
 * @date 2016年8月18日 下午2:37:00
 * @author lzj
 * @Description: 空白页controller
 *
 */
@Controller
@RequestMapping(value = DemoBlankController.REQUEST_PATH)
public class DemoBlankController extends BaseController {

	protected static final String REQUEST_PATH = "/demo";

	private static Logger logger = LoggerFactory.getLogger(DemoBlankController.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	/**
	 * 演示页
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/menuPage/demoBlank")
	public ModelAndView demoBlank(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		long t0 = System.currentTimeMillis();
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeMenuPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/demo/demoBlank");
		
		
		//业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);
		
		long t1 = System.currentTimeMillis();
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoPage getParameterMap:{},耗时:{}ms", JSON_UTILS.objectToJson(request.getParameterMap()),(t1-t0));
		}
		
		return pageView;
	}
	
	/**
	 * 演示页
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/menuPage/demoUeditor")
	public ModelAndView demoUeditor(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		long t0 = System.currentTimeMillis();
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoUeditor getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeMenuPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/demo/demoUeditor");
		
		
		//业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);
		
		long t1 = System.currentTimeMillis();
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoPage getParameterMap:{},耗时:{}ms", JSON_UTILS.objectToJson(request.getParameterMap()),(t1-t0));
		}
		
		return pageView;
	}
	
	/**
	 * 演示页
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/menuPage/demoIcon")
	public ModelAndView demoIcon(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		long t0 = System.currentTimeMillis();
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeMenuPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/demo/demoIcon");
		
		
		//业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);
		
		long t1 = System.currentTimeMillis();
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoPage getParameterMap:{},耗时:{}ms", JSON_UTILS.objectToJson(request.getParameterMap()),(t1-t0));
		}
		
		return pageView;
	}


//	/**
//	 * grid演示
//	 * 
//	 * @param ibean
//	 * @param request
//	 * @param response
//	 * @param model
//	 * @return
//	 * @throws Exception
//	 */
//	@RequestMapping(value = "/func/demoBlank/doQry")
//	@ResponseBody
//	public DemoBlankBean doQry(DemoBlankBean demoBlankBean, HttpServletRequest request,
//			HttpServletResponse response, Model model) throws Exception {
//
//		if (logger.isDebugEnabled()) {
//			logger.debug("请求参数  demoGridTree demoBlankBean:{},getParameterMap:{}",
//					JSON_UTILS.objectToJson(demoBlankBean),
//					JSON_UTILS.objectToJson(request.getParameterMap()));
//		}
//
//		this.doBeforeMenuPageAction(request, response, model, null);
//		
//		DemoBlankBean demoBlankBean1 = new DemoBlankBean();
//		demoBlankBean1.setStaffId(1L);
//		demoBlankBean1.setStaffName("World");
//		demoBlankBean1.setBirthDay(new java.util.Date());
//		
//		this.doAfterMenuPageAction(request, response, model, null);
//		return demoBlankBean1;
//	}

}
