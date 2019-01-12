/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2015年12月3日 下午5:20:26
 * @author lzj
 * @Description: Gid 缓冲池
 * 
 */
package com.basic.framework.gid.server;

//import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;

import com.basic.framework.common.utils.datatype.JsonUtils;

/**
 *
 * @date 2015年12月3日 下午5:20:26
 * @author lzj
 * @Description: Gid 缓冲池
 * 
 */
public class GidPool {
	
	private Logger logger = LoggerFactory.getLogger(GidPool.class);
	
	private static final JsonUtils JSON_UTILS = JsonUtils.getInstance();
	
	private static GidPool inst = new GidPool();
	
	private static ConcurrentMap<String, BlockingQueue<Long>> gidPoolMap;
	
	private static ConcurrentMap<String, GidServerVo> gidVoMap;
	
	
	private GidPool(){
		
	}
	
	protected static GidPool getInstance(){
		return inst;
	}
	
	/**
	 * 初始化
	 * @date 2015年12月5日 下午3:26:16
	 * @author lzj
	 * @Description: Gid Pool 初始化
	 * @param gidVos
	 *
	 */
	protected void init(List<GidServerVo> gidVos){
		
		logger.info("GidPool init start......");
		
		if(logger.isDebugEnabled()){
			logger.info("GidPool init...... gidVos:{}", JSON_UTILS.objectToJson(gidVos));
		}
		
		gidPoolMap = new ConcurrentHashMap<String, BlockingQueue<Long>>();
		gidVoMap = new ConcurrentHashMap<String,GidServerVo>();
		
		for(GidServerVo gidVo:gidVos){
			logger.info("GidPool init...... gidCode:{}",gidVo.getGidCode());
			
			String gidCode = gidVo.getGidCode();
			//ArrayBlockingQueue队列的空间应该是两倍，不然到达水位再次填充的时候空间不够
			//cacheSize为0的时候不应该进来
			//clientCacheSize为0，有可能造成队列里面（服务端缓存）全是0的数组或者1、2、3、4。。。
			int cacheSize = gidVo.getCacheSize();
			int clientCacheSize = gidVo.getClientCacheSize();
			if(cacheSize != 0 && clientCacheSize != 0){
				BlockingQueue<Long> queue = new ArrayBlockingQueue<Long>(cacheSize*2);
				gidPoolMap.put(gidCode, queue);
				gidVoMap.put(gidCode, gidVo);
			}
		}
		
		logger.info("GidPool init success......");

	}
	
	/**
	 * 根据编码获取gid
	 * @date 2015年12月3日 下午5:25:28
	 * @author lzj
	 * @Description: 根据编码获取gid
	 * @param gidCode
	 * @return
	 *
	 */
	protected Long getGidValue(String gidCode) throws Exception{
		Long gidValue = null;
		BlockingQueue<Long> queue = gidPoolMap.get(gidCode);
		if(queue!=null){
			gidValue = queue.poll(Config.getGetTimeOut(),TimeUnit.MILLISECONDS);
			if(gidValue == null){
				gidValue=-2L; //队列为空
			}
		}else{
			gidValue=-1L;//未管理这个GID
		}
		return gidValue;
	}
	
	/**
	 * 将gid列表加入队列
	 * @date 2015年12月3日 下午5:30:26
	 * @author lzj
	 * @Description: 将gid列表加入队列
	 * @param gidCode
	 * @param gidValues
	 * @throws Exception
	 *
	 */
	protected void putGidValues(String gidCode,List<Long> gidValues, long targetValue) {
		BlockingQueue<Long> queue = gidPoolMap.get(gidCode);
		queue.addAll(gidValues);
		GidServerVo gidVo = gidVoMap.get(gidCode);
		gidVo.setCurrValue(targetValue);
		gidPoolMap.put(gidCode, queue);
//		gidVoMap.put(gidCode, gidVo);
	}
	
