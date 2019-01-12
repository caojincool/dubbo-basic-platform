/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2015年12月7日 下午2:43:52
 * @author lzj
 * @Description: 填充Gid缓冲
 * 
 */
package com.basic.framework.gid.server;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @date 2015年12月7日 下午2:43:52
 * @author lzj
 * @Description: 填充Gid缓冲
 * 
 */
public class GidFiller {

	private Logger logger = LoggerFactory.getLogger(GidFiller.class);
	
	private static GidFiller inst = new GidFiller();
	
	private static ConcurrentMap<String, FillerThread> fillerThreadmap = new ConcurrentHashMap<String, FillerThread>();
	
	@Autowired
	private GidDAO gidDAO;

	private GidPool gidPool;
	
	
	private GidFiller() {
		
	}

	protected static GidFiller getInstance() {
		return inst;
	}
	
	public void setGidDAO(GidDAO gidDAO){
		this.gidDAO = gidDAO;
	}
	

	/**
	 * 初始化
	 * @date 2015年12月10日 下午3:42:41
	 * @author lzj
	 * @Description: 初始化
	 *
	 */
	protected void init(List<GidServerVo> gidVos){
		logger.error("GidFiller init start......");
		
		gidPool = GidPool.getInstance();
		
//		List<GidServerVo> gidVos = gidDAO.selectAllGid();
		
		for(GidServerVo gidVo:gidVos){
			//非0判断应该最初开始，不然会出现很多无效线程在跑
			//cacheSize为0的时候不应该进来
			//clientCacheSize为0，有可能造成队列里面（服务端缓存）全是0的数组或者1、2、3、4。。。
			int cacheSize = gidVo.getCacheSize();
			int clientCacheSize = gidVo.getClientCacheSize();
			if(cacheSize != 0 && clientCacheSize != 0){
				FillerThread fillerThread = new FillerThread(gidVo.getGidCode());
				Thread thread = new Thread(fillerThread,gidVo.getGidCode());
				thread.start();
				fillerThreadmap.put(gidVo.getGidCode(), fillerThread);
			}
		}
		
		UpdatorThread updatorThread = new UpdatorThread();
		Thread thread = new Thread(updatorThread);
		thread.start();
		
		logger.error("GidFiller init success......");
	}
	
	/**
	 * 填充
	 * @date 2015年12月7日 下午2:48:05
	 * @author lzj
	 * @Description: 单独填充   改造的处：for循环中循环次数参数改为客户端的容量CLIENT_CAPACITY
	 * @param gidFillerVo
	 * 
	 */
	protected void fillGid(String gidCode){
		GidServerVo gidVo = gidDAO.selectByCode(gidCode);
		
		List<Long> gidList = new ArrayList<Long>();
		
		long currValue = gidVo.getCurrValue();
		long targetValue = currValue;
		for(int i=0;i<gidVo.getCacheSize();i++){
			targetValue = targetValue + gidVo.getClientCacheSize();
			gidList.add(targetValue);
		}
		//更新
		int updateCount = gidDAO.updateCurrValue(targetValue, currValue, gidVo.getGidCode());
		
		boolean flag = false;
		if(updateCount > 0){//如果未更新，表示初始值不对，可能是被其它进程更新了。
			GidPool.getInstance().putGidValues(gidVo.getGidCode(), gidList, targetValue);			
			flag = true;
		}
		logger.info("填充缓冲池 fillGid flag:{} ...  gidCode:{},targetValue:{}",flag,gidVo.getGidCode(),targetValue);

	}
	
	protected void doFill(String gidCode){
		logger.debug("准备一次填充，开始判断，gidCode:{}",gidCode);
		boolean flag = gidPool.isLower(gidCode);
		if(flag){
			logger.debug("开始填充，gidCode:{}",gidCode);
			fillGid(gidCode);
		}else{
			logger.debug("未填充，gidCode:{}",gidCode);
		}
		
	}

	
	class FillerThread implements Runnable{
		
		private boolean flag = true;
		
		private String gidCode;
		FillerThread(String gidCode){
			this.gidCode = gidCode;
		}
		
		@Override
		public void run() {
			while(flag){
				try{
					doFill(gidCode);
				}catch(Exception e){
					logger.error("发生异常,gidCode:{}",gidCode,e);
				}
				try{
					Thread.sleep(Config.getFillSleepTime());
				}catch(Exception e){
					logger.error("休眠异常,gidCode:{}",gidCode,e);
				}
			}
		}
		

	}
	
	class UpdatorThread implements Runnable{
		
		private boolean flag = true;
		
		@Override
		public void run() {
			while(flag){
				try{
					doUpdate();
				}catch(Exception e){
					logger.error("发生异常",e);
				}
				try{
					Thread.sleep(Config.getUpdateSleepTime());
				}catch(Exception e){
					logger.error("休眠异常",e);
				}
			}
		}
		
		void doUpdate(){
			logger.debug("检查更新，doUpdate");
			//有效无效都要查询出来
			List<GidServerVo> list = gidDAO.selectAll();
			for(GidServerVo gidVo:list){
				//非0判断应该最初开始，不然会出现很多无效线程在跑
				//cacheSize为0的时候不应该进来
				//clientCacheSize为0，有可能造成队列里面（服务端缓存）全是0的数组或者1、2、3、4。。。
				int cacheSize = gidVo.getCacheSize();
				int clientCacheSize = gidVo.getClientCacheSize();
				if(cacheSize != 0 && clientCacheSize != 0){
					String gidCode = gidVo.getGidCode();
					boolean isExist = gidPool.isExist(gidCode);
					if(gidVo.getIsUse() == 1){//在用
						if(!isExist){//内存中没有
							gidPool.addGid(gidVo);
							
							FillerThread fillerThread = new FillerThread(gidVo.getGidCode());
							Thread thread = new Thread(fillerThread,gidVo.getGidCode());
							thread.start();
							fillerThreadmap.put(gidVo.getGidCode(), fillerThread);
						}
					}else {//不在用
						if(isExist){
							FillerThread thread = fillerThreadmap.get(gidCode);
							thread.flag = false;
							fillerThreadmap.remove(gidCode);
							gidPool.removeGid(gidCode);
							
						}
					}
				}
			}
		}

	}
	
}
