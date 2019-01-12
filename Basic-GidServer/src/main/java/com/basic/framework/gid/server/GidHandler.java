/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2015年12月11日 下午3:02:57
 * @author lzj
 * @Description: GID 服务响应
 * 
 */
package com.basic.framework.gid.server;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.eclipse.jetty.server.handler.AbstractHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.eclipse.jetty.server.Request;

/**
 *
 * @date 2015年12月11日 下午3:02:57
 * @author lzj
 * @Description: GID 服务响应
 * 
 */
public class GidHandler extends AbstractHandler {

	private static final Logger logger = LoggerFactory.getLogger(GidHandler.class);
	private GidManager gidManager;
	
	public GidHandler(){
		logger.error("GidHandler init start......");
		
		this.gidManager = BeanFactoryUtils.getBeanByName("gidManager", GidManager.class);
		this.gidManager.init();

		logger.error("GidHandler init success......");

	}
	
	/**
	 * 
	 * @date 2015年12月24日 下午6:20:22
	 * @author lzj
	 * @Description: 
	 * @param gidCode
	 * @return -1 未管理当前GID，-2GID缓冲池中无数据，-3传入的gidCode为空 -4其它错误
	 * 改造点：向客户端发送的数据为 json字符串，包含的字段为，当前值CURR_VALUE和客户端的容量CLI_CAPACITY。
	 *
	 */
	@Override
    public void handle(String path, Request r, HttpServletRequest req, HttpServletResponse res)
			throws IOException, ServletException {
		logger.debug("handle ........... path:{}",path);
		String gidCode = req.getParameter("gidCode");

		logger.debug("handle ........... gidCode:{}",gidCode);

		res.setContentType("text/html;charset=utf-8");
		String clientData = null;
		Long gidValue = null;
		int capacity = 0;
		int increamentBy = 0;
		if (gidCode == null) {
			logger.error("gidCode param can not be null !!");
			gidValue = -3L;
		} else {

			gidValue = gidManager.getGidValue(gidCode);
			capacity  = gidManager.getClientCacheSize(gidCode);
			increamentBy = gidManager.getIncreamentBy(gidCode);
			clientData = appentResult(gidValue,capacity,increamentBy);
//			long t1 = System.currentTimeMillis();
		}
		gidValue = gidValue==null?-4L:gidValue;
		res.getWriter().write(clientData);
		res.getWriter().close();
		
	}
	
	private String appentResult(Long gidValue,int cacheSize,int increamentBy){
		return String.valueOf(gidValue)+","+String.valueOf(cacheSize)+","+String.valueOf(increamentBy);
	}
}
