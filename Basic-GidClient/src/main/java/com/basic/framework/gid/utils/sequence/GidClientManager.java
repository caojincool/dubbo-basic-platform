/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2015年12月24日 下午5:45:17
 * @author lzj
 * @Description: GID 客户端管理
 * 
 */
package com.basic.framework.gid.utils.sequence;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.basic.framework.common.utils.PropertiesUtils;
import com.basic.framework.common.utils.datatype.DoubleUtils;
import com.basic.framework.common.utils.datatype.IntegerUtils;
import com.basic.framework.common.utils.datatype.JsonUtils;
import com.basic.framework.common.utils.datatype.LongUtils;


/**
 * GID 客户端管理
 * 
 * @date 2015年12月24日 下午5:45:17
 * @author lzj
 * @Description: GID 客户端管理
 * 
 */
public class GidClientManager {

	private static Logger logger = LoggerFactory.getLogger(GidClientManager.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();

	private static final String GID_CLENT_PROPERTIES_PATH = "/config/properties/gidclient.properties";
	
	private static String[] serverUrlArr = null;//服务端地址数组
	private static Long timeOut = null;//超时时间
	private static Long sleepTime = null;//休眠时间
	private static Double scale = null;//水位

	private static GidClientManager inst = new GidClientManager();
	private static ConcurrentMap<String, BlockingQueue<Long>> gidPoolMap;
	private static ConcurrentMap<String, GidVo> gidVoMap;

	private static final String PARAM_NAME = "?gidCode=";
	long t2 = 0;
	private GidClientManager() {
		logger.info("GidClientManager init......");
		try{
			init();
		}catch(Exception e){
			logger.error("GidClientManager init ERROR",e);
		}
		
		logger.info("GidClientManager init success......");
	}

	static GidClientManager getInstance() {
		return inst;
	}

	/**
	 * 初始化
	 * 
	 * @date 2015年12月24日 下午6:16:54
	 * @author lzj
	 * @throws InterruptedException 
	 * @Description: 初始化
	 *
	 */
	private void init() throws InterruptedException {
		
		//初始化GidClient
		Properties gidClientProperties = PropertiesUtils.loadProperties(GID_CLENT_PROPERTIES_PATH);//读取GidClient配置
		
		//服务端地址
		String urls = gidClientProperties.getProperty("gid.server.url");
		logger.info("urls:{}", urls);
		serverUrlArr = urls.split(",");
		
		//超时时间
		String timeOutStr = gidClientProperties.getProperty("gid.timeout");
		timeOut = timeOutStr!=null?LongUtils.valueOf(timeOutStr):1000L;
		
		//水位
		String scaleStr = gidClientProperties.getProperty("gid.scale");
		scale = DoubleUtils.valueOf(scaleStr);
				
		//休眠时间
		String sleepTimeStr = gidClientProperties.getProperty("gid.sleeptime");
		sleepTime = LongUtils.valueOf(sleepTimeStr);

		logger.info("serverUrlArr:{},timeOut:{},scale:{}",serverUrlArr,timeOut,scale);
		
		gidPoolMap = new ConcurrentHashMap<String, BlockingQueue<Long>>();
		gidVoMap = new  ConcurrentHashMap<String, GidVo>();

		logger.info("GidClientManager init. gid:{}", JSON_UTILS.objectToJson(gidVoMap));
	}
	
	/**
	 * 
	 * @date 2016年8月4日 下午3:28:36
	 * @author cht
	 * @Description: 改造：使用哪一类id就缓存哪一类id
	 * @param gidCode
	 * @throws InterruptedException
	 *
	 */
	private void setGidPoolMap(String gidCode) throws InterruptedException{
		if(null == gidPoolMap.get(gidCode)){
			//TODO 这里获取一次，下面又获取一次，会造成浪费，后面有时间可以改
			String gidAndSize = getGidSizeFromServer(gidCode);
			String[] tmpArr = gidAndSize.split(",");
			Integer clientCacheSize = IntegerUtils.valueOf(tmpArr[1]);//客户端缓存大小
			Integer increamentBy = IntegerUtils.valueOf(tmpArr[2]);//步长

			GidVo gidVo = new GidVo();
			gidVo.setGidCode(gidCode);
			gidVo.setClientCacheSize(clientCacheSize);
			gidVo.setIncreamentBy(increamentBy);
			
			gidVoMap.put(gidCode, gidVo);
			
//			BlockingQueue<Long> queue = new ArrayBlockingQueue<Long>(cacheSize*2);
			BlockingQueue<Long> queue = new ArrayBlockingQueue<Long>(clientCacheSize.intValue()*2);

			gidPoolMap.put(gidCode, queue);
			
			this.fillGidPool(gidVo, queue);
			
			FillerThread fillerThread = new FillerThread(gidVo,queue);
			Thread thread = new Thread(fillerThread,gidVo.getGidCode());
			thread.start();
		}
	}
	
	/**
	 * 
	 * @date 2015年12月24日 下午6:20:22
	 * @author lzj
	 * @Description: 
	 * @param gidCode
	 * @return
	 *
	 */
	Long getGidValue(String gidCode)  {
		Long gidValue = null;
		try {
			setGidPoolMap(gidCode);
			BlockingQueue<Long> queue = gidPoolMap.get(gidCode);
			if(queue!=null){
				gidValue = queue.poll(timeOut,TimeUnit.MILLISECONDS);
				if(gidValue == null){
					this.fillGidPool(gidCode, queue);
					gidValue = queue.poll(timeOut,TimeUnit.MILLISECONDS);
				}	
			}
		} catch (Exception e) {
			logger.error("getGidValue error. gidCode:{}",gidCode,e);
			throw new RuntimeException("gidCode:"+gidCode+"获取异常",e);
		}
		return gidValue;
	}
	
	/**
	 * 填充GID缓冲池
	 * @date 2015年12月25日 上午10:56:59
	 * @author lzj
	 * @Description: 填充GID缓冲池
	 * @param gidCode
	 * @param queue
	 * @throws InterruptedException 
	 *
	 */
	private void fillGidPool(String gidCode,BlockingQueue<Long> queue) throws InterruptedException{
		GidVo gidVo = gidVoMap.get(gidCode);
		this.fillGidPool(gidVo, queue);
	}

	/**
	 * 
	 * @date 2016年8月1日 上午9:59:58
	 * @author cht
	 * @Description: 填充缓冲池
	 * @param gidVo
	 * @param queue
	 * @throws InterruptedException
	 *
	 */
	private void fillGidPool(GidVo gidVo,BlockingQueue<Long> queue) throws InterruptedException{
		String gidAndSize = this.getGidSizeFromServer(gidVo.getGidCode());
		String[] gidAndSizeArray  = gidAndSize.split(",");
		if(StringUtils.isNotBlank(gidAndSize)){
			Long gidValue = LongUtils.valueOf(gidAndSizeArray[0]);
			int clientCacheSize  = IntegerUtils.valueOf(gidAndSizeArray[1]);
			//必须减去客户端缓存大小，不然造成大量浪费，例如：客户端缓存大小是100，当前值为1，缓存大小为50，服务端将会缓存（101,201,...5001）,程序用http第一次拿到的gid值是101
			gidValue = gidValue - clientCacheSize;
			int increamentBy = IntegerUtils.valueOf(gidAndSizeArray[2]);
			if(gidValue!=null && gidValue>0){
				//queue.clear();
				for(int i=0;i<clientCacheSize;i++){
					queue.put(gidValue);
					gidValue = gidValue + increamentBy;
				}
			}
		}else{
			logger.info("获取服务器数据失败 GidVo:{}", JSON_UTILS.objectToJson(gidVo));
		}
	}
	
	/**
	 * 
	 * @date 2016年8月1日 上午9:52:07
	 * @author cht
	 * @Description: 从服务端获取gid和步长和客户端缓存数量
	 * @param gidCode
	 * @return
	 *
	 */
	private String getGidSizeFromServer(String gidCode) {
		String[] urlArr = serverUrlArr;
		String gidAndSize = null;
		try{
			int len = urlArr.length;
			for(int i=0;i<len;i++){
				gidAndSize = this.getGidSizeFromServer(urlArr[i], gidCode);
				if(StringUtils.isNotEmpty(gidAndSize)){
					break;
				}else{
					logger.info("服务器连接失败：url:"+urlArr[i]);
					if(StringUtils.isEmpty(gidAndSize)){
						logger.info("服务器连接失败：url:"+urlArr[i]);
					}
				}
			}
		}catch(Exception e){
				logger.error("error Exception:{}", e);
			}
		return gidAndSize;
	}
	
	/**
	 * 
	 * @date 2016年8月1日 上午9:54:52
	 * @author cht
	 * @Description: 获取gid和步长和容量
	 * @param url
	 * @param gidCode
	 * @return
	 * @throws Exception
	 *
	 */
	private String getGidSizeFromServer(String url, String gidCode) throws Exception {
		// 创建HttpClient实例
		String getURL = url + PARAM_NAME + gidCode;
		String result = null;
		URL getUrl = null;
		HttpURLConnection connection = null;
		BufferedReader reader = null;
		try {
			getUrl = new URL(getURL);
			connection = (HttpURLConnection) getUrl.openConnection();
			connection.connect();
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String lines;

			while ((lines = reader.readLine()) != null) {
				result = lines;
			}
		} catch (IOException e) {
			//e.printStackTrace();
			logger.error("connect error serverUrl:{},gidCode:{}", url, gidCode, e);
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					//e.printStackTrace();
					logger.error("close reader error serverUrl:{},gidCode:{}", url, gidCode, e);
				}
			}

			if (connection != null) {
				connection.disconnect();
			}
		}
		return result;
	}

	class FillerThread implements Runnable{
		
		private boolean flag = true;
		
		private GidVo gidVo;
		private BlockingQueue<Long> queue;
		
		FillerThread(GidVo gidVo,BlockingQueue<Long> queue){
			
			this.gidVo = gidVo;
			this.queue = queue;
			
		}
		
		@Override
        public void run() {
			while(flag){
				try{
					int size = queue.size();
					if(size<gidVo.getClientCacheSize()*scale){
						//logger.debug("装填一次，fillGidPool,gidCode:{}",gidVo.getGidCode());
						fillGidPool(gidVo,queue);
					}
				}catch(Exception e){
					logger.error("发生异常,gidCode:{}",gidVo.getGidCode(),e);
				}
				try{
					Thread.sleep(sleepTime);
				}catch(Exception e){
					logger.error("休眠异常,gidCode:{}",gidVo.getGidCode(),e);
				}
			}
		}

	}


	class GidVo {
		private String gidCode;
		private int clientCacheSize;
		private int increamentBy;

		String getGidCode() {
			return gidCode;
		}

		void setGidCode(String gidCode) {
			this.gidCode = gidCode;
		}

		int getIncreamentBy() {
			return increamentBy;
		}

		void setIncreamentBy(int increamentBy) {
			this.increamentBy = increamentBy;
		}

		int getClientCacheSize() {
			return clientCacheSize;
		}

		void setClientCacheSize(int clientCacheSize) {
			this.clientCacheSize = clientCacheSize;
		}

	}
	
	public static void main(String args[]){
		GidClientManager inst = GidClientManager.getInstance();
		logger.info("gidValue:{}",inst.getGidValue("TEST_SEQ"));
	}

}



