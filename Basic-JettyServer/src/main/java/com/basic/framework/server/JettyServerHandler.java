package com.basic.framework.server;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.Request;
import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.basic.framework.common.utils.datatype.JsonUtils;

public class JettyServerHandler extends AbstractHandler {

	private static final Logger logger = LoggerFactory.getLogger(JettyServerHandler.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	private static Map<String,JettyServiceInterface> serviceMap;
	
	public static void setServiceMap(Map<String, JettyServiceInterface> serviceMap) {
		logger.info("setServiceMap:{}", JSON_UTILS.objectToJson(serviceMap.keySet()));
		JettyServerHandler.serviceMap = serviceMap;
		logger.info("setServiceMap:{}", JSON_UTILS.objectToJson(JettyServerHandler.serviceMap.keySet()));
		
	}

	@Override
	public void handle(String target, Request baseRequest, HttpServletRequest request, HttpServletResponse response)
			throws IOException, ServletException {
		logger.debug("handle target:{},baseRequest:{}",target,baseRequest);
		
		if(target!=null){
			logger.info("serviceMap", JSON_UTILS.objectToJson(serviceMap.keySet()));

			JettyServiceInterface jettyService = serviceMap.get(target);
			if(jettyService!=null){
				jettyService.invoke(baseRequest, request, response);

			}
		}else{
			String result = "Hello World";
			
			logger.debug("handle target:{},result:{}",target,result);
			
			response.setContentType("text/html;charset=utf-8");
			response.getWriter().write(result);
			response.getWriter().close();
			
		}
		
	}  
	
	

}
