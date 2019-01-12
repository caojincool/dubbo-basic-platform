/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2015年12月5日 下午3:54:23
 * @author lzj
 * @Description: 全局配置
 * 
 */
package com.basic.framework.gid.server;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.basic.framework.common.utils.datatype.DoubleUtils;
import com.basic.framework.common.utils.datatype.LongUtils;

/**
 *
 * @date 2015年12月5日 下午3:54:23
 * @author lzj
 * @Description: 全局配置
 * 
 */
public class Config {

	private static final Logger logger = LoggerFactory.getLogger(Config.class);

	private static double scale;//水位
	private static long getTimeOut;//从缓冲池中取得序列超时时间
	
	private static long fillSleepTime;//填充间隔时间
	private static long updateSleepTime;//更新间隔时间
	
	private static final String GID_SERVER_PROPERTIES_PATH = "/config/properties/gidserver.properties";
	
	private Config(){
		
	}
	
	static{
		InputStream inputStream = Config.class.getResourceAsStream(GID_SERVER_PROPERTIES_PATH);

		Properties p = new Properties();
		try {
			p.load(inputStream);
			inputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
		String scaleStr = p.getProperty("gid.scale");
		scale = DoubleUtils.valueOf(scaleStr);

		String getTimeOutStr = p.getProperty("gid.gettimeout");
		getTimeOut = getTimeOutStr!=null?LongUtils.valueOf(getTimeOutStr):1000L;
		
		String fillSleepTimeStr = p.getProperty("gid.fillsleeptime");
		fillSleepTime = fillSleepTimeStr!=null?LongUtils.valueOf(fillSleepTimeStr):1000L;
		
		String updateSleepTimeStr = p.getProperty("gid.updatesleeptime");
		updateSleepTime = updateSleepTimeStr!=null?LongUtils.valueOf(updateSleepTimeStr):3000L;
		
		logger.info("Config param. scale:{},getTimeOut:{},fillSleepTime:{},updateSleepTime:{}",scale,getTimeOut,fillSleepTime,updateSleepTime);
	}
	
	public static double getScale() {
		return scale;
	}
	public static long getGetTimeOut() {
		return getTimeOut;
	}
	public static long getFillSleepTime() {
		return fillSleepTime;
	}
	public static long getUpdateSleepTime() {
		return updateSleepTime;
	}
	
	
	
}