	/**
	 * 增加新的序列
	 * @date 2015年12月7日 下午2:20:12
	 * @author lzj
	 * @Description: 增加新的序列
	 * @param gidVo
	 *
	 */
	protected void addGid(GidServerVo gidVo){
		//ArrayBlockingQueue队列的空间应该是两倍，不然到达水位再次填充的时候空间不够
		//cacheSize为0的时候不应该进来
		//clientCacheSize为0，有可能造成队列里面（服务端缓存）全是0的数组或者1、2、3、4。。。
		int cacheSize = gidVo.getCacheSize();
		int clientCacheSize = gidVo.getClientCacheSize();
		if(cacheSize != 0 && clientCacheSize != 0){
			BlockingQueue<Long> queue = new ArrayBlockingQueue<Long>(cacheSize * 2);
			gidPoolMap.put(gidVo.getGidCode(), queue);
			gidVoMap.put(gidVo.getGidCode(), gidVo);
		}
	}
	
	/**
	 * 删除序列
	 * @date 2015年12月7日 下午2:20:22
	 * @author lzj
	 * @Description: 删除序列
	 * @param gidCode
	 *
	 */
	protected void removeGid(String gidCode){
		gidPoolMap.remove(gidCode);
		gidVoMap.remove(gidCode);
	}
	
	/**
	 * 是否存在
	 * @date 2015年12月10日 下午2:58:01
	 * @author lzj
	 * @Description: 是否存在
	 * @param gidCode
	 * @return
	 *
	 */
	protected boolean isExist(String gidCode){
		return gidVoMap.containsKey(gidCode);
	}
	
	/**
	 * 是否低于水位
	 * @date 2015年12月10日 下午3:36:23
	 * @author lzj
	 * @Description: 是否低于水位
	 * @param gidCode
	 * @return
	 *
	 */
	protected boolean isLower(String gidCode){
		boolean flag = false;
		
		BlockingQueue<Long> gidQueue = gidPoolMap.get(gidCode);
		if(gidQueue != null){
			int queueSize = gidQueue.size();
			
			GidServerVo gidVo = gidVoMap.get(gidCode);
			int cacheSize = gidVo.getCacheSize();
			
			if(queueSize < cacheSize * Config.getScale()){ //小于水位
				flag = true;
			}
		}
		return flag;
	}

	/**
	 * @date 2016年7月30日 下午5:22:05
	 * @author cht
	 * @Description: 从数据库获取客户端的步长.
	 * @param gidCode
	 * @return
	 * 
	 */
	public int getClientCahceSize(String gidCode) {
		int clientCacheSize  = 0;
		clientCacheSize = gidVoMap.get(gidCode).getClientCacheSize();
		return clientCacheSize;
	}

	/**
	 * @date 2016年8月1日 上午10:10:41
	 * @author cht
	 * @Description: 获取客户端的步长
	 * @param gidCode
	 * @return
	 * 
	 */
	public int getIncreamentBy(String gidCode) {
		return gidVoMap.get(gidCode).getIncreamentBy();
	}
	
//	/**
//	 * 获取id数量低于水位的队列
//	 * @date 2015年12月7日 上午10:54:12
//	 * @author lzj
//	 * @Description: 获取id数量低于水位的队列
//	 * @return
//	 *
//	 */
//	protected List<String> getLowerGidCode(){
//		List<String> gidCodeList = new ArrayList<String>();
//		
//		for(String gidCode:gidPoolMap.keySet()){
//			BlockingQueue<Long> gidQueue = gidPoolMap.get(gidCode);
//			int queueSize = gidQueue.size();
//			
//			GidVo gidVo = gidVoMap.get(gidCode);
//			int cacheSize = gidVo.getCacheSize();
//			
//			if(queueSize < cacheSize * Config.SCALE){ //小于水位
//				gidCodeList.add(gidVo.getGidCode());
//			}
//		}
//		
//		return gidCodeList;
//	}
	
	
}
