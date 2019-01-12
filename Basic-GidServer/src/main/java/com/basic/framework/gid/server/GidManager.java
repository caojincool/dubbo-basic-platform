/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2015年12月5日 下午4:05:21
 * @author lzj
 * @Description: GID Manager
 * 
 */
package com.basic.framework.gid.server;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @date 2015年12月5日 下午4:05:21
 * @author lzj
 * @Description: GID Manager
 * 
 */
public class GidManager {

	private Logger logger = LoggerFactory.getLogger(GidFiller.class);

	private static GidManager inst = new GidManager();
	
	@Autowired
	private GidFiller gidFiller;
	@Autowired
	private GidDAO gidDAO;
	
	private GidPool gidPool;

	private GidManager(){
		
	}
	
	public static GidManager getInstance(){
		return inst;
	}
	
	public void setGidDAO(GidDAO gidDAO){
		this.gidDAO = gidDAO;
	}
	
	public void setGidFiller(GidFiller gidFiller){
		this.gidFiller = gidFiller;
	}
	
	public void init(){
		logger.error("GidManager init start......");
		
		gidPool = GidPool.getInstance();
		List<GidServerVo> gidVos = gidDAO.selectAllGid();
		gidPool.init(gidVos);
		
		gidFiller.init(gidVos);
		
		logger.error("GidManager init success......");
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
	public Long getGidValue(String gidCode) {
		Long gidValue = null;
		try {
			// 接收消息，参数：接收消息的超时时间，为0的话则不超时，receive返回下一个消息，但是超时了或者消费者被关闭，返回null
			gidValue = GidPool.getInstance().getGidValue(gidCode);
			if(gidValue.longValue() == -2L){//队列为空
				gidFiller.doFill(gidCode);//填充一次
				gidValue = GidPool.getInstance().getGidValue(gidCode);
			}
		} catch (Exception e) {
			logger.error("getGidValue error. gidCode:{}",gidCode,e);
			e.printStackTrace();
		}
		return gidValue;
	}

	/**
	 * @date 2016年7月30日 下午5:19:28
	 * @author cht
	 * @Description:从数据库得到客户端数据缓存容量
	 * @param gidCode
	 * @return
	 * 
	 */
	public int getClientCacheSize(String gidCode) {
		int cacheSize  = 0;
		cacheSize  = GidPool.getInstance().getClientCahceSize(gidCode);
		return cacheSize;
	}

	/**
	 * @date 2016年8月1日 上午10:09:36
	 * @author cht
	 * @Description: 获取客户端的步长
	 * @return
	 * 
	 */
	public int getIncreamentBy(String gidCode) {
		
		return GidPool.getInstance().getIncreamentBy(gidCode);
	}

}
