package com.basic.framework.console.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.basic.framework.common.utils.HttpUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.web.BaseController;

/**
 * 
 *
 * @date 2017年7月21日 下午4:13:39
 * 
 * @Description: 线程监控
 *
 */
@Controller
@RequestMapping(value = ProcessMonitorController.REQUEST_PATH)
public class ProcessMonitorController extends BaseController {

	protected static final String REQUEST_PATH = "/process";
	
	private static Logger logger = LoggerFactory.getLogger(ProcessMonitorController.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	//服务端地址
	private static String serverUrl = null;
	
	/**
	 * 
	 * @date 2017年7月21日 下午4:13:18
	 * 
	 * @Description: 进入菜单页面 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/menuPage/processMonitor")
	public ModelAndView intoPage(HttpServletRequest request, HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  intoPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeMenuPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/process/processMonitor/processMonitor");
		
		//业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年7月21日 下午4:13:07
	 * 
	 * @Description: 查询
	 * @param address
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/processMonitor/pagin")
	@ResponseBody
	public String pagin(String address,HttpServletRequest request,HttpServletResponse response, Model model) {

		if(logger.isDebugEnabled()){
			logger.debug("请求参数  pagin getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		serverUrl = "http://"+address+"/processJettyService";
		String urlParam = "?method=getThreadState";
		String result = null;
		try {
			result = HttpUtils.request(serverUrl, urlParam);
		} catch (Exception e) {
			logger.error("jetty返回参数出错:{}", e);
		}

		StringBuilder jsonStr = new StringBuilder("{");
		jsonStr.append("\"page\": ");
		jsonStr.append(1);
		jsonStr.append(", \"total\": ");
		jsonStr.append(1);
		jsonStr.append(", \"records\": ");
		jsonStr.append(-1);
		jsonStr.append(", \"root\": ");
		jsonStr.append(result);
		jsonStr.append("}");

		json = jsonStr.toString();
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		
		if(logger.isDebugEnabled()){
			logger.debug("输出参数json:{}", json);
		}
		return json;
	}
	
}
