/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年3月29日 下午2:16:21
 * 
 * @Description: 实现了选举的进程
 * 
 */
package com.basic.framework.processframe.impl;

import java.util.Map;
import java.util.Properties;
import java.util.Set;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.basic.framework.processframe.thread.ThreadLauncher;
import com.basic.framework.common.utils.PropertiesUtils;
import com.basic.framework.election.intf.ElectionHandle;



/**
 *
 * @date 2017年3月29日 下午2:16:21
 * 
 * @Description: 实现了选举的进程
 * 
 */
public class ProcessElection implements ElectionHandle {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ProcessElection.class);
	public static boolean pauseFlag = false;//暂停标识,当前进程失去leader之后,设置为true
	public static Map<String,ThreadLauncher> map;
	private Properties properties;
	
	/**
	 * @date 2017年3月29日 上午11:32:27
	 * 
	 * @Description: 构造
	 * @param configPath 配置文件所在路径
	 * @throws Exception 
	 * 
	 */
	public ProcessElection(String configPath, Map<String,ThreadLauncher> map) throws Exception {
		this.init(configPath,map);
	}

	/**
	 * @date 2017年3月29日 上午11:33:38
	 * 
	 * @throws Exception 
	 * @Description: 初始化
	 * 
	 */
	private void init(String configPath, Map<String,ThreadLauncher> map) throws Exception {
//		File file = new File(config_path);
//		FileInputStream fis = new FileInputStream(file);
//		properties = new Properties();
//		properties.load(fis);

		properties = PropertiesUtils.loadProperties(configPath);
		
		ProcessElection.map = map;
		
		//setThreadLauncherMap(map);
		
	}
	
	/* (non-Javadoc)
	 * @see com.basic.framework.election.intf.ElectionHandle#isLeader()
	 */
	@Override
	public void isLeader() {
		LOGGER.error("=====================>机子成为leader");
		if (pauseFlag) {
			//暂停标识为真,证明当前线程曾经是leader,那么调用生产者的重启方法
			Set<String> keySet = map.keySet();
			ThreadLauncher threadLauncher;
			for(String key : keySet){
				threadLauncher = map.get(key);
				threadLauncher.reStart();
			}
		} else {
			Set<String> keySet = map.keySet();
			ThreadLauncher threadLauncher;
			for(String key : keySet){
				threadLauncher = map.get(key);
				setThreadLauncher(threadLauncher, key, properties);
				try {
					threadLauncher.launch();
				} catch (Exception e) {
					LOGGER.error("================>机子成为leader时,线程启动失败");
					e.printStackTrace();
				}
			}
		}
		
	}

	/* (non-Javadoc)
	 * @see com.basic.framework.election.intf.ElectionHandle#notLeader()
	 */
	@Override
	public void notLeader() {
		LOGGER.error("=====================>机子失去leader，暂停线程");
		Set<String> keySet = map.keySet();
		ThreadLauncher threadLauncher;
		for(String key : keySet){
			threadLauncher = map.get(key);
			try {
				threadLauncher.pause();
				pauseFlag = true;
			} catch (Exception e) {
				LOGGER.error("================>机子失去leader时,线程暂停失败");
				e.printStackTrace();
			}
		}
		
	}

	/**
	 * 
	 * @date 2017年7月14日 下午2:31:30
	 * @author Kevin
	 * @Description: 设置线程启动器参数
	 * @param threadLauncher
	 * @param busiName
	 * @param properties
	 *
	 */
	private static void setThreadLauncher(ThreadLauncher threadLauncher, String busiName, Properties properties){
		String sleepTime = properties.getProperty(busiName+".sleepTime");
		String noDataSleepTime = properties.getProperty(busiName+".noDataSleepTime");
		String errSleepTime = properties.getProperty(busiName+".errSleepTime");
		String producerThreadCount = properties.getProperty(busiName+".producerThreadCount");
		String consumerThreadCount = properties.getProperty(busiName+".consumerThreadCount");
		String bufferSize = properties.getProperty(busiName+".bufferSize");
		String dispatchMode = properties.getProperty(busiName+".dispatchMode");
		String overSleepTime = properties.getProperty(busiName+".overSleepTime");
		String produceCount = properties.getProperty(busiName+".produceCount");
		String processCode = properties.getProperty(busiName+".processCode");
		
		
		threadLauncher.setSleepTime(Integer.parseInt(sleepTime));				// 正常生产一次后休眠时间
		threadLauncher.setNoDataSleepTime(Integer.parseInt(noDataSleepTime));	// 当前没有需要处理的消息，休眠时间
		threadLauncher.setErrSleepTime(Integer.parseInt(errSleepTime));		// 查询失败后休眠时间，此处的休眠有利于内存回收
		threadLauncher.setProducerThreadCount(Integer.parseInt(producerThreadCount));	// 查询线程个数
		threadLauncher.setConsumerThreadCount(Integer.parseInt(consumerThreadCount));	// 处理线程个数
		threadLauncher.setBufferSize(Integer.parseInt(bufferSize)); //缓存区大小,每个消费都都有单独的缓存区，此处指定的是每个缓冲区的大小
		threadLauncher.setDispatchMode(dispatchMode); //派发模式，负载均衡模式时派发到空闲消费者，hash散列模式可以保存同一分组的消息派给同一消费者
		threadLauncher.setOverSleepTime(Integer.parseInt(overSleepTime)); //超过缓冲区后休眠时间
		threadLauncher.setProcessCode(processCode); //进程编码
		threadLauncher.setProduceCount(Integer.parseInt(produceCount)); //一次生产消息的数量
	}

	
	
	/**
	 * 
	 * @date 2017年3月29日 下午2:28:51
	 * 
	 * @Description: 停止线程
	 *
	 */
	public static  void stopThread() {
		LOGGER.error("=============>停止执行线程");
		Set<String> keySet = map.keySet();
		ThreadLauncher threadLauncher;
		for(String key : keySet){
			threadLauncher = map.get(key);
			try {
				threadLauncher.pause();
				pauseFlag = true;
			} catch (Exception e) {
				LOGGER.error("================>机子失去leader时,线程暂停失败");
				e.printStackTrace();
			}
		}
	}
	
}
