/**
 * companyName
 * copyright 2015-2020
 *
 * @date 2016年8月3日 下午3:55:24
 * @author zwj
 * @Description: 处理线程
 */
package com.basic.framework.threadframe.thread;

import java.util.concurrent.LinkedBlockingQueue;

import com.basic.framework.threadframe.api.Consumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.basic.framework.threadframe.dto.MsgAbstractDto;
import com.basic.framework.threadframe.dto.ThreadConfDto;

/**
 *
 * @date 2016年8月3日 下午3:55:24
 *  
 * @Description: 消费者线程
 *
 */
public class ConsumerThread implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(ConsumerThread.class);
    private boolean activeFlag;

    private LinkedBlockingQueue<MsgAbstractDto> msgQueue;//空闲的处理线程队列

    private MsgAbstractDto msgDto;//待处理的数据

    private String processCode;// 进程id
    private String threadGroupName;// 线程组名称
    private int threadIndex;// 线程序号
    private int threadCount;// 线程数量

    private String threadAllName;//当前线程名称

    private Consumer consumer;//处理业务逻辑


    /**
     *
     * @param threadConfDto
     * @param threadIndex
     * @param msgQueue
     * @throws Exception
     */
    ConsumerThread(ThreadConfDto threadConfDto, int threadIndex, LinkedBlockingQueue<MsgAbstractDto> msgQueue) throws Exception {

        this.activeFlag = true;

        this.processCode = threadConfDto.getProcessCode();
        this.threadIndex = threadIndex;
        this.threadCount = threadConfDto.getConsumerThreadCount();
        this.threadGroupName = threadConfDto.getThreadGroupName();
        this.consumer = threadConfDto.getConsumer();
        this.msgQueue = msgQueue;

        this.makeThreadAllName();
        this.initConsumer();
        logger.info("消费者线程初始化完成......threadAllName:{}", threadAllName);
    }

    private void makeThreadAllName() {
        this.threadAllName = processCode + "-" + threadGroupName + "_dealThread:[" + threadIndex + "/" + threadCount + "]";
    }

    private void initConsumer() throws Exception {
        this.consumer.init(processCode, threadGroupName, threadIndex, threadCount);
    }

    @Override
    public void run() {
        Thread.currentThread().setName(threadAllName);//设置线程名，方便以后监控
        while (activeFlag){
            try{
                this.msgDto = msgQueue.take();
                this.excute();
            }catch (Throwable t){
                logger.error("{} 消费异常!,msgDto:{}",threadAllName,msgDto,t);
            }
        }

    }

    public void excute() {
        msgDto.setBeginConsumeTime(System.currentTimeMillis());
        try {//处理数据
            consumer.consumeMsg(msgDto);
        } catch (Throwable t) {
            logger.error("{} 消费异常!,msgDto:{}",threadAllName,msgDto,t);
        }finally {
        }
        msgDto.setFinishConsumeTime(System.currentTimeMillis());
        if (logger.isDebugEnabled()){
            long produceCosts = msgDto.getBeginProduceTime() - msgDto.getFinishProduceTime();//生产消耗时间
            long dispatchCosts = msgDto.getBeginConsumeTime() - msgDto.getFinishProduceTime();//调度消耗时间
            long consumeCosts = msgDto.getFinishConsumeTime() - msgDto.getBeginConsumeTime();//消费消耗时间
            logger.debug("{},消息处理完成,produceCosts:{}ms,dispatchCosts:{}ms,consumeCosts:{}ms", threadAllName, produceCosts, dispatchCosts, consumeCosts);
        }
        this.msgDto = null;
    }

    /**
     * 获取正在消费的消息
     * @return
     */
    MsgAbstractDto getMsgDto(){
        return msgDto;
    }



}
