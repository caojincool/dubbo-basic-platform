package com.basic.framework.console.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.basic.framework.cache.model.EhcacheBean;
import com.basic.framework.common.model.DealResult;
import com.basic.framework.common.utils.HttpUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.web.BaseController;

/**
 * 
 *
 * @date 2017年8月7日 下午3:18:58
 * 
 * @Description: ehcache缓存
 *
 */
@Controller
@RequestMapping(value = EhcacheController.REQUEST_PATH)
public class EhcacheController extends BaseController {

	protected static final String REQUEST_PATH = "/cache";
	
	private static Logger logger = LoggerFactory.getLogger(EhcacheController.class);
	
//	private static final String EHCACHE_PROPERTIES_PATH = "/config/properties/ehcache.properties";

	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	//服务端地址
	private static String serverUrl = null;
	
	//改成页面输入
//	private EhCacheController() {
//		//读取EhCache配置
//		Properties ehcacheProperties = PropertiesUtils.loadProperties(EHCACHE_PROPERTIES_PATH);
//		//服务端地址
//		serverUrl = ehcacheProperties.getProperty("ehcache.server.url");
//	}
	
	/**
	 * 
	 * @date 2017年8月7日 下午3:21:43
	 * 
	 * @Description: ehcache主页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/menuPage/ehcache")
	public ModelAndView ehcache(HttpServletRequest request, HttpServletResponse response, Model model) {

		if (logger.isDebugEnabled()) {
			logger.debug("请求参数  ehcache getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeMenuPageAction(request, response, model, null);

		// 业务逻辑开始
		ModelAndView pageView = new ModelAndView("/cache/ehcache/ehcache");

		// 业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);

		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年8月7日 下午3:21:59
	 * 
	 * @Description: ehcache查询
	 * @param address
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/ehcache/pagin")
	@ResponseBody
	public String pagin(String address,HttpServletRequest request,HttpServletResponse response, Model model) {

		if(logger.isDebugEnabled()){
			logger.debug("请求参数  pagin getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		if(logger.isDebugEnabled()){
			logger.debug("-------通过url读取jetty发布的服务-------");
		}
		
		serverUrl = "http://"+address+"/ehcacheJettyService";
		String urlParam = "?method=qryCacheList";
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
	
	/**
	 * 
	 * @date 2017年8月7日 下午3:22:13
	 * 
	 * @Description: 根据cacheName清除一个ehcache缓存
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/ehcache/clearCache")
	@ResponseBody
	public Object clearCache(EhcacheBean ibean,HttpServletRequest request,HttpServletResponse response, Model model)  {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  clearCache getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		
		String json = null;
		DealResult dealResult = new DealResult();
		try {
			if(StringUtils.isNotBlank(ibean.getCacheName())){
				String result = null;
				String urlParam = "?method=clearCache&cacheName="+ibean.getCacheName();
				result = HttpUtils.request(serverUrl, urlParam);
				//json = result;
				//json = "success";
				if("success".equals(result)){
					dealResult.setReturnCode(DealResult.SUCCESS);
				}
			}
		} catch (Exception e) {
			logger.error("清空一个:{}, 缓存失败:{}", ibean.getCacheName(), e);
			//json = "failure";
			dealResult.setReturnCode(DealResult.FAILURE);
			dealResult.setReturnMsg(null);
		}
		dealResult.setReturnData(null);
		
		json = JSON_UTILS.objectToJson(dealResult);
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		return json;
	}
	
	/**
	 * 
	 * @date 2017年8月7日 下午3:22:38
	 * 
	 * @Description: 根据cacheName清除一个ehcache缓存
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/ehcache/clearAll")
	@ResponseBody
	public Object clearAll(HttpServletRequest request,HttpServletResponse response, Model model)  {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  clearAll getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		
		String json = null;
		DealResult dealResult = new DealResult();
		try {
			String result = null;
			String urlParam = "?method=clearAll";
			result = HttpUtils.request(serverUrl, urlParam);
			//json = result;
			//json = "success";
			if("success".equals(result)){
				dealResult.setReturnCode(DealResult.SUCCESS);
			}
		} catch (Exception e) {
			logger.error("清空全部失败:{}", e);
			//json = "failure";
			dealResult.setReturnCode(DealResult.FAILURE);
			dealResult.setReturnMsg(null);
		}
		dealResult.setReturnData(null);
		
		json = JSON_UTILS.objectToJson(dealResult);
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		return json;
	}
	
}
