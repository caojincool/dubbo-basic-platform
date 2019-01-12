/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2017年7月14日 下午3:20:37
 * @author Kevin
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 */
package com.basic.framework.process.test.main;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.basic.framework.processframe.impl.ProcessElection;
import com.basic.framework.processframe.thread.ThreadLauncher;
import com.basic.framework.election.impl.ElectionImpl;
import com.basic.framework.election.intf.Election;
import com.basic.framework.process.test.impl.ConsumerBusiImpl;
import com.basic.framework.process.test.impl.ProducerBusiImpl;
import com.basic.framework.server.JettyServerMain;

/**
 *
 * @date 2017年7月14日 下午3:20:37
 * @author Kevin
 * @Description: TODO(这里用一句话描述这个类的作用)
 * 
 */
public class Main {
	
	private static final Logger logger = LoggerFactory.getLogger(Main.class);

	public static void main(String[] args) throws Exception {
		
		JettyServerMain.main(args);
		
		//配置路径
		String path = "/config/properties/inf_info_cfg.properties";
		Election election = new ElectionImpl();
		
		//线程启动器
		Map<String, ThreadLauncher> map = new HashMap<String, ThreadLauncher>();
		//XxArea 为业务名称 
		//对应配置
		//XxArea.produceCount=5         	每次查询数量
		//XxArea.sleepTime=1000				正常生产一次后休眠时间
		//XxArea.errSleepTime=1000			发生错误等待时间
		//XxArea.overSleepTime=5			超过缓冲区后休眠时间
		//XxArea.noDataSleepTime=10000		没有数据时等待时间
		//XxArea.producerThreadCount=5		生产者线程数量
		//XxArea.consumerThreadCount=5		消费者线程数量
		//XxArea.bufferSize=50				缓冲区大小
		
		map.put("XxArea", new ThreadLauncher( new ProducerBusiImpl(), new ConsumerBusiImpl()));
		map.put("XxArea2", new ThreadLauncher( new ProducerBusiImpl(), new ConsumerBusiImpl()));
		map.put("XxArea3", new ThreadLauncher( new ProducerBusiImpl(), new ConsumerBusiImpl()));
		
		//添加选举实例 ProcessElection为ElectionHandle 实现类
		election.addInstance("threadTest", new ProcessElection(path,map));
		
		logger.info("Process init success......");
		System.in.read();
	}
}
