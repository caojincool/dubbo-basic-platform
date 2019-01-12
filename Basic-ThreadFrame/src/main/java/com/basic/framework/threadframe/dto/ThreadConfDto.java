/**
 *
 * companyName
 * copyright 2015-2020
 * @date 2016年8月3日 下午2:58:18
 * @author zwj
 * @Description: 查询线程的配置信息
 * 
 */
package com.basic.framework.threadframe.dto;

import com.basic.framework.threadframe.api.Consumer;
import com.basic.framework.threadframe.api.Producer;

/**
 *
 * @date 2016年8月3日 下午2:58:18
 * @author zwj
 * @Description: 查询线程的配置信息
 * 
 */
public class ThreadConfDto {

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

	private Consumer consumer;// 生产者业务逻辑
	private Producer producer;// 消费者业务逻辑


	public String getProcessCode() {
		return processCode;
	}

	public void setProcessCode(String processCode) {
		this.processCode = processCode;
	}

	public String getThreadGroupName() {
		return threadGroupName;
	}

	public void setThreadGroupName(String threadGroupName) {
		this.threadGroupName = threadGroupName;
	}

	public int getProduceCount() {
		return produceCount;
	}

	public void setProduceCount(int produceCount) {
		this.produceCount = produceCount;
	}

	public int getBufferSize() {
		return bufferSize;
	}

	public void setBufferSize(int bufferSize) {
		this.bufferSize = bufferSize;
	}

	public String getDispatchMode() {
		return dispatchMode;
	}

	public void setDispatchMode(String dispatchMode) {
		this.dispatchMode = dispatchMode;
	}

	public long getSleepTime() {
		return sleepTime;
	}

	public void setSleepTime(long sleepTime) {
		this.sleepTime = sleepTime;
	}

	public long getErrSleepTime() {
		return errSleepTime;
	}

	public void setErrSleepTime(long errSleepTime) {
		this.errSleepTime = errSleepTime;
	}

	public long getNoDataSleepTime() {
		return noDataSleepTime;
	}

	public void setNoDataSleepTime(long noDataSleepTime) {
		this.noDataSleepTime = noDataSleepTime;
	}

	public long getOverSleepTime() {
		return overSleepTime;
	}

	public void setOverSleepTime(long overSleepTime) {
		this.overSleepTime = overSleepTime;
	}

	public int getProducerThreadCount() {
		return producerThreadCount;
	}

	public void setProducerThreadCount(int producerThreadCount) {
		this.producerThreadCount = producerThreadCount;
	}

	public int getConsumerThreadCount() {
		return consumerThreadCount;
	}

	public void setConsumerThreadCount(int consumerThreadCount) {
		this.consumerThreadCount = consumerThreadCount;
	}

	public Consumer getConsumer() {
		return consumer;
	}

	public void setConsumer(Consumer consumer) {
		this.consumer = consumer;
	}

	public Producer getProducer() {
		return producer;
	}

	public void setProducer(Producer producer) {
		this.producer = producer;
	}

}
