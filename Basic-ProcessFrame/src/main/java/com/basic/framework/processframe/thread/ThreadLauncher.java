/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2016年9月27日 下午3:17:12
 * @author Jerman
 * @Description: 线程启动器
 * 
 */
package com.basic.framework.processframe.thread;

import java.util.List;

import com.basic.framework.threadframe.api.Consumer;
import com.basic.framework.threadframe.api.Producer;
import com.basic.framework.threadframe.dto.DispatchMode;
import com.basic.framework.threadframe.dto.ThreadConfDto;
import com.basic.framework.threadframe.dto.ThreadState;
import com.basic.framework.threadframe.thread.ThreadManager;



/**
 * 
 *
 * @date 2017年7月14日 上午10:50:29
 * @author Kevin
 * @Description: 线程启动器
 *
 */
public class ThreadLauncher {

	private Producer producer; //生产者实现类
	
	private Consumer consumer; //消费者实现类

	private String processCode;//进程编码
	private String threadGroupName;// 线程组名称

	private int produceCount;//一次生产消息的数量
	private int bufferSize;//缓存区大小,每个消费都都有单独的缓存区，此处指定的是每个缓冲区的大小
	private String dispatchMode; //派发模式，负载均衡模式时派发到空闲消费者，hash散列模式可以保存同一分组的消息派给同一消费者

	private long sleepTime;// 正常生产一次后休眠时间
	private long errSleepTime;// 异常后休眠时间
	private long noDataSleepTime;// 没数据时休眠时间
	private long overSleepTime;//超过缓冲区后休眠时间

	private int producerThreadCount;// 生产者线程数量
	private int consumerThreadCount;// 消费者线程数量

	
	private boolean isLaunched = false;
	
	private ThreadManager threadManager;
	
	/**
	 * 
	 * @date 2017年7月14日 下午2:28:51
	 * @author Kevin
	 * @Description: 构造函数
	 * @param producer
	 * @param consumer
	 *
	 */
	public ThreadLauncher(Producer producer, Consumer consumer){
		this( producer,  consumer,  "1",  1,  50,
			 DispatchMode.BALANCE_MODE.getCode(),  1000,  1000,  1000,  1000);
	}
	
	public ThreadLauncher(Producer producer, Consumer consumer, String processCode,  int bufferSize,
			String dispatchMode, long sleepTime, long errSleepTime, long noDataSleepTime, long overSleepTime){
		this( producer,  consumer,  processCode,  1,  bufferSize,
			 dispatchMode,  sleepTime,  errSleepTime,  noDataSleepTime,  overSleepTime);
	}
	/**
	 * 
	 * @date 2017年7月14日 下午2:22:21
	 * @author Kevin
	 * @Description: 构造
	 * @param producer
	 * @param consumer
	 * @param processCode
	 * @param produceCount
	 * @param bufferSize
	 * @param dispatchMode
	 * @param sleepTime
	 * @param errSleepTime
	 * @param noDataSleepTime
	 * @param overSleepTime
	 *
	 */
	
	public ThreadLauncher(Producer producer, Consumer consumer, String processCode, int produceCount, int bufferSize,
			String dispatchMode, long sleepTime, long errSleepTime, long noDataSleepTime, long overSleepTime){
		this( producer,  consumer,  processCode,  produceCount,  bufferSize,
			 dispatchMode,  sleepTime,  errSleepTime,  noDataSleepTime,  overSleepTime,10,10);
	}

	/**
	 * @date 2017年7月14日 下午12:18:39
	 * @author Kevin
	 * @Description: 构造函数
	 * @param producer
	 * @param consumer
	 * @param processCode
	 * @param produceCount
	 * @param bufferSize
	 * @param dispatchMode
	 * @param sleepTime
	 * @param errSleepTime
	 * @param noDataSleepTime
	 * @param overSleepTime
	 * @param producerThreadCount
	 * @param consumerThreadCount
	 * 
	 */
	public ThreadLauncher(Producer producer, Consumer consumer, String processCode, int produceCount, int bufferSize,
			String dispatchMode, long sleepTime, long errSleepTime, long noDataSleepTime, long overSleepTime,
			int producerThreadCount, int consumerThreadCount) {
		super();
		this.producer = producer;
		this.consumer = consumer;
		this.processCode = processCode;
		this.produceCount = produceCount;
		this.bufferSize = bufferSize;
		this.dispatchMode = dispatchMode;
		this.sleepTime = sleepTime;
		this.errSleepTime = errSleepTime;
		this.noDataSleepTime = noDataSleepTime;
		this.overSleepTime = overSleepTime;
		this.producerThreadCount = producerThreadCount;
		this.consumerThreadCount = consumerThreadCount;
	}



	
	/**
	 * 
	 * @date 2017年7月14日 下午2:47:46
	 * @author Kevin
	 * @Description: 启动线程。该方法只在第一次调用时起作用。
	 * @throws Exception
	 *
	 */
	public synchronized void launch() throws Exception {
		if(!this.isLaunched){
			threadManager = new ThreadManager(getConfig());
			threadManager.startThread();
			this.isLaunched = true;
		}
	}
	
