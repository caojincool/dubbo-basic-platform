package com.basic.framework.cache.impl;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.basic.framework.cache.model.EhcacheBean;
import com.basic.framework.cache.service.EhcacheService;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.server.JettyServiceInterface;

/**
 * 
 *
 * @date 2017年8月7日 上午10:09:34
 * 
 * @Description: ehcache的jetty实现类
 *
 */
//@Service
public class EhcacheJettyServiceImpl implements JettyServiceInterface{

	private static final Logger logger = LoggerFactory.getLogger(EhcacheJettyServiceImpl.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	private static final String METHOD_QRY_CACHE_LIST = "qryCacheList";
	private static final String METHOD_QRY_CACHE_SIZE_BY_CACHE_NAME = "qryCacheSizeByCacheName";
	private static final String METHOD_CLEAR_CACHE = "clearCache";
	private static final String METHOD_CLEAR_ALL = "clearAll";

//	@Autowired
	private EhcacheService ehcacheService;
	
	public void setEhcacheService(EhcacheService ehcacheService) {
		this.ehcacheService = ehcacheService;
	}

	@Override
	public void invoke(Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		String result = null;
		String method = request.getParameter("method");
		
		if(logger.isDebugEnabled()){
			logger.debug("invoke method:{}", method);
		}
		
		if(METHOD_QRY_CACHE_LIST.equals(method)){
			List<EhcacheBean> list = ehcacheService.qryCacheList();
			result = JSON_UTILS.objectToJson(list);
		}else if(METHOD_QRY_CACHE_SIZE_BY_CACHE_NAME.equals(method)){
			String cacheName = request.getParameter("cacheName");
			int size = ehcacheService.qryCacheSizeByCacheName(cacheName);
			result = "size:"+size;
		}else if(METHOD_CLEAR_CACHE.equals(method)){
			String cacheName = request.getParameter("cacheName");
			ehcacheService.clearCache(cacheName);
			result="success";
		}else if(METHOD_CLEAR_ALL.equals(method)){
			ehcacheService.clearAll();
			result="success";
		}
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(result);
		response.getWriter().close();
	}
	
}
