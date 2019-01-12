/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2015年12月5日 下午4:05:13
 * @author lzj
 * @Description: 启动服务
 * 
 */
package com.basic.framework.gid.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.eclipse.jetty.util.resource.Resource;
import org.eclipse.jetty.xml.XmlConfiguration;
import org.eclipse.jetty.server.Server;

/**
 * GID 服务端，客户端通过此服务
 * 
 * @date 2015年12月5日 下午4:05:13
 * @author lzj
 * @Description: GID 服务端
 * 
 */
public class GidServerTest {

	private static final Logger logger = LoggerFactory.getLogger(GidServerTest.class);

	/**
	 * 启动服务
	 * @date 2015年12月11日 下午3:01:20
	 * @author lzj
	 * @Description: 启动服务
	 * @param args
	 * @throws Exception
	 *
	 */
	public static void main(String[] args) throws Exception {

		logger.info("GidServer init start......");
		Resource serverXml = Resource.newSystemResource("/config/spring/jetty.xml");
		XmlConfiguration configuration = new XmlConfiguration(serverXml.getInputStream());
		Server server = (Server) configuration.configure();
		server.start();
		server.join();

		logger.info("GidServer init success......");
	}

}