	/**
	 * 
	 * @date 2017年7月14日 上午11:46:30
	 * @author Kevin
	 * @Description: 重新成为leader时,生产者继续生产
	 *
	 */
	public void reStart() {
		threadManager.reStartThread();
	}
	
	/**
	 * 
	 * @date 2017年7月14日 上午11:46:54
	 * @author Kevin
	 * @Description: 暂停
	 * @throws Exception
	 *
	 */
	public void pause() throws Exception {
		threadManager.pauseThread();
	}
	

	
	/**
	 * 
	 * @date 2017年7月14日 上午11:54:17
	 * @author Kevin 获取线程状态
	 * @return
	 *
	 */
	public List<ThreadState> getThreadState() {
		return threadManager.getThreadState();
	}
	
	/**
	 * 
	 * @date 2017年7月14日 上午11:06:55
	 * @author Kevin
	 * @Description: 获取线程配置
	 * @return
	 *
	 */
	private ThreadConfDto getConfig(){
		ThreadConfDto threadConfig = new ThreadConfDto();
		
		threadConfig.setProducer(this.producer); // 生产者业务逻辑
		threadConfig.setConsumer(this.consumer); // 消费者业务逻辑
		threadConfig.setThreadGroupName(this.producer.getClass().getSimpleName()); // 线程组名称
		threadConfig.setSleepTime(this.sleepTime);				// 正常生产一次后休眠时间
		threadConfig.setNoDataSleepTime(this.noDataSleepTime);	// 当前没有需要处理的消息，休眠时间
		threadConfig.setErrSleepTime(this.errSleepTime);		// 查询失败后休眠时间，此处的休眠有利于内存回收
		threadConfig.setProducerThreadCount(this.producerThreadCount);	// 查询线程个数
		threadConfig.setConsumerThreadCount(this.consumerThreadCount);	// 处理线程个数
		threadConfig.setBufferSize(this.bufferSize); //缓存区大小,每个消费都都有单独的缓存区，此处指定的是每个缓冲区的大小
		threadConfig.setDispatchMode(this.dispatchMode); //派发模式，负载均衡模式时派发到空闲消费者，hash散列模式可以保存同一分组的消息派给同一消费者
		threadConfig.setOverSleepTime(this.overSleepTime); //超过缓冲区后休眠时间
		threadConfig.setProcessCode(this.processCode); //进程编码
		threadConfig.setProduceCount(this.produceCount); //一次生产消息的数量
		return threadConfig;
	}

	

	/**
	 * @param processCode the processCode to set
	 */
	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}


	/**
	 * @param produceCount the produceCount to set
	 */
	public void setProduceCount(int produceCount) {
		this.produceCount = produceCount;
	}

	/**
	 * @param bufferSize the bufferSize to set
	 */
	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	/**
	 * @param dispatchMode the dispatchMode to set
	 */
	public void setDispatchMode(String dispatchMode) {
		this.dispatchMode = dispatchMode;
	}

	/**
	 * @param sleepTime the sleepTime to set
	 */
	public void setSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
	}

	/**
	 * @param errSleepTime the errSleepTime to set
	 */
	public void setErrSleepTime(long errSleepTime) {
		this.errSleepTime = errSleepTime;
	}

	/**
	 * @param noDataSleepTime the noDataSleepTime to set
	 */
	public void setNoDataSleepTime(long noDataSleepTime) {
		this.noDataSleepTime = noDataSleepTime;
	}

	/**
	 * @param overSleepTime the overSleepTime to set
	 */
	public void setOverSleepTime(long overSleepTime) {
		this.overSleepTime = overSleepTime;
	}

	/**
	 * @param producerThreadCount the producerThreadCount to set
	 */
	public void setProducerThreadCount(int producerThreadCount) {
		this.producerThreadCount = producerThreadCount;
	}

	/**
	 * @param consumerThreadCount the consumerThreadCount to set
	 */
	public void setConsumerThreadCount(int consumerThreadCount) {
		this.consumerThreadCount = consumerThreadCount;
	}

	

	
	
	

}
