package com.basic.framework.console.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.basic.framework.cache.Cache;
import com.basic.framework.cache.model.RedisCache;
import com.basic.framework.common.model.DealResult;
import com.basic.framework.common.utils.PropertiesUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.web.BaseController;

/**
 * 
 *
 * @date 2017年7月6日 上午10:17:35
 * 
 * @Description: redisCache
 *
 */
@Controller
@RequestMapping(value = RedisCacheController.REQUEST_PATH)
public class RedisCacheController extends BaseController{

	protected static final String REQUEST_PATH = "/cache";
	
	private static Logger logger = LoggerFactory.getLogger(RedisCacheController.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	private static final String REDISCACHECODE_PROPERTIES_PATH = "/config/properties/redisCacheCode.properties";
	
	@Autowired
	private Cache cacheRedis;

	/**
	 * 
	 * @date 2017年7月6日 上午10:23:59
	 * 
	 * @Description: redisCache主页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/menuPage/redisCache")
	public ModelAndView intoPage(HttpServletRequest request,HttpServletResponse response, Model model) {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  intoPage getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeMenuPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/cache/redisCache/redisCache");
		
		//业务逻辑结束
		this.doAfterMenuPageAction(request, response, model, null);
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年7月6日 上午10:24:07
	 * 
	 * @Description: redisCache查询
	 * @param ibean
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception 
	 *
	 */
	@RequestMapping(value = "/func/redisCache/pagin")
	@ResponseBody
	public String pagin(RedisCache ibean,HttpServletRequest request,HttpServletResponse response, Model model) throws Exception {

		if(logger.isDebugEnabled()){
			logger.debug("请求参数  pagin getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		
		if(StringUtils.isBlank(ibean.getKey())){
			ibean.setKey("*");
		}
		
		List<RedisCache> list = cacheRedis.getAllByKey("oaasStaffCache", ibean.getKey());
		int records = 0;//(int) excelExportTmpService.qryExcelExportTmpListCount(ibean);//总记录数
		//Integer offset = ibean.getOffset();
//		Integer limit = null;//ibean.getLimit();//每页记录数
		Integer page = null;//ibean.getPage();
		
		int total = 0;//records%limit==0? records/limit : records/limit +1;//总页数
		
		json = this.toDataGridJson(page, total, records, list);

		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		
		if(logger.isDebugEnabled()){
			logger.debug("输出参数json:{}", json);
		}
		return json;
	}
	
	/**
	 * 
	 * @date 2017年7月6日 上午10:24:17
	 * 
	 * @Description: redisCache新增修改详情页面
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/funcPage/redisCache/redisCacheDetail")
	public ModelAndView detail(HttpServletRequest request,HttpServletResponse response, Model model) {
				
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  detail getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		
		this.doBeforeFuncPageAction(request, response, model, null);
		
		//业务逻辑开始
		ModelAndView pageView = new ModelAndView("/cache/redisCache/redisCacheDetail");
		
		//业务逻辑结束
		this.doAfterFuncPageAction(request, response, model, null);
		
		return pageView;
	}
	
	/**
	 * 
	 * @date 2017年7月6日 上午10:24:24
	 * 
	 * @Description: redisCache新增编辑操作
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 * @throws Exception
	 *
	 */
	@RequestMapping(value = "/func/redisCache/createOrModify")
	@ResponseBody
	public Object createOrModify(HttpServletRequest request,HttpServletResponse response, Model model) throws Exception {

		if(logger.isDebugEnabled()){
			logger.debug("请求参数  createOrModify getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		String json = null;
		DealResult dealResult = new DealResult();
		
		try {
			// 获取并校验参数
			RedisCache ibean = JSON_UTILS.jsonToObject(this.getParameterFromRequest(request, "params"),
					RedisCache.class);		

			String cacheCode = ibean.getCacheCode();
			String key = ibean.getKey();
			String value = ibean.getValue();
			
			Object obj = JSON_UTILS.jsonToObject(value, Object.class);
			cacheRedis.setObject(cacheCode, key, obj);
			
			dealResult.setReturnCode(DealResult.SUCCESS);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(null);
			
		} catch (Exception e) {
			logger.error("redisCache新增编辑操作失败：{}", e);
			dealResult.setReturnCode(DealResult.FAILURE);
			dealResult.setReturnMsg(null);
			dealResult.setReturnData(null);
			
		}
		
		json = JSON_UTILS.objectToJson(dealResult);
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		return json;
	}
	
	/**
	 * 
	 * @date 2017年7月6日 上午10:24:32
	 * 
	 * @Description: redisCache删除
	 * @param templateCode
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/redisCache/del")
	@ResponseBody
	public Object del(String cacheCode,String key,HttpServletRequest request,HttpServletResponse response, Model model)  {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  del getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		
		String json = null;
		try {
			if(StringUtils.isNotBlank(cacheCode) && StringUtils.isNotBlank(key)){
				cacheRedis.removeByKey(cacheCode, key);
				json = "success";
			}
		} catch (Exception e) {
			logger.error("删除失败:{}", e);
			json = "failure";
		}
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		return json;
	}
	
	
	/**
	 * 
	 * @date 2017年9月28日 上午10:53:41
	 * 
	 * @Description: 获取所有的cacheCode
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 *
	 */
	@RequestMapping(value = "/func/redisCache/qryCacheCodes")
	@ResponseBody
	public Object qryCacheCodes(HttpServletRequest request,HttpServletResponse response, Model model)  {
		
		if(logger.isDebugEnabled()){
			logger.debug("请求参数  qryCacheCodes getParameterMap:{}", JSON_UTILS.objectToJson(request.getParameterMap()));
		}
		//业务逻辑开始
		this.doBeforeFuncAction(request, response, model, null);
		
		String json = null;
		
		Properties properties = PropertiesUtils.loadProperties(REDISCACHECODE_PROPERTIES_PATH);//读取自定义日期
		Set<Object> keys = properties.keySet();
//		Set<Entry<Object, Object>> hashTable = properties.entrySet();
//		Collection<Object> values = properties.values();
		Map<Object, Object> resultMap = new HashMap<Object, Object>();
		if(keys != null && keys.size() > 0){
			for(Object key : keys){
				Object value = properties.get(key);
				resultMap.put(key, value);
			}
		}
		
		DealResult dealResult = new DealResult();
		dealResult.setReturnCode(DealResult.QRYSUCCESS);
		dealResult.setReturnMsg(null);
		dealResult.setReturnData(resultMap);
		
		json = JSON_UTILS.objectToJson(dealResult);
		
		//业务逻辑结束
		this.doAfterFuncAction(request, response, model, null);
		return json;
	}
	
}
