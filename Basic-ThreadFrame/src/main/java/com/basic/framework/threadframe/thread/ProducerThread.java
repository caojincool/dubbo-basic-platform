/**
 * companyName
 * copyright 2015-2020
 *
 * @date 2016年8月3日 下午3:55:09
 * @author zwj
 * @Description: 查询线程
 */
package com.basic.framework.threadframe.thread;

import com.basic.framework.threadframe.api.Producer;
import com.basic.framework.threadframe.dto.MsgAbstractDto;
import com.basic.framework.threadframe.dto.ThreadConfDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;


/**
 * 查询线程
 */
public class ProducerThread implements Runnable {

    private static Logger logger = LoggerFactory.getLogger(ProducerThread.class);

    private boolean activeFlag;// 线程运行的标志
    private boolean pauseFlag;//暂停标识

    private String processCode;// 进程id
    private String threadGroupName;// 线程组名称
    private int threadIndex;// 线程序号
    private int threadCount;// 线程数量

    private long sleepTime;// 正常查询一次后休眠时间
    private long errSleepTime;// 异常后休眠时间
    private long noDataSleepTime;// 没数据时休眠时间
    private long overSleepTime;//buffer数据过多休眠
    private int produceCount;// 查询的数量

    private String threadAllName;// 线程全名 this.threadGroupName + "_qryThread:[readIndex + "/" + threadCount + "]";

    private Producer producer;// 查询逻辑
    private Dispatcher dispatcher;

    ProducerThread(ThreadConfDto threadConfDto, int threadIndex, Dispatcher dispatcher)
            throws Exception {

        this.activeFlag = false;
        this.pauseFlag = false;
        this.threadIndex = threadIndex;
        this.threadCount = threadConfDto.getProducerThreadCount();
        this.threadGroupName = threadConfDto.getThreadGroupName();
        this.processCode = threadConfDto.getProcessCode();

        this.sleepTime = threadConfDto.getSleepTime();
        this.errSleepTime = threadConfDto.getErrSleepTime();
        this.noDataSleepTime = threadConfDto.getNoDataSleepTime();
        this.overSleepTime = threadConfDto.getOverSleepTime();
        this.produceCount = threadConfDto.getProduceCount();
        this.producer = threadConfDto.getProducer();

        this.dispatcher = dispatcher;
        this.makeThreadAllName();
        this.initProducer();

        logger.info("生产者线程初始化完成......threadAllName:{}", threadAllName);
    }

    private void makeThreadAllName() {
        this.threadAllName = processCode + "-" + threadGroupName + "_qryThread:[" + threadIndex + "/" + threadCount + "]";
    }


    private void initProducer() throws Exception {
        this.producer.init(processCode, threadGroupName, threadIndex, threadCount);
    }


    @Override
    public void run() {
        Thread.currentThread().setName(threadAllName);// 设置线程名，方便以后监控
        activeFlag = true;
        while (activeFlag) {
            if (!pauseFlag) {//暂停标识
                if(dispatcher.canDispatch()){
                    execute();
                }else{
                    logger.info("缓冲者消息过多,休眠{}ms",overSleepTime);
                    sleep(overSleepTime);
                }
            } else {
                logger.debug("生产者暂停中，休眠{}ms",sleepTime);
                sleep(sleepTime);
            }
        }
    }


    /**
     * @date 2016年8月3日 下午4:21:04
     * @author zwj
     * @Description: 查询数据
     */
    private void execute() {
        try {
            logger.debug("生产者开始生产");
            long beginProduceTime = System.currentTimeMillis();// 开始查询时间
            // 查询数据
            List<MsgAbstractDto> msgList = producer.produceMsg(processCode, threadGroupName, threadIndex, threadCount, produceCount);
            int msgSize = msgList !=null ? msgList.size():0;
            long finishProduceTime = System.currentTimeMillis();// 查询完成时间
            logger.debug("生产者生产完成,计划生成{},实现生产{},开始分配,costs:{}ms",produceCount,msgSize,(finishProduceTime-beginProduceTime));
            if (msgSize>0) {// 有数据
                for(MsgAbstractDto msgDto:msgList){
                    msgDto.setBeginProduceTime(beginProduceTime);
                    msgDto.setFinishProduceTime(finishProduceTime);
                    this.dispatcher.dispatchMsg(msgDto);
//                    logger.debug("分配消息");
               }
                logger.debug("正常生产中，休眠{}ms",sleepTime);
                sleep(sleepTime);// 正常休息
            } else {
                logger.debug("没有数据，休眠{}ms",noDataSleepTime);
                sleep(noDataSleepTime);// 没数据,休息一下
            }
        } catch (Throwable e) {
            logger.error("生产异常！！！！！！！threadAllName:{}", threadAllName, e);
            sleep(errSleepTime);// 发生异常休眠,如果是网络等问题,过一段时间可能可以恢复
        }
    }



    /**
     * @param sTime
     * @date 2016年8月3日 下午4:17:34
     * @author zwj
     * @Description: 线程休眠
     */
    private void sleep(long sTime) {
        try {
            Thread.sleep(sTime);
        } catch (Throwable e) {
            logger.error("sleep ERROR! threadAllName:{}", threadAllName, e);
        }
    }

    /**
     * 暂停
     */
    void pause(){
        this.pauseFlag = true;
        logger.info("pauseFlag OK! threadAllName:{}", threadAllName);

    }

    /**
     * 暂停恢复
     */
    void reStart(){
        this.pauseFlag = false;
        logger.info("reStart OK! threadAllName:{}", threadAllName);

    }

    public String getStateName(){
        if(activeFlag){
            if(pauseFlag){
                return "pause";
            }else{
                return "running";
            }
        }else{
            return "not running";
        }
    }


}
