package com.basic.framework.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.basic.framework.web.BaseController;
import com.basic.framework.common.utils.datatype.JsonUtils;

/**
 * 模态窗口
 *
 * @date 2016年8月18日 下午4:32:25
 * @author lzj
 * @Description: 模态窗口
 *
 */
@Controller
@RequestMapping(value = DemoOpenWinController.REQUEST_PATH)
public class DemoOpenWinController extends BaseController {

	protected static final String REQUEST_PATH = "/demo";

	private static Logger logger = LoggerFactory.getLogger(DemoOpenWinController.class);
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	/**
	 * 模态窗口
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/menuPage/demoOpenWin")
	public ModelAndView demoOpenWin(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		long t0 = System.currentTimeMillis();
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoOpenWin getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeMenuPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/demo/demoOpenWin");
		
		//业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);
		
		long t1 = System.currentTimeMillis();
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoOpenWin getParameterMap:{},耗时:{}ms", JSON_UTILS.objectToJson(request.getParameterMap()),(t1-t0));
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
	@RequestMapping(value = "/funcPage/demoModalWin")
	public ModelAndView demoModalWin(HttpServletRequest request,HttpServletResponse response, Model model) {
		long t0 = System.currentTimeMillis();

		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoModalWin getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/demo/demoModalWin");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		long t1 = System.currentTimeMillis();
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoModalWin getParameterMap:{},耗时:{}ms", JSON_UTILS.objectToJson(request.getParameterMap()),(t1-t0));
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
	@RequestMapping(value = "/funcPage/demoModalWin1")
	public ModelAndView demoModalWin1(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		long t0 = System.currentTimeMillis();
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoModalWin1 getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/demo/demoModalWin1");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		long t1 = System.currentTimeMillis();
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  demoModalWin1 getParameterMap:{},耗时:{}ms", JSON_UTILS.objectToJson(request.getParameterMap()),(t1-t0));
		}

		return pageView;
	}

}
