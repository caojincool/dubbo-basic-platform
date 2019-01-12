package com.basic.framework.threadframe.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.basic.framework.threadframe.dto.MsgAbstractDto;
import com.basic.framework.threadframe.dto.ThreadConfDto;
import com.basic.framework.threadframe.dto.ThreadState;
import com.basic.framework.threadframe.dto.ThreadType;

/**
 * Created by lzj on 2017/6/28.
 */
public class ThreadManager {

    private static final Logger logger = LoggerFactory.getLogger(ThreadManager.class);

    private final ThreadConfDto threadConfDto;

    private ThreadPoolExecutor producerThreadExecutor;//查询线程执行器
    private ThreadPoolExecutor consumerThreadExecutor;//处理线程执行器
    private ProducerThread[] producerThreadArr;//查询线程列表
    private ConsumerThread[] consumerThreadArr;//处理线程列表
    private Dispatcher dispatcher;//派发器
    private LinkedBlockingQueue<MsgAbstractDto>[] msgQueueArr;

    private boolean startRequestFlag;//防止接收多次请求

    public ThreadManager(ThreadConfDto threadConfDto) throws Exception {
        this.threadConfDto = threadConfDto;
        startRequestFlag = false;
        this.initMsgQueue();
        this.initDispatcher();
        this.initQryThread();
        this.initDealThread();
    }

    private void initMsgQueue(){
        int threadCount = threadConfDto.getConsumerThreadCount();
        msgQueueArr = new LinkedBlockingQueue[threadCount];
        for(int i=0;i<threadCount;i++){
            msgQueueArr[i] = new LinkedBlockingQueue<MsgAbstractDto>();
        }
    }

    private void initDispatcher(){
        int bufferSize = threadConfDto.getBufferSize();
        String dispatchMode= threadConfDto.getDispatchMode();
        dispatcher = new Dispatcher(msgQueueArr,bufferSize,dispatchMode);
    }


    /**
     * 初始化查询线程
     *
     * @throws Exception
     */
    private void initQryThread() throws Exception {
        int threadCount = threadConfDto.getProducerThreadCount();
        producerThreadArr = new ProducerThread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            ProducerThread thread = new ProducerThread(threadConfDto, i, dispatcher);
            producerThreadArr[i] = thread;
        }
        producerThreadExecutor = new ThreadPoolExecutor(
                threadCount,
                threadCount* 2, 60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }

    /**
     * 初始化处理线程
     *
     * @throws Exception
     */
    private void initDealThread() throws Exception {
        int threadCount = threadConfDto.getConsumerThreadCount();
        consumerThreadArr = new ConsumerThread[threadCount];
        for (int i = 0; i < threadCount; i++) {
            ConsumerThread thread = new ConsumerThread(threadConfDto, i, msgQueueArr[i]);
            consumerThreadArr[i] = thread;
        }
        // 初始化处理线程执行器
        consumerThreadExecutor = new ThreadPoolExecutor(
                threadCount,
                threadCount * 2, 60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>());
    }


    /**
     * 暂停线程
     */
    public synchronized void pauseThread() {
        for (ProducerThread producerThread : producerThreadArr) {
            producerThread.pause();
        }
    }

    /**
     * 暂停恢复
     */
    public synchronized void reStartThread() {
        for (ProducerThread producerThread : producerThreadArr) {
            producerThread.reStart();
        }
    }

    /**
     * 启动线程
     * 注意，启动只能在初始化之后，停止之后再启动是不行的。
     */
    public synchronized boolean startThread() {
        boolean successFlag = false;
        if(startRequestFlag){
            successFlag = true;
            logger.info("thread started already ....OK.... processCode:{},threadGroupName:{}", threadConfDto.getProcessCode(), threadConfDto.getThreadGroupName());
        }else{
            startRequestFlag = true;
            for(ConsumerThread consumerThread:consumerThreadArr){
                consumerThreadExecutor.execute(consumerThread);
            }
            for (ProducerThread producerThread : producerThreadArr) {
                producerThreadExecutor.execute(producerThread);
            }
            successFlag = true;
           logger.info("startThread ....OK.... processCode:{},threadGroupName:{}", threadConfDto.getProcessCode(), threadConfDto.getThreadGroupName());
        }
        return successFlag;
    }


    /**
     * 查询线程状态
     * @return
     */
    public List<ThreadState> getThreadState(){
        List<ThreadState> list = new ArrayList<ThreadState>();
        for(int i=0;i<producerThreadArr.length;i++){
            ThreadState threadState = new ThreadState();
            threadState.setThreadIndex(i);
            threadState.setThreadStateName(producerThreadArr[i].getStateName());
            threadState.setThreadType(ThreadType.PRODUCER_THREAD.getCode());
            list.add(threadState);
        }

        for(int i=0;i<consumerThreadArr.length;i++){
            ThreadState threadState = new ThreadState();
            threadState.setThreadIndex(i);
            threadState.setThreadType(ThreadType.CONSUMER_THREAD.getCode());
            if(consumerThreadArr[i].getMsgDto() != null){
            	threadState.setCurMsgId(consumerThreadArr[i].getMsgDto().getMsgId());
            }
            threadState.setDataBufferSize(msgQueueArr[i].size());
            list.add(threadState);
        }

        return list;
    }



}
